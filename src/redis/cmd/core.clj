(ns redis.cmd.core
  (:require [clojure.string :as str]
            [tick.core :as t]))


(defn ping
  "This command returns the bulk string PONG if no argument is provided, 
   otherwise it returns a copy of the provided message as a bulk string.
   This command is useful for:
   
   - Testing whether a connection is still alive.
   - Verifying the server's ability to serve data - an error is returned 
   when this isn't the case (for example, during load from persistence
   data or accessing a stale replica).
   - Measuring latency.
   
   If the client is subscribed to a channel or a pattern, it will 
   instead return a multi-bulk string with PONG in the first position 
   and an empty bulk string in the second position, unless an argument 
   is provided, in which case it returns a copy of the argument."
  [_state _msg] :PONG)

(defn echo
  "Returns message."
  [_state msg]
  (cond-> (second msg) keyword? name))

(defn get-option [msg opt]
  (reduce (fn [acc el]
            (cond
              (= (str/lower-case el) (str/lower-case opt)) true
              :else acc))
          false msg))

(defn get-arg [msg arg]
  (let [res
        (reduce (fn [acc el]
                  (cond
                    (= (str/lower-case el) (str/lower-case arg)) :next-element
                    (= :next-element acc) el
                    :else acc))
                nil msg)]
    (assert (not= res :next-element) "ERR syntax error")
    res))

(defn remove-expired! [state]
  (loop [top (-> @state :ttl peek)]
    (when-some [[top-key top-time] top]
      (when (t/<= top-time (t/now))
        (swap! state update :ttl #(dissoc % top-key))
        (swap! state update :db #(dissoc % top-key))
        (recur (-> @state :ttl peek))))))

(defn rset!
  "Set key to hold the string value. If key already holds a value, it is overwritten, 
   regardless of its type. Any previous time to live associated with the key is discarded 
   on successful SET operation."
  [state msg]
  (remove-expired! state)
  (let [key (nth msg 1)
        val (nth msg 2)
        nx? (get-option msg "nx")
        xx? (get-option msg "xx")
        ifeq-value (get-arg msg "ifeq")
        ifne-value (get-arg msg "ifne")
        ifdeq-digest (get-arg msg "ifdeq")
        ifdne-digest (get-arg msg "ifdne")
        get? (get-option msg "get")
        seconds (some-> (get-arg msg "ex") parse-long)
        milliseconds (some-> (get-arg msg "px") parse-long)
        unix-time-seconds (some-> (get-arg msg "exat") parse-long)
        unix-time-milliseconds (some-> (get-arg msg "pxat") parse-long)
        expiration (cond
                     seconds (t/>> (t/now) (t/new-duration seconds :seconds))
                     milliseconds (t/>> (t/now) (t/new-duration milliseconds :millis))
                     unix-time-seconds (t/instant (t/new-duration unix-time-seconds :seconds))
                     unix-time-milliseconds (t/instant (t/new-duration unix-time-milliseconds :millis)))
        keepttl? (get-option msg "keepttl")
        previous-value (-> @state :db (get key))

        response
        (cond
          (and nx? (contains? (@state :db) key)) nil
          (and xx? ((complement contains?) (@state :db) key)) nil
          (and ifeq-value (not= previous-value ifeq-value)) nil
          (and ifne-value (= previous-value ifne-value)) nil
          (and ifdeq-digest (not= (format "%X" (hash previous-value)) ifdeq-digest)) nil
          (and ifdne-digest (= (format "%X" (hash previous-value)) ifdne-digest)) nil

          :else
          (do
            (if (or keepttl? (not expiration)) nil
                (swap! state update :ttl #(assoc % key expiration)))
            (swap! state update :db #(assoc % key val))
            :OK))]
    (if get? (or response previous-value) response)))

(defn rget!
  "Get the value of key. If the key does not exist, nil is returned. 
   An error is returned if the value stored at key is not a string, 
   because GET only handles string values."
  [state msg]
  (remove-expired! state)
  (let [key (nth msg 1)
        val (-> @state :db (get key))]
    (assert (or (nil? val) (string? val)) "ERR value present at key is not a string")
    val))


(defn digest
  "Get the hash digest for the value stored in the specified key as a hexadecimal string. Keys must be of type string."
  [state msg]
  (assert (> 1 (count msg)) "ERR wrong number of arguments for 'digest' command")
  (remove-expired! state)
  (let [key (cond-> (second msg) string? keyword)]
    (when-some [val (-> @state :db (get key))]
      (assert (string? val) "value present is not a string")
      (format "%X" (hash val)))))
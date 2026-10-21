(ns redis.cmd.utils
  (:require [clojure.string :as str]
            [tick.core :as t]))

(defn get-option
  "Check for presense of particular option in given msg"
  [msg opt]
  (reduce (fn [acc el]
            (cond
              (= (str/lower-case el) (str/lower-case opt)) true
              :else acc))
          false msg))

(defn get-arg
  [msg arg]
  (let [res
        (reduce (fn [acc el]
                  (cond
                    (= (str/lower-case el) (str/lower-case arg)) :next-element
                    (= :next-element acc) el
                    :else acc))
                nil msg)]
    (assert (not= res :next-element) "ERR syntax error")
    res))

(defn extract
  [[kind val] type]
  (assert (= kind type) "WRONGTYPE Operation against a key holding the wrong kind of value")
  val)

(defn remove-expired! [state]
  (loop [top (-> @state :ttl peek)]
    (when-some [[top-key top-time] top]
      (when (t/<= top-time (t/now))
        (swap! state update :ttl #(dissoc % top-key))
        (swap! state update :db #(dissoc % top-key))
        (recur (-> @state :ttl peek))))))

(defn get-abs-idx [idx n]
  (cond (= 0 n) 0
        (and (neg? idx) (neg? (+ n idx))) 0
        (neg? idx) (+ n idx)
        :else (min idx (dec n))))

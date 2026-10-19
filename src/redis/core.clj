(ns redis.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [clojure.string :as str]
            [redis.resp :as resp]
            [redis.cmd.connection :as rconn]
            [redis.cmd.string :as rstr]
            [tick.core :as t]
            [clojure.data.priority-map :refer [priority-map-by]]))

(import '[java.net ServerSocket])

(defn send-message
  "Send the given string message out over the given socket"
  [socket msg]
  (print "[RESPONSE]: ")
  (pr msg)
  (println)
  (flush)
  (let [writer (io/writer socket)]
    (.write writer msg)
    (.flush writer)))
   
(defn listen [sock handler]
  (let [in (.getInputStream sock)
        buf (byte-array 1024)]
    (loop [acc (java.io.ByteArrayOutputStream.)]
      (let [n (.read in buf)]
        (if (= -1 n)
          (.close sock)
          (do (.write acc buf 0 n)
              (let [data (.toByteArray acc)
                    requests (try
                               (resp/parse data)
                               (catch clojure.lang.ExceptionInfo _ nil))]
                (if (nil? requests) (recur acc)
                    (do (.reset acc)
                        (doseq [req requests]
                          (send-message sock (handler req)))
                        (recur acc))))))))))

(defn serve [port handler]
  (with-open [server-sock (ServerSocket. port)]
    (. server-sock (setReuseAddress true))
    (loop []
     (let [sock (.accept server-sock)]
       (future (listen sock handler)))
      (recur))))

(def state (atom {:db (hash-map) :ttl (priority-map-by t/<)}))

(defn handler
  [msg]
  (println "[REQUEST]: " msg)
  (let [cmd (case (str/lower-case (first msg))
              "ping" rconn/ping
              "echo" rconn/echo
              "digest" rstr/digest!
              "get" rstr/get!
              "set" rstr/set!
              "append" rstr/append!)
        response (try (cmd state msg)
                      (catch Throwable e e))]

    (resp/encode response)))

(defn -main
  [& _args]
  (serve 6379 handler))

(ns redis.core
  (:gen-class)
  (:require [clojure.java.io :as io]
            [redis.resp :as resp]
            [redis.handler :refer [handler]]))

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
  (println (str "listening at localhost:" port))
  (with-open [server-sock (ServerSocket. port)]
    (. server-sock (setReuseAddress true))
    (while true
     (let [sock (.accept server-sock)]
       (future (listen sock handler))))))

(defn -main
  [& _args]
  (serve 6379 handler))

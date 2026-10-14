(ns redis.core
  (:gen-class))

(require '[clojure.java.io :as io])
(import '[java.net ServerSocket])
(require '[redis.resp :as resp])

(defn send-message
  "Send the given string message out over the given socket"
  [socket msg]
  (let [writer (io/writer socket)]
    (.write writer msg)
    (.flush writer)))
   
(defn listen [sock handler]
  (let [in (.getInputStream sock)
        buf (byte-array 1024)]
    (loop [_ nil]
      (if (not= -1 (.read in buf))
        (->> buf resp/parse handler (send-message sock) recur)
        (.close sock)))))

(defn serve [port handler]
  (with-open [server-sock (ServerSocket. port)]
    ;; Since the tester restarts your program quite often, setting SO_REUSEADDR
    ;; ensures that we don't run into 'Address already in use' errors
    (. server-sock (setReuseAddress true))

    (loop []
     (let [sock (.accept server-sock)]
       (future (listen sock handler)))
      (recur))))

(defn handler
  [msg]
  (print msg)
  (flush)
  "+PONG\r\n")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; You can use print statements as follows for debugging, they'll be visible when running tests.
  ;; Uncomment the code below to pass the first stage
  (serve 6379 handler))

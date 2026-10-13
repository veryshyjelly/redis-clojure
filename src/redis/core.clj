(ns redis.core
  (:gen-class))

(require '[clojure.java.io :as io])
(import '[java.net ServerSocket])

(defn send-message
  "Send the given string message out over the given socket"
  [socket msg]
  (let [writer (io/writer socket)]
    (.write writer msg)
    (.flush writer)))

(defn serve [port handler]
  (with-open [server-sock (ServerSocket. port)]
    ;; Since the tester restarts your program quite often, setting SO_REUSEADDR
    ;; ensures that we don't run into 'Address already in use' errors
    (. server-sock (setReuseAddress true))

    (with-open [sock (.accept server-sock)]
       (let [in (.getInputStream sock)
             buf (byte-array 1024)]
         (loop []
           (when (not= -1 (.read in buf))
             (->> buf handler (send-message sock)) 
             (recur)))))))

(defn handler
  [& args]
  (print args)
  "+PONG\r\n")

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  ;; You can use print statements as follows for debugging, they'll be visible when running tests.
  ;; Uncomment the code below to pass the first stage
  (serve 6379 handler))

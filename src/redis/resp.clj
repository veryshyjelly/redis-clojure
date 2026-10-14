(ns redis.resp)

(import '[java.nio ByteBuffer]
        '[java.nio.charset StandardCharsets])

;; Helper to convert a byte buffer slice directly into a UTF-8 String
(defn- buf->string [^ByteBuffer buf]
  (let [bytes (byte-array (.remaining buf))]
    (.get buf bytes)
    (String. bytes StandardCharsets/UTF_8)))

(defn- read-crlf [^ByteBuffer buf]
  (let [start (.position buf)
        limit (.limit buf)]
    (loop [i start]
      (cond
        (>= i (dec limit)) nil
        (and (= 13 (.get buf i)) (= 10 (.get buf (inc i))))
        (let [slice (-> buf .duplicate (.limit i) .slice buf->string)]
          (.position buf (+ i 2))
          slice)
        :else (recur (inc i))))))

(defn parse* [^ByteBuffer buf]
  (let [prefix (char (.get buf))]
    (case prefix
      \+ (read-crlf buf)
      \- {:error (read-crlf buf)}
      \: (parse-long (read-crlf buf))
      \$ (case (parse-long (read-crlf buf))
           -1 :nil
           (read-crlf buf))
      \* (let [n (parse-long (read-crlf buf))]
           (if (= -1 n) :nil
               (vec (repeatedly n #(parse* buf)))))
      \_ (do (read-crlf buf) :nil)
      \# (case (read-crlf buf) "t" true "f" false)
      \, (parse-double (read-crlf buf))
      \( (BigInteger. (read-crlf buf))
      \! {:error (let [len (read-crlf buf)] (read-crlf buf))}
      \= (let [n (parse-long (read-crlf buf))]
           (read-crlf buf))
      \% (let [n (parse-long (read-crlf buf))]
           (apply hash-map (repeatedly (* 2 n) #(parse* buf))))
      \| {:attributes (let [n (parse-long (read-crlf buf))]
                        (apply hash-map (repeatedly (* 2 n) #(parse* buf))))
          :value (parse* buf)}
      \~ (let [n (parse-long (read-crlf buf))]
           (if (= -1 n) :nil
               (set (repeatedly n #(parse* buf)))))
      :else  (throw (Exception. (str "Invalid RESP prefix: " prefix))))))

(defn parse [body] (parse* (ByteBuffer/wrap body)))

(defn encode [val] nil)

;; (def buf (byte-array (map (comp byte int) "*3\r\n$5\r\nhello\r\n$-1\r\n$5\r\nworld\r\n")))
;; (parse buf)
;; (def buw (ByteBuffer/wrap buf))
;; (buf->string (read-crlf buw))
;; (.position buw 0)
;; (.slice buw)
;; (char (.get buw))
;; (.limit buw)
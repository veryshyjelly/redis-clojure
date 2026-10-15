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
        (>= i (dec limit)) (throw (ex-info "Incomplete input" {:type :underflow}))
        (and (= 13 (.get buf i)) (= 10 (.get buf (inc i))))
        (let [slice (-> buf .duplicate (.limit i) .slice buf->string)]
          (.position buf (+ i 2))
          slice)
        :else (recur (inc i))))))

(defn parse* [^ByteBuffer buf]
  (let [prefix (char (.get buf))]
    (case prefix
      \+ (keyword (read-crlf buf))
      \- {:error (read-crlf buf)}
      \: (parse-long (read-crlf buf))
      \$ (case (parse-long (read-crlf buf))
           -1 nil
           (read-crlf buf))
      \* (let [n (parse-long (read-crlf buf))]
           (if (= -1 n) nil
               (vec (repeatedly n #(parse* buf)))))
      \_ (do (read-crlf buf) nil)
      \# (case (read-crlf buf) "t" true "f" false)
      \, (parse-double (read-crlf buf))
      \( (BigInteger. (read-crlf buf))
      \! {:error (let [_ (read-crlf buf)] (read-crlf buf))}
      \= (let [_ (parse-long (read-crlf buf))]
           (read-crlf buf))
      \% (let [n (parse-long (read-crlf buf))]
           (apply hash-map (repeatedly (* 2 n) #(parse* buf))))
      \| (let [atrributes (let [n (parse-long (read-crlf buf))]
                            (apply hash-map (repeatedly (* 2 n) #(parse* buf))))
               value (parse* buf)]
           (with-meta value atrributes))
      \~ (let [n (parse-long (read-crlf buf))]
           (if (= -1 n) nil
               (set (repeatedly n #(parse* buf)))))
      :else  (throw (Exception. (str "Invalid RESP prefix: " prefix))))))

(defn parse [body]
  (let [buf (ByteBuffer/wrap body)]
    (loop [messages []]
      (if (.hasRemaining buf)
        (recur (conj messages (parse* buf)))
        messages))))

(declare encode)

(defmulti encode-resp (fn [object] (:type object)))
(defmethod encode-resp :long [{v :value}] (str ":" v "\r\n"))
(defmethod encode-resp :big-int [{v :value}] (str "(" v "\r\n"))
(defmethod encode-resp :double [{v :value}] (str "," v "\r\n"))
(defmethod encode-resp :key [{v :value}] (str "+" (name v) "\r\n"))
(defmethod encode-resp :str [{v :value}] (str "$" (count v) "\r\n" v "\r\n"))
(defmethod encode-resp :array [{v :value}] (let [parts (map encode v)]
                                   (str "*" (count v) "\r\n" (apply str parts))))
(defmethod encode-resp :set [{v :value}] (let [parts (map encode v)]
                                  (str "~" (count v) "\r\n" (apply str parts))))
(defmethod encode-resp :map [{v :value}] (let [parts (map encode (reduce-kv conj [] v))]
                                  (str "%" (count v) "\r\n" (apply str parts))))
(defmethod encode-resp :error [{v :value}] (str "!" (count v) "\r\n" v "\r\n"))
(defmethod encode-resp :nil [_] "_\r\n")


(defn encode [val] 
  (let [type (cond
               (int? val) :long
               (double? val) :double
               (string? val) :str
               (list? val) :array
               (vector? val) :array
               (set? val) :set
               (map? val) :map
               (keyword? val) :key
               (nil? val) :nil)]
    (encode-resp {:type type :value val})))

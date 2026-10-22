(ns redis.handler
  (:require
   [tick.core :as t]
   [redis.resp :as resp]
   [clojure.string :as str]
   [redis.cmd.conn :as rconn]
   [redis.cmd.list :as rlist]
   [redis.cmd.string :as rstr]
   [redis.cmd.stream :as rstream]
   [redis.cmd.generic :as gen]
   [redis.cmd.utils :refer [remove-expired!]]
   [clojure.data.priority-map :refer [priority-map-by]]))

(def state {:db (ref (hash-map))
            :ttl (ref (priority-map-by t/<))})

(defn handler
  [msg]
  (println "[REQUEST]: " msg)
  (let [cmd (case (str/lower-case (first msg))
              ; connection commands
              "auth" rconn/auth!
              "client" rconn/client
              "echo" rconn/echo
              "hello" rconn/hello!
              "ping" rconn/ping
              "quit" rconn/quit!
              "reset" rconn/conn-reset!
              "select" rconn/select!
              ; string commands
              "append" rstr/append!
              "decr" rstr/decr!
              "decrby" rstr/decrby!
              "delex" rstr/delex!
              "digest" rstr/digest
              "get" rstr/rget
              "getdel" rstr/getdel!
              "getex" rstr/getex
              "getrange" rstr/getrange
              "getset" rstr/getset!
              "incr" rstr/incr!
              "incrby" rstr/incrby!
              "incrbyfloat" rstr/incrbyfloat!
              "increx" rstr/increx!
              "lcs" rstr/lcs!
              "mget" rstr/mget!
              "mset" rstr/mset!
              "msetex" rstr/msetex!
              "msetnx" rstr/msetnx!
              "psetex" rstr/psetex!
              "set" rstr/set!
              "setex" rstr/setex!
              "setnx" rstr/setnx!
              "setrange" rstr/setrange!
              "strlen" rstr/strlen!
              "substr" rstr/substr!
              ; list commands
              "blmove" rlist/blmove!
              "blmovem" rlist/blmovem!
              "blmpop" rlist/blmpop!
              "blpop" rlist/blpop!
              "brpop" rlist/brpop!
              "brpoplpush" rlist/brpoplpush!
              "lindex" rlist/lindex!
              "linsert" rlist/linsert!
              "llen" rlist/llen!
              "lmove" rlist/lmove!
              "lmovem" rlist/lmovem!
              "lmpop" rlist/lmpop!
              "lpop" rlist/lpop!
              "lpos" rlist/lpos!
              "lpush" rlist/lpush!
              "lpushx" rlist/lpushx!
              "lrange" rlist/lrange!
              "lrem" rlist/lrem!
              "lset" rlist/lset!
              "ltrim" rlist/ltrim!
              "rpop" rlist/rpop!
              "rpoplpush" rlist/rpoplpush!
              "rpush" rlist/rpush!
              "rpushx" rlist/rpushx!
              ; stream commands
              "xack" rstream/xack!
              "xackdel" rstream/xackdel!
              "xadd" rstream/xadd!
              "xautoclaim" rstream/xautoclaim!
              "xcfgset" rstream/xcfgset!
              "xclaim" rstream/xclaim!
              "xdel" rstream/xdel!
              "xdelex" rstream/xdelex!
              "xgroup" rstream/xgroup!
              "xidmprecord" rstream/xidmprecord!
              "xinfo" rstream/xinfo!
              "xlen" rstream/xlen!
              "xnack" rstream/xnack!
              "xpending" rstream/xpending!
              "xrange" rstream/xrange!
              "xread" rstream/xread!
              "xreadgroup" rstream/xreadgroup!
              "xrevrange" rstream/xrevrange!
              "xsetid" rstream/xsetid!
              "xtrim" rstream/xtrim!
              ; generic commands
              "copy" gen/copy!
              "del" gen/del!
              "dump" gen/dump!
              "exists" gen/exists!
              "expire" gen/expire!
              "expireat" gen/expireat!
              "expiretime" gen/expiretime!
              "keys" gen/keys!
              "migrate" gen/migrate!
              "move" gen/move!
              "object" gen/object!
              "persist" gen/persist!
              "pexpire" gen/pexpire!
              "pexpireat" gen/pexpireat!
              "pexpiretime" gen/pexpiretime!
              "pttl" gen/pttl!
              "randomkey" gen/randomkey!
              "rename" gen/rename!
              "renamenx" gen/renamenx!
              "restore" gen/restore!
              "scan" gen/scan!
              "sort" gen/sort!
              "sort-ro" gen/sort-ro!
              "touch" gen/touch!
              "ttl" gen/ttl!
              "type" gen/rtype
              "unlink" gen/unlink!
              "wait" gen/wait!
              "waitaof" gen/waitaof!)
        response (try (dosync
                       (remove-expired! state)
                       (cmd state msg))
                      (catch Throwable e e))]

    (resp/encode response)))
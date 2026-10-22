(ns redis.cmd.generic)

(defn copy!
  "Copies the value stored at the source key to the destination key.
   By default, the destination key is created in the logical database used by the current connection.
   The DB option allows specifying an alternative logical database index for the destination key.
   If the destination key already exists, the command returns zero and does not overwrite it.
   The REPLACE option removes the existing destination key before performing the copy.
   In clustered Redis environments, behaviour varies for multi-key operations.

   ```
   COPY source destination [DB destination-db] [REPLACE]
   ```

   source: the key to copy from.
   destination: the key to copy to.
   DB destination-db: copy the key to the given database index instead of the current one.
   REPLACE: replace the destination key if it already exists.

   Integer reply: 1 if source was copied.
   Integer reply: 0 if source was not copied."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn del!
  "Removes the specified keys from the dataset.
   A key that does not exist is silently ignored and does not cause an error.
   In a clustered Redis environment, the behavior of this command varies for multi-key operations.

   ```
   DEL key [key ...]
   ```

   key [key ...]: one or more keys to delete.

   Integer reply: the number of keys that were removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn dump!
  "Serializes the value stored at key in a Redis-specific opaque format and returns it to the caller.
   The returned value can be restored into a Redis key using the RESTORE command.
   The serialized value does not contain expire information; use the PTTL command to capture the time to live of the current value.
   If the key does not exist, a nil bulk reply is returned.

   ```
   DUMP key
   ```

   key: the name of the key to serialize.

   Bulk string reply: the serialized value of the key.
   Null reply: the key does not exist."
  [state msg]
  (let [key (nth msg 1)]
    (some-> @(state :db) (get key) second)))

(defn exists!
  "Returns the number of keys that exist from the specified keys.
   If the same key is specified multiple times, it is counted once per occurrence.
   In clustered Redis environments, behaviour varies for multi-key operations.

   ```
   EXISTS key [key ...]
   ```

   key [key ...]: one or more keys to check for existence, a repeated key is counted once per occurrence.

   Integer reply: the number of keys that exist from those specified as arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn expire!
  "Sets a timeout on the given key.
   After the timeout elapses, the key is automatically deleted.
   A key with an associated timeout is called volatile in Redis terminology.

   The timeout is cleared only by commands that delete or overwrite the key contents,
   such as DEL, SET, GETSET, and all *STORE commands.
   Operations that alter the value without replacing it, like INCR, LPUSH, or HSET,
   leave the timeout untouched.
   The PERSIST command can also clear the timeout, turning the key back into a persistent one.
   If a key is renamed with RENAME, the time to live is transferred to the new key name.
   If a key is overwritten by RENAME, the new key inherits all characteristics of the
   source key, regardless of whether the target had a timeout.

   Calling EXPIRE with a non-positive timeout results in the key being deleted
   immediately rather than expired.
   The optional modifiers NX, XX, GT, and LT are mutually exclusive.

   ```
   EXPIRE key seconds [NX | XX | GT | LT]
   ```

   key: the name of the key.
   seconds: the time to live in seconds, after which the key is deleted.
   NX: set the expiry only when the key has no expiry.
   XX: set the expiry only when the key already has an expiry.
   GT: set the expiry only when the new expiry is greater than the current one;
   a non-volatile key is treated as an infinite TTL.
   LT: set the expiry only when the new expiry is less than the current one;
   a non-volatile key is treated as an infinite TTL.

   Integer reply: 0 if the timeout was not set; for example, the key does not exist,
   or the operation was skipped because of the provided arguments.
   Integer reply: 1 if the timeout was set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn expireat!
  "Sets an expiration on a key using an absolute Unix timestamp in seconds.
   It has the same semantics as EXPIRE, but instead of a relative TTL it takes an absolute time (seconds since January 1, 1970).
   A timestamp in the past deletes the key immediately.
   The optional modifiers NX, XX, GT, and LT are mutually exclusive and control under what condition the expiry is actually applied.

   ```
   EXPIREAT key unix-time-seconds [NX | XX | GT | LT]
   ```

   key: the name of the key.
   unix-time-seconds: the absolute expiration time as a Unix timestamp in seconds, a timestamp in the past deletes the key immediately.
   NX: set the expiry only when the key has no expiry.
   XX: set the expiry only when the key already has an expiry.
   GT: set the expiry only when the new expiry is greater than the current one, a non-volatile key is treated as an infinite TTL for the purpose of GT.
   LT: set the expiry only when the new expiry is less than the current one, a non-volatile key is treated as an infinite TTL for the purpose of LT.

   Integer reply: 0 if the timeout was not set; for example, the key does not exist, or the operation was skipped because of the provided arguments.
   Integer reply: 1 if the timeout was set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn expiretime!
  "Returns the absolute Unix timestamp in seconds at which the given key will expire.
   If the key exists but has no associated expiration time, the command returns -1.
   If the key does not exist, the command returns -2.

   ```
   EXPIRETIME key
   ```

   key: the name of the key.

   Integer reply: the expiration Unix timestamp in seconds.
   Integer reply: -1 if the key exists but has no associated expiration time.
   Integer reply: -2 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn keys!
  "Returns all keys matching the given glob-style pattern.
   In clustered Redis environments the behavior of this command varies; see the multi-key operations documentation for details.

   ```
   KEYS pattern
   ```

   pattern: the glob-style pattern to match key names against.

   Array reply: a list of keys matching pattern."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn migrate!
  "Atomically transfers a key from the local Redis instance to a destination instance.
   On success the key is deleted from the source and is guaranteed to exist on the target.
   The transfer is atomic: at any given moment the key appears in exactly one of the two instances, unless a timeout error occurs.
   The command blocks both instances for the duration of the transfer.
   Multiple keys can be migrated in a single call by passing an empty string as the key argument and listing them with the KEYS option.

   ```
   MIGRATE host port <key | \"\"> destination-db timeout [COPY] [REPLACE]
     [AUTH password | AUTH2 username password] [KEYS key [key ...]]
   ```

   host: the hostname or IP address of the destination Redis instance.
   port: the port of the destination Redis instance.
   key or empty string: the key to migrate, or an empty string when migrating multiple keys with the KEYS option.
   destination-db: the database index to migrate the key into on the destination instance.
   timeout: the maximum idle time for the transfer, in milliseconds.
   COPY: do not remove the key from the local instance.
   REPLACE: replace an existing key on the destination instance.
   AUTH password: authenticate to the destination instance with the given password, mutually exclusive with AUTH2.
   AUTH2 username password: authenticate to the destination instance with the given username and password.
   KEYS key [key ...]: migrate all the listed keys, used when the key argument is an empty string.

   Simple string reply: OK on success.
   Simple string reply: NOKEY when no keys were found in the source instance."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn move!
  "Moves a key from the currently selected database to the specified destination database.
   If the key does not exist in the source database, or if it already exists in the destination database, the command does nothing.
   This atomic, no-overwrite behaviour makes MOVE usable as a locking primitive.

   ```
   MOVE key db
   ```

   key: the name of the key.
   db: the destination database index.

   Integer reply: 1 if key was moved.
   Integer reply: 0 if key was not moved."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn object! 
  "Dispatcher for object commands"
  [] 
  (throw (Exception. "Not Implemented")))

(defn object-encoding!
  "Returns the internal encoding for the Redis object stored at key.
   If the key does not exist, a null reply is returned.

   ```
   OBJECT ENCODING key
   ```

   key: the name of the key.

   Null reply: if the key doesn't exist.
   Bulk string reply: the encoding of the object."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn object-freq!
  "Returns the logarithmic access frequency counter of the object stored at key.
   This command is only available when the maxmemory-policy configuration directive is set to one of the LFU policies.

   ```
   OBJECT FREQ key
   ```

   key: the name of the key.

   Integer reply: the counter's value.
   Null reply: if key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn object-idletime!
  "Returns the time in seconds since the last access to the value stored at key.
   The command is only available when the maxmemory-policy configuration directive is not set to one of the LFU policies.
   If the key does not exist, a null reply is returned.

   ```
   OBJECT IDLETIME key
   ```

   key: the name of the key.

   Integer reply: the idle time in seconds.
   Null reply: if key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn object-refcount!
  "Returns the reference count of the value stored at key.
   If the key does not exist, a null reply is returned.

   ```
   OBJECT REFCOUNT key
   ```

   key: the name of the key.

   Integer reply: the number of references.
   Null reply: if key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn persist!
  "Removes the existing timeout on a key,
   turning it from a volatile key (one with an associated expire)
   to a persistent key (one that will never expire).
   If the key does not exist or has no associated timeout,
   the command has no effect.

   ```
   PERSIST key
   ```

   key: the name of the key.

   Integer reply: 0 if key does not exist or does not have an associated timeout.
   Integer reply: 1 if the timeout has been removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pexpire!
  "Sets a timeout on the key, with the time to live specified in milliseconds.
   This is the millisecond-precision equivalent of EXPIRE.
   If the key does not exist, the timeout is not set and the command returns 0.
   The optional condition modifiers are mutually exclusive and control under what circumstances the timeout is actually applied.
   NX sets the expiry only if the key currently has no expiry.
   XX sets the expiry only if the key already has an expiry.
   GT sets the expiry only if the new expiry is greater than the current one; a key with no expiry is treated as having an infinite TTL.
   LT sets the expiry only if the new expiry is less than the current one; a key with no expiry is treated as having an infinite TTL.

   ```
   PEXPIRE key milliseconds [NX | XX | GT | LT]
   ```

   key: the name of the key to set a timeout on.
   milliseconds: the timeout, in milliseconds.
   NX: set expiry only when the key has no expiry.
   XX: set expiry only when the key has an existing expiry.
   GT: set expiry only when the new expiry is greater than the current one, a non-volatile key is treated as an infinite TTL.
   LT: set expiry only when the new expiry is less than the current one, a non-volatile key is treated as an infinite TTL.

   Integer reply: 0 if the timeout was not set, for example if the key does not exist or the operation was skipped because of the provided arguments.
   Integer reply: 1 if the timeout was set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pexpireat!
  "PEXPIREAT sets an expiration on a key using an absolute Unix timestamp expressed in milliseconds.
   It has the same semantics as EXPIREAT, except the timestamp unit is milliseconds rather than seconds.
   If the supplied timestamp is in the past, the key is deleted immediately.
   If the key does not exist, the command does nothing and returns 0.
   The optional modifiers NX, XX, GT, and LT are mutually exclusive and restrict when the expiry is actually applied.

   ```
   PEXPIREAT key unix-time-milliseconds [NX | XX | GT | LT]
   ```

   key: the name of the key to set a timeout on.
   unix-time-milliseconds: the absolute Unix expiration timestamp in milliseconds since the Unix epoch; a timestamp in the past deletes the key immediately.
   NX: set expiry only when the key has no expiry.
   XX: set expiry only when the key has an existing expiry.
   GT: set expiry only when the new expiry is greater than the current one; a non-volatile key is treated as an infinite TTL for this comparison.
   LT: set expiry only when the new expiry is less than the current one; a non-volatile key is treated as an infinite TTL for this comparison.

   Integer reply: 1 if the timeout was set.
   Integer reply: 0 if the timeout was not set, for example if the key does not exist or the operation was skipped due to the provided arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pexpiretime!
  "Returns the absolute Unix expiration timestamp of a key in milliseconds.
   If the key exists but has no associated expiration time, the command returns -1.
   If the key does not exist, the command returns -2.

   ```
   PEXPIRETIME key
   ```

   key: the name of the key.

   Integer reply: Expiration Unix timestamp in milliseconds.
   Integer reply: -1 if the key exists but has no associated expiration time.
   Integer reply: -2 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn pttl!
  "Returns the remaining time to live of a key that has an expiration set, expressed in milliseconds.
   If the key does not exist, the command returns -2.
   If the key exists but has no associated expiration, the command returns -1.

   ```
   PTTL key
   ```

   key: the name of the key.

   Integer reply: TTL in milliseconds.
   Integer reply: -1 if the key exists but has no associated expiration.
   Integer reply: -2 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn randomkey!
  "Returns a random key from the currently selected database.
   If the database is empty, a null reply is returned.

   ```
   RANDOMKEY
   ```

   Null reply: when the database is empty.
   Bulk string reply: a random key in the database."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rename!
  "Renames key to newkey.
   Returns an error when key does not exist.
   If newkey already exists it is overwritten, executing an implicit DEL operation.
   If the deleted key contains a very big value it may cause high latency even though RENAME is usually a constant-time operation.
   In Cluster mode, both key and newkey must be in the same hash slot, meaning only keys with the same hash tag can be reliably renamed.

   ```
   RENAME key newkey
   ```

   key: the key to rename.
   newkey: the new key name, an existing key with this name is overwritten.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn renamenx!
  "Renames key to newkey if newkey does not yet exist.
   Returns an error when key does not exist.
   In Cluster mode, both key and newkey must be in the same hash slot,
   meaning that in practice only keys that have the same hash tag can be reliably renamed.

   ```
   RENAMENX key newkey
   ```

   key: the key to rename.
   newkey: the new key name, the command fails if a key with this name already exists.

   Integer reply: 1 if key was renamed to newkey.
   Integer reply: 0 if newkey already exists."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn restore!
  "Creates a key by deserializing the provided serialized value, which must have been produced by the DUMP command.
   If ttl is 0 the key is created without an expiration; otherwise the value is treated as a relative time-to-live in milliseconds.
   When the ABSTTL modifier is present, ttl is interpreted as an absolute Unix timestamp in milliseconds at which the key expires.
   The IDLETIME and FREQ modifiers allow setting the key idle time and access-frequency counter respectively, for use with LRU and LFU eviction policies.
   If the key already exists, the command returns a Target key name is busy error unless the REPLACE modifier is used.
   The command validates the RDB version and data checksum embedded in the serialized value; a mismatch produces an error.

   ```
   RESTORE key ttl serialized-value [REPLACE] [ABSTTL]
     [IDLETIME seconds] [FREQ frequency]
   ```

   key: the name of the key to create.
   ttl: the time to live for the key in milliseconds, 0 means no expiration, with ABSTTL this is an absolute Unix timestamp in milliseconds.
   serialized-value: the serialized value as produced by DUMP.
   REPLACE: replace the key if it already exists.
   ABSTTL: interpret ttl as an absolute Unix timestamp in milliseconds rather than a relative time.
   IDLETIME seconds: set the key idle time for LRU eviction to this many seconds.
   FREQ frequency: set the key access-frequency counter for LFU eviction to this value.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn scan!
  "SCAN incrementally iterates over the keys in the current database, returning a small number of elements per call.
   This avoids blocking the server for long periods, unlike commands such as KEYS that operate on the entire key space at once.
   Because the iteration is incremental, the collection of keys can change between calls, so the command offers only limited guarantees about which elements are returned.
   The command does not require a key name argument; the iterated object is the database itself.

   ```
   SCAN cursor [MATCH pattern] [COUNT count] [TYPE type]
   ```

   cursor: the cursor value, start with 0 and use the cursor returned by each call as the argument to the next call.
   MATCH pattern: only return keys matching the given glob-style pattern.
   COUNT count: a hint for the number of keys to return per iteration, defaults to 10.
   TYPE type: only return keys of the given type, such as string, list, or set.

   Array reply: specifically, an array with two elements.
   The first element is a Bulk string reply that represents an unsigned 64-bit number, the cursor.
   The second element is an Array reply with the names of scanned keys."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sort!
  "SORT returns or stores the elements contained in the list, set, or sorted set at key,
   sorted according to the specified modifiers.
   By default, elements are compared numerically as double precision floating point numbers
   and sorted in ascending order.
   The ALPHA modifier switches comparison to lexicographic order, and DESC reverses the sort direction.
   The BY modifier allows sorting by the values of external keys matched by a pattern
   instead of the elements themselves.
   The GET modifier can be used one or more times to return the values of external keys
   for each sorted element, or use the special pattern # to include the element itself.
   LIMIT restricts the result to a slice starting at offset and returning up to count elements.
   STORE writes the sorted result into the destination key instead of returning it.

   ```
   SORT key [BY pattern] [LIMIT offset count] [GET pattern [GET pattern
     ...]] [ASC | DESC] [ALPHA] [STORE destination]
   ```

   key: the name of the key to sort, which may hold a list, set, or sorted set.
   BY pattern: sort by the values of external keys matched by pattern (each * is replaced
     by an element) instead of by the elements themselves; a pattern with no * or a
     hash-field pattern that resolves the same for all elements skips sorting.
   LIMIT offset count: return a slice of the sorted result, skipping offset elements
     and returning up to count.
   GET pattern [GET pattern ...]: return the values of external keys matched by pattern
     for each sorted element; use GET # to also return the element itself; may be given
     multiple times.
   ASC | DESC: sort in ascending (default) or descending order.
   ALPHA: sort the elements lexicographically instead of numerically.
   STORE destination: store the resulting list in destination instead of returning it.

   Array reply: without passing the STORE option, the command returns a list of sorted elements.
   Integer reply: when the STORE option is specified, the command returns the number of
     sorted elements in the destination list."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn sort-ro!
  "SORT_RO is a read-only variant of the SORT command.
   It sorts the elements of a list, set, or sorted set and returns the result.
   Unlike SORT, it does not support the STORE option, so it can safely be executed on read-only replicas in a Redis Cluster without being redirected to the master.

   ```
   SORT_RO key [BY pattern] [LIMIT offset count] [GET pattern [GET
     pattern ...]] [ASC | DESC] [ALPHA]
   ```

   key: the name of the key to sort, which may hold a list, set, or sorted set.
   BY pattern: sort by the values of external keys matched by pattern (each * is replaced by an element) instead of by the elements themselves; a pattern with no * or a hash-field pattern that resolves the same for all elements skips sorting.
   LIMIT offset count: return a slice of the sorted result, skipping offset elements and returning up to count.
   GET pattern [GET pattern ...]: return the values of external keys matched by pattern for each sorted element; use GET # to also return the element itself; may be given multiple times.
   ASC | DESC: sort in ascending (ASC, the default) or descending (DESC) order.
   ALPHA: sort the elements lexicographically instead of numerically.

   Array reply: a list of sorted elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn touch!
  "Alters the last access time of one or more keys.
   A key that does not exist is silently ignored.
   In a clustered Redis environment the behavior of this command varies; see the multi-key operations documentation for details.

   ```
   TOUCH key [key ...]
   ```

   key [key ...]: one or more keys whose last-access time to update.

   Integer reply: the number of touched keys."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ttl!
  "Returns the remaining time to live, in seconds, of a key that has an expiration set.
   If the key exists but has no associated expiration, the command returns -1.
   If the key does not exist, the command returns -2.

   ```
   TTL key
   ```

   key: the name of the key.

   Integer reply: TTL in seconds.
   Integer reply: -1 if the key exists but has no associated expiration.
   Integer reply: -2 if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn rtype
  "Returns the string representation of the type of the value stored at key.
   The possible types are string, list, set, zset, hash, stream, and vectorset.
   If the key does not exist, the reply is none.

   ```
   TYPE key
   ```

   key: the name of the key.

   Simple string reply: the type of key, or none when key doesn't exist."
  [state msg]
  (let [key (nth msg 1)
        [kind _] (or (@(state :db) key) [:none nil])]
    kind))


(defn unlink!
  "Removes the specified keys from the keyspace.
   If a key does not exist it is silently ignored.
   Unlike DEL, the actual memory reclamation is performed asynchronously in a separate thread, so the command does not block the server while freeing memory.
   The command only unlinks the keys from the keyspace immediately; the underlying data is freed later.

   ```
   UNLINK key [key ...]
   ```

   key [key ...]: one or more keys to delete asynchronously.

   Integer reply: the number of keys that were unlinked."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn wait!
  "Blocks the current client until all previous write commands issued by this connection
   have been transferred and acknowledged by at least the specified number of replicas.
   If the timeout in milliseconds is reached before the required number of replicas
   has acknowledged, the command returns early.
   The command always returns the number of replicas that actually acknowledged the writes,
   whether the target was reached or the timeout expired.

   ```
   WAIT numreplicas timeout
   ```

   numreplicas: the number of replicas to wait for.
   timeout: the maximum time to wait in milliseconds, 0 means wait forever.

   Integer reply: the number of replicas reached by all the writes performed
   in the context of the current connection."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn waitaof!
  "WAITAOF blocks the current client until all previous write commands issued by that client are acknowledged as having been fsynced to the AOF of the local Redis instance and/or at least the specified number of replicas.
   When numlocal is set to 1, the command blocks until the data written to the Redis instance is confirmed to be persisted to the local AOF file.
   A value of 0 for numlocal disables the local fsync check.
   If the timeout, specified in milliseconds, is reached, the command returns even if the specified number of acknowledgments has not been met.
   The command always returns the number of local instances and replicas that have fsynced all write commands sent by the current client before the WAITAOF command, both when the thresholds are met and when the timeout is reached.

   ```
   WAITAOF numlocal numreplicas timeout
   ```

   numlocal: the number of local Redis instances to wait for an AOF fsync on, 0 or 1.
   numreplicas: the number of replicas to wait for an AOF fsync on.
   timeout: the maximum time to wait in milliseconds, 0 means wait forever.

   Array reply: the command returns an array of two integers.
   The first is the number of local Redises (0 or 1) that have fsynced to AOF all writes performed in the context of the current connection.
   The second is the number of replicas that have acknowledged doing the same."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

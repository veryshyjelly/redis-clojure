(ns redis.cmd.string
  (:require [tick.core :as t]
            [redis.cmd.utils :refer [get-arg get-option remove-expired! extract]]))

(defn append!
  "Appends a value to the string stored at key.
   If key does not exist, it is created with an empty string first,
   so in that case APPEND behaves like SET.

   ```
   APPEND key value
   ```

   key: the name of the key.
   value: the string to append to the existing value.

   Integer reply: the length of the string after the append operation."
  [state msg]
  (remove-expired! state)
  (let [key (nth msg 1)
        val (nth msg 2)
        previous-value (some-> @(state :db) (get key) (extract :string))
        val' (if previous-value (str previous-value val) val)]
    (alter (state :db) assoc key [:string val'])
    (count val')))

(defn decr!
  "Decrements the integer value stored at key by one.
   If the key does not exist, it is first set to 0 before the decrement is performed.
   An error is returned if the key holds a value of the wrong type or a string that cannot be parsed as an integer.
   The operation is limited to 64-bit signed integers.

   ```
   DECR key
   ```

   key: the name of the key.

   Integer reply: the value of the key after decrementing it."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn decrby!
  "DECRBY reduces the integer value stored at the given key by the specified decrement amount.
   If the key does not exist, it is first initialized to 0 before the subtraction is performed.
   If the value stored at the key is not an integer or the key holds a value of the wrong type, an error is returned.
   The operation is limited to 64-bit signed integers.

   ```
   DECRBY key decrement
   ```

   key: the name of the key.
   decrement: the integer amount to subtract from the value.

   Integer reply: the value of the key after decrementing it."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn delex!
  "Conditionally removes the specified key based on a value or hash digest comparison.
   If no condition option is specified, the key is removed regardless of its type.
   When a condition option is specified, the key must hold a string value; otherwise a simple error is returned.
   Only one condition option may be specified at a time.

   ```
   DELEX key [IFEQ ifeq-value | IFNE ifne-value | IFDEQ ifdeq-digest |
     IFDNE ifdne-digest]
   ```

   key: the name of the key to delete.
   IFEQ ifeq-value: delete the key only if its value is equal to ifeq-value.
   IFNE ifne-value: delete the key only if its value is not equal to ifne-value.
   IFDEQ ifdeq-digest: delete the key only if its hash digest is equal to ifdeq-digest.
   IFDNE ifdne-digest: delete the key only if its hash digest is not equal to ifdne-digest.

   Integer reply: 0 if not deleted (the key does not exist or a specified IFEQ/IFNE/IFDEQ/IFDNE condition is false), or 1 if deleted.
   Simple error reply if the key exists but holds a value that is not a string and one of IFEQ, IFNE, IFDEQ, or IFDNE is specified."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn digest!
  "Returns the hash digest of the value stored under the given key,
   encoded as a hexadecimal string.
   The key must hold a string value; if it holds any other type, a simple error is returned.
   If the key does not exist, a null bulk string is returned.

   ```
   DIGEST key
   ```

   key: the name of the key.

   Null bulk string reply if the key does not exist.
   Simple error reply if the key exists but holds a value which is not a string.
   Bulk string reply the hash digest of the value stored in the key as a hexadecimal string."
  [state msg]
  (assert (> 1 (count msg)) "ERR wrong number of arguments for 'digest' command")
  (remove-expired! state)
  (let [key (cond-> (second msg) string? keyword)]
    (when-some [val (some-> @(state :db) (get key) (extract :string))]
      (assert (string? val) "value present is not a string")
      (format "%X" (hash val)))))

(defn get!
  "Retrieves the value stored at the given key.
   If the key does not exist, a nil value is returned.
   An error is returned if the value stored at the key is not a string, since GET only operates on string values.

   ```
   GET key
   ```

   key: the name of the key.

   Bulk string reply: the value of the key.
   Null reply: key does not exist."
  [state msg]
  (remove-expired! state)
  (let [key (nth msg 1)]
    (or
     (some-> @(state :db) (get key) (extract :string))
     :null-string)))

(defn getdel!
  "Gets the value of a key and then deletes it.
   This is similar to GET, except that the key is also removed on success.
   The key is only deleted if its value type is a string.
   If the key does not exist, or if its value type is not a string, the command returns null and the key is not deleted.

   ```
   GETDEL key
   ```

   key: the name of the key to get and then delete.

   Bulk string reply: the value of the key.
   Null reply: if the key does not exist or if the key's value type is not a string."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn getex!
  "GETEX retrieves the value stored at key and optionally sets or removes its expiration.
   It behaves like GET but is a write command because it can modify the key time to live.
   The optional expiration options are mutually exclusive; only one may be given per call.
   If the key does not exist, a null reply is returned.

   ```
   GETEX key [EX seconds | PX milliseconds | EXAT unix-time-seconds |
     PXAT unix-time-milliseconds | PERSIST]
   ```

   key: the name of the key to retrieve.
   EX seconds: set the specified expire time, in seconds.
   PX milliseconds: set the specified expire time, in milliseconds.
   EXAT unix-time-seconds: set the specified Unix time at which the key will expire, in seconds.
   PXAT unix-time-milliseconds: set the specified Unix time at which the key will expire, in milliseconds.
   PERSIST: remove the time to live associated with the key.

   Bulk string reply: the value of key.
   Null reply: if key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn getrange!
  "Returns the substring of the string value stored at key,
   determined by the start and end offsets.
   Both offsets are inclusive.
   Negative offsets count backward from the end of the string,
   where -1 is the last character and -2 is the penultimate.
   Out-of-range offsets are clamped to the actual length of the string.

   ```
   GETRANGE key start end
   ```

   key: the name of the key.
   start: the start offset, zero-based, a negative value counts from the end of the string.
   end: the end offset, zero-based and inclusive, a negative value counts from the end of the string.

   Bulk string reply: The substring of the string value stored at key, determined by the offsets start and end (both are inclusive)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn getset!
  "Atomically sets key to value and returns the old value stored at key.
   Returns an error when key exists but does not hold a string value.
   Any previous time to live associated with the key is discarded on successful SET operation.

   ```
   GETSET key value
   ```

   key: the name of the key.
   value: the new value to set.

   Bulk string reply: the old value stored at the key.
   Null reply: if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn incr!
  "Increments the integer stored at key by one.
   If the key does not exist, it is first set to 0 before the increment is performed.
   An error is returned if the key holds a value of the wrong type or a string that cannot be parsed as an integer.
   The operation is limited to 64-bit signed integers.
   Redis stores integers in their integer representation, so there is no overhead for storing the string representation of the integer.

   ```
   INCR key
   ```

   key: the name of the key.

   Integer reply: the value of the key after the increment."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn incrby!
  "Increments the integer value stored at key by the given increment.
   If the key does not exist, it is first set to 0 before the operation is performed.
   An error is returned if the key holds a value of the wrong type
   or a string that cannot be parsed as an integer.
   The operation is limited to 64-bit signed integers.

   ```
   INCRBY key increment
   ```

   key: the name of the key.
   increment: the integer amount to add to the value.

   Integer reply: the value of the key after the increment."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn incrbyfloat!
  "Increments the floating-point number stored at key by the given increment.
   A negative increment value decrements the stored value instead.
   If the key does not exist, it is set to 0 before the operation is performed.
   An error is returned if the key holds a value that is not a valid floating-point number, or if the key holds a value of the wrong type.
   On success the new value is stored back at the key, replacing the old one, and returned as a string.
   Both the existing value and the increment argument may be expressed in exponential notation.
   The result is always stored in a consistent format: an integer part, optionally followed by a dot and a variable number of decimal digits, with trailing zeroes removed.
   The output precision is fixed at 17 digits after the decimal point.

   ```
   INCRBYFLOAT key increment
   ```

   key: the name of the key.
   increment: the floating-point amount to add to the value, a negative value decrements the value instead.

   Bulk string reply: the value of the key after the increment."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn increx!
  "Increments or decrements the numeric value stored at key by the specified amount, with optional upper and lower bounds and expiration control, in a single atomic operation.
   If the key does not exist, it is set to 0 before performing the operation.
   An error is returned if the key contains a value of the wrong type or a string that cannot be interpreted as a number.
   Unlike INCR and INCRBY, INCREX returns an array of two elements: the new value of the key after the increment, and the increment that was actually applied.
   When the computed result would fall outside an explicit LBOUND or UBOUND or the type limits, the default is to skip the operation and reply with the current value and 0, leaving the key and its TTL untouched.
   The SATURATE flag changes this behavior so the result is capped at the bound instead.

   ```
   INCREX key [BYFLOAT increment | BYINT increment]
    [LBOUND lowerbound] [UBOUND upperbound] [SATURATE]
    [EX seconds | PX milliseconds | EXAT unix-time-seconds| PXAT unix-time-milliseconds | PERSIST] [ENX]
   ```

   key: the name of the key to increment.
   BYFLOAT increment | BYINT increment: specifies the increment amount and type; BYFLOAT increments by a long-double float and the existing value may be either an integer or a float, while BYINT increments by a 64-bit signed integer and requires the existing value to be integer-typed; if neither is specified the key is incremented by 1 in integer mode; the two are mutually exclusive.
   LBOUND lowerbound: sets a lower bound for the resulting value; if the result would fall below it the operation is skipped (or floored with SATURATE); when omitted the bound is LLONG_MIN in integer mode or -LDBL_MAX in BYFLOAT mode; must be less than or equal to UBOUND when both are specified.
   UBOUND upperbound: sets an upper bound for the resulting value; if the result would exceed it the operation is skipped (or capped with SATURATE); when omitted the bound is LLONG_MAX in integer mode or LDBL_MAX in BYFLOAT mode; must be greater than or equal to LBOUND when both are specified.
   SATURATE: when specified, an out-of-bounds result is capped at UBOUND or floored at LBOUND (or saturated to the type limits when no explicit bound is given); the second reply element reflects the saturated delta; an error is returned if the delta cannot be represented as a 64-bit signed integer in integer mode or would produce Infinity in BYFLOAT mode.
   EX seconds: set the expiration time in seconds.
   PX milliseconds: set the expiration time in milliseconds.
   EXAT unix-time-seconds: set the expiration as a Unix time in seconds.
   PXAT unix-time-milliseconds: set the expiration as a Unix time in milliseconds.
   PERSIST: remove the expiration associated with the key.
   ENX: only sets the TTL if the key currently has no TTL; the increment is still applied but the TTL is left unchanged if one already exists; must be combined with EX, PX, EXAT, or PXAT and is incompatible with PERSIST.

   Array reply: a two-element array:
   New value — the value of the key after the increment, or the unchanged current value when an out-of-bounds result caused the operation to be skipped.
   Actual increment — the increment that was actually applied. May differ from the requested increment when SATURATE caps the result at a bound, and is always 0 when an out-of-bounds result caused the operation to be skipped.
   Both elements are Integer replies in integer mode (default or BYINT), or Double replies in BYFLOAT mode."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn lcs!
  "The LCS command computes the longest common subsequence between the string values stored at two keys.
   Matching characters do not need to be contiguous; they only need to appear in the same relative order in both strings.
   This is useful for measuring similarity between two strings, such as DNA sequences or text revisions.
   The algorithm runs in O(N*M) time where N and M are the lengths of the two strings, so it should be used with small strings or on a dedicated instance.
   The LEN option returns only the length of the subsequence rather than the subsequence itself.
   The IDX option returns the positions of matching ranges in both strings instead of the subsequence.
   MINMATCHLEN, used together with IDX, filters out matches shorter than the given length.
   WITHMATCHLEN, used together with IDX, includes the length of each match in the result.

   ```
   LCS key1 key2 [LEN] [IDX] [MINMATCHLEN min-match-len] [WITHMATCHLEN]
   ```

   key1: the name of the first key.
   key2: the name of the second key.
   LEN: return the length of the longest common subsequence instead of the subsequence itself.
   IDX: return the positions of the matches instead of the matching string.
   MINMATCHLEN min-match-len: when used with IDX, only return matches at least min-match-len characters long.
   WITHMATCHLEN: when used with IDX, include the length of each match in the result.

   Bulk string reply: the longest common subsequence.
   Integer reply: the length of the longest common subsequence when LEN is given.
   Map reply: a map with the LCS length and all the ranges in both the strings when IDX is given."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn mget!
  "Returns the values of all specified keys.
   For every key that does not hold a string value or does not exist, nil is returned.
   Because of this, the operation never fails.

   ```
   MGET key [key ...]
   ```

   key [key ...]: one or more keys whose values to retrieve.

   Array reply: a list of values at the specified keys."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn mset!
  "Sets the given keys to their respective values.
   Replaces existing values with new values, just as regular SET.
   MSET is atomic, so all given keys are set at once.
   It is not possible for clients to see that some of the keys were updated while others are unchanged.
   In clustered Redis environments the behavior varies; see the multi-key operations documentation for details.

   ```
   MSET key value [key value ...]
   ```

   key value [key value ...]: one or more key-value pairs to set.

   Simple string reply: always OK because MSET can't fail."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn msetex!
  "Atomically sets multiple string keys with an optional shared expiration in a single operation.
   The condition options NX and XX are mutually exclusive, as are the expiration options EX, PX, EXAT, PXAT, and KEEPTTL.
   NX restricts the write to cases where none of the specified keys already exist, while XX restricts it to cases where all of the specified keys already exist.

   ```
   MSETEX numkeys key value [key value ...] [NX | XX] [EX seconds |
     PX milliseconds | EXAT unix-time-seconds |
     PXAT unix-time-milliseconds | KEEPTTL]
   ```

   numkeys: the number of keys being set.
   key value [key value ...]: a series of key/value pairs.
   NX: set the keys and their expiration time only if none of the specified keys exist.
   XX: set the keys and their expiration time only if all of the specified keys already exist.
   EX seconds: set the specified expiration time in seconds, a positive integer.
   PX milliseconds: set the specified expiration time in milliseconds, a positive integer.
   EXAT unix-time-seconds: set the specified Unix time in seconds, a positive integer, at which the keys will expire.
   PXAT unix-time-milliseconds: set the specified Unix time in milliseconds, a positive integer, at which the keys will expire.
   KEEPTTL: retain the time to live associated with the keys.

   Integer reply: 0 if none of the keys were set; 1 if all of the keys were set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn msetnx!
  "Sets multiple keys to their respective values in a single atomic operation.
   If any of the given keys already exists, no operation is performed at all and none of the keys are set.
   This makes it suitable for setting several fields of a logical object where either all fields or none should be present.
   Because the command is atomic, clients cannot observe a state in which some keys were updated while others were not.

   ```
   MSETNX key value [key value ...]
   ```

   key value [key value ...]: one or more key-value pairs to set, no keys are set if any of them already exists.

   Integer reply: 0 if no key was set (at least one key already existed).
   Integer reply: 1 if all the keys were set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn psetex!
  "Sets a key to hold the given value and applies a time-to-live, specified in milliseconds.
   If the key already exists, its value is overwritten and the new time-to-live replaces any previous one.
   This command is deprecated; use SET with the PX option instead for new code.

   ```
   PSETEX key milliseconds value
   ```

   key: the name of the key.
   milliseconds: the time to live, in milliseconds.
   value: the value to set.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn set!
  "Sets the key to hold the specified string value.
   If the key already holds a value, it is overwritten regardless of its type.
   Any previous time to live associated with the key is discarded on a successful SET operation.
   The condition options (NX, XX, IFEQ, IFNE, IFDEQ, IFDNE) are mutually exclusive, as are the expiration options (EX, PX, EXAT, PXAT, KEEPTTL).

   ```
   SET key value [NX | XX | IFEQ ifeq-value | IFNE ifne-value |
     IFDEQ ifdeq-digest | IFDNE ifdne-digest] [GET] [EX seconds |
     PX milliseconds | EXAT unix-time-seconds |
     PXAT unix-time-milliseconds | KEEPTTL]
   ```

   key: the name of the key.
   value: the string value to set.
   NX: only set the key if it does not already exist.
   XX: only set the key if it already exists.
   IFEQ ifeq-value: set the key value and expiration only if its current value is equal to ifeq-value, if the key does not exist it will not be created.
   IFNE ifne-value: set the key value and expiration only if its current value is not equal to ifne-value, if the key does not exist it will be created.
   IFDEQ ifdeq-digest: set the key value and expiration only if the hash digest of its current value is equal to ifdeq-digest, if the key does not exist it will not be created.
   IFDNE ifdne-digest: set the key value and expiration only if the hash digest of its current value is not equal to ifdne-digest, if the key does not exist it will be created.
   GET: return the old string stored at the key, or nil if the key did not exist, an error is returned and SET is aborted if the value stored at the key is not a string.
   EX seconds: set the specified expire time in seconds, a positive integer.
   PX milliseconds: set the specified expire time in milliseconds, a positive integer.
   EXAT unix-time-seconds: set the specified Unix time at which the key will expire in seconds, a positive integer.
   PXAT unix-time-milliseconds: set the specified Unix time at which the key will expire in milliseconds, a positive integer.
   KEEPTTL: retain the time to live associated with the key.

   If GET was not specified:
   Null reply: the key does not exist and XX/IFEQ/IFDEQ was specified, the key was not created.
   Null reply: the key exists and NX was specified or a specified IFEQ/IFNE/IFDEQ/IFDNE condition is false, the key was not set.
   Simple string reply: OK, the key was set.
   If GET was specified:
   Null reply: the key did not exist before the SET operation, whether the key was created or not.
   Bulk string reply: the previous value of the key, whether the key was set or not."
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
        previous-value (some-> @(state :db) (get key) (extract :string))

        response
        (cond
          (and nx? (contains? @(state :db) key)) :null-string
          (and xx? ((complement contains?) @(state :db) key)) :nul-string
          (and ifeq-value (not= previous-value ifeq-value)) :null-string
          (and ifne-value (= previous-value ifne-value)) :null-string
          (and ifdeq-digest (not= (format "%X" (hash previous-value)) ifdeq-digest)) :null-string
          (and ifdne-digest (= (format "%X" (hash previous-value)) ifdne-digest)) :null-string

          :else
          (do
            (if (or keepttl? (not expiration)) nil
                (alter (state :ttl) assoc key expiration))
            (alter (state :db) assoc key [:string val])
            :OK))]
    (if get? (or response previous-value) response)))

(defn setex!
  "Sets a key to hold the given string value and sets a timeout on the key.
   The key expires after the specified number of seconds.
   This is equivalent to using SET with the EX argument.
   An error is returned when seconds is invalid.

   ```
   SETEX key seconds value
   ```

   key: the name of the key.
   seconds: the time to live, in seconds.
   value: the value to set.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn setnx!
  "Sets key to hold the given string value only if the key does not already exist.
   If the key already holds a value, no operation is performed and the existing value is left unchanged.
   This is equivalent to SET with the NX option.

   ```
   SETNX key value
   ```

   key: the name of the key.
   value: the value to set, only if the key does not already exist.

   Integer reply: 0 if the key was not set.
   Integer reply: 1 if the key was set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn setrange!
  "Overwrites part of the string stored at key, starting at the given offset,
   for the entire length of value.
   If the offset is larger than the current length of the string, the string is
   padded with zero-bytes to make the offset fit.
   Non-existing keys are treated as empty strings, so the key is created with a
   string large enough to hold value at the specified offset.
   The maximum offset is 2^29 - 1 (536870911), as Redis strings are limited to
   512 megabytes.
   Setting a large offset on a key that does not yet hold a string, or holds a
   small string, requires allocating all intermediate memory, which can block the
   server for some time.
   Subsequent calls to SETRANGE on the same key do not incur this allocation
   overhead.

   ```
   SETRANGE key offset value
   ```

   key: the name of the key.
   offset: the zero-based offset at which to start overwriting, the string is zero-padded if the offset is beyond the current length.
   value: the string to write at the offset.

   Integer reply: the length of the string after it was modified by the command."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn strlen!
  "Returns the length of the string value stored at key.
   If the key does not exist, the command returns 0.
   An error is returned when key holds a non-string value.

   ```
   STRLEN key
   ```

   key: the name of the key.

   Integer reply: the length of the string stored at key, or 0 when the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn substr!
  "Returns the substring of the string value stored at key,
   determined by the offsets start and end (both inclusive).
   Negative offsets can be used to count from the end of the string,
   where -1 is the last character, -2 the penultimate, and so on.
   Out-of-range requests are handled by limiting the resulting range
   to the actual length of the string.

   ```
   SUBSTR key start end
   ```

   key: the name of the key.
   start: the start offset, zero-based; a negative value counts from the end of the string.
   end: the end offset, zero-based and inclusive; a negative value counts from the end of the string.

   Bulk string reply: the substring of the string value stored at key, determined by the offsets start and end (both are inclusive)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

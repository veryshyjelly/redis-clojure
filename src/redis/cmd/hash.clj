(ns redis.cmd.hash)

(defn hdel!
  "Removes the specified fields from the hash stored at key.
   Specified fields that do not exist within the hash are ignored.
   If no fields remain after deletion, the hash itself is deleted.
   If the key does not exist, it is treated as an empty hash and the command returns 0.

   ```
   HDEL key field [field ...]
   ```

   key: the name of the key that holds the hash.
   field [field ...]: one or more fields to delete from the hash.

   Integer reply: the number of fields that were removed from the hash, excluding any specified but non-existing fields."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hexists!
  "Checks whether a given field exists in the hash stored at key.
   If the key does not exist, the command returns 0 rather than raising an error.

   ```
   HEXISTS key field
   ```

   key: the name of the key that holds the hash.
   field: the field to check for existence.

   Integer reply: 0 if the hash does not contain the field, or the key does not exist.
   Integer reply: 1 if the hash contains the field."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hexpire!
  "Sets an expiration (TTL) on one or more fields of a given hash key.
   At least one field must be specified.
   Fields are automatically deleted from the hash when their TTLs expire.
   Field expirations are only cleared by commands that delete or overwrite the contents of the hash fields, such as HDEL and HSET.
   Operations that alter a field value without replacing it leave the TTL untouched.
   The HPERSIST command can be used to clear a field TTL and make the field persistent again.
   Calling HEXPIRE with a zero TTL, or HPEXPIREAT with a past time, results in the field being deleted.

   ```
   HEXPIRE key seconds [NX | XX | GT | LT] FIELDS numfields field
     [field ...]
   ```

   key: the name of the key that holds the hash.
   seconds: the time to live, in seconds, after which each specified field is deleted.
   FIELDS numfields field [field ...]: the hash fields to set expiration for, where numfields is the count followed by that many field names.
   NX: for each specified field, set expiration only when the field has no expiration.
   XX: for each specified field, set expiration only when the field has an existing expiration.
   GT: for each specified field, set expiration only when the new expiration is greater than the current one; a non-volatile field is treated as an infinite TTL.
   LT: for each specified field, set expiration only when the new expiration is less than the current one; a non-volatile field is treated as an infinite TTL.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: 0 if the specified NX | XX | GT | LT condition has not been met.
   Integer reply: 1 if the expiration time was set/updated.
   Integer reply: 2 when HEXPIRE/HPEXPIRE is called with 0 seconds/milliseconds or when HEXPIREAT/HPEXPIREAT is called with a past Unix time in seconds/milliseconds.
   Simple error reply:
   if parsing failed, mandatory arguments are missing, unknown arguments are specified, or argument values are of the wrong type or out of range.
   if the provided key exists but is not a hash."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hexpireat!
  "HEXPIREAT sets an absolute Unix timestamp expiration on one or more fields of a hash.
   It is the absolute-time counterpart of HEXPIRE: instead of a relative TTL in seconds,
   it accepts a Unix timestamp in seconds since the Unix epoch.
   A timestamp in the past causes the field to be deleted immediately.
   The optional NX, XX, GT, and LT modifiers are mutually exclusive and restrict
   when the expiration is actually applied to each field.

   ```
   HEXPIREAT key unix-time-seconds [NX | XX | GT | LT] FIELDS numfields
     field [field ...]
   ```

   key: the name of the key that holds the hash.
   unix-time-seconds: the absolute Unix expiration timestamp in seconds since Unix epoch;
     a timestamp in the past deletes the field immediately.
   FIELDS numfields field [field ...]: the hash fields to set expiration for;
     numfields is the count followed by that many field names.
   NX: set expiration only when the field has no existing expiration.
   XX: set expiration only when the field already has an expiration.
   GT: set expiration only when the new expiration is greater than the current one;
     a non-volatile key is treated as an infinite TTL for this comparison.
   LT: set expiration only when the new expiration is less than the current one;
     a non-volatile key is treated as an infinite TTL for this comparison.

   Array reply: for each field, an integer reply with one of the following values:
   Integer reply: -2 if no such field exists in the provided hash key,
     or the provided key does not exist.
   Integer reply: 0 if the specified NX, XX, GT, or LT condition has not been met.
   Integer reply: 1 if the expiration time was set/updated.
   Integer reply: 2 when called with a past Unix time in seconds.
   Simple error reply: if parsing failed, mandatory arguments are missing,
     unknown arguments are specified, or argument values are of the wrong type
     or out of range.
   Simple error reply: if the provided key exists but is not a hash."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hexpiretime!
  "Returns the absolute Unix timestamp in seconds since Unix epoch at which the given key's field(s) will expire.
   If the key does not exist or the field is not found, -2 is returned for that field.
   If the field exists but has no associated expiration set, -1 is returned.

   ```
   HEXPIRETIME key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to retrieve the expiration time for, numfields is the number of fields followed by that many field names.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: -1 if the field exists but has no associated expiration set.
   Integer reply: the expiration (Unix timestamp) in seconds."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hget!
  "Returns the value associated with a given field in the hash stored at key.
   If the field does not exist in the hash, or if the key does not exist at all, a null reply is returned.

   ```
   HGET key field
   ```

   key: the name of the key that holds the hash.
   field: the field whose value to retrieve.

   Bulk string reply: The value associated with the field.
   Null reply: If the field is not present in the hash or key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hgetall!
  "Returns all fields and values of the hash stored at key.
   Every field name is followed by its value, so the length of the reply is twice the size of the hash.
   If the key does not exist, an empty list is returned.

   ```
   HGETALL key
   ```

   key: the name of the key that holds the hash.

   Map reply: a map of fields and their values, or an empty list when key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hgetdel!
  "Retrieves and deletes the values of one or more fields from a hash stored at the given key.
   If a field does not exist in the hash, a nil value is returned for that field.
   When the last remaining field is deleted, the key itself is also removed.

   ```
   HGETDEL key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to get and then delete, where numfields is the number of fields followed by that many field names.

   Array reply: a list of deleted fields and their values or nil for fields that do not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hgetex!
  "Retrieves the values of one or more fields from a hash stored at the given key.
   Optionally, an expiration time or time-to-live can be set on the retrieved fields.
   The expiration options are mutually exclusive; at most one may be specified.

   ```
   HGETEX key [EX seconds | PX milliseconds | EXAT unix-time-seconds |
     PXAT unix-time-milliseconds | PERSIST] FIELDS numfields field
     [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to retrieve,
     where numfields is the number of fields followed by that many field names.
   EX seconds: set the expiration time in seconds.
   PX milliseconds: set the expiration time in milliseconds.
   EXAT unix-time-seconds: set the Unix time at which the fields will expire, in seconds.
   PXAT unix-time-milliseconds: set the Unix time at which the fields will expire, in milliseconds.
   PERSIST: remove the TTL associated with the fields.

   Array reply: a list of values associated with the given fields,
     in the same order as they are requested."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn himport!
  "HIMPORT is a container command for session-based bulk hash import.
   Its subcommands let a client import many hashes that share the same field names
   efficiently by declaring those field names once and then sending only the values
   for each key.

   ```
   HIMPORT
   ```"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn himport-discard!
  "Removes a single session-local fieldset from the current connection.
   The fieldset must have been previously defined with HIMPORT PREPARE.
   If no fieldset with the given name exists on the connection, the command simply reports that nothing was removed.

   ```
   HIMPORT DISCARD fieldset-name
   ```

   fieldset-name: the name of the fieldset to remove from the current connection.

   Integer reply: 1 if the fieldset was removed, or 0 if no fieldset with that name existed on the connection."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn himport-discardall!
  "Removes every fieldset held by the current connection.
   All definitions previously created with HIMPORT PREPARE are discarded.

   ```
   HIMPORT DISCARDALL
   ```

   Integer reply: the number of fieldsets removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn himport-prepare!
  "Defines a session-local fieldset that maps a name to an ordered list of field names.
   The fieldset is scoped to the current connection and is intended to be used by later HIMPORT SET commands on that same connection.
   Preparing a fieldset with a name that already exists on the connection replaces the previous definition.
   A field name must not appear more than once in the same fieldset.

   ```
   HIMPORT PREPARE fieldset-name field [field ...]
   ```

   fieldset-name: the name to give the fieldset, scoped to the current connection;
     preparing a fieldset with a name that already exists replaces the previous definition.
   field [field ...]: one or more field names to store under the fieldset, in the order
     you intend to supply their values to HIMPORT SET; a field name must not appear
     more than once in the same fieldset.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn himport-set!
  "Creates a hash from a fieldset previously defined with HIMPORT PREPARE on the same connection.
   The field names from the fieldset are paired with the supplied values by position.
   If the key already exists, it is overwritten.
   The number of values must equal the number of fields declared in the fieldset.

   ```
   HIMPORT SET key fieldset-name value [value ...]
   ```

   key: the key to create, overwritten if it already exists.
   fieldset-name: the name of a fieldset previously defined with HIMPORT PREPARE on the same connection.
   value [value ...]: the field values, given in the same order as the field names were declared in the fieldset, the count must match the number of fields.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hincrby!
  "Increments the integer value stored at field in the hash at key by the given increment.
   If the key does not exist, a new key holding a hash is created.
   If the field does not exist, its value is treated as 0 before the operation is performed.
   The range of supported values is limited to 64 bit signed integers.

   ```
   HINCRBY key field increment
   ```

   key: the name of the key that holds the hash.
   field: the field whose value to increment.
   increment: the integer amount to add to the field's value.

   Integer reply: the value of the field after the increment operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hincrbyfloat!
  "Increments the value of the specified field in a hash by a floating-point amount.
   If the field does not exist, it is set to 0 before the operation is performed.
   A negative increment value decrements the field instead of incrementing it.
   An error is returned if the operation cannot be performed on the given key or field.

   ```
   HINCRBYFLOAT key field increment
   ```

   key: the name of the key that holds the hash.
   field: the field whose value to increment.
   increment: the floating-point amount to add to the field value, a negative value decrements the field instead.

   Bulk string reply: the value of the field after the increment operation."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hkeys!
  "Returns all field names in the hash stored at key.
   If the key does not exist, an empty list is returned.

   ```
   HKEYS key
   ```

   key: the name of the key that holds the hash.

   Array reply: a list of fields in the hash, or an empty list when the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hlen!
  "Returns the number of fields contained in the hash stored at key.
   If the key does not exist, the command returns 0.

   ```
   HLEN key
   ```

   key: the name of the key that holds the hash.

   Integer reply: the number of fields in the hash, or 0 when the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hmget!
  "Returns the values associated with the specified fields in the hash stored at key.
   For every field that does not exist in the hash, a nil value is returned.
   Because non-existing keys are treated as empty hashes,
   running HMGET against a non-existing key will return a list of nil values.

   ```
   HMGET key field [field ...]
   ```

   key: the name of the key that holds the hash.
   field [field ...]: one or more fields whose values to retrieve.

   Array reply: a list of values associated with the given fields,
   in the same order as they are requested."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hmset!
  "Sets the specified fields to their respective values in the hash stored at key.
   If key does not exist, a new key holding a hash is created.
   Any specified fields that already exist in the hash are overwritten.

   ```
   HMSET key field value [field value ...]
   ```

   key: the name of the key that holds the hash.
   field value [field value ...]: one or more field-value pairs to set in the hash.

   Simple string reply: OK."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hpersist!
  "Removes the existing expiration on one or more fields of a hash key.
   This turns the specified fields from volatile (having a TTL) to persistent (no TTL associated).
   If a field does not exist in the hash, or the key itself does not exist, the reply for that field is -2.
   If the field exists but has no expiration set, the reply is -1.
   If the expiration was successfully removed, the reply is 1.

   ```
   HPERSIST key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to remove the expiration from, where numfields is the number of fields followed by that many field names.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: -1 if the field exists but has no associated expiration set.
   Integer reply: 1 the expiration was removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hpexpire!
  "Sets the per-field expiration time, in milliseconds, for one or more fields of a hash.
   If the key does not exist or does not hold a hash, an error is returned.
   If a specified field does not exist in the hash, the reply for that field is -2.
   The optional condition NX, XX, GT, or LT (mutually exclusive) controls whether the expiration is actually set for each field.
   NX sets expiration only when the field currently has no expiration.
   XX sets expiration only when the field already has an expiration.
   GT sets expiration only when the new value is greater than the current one; a non-volatile key is treated as having an infinite TTL.
   LT sets expiration only when the new value is less than the current one; a non-volatile key is treated as having an infinite TTL.
   If the condition is not met, the reply for that field is 0.
   If the expiration is successfully set or updated, the reply for that field is 1.

   ```
   HPEXPIRE key milliseconds [NX | XX | GT | LT] FIELDS numfields field
     [field ...]
   ```

   key: the name of the key that holds the hash.
   milliseconds: the timeout, in milliseconds.
   FIELDS numfields field [field ...]: the hash fields to operate on, numfields is the number of fields followed by that many field names.
   NX: for each specified field, set expiration only when the field has no expiration.
   XX: for each specified field, set expiration only when the field has an existing expiration.
   GT: for each specified field, set expiration only when the new expiration is greater than the current one, a non-volatile key is treated as an infinite TTL for the purposes of GT.
   LT: for each specified field, set expiration only when the new expiration is less than the current one, a non-volatile key is treated as an infinite TTL for the purposes of LT.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: 0 if the specified NX, XX, GT, or LT condition has not been met.
   Integer reply: 1 if the expiration time was set/updated.
   Integer reply: 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
   Simple error reply:
   if parsing failed, mandatory arguments are missing, unknown arguments are specified, or argument values are of the wrong type or out of range.
   if the provided key exists but is not a hash."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hpexpireat!
  "Sets the absolute expiration time, in milliseconds since Unix epoch, on one or more fields of a hash.
   This is the millisecond-precision variant of HEXPIREAT; all other semantics are identical.
   A timestamp in the past deletes the field immediately.
   The optional condition NX, XX, GT, or LT is mutually exclusive and restricts when the expiration is actually applied to each field.
   If the key does not exist or a field is not found, the per-field reply is -2.
   If the key exists but is not a hash, a simple error is returned.

   ```
   HPEXPIREAT key unix-time-milliseconds [NX | XX | GT | LT]
     FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   unix-time-milliseconds: the absolute Unix expiration timestamp, in milliseconds since Unix epoch; a timestamp in the past deletes the field immediately.
   FIELDS numfields field [field ...]: the hash fields to operate on; numfields is the number of fields, followed by that many field names.
   NX: set expiration only when the field has no expiration.
   XX: set expiration only when the field has an existing expiration.
   GT: set expiration only when the new expiration is greater than the current one; a non-volatile key is treated as an infinite TTL.
   LT: set expiration only when the new expiration is less than the current one; a non-volatile key is treated as an infinite TTL.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: 0 if the specified NX, XX, GT, or LT condition has not been met.
   Integer reply: 1 if the expiration time was set/updated.
   Integer reply: 2 when HEXPIRE or HPEXPIRE is called with 0 seconds or milliseconds, or when HEXPIREAT or HPEXPIREAT is called with a past Unix time in seconds or milliseconds.
   Simple error reply: if parsing failed, mandatory arguments are missing, unknown arguments are specified, or argument values are of the wrong type or out of range.
   Simple error reply: if the provided key exists but is not a hash."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hpexpiretime!
  "Retrieves the absolute Unix expiration timestamp in milliseconds since the Unix epoch
   for the specified hash fields.
   It has the same semantics as HEXPIRETIME but returns the timestamp in milliseconds
   rather than seconds.
   If the key does not exist or a requested field is not found, the reply for that
   field is -2.
   If the field exists but has no expiration set, the reply for that field is -1.

   ```
   HPEXPIRETIME key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to retrieve the expiration time
   for, where numfields is the number of fields followed by that many field names.

   Array reply: for each field, an integer reply.
   Integer reply: -2 if no such field exists in the provided hash key, or the provided
   key does not exist.
   Integer reply: -1 if the field exists but has no associated expiration set.
   Integer reply: the expiration (Unix timestamp) in milliseconds."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hpttl!
  "Returns the remaining time to live of hash fields that have an expiration set, expressed in milliseconds.
   This is the millisecond-precision counterpart of HTTL.
   If the key does not exist or is not a hash, -2 is returned for each requested field.
   If a field exists but has no expiration set, -1 is returned for that field.

   ```
   HPTTL key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to retrieve the remaining time to live for, where numfields is the number of fields followed by that many field names.

   Array reply. For each field:
   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: -1 if the field exists but has no associated expiration set.
   Integer reply: the TTL in milliseconds."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hrandfield!
  "Returns a random field from the hash value stored at key.
   When called with only the key, a single random field name is returned.
   If a positive count is given, an array of distinct fields is returned, with length equal to the smaller of count and the hash field count.
   If a negative count is given, the command may return the same field more than once, and the number of returned fields is the absolute value of count.
   The optional WITHVALUES modifier causes the reply to include the value of each selected field alongside its name.

   ```
   HRANDFIELD key [count [WITHVALUES]]
   ```

   key: the name of the key that holds the hash.
   count: the number of fields to return, a positive count returns up to that many distinct fields while a negative count returns exactly the absolute value of count fields and may repeat fields, when omitted a single random field is returned.
   WITHVALUES: also return the value of each selected field, can only be used together with count.

   Null reply: if the key does not exist.
   Bulk string reply: a single, randomly selected field when the count option is not used.
   Array reply: a list containing count fields when the count option is used, or an empty array if the key does not exist.
   Array reply: a list of fields and their values when count and WITHVALUES were both used."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hscan!
  "Iterates over the fields of the hash stored at key using a cursor-based approach.
   Start an iteration with cursor 0, then pass the cursor returned by each call as the argument to the next call.
   The MATCH option filters results to only fields whose names match a glob-style pattern.
   The COUNT option provides a hint for the number of fields to return per iteration, defaulting to 10.
   The NOVALUES option returns only field names without their values.

   ```
   HSCAN key cursor [MATCH pattern] [COUNT count] [NOVALUES]
   ```

   key: the name of the key that holds the hash.
   cursor: the cursor value, start an iteration with 0 then use the cursor returned by each call as the argument to the next call.
   MATCH pattern: only return fields whose names match the given glob-style pattern.
   COUNT count: a hint for the number of fields to return per iteration, the default is 10.
   NOVALUES: return only the field names, without their values.

   Array reply: a two-element array.
   The first element is a Bulk string reply that represents an unsigned 64-bit number, the cursor.
   The second element is an Array reply of field/value pairs that were scanned. When the NOVALUES flag is used, only the field names are returned."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hset!
  "Sets the specified fields to their respective values in the hash stored at key.
   Overwrites the values of any fields that already exist in the hash.
   If key does not exist, a new key holding a hash is created.

   ```
   HSET key field value [field value ...]
   ```

   key: the name of the key that holds the hash.
   field value [field value ...]: one or more field-value pairs to set in the hash.

   Integer reply: the number of fields that were added."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hsetex!
  "Sets the value of one or more fields in a hash key and optionally sets their expiration time or TTL.
   If the key already holds a value, the specified fields are overwritten and any previous TTLs associated with the key are discarded.
   The FNX and FXX options are mutually exclusive, and the EX, PX, EXAT, PXAT, and KEEPTTL options are mutually exclusive.

   ```
   HSETEX key [FNX | FXX] [EX seconds | PX milliseconds |
     EXAT unix-time-seconds | PXAT unix-time-milliseconds | KEEPTTL]
     FIELDS numfields field value [field value ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field value [field value ...]: the hash field-value pairs to set,
     where numfields is the number of fields followed by that many field-value pairs.
   FNX: only set the fields if none of them already exist, mutually exclusive with FXX.
   FXX: only set the fields if all of them already exist.
   EX seconds: set the specified expiration time in seconds.
   PX milliseconds: set the specified expiration time in milliseconds.
   EXAT unix-time-seconds: set the specified Unix time in seconds at which the fields will expire.
   PXAT unix-time-milliseconds: set the specified Unix time in milliseconds at which the fields will expire.
   KEEPTTL: retain the TTL associated with the fields.

   Integer reply: 0 if no fields were set.
   Integer reply: 1 if all the fields were set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hsetnx!
  "Sets a field in the hash stored at key to the given value, but only if that field does not already exist.
   If the key does not exist, a new key holding a hash is created first.
   If the field already exists in the hash, the operation has no effect and the existing value is left unchanged.

   ```
   HSETNX key field value
   ```

   key: the name of the key that holds the hash.
   field: the field to set, only if it does not already exist.
   value: the value to set.

   Integer reply: 0 if the field already exists in the hash and no operation was performed.
   Integer reply: 1 if the field is a new field in the hash and the value was set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hstrlen!
  "Returns the string length of the value associated with a field in the hash stored at key.
   If the key does not exist or the field is not present in the hash, zero is returned.

   ```
   HSTRLEN key field
   ```

   key: the name of the key that holds the hash.
   field: the field whose value length to return.

   Integer reply: the string length of the value associated with the field, or zero when the field is not present in the hash or the key does not exist at all."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn httl!
  "Returns the remaining time to live, in seconds, for one or more fields of a hash key that have an expiration set.
   The command accepts multiple fields in a single call via the FIELDS subcommand.
   If a field does not exist in the hash, or the key itself does not exist, -2 is returned for that field.
   If a field exists but has no expiration set, -1 is returned for that field.

   ```
   HTTL key FIELDS numfields field [field ...]
   ```

   key: the name of the key that holds the hash.
   FIELDS numfields field [field ...]: the hash fields to retrieve the remaining time to live for, where numfields is the number of fields followed by that many field names.

   Integer reply: -2 if no such field exists in the provided hash key, or the provided key does not exist.
   Integer reply: -1 if the field exists but has no associated expiration set.
   Integer reply: the TTL in seconds."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn hvals!
  "Returns all values in the hash stored at key.
   If the key does not exist, an empty list is returned.
   If the key holds a value that is not a hash, an error is returned.

   ```
   HVALS key
   ```

   key: the name of the key that holds the hash.

   Array reply: a list of values in the hash, or an empty list when the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(ns redis.cmd.json)

(defn json-arrappend!
  "Appends the given JSON values to the array located at the specified path,
   inserting them after the last existing element.
   Multiple values can be appended in a single call.
   If the value matched by the path is not an array, the command returns null
   for that path instead of modifying anything.
   When a string is used as an array value to append, it must be wrapped in an
   additional set of single quotes around the already-quoted string.

   ```
   JSON.ARRAPPEND key path value [value ...]
   ```

   key: the key to modify.
   path: the JSONPath to match.
   value: one or more values to append to one or more arrays;
     to specify a string, wrap the quoted string with an additional set of single quotes.

   With $-based path argument (default): Array reply of integer replies or null replies,
     where each element is the array's new length, or null if the matching value is not an array.
   With .-based path argument: Integer reply representing the array's new length,
     or null reply if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-arrindex!
  "Searches for the first occurrence of a given JSON value within an array
   at the specified path.
   Returns the index of the first match, or -1 if the value is not found.
   If the value at the path is not an array, a null reply is returned.
   The optional start and stop arguments define a slice of the array to search;
   start is inclusive and stop is exclusive.
   Out-of-range indexes are rounded to the array boundaries.
   An inverse index range, such as start greater than stop, results in -1.
   Negative indexes are interpreted as offsets from the end of the array.
   To search for a string value, wrap the quoted string in an additional
   set of single quotes.

   ```
   JSON.ARRINDEX key path value [start [stop]]
   ```

   key: the key to parse.
   path: the JSONPath to specify.
   value: the value to find its index in one or more arrays.
   start: inclusive start value for the slice of the array to search,
   defaults to 0.
   stop: exclusive stop value for the slice of the array to search,
   defaults to 0, negative values are interpreted as starting from the end.

   With $-based path argument: Array reply of integer replies or null replies,
   where each element is the first position in the array, -1 if unfound,
   or null if the matching value is not an array.
   With .-based path argument: Integer reply representing the first position
   in the array, -1 if unfound, or null reply if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-arrinsert!
  "Inserts one or more JSON values into the array located at the given path, before the specified index.
   Existing elements at and after that index are shifted to the right.
   The index must fall within the array range; index 0 prepends to the array, and negative index values count from the end.
   If the value at the path is not an array, the operation returns null for that path.
   The path defaults to the root $.
   To insert a string value, wrap the quoted string in an additional set of single quotes, for example '\"silver\"'.

   ```
   JSON.ARRINSERT key path index value [value ...]
   ```

   key: the key to modify.
   path: JSONPath to specify, defaults to root $.
   index: position in the array where to insert, must be in the array range, 0 prepends, negative values start from the end.
   value: one or more JSON values to insert into the array.

   With $-based path argument (default): Array reply of integer replies or null replies, where each element is the array's new size, or null if the matching value is not an array.
   With .-based path argument: Integer reply representing the array's new size, or null reply if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-arrlen!
  "Reports the length of the JSON array at the given path in the key.
   If the key does not exist or the path does not match, null is returned.
   If the matching value is not an array, null is returned for that element.

   ```
   JSON.ARRLEN key [path]
   ```

   key: the key to parse.
   path: JSONPath to specify, defaults to root $ if not provided.

   With $-based path argument (default): Array reply of integer replies or null replies, where each element is the array length, or null if the matching value is not an array.
   With .-based path argument: Integer reply representing the array length, or null reply if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-arrpop!
  "Removes and returns an element from the array at the specified index.
   The default index is -1, which targets the last element.
   Out-of-range indexes are rounded to the nearest array end.
   Popping from an empty array returns null.
   The default path is the root $.

   ```
   JSON.ARRPOP key [path [index]]
   ```

   key: the key to modify.
   index: the position in the array to pop from, defaults to -1 (last element), out-of-range indexes round to their respective array ends, popping an empty array returns null.
   path: the JSONPath to specify, defaults to root $.

   Array reply of bulk string replies or null replies, where each element is the popped JSON value as a string, or null if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-arrtrim!
  "Trims an array stored at the given path so that it contains only the
   specified inclusive range of elements.
   Elements before the start index and after the stop index are removed.

   The command is forgiving with out-of-range indexes and does not produce
   an error.
   If start is larger than the array size or start is greater than stop,
   the array is emptied and 0 is returned.
   If start is negative, it is interpreted as an offset from the end of
   the array.
   If stop is larger than the last index, it is treated as the last
   element.

   ```
   JSON.ARRTRIM key path start stop
   ```

   key: the key to modify.
   path: the JSONPath to specify, defaults to root $.
   start: the index of the first element to keep, previous elements are
   trimmed, defaults to 0.
   stop: the index of the last element to keep, following elements are
   trimmed, defaults to 0, negative values are interpreted as starting
   from the end.

   With $-based path argument (default): Array reply of integer replies
   or null replies, where each element is the array's new size, or null
   if the matching value is not an array.
   With .-based path argument: Integer reply representing the array's new
   size, or null reply if the matching value is not an array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-clear!
  "Clears container values (arrays and objects) by emptying them, and sets numeric values to zero.
   Nonexisting paths are silently ignored.
   Already cleared values, such as empty containers or numbers already at zero, are not counted.

   ```
   JSON.CLEAR key [path]
   ```

   key: the key to parse.
   path: the JSONPath to specify, defaults to the root path $.
   Nonexisting paths are ignored.
   Already cleared values are ignored for empty containers and zero numbers.

   Integer reply: the number of matching JSON arrays and objects cleared plus the number of matching JSON numerical values zeroed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-debug!
  "JSON.DEBUG is a container command for debugging related tasks.
   It does not perform any operation on its own.

   ```
   JSON.DEBUG
   ```"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-debug-memory!
  "Reports the memory usage in bytes of a JSON value stored under the given key.
   The reported size may be higher than the actual total memory consumption of the key because of an internal JSON string reuse mechanism.
   If no path is supplied, the root of the JSON document is measured.

   ```
   JSON.DEBUG MEMORY key [path]
   ```

   key: the key to parse.
   path: a JSONPath to specify, defaults to the root $.

   Integer reply: the value size in bytes."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-del!
  "Deletes a value from the JSON document stored at the given key.
   If no path is specified, the root of the document is deleted, which is equivalent to removing the key from Redis.
   When a path is given, only the value at that path is removed; non-existing paths are silently ignored.
   If deleting a value leaves the root object or array empty, the key is also deleted from Redis.

   ```
   JSON.DEL key [path]
   ```

   key: the key to modify.
   path: JSONPath to specify, defaults to root $, non-existing paths are ignored.

   Integer reply: the number of paths deleted (0 or more)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-forget!
  "Deletes JSON paths from the document stored at key.
   This command is equivalent to JSON.DEL.

   ```
   JSON.FORGET key [path]
   ```

   Integer reply: the number of paths deleted (0 or more)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-get!
  "Returns the value at the specified path in JSON serialized form.
   If no path is given, the root $ is used by default.
   JSON.GET accepts multiple path arguments.
   With a single JSONPath, the result is a JSON string with a top-level array of serialized values; a legacy path returns a single value.
   With multiple JSONPath arguments, the result is a JSON string with a top-level object, each value being an array of serialized values.
   The INDENT, NEWLINE, and SPACE options control pretty-printing of the output.

   ```
   JSON.GET key [INDENT indent] [NEWLINE newline] [SPACE space] [path
     [path ...]]
   ```

   key: the key to parse.
   path: JSONPath to specify, defaults to root $, accepts multiple path arguments.
   INDENT: sets the indentation string for nested levels.
   NEWLINE: sets the string printed at the end of each line.
   SPACE: sets the string put between a key and a value.

   Bulk string reply: a JSON-encoded string with a top-level array containing the value(s) at the specified path(s).
   With a single path using $ (default), returns a JSON array containing the serialized value.
   With multiple paths, returns a JSON object where each key is a path and each value is an array of JSON serializations."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-merge!
  "Merges a given JSON value into matching paths within a JSON document.
   Values at matching paths are updated, deleted, or expanded with new children.
   This command complies with RFC7396 Json Merge Patch.
   Merging an existing object key with a null value deletes the key.
   Merging an existing object key with a non-null value updates the value.
   Merging a non-existing object key adds the key and value.
   Merging an existing array with any merged value replaces the entire array.

   ```
   JSON.MERGE key path value
   ```

   key: the key to merge into.
   path: specifies the JSONPath, for non-existing keys the path must be $,
     for existing keys the value matching the path is merged with the JSON value,
     and when the path exists except for the last element a new child is added.
   value: the JSON value to merge at the specified path,
     merging follows RFC7396 rules where null deletes a key,
     non-null updates a key, non-existing keys are added,
     and arrays are replaced entirely.

   Simple string reply: OK if executed correctly.
   Simple error reply: if the operation fails to set the new values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-mget!
  "Returns the value at the given path from multiple keys.
   Each key is parsed independently and the result for that key is placed at the corresponding position in the reply array.
   If a key does not exist, the corresponding element in the reply is null.
   If the path does not exist within a key, the corresponding element is also null.
   In cluster mode, all specified keys must reside on the same hash slot.
   When the database has more than one shard and the specified keys reside in different shards, no CROSSSLOT error is reported and the results may be partial.

   ```
   JSON.MGET key [key ...] path
   ```

   key: the key to parse, returns null for nonexistent keys.
   path: the JSONPath to specify, returns null for nonexistent paths.

   Array reply of bulk string replies or null replies, where each element is the JSON serialization of the value at the corresponding key's path, or null if the key or path doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-mset!
  "Sets or updates one or more JSON values according to the specified key-path-value triplets.
   The command is atomic, so all given additions or updates are either applied together or not at all.
   A JSON value is a hierarchical structure, and changing a value at a specific path affects nested values.
   When cluster mode is enabled, all specified keys must reside on the same hash slot.

   ```
   JSON.MSET key path value [key path value ...]
   ```

   key: the key to modify.
   path: the JSONPath to specify; for new keys the path must be the root, for existing keys the value at the full path is replaced, or a new child is added if only part of the path exists.
   value: the value to set at the specified path.

   Simple string reply: OK if executed correctly.
   Simple error reply: if the operation fails to set the new values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-numincrby!
  "Increments the number value stored at the given path by the given value.
   If the key does not exist, or if the result is out of range, a simple error is returned.
   If the matching value at the path is not a number, a null is returned for that path.
   The path syntax does not change the reply format.

   ```
   JSON.NUMINCRBY key path value
   ```

   key: the key to modify.
   path: the JSONPath to specify.
   value: the number value to increment by.

   Simple error reply if the key does not exist or the result is out of range.
   Array reply with one element per matching path: an integer reply or double reply containing the new value, or a null reply if the matching value is not a number.
   The array is empty if no path matches."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-nummultby!
  "Multiplies the number value stored at path by value.
   If the key does not exist or the result is out of range, a simple error is returned.
   If the matching value is not a number, a null reply is produced for that path.
   The path syntax does not change the reply format.

   ```
   JSON.NUMMULTBY key path value
   ```

   key: the key to modify.
   path: the JSONPath to specify.
   value: the number value to multiply by.

   Simple error reply if the key does not exist or the result is out of range.
   Array reply with one element per matching path: an integer reply or double reply containing the new value, or a null reply if the matching value is not a number. The array is empty if no path matches."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-objkeys!
  "Returns the key names of JSON objects at the paths matching a given path expression.
   A JSON object is a structure within a JSON document that contains an unordered set of key-value pairs.
   Do not confuse Redis keys with JSON object keys.

   ```
   JSON.OBJKEYS key [path]
   ```

   key: a Redis key storing a value of type JSON.
   path: a JSONPath expression (the root $, or any string starting with $. or $[) that resolves to all matching locations, or a legacy path expression (any string not matching JSONPath syntax, allowing a leading dot to be omitted so that name and .name are equivalent) that resolves to only the first matching location, defaults to the root of the document.

   If path is a JSONPath expression:
   A simple error if key does not exist.
   An empty array reply if path has no matches.
   An array reply where each array element corresponds to one match:
   nil if the match is not an object.
   An array reply of bulk string replies containing the object's key names if the match is an object.
   If path is a legacy path expression:
   nil if key does not exist.
   nil if path has no matches.
   A simple error if the first match is not an object.
   An array reply of bulk string replies containing the object's key names of the first match."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-objlen!
  "Returns the number of keys in JSON objects at the paths matching a given path expression.
   A JSON object is a structure within a JSON document that contains an unordered set of key-value pairs.
   Do not confuse Redis keys with JSON object keys.

   ```
   JSON.OBJLEN key [path]
   ```

   key: a Redis key storing a value of type JSON.
   path: a JSONPath expression (root '$' or any string starting with '$.' or '$[') that resolves to all matching locations, or a legacy path expression (any string not matching JSONPath syntax, allowing the leading dot to be omitted) that resolves to only the first matching location, defaults to '.' (legacy path pointing to the root of the document).

   If path is a JSONPath expression:
   A simple error if key does not exist.
   An empty array reply if path has no matches.
   An array reply where each array element corresponds to one match:
   nil if the match is not an object.
   An integer reply: the number of keys in the object.
   If path is a legacy path expression:
   nil if key does not exist.
   nil if path has no matches.
   A simple error if the first match is not an object.
   An integer reply: the number of keys in the first match."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-resp!
  "Returns the JSON value stored in key in Redis serialization protocol (RESP) form.
   The command is deprecated.
   When no path is given, the root of the JSON document is serialized.
   The mapping from JSON to RESP is as follows:
   JSON null maps to a bulk string reply.
   JSON false and true map to a simple string reply.
   JSON number maps to an integer reply or a bulk string reply, depending on the type.
   JSON string maps to a bulk string reply.
   JSON array is represented as an array reply whose first element is the simple string [, followed by the array elements.
   JSON object is represented as an array reply whose first element is the simple string {.
   Each successive entry is a two-entry array reply of bulk string replies representing a key-value pair.

   ```
   JSON.RESP key [path]
   ```

   key: the key to parse.
   path: JSONPath to specify, defaults to root $.

   Array reply representing the JSON value in RESP form, as detailed in the Redis serialization protocol specification.
   The mapping from JSON to RESP follows the rules described in the command arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-set!
  "Sets or replaces the value at each location resolved by the given JSONPath expression.
   If the key does not exist, a new JSON document can be created only by setting the root path ($ or .).
   If the key exists, new object members can be created when the parent object exists and the final path token is a non-existing member.
   If any intermediate path element does not exist, the path cannot be created and the command returns nil.
   The NX and XX options modify the set-or-create behavior for both new and existing keys.

   ```
   JSON.SET key path value [NX | XX] [FPHA <FP16 | BF16 | FP32 | FP64>]
   ```

   key: a new key to create or an existing JSON key to modify.
   path: a JSONPath expression that resolves to zero or more locations within the JSON document; the root is specified as $ or ..
   value: a valid JSON value (scalar or compound) to set at the specified path.
   NX: sets the value only if path has no matches.
   XX: sets the value only if path has one or more matches.
   FPHA: force floating point homogeneous arrays to use a specified FP type (FP16, BF16, FP32, or FP64); if any value in the array overflows the chosen type, a value out of range error is returned.

   Simple string reply: OK if executed correctly.
   Null reply: if key exists but path does not exist and cannot be created, or if an NX or XX condition is unmet.
   Simple error reply: (error) expected ... - if the value is invalid.
   Simple error reply: (error) Error occurred on position ... expected ... - if the path is invalid.
   Simple error reply: (error) ERR new objects must be created at the root - if key does not exist and path is not root ($ or .).
   Simple error reply: (error) ERR wrong static path - if a dynamic path expression has no matching locations.
   Simple error reply: (error) ERR index out of bounds - if the path refers to an array index outside the array bounds.
   Simple error reply: (error) value out of range for ... - if one or more values of a FP array are out of range for the given type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-strappend!
  "Appends a string to JSON strings at the paths matching a given path expression.
   The key must store a value of type JSON; if it does not exist, a simple error is returned.
   The value to append is specified as a string; to use a quoted string as the value, wrap it in an additional set of single quotes.
   The path argument accepts either a JSONPath expression (starting with $, $. or $[) or a legacy path expression (any other string, where a leading dot may be omitted).
   A JSONPath expression resolves to all matching locations, while a legacy path expression resolves to only the first matching location.
   The default path is the root of the document.

   ```
   JSON.STRAPPEND key [path] value
   ```

   key: a Redis key storing a value of type JSON.
   value: a string to append to the JSON strings at the paths matching path.
   path: a JSONPath expression or a legacy path expression that resolves to matching locations in key, defaults to the root of the document.

   If path is a JSONPath expression:
   A simple error if key does not exist.
   An empty array reply if path has no matches.
   An array reply where each array element corresponds to one match: nil if the match is not a string, or an integer reply with the new length of the string.
   If path is a legacy path expression:
   A simple error if key does not exist.
   A simple error if path has no matches.
   A simple error if the first match is not a string.
   An integer reply: the new length of the string at the first match."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-strlen!
  "Returns the length of JSON strings at the paths matching a given path expression.
   The path argument can be a JSONPath expression or a legacy path expression,
   and the reply format differs accordingly.
   If the key does not exist, a JSONPath path produces a simple error
   while a legacy path produces a nil reply.

   ```
   JSON.STRLEN key [path]
   ```

   key: a Redis key storing a value of type JSON.
   path: a JSONPath expression (root $ or any string starting with $. or $[)
     that resolves to all matching locations, or a legacy path expression
     (any other string, allowing a leading . to be omitted) that resolves to
     only the first matching location, defaults to . (legacy path pointing
     to the root of the document).

   If path is a JSONPath expression:
   A simple error if key does not exist.
   An empty array reply if path has no matches.
   An array reply where each array element corresponds to one match:
   nil if the match is not a string.
   An integer reply: the length of the string.
   If path is a legacy path expression:
   nil if key does not exist.
   A simple error if path has no matches.
   A simple error if the first match is not a string.
   An integer reply: the length of the string at the first match."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-toggle!
  "Toggles a Boolean value stored at the given path within the JSON document
   at key.
   If the value at the matching path is not a Boolean, the command returns
   null for that path without modifying the document.

   ```
   JSON.TOGGLE key path
   ```

   key: the key to modify.
   path: the JSONPath to specify, defaults to root $.

   With $-based path argument: Array reply of integer replies or null
   replies, where each element is the new value (0 if false or 1 if true),
   or null if the matching value is not Boolean.
   With .-based path argument: Integer reply representing the new value
   (0 if false or 1 if true), or null reply if the matching value is not
   Boolean."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn json-type!
  "Reports the type of the JSON value at the given path.
   The returned type is one of the standard JSON type strings.
   If the key or path does not exist, null is returned.

   ```
   JSON.TYPE key [path]
   ```

   key: the key to parse.
   path: JSONPath to specify, defaults to root $, returns null if the key or path do not exist.

   With $-based path argument: Array reply of bulk string replies, where each element is the type of the matching value.
   With .-based path argument: Bulk string reply representing the type of the matching value.
   With $-based path argument (default): Array reply of array replies of bulk string replies, where each nested array contains the type of the matching value.
   With .-based path argument: Array reply of bulk string replies representing the type of the matching value."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(ns redis.cmd.vector)

(defn vadd!
  "Adds a new element into the vector set stored under key.
   The vector data can be supplied either as a 32-bit floating point (FP32) binary blob or as a list of floating point numbers expressed as strings, prefixed by the element count.
   When using the FP32 format the blob must be little-endian encoded; on platforms with different endianness you should either adjust the byte order manually or use the VALUES syntax instead.
   The element name identifies the entry being added to the vector set.

   ```
   VADD key [REDUCE dim] (FP32 | VALUES num) vector element [CAS] [NOQUANT | Q8 | BIN]
     [EF build-exploration-factor] [SETATTR attributes] [M numlinks]
   ```

   key: the name of the key that will hold the vector set data.
   FP32 vector or VALUES num vector: either a 32-bit floating point (FP32) blob of values or num floating point numbers as strings; the FP32 blob must use little-endian encoding.
   element: the name of the element being added to the vector set.
   REDUCE dim: implements random projection to reduce the dimensionality of the vector; the projection matrix is saved and reloaded along with the vector set, and this option must be passed immediately before the vector.
   CAS: performs the operation partially using threads in a check-and-set style, where the neighbor candidates collection runs in the background while the command executes in the main thread.
   NOQUANT: forces the vector to be created without int8 quantization, which is otherwise the default; mutually exclusive with Q8 and BIN.
   BIN: forces the vector to use binary quantization instead of int8, which is faster and uses less memory but impacts recall quality; mutually exclusive with NOQUANT and Q8.
   Q8: forces the vector to use signed 8-bit quantization, which is the default; this option exists to verify at insertion time that the vector set is of the same format; mutually exclusive with NOQUANT and BIN.
   EF build-exploration-factor: controls the effort made to find good candidates when connecting the new node to the existing HNSW graph, defaulting to 200; a larger value may improve recall.
   SETATTR attributes: associates attributes in the form of a JavaScript object to the newly created entry or updates existing attributes, equivalent to calling VSETATTR separately.
   M numlinks: the maximum number of connections each node of the graph will have with other nodes, defaulting to 16; nodes at layer zero have M * 2 connections while higher layers have M connections; more connections use more memory but provide more efficient graph exploration.

   Boolean reply: true if key was added; false if key was not added.
   Simple error reply: if the command was malformed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vcard!
  "Returns the number of elements in the specified vector set.
   If the key does not exist, the command returns 0.

   ```
   VCARD key
   ```

   key: the name of the key that holds the vector set.

   Integer reply: 0 if the key does not exist or the number of elements contained in the vector set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vdim!
  "Returns the number of dimensions of the vectors in the specified vector set.
   If the vector set was created using the REDUCE option for dimensionality reduction,
   this command reports the reduced dimension.
   However, you must still use full-size vectors when performing queries with the VSIM command.

   ```
   VDIM key
   ```

   key: the name of the key that holds the vector set.

   Integer reply: the number of vector set elements.
   Simple error reply: if the key does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vemb!
  "Returns the approximate vector associated with a given element in a vector set.
   Vector sets normalize and may quantize vectors on insertion, so VEMB reverses this process by de-normalizing and de-quantizing to approximate the original vector.
   The RAW option retrieves the raw internal representation instead of the de-quantized vector.

   ```
   VEMB key element [RAW]
   ```

   key: the name of the key that holds the vector set.
   element: the name of the element whose vector you want to retrieve.
   RAW: returns the raw vector data, its quantization type, and metadata such as norm and range.

   Array reply: of doubles, representing the vector.
   Array reply: consisting of the following elements:
   The quantization type as a simple string: fp32, bin, or q8.
   A bulk string blob with the following raw data:
   4-byte floats for fp32 (little-endian encoding)
   A bitmap for binary quantization
   A byte array for q8
   The double L2 norm of the vector before normalization.
   (Only for q8): The quantization range as a double. Multiply this by integer components to recover normalized values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vgetattr!
  "Retrieves the JSON attributes associated with a specific element in a vector set.
   If the key does not exist, the element is not found, or the element has no
   attributes, a null reply is returned.

   ```
   VGETATTR key element
   ```

   key: the name of the key that holds the vector set.
   element: the name of the element whose attributes to retrieve.

   Simple string reply containing the JSON attribute(s).
   Null reply for unknown key or element, or when no attributes exist for the
   given key/element pair."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vinfo!
  "Returns metadata and internal details about a vector set.
   The information includes the size, dimensions, quantization type, and graph structure of the vector set.
   If the key does not exist, a null reply is returned.

   ```
   VINFO key
   ```

   key: the name of the key that holds the vector set.

   Array reply containing metadata and internal details about a vector set, including size, dimensions, quantization type, and graph structure.
   Null reply for unknown key."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vismember!
  "Checks whether a given element is a member of a vector set stored at the specified key.
   If the key does not exist, the element is considered not present.

   ```
   VISMEMBER key element
   ```

   key: the name of the key that holds the vector set.
   element: the element to check for membership.

   Boolean reply: false if the element does not exist in the vector set, or the key does not exist. true if the element exists in the vector set."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vlinks!
  "Returns the neighbors of a specified element in a vector set.
   The command shows the connections for each layer of the HNSW graph.
   When the WITHSCORES option is used, similarity scores are included for each neighbor.
   If the key or element does not exist, a null reply is returned.
   An incorrect syntax produces a simple error reply.

   ```
   VLINKS key element [WITHSCORES]
   ```

   key: the name of the key that holds the vector set.
   element: the name of the element whose HNSW neighbors you want to inspect.
   WITHSCORES: includes similarity scores for each neighbor.

   An array of array replies containing the names of adjacent elements as bulk strings when used without the WITHSCORES option.
   An array of map replies containing the names of adjacent elements as bulk strings, together with their scores as doubles when used with the WITHSCORES option.
   Simple error reply for incorrect syntax.
   Null reply for unknown keys and/or elements."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vrandmember!
  "Returns one or more random elements from a vector set.
   If no count is given, a single random element is returned.
   A positive count returns that many distinct elements.
   A negative count allows duplicates in the result.
   If the key does not exist, a null reply is returned when no count is specified,
   or an empty array is returned when a count is specified.

   ```
   VRANDMEMBER key [count]
   ```

   key: the name of the key that holds the vector set.
   count: specifies the number of elements to return,
     positive values return distinct elements,
     negative values allow duplicates.

   Array reply containing the names of count random elements as strings.
   Null reply for unknown keys.
   Array reply (empty array) for unknown keys when a count is specified."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vrange!
  "VRANGE provides a stateless iterator over the elements of a vector set.
   It lets you retrieve elements in small batches per call without needing an explicit cursor, and it gives guarantees about what you will miss if the vector set changes (elements added or removed) during iteration.
   Elements are returned in lexicographical order, using byte-by-byte comparison to establish a total order among elements.

   ```
   VRANGE key start end [count]
   ```

   key: the name of the vector set key from which to retrieve elements.
   start: the starting point of the lexicographical range; can be a string prefixed with [ for inclusive, ( for exclusive, or the special symbol - for the minimum element.
   end: the ending point of the lexicographical range; can be a string prefixed with [ for inclusive, ( for exclusive, or the special symbol + for the maximum element.
   count: the maximum number of elements to return; if negative, all elements in the specified range are returned, which may block the server for a long time with large sets.

   Array reply of elements in lexicographical order within the specified range.
   Array reply (empty array) if the key doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vrem!
  "Removes an element from a vector set.
   VREM reclaims memory immediately and does not use tombstones or logical deletions,
   making it safe to use in long-running applications that frequently update the same vector set.

   ```
   VREM key element
   ```

   key: the name of the key that holds the vector set.
   element: the name of the element to remove from the vector set.

   Boolean reply: false if either element or key do not exist; true if the element was removed."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vsetattr!
  "Associates a JSON object with an element in a vector set.
   The stored attributes can be used in filtered similarity searches with VSIM.
   Existing attributes can be updated, or deleted by passing an empty string.
   The JSON string is stored as given, without validation.
   Elements whose attributes are not valid JSON are treated as not matching by the FILTER option of VSIM.

   ```
   VSETATTR key element \"{ JSON obj }\"
   ```

   key: the name of the key that holds the vector set.
   element: the name of the element whose attributes you want to set or remove.
   json: the JSON string with the attributes of the element, use an empty string to delete the attributes, the string is stored as given without validation, elements whose attributes are not valid JSON are treated as not matching by the FILTER option of VSIM.

   Boolean reply: false if either the key or element does not exist; true if the attributes were set or deleted."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn vsim!
  "Returns elements similar to a given vector or element within a vector set.
   Performs approximate or exact similarity searches.
   The query can reference an existing element by name or supply a raw vector in binary float or stringified float format.
   Optional parameters control score output, attribute inclusion, result count, distance threshold, search effort, filtering, and execution mode.
   Setting TRUTH forces an exact linear scan of all elements, which is significantly slower but useful for benchmarking or calculating recall.
   NOTHREAD runs the search in the main thread and may block the server during execution.

   ```
   VSIM key (ELE | FP32 | VALUES num) (vector | element) [WITHSCORES] [WITHATTRIBS] [COUNT num]
     [EPSILON delta] [EF search-exploration-factor] [FILTER expression] [FILTER-EF max-filtering-effort]
     [TRUTH] [NOTHREAD]
   ```

   key: the name of the key that holds the vector set data.
   ELE | FP32 | VALUES num: specifies how the input vector is provided; use ELE to refer to an existing element, FP32 for binary float format (little-endian encoding required), or VALUES num for a list of stringified float values.
   vector or element: either the vector data (for FP32 or VALUES) or the name of the element (for ELE) to use as the similarity reference.
   WITHSCORES: returns the similarity score (from 1 to 0) alongside each result; a score of 1 is identical and 0 is the opposite.
   WITHATTRIBS: returns, for each element, the JSON attribute associated with the element or NULL when no attributes are present.
   COUNT num: limits the number of returned results to num.
   EPSILON delta: a floating point number between 0 and 1 that sets a maximum distance threshold; only elements whose distance to the query vector is less than delta are returned, which is equivalent to requiring a similarity score of (1 - delta) or better.
   EF search-exploration-factor: controls the search effort; higher values explore more nodes, improving recall at the cost of speed, typical values range from 50 to 1000.
   FILTER expression: applies a filter expression to restrict matching elements.
   FILTER-EF max-filtering-effort: limits the number of filtering attempts for the FILTER expression.
   TRUTH: forces an exact linear scan of all elements, bypassing the HNSW graph; significantly slower (O(N)), useful for benchmarking or calculating recall.
   NOTHREAD: executes the search in the main thread instead of a background thread; may block the server during execution.

   Simple error reply for unknown element.
   Array reply (empty array) for unknown key.
   Array reply with matching elements.
   With the WITHSCORES option, a map reply with matching bulk string elements (keys) and double scores (values).
   With the WITHSCORES and WITHATTRIBS options, a Map reply with matching bulk string elements (keys), and an additional array (values) with the following elements: (1) a double reply for the score and (2) a bulk string representing the JSON attribute associated with the element or null for the elements missing an attribute."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

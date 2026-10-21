(ns redis.cmd.tdigest)

(defn tdigest-add!
  "Adds one or more observations to a t-digest sketch.
   The key must reference an existing t-digest sketch; if it does not exist, an error is returned.
   Each value must be a valid floating-point number; an incorrect type results in an error.

   ```
   TDIGEST.ADD key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the floating-point value of an observation.

   Simple string reply OK if executed correctly.
   Simple error reply if the given key does not exist or the value parameter is of the incorrect type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-byrank!
  "Returns a floating-point estimation of the value at each given rank in a t-digest sketch.
   Multiple ranks can be requested in a single call, and the results are returned in the same order as the input ranks.
   A rank of 0 corresponds to the smallest observation and a rank of n-1 to the largest, where n is the total number of observations in the sketch.

   ```
   TDIGEST.BYRANK key rank [rank ...]
   ```

   key: the key name of an existing t-digest sketch.
   rank: ranks for which the values should be retrieved, where 0 is the rank of the smallest observation and n-1 is the rank of the largest observation, with n being the number of observations added to the sketch.

   Array of doubles as floating-points, populated with value_1, value_2, ..., value_R:
   an accurate result when rank is 0, the value of the smallest observation.
   an accurate result when rank is n-1, the value of the largest observation, where n denotes the number of observations added to the sketch.
   inf when rank is equal to n or larger than n.
   nan for all ranks when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, rank parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-byrevrank!
  "Returns, for each input reverse rank, an estimation of the floating-point value with that reverse rank.
   Multiple estimations can be retrieved in a single call.
   A reverse rank of 0 corresponds to the largest observation,
   and n-1 corresponds to the smallest observation,
   where n is the number of observations added to the sketch.
   If the key does not exist or is of an incorrect type, a simple error is returned.
   Rank parsing errors or an incorrect number of arguments also produce a simple error.

   ```
   TDIGEST.BYREVRANK key reverse_rank [reverse_rank ...]
   ```

   key: the key name of an existing t-digest sketch.
   revrank: reverse ranks for which the values should be retrieved,
     where 0 is the reverse rank of the largest observation
     and n-1 is the reverse rank of the smallest observation,
     where n denotes the number of observations added to the sketch.

   Array of doubles as floating-points, populated with value_1, value_2, ..., value_R:
   an accurate result when rank is 0, the value of the largest observation.
   an accurate result when rank is n-1, the value of the smallest observation,
     where n denotes the number of observations added to the sketch.
   inf when rank is equal to n or larger than n.
   nan for all ranks when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type,
     rank parsing errors, or an incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-cdf!
  "Returns, for each input value, an estimation of the floating-point fraction of observations smaller than the given value plus half the observations equal to the given value.
   Multiple fractions can be retrieved in a single call by passing multiple values.

   ```
   TDIGEST.CDF key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the values for which the CDF (Cumulative Distribution Function) should be retrieved.

   Array of doubles populated with fraction_1, fraction_2, ..., fraction_N.
   All values are nan if the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, value parsing errors, or an incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-create!
  "Allocates memory and initializes a new t-digest sketch under the given key.
   The compression parameter controls the tradeoff between accuracy and memory usage, defaulting to 100 if not specified.
   Higher compression values such as 1000 yield more accurate estimates at the cost of more memory.

   ```
   TDIGEST.CREATE key [COMPRESSION compression]
   ```

   key: the key name for this new t-digest sketch.
   COMPRESSION compression: a controllable tradeoff between accuracy and memory consumption, defaults to 100.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: incorrect key type or incorrect keyword."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-info!
  "Returns information and statistics about a t-digest sketch stored at the given key.
   The reply includes details such as compression, capacity, merged and unmerged node
   counts and weights, total observations, total compressions, and memory usage.
   If the key does not exist, is of an incorrect type, or the number of arguments is
   wrong, a simple error is returned.

   ```
   TDIGEST.INFO key
   ```

   key: the key name for an existing t-digest sketch.

   Map reply with information about the given sketch as name-value pairs:
   Compression: Integer reply, the compression (controllable trade-off between
     accuracy and memory consumption) of the sketch
   Capacity: Integer reply, size of the buffer used for storing the centroids and
     for the incoming unmerged observations
   Merged nodes: Integer reply, number of merged observations
   Unmerged nodes: Integer reply, number of buffered nodes (uncompressed observations)
   Merged weight: Integer reply, weight of values of the merged nodes
   Unmerged weight: Integer reply, weight of values of the unmerged nodes
     (uncompressed observations)
   Observations: Integer reply, number of observations added to the sketch
   Total compressions: Integer reply, number of times this sketch compressed data together
   Memory usage: Integer reply, number of bytes allocated for the sketch
   Simple error reply if the given key does not exist, is of an incorrect type,
     or an incorrect number of arguments is provided."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-max!
  "Returns the maximum observation value from a t-digest sketch.
   The result is always accurate.
   nan is returned if the sketch is empty.
   An error is returned if the number of arguments is incorrect or the key type is wrong.

   ```
   TDIGEST.MAX key
   ```

   key: the key name for an existing t-digest sketch.

   Double reply representing the maximum observation value from the given sketch. The result is always accurate. nan is returned if the sketch is empty.
   Simple string reply in these cases: incorrect number of arguments or incorrect key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-merge!
  "Merges multiple t-digest sketches into a single sketch.
   If the destination key does not exist, a new sketch is created.
   If the destination key already exists, its values are merged with the values of the source keys.
   Use OVERRIDE to overwrite the destination key contents instead of merging.

   When COMPRESSION is not specified, the resulting compression is set to the maximum value among all source sketches if the destination does not exist or OVERRIDE is used.
   If the destination already exists and OVERRIDE is not specified, its compression is left unchanged.

   ```
   TDIGEST.MERGE destination-key numkeys source-key [source-key ...]
     [COMPRESSION compression] [OVERRIDE]
   ```

   destination-key: the key name for a t-digest sketch to merge observation values into; if it does not exist a new sketch is created, and if it exists its values are merged with the source keys unless OVERRIDE is used.
   numkeys: the number of source sketches to merge from, one or more.
   source-key: a key name for a t-digest sketch from which to merge observation values.
   COMPRESSION compression: a tradeoff between accuracy and memory consumption; 100 is the default and a common value, 1000 is more accurate; when not specified the compression is set to the maximum among all source sketches if the destination does not exist or OVERRIDE is used, and left unchanged if the destination exists and OVERRIDE is not used.
   OVERRIDE: if the destination key already exists, it is overwritten instead of merged.

   Simple string reply OK if successful.
   Simple error reply in the following cases: incorrect key type, incorrect keyword, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-min!
  "Returns the minimum observation value from a t-digest sketch.
   The result is always accurate.
   If the sketch is empty, nan is returned.
   An error is returned if the number of arguments is incorrect or the key type is wrong.

   ```
   TDIGEST.MIN key
   ```

   key: the key name for an existing t-digest sketch.

   Double reply representing the minimum observation value from the given sketch. The result is always accurate. nan is returned if the sketch is empty.
   Simple string reply in these cases: incorrect number of arguments or incorrect key type."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-quantile!
  "Returns a floating-point estimate for each given quantile fraction, representing the value below which the specified fraction of observations falls.
   Multiple quantiles can be requested in a single call.
   When the quantile is 0, the result is the exact smallest observation in the sketch.
   When the quantile is 1, the result is the exact largest observation in the sketch.
   If the sketch is empty, nan is returned for every quantile.
   An error is returned if the key does not exist, the key holds a value of the wrong type, a quantile cannot be parsed, or the number of arguments is incorrect.

   ```
   TDIGEST.QUANTILE key quantile [quantile ...]
   ```

   key: the key name for an existing t-digest sketch.
   quantile: the input fraction between 0 and 1 inclusively.

   Array of double replies as estimates, populated with value_1, value_2, ..., value_N.
   an accurate result when quantile is 0, the value of the smallest observation.
   an accurate result when quantile is 1, the value of the largest observation.
   nan for all quantiles when the given sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-rank!
  "Returns the estimated rank for each floating-point input value against a t-digest sketch.
   The rank is the number of observations in the sketch that are smaller than the value, plus half the number of observations that are equal to the value.
   Multiple values can be queried in a single call.
   If the key does not exist or holds a value of an incorrect type, a simple error is returned.
   If the sketch is empty, all returned ranks are -2.

   ```
   TDIGEST.RANK key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the input value for which the rank should be estimated.

   Array of integers populated with rank_1, rank_2, ..., rank_V.
   -1 when value is smaller than the value of the smallest observation.
   The number of observations when value is larger than the value of the largest observation.
   Otherwise, an estimation of the number of (observations smaller than value + half the observations equal to value).
   0 is the rank of the value of the smallest observation.
   n-1 is the rank of the value of the largest observation, where n denotes the number of observations added to the sketch.
   All values are -2 if the sketch is empty.
   Simple error reply: the given key does not exist or is of an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-reset!
  "Resets a t-digest sketch by emptying it and re-initializing it to its default state.
   The key must reference an existing t-digest sketch; otherwise an error is returned.

   ```
   TDIGEST.RESET key
   ```

   key: the key name for an existing t-digest sketch.

   Simple string reply OK if executed correctly.
   Simple error reply in these cases: key does not exist or is of the wrong type, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-revrank!
  "Returns the estimated reverse rank for each floating-point input value.
   The reverse rank is the number of observations in the sketch that are
   larger than the value, plus half the number of observations equal to the value.
   Multiple reverse ranks can be retrieved in a single call.
   A simple error is returned if the key does not exist, is of an incorrect
   type, or if there are parsing errors or an incorrect number of arguments.

   ```
   TDIGEST.REVRANK key value [value ...]
   ```

   key: the key name for an existing t-digest sketch.
   value: the input value for which the reverse rank should be estimated.

   Array of integers populated with revrank_1, revrank_2, ..., revrank_V:
   -1 when value is larger than the value of the largest observation.
   The number of observations when value is smaller than the value of the
   smallest observation.
   Otherwise, an estimation of the number of (observations larger than value
   + half the observations equal to value).
   0 is the reverse rank of the value of the largest observation.
   n-1 is the rank of the value of the smallest observation, where n denotes
   the number of observations added to the sketch.
   All values are -2 if the sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of
   an incorrect type, quantile parsing errors, or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn tdigest-trimmed-mean!
  "Returns an estimation of the mean value from a t-digest sketch.
   Observation values outside the specified low and high cutoff quantiles are excluded from the calculation.
   If the sketch is empty, the result is nan.
   An error is returned if the key does not exist, is of an incorrect type,
   the quantiles are out of range, or the number of arguments is incorrect.

   ```
   TDIGEST.TRIMMED_MEAN key low_cut_quantile high_cut_quantile
   ```

   key: the key name for an existing t-digest sketch.
   low_cut_quantile: a floating-point value in the range [0..1],
     must be lower than high_cut_quantile;
     when 0 no low cut is applied,
     when greater than 0 observation values lower than this quantile are excluded.
   high_cut_quantile: a floating-point value in the range [0..1],
     must be higher than low_cut_quantile;
     when less than 1 observation values greater than or equal to this quantile are excluded,
     when 1 no high cut is applied.

   Double reply as an estimation of the mean value.
   nan if the sketch is empty.
   Simple error reply in these cases: the given key does not exist or is of an incorrect type,
   quantiles out of range [0..1], or incorrect number of arguments."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
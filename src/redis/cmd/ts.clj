(ns redis.cmd.ts)

(defn ts-add!
  "Appends a sample to a time series identified by key.
   If the key does not exist, a new time series is created first.
   The timestamp is a Unix time in milliseconds, or * to use the server clock.
   The value is a double-precision numeric value; the parser rejects values that do not fit in binary64 and does not accept infinite values.
   NaN values are supported as sample values.
   If the timestamp is older than the retention period relative to the maximum existing timestamp, the sample is discarded and an error is returned.
   When compaction rules are defined on the time series, the compacted value for the affected bucket is recalculated based on the reported sample and the available original samples; if original samples have been trimmed by the retention policy, the compacted bucket is not updated.
   Explicitly adding samples to a compacted time series may result in inconsistencies between raw and compacted data, as the compaction process may override such samples.

   ```
   TS.ADD key timestamp value 
     [RETENTION retentionPeriod] 
     [ENCODING <COMPRESSED|UNCOMPRESSED>] 
     [CHUNK_SIZE size] 
     [DUPLICATE_POLICY policy] 
     [ON_DUPLICATE policy_ovr] 
     [IGNORE ignoreMaxTimediff ignoreMaxValDiff] 
     [LABELS [label value ...]]
   ```

   key: the key name for the time series.
   timestamp: Unix time in milliseconds specifying the sample timestamp, or * to use the server clock.
   value: the double numeric data value of the sample, following RFC 7159; the parser rejects values that do not fit in binary64 and does not accept infinite values.
   RETENTION retentionPeriod: maximum retention period in milliseconds compared to the maximum existing timestamp; used only when creating a new time series, ignored for existing ones.
   ENCODING enc: the series sample encoding format; used only when creating a new time series, ignored for existing ones.
   CHUNK_SIZE size: memory size in bytes allocated for each data chunk; used only when creating a new time series, ignored for existing ones.
   DUPLICATE_POLICY policy: policy for handling insertion of multiple samples with identical timestamps; used only when creating a new time series, ignored for existing ones.
   ON_DUPLICATE policy_ovr: overwrite policy for handling samples with identical timestamps, effective only for this single command and does not set the time series duplication policy; can be BLOCK, FIRST, LAST, MIN, MAX, or SUM; when using MIN, MAX, or SUM, an error is returned if either the previous or the new value (but not both) are NaN; has no effect when creating a new time series.
   IGNORE ignoreMaxTimediff ignoreMaxValDiff: policy for handling duplicate samples; a new sample is ignored if the time series is not a compaction, DUPLICATE_POLICY is LAST, the sample is added in-order, the time difference is within ignoreMaxTimediff, and the value difference is within ignoreMaxValDiff; NaN values are never regarded as duplicates; used only when creating a new time series, ignored for existing ones.
   LABELS label value ...: set of label-value pairs representing metadata labels of the time series; used only when creating a new time series, ignored for existing ones.

   Integer reply: the timestamp of the upserted sample. If the sample is ignored, the reply will be the largest timestamp in the time series.
   Simple error reply in these cases: invalid arguments, wrong key type, duplication policy is BLOCK, or when timestamp is older than the retention period compared to the maximum existing timestamp."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-alter!
  "Updates the retention, chunk size, duplicate policy, and labels of an existing time series.
   Only the specified elements are altered; any option that is omitted leaves its current value unchanged.
   The key must already exist and must be a time series; otherwise an error is returned.

   ```
   TS.ALTER key 
     [RETENTION retentionPeriod] 
     [CHUNK_SIZE size] 
     [DUPLICATE_POLICY policy] 
     [LABELS [label value ...]]
   ```

   key: the key name for the time series.
   RETENTION retentionPeriod: maximum retention period, compared to the maximum existing timestamp, in milliseconds.
   CHUNK_SIZE size: the initial allocation size, in bytes, for the data part of each new chunk; changing this value does not affect existing chunks.
   DUPLICATE_POLICY policy: policy for handling multiple samples with identical timestamps.
   LABELS [label value ...]: set of label-value pairs that represent metadata labels of the key and serve as a secondary index; labels not present in the given list are removed implicitly, and specifying LABELS with no pairs removes all existing labels.

   Simple string reply: OK when the time series is altered successfully.
   Simple error reply in these cases: invalid arguments, wrong key type, key does not exist, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-create!
  "Creates a new time series under the given key.
   If the key already exists, a Redis error is returned with the message TSDB: key already exists.
   You can check for the existence of a key with the EXISTS command.
   Other commands that also create a new time series when called with a key that does not exist are TS.ADD, TS.INCRBY, and TS.DECRBY.

   ```
   TS.CREATE key 
     [RETENTION retentionPeriod] 
     [ENCODING <COMPRESSED|UNCOMPRESSED>] 
     [CHUNK_SIZE size] 
     [DUPLICATE_POLICY policy] 
     [IGNORE ignoreMaxTimediff ignoreMaxValDiff] 
     [LABELS [label value ...]]
   ```

   key: the key name for the time series.
   RETENTION retentionPeriod: maximum age for samples compared to the highest reported timestamp, in milliseconds; samples expire based solely on the difference between their timestamp and timestamps passed to subsequent TS.ADD, TS.MADD, TS.INCRBY, and TS.DECRBY calls; when set to 0 samples never expire; defaults to the global RETENTION_POLICY configuration (default 0).
   ENCODING enc: specifies the series samples encoding format as COMPRESSED (applies compression, saving memory and usually improving performance) or UNCOMPRESSED (keeps raw samples in memory); defaults to COMPRESSED.
   CHUNK_SIZE size: initial allocation size in bytes for the data part of each new chunk; must be a multiple of 8 in the range [48 .. 1048576]; defaults to the global CHUNK_SIZE_BYTES configuration (default 4096).
   DUPLICATE_POLICY policy: policy for handling insertion of multiple samples with identical timestamps; one of BLOCK (ignore and reply with an error), FIRST (ignore), LAST (override), MIN (override only if lower), MAX (override only if higher), or SUM (add to existing value); defaults to the global DUPLICATE_POLICY configuration (default BLOCK).
   IGNORE ignoreMaxTimediff ignoreMaxValDiff: policy for handling duplicate samples; a new sample is ignored if the time series is not a compaction, DUPLICATE_POLICY is LAST, the sample is added in-order, the timestamp difference is within ignoreMaxTimediff, and the absolute value difference is within ignoreMaxValDiff; defaults to the global IGNORE_MAX_TIME_DIFF and IGNORE_MAX_VAL_DIFF (both default 0).
   LABELS label value ...: set of label-value pairs that represent metadata labels of the key and serve as a secondary index for TS.MGET, TS.MRANGE, TS.MREVRANGE, and TS.QUERYINDEX.

   Simple string reply: OK when the time series is created successfully.
   Simple error reply in these cases: invalid arguments, key already exists, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-createrule!
  "Creates a compaction rule that aggregates samples from a source time series into a destination time series.
   The destination key must already exist before this command is called.
   Only samples added to the source series after the rule is created will be aggregated.
   If no samples are added to the source during a bucket period, no compacted sample is written to the destination.
   The timestamp of each compacted sample is set to the start of its compaction bucket.
   Explicitly adding samples to the destination series may cause inconsistencies, as the compaction process may override them.
   Deleting the destination key also deletes the compaction rule.
   In a clustered environment, sourceKey and destKey must be placed in the same hash slot using hash tags; otherwise compaction may silently fail.

   ```
   TS.CREATERULE sourceKey destKey 
     AGGREGATION aggregator bucketDuration 
     [alignTimestamp]
   ```

   sourceKey: key name for the source time series.
   destKey: key name for the destination (compacted) time series, must be created before calling this command.
   AGGREGATION aggregator bucketDuration: aggregates results into time buckets; aggregator is one of avg, sum, min, max, range, count, countNaN, countAll, first, last, std.p, std.s, var.p, var.s, twa; bucketDuration is the duration of each bucket in milliseconds.
   alignTimestamp: ensures a bucket starts exactly at this timestamp and aligns all other buckets accordingly, expressed in milliseconds, defaults to 0 (aligned with the Unix epoch).

   Simple string reply: OK when the compaction rule is created successfully.
   Simple error reply in these cases: invalid arguments, wrong key type, sourceKey does not exist, destKey does not exist, sourceKey is already a destination of a compaction rule, destKey is already a source or a destination of a compaction rule, or sourceKey and destKey are identical."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-decrby!
  "Decreases the value of the sample with the maximum existing timestamp by the given subtrahend.
   If the key does not exist, a new time series is created first.
   This command can be used as a counter or gauge that automatically accumulates history as a time series.
   If a duplicate-sample policy (IGNORE) is defined for the time series, TS.DECRBY operations are affected and sample additions or modifications can be filtered.
   Explicitly adding samples to a compacted time series may result in inconsistencies between the raw and the compacted data, as the compaction process may override such samples.
   An error is returned if the subtrahend is NaN.
   If the time series is empty, the new sample value is set to the subtrahend.
   The timestamp must be equal to or higher than the maximum existing timestamp; otherwise an error is returned.
   When the timestamp is not specified, it defaults to the Unix time of the server clock.
   An error is returned if the current value at the maximum existing timestamp is NaN.

   ```
   TS.DECRBY key subtrahend 
     [TIMESTAMP timestamp] 
     [RETENTION retentionPeriod] 
     [ENCODING <COMPRESSED|UNCOMPRESSED>] 
     [CHUNK_SIZE size] 
     [DUPLICATE_POLICY policy] 
     [IGNORE ignoreMaxTimediff ignoreMaxValDiff]  
     [LABELS [label value ...]]
   ```

   key: the key name for the time series.
   subtrahend: numeric value to subtract (double), an error is returned if it is NaN.
   TIMESTAMP timestamp: Unix time in milliseconds specifying the sample timestamp, or * to use the server clock time.
   RETENTION retentionPeriod: maximum retention period in milliseconds, compared to the maximum existing timestamp; used only when creating a new time series.
   ENCODING enc: specifies the series sample encoding format; used only when creating a new time series.
   CHUNK_SIZE size: memory size in bytes allocated for each data chunk; used only when creating a new time series.
   DUPLICATE_POLICY policy: policy for handling insertion of multiple samples with identical timestamps; used only when creating a new time series.
   IGNORE ignoreMaxTimediff ignoreMaxValDiff: policy for handling duplicate samples, where a new sample is ignored if the time difference and value difference thresholds are met; used only when creating a new time series.
   LABELS [label value ...]: set of label-value pairs representing metadata labels of the key, serving as a secondary index; used only when creating a new time series.

   Integer reply: the timestamp of the upserted sample. If the sample is ignored (see IGNORE in TS.CREATE), the reply will be the largest timestamp in the time series.
   Simple error reply in these cases: invalid arguments, wrong key type, or when timestamp is not equal to or higher than the maximum existing timestamp."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-del!
  "Deletes all samples within a given timestamp range from a time series.
   The timestamp interval is closed (inclusive), so samples whose timestamp equals the start or end timestamp are also deleted.
   If the start timestamp is older than the retention period relative to the maximum existing timestamp, the deletion is discarded and an error is returned.
   When compaction rules are defined on the time series, deleting samples from an affected compaction bucket triggers a recalculation of the compacted value based on the remaining original samples, or removal of the compacted value if all original samples in that bucket were deleted.
   If original samples for an affected compaction bucket have already expired, the deletion is discarded and an error is returned.
   Explicitly deleting samples from a compacted time series may result in inconsistencies between raw and compacted data, as the compaction process may override such samples.

   ```
   TS.DEL key from_timestamp to_timestamp
   ```

   key: the key name for the time series.
   from_timestamp: the start timestamp for the range deletion.
   to_timestamp: the end timestamp for the range deletion.

   Integer reply: the number of samples that were deleted.
   Simple error reply in these cases: invalid arguments, wrong key type, timestamp is older than the retention period compared to the maximum existing timestamp, or when an affected compaction bucket cannot be recalculated."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-deleterule!
  "Deletes a compaction rule between two time series.
   The rule is identified by the source and destination key pair.
   This command does not delete the compacted (destination) time series itself.
   If the rule does not exist, or if the arguments are invalid, a simple error is returned.
   In clustered Redis environments, the behavior of this command may vary; see the multi-key operations documentation for details.

   ```
   TS.DELETERULE sourceKey destKey
   ```

   sourceKey: key name for the source time series.
   destKey: key name for destination (compacted) time series.

   Simple string reply: OK when the compaction rule is deleted successfully.
   Simple error reply in these cases: invalid arguments, or when such rule does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-get!
  "Retrieves the sample with the highest timestamp from a given time series.
   NaN values are included in the results.
   If the key does not exist or is of the wrong type, an error is returned.
   If the time series is empty, an empty array is returned.
   When the time series is a compaction, the LATEST option controls whether the compacted value of the latest (possibly partial) bucket is included.
   Without LATEST, the latest (possibly partial) bucket is not reported.
   When the time series is not a compaction, LATEST is ignored.

   ```
   TS.GET key [LATEST]
   ```

   key: the key name for the time series.
   LATEST: when the time series is a compaction, includes the compacted value of the latest (possibly partial) bucket; without it, that bucket is not reported; ignored when the time series is not a compaction.

   Array reply of a single (Integer reply, Double reply) pair representing (timestamp, value) of the sample with the highest timestamp.
   An empty Array reply when the time series is empty.
   Simple error reply in these cases: invalid arguments, wrong key type, key does not exist, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-incrby!
  "Increases the value of the sample with the maximum existing timestamp by the given addend.
   If the key does not exist, a new time series is created and the sample value is set to the addend.
   The command can be used as a counter or gauge that automatically accumulates history as a time series.
   An error is returned if the addend is NaN, or if the current value at the maximum existing timestamp is NaN.
   If a duplicate-sample IGNORE policy is defined for the time series, TS.INCRBY operations are affected and sample additions or modifications can be filtered.
   Explicitly adding samples to a compacted time series may result in inconsistencies between the raw and the compacted data, as the compaction process may override such samples.
   The timestamp must be equal to or higher than the maximum existing timestamp; if equal, the existing sample value is increased, and if higher, a new sample is created with the value of the previous maximum sample plus the addend.
   When the timestamp is not specified, it defaults to the Unix time of the server clock.

   ```
   TS.INCRBY key addend 
     [TIMESTAMP timestamp] 
     [RETENTION retentionPeriod] 
     [ENCODING <COMPRESSED|UNCOMPRESSED>] 
     [CHUNK_SIZE size] 
     [DUPLICATE_POLICY policy] 
     [IGNORE ignoreMaxTimediff ignoreMaxValDiff]   
     [LABELS [label value ...]]
   ```

   key: key name for the time series.
   addend: numeric value of the addend (double), an error is returned if the addend is NaN.
   TIMESTAMP timestamp: Unix time in milliseconds specifying the sample timestamp, or * to use the server clock time.
   RETENTION retentionPeriod: maximum retention period in milliseconds, used only when creating a new time series.
   ENCODING enc: specifies the series sample encoding format, used only when creating a new time series.
   CHUNK_SIZE size: memory size in bytes allocated for each data chunk, used only when creating a new time series.
   DUPLICATE_POLICY policy: policy for handling insertion of multiple samples with identical timestamps, used only when creating a new time series.
   IGNORE ignoreMaxTimediff ignoreMaxValDiff: policy for handling duplicate samples based on time and value differences, used only when creating a new time series.
   LABELS [label value ...]: set of label-value pairs representing metadata labels of the key, used only when creating a new time series.

   Integer reply: the timestamp of the upserted sample. If the sample is ignored, the reply will be the largest timestamp in the time series.
   Simple error reply in these cases: invalid arguments, wrong key type, or when timestamp is not equal to or higher than the maximum existing timestamp."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-info!
  "Return information and statistics for a time series.
   The DEBUG flag provides more detailed information about the chunks.

   ```
   TS.INFO key [DEBUG]
   ```

   key: key name of the time series.
   DEBUG: optional flag to get more detailed information about the chunks.

   Map reply with information about the time series, organized as key-value pairs in a map structure rather than a flattened array.
   When DEBUG is specified, the response also contains the additional keySelfName and Chunks fields."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-madd!
  "TS.MADD appends new samples to one or more time series in a single call.
   Each sample is specified as a key, timestamp, and value triple, and multiple triples can be provided.
   If a timestamp is older than the retention period compared to the maximum existing timestamp in the series, the sample is discarded and an error is returned.
   Adding samples to a compacted time series may result in inconsistencies between the raw and compacted data, as the compaction process may override such samples.
   The ignoreMaxTimeDiff and ignoreMaxValDiff options cannot be specified directly, but the same logic applies based on the per-key configuration parameters set at creation time.

   ```
   TS.MADD key timestamp value [key timestamp value ...]
   ```

   key: the key name for the time series.
   timestamp: Unix time in milliseconds specifying the sample timestamp, or * to use the server clock time.
   value: the numeric data value of the sample as a double following RFC 7159; overly large values that would not fit in binary64 are rejected, and infinite values are not accepted.

   Array reply: each element is an integer representing the timestamp of an upserted sample; for each element that is ignored, the value is the largest timestamp in the time series.
   Simple error reply: returned for invalid arguments, wrong key type, duplication policy set to BLOCK, or when the timestamp is older than the retention period compared to the maximum existing timestamp."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-mget!
  "Gets the sample with the highest timestamp from each time series matching the specified filter expression.
   NaN values are included in the results.
   The command replies with an error if the current user does not have read access to all keys that match the filter.
   In clustered Redis environments, behavior varies; see the multi-key operations documentation.
   The MGET command cannot be part of a transaction when running on a Redis cluster.

   ```
   TS.MGET [LATEST] [WITHLABELS | <SELECTED_LABELS label...>] FILTER filterExpr...
   ```

   FILTER filterExpr...: filters time series based on their labels and label values; at least one expression of the form label=value or label=(value1,value2,...) is required, and all expressions are conjunctive.
   LATEST: when a time series is a compaction, also reports the compacted value of the latest possibly partial bucket; ignored when the time series is not a compaction.
   WITHLABELS: includes all label-value pairs of the time series in the reply; if neither WITHLABELS nor SELECTED_LABELS is specified, an empty list is reported as label-value pairs.
   SELECTED_LABELS label...: returns a subset of the label-value pairs representing metadata labels of the time series; if neither WITHLABELS nor SELECTED_LABELS is specified, an empty list is reported as label-value pairs.

   Map reply: for each time series matching the specified filters, the following is reported:
   Bulk string reply: The time series key name
   Map reply or Array reply: label-value pairs
   By default, an empty array is reported
   If WITHLABELS is specified, all labels associated with this time series are reported as a map
   If SELECTED_LABELS label... is specified, the selected labels are reported as a map (null value when no such label defined)
   Array reply: a single timestamp-value pair (Integer reply, Double reply)"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-mrange!
  "Queries a range of samples across multiple time series in the forward direction, selecting which series to include via label-based filter expressions.
   NaN values are included in raw measurement reports, that is queries without aggregation.
   The command replies only if the current user has read access to every key that matches the filter; otherwise it returns an error indicating the user lacks read permission on one or more matching keys.
   An MRANGE command cannot be part of a transaction when running on a Redis cluster.

   ```
   TS.MRANGE fromTimestamp toTimestamp
     [LATEST]
     [FILTER_BY_TS ts...]
     [FILTER_BY_VALUE min max]
     [WITHLABELS | <SELECTED_LABELS label...>]
     [COUNT count]
     [[ALIGN align] AGGREGATION aggregators bucketDuration [BUCKETTIMESTAMP bt] [EMPTY]]
     FILTER filterExpr...
     [GROUPBY label REDUCE reducer]
     [EXCLUDEEMPTY]
   ```

   fromTimestamp: the start timestamp for the range query, an integer Unix timestamp in milliseconds, or - to denote the timestamp of the earliest sample among all time series that pass the filter.
   toTimestamp: the end timestamp for the range query, an integer Unix timestamp in milliseconds, or + to denote the timestamp of the latest sample among all time series that pass the filter.
   FILTER filterExpr...: filters time series based on their labels and label values; at least one expression of the form label=value or label=(value1,value2,...) is required, and expressions are conjunctive.
   LATEST: when a time series is a compaction, also reports the compacted value of the latest possibly partial bucket if its start time falls within the range; ignored for non-compaction series.
   FILTER_BY_TS ts...: filters samples by a list of specific timestamps; a sample passes if its exact timestamp is in the list and falls within the range; when used with AGGREGATION, filtering happens before aggregation.
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values; min and max cannot be NaN; when used with AGGREGATION, filtering happens before aggregation.
   WITHLABELS: includes all label-value pairs of the time series in the reply; by default an empty list is reported.
   SELECTED_LABELS label...: returns only the specified subset of label-value pairs; by default an empty list is reported.
   COUNT count: without AGGREGATION, limits the number of reported samples per time series; with AGGREGATION, limits the number of reported buckets.
   ALIGN align: controls the time bucket alignment for AGGREGATION; accepts start or - for the query start time, end or + for the query end time, or a specific timestamp; defaults to 0 when not provided.
   AGGREGATION aggregators bucketDuration: aggregates samples into time buckets of the given duration in milliseconds; aggregators is one or more comma-separated values from avg, sum, min, max, range, count, countNaN, countAll, first, last, std.p, std.s, var.p, var.s, twa.
   BUCKETTIMESTAMP bt: controls how bucket timestamps are reported; accepts - or start for the bucket start time (default), + or end for the bucket end time, or ~ or mid for the bucket mid time rounded down.
   EMPTY: when specified, reports aggregations also for empty buckets; the value reported depends on the aggregator (0 for sum and count, NaN for most others, last sample value for last, interpolated value for twa).
   GROUPBY label REDUCE reducer: splits time series into groups by the value of the given label, then aggregates results within each group using the specified reducer; the produced series is named label=value and carries __reducer__ and __source__ labels; cannot be combined with multiple aggregators or with EXCLUDEEMPTY.
   EXCLUDEEMPTY: excludes from the reply any time series that has no samples in the requested range; a series whose only samples in the range are NaN is still reported; cannot be used together with GROUPBY.

   If GROUPBY label REDUCE reducer is not specified:
   Map reply: for each time series matching the specified filters, the following is reported:
   Bulk string reply: the time series key name.
   Map reply or Array reply: label-value pairs; by default an empty map is reported; if WITHLABELS is specified all labels are reported as a map; if SELECTED_LABELS is specified the selected labels are reported as a map with null value when no such label is defined.
   Additional metadata including aggregators information.
   Array reply: representing all samples or aggregations matching the range; without AGGREGATION or with a single aggregator, timestamp-value pairs (Integer reply, Double reply); with multiple aggregators, timestamp-value tuples (Integer reply, multiple Double reply).
   If GROUPBY label REDUCE reducer is specified:
   Similar structure to the non-GROUPBY reply but with map-based organization for labels and metadata, and double values instead of string values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-mrevrange!
  "Queries a range across multiple time series by filters in the reverse direction.
   NaN values are included in raw measurement reports (queries without aggregation).
   The command replies only if the current user has read access to all keys that match the filter; otherwise it returns an error indicating the user lacks read permission to one or more matching keys.
   An MREVRANGE command cannot be part of a transaction when running on a Redis cluster.

   ```
   TS.MREVRANGE fromTimestamp toTimestamp
     [LATEST]
     [FILTER_BY_TS ts...]
     [FILTER_BY_VALUE min max]
     [WITHLABELS | <SELECTED_LABELS label...>]
     [COUNT count]
     [[ALIGN align] AGGREGATION aggregators bucketDuration [BUCKETTIMESTAMP bt] [EMPTY]]
     FILTER filterExpr...
     [GROUPBY label REDUCE reducer]
     [EXCLUDEEMPTY]
   ```

   fromTimestamp: the start timestamp for the range query (integer Unix timestamp in milliseconds) or - to denote the timestamp of the earliest sample among all the time series that passes FILTER filterExpr...
   toTimestamp: the end timestamp for the range query (integer Unix timestamp in milliseconds) or + to denote the timestamp of the latest sample among all the time series that passes FILTER filterExpr...
   FILTER filterExpr...: filters time series based on their labels and label values; at least one filter expression with a syntax label=value or label=(value1,value2,...) is required, and filter expressions are conjunctive
   LATEST: when a time series is a compaction, also reports the compacted value of the latest (possibly partial) bucket given that this bucket start time falls within [fromTimestamp, toTimestamp]; ignored when the time series is not a compaction
   FILTER_BY_TS ts...: filters samples by a list of specific timestamps; a sample passes if its exact timestamp is specified and falls within [fromTimestamp, toTimestamp]; when used with AGGREGATION, samples are filtered before being aggregated
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values; min and max cannot be NaN; when used with AGGREGATION, samples are filtered before being aggregated
   WITHLABELS: includes in the reply all label-value pairs representing metadata labels of the time series; if neither WITHLABELS nor SELECTED_LABELS is specified, an empty list is reported
   SELECTED_LABELS label...: returns a subset of the label-value pairs representing metadata labels of the time series; if neither WITHLABELS nor SELECTED_LABELS is specified, an empty list is reported
   COUNT count: without AGGREGATION, limits the number of reported samples per time series; with AGGREGATION, limits the number of reported buckets
   ALIGN align: a time bucket alignment control for AGGREGATION that changes the reference timestamp on which a bucket is defined; values include start or - (reference is fromTimestamp), end or + (reference is toTimestamp), or a specific timestamp; when not provided, alignment is set to 0
   AGGREGATION aggregators bucketDuration: for each time series, aggregates samples into time buckets of the given duration in milliseconds; aggregators is one or more comma-separated aggregators (avg, sum, min, max, range, count, countNaN, countAll, first, last, std.p, std.s, var.p, var.s, twa); without ALIGN, bucket start times are multiples of bucketDuration; with ALIGN, bucket start times are multiples of bucketDuration with remainder align % bucketDuration; the first bucket start time is less than or equal to fromTimestamp
   BUCKETTIMESTAMP bt: controls how bucket timestamps are reported; - or start reports the bucket start time (default), + or end reports the bucket end time, ~ or mid reports the bucket mid time rounded down
   EMPTY: when specified, reports aggregations also for empty buckets; sum and count report 0, last reports the value of the last sample before the bucket start (NaN when no such sample), twa reports the average based on linear interpolation (NaN when no such samples), and min, max, range, avg, first, std.p, std.s report NaN; no data is reported for buckets that end before the earliest sample or begin after the latest sample
   GROUPBY label REDUCE reducer: splits time series into groups sharing the same value for the provided label, then aggregates results in each group using the specified reducer; when combined with AGGREGATION, GROUPBY/REDUCE is applied post aggregation and is not permitted with multiple aggregators; the produced time series is named label=value and contains labels __reducer__ and __source__
   EXCLUDEEMPTY: excludes from the reply any time series that has no samples in the requested range; a time series whose only samples in the range are NaN is not considered empty; cannot be used together with GROUPBY label REDUCE reducer

   If GROUPBY label REDUCE reducer is not specified:
   Map reply: for each time series matching the specified filters, the following is reported:
   Bulk string reply: The time series key name
   Map reply or Array reply: label-value pairs
   By default, an empty map is reported
   If WITHLABELS is specified, all labels associated with this time series are reported as a map
   If SELECTED_LABELS label... is specified, the selected labels are reported as a map
   Additional metadata including aggregators information
   Array reply: representing all samples/aggregations matching the range in reverse chronological order:
   Without AGGREGATION or with a single aggregator: timestamp-value pairs (Integer reply, Double reply) representing (timestamp, value)
   With multiple aggregators: timestamp-value tuples (Integer reply, multiple Double reply) representing (timestamp, value...)
   If GROUPBY label REDUCE reducer is specified:
   Similar structure to the non-GROUPBY case but with map-based organization for labels and metadata, and double values instead of string values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-nrange!
  "Queries a range of samples across an explicit list of time series in the forward direction.
   Results are grouped by timestamp: for each timestamp in the range, the reply contains the timestamp followed by one value per time series, in the order the keys are given.
   A time series with no sample at a given timestamp returns NaN for that timestamp.
   When AGGREGATION is used, a time series can contribute more than one value per timestamp, one for each aggregator requested.

   In a Redis cluster, all specified keys must map to the same hash slot.
   The command does not split a request across shards or merge replies from multiple hash slots.

   ```
   TS.NRANGE numkeys key [key ...] fromTimestamp toTimestamp [LATEST]
     [FILTER_BY_TS ts [ts ...]] [FILTER_BY_VALUE min max] [COUNT count]
     [[ALIGN align] AGGREGATION aggregators [aggregators ...] bucketDuration
     [BUCKETTIMESTAMP <start | - | end | + | mid | ~>] [EMPTY]]
   ```

   numkeys: the number of time series keys that follow, must be a positive integer and must equal the number of key arguments.
   key [key ...]: the explicit time series keys to query, key order and duplicate keys are significant, a repeated key produces a separate value for each occurrence.
   fromTimestamp: the start timestamp for the range query, an integer Unix timestamp in milliseconds or - to denote the earliest sample among the specified time series, the range is inclusive.
   toTimestamp: the end timestamp for the range query, an integer Unix timestamp in milliseconds or + to denote the latest sample among the specified time series, the range is inclusive.
   LATEST: when a time series is a compaction, also reports the compacted value of the latest possibly partial bucket if its start time falls within the range, without LATEST the latest partial bucket is not reported, ignored for non-compaction series.
   FILTER_BY_TS ts [ts ...]: filters samples by a list of specific timestamps, a sample passes if its exact timestamp is specified and falls within the range, samples are filtered before pivoting and before aggregation.
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values, a sample passes if its value is within the inclusive range, min and max cannot be NaN, samples are filtered before pivoting and before aggregation.
   COUNT count: limits the number of returned entries, keeping those with the lowest timestamps, applied after the per-timestamp merge.
   ALIGN align: a time bucket alignment control for AGGREGATION, controls the reference timestamp on which a bucket is defined, values include start or - for the query start time, end or + for the query end time, or a specific timestamp, defaults to 0 when not provided.
   AGGREGATION aggregators [aggregators ...] bucketDuration: aggregates samples into time buckets, provide one or more aggregators per key in the same order as the keys, the number of aggregator arguments must equal numkeys, each per-key argument is a single aggregator or a comma-separated list, bucketDuration is the duration of each bucket in milliseconds.
   BUCKETTIMESTAMP <start | - | end | + | mid | ~>: controls how bucket timestamps are reported, - or start for the bucket start time (default), + or end for the bucket end time, ~ or mid for the bucket mid time rounded down.
   EMPTY: when specified, reports aggregations also for empty buckets, sum and count report 0, last reports the value of the last sample before the bucket start or NaN, twa reports a linearly interpolated average or NaN, and min max range avg first std.p std.s report NaN.

   Array reply with one entry per timestamp, ordered by increasing timestamp.
   Each entry is an Array reply composed of an Integer reply (the timestamp) and a flat Array reply of Double reply values.
   The values are concatenated across keys in input order; with AGGREGATION, each key contributes one value per aggregator, otherwise one value per key.
   A missing value is reported as NaN.
   The reply is an empty array when no samples match.
   Simple error reply in these cases: invalid arguments, wrong number of aggregators, unknown aggregation type, wrong key type, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-nrevrange!
  "Queries a range across an explicit list of time series in the reverse direction.
   Results are grouped by timestamp: for each timestamp, the reply contains the timestamp followed by one value per time series, in the order the keys are given.
   With AGGREGATION, a time series can contribute more than one value per timestamp, one for each aggregator requested.
   A time series with no sample at a given timestamp returns NaN for that timestamp.
   In a Redis cluster, all specified keys must map to the same hash slot; the command does not split a request across shards or merge replies from multiple hash slots.

   ```
   TS.NREVRANGE numkeys key [key ...] fromTimestamp toTimestamp
     [LATEST] [FILTER_BY_TS ts [ts ...]] [FILTER_BY_VALUE min max]
     [COUNT count] [[ALIGN align] AGGREGATION aggregators [aggregators ...]
     bucketDuration [BUCKETTIMESTAMP <start | - | end | + | mid | ~>]
     [EMPTY]]
   ```

   numkeys: the number of time series keys that follow, must be a positive integer and must equal the number of key arguments.
   key [key ...]: the explicit time series keys to query, key order and duplicate keys are significant, a repeated key produces a separate value for each occurrence.
   fromTimestamp: the start timestamp for the range query (integer Unix timestamp in milliseconds) or - to denote the timestamp of the earliest sample among the specified time series, the range is inclusive.
   toTimestamp: the end timestamp for the range query (integer Unix timestamp in milliseconds) or + to denote the timestamp of the latest sample among the specified time series, the range is inclusive.
   LATEST: when a time series is a compaction, also reports the compacted value of the latest possibly partial bucket given that its start time falls within the range, without LATEST the latest possibly partial bucket is not reported, ignored when the time series is not a compaction.
   FILTER_BY_TS ts [ts ...]: filters samples by a list of specific timestamps, a sample passes if its exact timestamp is specified and falls within the range, samples are filtered before pivoting and before aggregation.
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values, a sample passes if its value is within the inclusive range, min and max cannot be NaN, samples are filtered before pivoting and before aggregation.
   COUNT count: limits the number of returned entries, keeping those with the highest timestamps, the limit is applied after the per-timestamp merge.
   ALIGN align: a time bucket alignment control for AGGREGATION, controls the reference timestamp on which a bucket is defined, values include start or - for the query start interval time, end or + for the query end interval time, or a specific timestamp, when not provided alignment defaults to 0.
   AGGREGATION aggregators [aggregators ...] bucketDuration: aggregates samples into time buckets, provide one or more aggregators per key in the same order as the keys, the number of aggregator arguments must equal numkeys, each per-key argument is either a single aggregator or a comma-separated list, bucketDuration is the duration of each bucket in milliseconds.
   BUCKETTIMESTAMP <start | - | end | + | mid | ~>: controls how bucket timestamps are reported, - or start reports the bucket start time (default), + or end reports the bucket end time, ~ or mid reports the bucket mid time rounded down.
   EMPTY: when specified, reports aggregations also for empty buckets, sum and count report 0, last reports the value of the last sample before the bucket start or NaN, twa reports a linearly interpolated average or NaN, and min max range avg first std.p std.s report NaN.

   Array reply with one entry per timestamp, ordered by decreasing timestamp.
   Each entry is an Array reply composed of an Integer reply (the timestamp) and a flat Array reply of Double reply values.
   The values are concatenated across keys in input order; with AGGREGATION, each key contributes one value per aggregator, otherwise one value per key.
   A missing value is reported as NaN.
   The reply is an empty array when no samples match.
   Simple error reply in these cases: invalid arguments, wrong number of aggregators, unknown aggregation type, wrong key type, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-queryindex!
  "Gets all time series keys matching a given filter list.
   All matching keys are returned regardless of whether the user has read access to them.
   Filter expressions are conjunctive, so multiple expressions are combined with AND.
   At least one filter expression of the form label=value or label=(value1,value2,...) is required.
   Whitespace is not allowed in a filter expression except within quoted values.
   This command cannot be part of a transaction when running on a Redis cluster.

   ```
   TS.QUERYINDEX filterExpr...
   ```

   filterExpr...: filters time series based on their labels and label values, supporting syntaxes for label presence, equality, list membership, and negation of labels and values.

   Set reply where each element is a Bulk string reply: a time series key. The set is empty if no time series matches the filter.
   Simple error reply in these cases: invalid filter expression."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-querylabels!
  "Returns the set of distinct label names used by matching time series, or the set of distinct values for a specific label.
   When no filter is given, all indexed time series are considered.
   Each name or value appears at most once in the result, regardless of how many series it belongs to, and the order of results is not defined.

   ```
   TS.QUERYLABELS <LABELS | VALUES label> [FILTER filterExpr
     [filterExpr ...]]
   ```

   LABELS | VALUES label: selects what to return; LABELS gives the set of distinct label names, VALUES label gives the set of distinct values of that label.
   FILTER filterExpr [filterExpr ...]: restricts the command to time series whose labels match all filter expressions; when omitted, all indexed time series are considered; each expression uses one of the forms label!=, label=value, label=(v1,v2,...), label=, label!=value, or label!=(v1,v2,...), and at least one equality form is required.

   Set reply where each element is a Bulk string reply: a label name (with LABELS) or a label value (with VALUES). The set is empty if no time series matches the filter.
   Simple error reply in these cases: invalid subtype, missing label after VALUES, or invalid filter expression."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-range!
  "Queries a range of samples in a time series in the forward direction.
   NaN values are included in raw measurement reports when no aggregation is applied.
   If the key does not exist, is not a time series, or a filter value is invalid, an error is returned.
   When the time series is a compaction, toTimestamp limits the timestamp of the compacted value,
   which is the start time of the raw bucket that was compacted, not the individual raw values within it.

   ```
   TS.RANGE key fromTimestamp toTimestamp
     [LATEST]
     [FILTER_BY_TS ts...]
     [FILTER_BY_VALUE min max]
     [COUNT count] 
     [[ALIGN align] AGGREGATION aggregators bucketDuration [BUCKETTIMESTAMP bt] [EMPTY]]
   ```

   key: the key name for the time series.
   fromTimestamp: start timestamp for the range query, an integer Unix timestamp in milliseconds,
     or - to denote the timestamp of the earliest sample in the time series.
   toTimestamp: end timestamp for the range query, an integer Unix timestamp in milliseconds,
     or + to denote the timestamp of the latest sample in the time series.
   LATEST: when the time series is a compaction, also reports the compacted value of the latest
     possibly partial bucket if its start time falls within the range; ignored for non-compaction series.
   FILTER_BY_TS ts...: filters samples by a list of specific timestamps; a sample passes if its exact
     timestamp is in the list and falls within the range; when used with AGGREGATION, samples are
     filtered before aggregation.
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values; min and max cannot be NaN;
     when used with AGGREGATION, samples are filtered before aggregation.
   COUNT count: limits the number of reported samples without AGGREGATION,
     or the number of reported buckets with AGGREGATION.
   ALIGN align: controls time bucket alignment for AGGREGATION by changing the reference timestamp;
     accepts start or - for fromTimestamp, end or + for toTimestamp, or a specific timestamp;
     defaults to 0 when not provided.
   AGGREGATION aggregators bucketDuration: aggregates samples into time buckets of the given duration
     in milliseconds; aggregators is one or more comma-separated values from avg, sum, min, max, range,
     count, countNaN, countAll, first, last, std.p, std.s, var.p, var.s, twa;
     without ALIGN, bucket start times are multiples of bucketDuration;
     with ALIGN, bucket start times are multiples of bucketDuration with remainder align % bucketDuration;
     the first bucket start time is less than or equal to fromTimestamp.
   BUCKETTIMESTAMP bt: controls how bucket timestamps are reported;
     - or start reports the bucket start time (default), + or end reports the bucket end time,
     ~ or mid reports the bucket mid time rounded down.
   EMPTY: when specified, reports aggregations also for empty buckets;
     sum and count report 0, last reports the value of the last sample before the bucket start or NaN,
     twa reports a linearly interpolated average or NaN,
     and min, max, range, avg, first, std.p, std.s report NaN;
     no data is reported for buckets that end before the earliest sample or begin after the latest sample.

   Array reply of
   Without AGGREGATION or with a single aggregator: (Integer reply, Double reply) pairs representing (timestamp, value).
   With multiple aggregators: (Integer reply, multiple Double reply) tuples representing (timestamp, value...).
   Simple error reply in these cases: invalid filter value, wrong key type, key does not exist, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-read!
  "TS.READ reads a batch of time series samples at or after a given timestamp,
   returning up to max_count of the oldest qualifying samples ordered by increasing
   timestamp.
   The key may identify a regular time series or a compaction time series.
   By default the command returns immediately with whatever samples are available.
   When the BLOCK keyword is present, the command waits until at least min_count
   qualifying samples exist, until milliseconds elapse, or until the key is removed,
   whichever occurs first.
   The timestamp argument is an inclusive lower-bound cursor: samples whose timestamp
   is greater than or equal to the resolved cursor are selected.
   The cursor can be a non-negative integer Unix timestamp in milliseconds, or one of
   the sentinels -, +, or $, which the server resolves exactly once when the command
   is received so the cursor stays stable while the client is blocked.
   A wrong key type, invalid timestamp, invalid milliseconds, invalid min_count or
   max_count, min_count greater than max_count, wrong number of arguments, or using
   BLOCK where blocking is not allowed (for example inside MULTI or a Lua script)
   produces an error reply.

   ```
   TS.READ key timestamp [BLOCK milliseconds min_count]
     [MAX_COUNT max_count]
   ```

   key: the key name for the time series, which may identify a regular series or a compaction series.
   timestamp: the inclusive lower-bound cursor for the read; a non-negative integer Unix timestamp in milliseconds, or one of the sentinels -, +, or $.
   BLOCK milliseconds min_count: makes the command block until at least min_count qualifying samples are available, until milliseconds elapse, or until the key is removed; milliseconds of 0 means block indefinitely, and min_count must be a positive integer.
   MAX_COUNT max_count: the maximum number of samples to return; must be a positive integer and, when BLOCK is used, greater than or equal to min_count; when omitted the reply is unbounded.

   Array reply of (Integer reply, Double reply) pairs representing (timestamp, value), ordered by increasing timestamp.
   The array is empty when no matching samples are available by the time the command returns, or when the key was removed while the command was blocked.
   Simple error reply in these cases: invalid timestamp, invalid milliseconds, invalid min_count or max_count, min_count greater than max_count, wrong number of arguments, wrong key type, the command is used with BLOCK where blocking is not allowed (for example, inside MULTI or a Lua script), etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ts-revrange!
  "Queries a time series in reverse chronological order,
   returning samples from toTimestamp down to fromTimestamp.
   If the key does not exist or is not a time series, an error is returned.
   When the time series is a compaction, the last compacted value may aggregate
   raw values with timestamps beyond toTimestamp, because toTimestamp limits only
   the timestamp of the compacted value (the start time of the raw bucket that
   was compacted).
   NaN values are included in raw measurement reports (queries without aggregation).

   ```
   TS.REVRANGE key fromTimestamp toTimestamp
     [LATEST]
     [FILTER_BY_TS ts...]
     [FILTER_BY_VALUE min max]
     [COUNT count]
     [[ALIGN align] AGGREGATION aggregators bucketDuration [BUCKETTIMESTAMP bt] [EMPTY]]
   ```

   key: the key name for the time series.
   fromTimestamp: start timestamp for the range query (integer Unix timestamp
     in milliseconds) or - to denote the timestamp of the earliest sample.
   toTimestamp: end timestamp for the range query (integer Unix timestamp
     in milliseconds) or + to denote the timestamp of the latest sample.
   LATEST: when the time series is a compaction, also reports the compacted
     value of the latest possibly partial bucket, given that its start time
     falls within [fromTimestamp, toTimestamp]; ignored when the time series
     is not a compaction.
   FILTER_BY_TS ts...: filters samples by a list of specific timestamps;
     a sample passes if its exact timestamp is specified and falls within
     [fromTimestamp, toTimestamp]; when used with AGGREGATION, samples are
     filtered before aggregation.
   FILTER_BY_VALUE min max: filters samples by minimum and maximum values;
     min and max cannot be NaN; when used with AGGREGATION, samples are
     filtered before aggregation.
   COUNT count: limits the number of reported samples (without AGGREGATION)
     or buckets (with AGGREGATION).
   ALIGN align: controls time bucket alignment for AGGREGATION; values are
     start or - (reference is fromTimestamp, which cannot be -), end or +
     (reference is toTimestamp, which cannot be +), or a specific timestamp;
     defaults to 0 when not provided.
   AGGREGATION aggregators bucketDuration: aggregates samples into time
     buckets of the given duration in milliseconds; aggregators is one or more
     comma-separated values from: avg, sum, min, max, range, count, countNaN,
     countAll, first, last, std.p, std.s, var.p, var.s, twa; without ALIGN,
     bucket start times are multiples of bucketDuration; with ALIGN, bucket
     start times are multiples of bucketDuration with remainder
     align % bucketDuration; the first bucket start time is less than or
     equal to fromTimestamp.
   BUCKETTIMESTAMP bt: controls how bucket timestamps are reported;
     - or start reports the bucket start time (default), + or end reports
     the bucket end time, ~ or mid reports the bucket mid time (rounded down
     if not an integer).
   EMPTY: when specified, reports aggregations also for empty buckets;
     for sum and count the value is 0, for last it is the value of the last
     sample before the bucket start (NaN when none), for twa it is the
     linearly interpolated average (NaN when no such samples), and for min,
     max, range, avg, first, std.p, std.s the value is NaN; no data is
     reported for buckets that end before the earliest sample or begin after
     the latest sample.

   Array reply of (Integer reply, Double reply) pairs representing
   (timestamp, value) in reverse chronological order, without AGGREGATION
   or with a single aggregator.
   Array reply of (Integer reply, multiple Double reply) tuples representing
   (timestamp, value...) in reverse chronological order, with multiple
   aggregators.
   Simple error reply in these cases: invalid filter value, wrong key type,
   key does not exist, etc."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(ns redis.cmd.search)

(defn ft-aggregate!
  "Runs a search query against a RediSearch index and applies a pipeline of aggregate transformations to the matching documents.
   The pipeline can group, reduce, sort, apply expressions, filter, and limit results in sequence.
   Only keys to which the user has read access are returned.
   If the index does not exist, the query syntax is invalid, or the number of arguments is incorrect, a simple error is returned.

   ```
   FT.AGGREGATE index query 
     [VERBATIM] 
     [LOAD count field [field ...]] 
     [TIMEOUT timeout] 
     [GROUPBY nargs property [property ...] [REDUCE function nargs arg [arg ...] [AS name] [REDUCE function nargs arg [arg ...] [AS name] ...]] ...]] 
     [SORTBY nargs [property ASC | DESC [property ASC | DESC ...]] [MAX num] [WITHCOUNT | WITHOUTCOUNT]] 
     [APPLY expression AS name [APPLY expression AS name ...]] 
     [LIMIT offset num] 
     [FILTER filter] 
     [WITHCURSOR [COUNT read_size] [MAXIDLE idle_time]] 
     [PARAMS nargs name value [name value ...]] 
     [SCORER scorer]
     [ADDSCORES] 
     [DIALECT dialect]
   ```

   index: the name of the index against which the query is executed, created previously with FT.CREATE.
   query: the base filtering query that retrieves documents, using the same syntax as a search query including filters, unions, not, and optional clauses.
   VERBATIM: if set, disables stemming for query expansion and searches the query terms verbatim.
   LOAD count field [field ...]: loads document attributes from the source document; count is the number of fields to load (use * for all), each field is an attribute name or JSON Path expression, and an optional AS name can rename the loaded property.
   GROUPBY nargs property [property ...]: groups results by one or more properties; each group must have at least one REDUCE function.
   REDUCE function nargs arg [arg ...] [AS name]: reduces matching results in each group into a single record using a reduction function; if no name is given, the result is named after the function and group properties.
   SORTBY nargs [property ASC | DESC ...] [MAX num] [WITHCOUNT | WITHOUTCOUNT]: sorts the pipeline by one or more properties in ascending or descending order; MAX limits sorting to the n-largest elements for optimization; WITHCOUNT returns accurate counts at the cost of processing all results.
   APPLY expression AS name: applies a 1-to-1 transformation on properties and stores the result as a new property for further pipeline operations.
   LIMIT offset num: limits results to num entries starting at zero-based offset; more efficient to use SORTBY with MAX when limiting sorted output.
   FILTER filter: filters results using predicate expressions applied post-query to the current pipeline state.
   WITHCURSOR [COUNT read_size] [MAXIDLE idle_time]: scans part of the results using a cursor-based alternative to LIMIT.
   TIMEOUT timeout: overrides the module timeout in milliseconds.
   PARAMS nargs name value [name value ...]: defines named value parameters that can be referenced in the query with a dollar sign prefix; requires DIALECT 2 or greater.
   SCORER scorer: uses a built-in or user-provided scoring function.
   ADDSCORES: exposes the full-text score values to the pipeline, accessible as @__score.
   DIALECT dialect: selects the dialect version under which to execute the query; if not specified, the default dialect version is used.

   attributes: Array of attribute names.
   format: Simple string - result format.
   results: Array of maps containing aggregated data.
   total_results: Integer - total number of results.
   warning: Array of warning messages.
   Simple error reply in these cases: incorrect number of arguments, non-existent index, invalid query syntax."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-aliasadd!
  "Adds an alias to an existing index.
   An index can have more than one alias, but an alias cannot refer to another alias.
   This allows administrators to transparently redirect application queries to alternative indexes.
   An error is returned if the alias already exists or if the index does not exist.

   ```
   FT.ALIASADD alias index
   ```

   alias: the alias to be added to an index.
   index: the index to which the alias is added.

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: alias already exists, index does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-aliasdel!
  "Removes an alias from a RediSearch index.
   If the alias does not exist, a simple error is returned.

   ```
   FT.ALIASDEL alias
   ```

   alias: the index alias to be removed.

   Simple string reply: OK if executed correctly.
   Simple error reply: alias does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-aliaslist!
  "Lists all aliases for the given index.
   If the index does not exist, a simple error is returned.

   ```
   FT.ALIASLIST index
   ```

   index: the name of the index for which to list aliases.

   A set of bulk strings, the names of index aliases, or an empty set if no aliases exist.
   A simple error when the provided index doesn't exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-aliasupdate!
  "Adds an alias to an index.
   If the alias is already associated with another index, the previous association is removed and the alias is re-pointed to the new index.

   ```
   FT.ALIASUPDATE alias index
   ```

   alias: the alias to be added to an index.
   index: the index to associate the alias with.

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: index does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-alter!
  "Adds a new attribute to an existing index.
   Any future document updates will use the new attribute when indexing,
   and existing documents are reindexed to include it.
   If the SKIPINITIALSCAN option is set, the index is not rescanned
   and existing documents are not reindexed.
   The index must already exist; otherwise a simple error is returned.
   Invalid schema syntax also produces a simple error.
   Depending on how the index was created, the total number of text
   attributes may be capped at 32.
   If the index was not created with the MAXTEXTFIELDS option,
   SCHEMA ADD can only bring the total to 32 text attributes.

   ```
   FT.ALTER index [SKIPINITIALSCAN] SCHEMA ADD field options
   ```

   index: the name of the existing index to modify.
   SKIPINITIALSCAN: if set, does not scan and index existing documents.
   SCHEMA ADD {attribute} {options} ...: declares which fields to add;
   attribute is the attribute to add, options are attribute options
   (refer to FT.CREATE for details).

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: no such index, invalid schema syntax."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-config-get!
  "Retrieves configuration options for the search module.
   A specific option can be retrieved by name, or all options at once using the wildcard.
   This command is deprecated and can be replaced by CONFIG GET.

   ```
   FT.CONFIG GET option
   ```

   option: the name of the configuration option, or * for all.

   Map where keys are configuration option names and values are their corresponding values."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-config-set!
  "Sets the value of a RediSearch configuration parameter at runtime.
   Values set with this command are not persisted after a server restart.
   Not all RediSearch configuration parameters can be changed at runtime.
   This command is deprecated and can be replaced by CONFIG SET.

   ```
   FT.CONFIG SET option value
   ```

   option: name of the configuration option, or * for all.
   value: value of the configuration option.

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: invalid option, invalid value."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-create!
  "Creates a new search index over documents stored as hashes or JSON.
   If an index with the same name already exists, the command returns an error.
   The schema section declares which fields to index, their types (TEXT, TAG, NUMERIC, GEO, VECTOR, GEOSHAPE), and per-field options such as SORTABLE, UNF, NOSTEM, NOINDEX, PHONETIC, WEIGHT, SEPARATOR, CASESENSITIVE, WITHSUFFIXTRIE, INDEXEMPTY, and INDEXMISSING.
   Optional parameters control the data type, key prefixes, default language, scoring, payload handling, stopword lists, and various memory-saving flags.

   ```
   FT.CREATE index 
     [ON HASH | JSON] 
     [PREFIX count prefix [prefix ...]] 
     [FILTER {filter}]
     [LANGUAGE default_lang] 
     [LANGUAGE_FIELD lang_attribute] 
     [SCORE default_score] 
     [SCORE_FIELD score_attribute] 
     [PAYLOAD_FIELD payload_attribute] 
     [MAXTEXTFIELDS] 
     [TEMPORARY seconds] 
     [NOOFFSETS] 
     [NOHL] 
     [NOFIELDS] 
     [NOFREQS] 
     [STOPWORDS count [stopword ...]] 
     [SKIPINITIALSCAN]
     [INDEXALL <ENABLE | DISABLE>]
     SCHEMA field_name [AS alias] TEXT | TAG | NUMERIC | GEO | VECTOR | GEOSHAPE [ SORTABLE [UNF]] 
     [NOINDEX] [ field_name [AS alias] TEXT | TAG | NUMERIC | GEO | VECTOR | GEOSHAPE [ SORTABLE [UNF]] [NOINDEX] ...]
   ```

   index: the name of the index to create; if it already exists, an error is returned.
   SCHEMA: declares the fields to index, each with an identifier, optional alias, type, and options.
   ON {data_type}: selects the document type to index, HASH (default) or JSON.
   PREFIX {count} {prefix}: specifies which key prefixes to index; defaults to all keys.
   FILTER {filter}: a filter expression using the RediSearch aggregation language to restrict which documents are indexed.
   LANGUAGE {default_lang}: sets the default language for stemming; defaults to English.
   LANGUAGE_FIELD {lang_attribute}: a document attribute that overrides the per-document language.
   SCORE {default_score}: the default document score; defaults to 1.0.
   SCORE_FIELD {score_attribute}: a document attribute used as the per-document rank, must be between 0.0 and 1.0.
   PAYLOAD_FIELD {payload_attribute}: a document attribute holding a binary-safe payload string available at query time.
   MAXTEXTFIELDS: allows adding more than 32 text attributes later via FT.ALTER.
   TEMPORARY {seconds}: creates a temporary index that expires after the given seconds of inactivity.
   NOOFFSETS: does not store term offsets, saving memory but disabling exact searches and highlighting; implies NOHL.
   NOHL: disables highlighting support to save storage.
   NOFIELDS: does not store attribute bits per term, saving memory but disabling filtering by specific attributes.
   NOFREQS: does not store term frequencies, saving memory but disabling frequency-based sorting.
   STOPWORDS {count} [stopword ...]: sets a custom stopword list; a count of 0 means no stopwords.
   SKIPINITIALSCAN: skips the initial scan of existing keys.
   INDEXALL {ENABLE | DISABLE}: maintains a compact index of document IDs to optimize wildcard queries; defaults to DISABLE.

   Simple string reply: OK if executed correctly.
   Simple error reply: index already exists, invalid schema syntax."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-cursor-del!
  "Deletes a cursor from a RediSearch index.
   If the cursor does not exist, a simple error reply is returned.

   ```
   FT.CURSOR DEL index cursor_id
   ```

   index: the index name.
   cursor_id: the id of the cursor.

   Simple string reply: OK if executed correctly.
   Simple error reply: cursor does not exist."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-cursor-read!
  "Reads the next batch of results from an existing cursor.
   The cursor must have been created by a prior FT.AGGREGATE or FT.SEARCH command issued with the WITHCURSOR option.
   If the cursor is not found, a simple error is returned.
   The optional COUNT parameter overrides the COUNT value that was specified in the original FT.AGGREGATE command.

   ```
   FT.CURSOR READ index cursor_id [COUNT read size]
   ```

   index: the index name.
   cursor_id: the id of the cursor.
   COUNT read_size: number of results to read, overrides the COUNT specified in FT.AGGREGATE.

   Map with structured search results and metadata.
   Simple error reply in these cases: cursor not found."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-dictadd!
  "Adds terms to a dictionary.

   ```
   FT.DICTADD dict term [term ...]
   ```

   dict: the dictionary name.
   term: the term to add to the dictionary.

   Integer reply: the number of new terms added to the dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-dictdel!
  "Deletes one or more terms from a dictionary.
   The command returns the number of terms actually deleted.

   ```
   FT.DICTDEL dict term [term ...]
   ```

   dict: the dictionary name.
   term: the term to delete from the dictionary.

   Integer reply: the number of terms deleted from the dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-dictdump!
  "Dumps all terms stored in the given dictionary.

   ```
   FT.DICTDUMP dict
   ```

   dict: the dictionary name.

   Set of dictionary terms."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-dropindex!
  "Deletes a full-text search index by name.
   The index must have been previously created with FT.CREATE.
   By default, only the index metadata is removed and the underlying document keys are left untouched.
   When the DD option is supplied, the document keys associated with the index are also deleted.
   The DD operation is asynchronous; if an index creation is still in progress, only the hashes that have already been indexed are deleted, and the remaining ones stay in the database.
   Use FT.INFO to check whether indexing has completed.

   ```
   FT.DROPINDEX index [DD]
   ```

   index: the full-text index name, which must have been created with FT.CREATE.
   DD: if set, also deletes the document keys associated with the index; the operation is asynchronous.

   Simple string reply: OK if executed correctly.
   Simple error reply: no such index."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-explain!
  "Returns the execution plan for a complex query against the specified index.
   The index must already have been created with FT.CREATE.
   The query string is interpreted the same way as it would be in FT.SEARCH.
   If the index does not exist or the query contains a syntax error, a simple error reply is returned.
   The DIALECT option allows specifying the dialect version under which the query is parsed; when omitted, the default dialect version set during module initial loading or via FT.CONFIG SET is used.
   In the returned plan, a + on a term indicates that stemming is applied.

   ```
   FT.EXPLAIN index query [DIALECT dialect]
   ```

   index: the name of the index, which must have been created with FT.CREATE.
   query: the query string, interpreted as if sent to FT.SEARCH.
   DIALECT dialect: the dialect version under which to execute the query; if not specified, the default dialect version set during module initial loading or via FT.CONFIG SET is used.

   Bulk string containing the query execution plan.
   Simple error reply in these cases: no such index, syntax error in query."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-explaincli!
  "Returns the execution plan for a complex query, formatted for easier reading without needing redis-cli --raw.
   The index must already exist, created previously with FT.CREATE.
   The query is interpreted the same way as it would be for FT.SEARCH.
   If the index does not exist or the query contains a syntax error, a simple error reply is returned.
   The optional DIALECT argument lets you specify the dialect version under which the query is executed; if omitted, the default dialect version set during module initial loading or via FT.CONFIG SET is used.
   In the returned response, a + on a term indicates stemming.

   ```
   FT.EXPLAINCLI index query [DIALECT dialect]
   ```

   index: the name of the index, which must have been created with FT.CREATE.
   query: the query string, interpreted as if sent to FT.SEARCH.
   DIALECT dialect: the dialect version under which to execute the query, defaults to the one set during module initial loading or via FT.CONFIG SET.

   Array of bulk string replies containing the query execution plan in CLI format.
   Simple error reply in these cases: no such index, syntax error in query."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-hybrid!
  "Performs hybrid search that combines full-text search and vector similarity in a single query.
   It supports fusion methods such as Reciprocal Rank Fusion and linear combination to merge the two result sets.
   The command builds on the query syntax of FT.SEARCH and FT.AGGREGATE, allowing post-processing through aggregation capabilities.
   Only document IDs and scores that the user has read access to are returned.
   Use LOAD * or LOAD with specific fields to retrieve entire documents.

   ```
   FT.HYBRID index
     SEARCH query
       [SCORER scorer]
       [YIELD_SCORE_AS name]
     VSIM vector_field $vector_param
       [KNN count K k [EF_RUNTIME ef_runtime]] [SHARD_K_RATIO shard_k_ratio]]
       [RANGE count RADIUS radius [EPSILON epsilon]]
       [YIELD_SCORE_AS name]
       [FILTER] count filter-expression [POLICY [ADHOC/BATCHES] BATCH_SIZE batch-size-value]
     [COMBINE RRF count [CONSTANT constant] [WINDOW window] [YIELD_SCORE_AS name]]
     [COMBINE LINEAR count [[ALPHA alpha] [BETA beta]] [WINDOW window] [YIELD_SCORE_AS name]]
     [LIMIT offset num]
     [SORTBY count sortby [ASC | DESC]]
     [NOSORT]
     [LOAD count field [field ...]]
     [LOAD *]
     [GROUPBY nargs property [property ...]
     [GROUPBY nargs property [property ...]
       [REDUCE function nargs arg [arg ...] [AS name]
       [REDUCE function nargs arg [arg ...] [AS name] ...]] ...]]
     [APPLY expression AS name [APPLY expression AS name ...]]
     [FILTER filter]
     [EXPLAINSCORE]
     PARAMS nargs vector_param vector_blob [name value ...]
     [TIMEOUT timeout]
   ```

   index: the name of the index, which must have been created with FT.CREATE.
   SEARCH search-expression: defines the text search component using the same syntax as FT.SEARCH queries, supporting field-specific searches, boolean operations, and phrase matching.
   VSIM vector_field vector-data: defines the vector similarity component, specifying the vector field to search against and the query vector for similarity comparison.
   SCORER algorithm params...: specifies the scoring algorithm and parameters for the text search component.
   YIELD_SCORE_AS alias-search-score: assigns an alias to the search score for use in post-processing operations like APPLY or SORTBY.
   KNN count K top-k [EF_RUNTIME ef-value] [SHARD_K_RATIO shard-k-ratio] [YIELD_SCORE_AS name]: configures K-nearest neighbors vector search; EF_RUNTIME controls accuracy versus speed, SHARD_K_RATIO controls per-shard retrieval in cluster setups.
   RANGE count RADIUS radius-value [EPSILON epsilon-value] [YIELD_SCORE_AS name]: configures range-based vector search within a specified radius; EPSILON provides additional precision control.
   FILTER filter-expression: applies pre-filtering to vector search results or post-filtering after COMBINE; affects which documents are considered but does not impact scoring.
   POLICY [ADHOC_BF|BATCHES] [BATCH_SIZE batch-size-value]: controls the pre-filtering policy for vector queries; ADHOC_BF processes filters on-demand, BATCHES processes in configurable batch sizes.
   COMBINE method params...: specifies how to fuse text search and vector similarity results; supports RRF (default, with WINDOW default 20 and CONSTANT default 60) and LINEAR (with ALPHA and BETA weights).
   YIELD_SCORE_AS alias-combined-score: assigns an alias to the combined fusion score for use in post-processing operations.
   LOAD count field...: specifies which fields to return in the results.
   GROUPBY count field... REDUCE function...: groups results by specified fields and applies reduction functions.
   APPLY expression AS field: applies transformations to create new fields, can reference aliased scores and distances.
   SORTBY count field [ASC|DESC]: sorts the final results by the specified field in ascending or descending order.
   LIMIT offset num: limits the final results, default is 10 when not specified, offset is zero-indexed.
   PARAMS count key value...: defines parameter substitution for the query, referenced in expressions using the dollar-sign prefix.
   EXPLAINSCORE: returns an explanation of how each result combined score was calculated, including the fusion method, text branch scorer breakdown, and vector branch details; cannot be combined with GROUPBY.
   TIMEOUT timeout: sets a runtime timeout for query execution in milliseconds; with RETURN or RETURN_STRICT the command returns accumulated results and reports the timeout in warnings, with FAIL it returns an error.

   Map with the following fields:
   total_results: Integer - total number of results
   execution_time: double containing hybrid query execution time
   warnings: Array of warning messages indicating partial results due to index errors or MAXPREFIXEXPANSIONS, out-of-memory conditions, and TIMEOUT reached
   results: Array of maps containing document information
   Simple error reply in these cases: no such index, syntax error in query."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-info!
  "Returns information and statistics about a given index.
   The index must have been created beforehand with FT.CREATE.
   An alias created via FT.ALIASADD or FT.ALIASUPDATE may be used in place of the index name.
   If no such index exists, a simple error is returned.

   ```
   FT.INFO index
   ```

   index: the name of the index to inspect, or an alias pointing to it.

   Map containing index information and statistics as key-value pairs.
   Simple error reply in these cases: no such index."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-profile!
  "Runs FT.SEARCH, FT.HYBRID, or FT.AGGREGATE against a given index and collects performance profiling details alongside the results.
   The LIMITED option can be used to omit details of reader iterators, reducing the size of the output.
   To further reduce reply size, use NOCONTENT or LIMIT 0 0 to reduce the number of result entries, or LIMITED to skip reader iterator details inside built-in unions such as fuzzy or prefix iterators.

   ```
   FT.PROFILE index <SEARCH | HYBRID | AGGREGATE> [LIMITED] QUERY query
   ```

   index: the name of an index created using FT.CREATE.
   SEARCH | HYBRID | AGGREGATE: the profile type, either FT.SEARCH, FT.HYBRID, or FT.AGGREGATE.
   LIMITED: removes details of any reader iterators.
   QUERY query: the query string, sent to FT.SEARCH, FT.HYBRID, or FT.AGGREGATE.

   Map with two keys: Results containing search results and Profile containing profiling information.
   Simple error reply in these cases: no such index, syntax error in query."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-search!
  "Searches the index with a textual query and returns matching documents or just their ids.
   Only keys to which the user has read access are returned.
   If the index does not exist or the query has a syntax error, a simple error reply is returned.

   ```
   FT.SEARCH index query 
     [NOCONTENT] 
     [VERBATIM] 
     [NOSTOPWORDS] 
     [WITHSCORES] 
     [WITHPAYLOADS] 
     [WITHSORTKEYS] 
     [FILTER numeric_field min max [ FILTER numeric_field min max ...]] 
     [GEOFILTER geo_field lon lat radius m | km | mi | ft [ GEOFILTER geo_field lon lat radius m | km | mi | ft ...]] 
     [INKEYS count key [key ...]] 
     [INFIELDS count field [field ...]] 
     [RETURN count identifier [AS property] [ identifier [AS property] ...]] 
     [SUMMARIZE [ FIELDS count field [field ...]] [FRAGS num] [LEN fragsize] [SEPARATOR separator]] 
     [HIGHLIGHT [ FIELDS count field [field ...]] [ TAGS open close]] 
     [SLOP slop] 
     [TIMEOUT timeout] 
     [INORDER] 
     [LANGUAGE language] 
     [EXPANDER expander] 
     [SCORER scorer] 
     [EXPLAINSCORE] 
     [PAYLOAD payload] 
     [SORTBY sortby [ ASC | DESC] [WITHCOUNT | WITHOUTCOUNT]] 
     [LIMIT offset num] 
     [PARAMS nargs name value [ name value ...]] 
     [DIALECT dialect]
   ```

   index: the name of the index to search, which must have been created with FT.CREATE.
   query: the text query to execute; if it contains more than one word, enclose it in quotes.
   NOCONTENT: return only the document ids without their content.
   VERBATIM: search query terms verbatim without stemming or query expansion.
   NOSTOPWORDS: ignore any defined stop words in full text searches; this option is deprecated.
   WITHSCORES: include the relative internal score of each document in the reply.
   WITHPAYLOADS: include optional document payloads after the id and, if set, the score.
   WITHSORTKEYS: return the sorting key value after the id, score, and/or payload;
     relevant only when used with SORTBY.
   FILTER numeric_attribute min max: limit results to documents whose numeric attribute
     falls within the given range; min and max follow ZRANGE syntax and support -inf,
     +inf, and exclusive ranges with parentheses; multiple filters for different
     attributes are supported; this option is deprecated in favor of query dialect 2 syntax.
   GEOFILTER geo_attribute lon lat radius m|km|mi|ft: limit results to documents within
     the given radius of the specified coordinates; multiple geo filters are supported;
     this option is deprecated in favor of query dialect 3 syntax.
   INKEYS num key ...: limit results to the specified set of keys; num is the count of
     keys and must be greater than zero; non-existent keys are ignored unless all keys
     are non-existent.
   INFIELDS num field ...: restrict matching to the specified document attributes;
     num is the number of attributes that follow.
   RETURN num identifier [AS property] ...: limit which attributes are returned;
     num is the count of identifiers; identifier is an attribute name or a JSON Path
     expression; AS property optionally renames the identifier in the result;
     if num is 0 it acts like NOCONTENT.
   SUMMARIZE [FIELDS num field ...] [FRAGS num] [LEN fragsize] [SEPARATOR separator]:
     return only the sections of attributes that contain matched text.
   HIGHLIGHT [FIELDS num field ...] [TAGS open close]: format occurrences of matched
     text with the specified open and close tags.
   SLOP slop: allow up to slop intermediate terms between query terms;
     defaults to no constraint.
   INORDER: require query terms to appear in the same order in the document;
     typically used with SLOP; defaults to false.
   LANGUAGE language: use the stemmer for the given language during query expansion;
     defaults to English; an unsupported language returns an error; not needed if the
     language was set at index creation.
   EXPANDER expander: use a custom query expander instead of the stemmer.
   SCORER scorer: use a built-in or user-provided scoring function.
   EXPLAINSCORE: return a textual description of how scores were calculated;
     requires WITHSCORES.
   PAYLOAD payload: pass an arbitrary binary-safe payload to custom scoring functions.
   SORTBY attribute [ASC|DESC] [WITHCOUNT|WITHOUTCOUNT]: order results by the value of
     the given attribute; the attribute should be declared SORTABLE in the index for
     low-latency access; WITHCOUNT returns an accurate total count by processing all
     results, which is less performant than the default.
   LIMIT offset num: limit results to the given zero-indexed offset and count;
     defaults to 0 10; use LIMIT 0 0 to count matches without returning documents;
     without SORTBY the result order is non-deterministic.
   TIMEOUT milliseconds: override the module timeout for this query.
   PARAMS nargs name value ...: define named parameters that can be referenced in the
     query with a dollar-sign prefix; requires DIALECT 2 or greater.
   DIALECT dialect_version: select the query dialect version; if omitted, the default
     dialect set at module load or via FT.CONFIG SET is used.

   total_results: Integer - the total number of documents that match the query,
     regardless of how many are returned in results
   results: Array of maps containing document information, limited by LIMIT
   attributes: Array of attribute names
   format: Simple string - result format
   warning: Array of warning messages
   Simple error reply in these cases: no such index, syntax error in query."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-spellcheck!
  "Performs spelling correction on a search query against a given index.
   Returns suggestions for each term that appears to be misspelled.
   If the specified index does not exist, a simple error is returned.

   ```
   FT.SPELLCHECK index query 
     [DISTANCE distance] 
     [TERMS INCLUDE | EXCLUDE dictionary [terms [terms ...]]] 
     [DIALECT dialect]
   ```

   index: the index containing the indexed terms to check against.
   query: the search query to perform spell checking on.
   TERMS: specifies an inclusion (INCLUDE) or exclusion (EXCLUDE) of a custom dictionary; multiple inclusion and exclusion TERMS can be specified.
   DISTANCE: maximum Levenshtein distance for spelling suggestions, defaults to 1, maximum 4.
   DIALECT dialect: selects the dialect under which to execute the query; if not specified, the default dialect set during module initial loading or via FT.CONFIG SET is used.

   Map with a results key containing spell check results for each term.
   Simple error reply in these cases: no such index."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-syndump!
  "Dumps the contents of a synonym group for the given index.
   Returns the synonym terms and their associated synonym group ids.
   If the index does not exist, a simple error is returned.

   ```
   FT.SYNDUMP index
   ```

   index: the name of the index whose synonym group to dump.

   Map where keys are synonym terms and values are arrays of their associated synonym groups.
   Simple error reply in these cases: no such index."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-synupdate!
  "Updates a synonym group in a RediSearch index by adding the given terms to it.
   If the synonym group does not yet exist, it is created.
   By default the command triggers a scan of all documents in the index so that existing documents are re-indexed with the new synonym mapping.
   If the index does not exist, the command returns an error.

   ```
   FT.SYNUPDATE index synonym_group_id 
     [SKIPINITIALSCAN] term [term ...]
   ```

   index: the name of the index.
   synonym_group_id: the synonym group to create or update.
   SKIPINITIALSCAN: skip the initial scan of the index, so that only documents indexed after the update are affected.

   Simple string reply: OK if executed correctly.
   Simple error reply in these cases: no such index."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-tagvals!
  "Returns the distinct set of values that have been indexed in a Tag field of a given index.
   The index must already exist and have been created with FT.CREATE.
   The field_name must refer to a field that was defined as a Tag type in the index schema.
   If the index does not exist, or if the specified field is not a Tag field, a simple error is returned.

   ```
   FT.TAGVALS index field_name
   ```

   index: the name of the full-text index, which must have been created with FT.CREATE.
   field_name: the name of a Tag field defined in the index schema.

   Set of distinct tag values as bulk strings.
   Simple error reply in these cases: no such index, not a tag field."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-list!
  "Returns a list of all existing indexes.
   The underscore prefix in the command name indicates that this is a temporary command.

   ```
   FT._LIST
   ```

   Set of index names as simple strings."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

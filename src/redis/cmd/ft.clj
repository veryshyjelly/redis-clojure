(ns redis.cmd.ft)

(defn ft-sugadd!
  "Adds a suggestion string to an auto-complete suggestion dictionary.
   The suggestion dictionary is disconnected from the index definitions, and creating and updating suggestion dictionaries is left to the user.
   If the suggestion already exists, its score is replaced by the new value unless the INCR option is used.
   The INCR option increments the existing entry by the given score instead of replacing it, which is useful for updating the dictionary based on user queries in real time.
   The PAYLOAD option saves an extra payload with the suggestion, which can be fetched by adding the WITHPAYLOADS argument to FT.SUGGET.

   ```
   FT.SUGADD key string score [INCR] [PAYLOAD payload]
   ```

   key: the suggestion dictionary key.
   string: the suggestion string to index.
   score: the floating point number representing the suggestion string weight.
   INCR: increments the existing entry by the given score instead of replacing it.
   PAYLOAD payload: saves an extra payload with the suggestion, fetchable via the WITHPAYLOADS argument to FT.SUGGET.

   Integer reply: number of elements added to the suggestion dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-sugdel!
  "Deletes a string from a suggestion index.
   If the string is not found in the index, the command returns 0 without raising an error.

   ```
   FT.SUGDEL key string
   ```

   key: the suggestion dictionary key.
   string: the suggestion string to delete.

   Integer reply: 1 if the suggestion was deleted, 0 if it was not found."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-sugget!
  "Retrieves completion suggestions for a given prefix from a suggestion dictionary.
   The key must reference an existing suggestion dictionary.
   The FUZZY option enables a fuzzy prefix search, matching prefixes at Levenshtein distance of 1 from the given prefix.
   The MAX option limits the number of results returned, defaulting to 5.
   The WITHSCORES option appends a score after each suggestion, useful for merging results across multiple instances.
   The WITHPAYLOADS option includes any payload saved with each suggestion; if a suggestion has no payload, a null is returned for that entry.

   ```
   FT.SUGGET key prefix 
     [FUZZY] 
     [WITHSCORES] 
     [WITHPAYLOADS] 
     [MAX max]
   ```

   key: the suggestion dictionary key.
   prefix: the prefix to complete on.
   FUZZY: performs a fuzzy prefix search, including prefixes at Levenshtein distance of 1 from the prefix sent.
   MAX num: limits the results to a maximum of num, defaults to 5.
   WITHSCORES: also returns the score of each suggestion, useful for merging results from multiple instances.
   WITHPAYLOADS: returns optional payloads saved along with the suggestions, returns a null reply if no payload is present for an entry.

   Array of the top suggestions matching the prefix, optionally with a score after each entry."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn ft-suglen!
  "Retrieves the number of entries stored in an auto-complete suggestion dictionary.
   The key must reference an existing suggestion dictionary; if it does not exist, the command returns an error.

   ```
   FT.SUGLEN key
   ```

   key: the suggestion dictionary key.

   Integer reply: number of suggestions in the dictionary."
  [_state _msg]
  (throw (Exception. "Not Implemented")))
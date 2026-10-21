(ns redis.cmd.geo)

(defn geoadd!
  "Adds geospatial items (longitude, latitude, member) to the specified key.
   The data is stored as a sorted set, enabling queries via GEOSEARCH.
   Coordinates are given in the standard x,y format, so longitude must come before latitude.
   There are limits to the coordinates that can be indexed; areas very near the poles are not indexable.
   The command reports an error when coordinates fall outside the allowed ranges.
   There is no dedicated delete command for geo entries; use ZREM to remove elements since the underlying structure is a sorted set.
   NX and XX are mutually exclusive options.

   ```
   GEOADD key [NX | XX] [CH] longitude latitude member [longitude
     latitude member ...]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   longitude latitude member [longitude latitude member ...]: one or more longitude-latitude-member triples to add to the index.
   NX: do not update already existing elements, always add new elements.
   XX: only update elements that already exist, never add new elements.
   CH: change the return value from the number of new elements added to the total number of elements changed (added or updated).

   Integer reply: when used without optional arguments, the number of elements added to the sorted set (excluding score updates). If the CH option is specified, the number of elements that were changed (added or updated)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn geodist!
  "Returns the distance between two members stored in a geospatial index represented by a sorted set.
   The sorted set must have been populated using the GEOADD command.
   If one or both of the specified members are missing from the set, the command returns NULL.
   The distance is computed assuming the Earth is a perfect sphere, so errors up to 0.5% are possible in edge cases.

   ```
   GEODIST key member1 member2 [M | KM | FT | MI]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   member1: the first member.
   member2: the second member.
   M | KM | FT | MI: the unit for the returned distance, meters (M, the default), kilometers (KM), feet (FT), or miles (MI).

   Null reply: one or both of the elements are missing.
   Bulk string reply: distance as a double (represented as a string) in the specified units."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn geohash!
  "Returns standard Geohash strings representing the position of one or more members in a geospatial index stored as a sorted set.
   The geospatial index must have been created with GEOADD.
   Redis internally encodes positions as 52-bit integers using a non-standard Geohash variant, but this command converts them to the standard Geohash string format.

   ```
   GEOHASH key [member [member ...]]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   member [member ...]: one or more members whose Geohash strings to return.

   Array reply: an array where each element is the Geohash corresponding to each member name passed as an argument to the command."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn geopos!
  "Returns the longitude and latitude of the specified members of a geospatial index
   stored as a sorted set under key.
   The coordinates are stored internally as a 52-bit geohash, so the values returned
   may differ slightly from the exact coordinates originally supplied via GEOADD.
   The command always returns an array of positions, even when only a single member
   is requested.
   Non-existing members are reported as null elements in the returned array.

   ```
   GEOPOS key [member [member ...]]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   member [member ...]: one or more members whose longitude and latitude to return.

   Array reply: an array where each element is a two elements array representing
   longitude and latitude (x,y) of each member name passed as argument to the command.
   Non-existing elements are reported as Null reply elements of the array."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn georadius!
  "Returns the members of a sorted set populated with geospatial information (via GEOADD)
   that fall within a circle defined by a center point and a radius.
   The radius must be specified together with a unit: meters, kilometers, feet, or miles.
   By default the command returns unsorted member names.
   The COUNT option limits the number of results; when combined with ANY the command
   stops as soon as enough matches are found, which is faster but may return items
   that are not the closest to the center.
   Without ANY the server scans all matching items and sorts them, so querying a large
   area with a small COUNT can be slow.
   ASC and DESC sort the results by distance from the center, nearest first or farthest first.
   WITHCOORD, WITHDIST, and WITHHASH add the coordinates, distance, or raw geohash score
   to each result.
   STORE and STOREDIST write the results to another key instead of returning them to the
   client; they are mutually exclusive.

   ```
   GEORADIUS key longitude latitude radius <M | KM | FT | MI>
     [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC | DESC]
     [STORE key | STOREDIST key]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   longitude: the longitude of the center point.
   latitude: the latitude of the center point.
   radius: the radius of the search circle, in the unit given by the following argument.
   M | KM | FT | MI: the unit for radius: meters (M), kilometers (KM), feet (FT), or miles (MI).
   WITHCOORD: also return the longitude and latitude of each matching item.
   WITHDIST: also return the distance of each matching item from the center, in the same unit as the radius.
   WITHHASH: also return the raw 52-bit geohash-encoded score of each matching item.
   COUNT count [ANY]: return at most count matches; with ANY the command returns as soon as enough matches are found, which is faster but the results may be unsorted.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).
   STORE key: store the results as a geospatial index in key instead of returning them; mutually exclusive with STOREDIST.
   STOREDIST key: store the results in key as a sorted set of distances from the center, instead of returning them.

   If no WITH* option is specified, an Array reply of matched member names.
   If WITHCOORD, WITHDIST, or WITHHASH options are specified, the command returns an Array reply of arrays, where each sub-array represents a single item:
   The distance from the center as a floating point number, in the same unit specified in the radius.
   The Geohash integer.
   The coordinates as a two items x,y array (longitude,latitude)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn georadiusbymember!
  "Searches for geospatial items within a given radius of a specified member in a geospatial index.
   The center of the search is determined by the position of the member, rather than by explicit longitude and latitude coordinates.
   The key must hold a sorted set representing the geospatial index.
   The radius must be followed by a unit: M (meters), KM (kilometers), FT (feet), or MI (miles).
   Optional flags can augment the output with coordinates, distances, or raw geohash values, limit the number of results, control sort order, or store the results in another key.
   STORE and STOREDIST are mutually exclusive.
   When COUNT is used with ANY, the command returns as soon as enough matches are found, which is faster but the results may be unsorted.

   ```
   GEORADIUSBYMEMBER key member radius <M | KM | FT | MI> [WITHCOORD]
     [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC | DESC] [STORE key
     | STOREDIST key]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   member: the member whose position is used as the center of the search.
   radius: the radius of the search circle, in the unit given by the following argument.
   M | KM | FT | MI: the unit for radius: meters (M), kilometers (KM), feet (FT), or miles (MI).
   WITHCOORD: also return the longitude and latitude of each matching item.
   WITHDIST: also return the distance of each matching item from the center, in the same unit as the radius.
   WITHHASH: also return the raw 52-bit geohash-encoded score of each matching item.
   COUNT count [ANY]: return at most count matches; with ANY, the command returns as soon as enough matches are found, faster but the results may be unsorted.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).
   STORE key: store the results as a geospatial index in key instead of returning them; mutually exclusive with STOREDIST.
   STOREDIST key: store the results in key as a sorted set of distances from the center, instead of returning them.

   If no WITH* option is specified, an Array reply of matched member names
   If WITHCOORD, WITHDIST, or WITHHASH options are specified, the command returns an Array reply of arrays, where each sub-array represents a single item:
   The distance from the center as a floating point number, in the same unit specified in the radius.
   The Geohash integer.
   The coordinates as a two items x,y array (longitude,latitude)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn georadiusbymember-ro!
  "Read-only variant of the GEORADIUSBYMEMBER command.
   It searches for geospatial items within a given radius of a specified member stored in a sorted set.
   This command is identical to GEORADIUSBYMEMBER except that it does not support the STORE and STOREDIST parameters.

   ```
   GEORADIUSBYMEMBER_RO key member radius <M | KM | FT | MI>
     [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC | DESC]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   member: the member whose position is used as the center of the search.
   radius: the radius of the search circle, in the unit given by the following argument.
   M | KM | FT | MI: the unit for radius: meters (M), kilometers (KM), feet (FT), or miles (MI).
   WITHCOORD: also return the longitude and latitude of each matching item.
   WITHDIST: also return the distance of each matching item from the center, in the same unit as the radius.
   WITHHASH: also return the raw 52-bit geohash-encoded score of each matching item.
   COUNT count [ANY]: return at most count matches; with ANY, the command returns as soon as enough matches are found, which is faster but the results may be unsorted.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).

   Array reply of matched member names, if no WITH* option is specified.
   Array reply of arrays, where each sub-array represents a single item, if WITHCOORD, WITHDIST, or WITHHASH options are specified.
   The distance from the center as a floating point number, in the same unit specified in the radius.
   The Geohash integer.
   The coordinates as a two items x,y array (longitude,latitude)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn georadius-ro!
  "GEORADIUS_RO is the read-only variant of GEORADIUS.
   It searches a geospatial index stored as a sorted set for members within a given radius of a center point.
   The command is identical to GEORADIUS except that it does not support the STORE and STOREDIST parameters.

   ```
   GEORADIUS_RO key longitude latitude radius <M | KM | FT | MI>
     [WITHCOORD] [WITHDIST] [WITHHASH] [COUNT count [ANY]] [ASC | DESC]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   longitude: the longitude of the center point.
   latitude: the latitude of the center point.
   radius: the radius of the search circle, in the unit given by the following argument.
   M | KM | FT | MI: the unit for radius: meters (M), kilometers (KM), feet (FT), or miles (MI).
   WITHCOORD: also return the longitude and latitude of each matching item.
   WITHDIST: also return the distance of each matching item from the center, in the same unit as the radius.
   WITHHASH: also return the raw 52-bit geohash-encoded score of each matching item.
   COUNT count [ANY]: return at most count matches; with ANY, the command returns as soon as enough matches are found, which is faster but the results may be unsorted.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).

   Array reply of matched member names, if no WITH* option is specified.
   Array reply of arrays, where each sub-array represents a single item, if WITHCOORD, WITHDIST, or WITHHASH options are specified.
   The distance from the center as a floating point number, in the same unit specified in the radius.
   The Geohash integer.
   The coordinates as a two items x,y array (longitude,latitude)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn geosearch!
  "Returns the members of a sorted set populated with geospatial information (created via GEOADD) that fall within a specified search area.
   The search area can be a circle defined by a radius or an axis-aligned rectangle defined by a width and height.
   The center of the search is either the position of an existing member or a pair of longitude and latitude coordinates.
   Results can be sorted by distance from the center, limited in count, and augmented with coordinates, distances, or raw geohash scores.

   ```
   GEOSEARCH key <FROMMEMBER member | FROMLONLAT longitude latitude>
     <BYRADIUS radius <M | KM | FT | MI> | BYBOX width height <M | KM |
     FT | MI>> [ASC | DESC] [COUNT count [ANY]] [WITHCOORD] [WITHDIST]
     [WITHHASH]
   ```

   key: the name of the key that holds the geospatial index (a sorted set).
   FROMMEMBER member: use the position of the existing member as the center of the search, mutually exclusive with FROMLONLAT.
   FROMLONLAT longitude latitude: use the given coordinates as the center of the search, mutually exclusive with FROMMEMBER.
   BYRADIUS radius M | KM | FT | MI: search within a circle of the given radius in the specified unit, mutually exclusive with BYBOX.
   BYBOX width height M | KM | FT | MI: search within an axis-aligned box of the given width and height in the specified unit, mutually exclusive with BYRADIUS.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).
   COUNT count [ANY]: return at most count matches; with ANY the command returns as soon as enough matches are found (faster but results may be unsorted), without ANY the effort is proportional to the number of matching items and they are sorted.
   WITHCOORD: also return the longitude and latitude of each matching item.
   WITHDIST: also return the distance of each matching item from the center, in the same unit as the radius.
   WITHHASH: also return the raw 52-bit geohash-encoded score of each matching item.

   If no WITH* option is specified, an Array reply of matched member names.
   If WITHCOORD, WITHDIST, or WITHHASH options are specified, the command returns an Array reply of arrays, where each sub-array represents a single item:
   The distance from the center as a floating point number, in the same unit specified in the radius.
   The Geohash integer.
   The coordinates as a two items x,y array (longitude,latitude)."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn geosearchstore!
  "GEOSEARCHSTORE performs a geospatial search on a sorted set and stores the matching members in a destination sorted set.
   It is similar to GEOSEARCH, except the results are written to the destination key rather than returned directly.
   By default the destination sorted set is populated with the geohash-encoded position of each member as its score.
   When the STOREDIST option is used, the score is instead the distance from the center of the search shape, expressed as a floating-point number in the same unit specified for that shape.
   In clustered Redis environments the behavior of this command varies; see the multi-key operations documentation for details.

   ```
   GEOSEARCHSTORE destination source <FROMMEMBER member |
     FROMLONLAT longitude latitude> <BYRADIUS radius <M | KM | FT | MI>
     | BYBOX width height <M | KM | FT | MI>> [ASC | DESC] [COUNT count
     [ANY]] [STOREDIST]
   ```

   destination: the key to store the results in.
   source: the geospatial index key to search.
   FROMMEMBER member: use the position of the existing member as the center of the search, mutually exclusive with FROMLONLAT.
   FROMLONLAT longitude latitude: use the given coordinates as the center of the search, mutually exclusive with FROMMEMBER.
   BYRADIUS radius M | KM | FT | MI: search within a circle of the given radius in the specified unit, mutually exclusive with BYBOX.
   BYBOX width height M | KM | FT | MI: search within an axis-aligned box of the given width and height in the specified unit, mutually exclusive with BYRADIUS.
   ASC | DESC: sort the results by distance from the center, nearest first (ASC) or farthest first (DESC).
   COUNT count [ANY]: return at most count matches; with ANY the command returns as soon as enough matches are found, which is faster but the results may be unsorted.
   STOREDIST: store the distance from the center as the sorted-set score instead of the geohash-encoded position.

   Integer reply: the number of elements in the resulting set"
  [_state _msg]
  (throw (Exception. "Not Implemented")))

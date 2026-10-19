(ns redis.cmd.bitmap)

(defn bitcount!
  "Counts the number of set bits (population counting) in the string stored at key.
   By default all bytes in the string are examined.
   An optional range can be specified with start and end to count only within that interval (inclusive).
   Negative values for start and end index from the end of the string, where -1 is the last element.
   Non-existent keys are treated as empty strings, so the command returns zero.
   By default start and end are byte offsets; the BIT option interprets them as bit offsets instead, where 0 is the first bit and -1 is the last bit.

   ```
   BITCOUNT key [start end [BYTE | BIT]]
   ```

   key: the name of the key that holds the string.
   start end: count set bits only within the range from start to end (inclusive), negative values count from the end of the string.
   BYTE | BIT: interpret start and end as byte offsets (BYTE, the default) or bit offsets (BIT).

   Integer reply: the number of bits set to 1."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bitfield!
  "Treats a Redis string as an array of bits, allowing you to address specific integer fields of varying bit widths at arbitrary non-aligned bit offsets.
   You can set, get, increment, or decrement these fields, with configurable overflow and underflow behavior.
   The command accepts a list of sub-operations applied in order and returns an array of replies, one per sub-operation.
   If the key does not exist, it is created as an empty string before the operations are applied.
   If the key holds a value that is not a string, an error is returned.

   ```
   BITFIELD key [GET encoding offset | [OVERFLOW <WRAP | SAT | FAIL>]
     <SET encoding offset value | INCRBY encoding offset increment>
     [GET encoding offset | [OVERFLOW <WRAP | SAT | FAIL>]
     <SET encoding offset value | INCRBY encoding offset increment>
     ...]]
   ```

   key: the name of the key that holds the string.
   GET encoding offset: return the value at offset, interpreted with encoding.
   SET encoding offset value: set the value at offset to value and return its previous value.
   INCRBY encoding offset increment: increment the value at offset by increment and return the new value.
   OVERFLOW WRAP | SAT | FAIL: set the overflow behavior for the following SET and INCRBY operations; WRAP is the default, SAT saturates to the min or max value, FAIL returns nil on overflow.

   Array reply: each entry being the corresponding result of the sub-command given at the same position.
   Null reply: if OVERFLOW FAIL was given and overflows or underflows are detected."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bitfield-ro!
  "BITFIELD_RO is a read-only variant of the BITFIELD command.
   It only accepts GET subcommands and can safely be used on read-only replicas.
   Because the original BITFIELD includes SET and INCRBY options, it is flagged as a writing command,
   which causes read-only replicas in a Redis Cluster to redirect it to the master.
   BITFIELD_RO avoids this issue by restricting itself to read-only operations.

   ```
   BITFIELD_RO key [GET encoding offset [GET encoding offset ...]]
   ```

   key: the name of the key that holds the string.
   GET encoding offset: return the value at offset, interpreted with encoding
   (for example u8 or i16), may be given multiple times.

   Array reply: each entry being the corresponding result of the sub-command
   given at the same position."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bitop!
  "Performs a bitwise operation between multiple string keys and stores the result in the destination key.
   The supported operations are AND, OR, XOR, NOT, DIFF, DIFF1, ANDOR, and ONE.
   AND sets a bit in the destination only if it is set in all source bitmaps.
   OR sets a bit if it is set in at least one source bitmap.
   XOR sets a bit if its value differs between the source bitmaps, typically used with two sources.
   NOT is a unary operator that inverts each bit of a single source bitmap.
   DIFF sets a bit if it is set in the first source key but not in any of the remaining source keys.
   DIFF1 sets a bit if it is set in one or more of the remaining source keys but not in the first source key.
   ANDOR sets a bit if it is set in the first source key and also in one or more of the remaining source keys.
   ONE sets a bit if it is set in exactly one of the source keys.
   The result is always written to the destination key, and its size equals the size of the longest input string.

   ```
   BITOP <AND | OR | XOR | NOT | DIFF | DIFF1 | ANDOR | ONE> destkey key [key ...]
   ```

   operation: the bitwise operation to perform, one of AND, OR, XOR, NOT, DIFF, DIFF1, ANDOR, or ONE; NOT accepts exactly one source key.
   destkey: the key to store the result in.
   key [key ...]: one or more source keys.

   Integer reply: the size of the string stored in the destination key is equal to the size of the longest input string."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn bitpos!
  "BITPOS returns the position of the first bit set to 1 or 0 in a string value.
   The position is counted from left to right, where the most significant bit of the first byte is position 0, the most significant bit of the second byte is position 8, and so on.
   This is the same convention used by GETBIT and SETBIT.
   By default all bytes in the string are examined, but a range can be limited with the start and end arguments.
   By default the range is interpreted as byte offsets; the optional BIT modifier makes it a bit-offset range instead.
   Bit positions are always returned as absolute values starting from zero, even when a range is specified.
   Negative start and end values index from the end of the string, where -1 is the last byte, or the last bit when BIT is used.
   If the key does not exist it is treated as an infinite array of zero bits: searching for 0 returns 0, searching for 1 returns -1.
   An existing empty string contains no bits at all, so searching for either 0 or 1 returns -1.
   When searching for clear bits (0) and the string consists entirely of set bits, the result is the first bit position beyond the string, as if the right side were padded with zeros, unless both start and end are specified, in which case -1 is returned if no clear bit is found within that range.

   ```
   BITPOS key bit [start [end [BYTE | BIT]]]
   ```

   key: the name of the key that holds the string.
   bit: the bit value to search for, 0 or 1.
   start: the offset to start searching from, by default a byte offset, negative values count from the end of the string.
   end: the offset to stop searching at, inclusive.
   BYTE | BIT: interpret start and end as byte offsets (BYTE, the default) or bit offsets (BIT).

   Integer reply: the position of the first bit set to 1 or 0 according to the request.
   Integer reply: -1, if the bit argument is 1 and the string is empty or composed of just zero bytes."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn getbit!
  "Returns the bit value at the given offset in the string value stored at key.
   If the offset is beyond the string length, the string is treated as a contiguous space of zero bits.
   If the key does not exist, it is treated as an empty string, so the offset is always out of range and the result is zero.

   ```
   GETBIT key offset
   ```

   key: the name of the key that holds the string.
   offset: the zero-based bit offset to read.

   Integer reply: 0.
   Integer reply: 1."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

(defn setbit!
  "Sets or clears the bit at offset in the string value stored at key.
   The bit is set to 1 or cleared to 0 depending on the value argument.
   If key does not exist, a new string value is created and grown to hold the bit at offset.
   The offset must be greater than or equal to 0 and smaller than 2^32, which limits bitmaps to 512 MB.
   When the string is grown, the newly added bits are set to 0.
   Setting the last possible bit (offset equal to 2^32 - 1) on a key that does not yet hold a string, or holds a small string, requires allocating all intermediate memory and can block the server for some time.
   Once that initial allocation is done, subsequent SETBIT calls for the same key do not carry the allocation overhead.

   ```
   SETBIT key offset value
   ```

   key: the name of the key that holds the string.
   offset: the zero-based bit offset to set, the string is zero-padded if the offset is beyond its current length.
   value: the bit value to set, 0 or 1.

   Integer reply: the original bit value stored at offset."
  [_state _msg]
  (throw (Exception. "Not Implemented")))

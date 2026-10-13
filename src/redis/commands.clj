(ns redis.commands)

(defn ping
  "
This command returns the bulk string PONG if no argument is provided, otherwise it returns a copy of the provided message as a bulk string.
This command is useful for:

- Testing whether a connection is still alive.
- Verifying the server's ability to serve data - an error is returned when this isn't the case (for example, during load from persistence data or accessing a stale replica).
- Measuring latency.

If the client is subscribed to a channel or a pattern, it will instead return a multi-bulk string with PONG in the first position and an empty bulk string in the second position, unless an argument is provided, in which case it returns a copy of the argument.
"
  [& msg])
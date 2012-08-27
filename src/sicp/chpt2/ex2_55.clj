(ns sicp.chpt2.ex2-55)

;;; `'` expands to `(quote ?)` where `?` is whatever immediately follows `'`

;;; `'x` is equivalent to `(quote x)`

;;; `''abracadabra` expands to `(quote (quote abracadabra))` and resolves to `(quote abracadabra)`

;;     `(first ''abracadabra)`
;;     => `quote`

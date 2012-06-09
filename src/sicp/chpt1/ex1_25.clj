(ns sicp.chpt1.ex1-25)


;; Alyssa's code would work for smaller numbers but  even \\(10^{100}\\) would overflow in most languages and would
;; need slower, arbitrary precision arithmetic in Clojure.

;; The `expmod` function works with primitive arithmetic operations, even on very large numbers.

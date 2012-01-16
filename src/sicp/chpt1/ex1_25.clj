(ns sicp.chpt1.ex1-25)


;; Alyssa's code could work for smaller numbers but raising even 10 to 100 would overflow in most languages and would
;; need slower, arbitrary precision arithmetic in Clojure.

;; The original expmod algorithm works with primitive arithmetic operations, even on very large numbers.

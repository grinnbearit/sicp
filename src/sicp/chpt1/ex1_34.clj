(ns sicp.chpt1.ex1-34)

;; Evaluating `(f f)`, we'd get an error since 2 is not a function

(defn f
  [g]
  (g 2))


;;     (f f)
;;     |
;;     (f 2)
;;     |
;;     (2 2)
;;     => java.lang.Long cannot be cast to clojure.lang.IFn

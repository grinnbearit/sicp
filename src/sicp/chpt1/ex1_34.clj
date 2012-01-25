(ns sicp.chpt1.ex1-34)

;; We'd get an error since 2 is not a function

(defn f
  [g]
  (g 2))


;; (f f)
;; => java.lang.Long cannot be cast to clojure.lang.IFn

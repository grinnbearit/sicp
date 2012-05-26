(ns sicp.chpt1.ex1-06)


;;; `(sqrt-iter (improve guess x) x)`
;;; is evaluated whether `(good-enough? guess x)` was true or not,
;;; this would result in an infinite call to `sqrt-iter`

;;; This is only in the case of applicative order evaluation

(ns sicp.chpt2.ex2-26)


(def x (list 1 2 3))
(def y (list 4 5 6))


;;; `concat` is equivalent to `append`

;;;     (concat x y)
;;;     => (1 2 3 4 5 6

;;;     (cons x y)
;;;     => ((1 2 3) 4 5 6)

;;;     (list x y)
;;;     => ((1 2 3) (4 5 6))

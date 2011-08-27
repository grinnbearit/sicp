(ns sicp.chpt1.ex1-4)


(defn a-plus-abs-b
  [a b]
  ((if (> b 0) + -) a b))


;;; b > 0
;;; (+ a |b|)
;;; (+ a b)
(+ a b)

;;; b <= 0
;;; (+ a |b|)
;;; (+ a (- b))
;;; (- a b)
(- a b)
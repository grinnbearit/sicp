(ns sicp.chpt2.ex2-04)


(defn cons
  [x y]
  (fn [m] (m x y)))


(defn car
  [z]
  (z (fn [x y] x)))


(defn cdr
  [z]
  (z (fn [x y] y)))


;;; (cons 2 3)
;;; (car (cons 2 3))
;;; => 2

;;; (cdr (cons 2 3))
;;; => 3

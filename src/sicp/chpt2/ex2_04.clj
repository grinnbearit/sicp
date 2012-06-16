(ns sicp.chpt2.ex2-04
  (:refer-clojure :exclude [cons]))


(defn cons
  [x y]
  (fn [m] (m x y)))


(defn car
  [z]
  (z (fn [x y] x)))


;;; The corresponding definition of cdr


(defn cdr
  [z]
  (z (fn [x y] y)))


;;;     (car (cons 2 3))
;;;     => 2

;;;     (cdr (cons 2 3))
;;;     => 3


;;; Using the substitution model

;;;     (car (cons 2 3))
;;;     # (car (fn [m] (m 2 3)))
;;;     # ((fn [m] (m 2 3)) (fn [x y] x))
;;;     # ((fn [x y] x) 2 3)
;;;     => 2


;;;     (cdr (cons 2 3))
;;;     # (cdr (fn [m] (m 2 3)))
;;;     # ((fn [m] (m 2 3)) (fn [x y] y))
;;;     # ((fn [x y] y) 2 3)
;;;     => 3

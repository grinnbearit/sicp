(ns sicp.chpt2.ex2-05
  (:refer-clojure :exclude [cons]))


;;; Since both 2 and 3 are prime, \\(2^{a}3^{b}\\) is unique to the pair (a, b)


(defn cons
  [x y]
  (int (* (Math/pow 2 x)
          (Math/pow 3 y))))


;;; `d-component` computes the largest integer value of x which satisfies \\(d^x \leq n\\)

(defn- d-component
  [n d]
  (loop [x n acc 0]
    (if (zero? (rem x d))
      (recur (/ x d) (inc acc))
      acc)))


(defn car
  [z]
  (d-component z 2))


(defn cdr
  [z]
  (d-component z 3))


;;;     (car (cons 2 3))
;;;     => 2

;;;     (cdr (cons 2 3))
;;;     => 3


;;; Using the substitution model

;;;     (car (cons 2 3))
;;;     # (car 108)
;;;     => 2


;;;     (cdr (cons 2 3))
;;;     # (cdr 108)
;;;     => 3

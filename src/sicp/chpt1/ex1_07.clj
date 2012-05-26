(ns sicp.chpt1.ex1-07)


;;; `good-enough?` is set to restrict the number of steps in evaluating an answer


;;; Using a delta (even a small one) to check `good-enough?` works ok for numbers in
;;; a small range but very large numbers take a long time to converge (with much higher precision)
;;; while very small numbers take a small time to converge (with loss of precision).

;;; The suggested implementation of `good-enough?` is much better at limiting to an equal precision
;;; for both very large and very small numbers


(defn sqr
  [x]
  (* x x))


(defn abs
  [x]
  (if (> x 0) x (- x)))


(defn good-enough?
  [guess x]
  (< (abs (- (sqr guess) x))
     1/1000))


(defn improve
  [guess x]
  (/ (+ (/ x guess)
        guess)
     2))


(defn sqrt-iter
  [guess x]
  (if (good-enough? guess x)
    guess
    (recur (improve guess x) x)))


(defn sqrt
  [x]
  (sqrt-iter 1.0 x))


(defn good-enough2?
  [last-guess guess]
  (< (/ (abs (- guess last-guess))
        last-guess)
     1/10000000))


(defn sqrt-iter2
  [guess x]
  (if (good-enough2? guess (improve guess x))
    guess
    (recur (improve guess x) x)))


(defn sqrt2
  [x]
  (sqrt-iter2 1.0 x))

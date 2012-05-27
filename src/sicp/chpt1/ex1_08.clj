(ns sicp.chpt1.ex1-08)


;;; Almost exactly like the sqrt implementation, the major change being in the implementation of `improve`


(defn sqr
  [x]
  (* x x))


(defn abs
  [x]
  (if (> x 0) x (- x)))


(defn good-enough?
  [last-guess guess]
  (< (/ (abs (- guess last-guess))
        last-guess)
     1/10000000))


(defn improve
  [guess x]
  (/ (+ (/ x (sqr guess))
        (* 2 guess))
     3))


(defn cbrt-iter
  [guess x]
  (if (good-enough? guess (improve guess x))
    guess
    (recur (improve guess x) x)))


(defn cbrt
  [x]
  (cbrt-iter 1.0 x))

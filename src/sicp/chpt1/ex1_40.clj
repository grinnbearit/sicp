(ns sicp.chpt1.ex1-40
  (:use [sicp.chpt1.ex1-35 :only [fixed-point]]))


(def dx 1/100000)


(defn deriv
  [g]
  (fn [x]
    (/ (- (g (+ x dx)) (g x))
       dx)))


(defn newton-transform
  [g]
  (fn [x]
    (- x (/ (g x) ((deriv g) x)))))


(defn newton-method
  [g guess]
  (fixed-point (newton-transform g) guess))


(defn cubic
  [a b c]
  (fn [x]
    (+ (* x x x)
       (* a x x)
       (* b x)
       c)))



;;;     ((cubic 1 2 3) 1)
;;;     => 7

;;;     (float (newton-method (cubic 1 1 1) 1))
;;;     => -1.0

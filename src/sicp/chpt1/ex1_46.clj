(ns sicp.chpt1.ex1-46)


(defn iterative-improve
  [good-enough? improve-guess]
  (fn [guess]
    (if (good-enough? guess)
      guess
      (recur (improve-guess guess)))))


(def tolerance 1/10000)


(defn sqrt
  [x]
  (letfn [(good-enough?
            [guess]
            (< (Math/abs (- (* guess guess) x)) tolerance))

          (improve-guess
            [guess]
            (/ (+ (/ x guess) guess) 2))]

    ((iterative-improve good-enough? improve-guess) 1.0)))


(defn fixed-point
  [f]
  (letfn [(good-enough?
            [guess]
            (< (Math/abs (- (f guess) guess)) tolerance))

          (improve-guess
            [guess]
            (f guess))]

    (iterative-improve good-enough? improve-guess)))


;;     (sqrt 10)
;;     => 3.162277665175675

;;     ((fixed-point (fn [x] (+ 1 (/ x)))) 1.0)
;;     => 1.6179775280898876

(ns sicp.chpt1.ex1-40)


(defn abs
  "Because Math/abs does not work for rationals"
  [x]
  (if (< x 0)
    (- x)
    x))


(def tolerance 1/100000)


(defn fixed-point
  [f first-guess]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))

          (try-it [guess i]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next (inc i)))))]

    (try-it first-guess 0)))



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


;;; (newton-method (cubic 1 1 1) 1) => -1.0

(ns sicp.chpt1.ex1-35)


;; \\(x \rightarrow 1 + \frac{1}{x}\\)

;; is a transformation whose fixed point is the solution to the equation

;; \\(x = 1 + \frac{1}{x}\\)

;; \\(\Rightarrow x = \frac{x + 1}{x}\\)

;; \\(\Rightarrow x^2 = x + 1\\)

;; which is also the definition for the golden ratio, \\(\phi\\)


(defn abs
  [x]
  (if (< x 0)
    (- x)
    x))


(def tolerance 1/100000)

(defn fixed-point
  [f first-guess]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))

          (try-it [guess]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next))))]

    (try-it first-guess)))


(def phi
  (fixed-point (fn [x] (+ 1 (/ x))) 1))


;;     (float phi)
;;     => 1.6180328

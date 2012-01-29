(ns sicp.chpt1.ex1-36)


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
            (println guess i)
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next (inc i)))))]

    (try-it first-guess 0)))


;;; solutions to x^x = 1000
;;; x -> log(1000)/log(x)

(defn without-damping
  []
  (fixed-point (fn [x]
                 (/ (Math/log 1000)
                    (Math/log x)))
               10))

;;; without damping 32 steps


;;; 2x -> x + log(1000)/log(x)
;;; x -> (xlog(x) + log(1000))/2log(x)

(defn with-damping
  []
  (fixed-point (fn [x]
                 (let [logx (Math/log x)]
                   (/ (+ (* x logx) (Math/log 1000))
                      (* 2 logx))))
               10))

;;; with damping 9 steps

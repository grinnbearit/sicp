(ns sicp.chpt1.ex1-45)


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

          (try-it [guess]
            (let [next (f guess)]
              (if (close-enough? guess next)
                next
                (recur next))))]

    (try-it first-guess)))


(defn fixed-point-limit
  [f first-guess limit]
  (letfn [(close-enough? [v1 v2]
            (< (abs (- v1 v2)) tolerance))

          (try-it [guess i]
            (let [next (f guess)]
              (if (or (zero? i) (close-enough? guess next))
                [next (- limit i)]
                (recur next (dec i)))))]

    (try-it first-guess limit)))


(defn fixed-point-of-transform-limit
  [g transform guess limit]
  (fixed-point-limit (transform g) guess limit))


(defn fixed-point-of-transform
  [g transform guess]
  (fixed-point (transform g) guess))


(defn average-damp
  [f]
  (fn [x]
    (/ (+ (f x) x) 2)))


(defn repeated
  [f n]
  (loop [g f i n]
    (cond (= 1 i)
          g

          (even? i)
          (recur (comp g g) (/ i 2))

          :else
          (recur (comp f g) (dec i)))))


(defn fast-expt
  ([b n]
     (fast-expt b n 1))
  ([b n a]
     (cond (zero? n)
           a

           (even? n)
           (recur (* b b) (/ n 2) a)

           :else
           (recur b (dec n) (* a b)))))


(defn nth-root-fixed-point
  [y n]
  (fn [x]
    (/ y (fast-expt x (dec n)))))


;; Steps Reached [100]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 2) identity 1.0 100)

;; Steps Reached [7]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 2) average-damp 1.0 100)

;; Steps Reached [100]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 4) average-damp 1.0 100)

;; Steps Reached [12]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 4) (repeated average-damp 2) 1.0 100)

;; Steps Reached [100]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 8) (repeated average-damp 2) 1.0 100)

;; Steps Reached [37]
;; (fixed-point-of-transform-limit (nth-root-fixed-point 100 8) (repeated average-damp 3) 1.0 100)

;; Conculusion, average damping of at least log2(n) is needed to converge the fixed point for the nth root

(defn nth-root
  [x n]
  (fixed-point-of-transform (nth-root-fixed-point x n)
                            (repeated average-damp (int (/ (Math/log n)
                                                           (Math/log 2))))
                            1.0))

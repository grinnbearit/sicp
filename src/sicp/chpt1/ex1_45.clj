(ns sicp.chpt1.ex1-45
  (:use [sicp.chpt1.ex1-07 :only [abs]]
        [sicp.chpt1.ex1-16 :only [fast-expt]]
        [sicp.chpt1.ex1-35 :only [fixed-point]]
        [sicp.chpt1.ex1-43 :only [repeated]]))


(def tolerance 1/100000)


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


(defn nth-root-fixed-point
  [y n]
  (fn [x]
    (/ y (fast-expt x (dec n)))))


;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 2) identity 1.0 100)

;; Steps Reached [100]

;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 2) average-damp 1.0 100)

;; Steps Reached [7]

;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 4) average-damp 1.0 100)

;; Steps Reached [100]

;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 4)
;;       (repeated average-damp 2)
;;       1.0
;;       100)

;; Steps Reached [12]

;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 8)
;;       (repeated average-damp 2)
;;       1.0
;;       100)

;; Steps Reached [100]

;;     (fixed-point-of-transform-limit
;;       (nth-root-fixed-point 100 8)
;;       (repeated average-damp 3)
;;       1.0
;;       100)

;; Steps Reached [37]

;; In conculusion, average damping of at least \\(\log _2 n\\) is needed to converge the fixed point for the nth root


(defn nth-root
  [x n]
  (fixed-point-of-transform (nth-root-fixed-point x n)
                            (repeated average-damp (int (/ (Math/log n)
                                                           (Math/log 2))))
                            1.0))

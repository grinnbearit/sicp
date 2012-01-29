(ns sicp.chpt1.ex1-39)


(defn cont-frac
  ([n d k]
     (cont-frac n d k 0))
  ([n d k acc]
     (if (zero? k)
       acc
       (recur n d (dec k) (/ (n k)
                             (+ (d k) acc))))))


(defn tan-cf
  [x k]
  (letfn [(numerator [i]
            (if (= 1 i)
              x
              (- (* x x))))

          (denominator [i]
            (dec (* 2 i)))]

    (float (cont-frac numerator denominator k))))


;; (tan-cf (* Math/PI 0) 11)
;; => 0.0

;; (tan-cf (* Math/PI 1/4) 11)
;; => 1.0

;; (tan-cf (* Math/PI 1/2) 11)
;; => undefined

;; (tan-cf (* Math/PI 3/4) 11)
;; => -1.0

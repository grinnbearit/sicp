(ns sicp.chpt1.ex1-39
  (:use [sicp.chpt1.ex1-37 :only [cont-frac]]))


(defn tan-cf
  [x k]
  (letfn [(numerator [i]
            (if (= 1 i)
              x
              (- (* x x))))

          (denominator [i]
            (dec (* 2 i)))]

    (float (cont-frac numerator denominator k))))


;;; \\(\tan 0 = 0\\)

;;;     (tan-cf (* Math/PI 0) 11)
;;;     => 0.0

;;; \\(\tan \frac{\pi}{4} = 1\\)

;;;     (tan-cf (* Math/PI 1/4) 11)
;;;     => 1.0

;; \\(\tan \frac{\pi}{2} = \infty\\)

;;;     (tan-cf (* Math/PI 1/2) 11)
;;;     => *Divide By Zero*

;; \\(\tan \frac{3\pi}{4} = -1\\)

;;;     (tan-cf (* Math/PI 3/4) 11)
;;;     => -1.0

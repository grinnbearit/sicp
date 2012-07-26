(ns sicp.chpt2.ex2-10
  (:use [sicp.chpt2.ex2-07 :only [make-interval
                                  lower-bound
                                  upper-bound]]
        [sicp.chpt2.ex2-09 :only [mul-interval]]))


(defn div-interval
  [x y]
  (letfn [(spans-zero?
            [i]
            (and (<= (lower-bound i) 0)
                 (<= 0 (upper-bound i))))]

    (if (spans-zero? y)
      (throw (ArithmeticException. "Divide by zero"))
      (mul-interval x
                    (make-interval (/ (lower-bound y))
                                   (/ (upper-bound y)))))))


;;     (div-interval (make-interval 2 3)
;;                   (make-interval -1 1))
;;     => *Divide by zero*

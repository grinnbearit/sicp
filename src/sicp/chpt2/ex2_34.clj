(ns sicp.chpt2.ex2-34
  (:use [sicp.chpt2.ex2-33 :only [accumulate]]))


(defn horner-eval
  [x coefficient-sequence]
  (accumulate (fn [this-coeff higher-terms] (+ this-coeff (* x higher-terms)))
              0
              coefficient-sequence))


;;      (horner-eval 2 (list 1 3 0 5 0 1))
;;      => 79

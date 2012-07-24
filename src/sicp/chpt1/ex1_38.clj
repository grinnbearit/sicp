(ns sicp.chpt1.ex1-38
  (:use [sicp.chpt1.ex1-37 :only [cont-frac]]))


;;; \\(e \approx 2.71828183\\)


(defn e
  [k]
  (float (+ (cont-frac (constantly 1)
                       (fn [x]
                         (if (= (rem x 3) 2)
                           (* 2 (inc (quot x 3)))
                           1))
                       k)
            2)))

;;;     (e 9)
;;;     => 2.71828

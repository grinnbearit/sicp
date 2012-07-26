(ns sicp.chpt2.ex2-12
  (:use [sicp.chpt2.ex2-07 :only [make-interval
                                  lower-bound
                                  upper-bound]]))


(defn make-center-percent
  [c p]
  (let [width (Math/abs (* (/ p 100.0) c))]
    (make-interval (- c width)
                   (+ c width))))


(defn center
  [i]
  (/ (+ (lower-bound i)
        (upper-bound i))
     2))


(defn percent
  [i]
  (let [c (center i)]
    (Math/abs (* (/ (- (upper-bound i) c)
                    c)
                 100.0))))


(defn print-interval
  [i]
  (format "(%1.2f, %1.2f%%)"
          (double (center i))
          (double (percent i))))


;;     (center (make-center-percent 100 10))
;;     => 100

;;     (percent (make-center-percent 100 10))
;;     => 10.0

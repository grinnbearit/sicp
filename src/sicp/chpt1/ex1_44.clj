(ns sicp.chpt1.ex1-44
  (:use [sicp.chpt1.ex1-43 :only [repeated]]))


(def dx 1/100000)


(defn smooth
  [f]
  (fn [x]
    (/ (+ (f (- x dx))
          (f x)
          (f (+ x dx)))
       3)))


(defn n-fold-smooth
  [f n]
  ((repeated smooth n) f))

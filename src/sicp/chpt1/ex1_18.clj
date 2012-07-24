(ns sicp.chpt1.ex1-18
  (:refer-clojure :exclude [double])
  (:use [sicp.chpt1.ex1-17 :only [double halve]]))


;;; Iterative version of `fast-*`

;;; Keeping \\(ba+n\\) constant in every iteration


(defn fast-*
  ([a b]
     (fast-* a b 0))
  ([a b n]
     (cond (zero? b)
           n

           (even? b)
           (recur (double a) (halve b) n)

           :else
           (recur a (dec b) (+ a n)))))

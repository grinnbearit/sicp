(ns sicp.chpt1.ex1-21)


(defn divides?
  [a b]
  (zero? (rem a b)))


(defn find-smallest-divisor
  ([n]
     (find-smallest-divisor n 2))
  ([n test-divisor]
     (cond (> (* test-divisor test-divisor) n)
           n

           (divides? n test-divisor)
           test-divisor

           :else
           (recur n (inc test-divisor)))))


;;;     (find-smallest-divisor 199)
;;;     => 199
;;;
;;;     (find-smallest-divisor 1999)
;;;     => 1999
;;;
;;;     (find-smallest-divisor 19999)
;;;     => 7

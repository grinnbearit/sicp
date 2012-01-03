(ns sicp.chpt1.ex1-16)


;;; Keeping b^n * a constant in every iteration


(defn fast-expt
  ([b n]
     (fast-expt b n 1))
  ([b n a]
     (cond (zero? n)
           a

           (even? n)
           (recur (* b b) (/ n 2) a)

           :else
           (recur b (dec n) (* a b)))))
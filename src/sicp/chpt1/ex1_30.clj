(ns sicp.chpt1.ex1-30)


(defn sum
  [term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (recur (next a) (+ (term a) result))))]
    (iter a 0)))

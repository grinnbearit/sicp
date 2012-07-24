(ns sicp.chpt1.ex1-33
  (:use [sicp.chpt1.ex1-20 :only [gcd]]
        [sicp.chpt1.ex1-22 :only [prime?]]))


(defn filtered-accumulate
  [combiner null-value term a next b filter]
  (letfn [(iter [a result]
            (cond (> a b)
                  result

                  (filter a)
                  (recur (next a) (combiner (term a) result))

                  :else
                  (recur (next a) result)))]

    (iter a null-value)))


(defn sum-squares-of-primes
  [a b]
  (letfn [(square [x]
            (* x x))]

    (filtered-accumulate + 0 square a inc b prime?)))


;;     (sum-squares-of-primes 1 10)
;;     => 88


(defn prod-coprime
  [n]
  (letfn [(coprime? [x]
            (= 1 (gcd x n)))]

    (filtered-accumulate * 1 identity 2 inc (dec n) coprime?)))


;;     (prod-coprime 10)
;;     => 189

(ns sicp.chpt1.ex1-33)


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


(declare prime? gcd)


(defn sum-squares-of-primes
  [a b]
  (letfn [(square [x]
            (* x x))]

    (filtered-accumulate + 0 square a inc b prime?)))


(defn prod-coprime
  [n]
  (letfn [(coprime? [x]
            (= 1 (gcd x n)))]

    (filtered-accumulate * 1 identity 2 inc (dec n) coprime?)))


;; scaffolding


(defn prime?
  [n]
  (letfn [(divides? [a b]
            (zero? (rem a b)))

          (find-smallest-divisor
            ([n]
               (if (divides? n 2)
                 2
                 (find-smallest-divisor n 3)))
            ([n test-divisor]
               (cond (> (* test-divisor test-divisor) n)
                     n

                     (divides? n test-divisor)
                     test-divisor

                     :else
                     (recur n (inc (inc test-divisor))))))]

    (= n (find-smallest-divisor n))))


(defn gcd
  [a b]
  (if (zero? a)
    b
    (recur (rem b a) a)))

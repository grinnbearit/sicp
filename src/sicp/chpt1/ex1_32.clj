(ns sicp.chpt1.ex1-32)


(defn accumulate
  [combiner null-value term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (recur (next a) (combiner (term a) result))))]
    (iter a null-value)))


(defn sum
  [term a next b]
  (accumulate + 0 term a next b))


(defn product
  [term a next b]
  (accumulate * 1 term a next b))


(defn accumulate-rec
  [combiner null-value term a next b]
  (if (> a b)
    null-value
    (combiner (term a)
              (accumulate combiner null-value term (next a) next b))))


(defn sum-rec
  [term a next b]
  (accumulate-rec + 0 term a next b))


(defn product-rec
  [term a next b]
  (accumulate-rec * 1 term a next b))

(ns sicp.chpt2.ex2-20)


;;; recursive


(defn same-parity
  [x & xs]
  (cond (empty? xs)
        ()

        (= (rem x 2) (rem (first xs) 2))
        (conj (apply same-parity x (rest xs))
              (first xs))

        :else
        (apply same-parity x (rest xs))))


;;; iterative


(defn- same-parity-iter
  [x xs acc]
  (cond (empty? xs)
        acc

        (= (rem x 2) (rem (first xs) 2))
        (recur x (rest xs) (conj acc (first xs)))

        :else
        (recur x (rest xs) acc)))


(defn same-parity
  [x & xs]
  (same-parity-iter x xs []))


;;; other implementations


(defn same-parity
  [x & xs]
  (filter (if (even? x) even? odd?) xs))

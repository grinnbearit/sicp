(ns sicp.chpt1.ex1-31)


(defn product
  [term a next b]
  (letfn [(iter [a result]
            (if (> a b)
              result
              (recur (next a) (* (term a) result))))]
    (iter a 1)))


(defn factorial
  [n]
  (product identity 1 inc n))


;;     (factorial 10)
;;     => 3628800


(defn pi
  [n]
  (letfn [(term [i]
            (if (odd? i)
              (/ (inc i) (+ i 2))
              (/ (+ i 2) (inc i))))]

    (* 4 (product term 1 inc n))))


;;     (float (pi 1000))
;;     => 3.1431608


(defn product-rec
  [term a next b]
  (if (> a b)
    1
    (* (term a)
       (product-rec term (next a) next b))))


(defn factorial-rec
  [n]
  (product-rec identity 1 inc n))


;;     (factorial-rec 10)
;;     => 3628800

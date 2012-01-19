(ns sicp.chpt1.ex1-28)


;; With great help from http://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test


(defn square
  [x]
  (* x x))


(defn expmod
  [base exp m]
  (cond (zero? exp)
        1

        (even? exp)
        (rem (square (expmod base (/ exp 2) m)) m)

        :else
        (rem (* base (expmod base (dec exp) m)) m)))


(defn divides?
  [a b]
  (zero? (rem a b)))


(defn factor-2s
  "Returns [s d] which is n in the form (2^s)*d"
  [n]
  (loop [t 0 d n]
    (if (divides? d 2)
      (recur (inc t) (/ d 2))
      [t d])))


(defn squaremod
  [x m]
  (rem (square x) m))


(defn miller-rabin-test
  [n a]
  (let [[s d] (factor-2s (dec n))
        x (expmod a d n)]
    (if (or (= 1 x) (= (dec n) x))
      true
      (let [sqrs (take s (iterate #(squaremod % n) x))]
        (cond (some #(= 1 %) sqrs)
              false

              (some #(= (dec n) %) sqrs)
              true

              :else
              false)))))


(defn miller-rabin-prime?
  [n]
  (->> (range 3 (- n 2) 2)
       (map (partial miller-rabin-test n))
       (every? true?)))


;; Carmichael numbers

;; (miller-rabin-prime? 1009) => false (Not sure why the test fails here, 1009 is prime)
;; (miller-rabin-prime? 1011) => false
;; (miller-rabin-prime? 10007) => true
;; (miller-rabin-prime? 10011) => false
;; (miller-rabin-prime? 1000003) => true
;; (miller-rabin-prime? 1000007) => false

;; (miller-rabin-prime? 561) => true
;; (miller-rabin-prime? 1105) => true
;; (miller-rabin-prime? 1729) => true
;; (miller-rabin-prime? 2465) => true
;; (miller-rabin-prime? 2821) => true
;; (miller-rabin-prime? 6601) => true


;; None of the Carmichael numbers in the footnote fool the miller-rabin-prime test


(ns sicp.chpt1.ex1-28
  (:use [sicp.chpt1.ex1-03 :only [sqr]]
        [sicp.chpt1.ex1-21 :only [divides?]]
        [sicp.chpt1.ex1-24 :only [expmod]]))


;; With lots of help from [Miller Rabin primality test](http://en.wikipedia.org/wiki/Miller%E2%80%93Rabin_primality_test)


(defn factor-2s
  "Returns the tuple `[s d]` which is `n` in the form (2^s)*d"
  [n]
  (loop [t 0 d n]
    (if (divides? d 2)
      (recur (inc t) (/ d 2))
      [t d])))


(defn sqrmod
  [x m]
  (rem (sqr x) m))


(defn miller-rabin-test
  [n a]
  (let [[s d] (factor-2s (dec n))
        x (expmod a d n)]
    (if (or (= 1 x) (= (dec n) x))
      true
      (let [sqrs (take s (iterate #(sqrmod % n) x))]
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


;; known primes and non primes

;;     (miller-rabin-prime? 1009)
;;     => false (Not sure why the test fails here, 1009 is prime)

;;     (miller-rabin-prime? 1011)
;;     => false

;;     (miller-rabin-prime? 10007)
;;     => true

;;     (miller-rabin-prime? 10011)
;;     => false

;;     (miller-rabin-prime? 1000003)
;;     => true

;;     (miller-rabin-prime? 1000007)
;;     => false

;; Carmichael numbers

;;     (miller-rabin-prime? 561)
;;     => false

;;     (miller-rabin-prime? 1105)
;;     => false

;;     (miller-rabin-prime? 1729)
;;     => false

;;     (miller-rabin-prime? 2465)
;;     => false

;;     (miller-rabin-prime? 2821)
;;     => false

;;     (miller-rabin-prime? 6601)
;;     => false


;; None of the Carmichael numbers in the footnote fool the miller-rabin-prime test

(ns sicp.chpt1.ex1-27)


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


(defn fermat-test
  [n a]
  (= (expmod a n n) a))


(defn fermat-prime?
  [n]
  (->> (range 2 n)
       (map (partial fermat-test n))
       (every? true?)))


(defn divides?
  [a b]
  (zero? (rem a b)))


(defn find-smallest-divisor
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
           (recur n (inc (inc test-divisor))))))


(defn prime?
  [n]
  (= n (find-smallest-divisor n)))


;; known primes and non primes
;;
;;     (fermat-prime? 1009)
;;     => true
;;
;;     (prime? 1009)
;;     => true
;;
;;     (fermat-prime? 1011)
;;     => false
;;
;;     (prime? 1011)
;;     => false
;;
;;     (fermat-prime? 10007)
;;     => true
;;
;;     (prime? 10007)
;;     => true
;;
;;     (fermat-prime? 10011)
;;     => false
;;
;;     (prime? 10011)
;;     => false
;;

;; Carmichael numbers
;;
;;     (fermat-prime? 561)
;;     => true
;;
;;     (prime? 561)
;;     => false
;;
;;     (fermat-prime? 1105)
;;     => true
;;
;;     (prime? 1105)
;;     => false
;;
;;     (fermat-prime? 1729)
;;     => true
;;
;;     (prime? 1729)
;;     => false
;;
;;     (fermat-prime? 2465)
;;     => true
;;
;;     (prime? 2465)
;;     => false
;;
;;     (fermat-prime? 2821)
;;     => true
;;
;;     (prime? 2821)
;;     => false
;;
;;     (fermat-prime? 6601)
;;     => true
;;
;;     (prime? 6601)
;;     => false


;; All the Carmichael numbers in the footnote fool the fermat-prime test

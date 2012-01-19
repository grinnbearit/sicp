(ns sicp.chpt1.ex1-24)


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
  [n]
  (letfn [(try-it [a]
            (= (expmod a n n) a))]
    (try-it (+ 1 (int (rand n))))))


(defn prime?
  ([n]
     (prime? n (int (Math/log n))))
  ([n times]
     (if (zero? times)
       true
       (and (fermat-test n) (prime? n (dec times))))))


(defn timed-prime-test
  [n]
  (let [start (. System (nanoTime))
        is-prime (prime? n)]
    [is-prime (/ (double (- (. System (nanoTime)) start)) 1000000.0)]))


(defn find-timed-primes-between
  [a b]
  (letfn [(mapper [n]
            (let [[is-prime time-taken] (timed-prime-test n)]
              [is-prime n time-taken]))]

    (for [[is-prime n time-taken]
          (map mapper (range (if (odd? a) a (inc a))
                             b
                             2))
          :when is-prime]
      [n time-taken])))


;; Each test was run 100,000 times before the time was recorded to account for HotSpot optimization

;; (take 3 (find-timed-primes-between 1000 1000000000))

;; 1009 0.043298 msec
;; 1013 0.044814 msec
;; 1019 0.042874 msec

;; average time taken 0.04366 msec

;; (take 3 (find-timed-primes-between 10000 1000000000))

;; 10007 0.062862 msec
;; 10009 0.064997 msec
;; 10037 0.06907 msec

;; average time taken 0.065654 msec

;; (take 3 (find-timed-primes-between 100000 1000000000))

;; 100003 0.084971 msec
;; 100019 0.083323 msec
;; 100043 0.083917 msec

;; average time taken 0.08407 msec

;; (take 3 (find-timed-primes-between 1000000 1000000000))

;; 1000003 0.116445 msec
;; 1000033 0.119691 msec
;; 1000037 0.108891 msec

;; average time taken 0.11501 msec

;; Since the growth is log(n), I would expect that the time to test primes near 10^6 is
;; roughly log(1000) times greater that testing primes near 10^3, roughly 10 times larger

;; The real result is closer to 2.5 times

;; This discrepancy is probably due to the Stochastic nature of this algorithm which can terminate in
;; a fewer number of steps if the primality test fails earlier

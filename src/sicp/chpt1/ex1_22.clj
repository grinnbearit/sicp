(ns sicp.chpt1.ex1-22)


(defn divides?
  [a b]
  (zero? (rem a b)))


(defn find-smallest-divisor
  ([n]
     (find-smallest-divisor n 2))
  ([n test-divisor]
     (cond (> (* test-divisor test-divisor) n)
           n

           (divides? n test-divisor)
           test-divisor

           :else
           (recur n (inc test-divisor)))))


(defn prime?
  [n]
  (= n (find-smallest-divisor n)))


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

;; `(take 3 (find-timed-primes-between 1000 1000000000))`
;;
;; * 1009, 0.002619 msec
;; * 1013, 0.002167 msec
;; * 1019, 0.002177 msec
;;
;; average time taken, 0.00232

;; `(take 3 (find-timed-primes-between 10000 1000000000))`
;;
;; * 10007, 0.006244 msec
;; * 10009, 0.005459 msec
;; * 10037, 0.005579 msec
;;
;; average time taken, 0.00576

;; `(take 3 (find-timed-primes-between 100000 1000000000))`
;;
;; * 100003, 0.018717 msec
;; * 100019, 0.017477 msec
;; * 100043, 0.016873 msec
;;
;; average time taken, 0.01769

;; `(take 3 (find-timed-primes-between 1000000 1000000000))`
;;
;; * 1000003, 0.05348 msec
;; * 1000033, 0.053039 msec
;; * 1000037, 0.052869 msec
;;
;; average time taken, 0.05313

;; Taking the average ratios of each order of magnitude increase
;;
;; \\(\frac{\frac{0.00576}{0.00232} + \frac{0.01769}{0.00576} + \frac{0.05313}{0.01769}}{3} \rightarrow 2.85244\\)

;; Which is roughly equal to \\(\sqrt{10}\\)

;; This supports the \\(\sqrt{n}\\) prediction and shows that runtime is proportional to number of steps

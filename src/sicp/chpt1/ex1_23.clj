(ns sicp.chpt1.ex1-23)

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
;;
;; 1009 0.0207 msec
;; 1013 0.01748 msec
;; 1019 0.017589 msec
;;
;; average time taken 0.01859

;; (take 3 (find-timed-primes-between 10000 1000000000))
;;
;; 10007 0.053858 msec
;; 10009 0.05361 msec
;; 10037 0.053845 msec
;;
;; average time taken 0.05377

;; (take 3 (find-timed-primes-between 100000 1000000000))
;;
;; 100003 0.251168 msec
;; 100019 0.239663 msec
;; 100043 0.241405 msec
;;
;; average time taken 0.24408

;; (take 3 (find-timed-primes-between 1000000 1000000000))
;;
;; 1000003 0.526431 msec
;; 1000033 0.525764 msec
;; 1000037 0.515357 msec
;;
;; average time taken 0.52252


;; Taking the average ratios of each order of magnitude increase
;;
;; (/ (+ (/ 0.05377 0.01859) (/ 0.24408 0.05377) (/ 0.52252 0.24408)) 3)
;;
;; we get 3.19084 which is roughly equal to (Math/sqrt 10)


;; Ratio of without-even-integers/with-all-integers
;; > 1000
;; (/ 0.01859 0.00232)
;; 8.01293

;; > 10000
;; (/ 0.05377 0.00576)
;; 9.33506

;; > 100000
;; (/ 0.24408 0.01769)
;; 13.79763

;; > 1000000
;; (/ 0.52252 0.05313)
;; 9.83474

;; Average 10.24509 which is an order of magnitude worse than the original version

;; I think this is because INC corresponds directly to a register increment which is a very fasy operation
;; compared to 'ADD 2'. Even though the number of steps is halved, this difference shows by making this version an order of magnitude slower.

;; This is only for smaller numbers though, as the input gets larger and larger, the halving of the number of steps will dominate
;; Asymptotically, this algorithm should complete in half the time as the previous one

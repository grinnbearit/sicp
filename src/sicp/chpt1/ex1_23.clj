(ns sicp.chpt1.ex1-23
  (:use [sicp.chpt1.ex1-21 :only [divides?]])
  (:require [sicp.chpt1.ex1-22 :as c1e22]))


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


(def timed-prime-test
  #(c1e22/timed-prime-test % :prime-fn prime?))


(def find-timed-primes-between
  #(c1e22/find-timed-primes-between %1 %2 :timed-prime-test-fn timed-prime-test))


;; Each test was run 100,000 times before the time was recorded to account for HotSpot optimization

;; `(take 3 (find-timed-primes-between 1000 1000000000))`
;;
;; * 1009, 0.0207 msec
;; * 1013, 0.01748 msec
;; * 1019, 0.017589 msec
;;
;; average time taken, 0.01859

;; `(take 3 (find-timed-primes-between 10000 1000000000))`
;;
;; * 10007, 0.053858 msec
;; * 10009, 0.05361 msec
;; * 10037, 0.053845 msec
;;
;; average time taken, 0.05377

;; `(take 3 (find-timed-primes-between 100000 1000000000))`
;;
;; * 100003, 0.251168 msec
;; * 100019, 0.239663 msec
;; * 100043, 0.241405 msec
;;
;; average time taken, 0.24408

;; `(take 3 (find-timed-primes-between 1000000 1000000000))`
;;
;; * 1000003, 0.526431 msec
;; * 1000033, 0.525764 msec
;; * 1000037, 0.515357 msec
;;
;; average time taken, 0.52252


;; Taking the average ratios of each order of magnitude increase
;;
;; \\(\frac{\frac{0.05377}{0.01859} + \frac{0.24408}{0.05377} + \frac{0.52252}{0.24408}}{3} \rightarrow 3.19084\\)

;; Which is roughly equal to \\(\sqrt{10}\\)


;; Ratio of `without-even-integers` to `with-all-integers`

;; for \\(n > 1000\\)

;; \\(\frac{0.01859}{0.00232} \rightarrow 8.01293\\)

;; for \\(n > 10000\\)

;; \\(\frac{0.05377}{0.00576} \rightarrow 9.33506\\)

;; for \\( n > 100000\\)

;; \\(\frac{0.24408}{0.01769} \rightarrow 13.79763\\)

;; for \\(n > 1000000\\)

;; \\(\frac{0.52252}{0.05313} \rightarrow 9.83474\\)

;; The average is 10.24509, which is an order of magnitude worse than the original version

;; I think this is because `inc` corresponds directly to a register increment which is a very fast operation
;; compared to `+`. Even though the number of steps is halved, this difference shows by making this version an order of magnitude slower.

;; This is only for smaller numbers though, as the input gets larger and larger, the halving of the number of steps will dominate. Asymptotically,
;; this algorithm should complete in half the time as the previous one

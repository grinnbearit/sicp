(ns sicp.chpt2.ex2-14)


(defrecord Interval [lb ub])


(defn abs
  [x]
  (if (pos? x) x (- x)))


(defn make-interval
  [lb ub]
  (Interval. lb ub))


(defn lower-bound
  [i]
  (.lb i))


(defn upper-bound
  [i]
  (.ub i))


(defn make-center-percent
  [c p]
  (let [width (abs (* (/ p 100) c))]
    (make-interval (- c width)
                   (+ c width))))


(defn center
  [i]
  (/ (+ (lower-bound i)
        (upper-bound i))
     2))


(defn percent
  [i]
  (let [c (center i)]
    (abs (* (/ (- (upper-bound i) c)
               c)
            100))))


(defn print-interval
  [i]
  (format "(%1.2f, %1.2f%%)"
          (double (center i))
          (double (percent i))))


(defn add-interval
  [x y]
  (make-interval (+ (lower-bound x)
                    (lower-bound y))
                 (+ (upper-bound x)
                    (upper-bound y))))


(defn mul-interval
  [x y]
  (let [p1 (* (lower-bound x) (lower-bound y))
        p2 (* (lower-bound x) (upper-bound y))
        p3 (* (upper-bound x) (lower-bound y))
        p4 (* (upper-bound x) (upper-bound y))]
    (make-interval (min p1 p2 p3 p4)
                   (max p1 p2 p3 p4))))


(defn div-interval
  [x y]
  (letfn [(spans-zero?
            [i]
            (and (<= (lower-bound i) 0)
                 (<= 0 (upper-bound i))))]

    (if (spans-zero? y)
      (throw (ArithmeticException. "Divide by zero"))
      (mul-interval x
                    (make-interval (/ (lower-bound y))
                                   (/ (upper-bound y)))))))


(defn par1
  [r1 r2]
  (div-interval (mul-interval r1 r2)
                (add-interval r1 r2)))


(defn par2
  [r1 r2]
  (let [one (make-interval 1 1)]
    (div-interval one
                  (add-interval (div-interval one r1)
                                (div-interval one r2)))))


;;     (let [one (make-interval 1 1)
;;           i (make-center-percent 1000 1)]
;;       (letfn [(mapper [n]
;;                 (->> (cycle [i (div-interval one i)])
;;                      (take (* 2 n))
;;                      (reduce mul-interval)
;;                      print-interval))]
;;         (map mapper (range 1 5))))
;;     => ("(1.00, 2.00%)"
;;         "(1.00, 4.00%)"
;;         "(1.00, 5.99%)"
;;         "(1.00, 7.98%)")

;; `mapper` returns \\(\prod [1000, \frac{1}{1000}, 1000, \frac{1}{1000}, 1000, \frac{1}{1000}, ...]\\), for \\(2n\\) terms

;; The error increases by 1% with every multiplication

;;     (let [i1 (make-center-percent 1000 1)
;;           i2 (make-center-percent -1000 1)]
;;       (letfn [(mapper [n]
;;                 (->> (cycle [i1 i2])
;;                      (concat [i1])
;;                      (take (* 2 n))
;;                      (reduce add-interval)
;;                      print-interval))]
;;         (map mapper (range 1 5))))
;;     => ("(2000.00, 1.00%)"
;;         "(2000.00, 2.00%)"
;;         "(2000.00, 3.00%)"
;;         "(2000.00, 4.00%)")

;; `mapper` returns \\(\sum [(1000), 1000, -1000, 1000, -1000, ...]\\), for \\(2n\\) terms

;; The error increases by 0.5% with every addition

;;     (let [i1 (make-center-percent 1000 1)
;;           i2 (make-center-percent 2000 1)]
;;       [(print-interval (par1 i1 i2))
;;        (print-interval (par2 i1 i2))])
;;     => ["(666.93, 3.00%)"
;;         "(666.67, 1.00%)"]

;; The formula \\(\frac{R1R2}{R1+R2}\\) results in a higher error than \\(\frac{1}{R1} + \frac{1}{R2}\\)

;; Every operation with intervals, even if algebraicly a no-op increases the error

;; The problem here is that identical intervals don't cancel themselves, ie. \\(\frac{x}{x} \neq 1\\)

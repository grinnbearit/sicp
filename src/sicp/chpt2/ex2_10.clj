(ns sicp.chpt2.ex2-10)


(defrecord Interval [lb ub])


(defn make-interval
  [lb ub]
  (Interval. lb ub))


(defn lower-bound
  [i]
  (.lb i))


(defn upper-bound
  [i]
  (.ub i))


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

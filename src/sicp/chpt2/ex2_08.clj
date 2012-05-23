(ns sicp.chpt2.ex2-08)


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


(defn sub-interval
  [x y]
  (let [a (- (lower-bound x)
             (lower-bound y))
        b (- (upper-bound x)
             (upper-bound y))]
    (if (< a b)
      (make-interval a b)
      (make-interval b a))))

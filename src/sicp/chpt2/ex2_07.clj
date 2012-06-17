(ns sicp.chpt2.ex2-07)


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


;;     (lower-bound (make-interval 2 3))
;;     => 2

;;     (upper-bound (make-interval 2 3))
;;     => 3

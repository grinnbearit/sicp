(ns sicp.chpt2.ex2-08
  (:use [sicp.chpt2.ex2-07 :only [make-interval
                                  lower-bound
                                  upper-bound]]))


(defn add-interval
  [x y]
  (make-interval (+ (lower-bound x)
                    (lower-bound y))
                 (+ (upper-bound x)
                    (upper-bound y))))


;;     (add-interval (make-interval 4 5)
;;                   (make-interval 2 3))
;;     => #sicp.chpt2.ex2_08.Interval{:lb 6, :ub 8}


(defn sub-interval
  [x y]
  (let [a (- (lower-bound x)
             (lower-bound y))
        b (- (upper-bound x)
             (upper-bound y))]
    (if (< a b)
      (make-interval a b)
      (make-interval b a))))


;;     (sub-interval (make-interval 4 5)
;;                   (make-interval 2 3))
;;     => #sicp.chpt2.ex2_08.Interval{:lb 2, :ub 2}

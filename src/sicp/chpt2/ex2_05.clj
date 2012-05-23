(ns sicp.chpt2.ex2-05)


(defn cons
  [x y]
  (int (* (Math/pow 2 x)
          (Math/pow 3 y))))


(defn- d-component
  [n d]
  (loop [x n acc 0]
    (if (zero? (rem x d))
      (recur (/ x d) (inc acc))
      acc)))


(defn car
  [z]
  (d-component z 2))


(defn cdr
  [z]
  (d-component z 3))


;;; (cons 2 3)
;;; (car (cons 2 3))
;;; => 2

;;; (cdr (cons 2 3))
;;; => 3

(ns sicp.chpt1.ex1-37)


(defn cont-frac
  ([n d k]
     (cont-frac n d k 1))
  ([n d k i]
     (if (> i k)
       0
       (/ (n i) (+ (d i) (cont-frac n d k (inc i)))))))



;;; phi is 1.61803399
;;; 1/phi is 0.61803398


(defn phi
  [k]
  (cont-frac (constantly 1)
             (constantly 1)
             k))

;;; (float (phi 11))
;;; with k = 11, we get 4 digits after the decimal point of accuracy


;;; an iterative solution

(defn cont-frac-iter
  ([n d k]
     (cont-frac-iter n d k 0))
  ([n d k acc]
     (if (zero? k)
       acc
       (recur n d (dec k) (/ (n k)
                             (+ (d k) acc))))))

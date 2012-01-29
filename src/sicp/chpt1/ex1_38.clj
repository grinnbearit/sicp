(ns sicp.chpt1.ex1-38)


(defn cont-frac
  ([n d k]
     (cont-frac n d k 0))
  ([n d k acc]
     (if (zero? k)
       acc
       (recur n d (dec k) (/ (n k)
                             (+ (d k) acc))))))


(defn e
  [k]
  (float (+ (cont-frac (constantly 1)
                       (fn [x]
                         (if (= (rem x 3) 2)
                           (* 2 (inc (quot x 3)))
                           1))
                       k)
            2)))

;;; e is 2.71828183
;;; (e 9)
;;; => 2.71828

(ns sicp.chpt1.ex1-37)


;;; __Recursive__


(defn cont-frac
  ([n d k]
     (cont-frac n d k 1))
  ([n d k i]
     (if (> i k)
       0
       (/ (n i) (+ (d i) (cont-frac n d k (inc i)))))))



;;; \\(\phi \approx 1.61803399\\)

;;; \\(\frac{1}{\phi} \approx 0.61803398\\)


(defn phi-inverse
  [k]
  (cont-frac (constantly 1)
             (constantly 1)
             k))

;;;     (float (phi-inverse 11))
;;;     => 0.6180556

;;; when k is 11, we get 4 digits after the decimal point of accuracy


;;; __Iterative__


(defn cont-frac
  ([n d k]
     (cont-frac n d k 0))
  ([n d k acc]
     (if (zero? k)
       acc
       (recur n d (dec k) (/ (n k)
                             (+ (d k) acc))))))

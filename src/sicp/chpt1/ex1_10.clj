(ns sicp.chpt1.ex1-10)


;;; The [Ackermann function](http://en.wikipedia.org/wiki/Ackermann_function) grows very quickly for larger values
;;; of `x`

(defn A
  [x y]
  (cond (= y 0)
        0

        (= x 0)
        (* 2 y)

        (= y 1)
        2

        :else
        (recur (- x 1)
               (A x (- y 1)))))


(comment (A 1 10))
;;;     => 1024

(comment (A 2 4))
;;;     => 65536


(comment (A 3 3))
;;;     => 65536


(defn f [n] (A 0 n))
;;; (A 0 n) = 2*n


(defn g [n] (A 1 n))
;;; (A 1 n) = 2^n


(defn h [n] (A 2 n))
;;; (A 2 n) = 2^2^2^2...n times

;;; (A 2 n) is called tetration, this is the next hyper operator after exponentiation

(ns sicp.chpt2.ex2-57
  (:require [sicp.chpt2.ex2-56 :as ex2-56]))

;;; `make-sum` and `make-product` don't need to be rebound but can be used
;;; to build an expression


(defn make-sum
  [& args]
  (list* '+ args))


(defn augend
  [e]
  (if (> (count e) 3)
    (conj (drop 2 e) '+)
    (nth e 2)))


(defn make-product
  [& args]
  (list* '* args))


(defn multiplicand
  [e]
  (if (> (count e) 3)
    (conj (drop 2 e) '*)
    (nth e 2)))


;;; `with-redefs` rebinds the functions for all threads in it's context.
;;; An alternative would be to use `binding` and mark `ex2-56/augend` and  `ex2-56/multiplicand`
;;; dynamic


(defn deriv
  [exp var]
  (with-redefs [ex2-56/augend augend
                ex2-56/multiplicand multiplicand]
    (ex2-56/deriv exp var)))


;;     (deriv '(+ x x x) 'x)
;;     => 3

;;     (deriv '(* x x x) 'x)
;;     => [+ [* x [+ x x]] (* x x)]

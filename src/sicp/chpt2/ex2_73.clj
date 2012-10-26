(ns sicp.chpt2.ex2-73
  (:refer-clojure :exclude [get])
  (:use [sicp.chpt2.ex2-56 :only [variable?
                                  same-variable?
                                  make-sum
                                  make-product
                                  make-exponentiation]]))


;; `number?` and `same-variable` don't dispatch on a particular operator and so can't be used
;; with the data-directed approach


;; Clojure is opinionated about mutable state and controls access to it through [atoms](http://clojure.org/atoms),
;; [refs](http://clojure.org/refs) and [agents](http://clojure.org/agents)


(def dispatch-table (atom {}))


(defn put
  [op type item]
  (swap! dispatch-table assoc-in [op type] item))


(defn get
  [op type]
  (get-in @dispatch-table [op type] false))


(defn operator
  [exp]
  (first exp))


(defn operands
  [exp]
  (rest exp))


(defn deriv
  [exp var]
  (cond (number? exp)
        0

        (variable? exp)
        (if (same-variable? exp var) 1 0)

        :else
        ((get 'deriv (operator exp)) (operands exp) var)))


(defn addend
  [ops]
  (first ops))


(defn augend
  [ops]
  (if (< 2 (count ops))
    (conj (rest ops) '+)
    (second ops)))


(defn sum-deriv
  [ops var]
  (make-sum (deriv (addend ops) var)
            (deriv (augend ops) var)))


(defn multiplier
  [ops]
  (first ops))


(defn multiplicand
  [ops]
  (if (< 2 (count ops))
    (conj (rest ops) '*)
    (second ops)))


(defn product-deriv
  [ops var]
  (make-sum
   (make-product (multiplier ops)
                 (deriv (multiplicand ops) var))
   (make-product (deriv (multiplier ops) var)
                 (multiplicand ops))))


;; install sum and product

;;     (put 'deriv '+ sum-deriv)
;;     (put 'deriv '* product-deriv)

;;     (deriv '(+ x x x) 'x)
;;     => 3

;;     (deriv '(* x x x) 'x)
;;     => [+ [* x [+ x x]] (* x x)]


(defn base
  [ops]
  (first ops))


(defn exponent
  [ops]
  (if (< 2 (count ops))
    (conj (rest ops) '**)
    (second ops)))


(defn exponent-deriv
  [ops var]
  (make-product
   (make-product (exponent ops)
                 (make-exponentiation (base ops)
                                      (make-sum (exponent ops)
                                                -1)))
   (deriv (base ops) var)))


;; install exponent

;;     (put 'deriv '** exponent-deriv)

;;     (deriv '(+ (* x y) (** x 2)) 'x)
;;     => [+ y [* 2 x]]


;; changing the order of the arguments to `get` would
;; only require that to be reflected in `put`

;; `(put 'deriv '+ sum-deriv)` to `(put '+ 'deriv sum-deriv)`

;; It doesn't matter how the function is stored as long as its accessed the same way


;; __alternative implementation__


;; Clojure does have its own take on polymorphic dispatch using [multimethods](http://clojure.org/multimethods), the same
;; problem can be solved without an explicit dispatch table


(defmulti deriv-multi (fn [op _ _] op))


(defn deriv
  [exp var]
  (cond (number? exp)
        0

        (variable? exp)
        (if (same-variable? exp var) 1 0)

        :else
        (deriv-multi (operator exp) (operands exp) var)))


(defmethod deriv-multi '+
  [_ ops var]
  (sum-deriv ops var))


(defmethod deriv-multi '*
  [_ ops var]
  (product-deriv ops var))


(defmethod deriv-multi '**
  [_ ops var]
  (exponent-deriv ops var))


;;     (deriv '(+ x x x) 'x)
;;     => 3

;;     (deriv '(* x x x) 'x)
;;     => [+ [* x [+ x x]] (* x x)]

;;     (deriv '(+ (* x y) (** x 2)) 'x)
;;     => [+ y [* 2 x]]

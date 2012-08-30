(ns sicp.chpt2.ex2-56
  (:use [sicp.chpt1.ex1-16 :only [fast-expt]]))


(defn variable?
  [e]
  (symbol? e))


(defn same-variable?
  [v1 v2]
  (= v1 v2))


(defn sum?
  [e]
  (and (coll? e)
       (= (first e) '+)))


(defn addend
  [e]
  (nth e 1))


(defn augend
  [e]
  (nth e 2))


(defn =number?
  [exp num]
  (and (number? exp)
       (= exp num)))


(defn make-sum
  [a1 a2]
  (cond (=number? a1 0)
        a2

        (=number? a2 0)
        a1

        (and (number? a1) (number? a2))
        (+ a1 a2)

        :else
        ['+ a1 a2]))


(defn product?
  [e]
  (and (coll? e)
       (= (first e) '*)))


(defn multiplier
  [e]
  (nth e 1))


(defn multiplicand
  [e]
  (nth e 2))


(defn make-product
  [m1 m2]
  (cond (or (=number? m1 0)
            (=number? m2 0))
        0

        (=number? m1 1)
        m2

        (=number? m2 1)
        m1

        (and (number? m1) (number? m2))
        (* m1 m2)

        :else
        ['* m1 m2]))


(defn exponentiation?
  [exp]
  (and (coll? exp)
       (= '** (first exp))))


(defn base
  [exp]
  (nth exp 1))


(defn exponent
  [exp]
  (nth exp 2))


(defn make-exponentiation
  [b e]
  (cond (=number? e 0)
        1

        (=number? e 1)
        b

        (and (number? b) (number? e))
        (fast-expt b e)

        :else
        ['** b e]))


(defn deriv
  [exp var]
  (cond (number? exp)
        0

        (variable? exp)
        (if (same-variable? exp var) 1 0)

        (sum? exp)
        (make-sum (deriv (addend exp) var)
                  (deriv (augend exp) var))

        (product? exp)
        (make-sum
         (make-product (multiplier exp)
                       (deriv (multiplicand exp) var))
         (make-product (deriv (multiplier exp) var)
                       (multiplicand exp)))

        (exponentiation? exp)
        (make-product
         (make-product (exponent exp)
                       (make-exponentiation (base exp)
                                            (make-sum (exponent exp)
                                                      -1)))
         (deriv (base exp) var))

        :else
        (throw (Exception. (str "unknown expression type -- DERIV " exp)))))


;;     (deriv '(+ (* x y) (** x 2)) 'x)
;;     => [+ y [* 2 x]]

;;     (deriv '(+ y (* 2 x)) 'x)
;;     => 2

;;     (deriv '2 'x)
;;     => 0

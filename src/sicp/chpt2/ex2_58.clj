(ns sicp.chpt2.ex2-58
  (:require [sicp.chpt2.ex2-56 :as ex2_56])
  (:use [sicp.chpt1.ex1-16 :only [fast-expt]]))


;;; a sum is a sequence containing a `+`, similarly a product contains a `*`

;;; the addend/multiplier consists of all terms to the __first__ occurrence of the operator
;;; and the augend/multiplicand, the remaining terms

;;; exponentiation is right-associative unlike addition and multiplication, so the base
;;; consists of all terms to the __last__ occurrence of `**` and the exponent, the remaining
;;; terms

;;; to handle precedence, check for the operator with the lowest precedence first,
;;; `+` first, then `*` and finally `**`


(defn single?
  [e]
  (and (coll? e)
       (= 1 (count e))))


(defn sum?
  [e]
  (and (coll? e)
       (some #{'+} e)))


(defn addend
  [e]
  (let [a (take-while (complement #{'+}) e)]
    (if (single? a) (first a) a)))


(defn augend
  [e]
  (let [a (rest (drop-while (complement #{'+}) e))]
    (if (single? a) (first a) a)))


(defn make-sum
  [a1 a2]
  (cond (ex2_56/=number? a1 0)
        a2

        (ex2_56/=number? a2 0)
        a1

        (and (number? a1) (number? a2))
        (+ a1 a2)

        :else
        [a1 '+ a2]))


(defn product?
  [e]
  (and (coll? e)
       (some #{'*} e)))


(defn multiplier
  [e]
  (let [m (take-while (complement #{'*}) e)]
    (if (single? m) (first m) m)))


(defn multiplicand
  [e]
  (let [m (rest (drop-while (complement #{'*}) e))]
    (if (single? m) (first m) m)))


(defn make-product
  [m1 m2]
  (cond (or (ex2_56/=number? m1 0)
            (ex2_56/=number? m2 0))
        0

        (ex2_56/=number? m1 1)
        m2

        (ex2_56/=number? m2 1)
        m1

        (and (number? m1) (number? m2))
        (* m1 m2)

        :else
        [m1 '* m2]))


(defn exponentiation?
  [e]
  (and (coll? e)
       (some #{'**} e)))


(defn base
  [e]
  (let [m (rest (drop-while (complement #{'**}) (reverse e)))]
    (if (single? m) (first m) (reverse m))))


(defn exponent
  [e]
  (let [m (take-while (complement #{'**}) (reverse e))]
    (if (single? m) (first m) (reverse m))))


(defn make-exponentiation
  [b e]
  (cond (ex2_56/=number? e 0)
        1

        (ex2_56/=number? e 1)
        b

        (and (number? b) (number? e))
        (fast-expt b e)

        :else
        [b '** e]))


(defn deriv
  [exp var]
  (with-redefs [ex2_56/sum? sum?
                ex2_56/addend addend
                ex2_56/augend augend
                ex2_56/make-sum make-sum
                ex2_56/product? product?
                ex2_56/multiplier multiplier
                ex2_56/multiplicand multiplicand
                ex2_56/make-product make-product
                ex2_56/exponentiation? exponentiation?
                ex2_56/base base
                ex2_56/exponent exponent
                ex2_56/make-exponentiation make-exponentiation]
    (ex2_56/deriv exp var)))


;;     (deriv '(x ** 2 + x * x) 'x)
;;     => [[2 * x] + [x + x]]

;;     (deriv '(x ** 2 ** 3) 'x)
;;     => [[3 * [(x ** 2) ** 2]] * [2 * x]]

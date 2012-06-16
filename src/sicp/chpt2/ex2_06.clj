(ns sicp.chpt2.ex2-06)


;;; An integer \\(n\\) corresponds to n applications of a function `f` as a church numeral


(defn church->int
  [n]
  ((n inc) 0))


(def zero
  (fn [f]
    (fn [x]
      x)))


(defn add1
  [n]
  (fn [f]
    (fn [x]
      (f ((n f) x)))))


(def one
  (fn [f]
    (fn [x]
      (f x))))


(def two
  (fn [f]
    (fn [x]
      (f (f x)))))


;;; Addition is simply function composition


(defn add
  [a b]
  (fn [f]
    (comp (a f) (b f))))


;;;     (church->int zero)
;;;     => 0

;;;     (church->int one)
;;;     => 1

;;;     (church->int two)
;;;     => 2

;;;     (church->int (add one two))
;;;     => 3

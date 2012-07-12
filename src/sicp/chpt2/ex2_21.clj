(ns sicp.chpt2.ex2-21)


;;; __Recursive__


(defn square-list
  [items]
  (if (empty? items)
    items
    (conj (square-list (rest items))
          (#(* % %) (first items)))))


;;; __Sequential__


(defn square-list
  [items]
  (map #(* % %) items))


;;; in Clojure, the sequence operators (like `map` and `filter`) return lazy sequences,
;;; these let us chain operators while only computing the values on realizing them

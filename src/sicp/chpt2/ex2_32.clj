(ns sicp.chpt2.ex2-32)


(defn subsets
  [s]
  (if (empty? s)
    [()]
    (let [rst (subsets (rest s))]
      (concat rst (map #(conj % (first s)) rst)))))



;;      (subsets [1 2 3])
;;      => (() (3) (2) (2 3) (1) (1 3) (1 2) (1 2 3))


;;; `subsets` works because it can be defined recursively as
;;; the collection of all the subsets of the set with the first element
;;; removed (`rst`) and the same subsets with the first element added to each.

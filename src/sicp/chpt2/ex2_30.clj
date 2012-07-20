(ns sicp.chpt2.ex2-30)

;;; __directly__

(defn square-tree
  [tree]
  (cond (not (coll? tree))
        (* tree tree)

        (empty? tree)
        ()

        :else
        (conj (square-tree (rest tree))
              (square-tree (first tree)))))


;;; __using map__


(defn square-tree
  [tree]
  (map (fn [subtree]
         (if (coll? subtree)
           (square-tree subtree)
           (* subtree subtree)))
       tree))



;;      (square-tree (list 1
;;                         (list 2 (list 3 4) 5)
;;                         (list 6 7)))
;;      => (1 (4 (9 16) 25) (36 49))

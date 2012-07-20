(ns sicp.chpt2.ex2-31)


(defn tree-map
  [f tree]
  (map (fn [subtree]
         (if (coll? subtree)
           (tree-map f subtree)
           (f subtree)))
       tree))


(defn square-tree
  [tree]
  (tree-map #(* % %) tree))


;;      (square-tree (list 1
;;                         (list 2 (list 3 4) 5)
;;                         (list 6 7)))
;;      => (1 (4 (9 16) 25) (36 49))

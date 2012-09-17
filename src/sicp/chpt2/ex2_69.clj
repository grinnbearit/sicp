(ns sicp.chpt2.ex2-69
  (:use [sicp.chpt2.ex2-67 :only [weight make-code-tree make-leaf decode]]
        [sicp.chpt2.ex2-68 :only [encode]]))


;;; implementing more declarative versions of both `adjoin-set` and `make-leaf-set`


(defn adjoin-set
  [x s]
  (let [[smaller larger] (split-with #(< (weight %) (weight x)) s)]
    (concat smaller (conj larger x))))


(defn make-leaf-set
  [leaves]
  (reduce #(adjoin-set %2 %1) () leaves))


(defn successive-merge
  [leaf-set]
  (if (= 1 (count leaf-set))
    (first leaf-set)
    (recur (adjoin-set (make-code-tree (second leaf-set)
                                       (first leaf-set))
                       (drop 2 leaf-set)))))


(defn generate-huffman-tree
  [leaves]
  (successive-merge (make-leaf-set leaves)))


(def tree
  (generate-huffman-tree [(make-leaf 'A 8)
                          (make-leaf 'B 4)
                          (make-leaf 'D 1)
                          (make-leaf 'C 1)]))


;;     (decode [0 1 1 0 0 1 0 1 0 1 1 1 0] tree)
;;     => (A D A B B C A)


;;     (encode '(A D A B B C A) tree)
;;     => (0 1 1 0 0 1 0 1 0 1 1 1 0)

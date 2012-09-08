(ns sicp.chpt2.ex2-63)

;;; Both `tree->list-1` and `tree->list-2` __walk__ the tree [in order](http://en.wikipedia.org/wiki/Depth-first_search#Vertex_orderings)

;;; Each algorithm walks the tree just once and so both take the same number of steps \\(O(n)\\).


(defrecord Tree [entry left right])


(defn make-tree
  [entry left right]
  (Tree. entry left right))


(defn left-branch
  [tree]
  (.left tree))


(defn right-branch
  [tree]
  (.right tree))


(defn entry
  [tree]
  (.entry tree))


(defn tree->list-1
  [tree]
  (if (nil? tree)
    []
    (concat (tree->list-1 (left-branch tree))
            [(entry tree)]
            (tree->list-1 (right-branch tree)))))


(defn tree->list-2
  [tree]
  (letfn [(copy-to-list
            [t result-list]
            (if (nil? t)
              result-list
              (recur (left-branch t)
                     (conj (copy-to-list (right-branch t) result-list)
                           (entry t)))))]
    (copy-to-list tree ())))

;;; examples from figure 2.16

(def tree-a
  (make-tree 7
             (make-tree 3
                        (make-tree 1 nil nil)
                        (make-tree 5 nil nil))
             (make-tree 9
                        nil
                        (make-tree 11 nil nil))))


(def tree-b
  (make-tree 3
             (make-tree 1 nil nil)
             (make-tree 7
                        (make-tree 5 nil nil)
                        (make-tree 9
                                   nil
                                   (make-tree 11 nil nil)))))


(def tree-c
  (make-tree 5
             (make-tree 3
                        (make-tree 1 nil nil)
                        nil)
             (make-tree 9
                        (make-tree 7 nil nil)
                        (make-tree 11 nil nil))))


;;     (tree->list-1 tree-a)
;;     => (1 3 5 7 9 11)

;;     (tree->list-1 tree-b)
;;     => (1 3 5 7 9 11)

;;     (tree->list-1 tree-c)
;;     => (1 3 5 7 9 11)

;;     (tree->list-2 tree-a)
;;     => (1 3 5 7 9 11)

;;     (tree->list-2 tree-b)
;;     => (1 3 5 7 9 11)

;;     (tree->list-2 tree-c)
;;     => (1 3 5 7 9 11)

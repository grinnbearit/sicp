(ns sicp.chpt2.ex2-64
  (:use [sicp.chpt2.ex2-63 :only [make-tree]]))


;;; `list-tree` converts a sorted sequence of elements into a balanced binary tree
;;; in just one pass over `elts`, it does this by using `partial-tree`

;;; `partial-tree` builds the tree bottom up and in-order, the first element in `elts`
;;; becomes the bottom left leaf node in the whole tree and as the list is "consumed",
;;; builds up to the root node.

;;; At the root node, `partial-tree` uses the remaining elements to build the right branch
;;; in the same way.

;;; `partial-tree` only traverses `elts` once and so grows linearly with the number of elements.


(declare partial-tree)


(defn list->tree
  [elts]
  (first (partial-tree elts (count elts))))


(defn partial-tree
  [elts n]
  (if (zero? n)
    [nil elts]
    (let [left-size (quot (dec n) 2)
          [left-tree non-left-elts] (partial-tree elts left-size)
          right-size (- n (inc left-size))
          this-entry (first non-left-elts)
          [right-tree remaining-elts] (partial-tree (rest non-left-elts) right-size)]
      [(make-tree this-entry left-tree right-tree) remaining-elts])))

;;; `partial-tree` avoids counting or partitioning `elts` to have a runtime of \\(O(n)\\)

;;; The naive `list->tree` algorithm partitions the remaining elements at every level and so has a runtime
;;; similar to [merge-sort](http://en.wikipedia.org/wiki/Merge_sort), \\(O(log _2 n)\\)

(defn list->tree-naive
  [elts]
  (when-not (empty? elts)
    (let [pivot (quot (count elts) 2)
          [left-elts [this-entry & right-elts]] (split-at pivot elts)]
      (make-tree this-entry
                 (list->tree left-elts)
                 (list->tree right-elts)))))

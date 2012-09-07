(ns sicp.chpt2.ex2-65
  (:use [sicp.chpt2.ex2-62 :only [union-set]]
        [sicp.chpt2.ex2-63 :only [tree->list-1]]
        [sicp.chpt2.ex2-64 :only [list->tree]]))

;;; Since `tree->list-1`, `list->tree` and `union-set`/`intersection-set` are all \\(O(n)\\),
;;; a linear combination of these operations is also \\(O(n)\\)

;;; intersection of 2 sorted sequences

(defn intersection-set
  [s1 s2]
  (cond (or (empty? s1) (empty? s2))
        ()

        (< (first s1) (first s2))
        (recur (rest s1) s2)

        (> (first s1) (first s2))
        (recur s1 (rest s2))

        :else
        (conj (intersection-set (rest s1) (rest s2))
              (first s1))))

;;     (intersection-set [1 3 5 7 9] [2 3 5 7 8])
;;     => (3 5 7)

;;     (intersection-set [1 3 5] [2 4 6])
;;     => ()


(defn union-btree
  [b1 b2]
  (list->tree
   (union-set (tree->list-1 b1)
              (tree->list-1 b2))))


;;     (tree->list-1
;;      (union-btree (list->tree [1 3 5])
;;                   (list->tree [2 4 6])))
;;     => (1 2 3 4 5 6)


(defn intersection-btree
  [b1 b2]
  (list->tree
   (intersection-set (tree->list-1 b1)
                     (tree->list-1 b2))))


;;     (tree->list-1
;;       (intersection-btree (list->tree [1 3 5])
;;                           (list->tree [2 4 6])))
;;     => ()

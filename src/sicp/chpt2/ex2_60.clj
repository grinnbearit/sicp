(ns sicp.chpt2.ex2-60
  (:use [sicp.chpt2.ex2-59 :only [element-of-set?]]))


;;; with either representation, `element-of-set?` works the same way and doesn't need to be reimplemented


;;; `ajoin-set` no longer needs to check membership

(defn adjoin-set
  [x s]
  (conj s x))

;;     (adjoin-set 2 [1 2 3 1])
;;     => [1 2 3 1 2]

;;     (adjoin-set 4 [1 2 3 1])
;;     => [1 2 3 1 4]


;;; `intersection-set` doesn't change since it's built on top of `element-of-set?`

(defn intersection-set
  [s1 s2]
  (cond (or (empty? s1) (empty? s2))
        []

        (element-of-set? (first s1) s2)
        (conj (intersection-set (rest s1) s2) (first s1))

        :else
        (recur (rest s1) s2)))

;;     (intersection-set [1 2 3] [4 5 6])
;;     => []

;;     (intersection-set [1 2 3] [3 4 5])
;;     => [3]

;;; without removing duplicates, a union of 2 sets is simply their concatenation

(defn union-set
  [s1 s2]
  (concat s1 s2))

;;     (union-set [1 2 3] [4 5 6])
;;     => [1 2 3 4 5 6]

;;     (union-set [1 2 3] [3 4 5])
;;     => [1 2 3 3 4 5]


;;; the alternative implementation of `union-set` using `adjoin-set` would not need to be reimplemented,
;;; the implementation detail of duplicates is contained in `adjoin-set`


(defn union-set
  [s1 s2]
  (reduce #(adjoin-set %2 %1) s1 s2))

;;     (union-set [1 2 3] [4 5 6])
;;     => [1 2 3 4 5 6]

;;     (union-set [1 2 3] [3 4 5])
;;     => [1 2 3 3 4 5]

;;; for applications where a set is required but duplicates are rare and the most common operation is union
;;; this implementation is much more performant, `adjoin-set` is now an \\(O(1)\\) operation while `union-set`
;;; depends on the implementation of `concat` which is at worst \\(O(n)\\)

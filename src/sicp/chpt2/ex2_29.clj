(ns sicp.chpt2.ex2-29)


(defn make-mobile
  [left right]
  (list left right))


(defn make-branch
  [length structure]
  (list length structure))


;;; __a. selectors__


(defn left-branch
  [mobile]
  (first mobile))


(defn right-branch
  [mobile]
  (second mobile))


(defn branch-length
  [branch]
  (first branch))


(defn branch-structure
  [branch]
  (second branch))


;;; __b. mobile weights__


(defn mobile?
  [structure]
  (not (number? structure)))


(declare total-weight)


(defn branch-weight
  [branch]
  (if (mobile? (branch-structure branch))
    (total-weight (branch-structure branch))
    (branch-structure branch)))


(defn total-weight
  [mobile]
  (+ (branch-weight (left-branch mobile))
     (branch-weight (right-branch mobile))))


;;      (total-weight
;;       (make-mobile (make-branch 1
;;                                 (make-mobile
;;                                  (make-branch 1 10)
;;                                  (make-branch 1 10)))
;;                    (make-branch 1
;;                                 (make-mobile
;;                                  (make-branch 1 10)
;;                                  (make-branch 2 20)))))
;;      => 50


;;; __c. balanced?__


(defn torque
  [branch]
  (* (branch-length branch)
     (branch-weight branch)))


(declare balanced?)


(defn balanced-branch?
  [branch]
  (if (mobile? (branch-structure branch))
    (balanced? (branch-structure branch))
    true))


(defn balanced?
  [mobile]
  (and (= (torque (left-branch mobile))
          (torque (right-branch mobile)))

       (balanced-branch? (left-branch mobile))

       (balanced-branch? (right-branch mobile))))


;;      (balanced? (make-mobile (make-branch 3
;;                                           (make-mobile
;;                                            (make-branch 1 10)
;;                                            (make-branch 1 10)))
;;                              (make-branch 1 60)))
;;      => true


;;      (balanced? (make-mobile (make-branch 1 20)
;;                              (make-branch 1 (make-mobile
;;                                              (make-branch 2 10)
;;                                              (make-branch 1 10)))))
;;      => false


;;; __d. abstraction__

;;; since Clojure doesn't have `pair` (can't `cons` onto a non `seq`), using `vector` instead


(defn make-mobile
  [left right]
  (vector left right))


(defn make-branch
  [length structure]
  (vector length structure))

;;; since a `vector` is a `seq`, nothing else needs to be changed, `first` and `second` just work

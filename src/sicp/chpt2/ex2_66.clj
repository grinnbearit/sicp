(ns sicp.chpt2.ex2-66
  (:refer-clojure :exclude [key])
  (:use [sicp.chpt2.ex2-63 :only [make-tree left-branch right-branch entry]]))


;;; This is similar to `element-of-set?` where the set was a balanced
;;; binary tree except here the tree entries are also compound objects

(defrecord Record [key data])


(defn make-record
  [key data]
  (Record. key data))


(defn key
  [record]
  (.key record))


(defn data
  [record]
  (.data record))


(defn lookup
  [given-key set-of-records]
  (cond (nil? set-of-records)
        nil

        (< given-key (key (entry set-of-records)))
        (recur given-key (left-branch set-of-records))

        (> given-key (key (entry set-of-records)))
        (recur given-key (right-branch set-of-records))

        :else
        (entry set-of-records)))


;;     (def record-tree
;;       (make-tree (make-record 4 :four)
;;                  (make-tree (make-record 3 :three)
;;                             (make-tree (make-record 1 :one)
;;                                        nil
;;                                        nil)
;;                             (make-tree (make-record 2 :two)
;;                                        nil
;;                                        nil))
;;                  (make-tree (make-record 6 :six)
;;                             (make-tree (make-record 5 :five)
;;                                        nil
;;                                        nil)
;;                             (make-tree (make-record 7 :seven)
;;                                        nil
;;                                        nil))))

;;     (lookup 3 record-tree)
;;     => #sicp.chpt2.ex2_66.Record{:key 3, :data :three}


;;     (lookup 0 record-tree)
;;     => nil

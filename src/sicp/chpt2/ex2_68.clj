(ns sicp.chpt2.ex2-68
  (:use [sicp.chpt2.ex2-59 :only [element-of-set?]]
        [sicp.chpt2.ex2-67 :only [left-branch right-branch leaf? symbols sample-tree]]))


(defn encode-symbol
  [sym tree]
  (loop [path [] branch tree]
    (cond (leaf? branch)
          path

          (element-of-set? sym (symbols (left-branch branch)))
          (recur (conj path 0) (left-branch branch))

          (element-of-set? sym (symbols (right-branch branch)))
          (recur (conj path 1) (right-branch branch))

          :else
          (throw (RuntimeException. (str "Symbol not in tree, " sym))))))


(defn encode
  [message tree]
  (if (empty? message)
    []
    (concat (encode-symbol (first message) tree)
            (encode (rest message) tree))))


;;     (encode '(A D A B B C A) sample-tree)
;;     => (0 1 1 0 0 1 0 1 0 1 1 1 0)

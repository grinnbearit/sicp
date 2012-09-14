(ns sicp.chpt2.ex2-67)


;;; Clojure has better alternatives for dynamic dispatch, [protocols](http://clojure.org/protocols)


(defprotocol CodeNode
  (symbols [this])
  (weight [this]))


(defrecord Leaf [sym w]
  CodeNode
  (symbols [this]
    [(.sym this)])
  (weight [this]
    (.w this)))


(defn make-leaf
  [symbol weight]
  (Leaf. symbol weight))


(defn leaf?
  [this]
  (= (class this) Leaf))


(defrecord CodeTree [left right syms w]
  CodeNode
  (symbols [this]
    (.syms this))
  (weight [this]
    (.w this)))


(defn make-code-tree
  [left right]
  (CodeTree. left
             right
             (concat (symbols left)
                     (symbols right))
             (+ (weight left)
                (weight right))))


(defn left-branch
  [tree]
  (.left tree))


(defn right-branch
  [tree]
  (.right tree))


;;; Both `Leaf` and `CodeTree` have their own implementations of `weight` and `symbols`
;;; which are part of the `CodeNode` protocol, no conditional switching in the code required


(defn choose-branch
  [bit branch]
  (cond (zero? bit)
        (left-branch branch)

        (= 1 bit)
        (right-branch branch)

        :else
        (throw (RuntimeException. (str "Invalid bit, " bit)))))


(defn decode
  [bits tree]
  (letfn [(decode-1 [bs current-branch]
            (if (empty? bs)
              ()
              (let [next-branch (choose-branch (first bs) current-branch)]
                (if (leaf? next-branch)
                  (conj (decode-1 (rest bs) tree)
                        (first (symbols next-branch)))
                  (recur (rest bs) next-branch)))))]

    (decode-1 bits tree)))


;;; __example__


(def sample-tree
  (make-code-tree (make-leaf 'A 4)
                  (make-code-tree
                   (make-leaf 'B 2)
                   (make-code-tree (make-leaf 'D 1)
                                   (make-leaf 'C 1)))))


(def sample-message
  [0 1 1 0 0 1 0 1 0 1 1 1 0])


;;     (decode sample-message sample-tree)
;;     => (A D A B B C A)

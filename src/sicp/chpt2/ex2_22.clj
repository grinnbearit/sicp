(ns sicp.chpt2.ex2-22)


;;; using loop instead of an anonymous fn

(defn square-list
  [items]
  (loop [things items answer ()]
    (if (empty? things)
      answer
      (recur (rest things)
             (conj answer
                   (#(* % %) (first things)))))))


;;; Clojure (and most lisps, append to the front of a list when consing,
;;; the list is processed in order but each successive result is appended
;;; to the start of `answer` reversing it

(defn square-list
  [items]
  (loop [things items answer ()]
    (if (empty? things)
      answer
      (recur (rest things)
             (conj (#(* % %) (first things))
                   answer)))))


;;; Clojure doesn't have a concept of pairs, you can only `cons` onto a sequence
;;; so this throws an exception. Even if we had pairs, we'd end up with a deeply nested
;;; result, but it would be in the same order as the input.

;;; __Solutions__

;;; There are a few ways to get around this

;;; Using `reverse` in the final step of the iteration solves this issue but it ends up traversing the input twice

;;; Another solution is to use Clojure's `vector` datatype instead of the `list`, using `conj` on a vector appends
;;; to the end which maintains order

;;;     (conj [] 1 2 3)
;;;     => [1 2 3]

;;;     (conj () 1 2 3)
;;;     => (3 2 1)

;;; initializing answer with `[]` instead of `()` will keep order

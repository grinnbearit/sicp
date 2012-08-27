(ns sicp.chpt2.ex2-54)


(defn equal?
  [l1 l2]
  (cond
   (and (symbol? l1)
        (symbol? l2))
   (= l1 l2)

   (or (symbol? l1)
       (symbol? l2))
   false

   (and (empty? l1)
        (empty? l2))
   true

   (or (empty? l1)
       (empty? l2))
   false

   :else
   (and (equal? (first l1) (first l2))
        (recur (rest l1) (rest l2)))))

;;     (equal? '((a b c) d (e f g))
;;             '((a b c) d (e f g)))
;;     => true

;;     (equal? '(a b)
;;             '(a b c))
;;     => false

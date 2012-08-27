(ns sicp.chpt2.ex2-53)

;;; `car` is the same as `first` while `cdr` is the same as `rest`, `cadr` can be replaced by `(comp first rest)`

(defn memq
  [s l]
  (cond
   (empty? l)
   false

   (= s (first l))
   l

   :else
   (recur s (rest l))))


;;     (list 'a 'b 'c)
;;     => (a b c)

;;     (list (list 'george))
;;     => ((george))

;;     (rest '((x1 x2) (y1 y2)))
;;     => ((y1 y2))

;;     ((comp first rest) '((x1 x2) (y1 y2)))
;;     => (y1 y2)

;;     (coll? (first '(a short list)))
;;     => false

;;     (memq 'red '((red shoes) (blue socks)))
;;     => false

;;     (memq 'red '(red shoes blue socks))
;;     => (red shoes blue socks)

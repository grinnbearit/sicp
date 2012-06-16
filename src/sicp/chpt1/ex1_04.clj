(ns sicp.chpt1.ex1-04)


;;; if \\(b>0\\) then the conditional resolves to `+` returning `(+ a b)`

;;; if \\(b \leq 0\\) then  the conditional resolves to `-` returning `(- a b)`


(defn a-plus-abs-b
  [a b]
  ((if (> b 0) + -) a b))

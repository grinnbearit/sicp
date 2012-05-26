(ns sicp.chpt1.ex1-05)


;;; In applicative order, the arguments are evaluated before being passed
;;; into the function. The argument `y` resolves to `(p)` which causes an infinite loop or a stack overflow

;;; Normal order, resolves `x` since its used first and terminates, returning 0 without ever resolving
;;; `y`

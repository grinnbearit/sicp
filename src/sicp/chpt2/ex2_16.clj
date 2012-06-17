(ns sicp.chpt2.ex2-16)


;;; Regular algebra assumes that identities always have a fixed value,
;;; that's why identical identites can cancel each other out perfectly

;;; With intervals that's not the case. The value is not precise and so normal
;;; algebraic simplifications can't work.

;;; I don't think it is possible to design an interval-arithmetic library
;;; that does not have this problem.

;;; What we can do is to express our equations with no standard algebraic assumptions
;;; like \\(\frac{n}{n}, n \neq 0 \rightarrow 1\\).

;;; This will minimize the error

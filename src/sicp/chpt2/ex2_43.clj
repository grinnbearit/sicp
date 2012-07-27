(ns sicp.chpt2.ex2-43)


;;; Both algorithms are recursive

;;; The first algorithm is linearly recursive, every iteration calls `queen-cols` once, so for
;;; an \\(n \times n\\) board, the runtime is \\(O(n)\\)


;;; The second algorithm is tree recursive, with `queen-cols` being called for every row in every iteration,
;;; so for an \\(n \times n\\) board, the runtime is \\(O(n^n)\\)

;;; If the first algorithm runs in time \\(T\\), the second algorithm will complete after time \\(T^T\\)

(ns sicp.chpt2.ex2-36
  (:use [sicp.chpt2.ex2-33 :only [accumulate]]))


(defn accumulate-n
  [op init seqs]
  (if (empty? (first seqs))
    ()
    (cons (accumulate op init (map first seqs))
          (accumulate-n op init (map rest seqs)))))


;;      (accumulate-n + 0 [[1 2 3] [4 5 6] [7 8 9] [10 11 12]])
;;      => (22 26 30)

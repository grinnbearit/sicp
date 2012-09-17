(ns sicp.chpt2.ex2-70
  (:use [sicp.chpt2.ex2-67 :only [make-leaf decode]]
        [sicp.chpt2.ex2-68 :only [encode]]
        [sicp.chpt2.ex2-69 :only [generate-huffman-tree]]))


(def rock-tree
  (generate-huffman-tree [(make-leaf 'A 2)
                          (make-leaf 'BOOM 1)
                          (make-leaf 'GET 2)
                          (make-leaf 'JOB 2)
                          (make-leaf 'NA 16)
                          (make-leaf 'SHA 3)
                          (make-leaf 'YIP 9)
                          (make-leaf 'WAH 1)]))


;;     (encode '[GET A JOB
;;               SHA NA NA NA NA NA NA NA NA
;;               GET A JOB
;;               SHA NA NA NA NA NA NA NA NA
;;               WAH YIP YIP YIP YIP YIP YIP YIP YIP YIP
;;               SHA BOOM]
;;             rock-tree)
;;     => (0 0 0 0 1 0 0 0  0 0 0 0 1 0 0 0
;;         0 1 1 1 1 1 1 1  1 1 0 0 0 0 1 0
;;         0 0 0 0 0 0 1 0  0 0 0 1 1 1 1 1
;;         1 1 1 1 0 0 1 1  1 0 1 0 1 0 1 0
;;         1 0 1 0 1 0 1 0  1 0 1 0 0 0 1 0
;;         0 1 1 0)

;; With the huffman tree, the encoded message length is 84 bits. With a fixed
;; length code you'd need 3 bits to uniquely represent all 8 words and the total
;; message length would be 108 bits

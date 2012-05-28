(ns sicp.chpt1.ex1-12)


;;; The recursion can be expressed as

;;; * \\(pascal(row,1) = 1\\)
;;; * \\(pascal(row,column) = 1, row = column\\)
;;; * \\(pascal(row,column) = pascal(row-1,column-1) + pascal(row-1,column)\\)

;;; where `row` is the row number and `column` the column number


(defn pascal
  "Returns the entry in Pascal's triangle given by the coordinates (row, column)"
  [row column]
  (if (or (= 1 column)
          (= row column))
    1
    (+ (pascal (dec row) (dec column)) (pascal (dec row) column))))


(comment
  (for [x (range 1 5) y (range 1 (inc x))]
    (pascal x y)))

;;;     => (1 1 1 1 2 1 1 3 3 1)

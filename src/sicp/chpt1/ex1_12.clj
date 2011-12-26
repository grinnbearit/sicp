(ns sicp.chpt1.ex1-12)


(defn row
  "Given a position in Pascal's triangle (numbered left to right, top to bottom)
returns the row number"
  [n]
  (nth (mapcat #(repeat % %) (range 1 (inc n))) (dec n)))


(defn pascal-n
  "Returns the nth entry in Pascal's triangle"
  [n]
  (let [row-n (row n)
        x-pos (- n row-n)
        y-pos (inc (- n row-n))]
    (if (or (zero? x-pos) (not= (row x-pos) (row y-pos)))
      1
      (+ (pascal-n x-pos) (pascal-n y-pos)))))

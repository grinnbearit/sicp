(ns sicp.chpt2.ex2-42)


;;; __scaffolding__


(defrecord Position [col row])


(defn make-position
  [col row]
  (Position. col row))


(defn column-value
  [position]
  (.col position))


(defn row-value
  [position]
  (.row position))


;;; __implementation__


(def empty-board ())


(defn adjoin-position
  [row k positions]
  (conj positions (make-position (dec k) row)))


(defn attacks?
  [p1 p2]
  (or (= (row-value p1) (row-value p2))
      (= (column-value p1) (column-value p2))
      (= (- (row-value p1) (column-value p1))     ; along right diagonal
         (- (row-value p2) (column-value p2)))
      (= (+ (row-value p1) (column-value p1))     ; along left diagonal
         (+ (row-value p2) (column-value p2)))))


(defn safe?
  [k positions]
  (not (some (partial attacks? (first positions))
             (rest positions))))


(defn queens
  [board-size]
  (letfn [(queen-cols
            [k]
            (if (zero? k)
              [empty-board]
              (filter (fn [positions] (safe? k positions))
                      (mapcat
                       (fn [rest-of-queens]
                         (map (fn [new-row]
                                (adjoin-position new-row k rest-of-queens))
                              (range board-size)))
                       (queen-cols (dec k))))))]
    (queen-cols board-size)))


;;; __using `for`__


(defn queens
  [board-size]
  (letfn [(queen-cols
            [k]
            (if (zero? k)
              [empty-board]
              (for [positions (queen-cols (dec k))
                    new-row (range board-size)
                    :when (safe? k (adjoin-position new-row k positions))]
                (adjoin-position new-row k positions))))]
    (queen-cols board-size)))


;;; __ascii art__


(defn place-pieces
  [board-size positions]
  (reduce (fn [board position]
            (assoc-in board [(row-value position) (column-value position)] :q))
          (vec (repeat board-size (vec (repeat board-size :-))))
          positions))


(defn print-board
  [board]
  (doseq [row board]
    (println row)))


;;      (doseq [positions (queens 4)]
;;        (println)
;;        (print-board (place-pieces 4 positions))
;;        (println))

;;      # [:- :- :q :-]
;;      # [:q :- :- :-]
;;      # [:- :- :- :q]
;;      # [:- :q :- :-]


;;      # [:- :q :- :-]
;;      # [:- :- :- :q]
;;      # [:q :- :- :-]
;;      # [:- :- :q :-]
;;
;;      => nil

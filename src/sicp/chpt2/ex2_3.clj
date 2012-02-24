(ns sicp.chpt2.ex2-3)


(defrecord Point [x y])


(defn make-point
  [x y]
  (Point. x y))


(defn x-point
  [p]
  (.x p))


(defn y-point
  [p]
  (.y p))


(defrecord Segment [p1 p2])


(defn make-segment
  [p1 p2]
  (Segment. p1 p2))


(defn start-segment
  [s]
  (.p1 s))


(defn end-segment
  [s]
  (.p2 s))


;;; For the sake of simplicity, assume the sides of the rectangle always perpendicular to the coordinate axes

;;; storing only the diagonal, for a general rectangle, the angle to the base would also be needed.
(defrecord Rectangle [diag])


(defn make-rectangle
  [diag]
  (Rectangle. diag))


;;; calculating the other points whenever rectangle-points is called
(defn rectangle-points
  [rect]
  (let [diag (.diag rect)
        a (start-segment diag)
        c (end-segment diag)
        b (make-point (x-point a)
                      (y-point c))
        d (make-point (x-point c)
                      (y-point a))]
    [a b c d]))


(defn distance
  "Returns the Euclidean distance between 2 points"
  [p1 p2]
  (letfn [(sqr [x]
            (* x x))]

    (Math/sqrt (+ (sqr (- (y-point p2)
                          (y-point p1)))
                  (sqr (- (x-point p2)
                          (x-point p1)))))))


(defn perimeter
  [rect]
  (let [[a b c d] (rectangle-points rect)]
    (* 2 (+ (distance a b)
            (distance b c)))))


(defn area
  [rect]
  (let [[a b c] (rectangle-points rect)]
    (* (distance a b)
       (distance b c))))

(comment
  (let [rect (make-rectangle (make-segment (make-point 1 1)
                                           (make-point 0 0)))]
    {:perimeter (perimeter rect)        ; => 4.0
     :area (area rect)}))               ; => 1.0


;;; Changing how rectangle is stored to store all 4 points
(defrecord Rectangle [a b c d])


(defn make-rectangle
  [diag]
  (let [a (start-segment diag)
        c (end-segment diag)
        b (make-point (x-point a)
                      (y-point c))
        d (make-point (x-point c)
                      (y-point a))]
    (Rectangle. a b c d)))


;;; calculating the other points whenever rectangle-points is called
(defn rectangle-points
  [rect]
  [(.a rect) (.b rect) (.c rect) (.d rect)])


(comment
  (let [rect (make-rectangle (make-segment (make-point 1 1)
                                           (make-point 0 0)))]
    {:perimeter (perimeter rect)        ; => 4.0
     :area (area rect)}))               ; => 1.0


;;; The trade off here is between memory and performance.
;;; The first structure stored the minimum required data but needed to calculate all 4 points everytime rectangle-points was called.
;;; The second approach calculated all 4 points just once but needed to store double the data.

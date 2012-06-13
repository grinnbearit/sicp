(ns sicp.chpt2.ex2-02)


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


(defn midpoint
  [s]
  (let [p1 (start-segment s)
        p2 (end-segment s)
        x1 (x-point p1)
        y1 (y-point p1)
        x2 (x-point p2)
        y2 (y-point p2)]
    (make-point (/ (+ x1 x2) 2)
                (/ (+ y1 y2) 2))))


;;;     (midpoint (make-segment (make-point 0 0)
;;;                             (make-point 2 2)))
;;;     => #sicp.chpt2.ex2_02.Point{:x 1, :y 1}

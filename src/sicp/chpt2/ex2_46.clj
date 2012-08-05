(ns sicp.chpt2.ex2-46)


(defrecord Vector [x y])


(defn make-vect
  [x y]
  (Vector. x y))


(defn xcor-vect
  [v]
  (.x v))


(defn ycor-vect
  [v]
  (.y v))


;;     (xcor-vect (make-vect 3 4))
;;     => 3


;;     (ycor-vect (make-vect 3 4))
;;     => 4


(defn add-vect
  [v1 v2]
  (make-vect (+ (xcor-vect v1)
                (xcor-vect v2))
             (+ (ycor-vect v1)
                (ycor-vect v2))))


;;     (add-vect (make-vect 3 4)
;;               (make-vect 5 6))
;;     => #sicp.chpt2.ex2_46.Vector{:x 8, :y 10}


(defn sub-vect
  [v1 v2]
  (make-vect (- (xcor-vect v1)
                (xcor-vect v2))
             (- (ycor-vect v1)
                (ycor-vect v2))))


;;     (sub-vect (make-vect 5 6)
;;               (make-vect 3 4))
;;     => #sicp.chpt2.ex2_46.Vector{:x 2, :y 2}


(defn scale-vect
  [c v]
  (make-vect (* c (xcor-vect v))
             (* c (ycor-vect v))))


;;     (scale-vect 5 (make-vect 3 4))
;;     => #sicp.chpt2.ex2_46.Vector{:x 15, :y 20}

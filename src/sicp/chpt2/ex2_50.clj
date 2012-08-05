(ns sicp.chpt2.ex2-50
  (:use [sicp.chpt2.ex2-46 :only [make-vect
                                  sub-vect]]
        [sicp.chpt2.ex2-47 :only [make-frame]]
        [sicp.chpt2.ex2-49 :only [frame-coord-map
                                  identity-frame
                                  wave-painter
                                  render]]))


(defn transform-painter
  [painter origin corner1 corner2]
  (fn [frame]
    (let [m (frame-coord-map frame)
          new-origin (m origin)]
      (painter
       (make-frame new-origin
                   (sub-vect (m corner1) new-origin)
                   (sub-vect (m corner2) new-origin))))))


(defn flip-vert
  [painter]
  (transform-painter painter
                     (make-vect 0 1)
                     (make-vect 1 1)
                     (make-vect 0 0)))


(defn flip-horiz
  [painter]
  (transform-painter painter
                     (make-vect 1 0)
                     (make-vect 0 0)
                     (make-vect 1 1)))


(defn rotate90
  [painter]
  (transform-painter painter
                     (make-vect 0 1)
                     (make-vect 0 0)
                     (make-vect 1 1)))


(defn rotate180
  [painter]
  (transform-painter painter
                     (make-vect 1 1)
                     (make-vect 0 1)
                     (make-vect 1 0)))


(defn rotate270
  [painter]
  (transform-painter painter
                     (make-vect 1 0)
                     (make-vect 1 1)
                     (make-vect 0 0)))


;;; __alternative implementations__


(def rotate180
  (comp rotate90 rotate90))


(def rotate270
  (comp rotate180 rotate90))


;;; `flip-vert`

;;     (render ((flip-vert wave-painter) identity-frame))

;;; `flip-horiz`

;;     (render ((flip-horiz wave-painter) identity-frame))

;;; `rotate90`

;;     (render ((rotate90 wave-painter) identity-frame))

;;; `rotate180`

;;     (render ((rotate180 wave-painter) identity-frame))

;;; `rotate270`

;;     (render ((rotate270 wave-painter) identity-frame))

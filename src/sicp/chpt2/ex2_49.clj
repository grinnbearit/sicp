(ns sicp.chpt2.ex2-49
  (:use [sicp.chpt2.ex2-46 :only [add-vect
                                  scale-vect
                                  make-vect
                                  xcor-vect
                                  ycor-vect]]
        [sicp.chpt2.ex2-47 :only [make-frame
                                  origin-frame
                                  edge1-frame
                                  edge2-frame]]
        [sicp.chpt2.ex2-48 :only [make-segment
                                  start-segment
                                  end-segment]]
        [seesaw.graphics :only [draw line style]])
  (:require [seesaw.core :as ss]))


;;; [seesaw](https://github.com/daveray/seesaw) is a swing wrapper for Clojure
;;; and made the picture language a lot simpler to implement


;;; There are a few differences from the SICP version

;;; Applying a `painter` to a `frame` returns a `renderer` which is a function which takes a [canvas](http://docs.oracle.com/javase/1.4.2/docs/api/java/awt/Canvas.html)
;;; and a [graphics](http://docs.oracle.com/javase/1.4.2/docs/api/java/awt/Graphics.html) object
;;; as arguments. This function can be attached to a `seesaw.core/canvas` and is used whenever it needs to be redrawn.

;;; Since the `renderer` is redrawn whenever the canvas is resized, it scales automatically to fill all available space


(defn frame-coord-map
  [frame]
  (fn [v]
    (add-vect (origin-frame frame)
              (add-vect (scale-vect (xcor-vect v)
                                    (edge1-frame frame))
                        (scale-vect (ycor-vect v)
                                    (edge2-frame frame))))))


(def identity-frame
  (make-frame (make-vect 0 0)
              (make-vect 1 0)
              (make-vect 0 1)))


(defn project-frame
  "Projects the vectors of a frame to match a rectangle of different dimensions"
  [frame x-axis y-axis]
  (make-frame (make-vect (* x-axis (xcor-vect (origin-frame frame)))
                         (* y-axis (ycor-vect (origin-frame frame))))
              (scale-vect x-axis (edge1-frame frame))
              (scale-vect y-axis (edge2-frame frame))))


(defn segment->line
  "Converts a `segment` into a `seesaw.graphics/line`"
  ([segment]
     (segment->line identity-frame segment))
  ([frame segment]
     (let [translate (frame-coord-map frame)
           start (translate (start-segment segment))
           end (translate (end-segment segment))]
       (line (xcor-vect start) (ycor-vect start)
             (xcor-vect end) (ycor-vect end)))))


(defn segments->painter
  [segment-list]
  (fn [frame]
    (fn [c g]
      (let [style (style :foreground :black :stroke 1)
            projected (project-frame frame (.getWidth c) (.getHeight c))]
        (doseq [segment segment-list]
          (draw g (segment->line projected segment) style))))))



(defn render
  "Initializes a resizable `seesaw.core/frame` with the `renderer` as the  paint function"
  [renderer]
  (ss/frame :width 200
            :height 200
            :visible? true
            :content (ss/border-panel
                      :center (ss/canvas
                               :background "#ffffff"
                               :paint renderer))))


(defn chain
  "Returns a new `renderer` which draws all passed renderers in sequence"
  [& renderers]
  (fn [c g]
    (doseq [r renderers]
      (r c g))))



(def outline-painter
  (segments->painter [(make-segment (make-vect 0 0)
                                    (make-vect 0 1))
                      (make-segment (make-vect 0 1)
                                    (make-vect 1 1))
                      (make-segment (make-vect 1 1)
                                    (make-vect 1 0))
                      (make-segment (make-vect 1 0)
                                    (make-vect 0 0))]))


(def x-painter
  (segments->painter [(make-segment (make-vect 0 0)
                                    (make-vect 1 1))
                      (make-segment (make-vect 0 1)
                                    (make-vect 1 0))]))


(def diamond-painter
  (segments->painter [(make-segment (make-vect 0.5 0)
                                    (make-vect 1 0.5))
                      (make-segment (make-vect 1 0.5)
                                    (make-vect 0.5 1))
                      (make-segment (make-vect 0.5 1)
                                    (make-vect 0 0.5))
                      (make-segment (make-vect 0 0.5)
                                    (make-vect 0.5 0))]))


(def wave-painter
  (segments->painter [;; head
                      (make-segment (make-vect 0.4 0)
                                    (make-vect 0.3 0.2))
                      (make-segment (make-vect 0.3 0.2)
                                    (make-vect 0.4 0.4))
                      (make-segment (make-vect 0.6 0)
                                    (make-vect 0.7 0.2))
                      (make-segment (make-vect 0.7 0.2)
                                    (make-vect 0.6 0.4))

                      ;; legs
                      (make-segment (make-vect 0.4 1)
                                    (make-vect 0.5 0.8))
                      (make-segment (make-vect 0.6 1)
                                    (make-vect 0.5 0.8))
                      (make-segment (make-vect 0.2 1)
                                    (make-vect 0.3 0.8))
                      (make-segment (make-vect 0.8 1)
                                    (make-vect 0.7 0.8))

                      ;; left arm
                      (make-segment (make-vect 0 0.2)
                                    (make-vect 0.2 0.4))
                      (make-segment (make-vect 0 0.4)
                                    (make-vect 0.2 0.6))


                      ;; right arm
                      (make-segment (make-vect 1 0.8)
                                    (make-vect 0.8 0.6))
                      (make-segment (make-vect 1 0.6)
                                    (make-vect 0.8 0.4))

                      ;; body
                      (make-segment (make-vect 0.2 0.4)
                                    (make-vect 0.4 0.4))
                      (make-segment (make-vect 0.6 0.4)
                                    (make-vect 0.8 0.4))
                      (make-segment (make-vect 0.2 0.6)
                                    (make-vect 0.3 0.6))
                      (make-segment (make-vect 0.8 0.6)
                                    (make-vect 0.7 0.6))
                      (make-segment (make-vect 0.3 0.6)
                                    (make-vect 0.3 0.8))
                      (make-segment (make-vect 0.7 0.6)
                                    (make-vect 0.7 0.8))]))


;;; `outline-painter`

;;     (render (outline-painter identity-frame))

;;; `x-painter`

;;     (render (x-painter identity-frame))

;;; `diamond-painter`

;;     (render (diamond-painter identity-frame))

;;; `wave-painter`

;;     (render (wave-painter identity-frame))

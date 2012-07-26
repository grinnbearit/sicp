(ns sicp.chpt2.ex2-11
  (:use [sicp.chpt2.ex2-07 :only [make-interval
                                  lower-bound
                                  upper-bound]]))


(defn mul-interval
  [x y]
  (let [lbx (lower-bound x)
        lby (lower-bound y)
        ubx (upper-bound x)
        uby (upper-bound y)]
    (cond (and (pos? lbx) (pos? lby))
          (make-interval (* lbx lby) (* ubx uby))

          (and (neg? lbx) (neg? lby) (neg? ubx) (neg? uby))
          (make-interval (* ubx uby) (* lbx lby))

          (and (pos? lbx) (neg? lby) (pos? ubx) (neg? uby))
          (make-interval (* ubx lby) (* lbx uby))

          (and (neg? lbx) (pos? lby) (neg? ubx) (pos? uby))
          (make-interval (* lbx uby) (* ubx lby))

          (and (pos? lbx) (neg? lby) (pos? ubx) (pos? uby))
          (make-interval (* ubx lby) (* ubx uby))

          (and (neg? lbx) (pos? lby) (pos? ubx) (pos? uby))
          (make-interval (* lbx uby) (* ubx uby))

          (and (neg? lbx) (neg? lby) (neg? ubx) (pos? uby))
          (make-interval (* lbx uby) (* lbx lby))

          (and (neg? lbx) (neg? lby) (pos? ubx) (neg? uby))
          (make-interval (* ubx lby) (* lbx uby))

          :else
          (let [l1 (* lbx uby)
                l2 (* ubx lby)
                u1 (* lbx lby)
                u2 (* ubx uby)]
            (make-interval (if (< l1 l2) l1 l2)
                           (if (< u1 u2) u2 u1))))))

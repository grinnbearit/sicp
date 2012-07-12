(ns sicp.chpt2.ex2-23)


(defn for-each
  ([f coll]
     (for-each f coll nil))
  ([f coll return]
     (loop [things coll]
       (if (empty? things)
         return
         (do (f (first things))
             (recur (rest things)))))))


;;     (for-each println [1 2 3 4 5] true)
;;     # 1
;;     # 2
;;     # 3
;;     # 4
;;     # 5
;;     => true


;;; __Other Implementations__


(defn for-each
  ([f coll]
     (for-each f coll nil))
  ([f coll return]
     (doseq [x coll]
       (f x))
     return))

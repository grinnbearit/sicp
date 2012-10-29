(ns sicp.chpt2.ex2-74
  (:refer-clojure :exclude [get])
  (:use [sicp.chpt2.ex2-73 :only [get put]]))


;; defining `apply-generic`


(defn apply-generic
  [op & args]
  (apply (get op (type (first args))) args))


;; Each file would need to store the type information of how its data is structured. For
;; this solution, I'm using clojure records to store both the type information and the data


;; `EmployeeList` stores records in a list of vectors with the format [name record]

(defrecord EmployeeList [employees])


(defn get-record-list
  [employee-list name]
  (some (fn [[n rec]]
          (when (= n name)
            rec))
        (.employees employee-list)))

;; `EmployeeMap` stores records in a hash-map with employee names as keys and the records as values

(defrecord EmployeeMap [employees])


(defn get-record-map
  [employee-map name]
  ((.employees employee-map) name))


;; Installing `get-record` for both types

;;     (put 'get-record EmployeeList #'get-record-list)
;;     (put 'get-record EmployeeMap #'get-record-map)


(defn get-record
  [employees name]
  (apply-generic 'get-record employees name))


;;     (defrecord AbbreviatedEmployee [n s a])
;;     (defrecord DetailedEmployee [name salary address])


;;     (def employee-a
;;       (AbbreviatedEmployee. "Alpha" 100 "Argentina"))


;;     (def employee-b
;;       (AbbreviatedEmployee. "Bravo" 200 "Brazil"))


;;     (def employee-c
;;       (DetailedEmployee. "Charlie" 300 "Chile"))


;;     (def employee-list
;;       (EmployeeList. [["Alpha" employee-a]
;;                   ["Bravo" employee-b]]))

;;     (def employee-map
;;       (EmployeeMap. {"Charlie" employee-c}))

;;     (get-record employee-list "Alpha")
;;     => #sicp.chpt2.ex2_74.AbbreviatedEmployee{:n "Alpha", :s 100, :a "Argentina"}

;;     (get-record employee-map "Charlie")
;;     => #sicp.chpt2.ex2_74.DetailedEmployee{:name "Charlie", :salary 300, :address "Chile"}


(defn get-salary-abbr
  [employee]
  (.s employee))


(defn get-salary-detailed
  [employee]
  (.salary employee))

;; Installing `get-salary` for both types

;;     (put 'get-salary AbbreviatedEmployee #'get-salary-abbr)
;;     (put 'get-salary DetailedEmployee #'get-salary-detailed)


(defn get-salary
  [employee]
  (apply-generic 'get-salary employee))


;;     (get-salary employee-b)
;;     => 200


;;     (get-salary employee-c)
;;     => 300


(defn find-employee-record
  [files name]
  (some #(get-record % name) files))


;;     (find-employee-record [employee-list employee-map] "Bravo")
;;     => #sicp.chpt2.ex2_74.AbbreviatedEmployee{:n "Bravo", :s 200, :a "Brazil"}

;;     (find-employee-record [employee-list employee-map] "Charlie")
;;     => #sicp.chpt2.ex2_74.DetailedEmployee{:name "Charlie", :salary 300, :address "Chile"}


;; When Insatiable takes over a new company, the company must expose functions which map to Insatiable's employee
;; functions.

;; These functions can then be installed just like `get-employees-map` and `get-employees-list`

;; __alternative implementation__

;; Multimethods would work here too, with a dispatch function on type

;; When you need to dispatch on type, Clojure provides an alternative, more efficient dispatch mechanism,
;; [protocols](http://clojure.org/protocols)


(defprotocol EmployeeFile
  (get-record [this name]))


(extend-type EmployeeList
  EmployeeFile
  (get-record [this name]
    (get-record-list this name)))


(extend-type EmployeeMap
  EmployeeFile
  (get-record [this name]
    (get-record-map this name)))


;;     (get-record employee-list "Alpha")
;;     => #sicp.chpt2.ex2_74.AbbreviatedEmployee{:n "Alpha", :s 100, :a "Argentina"}

;;     (get-record employee-map "Charlie")
;;     => #sicp.chpt2.ex2_74.DetailedEmployee{:name "Charlie", :salary 300, :address "Chile"}


(defprotocol Employee
  (get-salary [this]))


(extend-type AbbreviatedEmployee
  Employee
  (get-salary [this]
    (.s this)))


(extend-type DetailedEmployee
  Employee
  (get-salary [this]
    (.salary this)))


;;     (get-salary employee-b)
;;     => 200


;;     (get-salary employee-c)
;;     => 300

;; `find-employee-record` requires no changes

;;     (find-employee-record [employee-list employee-map] "Bravo")
;;     => #sicp.chpt2.ex2_74.AbbreviatedEmployee{:n "Bravo", :s 200, :a "Brazil"}

;;     (find-employee-record [employee-list employee-map] "Charlie")
;;     => #sicp.chpt2.ex2_74.DetailedEmployee{:name "Charlie", :salary 300, :address "Chile"}

;; In this case, all the new company would have to do is extend the `EmployeeFile` protocol to their file implementation
;; and the `Employee` protocol to their record implementation

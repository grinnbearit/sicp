(ns sicp.chpt1.ex1-14)


;;; Expansion of count-change
;;; Indentation indicates recursive calls


(count-change 11)
(cc 11 5)
  (cc 11 4)
    (cc 11 3)
      (cc 11 2)
        (cc 11 1)
          (cc 11 0)
          (cc 10 1)
            (cc 10 0)
            (cc 9 1)
              (cc 9 0)
              (cc 8 1)
                (cc 8 0)
                (cc 7 1)
                  (cc 7 0)
                  (cc 6 1)
                    (cc 6 0)
                    (cc 5 1)
                      (cc 5 0)
                      (cc 4 1)
                        (cc 4 0)
                        (cc 3 1)
                          (cc 3 0)
                          (cc 2 1)
                            (cc 2 0)
                            (cc 1 1)
                              (cc 1 0)
                              (cc 0 1)
        (cc 6 2)
          (cc 6 1)
            (cc 6 0)
            (cc 5 1)
              (cc 5 0)
              (cc 4 1)
                (cc 4 0)
                (cc 3 1)
                  (cc 3 0)
                  (cc 2 1)
                    (cc 2 0)
                    (cc 1 1)
                      (cc 1 0)
                      (cc 0 1)
          (cc 1 2)
            (cc 1 1)
              (cc 1 0)
              (cc 0 1)
            (cc -4 2)
      (cc 1 3)
        (cc 1 2)
          (cc 1 1)
            (cc 1 0)
            (cc 0 1)
          (cc -4 2)
        (cc -9 3)
    (cc -14 4)
  (cc -39 5)


;;; Each call to cc results in 2 more calls to cc, also the amount drops by 1 when 'kinds of coins' is 1
;;; Therefore I'm making the assumption (without rigorous proof) that the upper bound time complexity is O(2^Amt)

;;; The space complexity is O(Amt) since the maximum number of recursive calls is when 'kinds of coins' is 1
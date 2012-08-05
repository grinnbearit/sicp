(defproject sicp "0.1.0"
  :description "#SICP in Clojure

These are my solutions to the exercises
from Structure and Interpretation of Computer Programs

All feedback welcome especially if you see something I could have made
clearer.

I hope you find this useful and have as much fun working through
these as I did"
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [org.clojure/tools.trace "0.7.3"]
                 [seesaw "1.4.2"]]
  :plugins [[lein-marginalia "0.7.1"]
            [lein-swank "1.4.4"]]
  :marginalia {:javascript ["mathjax/MathJax.js?config=default"]})

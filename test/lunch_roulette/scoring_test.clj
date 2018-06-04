(ns lunch-roulette.scoring-test
  (:require [lunch-roulette.data :as data]
            [lunch-roulette.scoring :refer :all]
            [midje.sweet :refer :all]))

(defn group [restaurant]
  {:people     []
   :restaurant restaurant})

(def fulana-key :fulana.silva)
(def fulana (fulana-key data/people))

(def beltrana-key :beltrana.souza)
(def beltrana (beltrana-key data/people))

(def siclana-key :siclana.jesus)
(def siclana (siclana-key data/people))

(facts "on score"
  (fact "vegan/vegetarian/gluten free need -1000000 for incompatible restaurants"
    (score siclana (group :almost-green-house)) => data/incompatible-score
    (score siclana (group :green-with-gluten)) => data/incompatible-score
    (score beltrana (group :old-burger)) => data/incompatible-score))

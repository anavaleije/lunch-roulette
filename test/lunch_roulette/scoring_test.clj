(ns lunch-roulette.scoring-test
  (:require [lunch-roulette.data :as data]
            [lunch-roulette.scoring :refer :all]
            [midje.sweet :refer :all]))

(defn group
  ([restaurant]
   (group [] restaurant))
  ([people restaurant]
   {:people     people
    :restaurant restaurant}))

(def fulana-key :fulana.silva)
(def fulana (fulana-key data/people))

(def beltrana-key :beltrana.souza)
(def beltrana (beltrana-key data/people))

(def siclana-key :siclana.jesus)
(def siclana (siclana-key data/people))

(facts "on score"
  (fact "vegan/vegetarian/gluten free return `incompatible` score"
    (score siclana (group :almost-green-house)) => data/incompatible-score
    (score siclana (group :green-with-gluten)) => data/incompatible-score
    (score beltrana (group :old-burger)) => data/incompatible-score)
  (fact "disliked restaurants also return `incompatible` score"
    (score fulana (group :old-burger)) => data/incompatible-score)
  (fact "a group with more people allocated has a smaller score"
    (< (score fulana (group [:beltrana.souza] :pasta-mix))
       (score fulana (group [] :pasta-mix)))
    => true))

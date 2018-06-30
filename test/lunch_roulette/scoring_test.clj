(ns lunch-roulette.scoring-test
  (:require [lunch-roulette.data :as data]
            [lunch-roulette.scoring :refer :all]
            [midje.sweet :refer :all]))

(def past-events
  [{:date   "2018-05-08"
    :groups [{:people     [:siclana.jesus :beltrana.souza :siclano.ajesus]
              :restaurant :green-house}
             {:people     [:fulana.silva :fulano.silva :beltrano.souza]
              :restaurant :old-burger}]}
   {:date   "2018-05-15"
    :groups [{:people     [:siclana.jesus :fulano.silva :siclano.jesus]
              :restaurant :green-house}
             {:people     [:fulana.silva :beltrana.souza :beltrano.souza]
              :restaurant :pasta-mix}]}])

(facts "on people-frequency"
  (people-frequency :fulana.silva past-events)
  => {:beltrana.souza 1 :beltrano.souza 2 :fulana.silva 2 :fulano.silva 1})

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
    (score siclana (group :almost-green-house) []) => data/incompatible-score
    (score siclana (group :green-with-gluten) []) => data/incompatible-score
    (score beltrana (group :old-burger) []) => data/incompatible-score)

  (fact "disliked restaurants also return `incompatible` score"
    (score fulana (group :old-burger) []) => data/incompatible-score)

  (fact "a group with more people allocated has a smaller score"
    (< (score fulana (group [:someone] :pasta-mix) [])
       (score fulana (group [] :pasta-mix) []))
    => true)

  (fact "a group with people someone has already had lunch with has a smaller score"
    (< (score fulana (group [:beltrano.souza] :pasta-mix) past-events)
       (score fulana (group [:fulano.silva] :pasta-mix) past-events))
    => true))

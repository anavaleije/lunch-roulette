(ns lunch-roulette.scoring-test
  (:require [lunch-roulette.aux :as aux]
            [lunch-roulette.scoring :refer :all]
            [midje.sweet :refer :all]))

(facts "on people-frequency"
  (people-frequency :fulana.silva aux/past-events)
  => {:beltrana.souza 1 :beltrano.souza 2 :fulana.silva 2 :fulano.silva 1})

(defn group
  ([restaurant]
   (group [] restaurant))
  ([people restaurant]
   {:people     people
    :restaurant restaurant}))

(facts "on score"

  (fact "vegan/vegetarian/gluten free return `incompatible` score"
        (score aux/siclana (group :almost-green-house) aux/env) => incompatible-score
        (score aux/siclana (group :green-with-gluten) aux/env) => incompatible-score
        (score aux/beltrana (group :old-burger) aux/env) => incompatible-score)

  (fact "disliked restaurants also return `incompatible` score"
        (score aux/fulana (group :old-burger) aux/env) => incompatible-score)

  (fact "a group with more people allocated has a smaller score"
    (< (score aux/fulana (group [:someone] :pasta-mix) aux/env)
       (score aux/fulana (group [] :pasta-mix) aux/env))
    => true)

  (fact "a group with people someone has already had lunch with has a smaller score"
    (< (score aux/fulana (group [:beltrano.souza] :pasta-mix) aux/env)
       (score aux/fulana (group [:fulano.silva] :pasta-mix) aux/env))
    => true))

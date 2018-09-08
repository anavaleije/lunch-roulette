(ns lunch-roulette.sampling-test
  (:require [lunch-roulette.sampling :refer :all]
            [lunch-roulette.aux :as aux]
            [midje.sweet :refer :all]))

(def env (assoc aux/env :n-groups 4))

(facts "on restaurants-frequency"
  (restaurants-frequency (:restaurants env) (:past-events env))
  => {:green-house        2
      :old-burger         1
      :pasta-mix          1
      :almost-green-house 0
      :green-with-gluten  0})

(facts "on sample-restaurant-groups"
  (sample-restaurant-groups env)
  => {:almost-green-house {:people [] :restaurant :almost-green-house}
      :green-with-gluten  {:people [] :restaurant :green-with-gluten}
      :old-burger         {:people [] :restaurant :old-burger}
      :pasta-mix          {:people [] :restaurant :pasta-mix}})

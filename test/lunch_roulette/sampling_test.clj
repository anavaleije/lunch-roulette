(ns lunch-roulette.sampling-test
  (:require [lunch-roulette.sampling :refer :all]
            [midje.sweet :refer :all]))

(def restaurants
  {:green-house        {:id          :green-house
                        :name        "Green House"}
   :almost-green-house {:id          :almost-green-house
                        :name        "Almost Green House"}
   :green-with-gluten  {:id          :green-with-gluten
                        :name        "Green With Gluten"}
   :pasta-mix          {:id          :pasta-mix
                        :name        "Pasta Mix"}
   :old-burger         {:id          :old-burger
                        :name        "Old Burger"}})

(def past-events
  [{:date   "2018-05-08"
    :groups [{:people     [:siclana :beltrana :siclano]
              :restaurant :green-house}
             {:people     [:fulana :fulano :beltrano]
              :restaurant :old-burger}]}
   {:date   "2018-05-15"
    :groups [{:people     [:siclana :fulano :siclano]
              :restaurant :green-house}
             {:people     [:fulana :beltrana :beltrano]
              :restaurant :pasta-mix}]}])

(facts "on restaurants-frequency"
  (restaurants-frequency restaurants past-events) => {:green-house        2
                                                      :old-burger         1
                                                      :pasta-mix          1
                                                      :almost-green-house 0
                                                      :green-with-gluten  0})

(facts "on sample-restaurant-groups"
  (let [groups (sample-restaurant-groups 4 (range 0 11))
        first-restaurant (-> groups keys first)]
    (count groups) => 4
    (count (set groups)) => 4
    (filter #(or (> % 10) (< % 0)) (keys groups)) => ()
    (get groups first-restaurant) => {:people     []
                                      :restaurant first-restaurant}))

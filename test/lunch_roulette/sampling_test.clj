(ns lunch-roulette.sampling-test
  (:require [lunch-roulette.sampling :refer :all]
            [midje.sweet :refer :all]))

(facts "on sample-restaurant-groups"
  (let [groups (sample-restaurant-groups 4 (range 0 11))
        first-restaurant (-> groups keys first)]
    (count groups) => 4
    (count (set groups)) => 4
    (filter #(or (> % 10) (< % 0)) (keys groups)) => ()
    (get groups first-restaurant) => {:people     []
                                      :restaurant first-restaurant}))

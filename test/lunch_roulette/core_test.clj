(ns lunch-roulette.core-test
  (:require [lunch-roulette.core :refer :all]
            [lunch-roulette.data :as data]
            [midje.sweet :refer :all]))

(facts "on sample-restaurant-groups"
       (let [groups (sample-restaurant-groups 4 (range 0 11))
             first-restaurant (-> groups keys first)]
         (count groups) => 4
         (count (set groups)) => 4
         (filter #(or (> % 10) (< % 0)) (keys groups)) => ()
         (get groups first-restaurant) => {:people []
                                           :restaurant first-restaurant}))

(def green-house-group nil)
(def groups {:green-house {:people []}
             :old-burguer {:people []}})
(def fulana-key :fulana.silva)
(def fulana (fulana-key data/people))

(fact "on allocate person"
      (allocate-person groups [fulana-key fulana]) => group-2 #_(assoc group-2 :people [person-key])
      (provided
        (score person group-1) => 1
        (score person group-2) => 2))

(fact "on score"
      (score person-key group-1) => 1)

; Integration tests
(fact "on sample-next-event-groups"
      (sample-next-event-groups) => nil)

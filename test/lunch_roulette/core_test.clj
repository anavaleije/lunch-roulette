(ns lunch-roulette.core-test
  (:require [lunch-roulette.core :refer :all]
            [midje.sweet :refer :all]))

(facts "on sample-restaurant-groups"
       (let [groups (sample-restaurant-groups 4 (range 0 11))]
         (count groups) => 4
         (count (set groups)) => 4
         (filter (fn [{:keys [restaurant]}]
                   (or (> restaurant 10) (< restaurant 0)))
                 groups) => ()
         (first groups) => (contains {:people []})))

(def person :fulano)
(def group-1 {:restaurant :1})
(def group-2 {:restaurant :2})
(def groups [group-1 group-2])

(fact "on allocate person"
      (allocate-person groups person) => group-2
      (provided
        (score person group-1) => 1
        (score person group-2) => 2))

(fact "on score"
      (score person group-1) => 1)


(fact "on sample-next-event-groups")

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
    (get groups first-restaurant) => {:people     []
                                      :restaurant first-restaurant}))

(def fulana-key :fulana.silva)
(def fulana (fulana-key data/people))

(defn group [restaurant]
  {:people     []
   :restaurant restaurant})

(def green-house-group (group :green-house))
(def old-burger-group (group :old-burger))
(def groups {:green-house green-house-group
             :old-burger  old-burger-group})

(fact "on allocate person"
  (allocate-person groups [fulana-key fulana])
  => (update-in groups [:old-burger :people] conj fulana-key)
  (provided
    (score fulana green-house-group) => 1
    (score fulana old-burger-group) => 2))

(fact "on score"
      (score person-key group-1) => 1)

; Integration tests
(fact "on sample-next-event-groups"
      (sample-next-event-groups) => nil)

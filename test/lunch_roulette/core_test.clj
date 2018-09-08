(ns lunch-roulette.core-test
  (:require [lunch-roulette.core :refer :all]
            [lunch-roulette.aux :as aux]
            [lunch-roulette.scoring :as scoring]
            [midje.sweet :refer :all]))

(defn group [restaurant]
  {:people     []
   :restaurant restaurant})

(def green-house-group (group :green-house))
(def old-burger-group (group :old-burger))
(def groups {:green-house green-house-group
             :old-burger  old-burger-group})

(def env {:restaurants aux/restaurants
          :past-events aux/past-events})

(fact "on allocate person"
      (allocate-person env groups [aux/fulana-key aux/fulana])
      => (update-in groups [:old-burger :people] conj aux/fulana-key)
      (provided
        (scoring/score aux/fulana green-house-group env) => 1
        (scoring/score aux/fulana old-burger-group env) => 2))

;
;; Integration tests
;(fact "on sample-next-event-groups"
;      (sample-next-event-groups) => nil)

(ns lunch-roulette.scoring
  (:require [lunch-roulette.data :as data]))

(defn restaurant-is-incompatible? [person restaurant]
  (->> person
       :restrictions
       (mapv restaurant)
       (some false?)))

(defn score [person group]
  (if (restaurant-is-incompatible? person (data/restaurants (:restaurant group)))
    data/incompatible-score
    0))

(ns lunch-roulette.scoring
  (:require [lunch-roulette.data :as data]))

(defn restaurant-is-incompatible? [person restaurant]
  (or
    (some #(= (:id restaurant) %) (:disliked-restaurants person))
    (->> person
        :restrictions
        (mapv restaurant)
        (some false?))))

(defn people-frequency [person past-events]
  (reduce
    (fn [m {:keys [groups]}]
      (->> (filter (fn [{:keys [people]}]
                     (some #(= person %) people)) groups)
           first
           :people
           (#(interleave % (repeat 1)))
           (apply assoc {})
           (merge-with + m)))
    {}
    past-events))

(defn score [person group]
  (if (restaurant-is-incompatible? person (data/restaurants (:restaurant group)))
    data/incompatible-score

    (-> 0
        (- (count (:people group))))))

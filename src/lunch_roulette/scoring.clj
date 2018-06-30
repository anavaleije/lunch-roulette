(ns lunch-roulette.scoring
  (:require [lunch-roulette.data :as data]))

(defn restaurant-is-incompatible? [person restaurant]
  (or
    (some #(= (:id restaurant) %) (:disliked-restaurants person))
    (->> person
        :restrictions
        (mapv restaurant)
        (some false?))))

(defn people-frequency [person-key past-events]
  (reduce
    (fn [m {:keys [groups]}]
      (some->> (filter (fn [{:keys [people]}]
                         (some #(= person-key %) people)) groups)
               first
               :people
               (#(interleave % (repeat 1)))
               (apply assoc {})
               (merge-with + m)))
    {}
    past-events))

(defn score [person group past-events]
  (if (restaurant-is-incompatible? person (data/restaurants (:restaurant group)))
    data/incompatible-score

    (let [group-size-score (count (:people group))
          people-frequency (people-frequency (:id person) past-events)
          people-score (->> group
                            :people
                            (select-keys people-frequency)
                            vals
                            (apply +))]
      (+ (- group-size-score)
         (- people-score)))))

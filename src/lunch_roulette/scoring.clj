(ns lunch-roulette.scoring)

(def incompatible-score -1000000)

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
      (let [person-groups (filter (fn [{:keys [people]}]
                                    (some #(= person-key %) people)) groups)]
        (if (seq person-groups)
          (->> person-groups
               first
               :people
               (#(interleave % (repeat 1)))
               (apply assoc {})
               (merge-with + m))
          m)))
    {}
    past-events))

(defn score [person group {:keys [restaurants past-events]}]
  (if (restaurant-is-incompatible? person (restaurants (:restaurant group)))
    incompatible-score

    (let [group-size-score (count (:people group))
          people-frequency (people-frequency (:id person) past-events)
          people-score (->> group
                            :people
                            (select-keys people-frequency)
                            vals
                            (apply +))]
      (+ (- group-size-score)
         (- people-score)))))

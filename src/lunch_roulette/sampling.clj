(ns lunch-roulette.sampling)

(defn restaurants-frequency [restaurants past-events]
  (let [initial-restaurants-freq (->> (repeat 0)
                                      (interleave (keys restaurants))
                                      (apply hash-map))
        past-events-restaurants (->> past-events
                                     (mapv :groups)
                                     flatten
                                     (mapv :restaurant))]
    (reduce
      (fn [restaurants-freq restaurant]
        (if (contains? restaurants-freq restaurant)
          (update restaurants-freq restaurant inc)
          restaurants-freq))
      initial-restaurants-freq
      past-events-restaurants)))

(defn sample-restaurant-groups [{:keys [n-groups restaurants past-events]}]
  (let [groups (->> past-events
                    (restaurants-frequency restaurants)
                    (sort-by second)
                    (keys)
                    (take n-groups)
                    (#(zipmap % (repeat n-groups {:people []}))))]
    (reduce
      (fn [groups restaurant]
        (assoc-in groups [restaurant :restaurant] restaurant))
      groups
      (keys groups))))


(comment
  (require '[lunch-roulette.data-bkp :as data-bkp])

  (->> (restaurants-frequency data-bkp/restaurants data-bkp/past-events)
       (sort-by second))
  )

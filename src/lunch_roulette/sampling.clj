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

(defn sample-restaurant-groups [n-groups restaurants]
  (let [groups (->> (shuffle restaurants)
                 (take n-groups)
                 (#(zipmap % (repeat n-groups {:people []}))))]
    (reduce
      (fn [groups restaurant]
        (assoc-in groups [restaurant :restaurant] restaurant))
      groups
      (keys groups))))

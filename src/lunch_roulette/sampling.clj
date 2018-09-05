(ns lunch-roulette.sampling)

(defn sample-restaurant-groups [n-groups restaurants]
  (let [groups (->> (shuffle restaurants)
                 (take n-groups)
                 (#(zipmap % (repeat n-groups {:people []}))))]
    (reduce
      (fn [groups restaurant]
        (assoc-in groups [restaurant :restaurant] restaurant))
      groups
      (keys groups))))

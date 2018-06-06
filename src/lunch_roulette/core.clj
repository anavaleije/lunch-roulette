(ns lunch-roulette.core
  (:require [lunch-roulette.data :as data]
            [lunch-roulette.scoring :as scoring]))

(defn sample-restaurant-groups [n-groups restaurants]
  (let [groups (->> (shuffle restaurants)
                    (take n-groups)
                    (#(zipmap % (repeat n-groups {:people []}))))]
    (reduce
      (fn [groups restaurant]
        (assoc-in groups [restaurant :restaurant] restaurant))
      groups
      (keys groups))))

(defn allocate-person [groups [person-key person]]
  (let [{:keys [restaurant]} (apply max-key (partial scoring/score person) (vals groups))]
    (update-in groups [restaurant :people] #(conj % person-key))))

(defn sample-next-event-groups []
  (let [groups (sample-restaurant-groups data/n-groups (keys data/restaurants))]
    (-> (reduce
          allocate-person
          groups
          data/people)
        vals)))

(comment
  (sample-next-event-groups))

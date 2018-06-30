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
  (let [{:keys [restaurant]} (apply max-key
                                    (partial scoring/score person data/past-events)
                                    (vals groups))]
    (update-in groups [restaurant :people] #(conj % person-key))))

(defn format-slack-message [results all-people all-restaurants]
  (->> results
       (mapv (fn [{:keys [people restaurant]}]
               (->> people
                    (mapv #(get-in all-people [% :slack]))
                    (clojure.string/join " ")
                    (str (get-in all-restaurants [restaurant :name]) " - "))))
       (clojure.string/join "\n")))

(defn sample-next-event-groups []
  (let [groups (sample-restaurant-groups data/n-groups (keys data/restaurants))
        results (-> (reduce
                      allocate-person
                      groups
                      data/people)
                    vals)]
    (println results)
    (println (format-slack-message results data/people data/restaurants))))

(comment
  (sample-next-event-groups))

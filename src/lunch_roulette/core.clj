(ns lunch-roulette.core
  (:require [lunch-roulette.data :as data]))

(def min-groups-size 4)
(def n-groups (quot (count data/people) min-groups-size))

(defn sample-restaurant-groups [n-groups restaurants]
  (->> (shuffle restaurants)
       (take n-groups)
       (mapv (fn [restaurant]
               {:people     []
                :restaurant restaurant}))))

(defn score [person group]
  (print group))

(defn allocate-person [groups person]
  (-> (apply max-key (partial score person) groups)))

(defn sample-next-event-groups []
  (let [groups (sample-restaurant-groups n-groups (keys data/restaurants))]
    (reduce
      allocate-person
      groups
      data/people)))

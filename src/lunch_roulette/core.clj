(ns lunch-roulette.core
  (:require [lunch-roulette.data :as data]))

(def min-groups-size 3)
(def n-groups (quot (count data/people) min-groups-size))

(defn tap [k]
  (println k)
  k)

(defn sample-restaurant-groups [n-groups restaurants]
  (->> (shuffle restaurants)
       (take n-groups)
       (#(zipmap % (repeat n-groups {:people []})))))

(defn score [person group]
  0)

(defn allocate-person [groups [person-key person]]
  (let [[chosen-restaurant _] (apply max-key (partial score person) groups)]
    (update-in groups [chosen-restaurant :people] #(conj % person-key))))

(defn sample-next-event-groups []
  (let [groups (sample-restaurant-groups n-groups (keys data/restaurants))]
    (reduce
      allocate-person
      groups
      data/people)))

(ns lunch-roulette.core
  (:require [lunch-roulette.data :as data]
            [lunch-roulette.sampling :as sampling]
            [lunch-roulette.scoring :as scoring]))

(defn allocate-person [env groups [person-key person]]
  (let [{:keys [restaurant]} (apply max-key
                                    #(scoring/score person % env)
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
  (let [env data/env
        groups (sampling/sample-restaurant-groups env)
        results (-> (reduce
                      (partial allocate-person env)
                      groups
                      (:people env))
                    vals)]
    (println (format-slack-message results (:people env) (:restaurants env)))
    results))

(comment
  (sample-next-event-groups))

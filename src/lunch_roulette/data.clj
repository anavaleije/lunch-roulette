(ns lunch-roulette.data)

(def people
  {:fulana.silva   {:slack                "@fulana.silva"
                    :email                "fulana.silva@uhu.com.br"
                    :restrictions         []
                    :disliked-restaurants []}
   :beltrana.souza {:slack                "@beltrana"
                    :email                "beltrana.souza@uhu.com.br"
                    :restrictions         [:vegetarian]
                    :disliked-restaurants []}
   :siclana.jesus  {:slack                "@siclana"
                    :email                "siclana.jesush@uhu.com.br"
                    :restrictions         [:gluten-free
                                           :vegan]
                    :disliked-restaurants [:pasta-mix :old-burger]}
   :fulano.silva   {:slack                "@fulano.silva"
                    :email                "fulano.silva@uhu.com.br"
                    :restrictions         []
                    :disliked-restaurants []}
   :beltrano.souza {:slack                "@beltrano"
                    :email                "beltrano.souza@uhu.com.br"
                    :restrictions         [:vegetarian]
                    :disliked-restaurants []}
   :siclano.jesus  {:slack                "@siclano"
                    :email                "siclano.jesush@uhu.com.br"
                    :restrictions         [:gluten-free
                                           :vegan]
                    :disliked-restaurants [:pasta-mix :old-burger]}})

(def restaurants
  {:green-house {:name         "Green House"
                 :address      "Rua dos Pombos, 0"
                 :vegan?       true
                 :vegetarian?  true
                 :gluten-free? true}
   :pasta-mix   {:name         "Pasta Mix"
                 :address      "Rua das Gaivotas, 0"
                 :vegan?       false
                 :vegetarian?  true
                 :gluten-free? false}
   :old-burger  {:name         "Old Burger"
                 :address      "Rua das Andorinhas, 0"
                 :vegan?       false
                 :vegetarian?  true
                 :gluten-free? true}})

(def past-events
  ["2018-05-08" [{:people     [:siclana :beltrana :siclano]
                  :restaurant :green-house}
                 {:people     [:fulana :fulano :beltrano]
                  :restaurant :old-burger}]
   "2018-05-15" [{:people     [:siclana :fulano :siclano]
                  :restaurant :green-house}
                 {:people     [:fulana :beltrana :beltrano]
                  :restaurant :pasta-mix}]])
































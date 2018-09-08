(ns lunch-roulette.aux)

(def people
  {:fulana.silva   {:id                   :fulana.silva
                    :slack                "@fulana.silva"
                    :email                "fulana.silva@uhu.com.br"
                    :restrictions         []
                    :disliked-restaurants [:old-burger]}
   :beltrana.souza {:id                   :beltrana.souza
                    :slack                "@beltrana"
                    :email                "beltrana.souza@uhu.com.br"
                    :restrictions         [:vegetarian]
                    :disliked-restaurants []}
   :siclana.jesus  {:id                   :siclana.jesus
                    :slack                "@siclana"
                    :email                "siclana.jesush@uhu.com.br"
                    :restrictions         [:gluten-free
                                           :vegan]
                    :disliked-restaurants [:pasta-mix :old-burger]}
   :fulano.silva   {:id                   :fulano.silva
                    :slack                "@fulano.silva"
                    :email                "fulano.silva@uhu.com.br"
                    :restrictions         []
                    :disliked-restaurants [:old-burger]}
   :beltrano.souza {:id                   :beltrano.souza
                    :slack                "@beltrano"
                    :email                "beltrano.souza@uhu.com.br"
                    :restrictions         [:vegetarian]
                    :disliked-restaurants []}
   :siclano.jesus  {:id                   :siclano.jesus
                    :slack                "@siclano"
                    :email                "siclano.jesush@uhu.com.br"
                    :restrictions         [:gluten-free
                                           :vegan]
                    :disliked-restaurants [:pasta-mix :old-burger]}})

(def restaurants
  {:green-house        {:id          :green-house
                        :name        "Green House"
                        :address     "Rua dos Pombos, 0"
                        :vegan       true
                        :vegetarian  true
                        :gluten-free true}
   :almost-green-house {:id          :almost-green-house
                        :name        "Almost Green House"
                        :address     "Rua dos Pombos, 1"
                        :vegan       false
                        :vegetarian  true
                        :gluten-free true}
   :green-with-gluten  {:id          :green-with-gluten
                        :name        "Green With Gluten"
                        :address     "Rua dos Pombos, 1"
                        :vegan       true
                        :vegetarian  true
                        :gluten-free false}
   :pasta-mix          {:id          :pasta-mix
                        :name        "Pasta Mix"
                        :address     "Rua das Gaivotas, 0"
                        :vegan       false
                        :vegetarian  true
                        :gluten-free false}
   :old-burger         {:id          :old-burger
                        :name        "Old Burger"
                        :address     "Rua das Andorinhas, 0"
                        :vegan       false
                        :vegetarian  false
                        :gluten-free true}})

(def past-events
  [{:date   "2018-05-08"
    :groups [{:people     [:siclana.jesus :beltrana.souza :siclano.jesus]
              :restaurant :green-house}
             {:people     [:fulana.silva :fulano.silva :beltrano.souza]
              :restaurant :old-burger}]}
   {:date   "2018-05-15"
    :groups [{:people     [:siclana.jesus :fulano.silva :siclano.jesus]
              :restaurant :green-house}
             {:people     [:fulana.silva :beltrana.souza :beltrano.souza]
              :restaurant :pasta-mix}]}])

(def fulana-key :fulana.silva)
(def fulana (fulana-key people))

(def beltrana-key :beltrana.souza)
(def beltrana (beltrana-key people))

(def siclana-key :siclana.jesus)
(def siclana (siclana-key people))

(def env {:restaurants restaurants
          :people      people
          :past-events past-events})

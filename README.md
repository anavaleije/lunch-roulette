# lunch_roulette

A Clojure library designed to  randomly generate lunch groups, sampling both people and
restaurants. One possible application is to sample groups to go have lunch at work, to
improve teams integration. Lunch roulette samples the groups from the following data:

- a list of people with their preferences and restrictions;
- a list of restaurants with some characteristics (vegan, vegetarian, gluten-free)
- and a historical sampling, so it can avoid repeating people and restaurants.

## Usage

First, you need to define your `data.clj` to reflect your colleagues and restaurants. Once this is set, you can just `lein run` and it'll sample the next lunch groups. It'll print two blocks of information:
  - A message to be sent in Slack
  - A list to be manually added to `past-events` in your `data.clj`

## License

Copyright © 2018

Distributed under the Eclipse Public License either version 1.0.

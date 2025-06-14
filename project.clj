(defproject org.clojars.jj/iso-countries "1.2.2-SNAPSHOT"
  :description "iso-countries is a Clojure library designed to provide easy access to comprehensive ISO 3166-1 country data.

  This library aims to simplify the process of retrieving country-specific information, including official names,
  alpha-2 and alpha-3 codes, numeric codes, regional classifications, and unicode flags."

  :url "https://github.com/ruroru/iso-countries"
  :license {:name "EPL-2.0"
            :url  "https://www.eclipse.org/legal/epl-2.0/"}
  :dependencies [[org.clojure/clojure "1.12.1"]]

  :deploy-repositories [["clojars" {:url      "https://repo.clojars.org"
                                    :username :env/clojars_user
                                    :password :env/clojars_pass}]]

  :plugins [[org.clojars.jj/bump "1.0.4"]
            [org.clojars.jj/bump-md "1.0.0"]
            [org.clojars.jj/strict-check "1.0.2"]])

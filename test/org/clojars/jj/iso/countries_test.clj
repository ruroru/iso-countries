(ns org.clojars.jj.iso.countries-test
  (:require [clojure.java.io]
            [clojure.test :refer [deftest is are]]
            [clojure.spec.alpha :as s]
            [org.clojars.jj.iso.countries :as countries]))

(s/def ::name string?)
(s/def ::alpha-2 string?)
(s/def ::alpha-3 string?)
(s/def ::region string?)
(s/def ::flag string?)
(s/def ::numeric string?)
(s/def ::region-code string?)
(s/def ::country
  (s/keys :req-un [::name ::alpha-2 ::alpha-3 ::region ::flag ::numeric ::region-code]))
(s/def ::country-map (s/map-of keyword? ::country))

(deftest get-all-countries
  (let [countries (countries/get-all-countries)]
    (s/valid? ::country-map countries)
    (is (= 249 (count countries)))))

(deftest get-country
  (are [country-name expected] (= expected (countries/get-country country-name))
                               :sweden
                               {:alpha-2     "SE"
                                :alpha-3     "SWE"
                                :name        "Sweden"
                                :flag        "🇸🇪"
                                :numeric     "752"
                                :region      "Europe"
                                :region-code "150"}
                               :cocos-keeling-islands
                               {:name        "Cocos (Keeling) Islands",
                                :alpha-2     "CC",
                                :region      "Oceania",
                                :flag        "🇨🇨"
                                :numeric     "166",
                                :alpha-3     "CCK",
                                :region-code "009"}
                               ))

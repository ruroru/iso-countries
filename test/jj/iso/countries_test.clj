(ns jj.iso.countries-test
  (:require [clojure.java.io]
            [clojure.test :refer [deftest is are]]
            [clojure.spec.alpha :as s]
            [jj.iso.countries :as countries]))

(s/def ::name string?)
(s/def ::alpha-2 string?)
(s/def ::alpha-3 string?)
(s/def ::region keyword?)
(s/def ::flag string?)
(s/def ::numeric string?)
(s/def ::region-code string?)
(s/def ::country
  (s/keys :req-un [::name ::alpha-2 ::alpha-3 ::region ::flag ::numeric ::region-code]))
(s/def ::country-map (s/map-of keyword? ::country))

(deftest get-all-countries
  (let [countries (countries/get-all-countries)]
    (is (s/valid? ::country-map countries))
    (is (= 249 (count countries)))))

(deftest get-country
  (are [country-name expected] (= expected (countries/get-country country-name))
                               :sweden
                               {:alpha-2       "SE"
                                :alpha-3       "SWE"
                                :flag          "🇸🇪"
                                :name          "Sweden"
                                :numeric       "752"
                                :official-name "Sweden"
                                :region        :europe
                                :region-code   "150"}
                               :cocos-keeling-islands
                               {:alpha-2       "CC"
                                :alpha-3       "CCK"
                                :flag          "🇨🇨"
                                :name          "Cocos (Keeling) Islands"
                                :numeric       "166"
                                :official-name "Cocos (Keeling) Islands"
                                :region        :oceania
                                :region-code   "009"}))


(deftest alpha-2-to-name
  (are [country-name expected] (= expected (countries/alpha-2->name country-name))
                               "se" :sweden
                               "DE" :germany
                               "Dm" :dominica
                               "AA" nil                     ;; not existing
                               ))


(deftest alpha-3-to-name
  (are [country-name expected] (= expected (countries/alpha-3->name country-name))
                               "swe" :sweden
                               "COG" :congo
                               "PLW" :palau
                               "AAA" nil                     ;; not existing
                               ))


(deftest detect-country
  (are [country-name expected] (= expected (countries/detect-country country-name))
                               "swe" :sweden
                               "DE" :germany
                               "PLW" :palau
                               "AAA" nil                     ;; not existing
                               ))
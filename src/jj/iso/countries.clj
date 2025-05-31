(ns jj.iso.countries
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.io FileNotFoundException InputStreamReader PushbackReader)
           (java.net URL)))


(defn get-all-countries []
  (let [edn-file "jj/countries.edn"]
    (if-let [url (io/resource edn-file)]
      (let [edn (edn/read (PushbackReader. (InputStreamReader. (.openStream ^URL url))))]
        (into {}
              (map (fn [[k v]]
                     (let [flag (apply str
                                       (map #(String. (Character/toChars
                                                        (+ 0x1F1E6 (- (int (Character/toUpperCase ^char %)) (int \A)))))
                                            (:alpha-2 v)))]
                       [k (assoc v :flag flag)]))
                   edn)))
      (throw (FileNotFoundException. (format "Resource not found: %s" edn-file))))))


(defn get-country [country]
  "Returns all data related to a  country

  for example:
  (get-country :tonga)
  =>  {
  :alpha-2     \"TG\"
  :alpha-3     \"TGO\"
  :flag        \"🇹🇬\"
  :name        \"Togo\"
  :numeric     \"768\"
  :region      \"Africa\"
  :region-code 002}
  "
  ((get-all-countries) country))

(defn alpha-2->name [country-name]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-2->name \"SE\") =>  :sweden"
  (let [upper-case-name ^String (-> country-name
                                    str/upper-case
                                    )]

    (let [country (-> (filter (fn [[key value]]

                                (.equals upper-case-name (value :alpha-2)))
                              (get-all-countries))
                      first)]

      (when country
        (first country)))))

(defn alpha-3->name [country-name]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-3->name \"SWE\") =>  :sweden"
  (let [upper-case-name ^String (-> country-name
                                    str/upper-case
                                    )]

    (let [country (-> (filter (fn [[key value]]

                                (.equals upper-case-name (value :alpha-3)))
                              (get-all-countries))
                      first)]

      (when country
        (first country)))))

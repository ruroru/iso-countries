(ns jj.iso.countries
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
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
  =>  {:alpha-2     \"TG\"
 :alpha-3     \"TGO\"
 :flag        \"🇹🇬\"
 :name        \"Togo\"
 :numeric     \"768\"
 :region      \"Africa\"
 :region-code 002}
  "
  ((get-all-countries) country))

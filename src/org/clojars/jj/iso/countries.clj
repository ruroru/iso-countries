(ns org.clojars.jj.iso.countries
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io])
  (:import (java.io FileNotFoundException InputStreamReader PushbackReader)
           (java.net URL)))


(defn- get-countries []
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


(def ^:private  countries (atom {}))


(defn get-all-countries []
  (get-countries))

(defn get-country
  ([country]
   (if (contains? @countries country)
     (@countries country)
     (let [country-info ((get-all-countries) country)]
       (when country-info
         (reset! countries (assoc @countries country country-info))
         country-info
         )))))
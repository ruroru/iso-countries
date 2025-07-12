(ns jj.iso.countries
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.io FileNotFoundException InputStreamReader PushbackReader)
           (java.net URL)
           (java.util WeakHashMap)))

(def ^:private all-cache ^WeakHashMap (WeakHashMap. 1))
(def ^:private cache ^WeakHashMap (WeakHashMap. 256))

(defn get-all-countries []
  (when (not (.containsKey ^WeakHashMap all-cache :all))
    (let [edn-file "jj/countries.edn"]
      (if-let [url (io/resource edn-file)]
        (let [edn (edn/read (PushbackReader. (InputStreamReader. (.openStream ^URL url))))]
          (.put ^WeakHashMap all-cache :all (into {}
                                                  (map (fn [[k v]]
                                                         (let [flag (apply str
                                                                           (map #(String. (Character/toChars
                                                                                            (+ 0x1F1E6 (- (int (Character/toUpperCase ^char %)) (int \A)))))
                                                                                (:alpha-2 v)))]
                                                           [k (assoc v :flag flag)]))
                                                       edn))))
        (throw (FileNotFoundException. (format "Resource not found: %s" edn-file))))))
  (when (.containsKey ^WeakHashMap all-cache :all)
    (.get ^WeakHashMap all-cache :all)))

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

(defn alpha-2->name [alpha2-code]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-2->name \"SE\") =>  :sweden"
  (let [upper-case-name ^String (-> alpha2-code
                                    str/upper-case)]
    (when (not (.containsKey ^WeakHashMap cache (keyword upper-case-name)))
      (doseq [country (get-all-countries)]
        (.put ^WeakHashMap cache (keyword (:alpha-2 (second country))) (first country))))
    (.get ^WeakHashMap cache (keyword upper-case-name))))

(defn alpha-3->name [alpha3-code]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-3->name \"SWE\") =>  :sweden"
  (let [upper-case-name ^String (-> alpha3-code
                                    str/upper-case)]
    (when (not (.containsKey ^WeakHashMap cache (keyword upper-case-name)))
      (doseq [country (get-all-countries)]
        (.put ^WeakHashMap cache (keyword (:alpha-3 (second country))) (first country))))
    (.get ^WeakHashMap cache (keyword upper-case-name))))

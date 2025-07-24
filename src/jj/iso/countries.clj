(ns jj.iso.countries
  (:require [clojure.edn :as edn]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:import (java.io InputStream InputStreamReader PushbackReader)
           (java.util WeakHashMap)
           (java.util.concurrent ConcurrentHashMap)))

(def ^:private temporary-cache ^WeakHashMap (WeakHashMap. 1))
(def ^:private permanent-cache ^ConcurrentHashMap (ConcurrentHashMap. 1))

(defn get-all-countries []
  (let [all-countries (.get ^WeakHashMap temporary-cache :all)]
    (if (some? all-countries)
      all-countries
      (with-open [is ^InputStream (.openStream (io/resource "jj/countries.edn"))
                  isr ^InputStreamReader (InputStreamReader. is)
                  pr ^PushbackReader (PushbackReader. isr)]
        (let [computed-countries
              (let [edn (edn/read pr)
                    all-countries (into {}
                                        (map (fn [[k v]]
                                               (let [flag (apply str
                                                                 (map #(String. (Character/toChars
                                                                                  (+ 0x1F1E6 (- (int (Character/toUpperCase ^char %)) (int \A)))))
                                                                      (:alpha-2 v)))]
                                                 [k (assoc v :flag flag)]))
                                             edn))]
                all-countries)]
          (locking temporary-cache
            (if-let [current-cached-value (.get ^WeakHashMap temporary-cache :all)]
              current-cached-value
              (do
                (.put ^WeakHashMap temporary-cache :all computed-countries)
                computed-countries)))))
      )))


(defn get-country [country]
  "Returns all data related to a country

  for example:
  (get-country :tonga)
  => {
  :alpha-2 \"TG\"
  :alpha-3 \"TGO\"
  :flag \"🇹🇬\"
  :name \"Togo\"
  :numeric \"768\"
  :region \"Africa\"
  :region-code 002}  "
  ((get-all-countries) country))

(defn alpha-2->name [alpha2-code]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-2->name \"SE\") => :sweden"
  (let [upper-case-name (-> alpha2-code
                            str/upper-case)
        key (-> upper-case-name
                keyword)]
    (when (not (.containsKey ^ConcurrentHashMap permanent-cache key))
      (let [country (filter (fn [country]
                              (= upper-case-name (:alpha-2 (second country))))
                            (get-all-countries))]
        (if (not (empty? country))
          (.put ^ConcurrentHashMap permanent-cache key (-> country first first)))))
    (.get ^ConcurrentHashMap permanent-cache key)))

(defn alpha-3->name [alpha3-code]
  "Returns country name as a keyword, if country is found, else returns nil

  for example:
  (alpha-3->name \"SWE\") => :sweden"
  (let [upper-case-name (-> alpha3-code
                            str/upper-case)
        key (-> upper-case-name
                keyword)]
    (when (not (.containsKey ^ConcurrentHashMap permanent-cache key))
      (let [country (filter (fn [country]
                              (= upper-case-name (:alpha-3 (second country))))
                            (get-all-countries))]
        (if (not (empty? country))
          (.put ^ConcurrentHashMap permanent-cache key (-> country first first)))))
    (.get ^ConcurrentHashMap permanent-cache key)))

(defn detect-country [country-code]
  "Returns country name as a keyword, for a given alpha2 or alpha3 code. If none found returns nil

  for example:
  (detect-country \"SWE\") => :sweden
  (detect-country \"se\") => :sweden"
  (let [upper-case-name ^String (-> country-code
                                    str/upper-case)
        key (keyword upper-case-name)]
    (when (not (.contains ^ConcurrentHashMap permanent-cache key))
      (let [all-countries (get-all-countries)
            country-result (first
                             (filter (fn [[_ v]]
                                       (or
                                         (= upper-case-name (:alpha-2 v))
                                         (= upper-case-name (:alpha-3 v))))
                                     all-countries))]
        (when country-result
          (.put ^ConcurrentHashMap permanent-cache key (first country-result)))))
    (.get ^ConcurrentHashMap permanent-cache key)))

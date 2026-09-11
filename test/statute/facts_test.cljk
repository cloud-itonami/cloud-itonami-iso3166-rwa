(ns statute.facts-test
  (:require [kotoba.lang.text :as str]
            [clojure.test :refer [deftest is]]
            [statute.facts :as facts]))

(deftest rwa-has-spec-basis
  (let [sb (facts/spec-basis "RWA")]
    (is (= 3 (count sb)))
    (is (every? #(or (str/starts-with? (:statute/url %) "https://businessprocedures.rdb.rw/")
                      (str/starts-with? (:statute/url %) "https://risa.gov.rw/")
                      (str/starts-with? (:statute/url %) "https://www.mifotra.gov.rw/"))
                sb))
    (is (every? :statute/law-number sb))))

(deftest unknown-jurisdiction-has-no-spec-basis
  (is (nil? (facts/spec-basis "ATL")))
  (is (nil? (facts/spec-basis "ZZZ"))))

(deftest coverage-is-honest
  (let [c (facts/coverage ["RWA" "JPN" "ATL"])]
    (is (= 3 (:requested c)))
    (is (= 1 (:covered c)))
    (is (= ["ATL" "JPN"] (:missing-jurisdictions c)))))

(deftest by-topic-filters
  (is (= ["rwa.labour-law-66-2018"]
         (mapv :statute/id (facts/by-topic "RWA" :labor))))
  (is (= ["rwa.data-protection-law-058-2021"]
         (mapv :statute/id (facts/by-topic "RWA" :privacy))))
  (is (= ["rwa.companies-act-007-2021"]
         (mapv :statute/id (facts/by-topic "RWA" :incorporation))))
  (is (empty? (facts/by-topic "RWA" :environment)))
  (is (empty? (facts/by-topic "ATL" :labor))))

(ns marketentry.facts "Rwanda market-entry catalog.")
(def catalog
  {"RWA" {:name "Rwanda"
          :owner-authority "RPPA / Umucyo e-Procurement"
          :legal-basis "Law on Public Procurement"
          :national-spec "Umucyo supplier registration + RDB"
          :provenance "https://www.umucyo.gov.rw/"
          :required-evidence ["RDB registration record" "Umucyo registration record" "TIN record" "Authorized-representative record"]
          :rep-owner-authority "contracting authorities / RPPA"
          :rep-legal-basis "Rwandan legal entity (RDB) typically required for public awards"
          :rep-provenance "https://www.umucyo.gov.rw/"
          :corporate-number-owner-authority "RDB / RRA"
          :corporate-number-legal-basis "Company registration / TIN"
          :corporate-number-provenance "https://rdb.rw/"}
   "USA" {:name "United States" :owner-authority "GSA/SAM.gov" :legal-basis "FAR" :national-spec "SAM.gov" :provenance "https://sam.gov/"
          :required-evidence ["EIN record" "SAM.gov registration record" "State business registration record" "SAM UEI verification record"]}
   "KEN" {:name "Kenya" :owner-authority "PPRA" :legal-basis "PPADA" :national-spec "IFMIS" :provenance "https://www.ppra.go.ke/"
          :required-evidence ["KRA PIN" "IFMIS registration" "BRS extract" "Authorized-representative record"]}
   "UGA" {:name "Uganda" :owner-authority "PPDA" :legal-basis "PPDA Act" :national-spec "e-GP" :provenance "https://www.ppda.go.ug/"
          :required-evidence ["URSB record" "e-GP registration" "TIN record" "Authorized-representative record"]}})

(defn spec-basis [iso3] (get catalog iso3))
(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s) missing (remove catalog iso3s)]
     {:requested (count iso3s) :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note "R0 catalog seed"})))
(defn required-evidence-satisfied? [iso3 submitted]
  (when-let [{:keys [required-evidence]} (spec-basis iso3)]
    (= (count required-evidence) (count (filter (set submitted) required-evidence)))))
(defn evidence-checklist [iso3] (:required-evidence (spec-basis iso3) []))
(defn rep-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:rep-owner-authority sb)
      (select-keys sb [:rep-owner-authority :rep-legal-basis :rep-provenance]))))
(defn corporate-number-spec-basis [iso3]
  (when-let [sb (spec-basis iso3)]
    (when (:corporate-number-owner-authority sb)
      (select-keys sb [:corporate-number-owner-authority :corporate-number-legal-basis :corporate-number-provenance]))))

(ns statute.facts
  "General-law compliance catalog for Rwanda (RWA) -- extends this repo's
  existing `marketentry.facts` (public-procurement market-entry only,
  narrow scope: RPPA/Umucyo e-Procurement, RDB registration) with a
  second, orthogonal catalog of statutes a company operating in Rwanda
  must generally track for compliance. Mirrors
  cloud-itonami-iso3166-ken/-bdi/-zaf/-cmr/-gmb/-tgo/-zwe/-mwi/-nga/-egy/-ssd/-som's
  `statute.facts` (ADR-2607141700, cloud-itonami-compliance-fact-
  federation).

  All three entries below are directly WebFetch+pdftotext-verified
  against the bilingual/trilingual (Kinyarwanda/English/French) Official
  Gazette PDF text itself -- each PDF's own Article One ('Purpose of
  this Law') was read, not a secondary summary:

  - Companies Act: Law N° 007/2021 of 05/02/2021 governing companies,
    Official Gazette n° 04 ter of 08/02/2021, hosted directly on
    businessprocedures.rdb.rw -- the SAME Rwanda Development Board (RDB)
    already cited as this repo's `marketentry.facts` corporate-number
    authority ('RDB / RRA'), so this is that institution's own laws
    page, not a new/different source. Article One (read directly):
    'This Law governs companies.' Article 3 names the 'Registrar
    General' (RDB's own company-registry office) as the incorporation
    authority, consistent with `marketentry.facts`'s existing RDB
    citation. Amended by Law N° 019/2023 of 30/03/2023 (Official Gazette
    n° Special Bis of 30/03/2023), independently verified by reading the
    amending Act's own text directly (hosted by KIFC -- Kigali
    International Financial Centre, an RDB Group entity); recorded in
    `:statute/last-revised-date`.
  - Data Protection Law: Law N° 058/2021 of 13/10/2021 relating to the
    protection of personal data and privacy, Official Gazette n° Special
    of 15/10/2021, hosted directly on risa.gov.rw (Rwanda Information
    Society Authority). Article One (read directly): 'This Law aims at
    the protection of personal data and privacy and determines their
    processing.' The law's own Article 3(23°) defines the 'supervisory
    authority' as 'a public authority in charge of cyber security' --
    i.e. the National Cyber Security Authority (NCSA); the Data
    Protection & Privacy Office (dpo.gov.rw) operates as the
    NCSA-designated data-protection directorate under that definition
    (dpo.gov.rw's own TLS certificate could not be verified this
    iteration -- 'unable to verify the first certificate' -- so this
    catalog cites the law's own risa.gov.rw-hosted PDF instead of
    dpo.gov.rw directly). No independent evidence of a subsequent
    amendment was found this iteration.
  - Labour Law: Law N° 66/2018 of 30/08/2018 regulating labour in
    Rwanda, Official Gazette n° Special of 06/09/2018, hosted directly
    on mifotra.gov.rw (Ministry of Public Service and Labour). Article
    One (read directly): 'This Law regulates labour in Rwanda.' Amended
    by Law N° 027/2023 of 18/05/2023 (Official Gazette n° Special of
    30/05/2023, also directly mifotra.gov.rw-hosted and independently
    read -- its own title recital quotes 'Law n° 66/2018 of 30/08/2018
    regulating labour in Rwanda' verbatim); recorded in
    `:statute/last-revised-date`.

  Rwanda's general legal-gazette portal, amategeko.gov.rw, is a
  client-side-rendered application that returned only a near-empty page
  shell to WebFetch this iteration -- the same access-gap pattern
  already disclosed in several sibling docstrings for their own
  jurisdiction's official portal -- an honestly-flagged ACCESS gap, not
  a claim that amategeko.gov.rw lacks these texts. Every citation below
  was instead independently corroborated directly on each issuing
  ministry/agency's OWN .gov.rw (or RDB-Group) hosting.

  A law not in this table has NO spec-basis, full stop; extend
  `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of statute entries. `:statute/url` + `:statute/law-number`
  are the citation the governor requires before any compliance-fact
  proposal referencing this law can commit."
  {"RWA"
   [{:statute/id "rwa.companies-act-007-2021"
     :statute/title "Law N° 007/2021 of 05/02/2021 governing companies"
     :statute/jurisdiction "RWA"
     :statute/kind :law
     :statute/law-number "Law N° 007/2021 of 05/02/2021"
     :statute/url "https://businessprocedures.rdb.rw/media/Official_Gazette_N___04_ter_of_08-02-2021_Companies_Law_2021.pdf"
     :statute/url-provenance :official-rdb-rw
     :statute/enacted-date "2021-02-05"
     :statute/last-revised-date "2023-03-30"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:corporate-governance :incorporation}}
    {:statute/id "rwa.data-protection-law-058-2021"
     :statute/title "Law N° 058/2021 of 13/10/2021 relating to the protection of personal data and privacy"
     :statute/jurisdiction "RWA"
     :statute/kind :law
     :statute/law-number "Law N° 058/2021 of 13/10/2021"
     :statute/url "https://risa.gov.rw/fileadmin/user_upload/RISA/Publications/2.Laws/Law_relating_to_the_protection_of_personal_data_and_privacy.pdf"
     :statute/url-provenance :official-risa-gov-rw
     :statute/enacted-date "2021-10-13"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:data-protection :privacy}}
    {:statute/id "rwa.labour-law-66-2018"
     :statute/title "Law N° 66/2018 of 30/08/2018 regulating labour in Rwanda"
     :statute/jurisdiction "RWA"
     :statute/kind :law
     :statute/law-number "Law N° 66/2018 of 30/08/2018"
     :statute/url "https://www.mifotra.gov.rw/fileadmin/user_upload/Mifotra/Publication/3.LAWS/New_Labour_Law_2018.pdf"
     :statute/url-provenance :official-mifotra-gov-rw
     :statute/enacted-date "2018-08-30"
     :statute/last-revised-date "2023-05-18"
     :statute/retrieved-at "2026-07-23"
     :statute/topic #{:labor :employment}}]})

(defn spec-basis
  "The jurisdiction's statute vector, or nil -- nil means NO spec-basis
  for that jurisdiction yet."
  [iso3]
  (get catalog iso3))

(defn coverage
  "Honest coverage report, same shape/discipline as `marketentry.facts/coverage`:
  never report a missing jurisdiction as covered."
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-rwa statute.facts Wave 0 (ADR-2607141700): "
                 (count (get catalog "RWA")) " RWA statutes seeded with "
                 "official rdb.rw/risa.gov.rw/mifotra.gov.rw citations. Extend "
                 "`statute.facts/catalog`, never fabricate a law-id or URL.")})))

(defn by-topic
  "Statutes for `iso3` tagged with `topic` (e.g. :labor, :data-protection)."
  [iso3 topic]
  (filterv #(contains? (:statute/topic %) topic) (spec-basis iso3)))

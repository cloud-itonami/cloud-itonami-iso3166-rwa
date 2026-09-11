(ns culture.facts
  "Country-level regional-culture catalog for Rwanda (RWA) -- national
  dishes, protected products, beverages, crafts, festivals and heritage
  sites, per ADR-2607171400 addendum 2 (cloud-itonami-municipality-
  culture-catalog Wave 1, in com-junkawasaki/root). Sibling namespace to
  `marketentry.facts` / `statute.facts` (ADR-2607141700); city-level
  counterparts live in the cloud-itonami-municipality-* repos.

  Catalog is keyed by UPPERCASE ISO3 (mirrors `statute.facts`); entries
  carry no :culture/municipality (that attribute is city-level only).

  Every entry cites a source URL that was actually fetched and read on
  :culture/retrieved-at -- never fabricated. Summaries state only what the
  cited source confirms. An item not in this table has NO spec-basis, full
  stop; extend `catalog`, do not invent an id/url.")

(def catalog
  "iso3 -> vector of culture entries."
  {"RWA"
   [{:culture/id "rwa.dish.ugali"
     :culture/name "Ugali"
     :culture/name-local "Ubugali"
     :culture/country "RWA"
     :culture/kind :dish
     :culture/summary "Firm cornmeal dough eaten with beans, vegetables and meat, made in several African countries including Rwanda, where it is called ubugali."
     :culture/url "https://en.wikipedia.org/wiki/Ugali"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.dish.isombe"
     :culture/name "Isombe"
     :culture/country "RWA"
     :culture/kind :dish
     :culture/summary "Vegetable stew dish from Rwanda made from cassava leaves cooked with ground peanuts and vegetables."
     :culture/url "https://en.wikipedia.org/wiki/Isombe"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.dish.brochettes"
     :culture/name "Brochettes"
     :culture/country "RWA"
     :culture/kind :dish
     :culture/summary "Grilled meat on a skewer, usually goat or beef, a popular lunch dish in Rwandan cuisine."
     :culture/url "https://en.wikipedia.org/wiki/Rwandan_cuisine"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.beverage.urwagwa"
     :culture/name "Urwagwa"
     :culture/country "RWA"
     :culture/kind :beverage
     :culture/summary "Banana beer made from fermented mashed bananas, known as urwagwa in Rwanda and Burundi and sometimes consumed during rituals and ceremonies."
     :culture/url "https://en.wikipedia.org/wiki/Banana_beer"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.craft.agaseke"
     :culture/name "Agaseke"
     :culture/country "RWA"
     :culture/kind :craft
     :culture/summary "Traditional Rwandese woven basket."
     :culture/url "https://en.wikipedia.org/wiki/Agaseke"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.craft.imigongo"
     :culture/name "Imigongo"
     :culture/country "RWA"
     :culture/kind :craft
     :culture/summary "Art form popular in Rwanda, traditionally made by women using cow dung."
     :culture/url "https://en.wikipedia.org/wiki/Imigongo"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.festival.kwita-izina"
     :culture/name "Kwita Izina"
     :culture/country "RWA"
     :culture/kind :festival
     :culture/summary "Rwandan ceremony of giving a name to a newborn baby gorilla, mirroring traditional Rwandan naming customs for children and serving conservation purposes in Volcanoes National Park."
     :culture/url "https://en.wikipedia.org/wiki/Kwita_Izina"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}
    {:culture/id "rwa.heritage.volcanoes-national-park"
     :culture/name "Volcanoes National Park"
     :culture/country "RWA"
     :culture/kind :heritage
     :culture/summary "National park in northwestern Rwanda covering 160 sq km of rainforest, encompassing five of the eight volcanoes in the Virunga Mountains."
     :culture/url "https://en.wikipedia.org/wiki/Volcanoes_National_Park"
     :culture/url-provenance :wikipedia-en
     :culture/retrieved-at "2026-07-17"}]})

(defn spec-basis [iso3] (get catalog iso3))

(defn coverage
  ([] (coverage (keys catalog)))
  ([iso3s]
   (let [have (filter catalog iso3s)
         missing (remove catalog iso3s)]
     {:requested (count iso3s)
      :covered (count have)
      :covered-jurisdictions (vec (sort have))
      :missing-jurisdictions (vec (sort missing))
      :note (str "cloud-itonami-iso3166-rwa culture catalog "
                 "(ADR-2607171400 addendum 2, Wave 1): " (count (get catalog "RWA"))
                 " RWA entries, each with a fetched-and-read citation. "
                 "Extend `culture.facts/catalog`, never fabricate an id/url.")})))

(defn by-kind [iso3 kind]
  (filterv #(= (:culture/kind %) kind) (spec-basis iso3)))

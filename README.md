# cloud-itonami-iso3166-rwa

**`:implemented`** for **RWA**. Flagship `rw-entity-missing`, tax `rdb-unverified`.

```
kbb -M:dev:test
```

AGPL-3.0-or-later.

## Statute catalog

Alongside the market-entry catalog (`src/marketentry/facts.cljk`, scoped
narrowly to RPPA/Umucyo public-procurement market entry), this repo
carries a **general-law compliance catalog** (ADR-2607141700,
`cloud-itonami-compliance-fact-federation`) covering the statutes a
company operating in Rwanda must generally track for compliance beyond
procurement:

- `src/statute/facts.cljk` — the catalog, source of truth. Three
  entries, each independently WebFetch/pdftotext-verified directly
  against the issuing ministry/agency's own `.gov.rw` (or RDB-Group)
  hosting, not a secondary summary: Law N° 007/2021 of 05/02/2021
  governing companies (businessprocedures.rdb.rw — the same RDB already
  cited in `marketentry.facts`'s corporate-number authority), Law N°
  058/2021 of 13/10/2021 relating to the protection of personal data and
  privacy (risa.gov.rw), and Law N° 66/2018 of 30/08/2018 regulating
  labour in Rwanda (mifotra.gov.rw). Known subsequent amendments (Law N°
  019/2023 for the Companies Act, Law N° 027/2023 for the Labour Law)
  are recorded in `:statute/last-revised-date`.
- `schema/statute.edn` — DataScript schema.
- `data/datascript-tx.edn` — derived DataScript tx-data (regenerated
  from the catalog, never hand-edited).

Same provenance discipline as the market-entry catalog: an item not in
`statute.facts/catalog` has no spec-basis — never fabricate one.

## Culture catalog

Alongside the market-entry / statute catalogs, this repo carries a
**country-level regional-culture catalog** (ADR-2607171400 addendum 2,
`cloud-itonami-municipality-culture-catalog` Wave 1, in
`com-junkawasaki/root`) — national dishes, protected products, beverages,
crafts, festivals and heritage sites for Rwanda:

- `src/culture/facts.cljk` — the catalog, source of truth (keyed by
  uppercase ISO3, mirroring `statute.facts`).
- `schema/culture.edn` — DataScript schema.
- `data/culture-tx.edn` — derived DataScript tx-data (regenerated from
  the catalog, never hand-edited).

City-level counterparts live in the `cloud-itonami-municipality-*` repos.
Same provenance discipline as the compliance catalogs: every entry cites a
source URL that was actually fetched and read on `:culture/retrieved-at`;
summaries state only what the cited source confirms. An item not in
`culture.facts/catalog` has no spec-basis — never fabricate one.

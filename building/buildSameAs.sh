#!/bin/sh
tarql --dedup 999999 -e utf-8 -d ";" builderFinal.sparql fr-esr-pes-pedr-beneficiaires.csv > ../rdf/graphFinal.ttl

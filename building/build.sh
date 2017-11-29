#!/bin/sh
tarql --dedup 999999 -e utf-8 -d ";" builder.sparql fr-esr-pes-pedr-beneficiaires.csv > ../rdf/graph.ttl

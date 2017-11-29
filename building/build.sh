#!/bin/sh
tarql --dedup 99999 -e utf-8 -d ";" builder.sparql fr-esr-pes-pedr-beneficiaires.csv > ../rdf/graph.ttl 

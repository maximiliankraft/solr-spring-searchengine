# Apache Solr + Spring Such-API

Diese Codevorlage enthält einen Apache Solr Client ([SolrJ](https://solr.apache.
org/guide/solr/latest/deployment-guide/solrj.html) ) sowie eine Spring-Anwendung um Suchanfragen über das Web zu 
bearbeiten. 

## Liste aller Domains mit Bezug zu Österreich

> [https://www.data.gv.at/katalog/dataset/d75798ec-b601-4961-9b06-1c24faaab294](https://www.data.gv.at/katalog/dataset/d75798ec-b601-4961-9b06-1c24faaab294)

Von dort die Datei **domainnames.txt** herunterladen und in den ``\resources``-Ordner geben. Dann kann diese Datei 
über den ``/domains/index``-Endpuntk indexiert werden. Dannach sind alle Domains biltzschnell aufrufbar. 

## Deployment

Um solr+spring auszuführen kann man beide via Docker mit dem Kommando ``docker compose up -d`` starten. Wenn 
Änderungen vorgenommen wurden, müssen die Container mit ``docker compose build`` neu gebaut werden. 


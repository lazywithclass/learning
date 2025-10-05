---
tags:
  - databases
  - transaction
---

```table-of-contents
title: 
style: nestedList # TOC style (nestedList|nestedOrderedList|inlineFirstLevel)
minLevel: 0 # Include headings from the specified level
maxLevel: 0 # Include headings up to the specified level
includeLinks: true # Make headings clickable
hideWhenEmpty: false # Hide TOC if no headings are found
debugInConsole: false # Print debug info in Obsidian console
```

Gestire operazioni in modo atomico, non divisibile.\
Challenge: identificare queste operazione uniche, indivisibili.

## Esempio

TODO continua da materiale del prof

```language-sql
CREATE TABLE country_area {
	country char(3) PRIMARY KEY REFERENCES (country.iso3),
	sqkm double precision
}

INSERT INTO country_area VALUES('FRA', 
```


Non voglio arrivare in una situazione in cui ho solo alcune istruzioni che sono state eseguite. Se si presenta un errore devo ritornare al punto di partenza.

## Problemi

TODO aggiungi note da slide e vedi anche materiale caricato dal prof

Lost update - 


## ACID

TODO anche per questo vedi appunti del prof


TODO Vedere verifica della transazione alla fine di essa (dovrebbe chiamarsi deferred)
Si puo' rilassare un vincolo mettendo deferred.
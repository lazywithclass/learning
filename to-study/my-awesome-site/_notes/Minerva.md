---
cssclasses:
  - cornell-note
---

# Definizioni


## Documenti

Qualsiasi azione in ospedale produce un documento, anche per avere chiara la responsabilita'

### Documento rilasciati alla fine

#### DCACUM 
"Passa il medico e annota"
Notifiche manuale e automatiche.
Annotazioni riguardo il paziente.
Storia del ricovero, incompleto, non ci sono gli esami, referti di laboratorio, ...

#### FUT - Foglio Unico Terapia
Analogo DCACUM sulle somministrazioni

Lettera dimissione medica
Perche' e' stato dimesso

### Documento prodotto con paziente in ospedale

#### DCA
Singola entry del DCACUM

#### Param
Automatiche

#### Notifiche automatiche
Analogo del Param (cambia formato in xml)

#### Annotazioni manuali

#### Inquadramento clinico
Prima "cosa" che fanno al paziente quando arriva in ospedale

# Architettura 

![](photo_2024-10-11_11-36-56.jpg)

![](Pasted%20image%2020241014182839.png)

![](Pasted%20image%2020241018140706.png)

## Testa logica

Ha a che fare con i file, non con il loro contenuto.

<aside>ATTENZIONE!</aside>

<span class="b">Mai chiedere a Jessica riguardo il parsing dei PDF, i dati di questi vengono scritti su S3 e linkati per i Bronze e Silver, Gold TODO</span>
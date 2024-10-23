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

## Flusso dei dati

![](Pasted%20image%2020241018140706.png)

Ogni 4h, o a volonta' con il trigger manuale, la richiesta di andare a prendere i dati (da tabelle, pdf, e xml) vengono immesse tramite due lambda su due code separate.
Un componente successivamente si occupa di chiedere al Fetch di prendere questi dati, che li salva dentro il Bronze.
Quindi sul Bronze ci sono due tipi di dato per ogni azione di fetch:
* pdf_name.pdf che e' il contenuto del blob
* pdf_name.json che e' il contenuto delle colonne della tupla dove c'era il blob
Successivamente AWS Batch si occupa di prendere i dati e inserirli nella Testa Logica, la quale ha dei riferimenti a diversi repository di dati, in base al grado di estrazione compiuta:
* Bronze - dato grezzo
* Silver - c'e' stata estrazione dei dati
* Gold - le feature sono state ripulite e aggregate

Quindi FETCHER_VISTE fa fetch da 
* server A che parla Postgres dove abbiamo TIPOFILE1 dentro VISTA1, TIPOFILE2 deNTRO vista2 ecc. 
* server B che parla Oracle dove abbiamo TIPOFILE1 dentro VISTA1, TIPOFILE2 deNTRO vista2 ecc. 
* server C che parla Mysql dove abbiamo TIPOFILE1 dentro VISTA1, TIPOFILE2 deNTRO vista2 ecc. 
ecc.
In un server possiamo avere n viste e una  vista puo' ospitare da 0 a n tipofile che dobbiamo fetchare. Se ne ospita 0 non e' una vista che prendiamo in considerazione, se ne ospita 1 o + si.

Il fetcher gestisce il pool di connessioni e astrae la query che serve a prescindere dalla vista che interroghi e dal dialetto sql che usi

E a runtime costruisce dinamicamente la query a seconda della vista e dialetto sql e QUALSIASI sia la vista e i dati in essa presenti chiede sempre Gli STESSI dati

## Testa logica

Ha a che fare con i file, non con il loro contenuto.

<aside>ATTENZIONE!</aside>

<span class="b">I dati dei pdf vengono scritti su S3 e linkati per i Bronze, Silver, e Gold</span>

## DWH

Estrazione feature da S3, piu' pulizia del pdf perche' header e footer si mettono in mezzo quando c'e' da parsare, sopratutto quando sono in mezzo al cambio di pagina

Per ogni sezione tirare fuori l'informazione, c'e' uno schema di partenza da riempire con i dati che si trovano.

Creiamo quindi un JSON a partire da una struttura che dice cosa estrarre e come farlo.

$\forall$ docs
pulizia 
estrazione sezioni
estrazione informazioni
salvataggio file
update testa logica
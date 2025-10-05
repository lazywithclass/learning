
"Come funziona Internet"

## Info sul corso

Teoria: https://myariel.unimi.it/course/view.php?id=9026\
Laboratorio: 

Seconda meta' di Ottobre parte il laboratorio.\
Non ci sono slide.

F. Halsall\
Networking e Internet\
Pearson - 5a ed

A. Tanenbaum\
Reti di Calcolatori\
Pearson - 6a ed

Esame di teoria con 10 domande aperte.\
Esame di laboratorio prevede un "progettino" da fare in sede d'esame.\
**Teoria e laboratorio vanno dati nello stesso appello**, ci sara' prima teoria e poi laboratorio a distanza di un paio di giorni.

Il corso di teoria finisce a Dicembre, laboratorio potrebbe sforare Gennaio.

## Lezione 1 - 25/09

Rete: sistema che collega apparati remoti, fornisce la connettività.

Connettività: fisica o wireless. Mobile se effettivamente l'apparato può muoversi nello spazio.

Da `A` a `B` voglio mandare un messaggio Whatsapp.\
La rete collega `A` con `B` tramite un server: `A` parla con il server, capisco se `B` e' raggiungibile e come posso raggiungerla.

"Come connetto il server con il client?", c'e' quasi sempre un server che funge da sponda. In una comunicazione peer-to-peer un client e' sia client che server di se stesso.

Le reti sono due e radicalmente diverse:

* rete di accesso - consente all'apparato di garantire la connettività verso la destinazione
	* LAN - sia wireless che wired
	* rete cellulare - per meta' rete wireless per meta' rete fissa, ad un certo punto si connette ad Internet
* rete fissa - distribuisce a destinazione (Internet e' questo)

Noi ci occuperemo di:

 * LAN wired
 * rete fissa

Caratteristiche della rete fissa 

* indirizzamento, devo essere in grado di indirizzare in modo univoco (IP)
* affidabilità (TCP)
* quality of service

IP e TCP sono componenti software. La rete TCP/IP e' di fatto la rete Internet.\
Importante capire che diversi scenari hanno diverse esigenze in merito a queste caratteristiche. L'affidabilità non ha costo zero, in termini di delay; e' più importante la velocità che l'affidabilità.



## Lezione 2 - 30/09

Ad alto livello definiamo una rete come composta da 
* rete di accesso - collegamento tra utente finale e provider, può essere wireless (tipicamente broadcast)
* rete fissa - la parte che si occupa dell'indirizzamento e dell'instradamento (tipicamente punto a punto)

Ogni flusso di dati necessita di un tipo di servizio, erogato dalla rete.\
Ad esempio potrei volere un servizio di affidabilità, oppure di velocità.

Ci sono due tipologie di controllo della comunicazione:
* centralizzato - si comunica attraverso un server che centralizza la comunicazione
* distribuito - peer to peer, il controllo e' equamente distribuito tra i peer (ognuno fa sia da client che da server)

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251002230433.png" ><figcaption>Esempio di rete</figcaption> </figure>

Come si vede dall'immagine una rete non e' totalmente connessa, ad esempio 1 non e' connesso a 3.\
A causa di questa non connessione abbiamo il problema dell'instradamento: non sappiamo a priori senza conoscere la topologia della rete quale sara' il percorso che dovremo prendere per arrivare a destinazione, inoltre voglio poter:
* ottimizzare il numero di hop; oppure
* ottimizzare il tempo

Nel caso di un percorso congestionato potrebbe accadere che un percorso con più hop sia più veloce. E' la funzione di instradamento (o di routing) che sceglie la strada nella rete.

Il router e' quell'apparato che data la fotografia della rete (deve conoscerne la topologia) determina la strada verso la destinazione, relativamente alla metrica che ci interessa (numero di hop o tempo).

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251002231234.png" ><figcaption>Esplodiamo il nodo 3 per vedere più in dettaglio</figcaption> </figure>

Il tempo per passare da un apparato ad un altro e' influenzato anche dal tempo di processamento interno, ad esempio con il riempirsi delle code il traffico potrebbe subire rallentamenti. Inoltre il buffer overflow e' dietro l'angolo nei router, dovuto alla gestione della memoria al loro interno.
 
### Reti a pacchetto

Immaginiamo di dover trasferire un file da 5Mb e che deve essere instradato, chiaramente non ci sta nelle porte di I/O, quindi la soluzione e' frammentare in pacchetti a dimensione massima fissa, questo risolve il problema ma pone due nuovi problemi:
* frammentazione - qualcuno deve occuparsi di questo all'ingresso della rete fissa
* ricombinazione - qualcuno deve occuparsi di questo all'uscita 

Importante notare come questa frammentazione sia trasparente al di fuori della rete, questo perché come in programmazione strati superiori non conoscono i dettagli degli strati inferiori, su cui costruiscono le loro peculiarità; un ulteriore punto importante e' che la comunicazione avviene sempre e solo allo stesso livello.

### Frammentazione

Ogni pacchetto deve poter raggiungere la destinazione e deve poter essere assemblato.

#### Header

Contiene meta-informazioni riguardo il pacchetto, ad esempio:
* sorgente
* destinazione
* ordine di arrivo
* etc

#### Payload

L'informazione per come e' arrivata alla rete.

### Concetti di comunicazione affidabile

La rete deve essere affidabile: non devo perdere nulla, non devo avere duplicati, e in ricezione devo ottenere i pacchetti nello stesso ordine. Si parla di duplicazione perché potrebbe succedere che per raggiungere l'affidabilità la rete mandi più volte un pacchetto.

Nel caso l'affidabilità non sia richiesta ho il netto vantaggio della semplicità, questo porta ad una maggiore velocità.  

Internet per raggiungere l'affidabilità predilige una gestione ai margini, per evitare di caricare troppo la rete.

### Protocollo

Un insieme di regole comuni tra apparati distribuiti.\
Una macchina parla con un'altra se condivide lo stesso insieme di protocolli.

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251002234406.png" ><figcaption>Esempio di protocollo affidabile</figcaption> </figure>
Si può notare come l'ACK e il timer (T) permettono di assicurarsi che un pacchetto (PKT) sia stato ricevuto, o che sia necessaria una ritrasmissione.
### Clock

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251002234531.png" ><figcaption>Esempio di come il clock aiuta nell'invio di bit</figcaption> </figure>


## Lezione 3 - 02/10

"Come possiamo ottenere affidabilità tra due punti connessi fisicamente all'interno di una rete?" Tramite l'ACK.

Il timer va dimensionato in modo da essere maggiore del RTT (Round Trip Time)

### Tempi di trasmissione e propagazione

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005151743.png" ><figcaption>Calcolo del RTT</figcaption> </figure>

Quindi 

* $RTT = t_x + 2t_p$ dove $t_x$ e' il tempo di trasmissione e $t_p$ e' il tempo di propagazione
* $t_x = n \div b$ dove $n$ e' la grandezza del pacchetto in bit e $b$ e' la banda in bit al secondo
* $t_p = l \div v$ dove $l$ e' la lunghezza del cavo in metri e $v$ e' la velocità di propagazione in metri al secondo 

Posso aggiungere un 15% o 20% per dare il tempo alle code di smaltire il loro carico.

Importante notare che:

* il tempo di propagazione e' in funzione della lunghezza del cavo, per lunghezze di qualche chilometro non influisce, per lunghezze superiori potrebbe influire di molto.
* il tempo di propagazione non dipende dalla dimensione del pacchetto


<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005152410.png" ><figcaption>Differenza tra tempo di trasmissione e tempo di propagazione</figcaption> </figure>

### La necessita' di uno standard

Lo standard nasce perché ho bisogno di far parlare due macchine soddisfacendo al requisito di affidabilità.

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005152944.png" ><figcaption>Comunicazione tra nodi richiede uno standard</figcaption> </figure>

* ISO - International Standard Organization
* OSI - Open System Interconnection
* IETF - Internet Engineering Task Force, partita da OSI per dare standard ad internet, TCP e IP nascono da qui

### Visione di insieme 

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005153247.png" ><figcaption>Passaggio del pacchetto tra i livelli</figcaption> </figure>

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005153547.png" ><figcaption>Comunicazione tra sistemi con dettaglio sui livelli</figcaption> </figure>

Ogni livello aggiunge un pezzo all'header, che servira' poi al ricevente.

<figure> <img style="width: 300px;" src="attachments/Pasted%20image%2020251005153733.png" ><figcaption>L'header cresce (o decresce) man mano che si scende (o si sale) nei livelli</figcaption> </figure>

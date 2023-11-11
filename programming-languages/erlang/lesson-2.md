# Lesson 2

Modello di comunicazione: actor model

Thread come filamento di un processo

Stack e heap nel thread che condividono l'area data, e' semplice ma 
 * crea race conditions with update loss
 * deadlocks
 
Ogni "oggetto" e' un Attore
 * ha una casella di posta e 
 * manda riceve messaggi su questa casella
Ogni Attore gira in modo concorrente e sono implementati in user space (dentro la VM Erlang)
 
Comunicazione asincrona, no stato condiviso

spawn - crea attori
! - spedisce messaggi
pattern match - evade coda di messaggi

Il pid di un processo e' l'indirizzo a cui mandare processi

Si deve passare al figlio il pid del padre se si vuole che il figlio sia ingrado di parlare con il padre

## PID

self() da il PID

X.Y.Z

X - numero del nodo Erlang, il nostro, quello corrente e' sempre 0 (quello di un altro nodo Erlang sara' diverso)
Y - numero di processo
Z - informazioni aggiuntive (sempre 0 per noi)

Tramite `register(an_atom, PID)` e' possibile dare un nome ad un processo, c'e' anche `unregister(an_atom)`, `whereis(an_atom) -> Pid|undefined`, `registered()`

## Messaggi

`!` spedisce messaggi

I messaggi sono ordinati in ordine di arrivo, non in ordine di invio

Non abbiamo idea se un messaggio arriva, dobbiamo implementare noi una sorta di ACK, non c'e' niente nel protocollo standard di Erlang

Per ricevere

```Erlang
receive
  Any -> do.something(Any)
end
```

`receive` prende il l'ultimo messaggio che ha il pattern riconosciuto, altrimenti blocca attendendo

```bash
$ erl +p $NUM_PROCESSES # permette di estendere il max di processi di una macchina, che si prende con `erlang:system_info(process_limit)`
```

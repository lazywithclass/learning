// t = torre
// c = cavallo
// a = alfiere
// q = regina
// k = re
// p = pedone
// b = bianco
// n = nero

// qualsiasi relazione non presente indica una posizione vuota
let scacchiera = {
  a1: "tb",
  b1: "cb",
  c1: "ab",
  d1: "qb",
  e1: "kb",
  f1: "ab",
  g1: "cb",
  h1: "tb",
  a2: "pb",
  b2: "pb",
  c2: "pb",
  d2: "pb",
  e2: "pb",
  f2: "pb",
  g2: "pb",
  h2: "pb",

  a8: "tn",
  b8: "cn",
  c8: "an",
  d8: "qn",
  e8: "kn",
  f8: "an",
  g8: "cn",
  h8: "tn",
  a7: "pn",
  b7: "pn",
  c7: "pn",
  d7: "pn",
  e7: "pn",
  f7: "pn",
  g7: "pn",
  h7: "pn",
};

// pos = es. "d8", "h4"
function mosseLegaliPedone(pos) {
  // controllo se alla posizione pos c'e un pedone
  //      se non c'e un pedone, ritorno array vuoto
  // controlliamo la parte dx della chiave
  //      se e' un 2 si puo' muovere di 2 e di 1
  //      altrimenti si puo' muovere di 1
  // per calcolare il movimento, dobbiamo vedere se nero o se bianco
  // se bianco si somma, se nero si sottrae
  // per il nero mi fermo all'1, per il bianco mi fermo all'8
  let pedina = scacchiera[pos];
  if (pedina[0] !== "p") {
    return [];
  }
  let isBianco = pedina[1] == "b";
  let isAllaPartenza =
    (isBianco && pos[1] == "2") || (!isBianco && pos[1] == "7");
  let isAllaFine = (isBianco && pos[1] == "8") || (!isBianco && pos[1] == "1");
  if (isAllaFine) {
    console.log("GESTIRE!");
    return;
  }
  // parseInt e' una funzione che data una stringa, ritorna il suo equivalente in numero
  let riga = parseInt(pos[1], 10);
  if (isAllaPartenza) {
    return isBianco
      ? [pos[0] + (riga + 1), pos[0] + (riga + 2)]
      : [pos[0] + (riga - 1), pos[0] + (riga - 2)];
  }
  if (!isAllaPartenza) {
    return isBianco ? [pos[0] + (riga + 1)] : [pos[0] + (riga - 1)];
  }
}

// data la posizione del cavallo mi calcolo tutte le altre 8 posizioni
//prima : lettera + 1 e numero + 2
//seconda : lettera + 2 e numero + 1
//terza : letterea + 2 e numero - 1
//quarta : lettera + 1 e numero - 2
//quinta : lettera - 1 e numero - 2
//sesta : lettera - 2 e numero - 1
//settima : lettera - 2 e numero + 1
//ottava : lettera - 1 e numero + 2

//utilizzare un array in cui si mettono tutte le lettere
//controllo se la cella di arrivo e'occupata da un amico o da un nemico
function mosseLegaliCavallo(pos) {
  let prima = prossimaPosizione(pos, 1, 2);
  let seconda = prossimaPosizione(pos, 2, 1);
  let terza = prossimaPosizione(pos, 2, -1);
  let quarta = prossimaPosizione(pos, 1, -2);
  let quinta = prossimaPosizione(pos, -1, -2);
  let sesta = prossimaPosizione(pos, -2, -1);
  let settima = prossimaPosizione(pos, -2, 1);
  let ottava = prossimaPosizione(pos, -1, 2);

  let possibiliMosseLegali = [prima, seconda, terza, quarta, quinta, sesta, settima, ottava]
  return rimuoviPosizioniOccupateDaAmici(scacchiera[pos], possibiliMosseLegali);
}

// cicliamo su tutte le posizioni
//   ritorniamo solamente quelle posizioni che NON sono occupate da amici
function rimuoviPosizioniOccupateDaAmici(pedina, posizioni) {
  let posizioniLegali = []
  for (let i = 0; i < posizioni.length; i++) {
    if (!isOccupataDaAmico(pedina, posizioni[i])) {
      posizioniLegali.push(posizioni[i])
    }
  }
  return posizioniLegali
}

// assumiamo che come al solito pedina sia nel formato "cb"
// assumiamo che come al solito pos sia nel formato "c5"
function isOccupataDaAmico(pedina, pos) {
  let altraPedina = scacchiera[pos]
  if (!altraPedina) {
    return false
  }

  let pedinaColore = pedina[1]
  let altraPedinaColore = altraPedina[1]
  return pedinaColore == altraPedinaColore
}

// TODO dobbiamo ancora considerare i casi fuori dalla scacchiera
function prossimaPosizione(posAttuale, variazioneLettera, variazioneNumero) {
  let lettera = prossimaLettera(posAttuale, variazioneLettera);
  let numero = prossimoNumero(posAttuale, variazioneNumero);
  return lettera + numero;
}

function prossimoNumero(pos, num) {
  let prox = parseInt(pos[1], 10) + num;
  if (prox >= 1 && prox <= 8) {
    return prox;
  }
  return -1;
}

// creare un array con le lettere da a ad h
// estrarre da pos la lettera attuale
// guardare dove si trova lettera attuale nell'array (indice)
// sommare num all'indice
// richiedere all'array la relativa lettera
function prossimaLettera(pos, num) {
  let lettere = ["a", "b", "c", "d", "e", "f", "g", "h"];
  let lettera = pos[0];
  let indice = lettere.indexOf(lettera);
  indice = indice + num;
  if (indice >= 0 && indice <= lettere.length) {
    return lettere[indice];
  }
  return "";
}

function mossaCasuale(mosseLegali) {
  return mosseLegali[Math.floor(Math.random() * mosseLegali.length)];
}

// dato una posizione di partenza a8
//  se lettera e' a, la prossima e' la b
//  se lettera e' b, la prossima e' la c
//  ...
//  se lettera e' h, la prossima e' la a
//  diminuisco di 1 il numero
// se il numero e' 0 ritorno stinga vuota

function prossimaPosizionePerStampa(pos) {
  if (pos[1] == "0") {
    return "";
  }
  if (pos[0] == "a") {
    return "b" + pos[1];
  }
  if (pos[0] == "b") {
    return "c" + pos[1];
  }
  if (pos[0] == "c") {
    return "d" + pos[1];
  }
  if (pos[0] == "d") {
    return "e" + pos[1];
  }
  if (pos[0] == "e") {
    return "f" + pos[1];
  }
  if (pos[0] == "f") {
    return "g" + pos[1];
  }
  if (pos[0] == "g") {
    return "h" + pos[1];
  }
  if (pos[0] == "h") {
    return "a" + (parseInt(pos[1], 10) - 1);
  }
}

// creare una variabile posizione che parte da a8
// creo un ciclo while che termina quando la posizione e'fuori dalla scacchiera
// accumulare dentro la stringa gli eventuali pezzi di quella riga
// quando siamo arrivati a una stringa lunga 8, stampiamo la stringa e la azzeriamo
let traduzioneGrafica = {
  qb: "♕",
  kb: "♔",
  ab: "♗",
  cb: "♘",
  tb: "♖",
  pb: "♙",
  qn: "♛",
  kn: "♚",
  an: "♝",
  cn: "♞",
  tn: "♜",
  pn: "♟︎",
};

function stampaScacchiera() {
  let pos = "a8";
  let riga = "";
  let contatore = 0;
  while (pos) {
    let pedina = scacchiera[pos];
    riga = riga + (traduzioneGrafica[pedina] || " ");
    contatore++;
    if (contatore == 8) {
      console.log(riga);
      riga = "";
      contatore = 0;
    }
    pos = prossimaPosizionePerStampa(pos);
  }
}

stampaScacchiera();

// let pos = "h2";
// let pedina = scacchiera[pos];
// let mosseLegali = mosseLegaliPedone(pos);
// let nuovaPosizione = mossaCasuale(mosseLegali);
// delete scacchiera[pos];
// scacchiera[nuovaPosizione] = pedina;

// stampaScacchiera();
let pos = "d5";
scacchiera.d5 = "cb";

let mosseLegali = mosseLegaliCavallo(pos);
let nuovaPosizione = mossaCasuale(mosseLegali);
let pedina = scacchiera[pos];
delete scacchiera[pos];
scacchiera[nuovaPosizione] = pedina;
stampaScacchiera();

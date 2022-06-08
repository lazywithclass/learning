package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

/*
Presenze
========

Scrivere un programma in Go (il file deve chiamarsi 'presenze.go') dotato di

- una struttura Studente (nome, cognome, matr, presenze), dove:
	nome e cognome sono di tipo string
	matr e presenze di tipo int

- funzione incrementaPresenze(p_studente *Studente)
	che incrementa di 1 le presenze di studente

- una funzione main() che legge il nome di un file e un carattere da linea di comando

Il file conterrà una serie di righe, ciascuna con Nome, Cognome, Matr di uno studente, numero di presenze
(nomi e cognomi di una parola sola e con iniziali in A-Z, a-z)

Il programma deve creare una slice di studenti con i dati del file.

Il programma deve poi incrementare le presenze degli studenti della slice
il cui nome inizia con il carattere letto (secondo parametro linea di comando)
e produrre in output la lista degli studenti con la situazione delle presenze aggiornata.



Esempio
=======

Dato un file "uno.input" contenente:

Ida Unruh  381732 2
Carolynn Wimmer  93824923 6
Vada Furrow  28391 5
Maurine Even  28318 9
Lamar Tapp  3261725631 8
Sook Earle  763512 6
Denita Strothers  217632187 7
Carin Poteet  27651 7
Ina Blessing  29319812 5
Tish Billingsly  28738213 8
Felice Hermanson  5968598659 6
Everette Zeman  73627632 7
Agnus Birnbaum  29329832 6
Dania Crail  2329832 1
Violet Carnell  29387287 4
Marcelina Tuck  2736276 5
Freda Leonardo  2387872 7
Vina Clermont  832323 6
Gidget Phung  38723872 9
Ignacia Schaffner  23762372 4

L'esecuzione di:

$ go run presenze.go datiEsPresenze.input I

genererà:

Ida Unruh 381732 3
Carolynn Wimmer 93824923 6
Vada Furrow 28391 5
Maurine Even 28318 9
Lamar Tapp 3261725631 8
Sook Earle 763512 6
Denita Strothers 217632187 7
Carin Poteet 27651 7
Ina Blessing 29319812 6
Tish Billingsly 28738213 8
Felice Hermanson 5968598659 6
Everette Zeman 73627632 7
Agnus Birnbaum 29329832 6
Dania Crail 2329832 1
Violet Carnell 29387287 4
Marcelina Tuck 2736276 5
Freda Leonardo 2387872 7
Vina Clermont 832323 6
Gidget Phung 38723872 9
Ignacia Schaffner 23762372 5
*/

type Studente struct {
	nome, cognome  string
	matr, presenze int
}

func incrementaPresenze(p_studente *Studente) {
	p_studente.presenze++
}

func aggiornaPresenze(studenti []Studente, iniziale string) {
	for i, studente := range studenti {
		if string(studente.nome[0]) == iniziale {
			incrementaPresenze(&studente)
			studenti[i] = studente
		}
	}
}

func stampa(studenti []Studente) {
	for _, studente := range studenti {
		fmt.Printf("%s %s %d %d\n", studente.nome, studente.cognome, studente.matr, studente.presenze)
	}
}

func main() {
	nomeFile := os.Args[1]
	iniziale := os.Args[2]

	file, err := os.Open(nomeFile)
	if err != nil {
		return
	}
	defer file.Close()

	var studenti []Studente
	scanner := bufio.NewScanner(file)
	for scanner.Scan() {
		riga := scanner.Text()
		split := strings.Fields(riga)
		nome := split[0]
		cognome := split[1]
		matricola, _ := strconv.Atoi(split[2])
		presenze, _ := strconv.Atoi(split[3])
		studenti = append(studenti, Studente{nome, cognome, matricola, presenze})
	}

	aggiornaPresenze(studenti, iniziale)
	stampa(studenti)
}

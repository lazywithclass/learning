package main

import (
	"bufio"
	"fmt"
	"os"
	"strconv"
	"strings"
)

func main() {
	comandi := LeggiComandi()
	fmt.Println("Movimenti totali:")
	minorNumeroDiPassi := 99999
	direzioneMinore := ""
	for direzione, passi := range AnalizzaComandi(comandi) {
		if passi < minorNumeroDiPassi {
			minorNumeroDiPassi = passi
			direzioneMinore = direzione
		} else {
			if passi == minorNumeroDiPassi && direzione[0] < direzioneMinore[0] {
				minorNumeroDiPassi = passi
				direzioneMinore = direzione
			}
		}
		fmt.Println(direzione, passi)
	}
	fmt.Println("Direzione in cui il robot deve compiere il minor numero totale di passi:")
	fmt.Println(direzioneMinore)

	var comandiInvertiti []Comando
	for i := len(comandi) - 1; i >= 0; i-- {
		comandiInvertiti = append(comandiInvertiti, comandi[i])
	}

	opposti := map[string]string{
		"NORD": "SUD", "SUD": "NORD",
		"OVEST": "EST", "EST": "OVEST",
	}

	fmt.Println("Comandi inversi:")
	ultimoSeparatore := ", "
	for indice, comando := range comandiInvertiti {
		if indice == len(comandiInvertiti)-1 {
			ultimoSeparatore = ""
		}
		fmt.Print(opposti[comando.direzione], " ", comando.passi, ultimoSeparatore)
	}
}

type Comando struct {
	direzione string
	passi     int
}

func LeggiComandi() []Comando {
	var comandi []Comando
	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		riga := scanner.Text()
		if riga == "" {
			break
		}
		comando := ParsaComando(riga)
		comandi = append(comandi, comando)
	}

	return comandi
}

func AnalizzaComandi(comandi []Comando) map[string]int {
	var analisi map[string]int = make(map[string]int)
	for _, comando := range comandi {
		analisi[comando.direzione] += comando.passi
	}
	return analisi
}

func ParsaComando(comando string) Comando {
	split := strings.Split(comando, " ")
	conto, _ := strconv.Atoi(split[1])
	return Comando{split[0], conto}
}

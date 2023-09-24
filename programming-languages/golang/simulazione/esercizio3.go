package main

import (
	"bufio"
	"fmt"
	"math"
	"os"
	"strconv"
	"strings"
)

func main() {
	soglia, _ := strconv.ParseFloat(os.Args[1], 64)

	var punti []Punto

	scanner := bufio.NewScanner(os.Stdin)
	for scanner.Scan() {
		riga := scanner.Text()
		if riga == "" {
			break
		}
		punti = append(punti, CreaPunto(riga))
	}

	segmenti := CreaSegmenti(punti)

	for _, segmento := range segmenti {
		if NonEParallelo(segmento) && IsInDiversiQuadranti(segmento) && HaLunghezzaMaggioreDi(soglia, segmento) {
			fmt.Println(StringSegmento(segmento))
		}
	}
}

type Punto struct {
	etichetta string
	x, y      float64
}

type Segmento struct {
	puntoA Punto
	puntoB Punto
}

func NonEParallelo(s Segmento) bool {
	return s.puntoA.x != s.puntoB.x && s.puntoA.y != s.puntoB.y
}

func IsInDiversiQuadranti(s Segmento) bool {
	return (s.puntoA.x > 0 && s.puntoB.x < 0) || (s.puntoA.x < 0 && s.puntoB.x > 0) ||
		(s.puntoA.y > 0 && s.puntoB.y < 0) || (s.puntoA.y < 0 && s.puntoB.y > 0)
}

func HaLunghezzaMaggioreDi(soglia float64, s Segmento) bool {
	return Distanza(s.puntoA, s.puntoB) > soglia
}

func CreaSegmenti(punti []Punto) []Segmento {
	var segmenti []Segmento
	for i := 0; i < len(punti); i++ {
		puntoA := punti[i]
		for j := i; j < len(punti); j++ {
			puntoB := punti[j]
			segmenti = append(segmenti, Segmento{puntoA, puntoB})
		}
	}
	return segmenti
}

func CreaPunto(s string) Punto {
	split := strings.Split(s, ";")
	x, _ := strconv.ParseFloat(split[1], 64)
	y, _ := strconv.ParseFloat(split[2], 64)
	return Punto{split[0], x, y}
}

func Distanza(p1, p2 Punto) float64 {
	return math.Sqrt(math.Pow(p1.x-p2.x, 2) + math.Pow(p1.y-p2.y, 2))
}

func StringPunto(p Punto) string {
	return fmt.Sprintf("%s = (%.2f, %.2f)", p.etichetta, p.x, p.y)
}

func StringSegmento(s Segmento) string {
	return fmt.Sprintf("SEGMENTO con estremi %s e %s", StringPunto(s.puntoA), StringPunto(s.puntoB))
}

package main

import "fmt"

// scrivere un programma che modelli tre animali
// cane, gatto, coniglio
// modellare una funzione "verso" come metodo di ogni animale
// scrivere poi una funzione che presa una slice di animali stampi in output il verso di ognuno

func main() {
	var animali []Animale = []Animale{Cane{}, Gatto{}, Coniglio{}}
	for _, animale := range animali {
		fmt.Println(animale.verso())
	}
}

type Animale interface {
	verso() Verso
}

type Verso string

type Cane struct{}

func (c Cane) verso() Verso {
	return "BAU!"
}

type Gatto struct{}

func (g Gatto) verso() Verso {
	return "MEOW!"
}

type Coniglio struct{}

func (c Coniglio) verso() Verso {
	return "?!"
}

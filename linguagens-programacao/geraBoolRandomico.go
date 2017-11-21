package main

import "math/rand"

func geraBoolRandom() bool {

	if rand.Intn(11) > 5 {
		return true
	}
	return false
}

func converteSexo(sexo bool) string {
	if sexo {
		return "Masculino"
	} else {
		return "Feminino"
	}
}

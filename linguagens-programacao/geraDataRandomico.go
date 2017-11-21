package main

import (
	"math/rand"
	"strconv"
	"strings"
	"time"
)

func randate() time.Time {
	// pega a data atual
	var dataAtual = time.Now().Format(time.RFC1123Z)
	// separa por espaços
	var dataAtualSeparada = strings.Fields(dataAtual)
	var anoNumerico64, _ = strconv.ParseInt(dataAtualSeparada[3], 10, 0)
	// converte de int64 para int
	var anoAtual = int(anoNumerico64)

	min := time.Date(1970, 1, 0, 0, 0, 0, 0, time.UTC).Unix()
	max := time.Date(anoAtual, 1, 0, 0, 0, 0, 0, time.UTC).Unix()
	delta := max - min

	sec := rand.Int63n(delta) + min
	return time.Unix(sec, 0)
}

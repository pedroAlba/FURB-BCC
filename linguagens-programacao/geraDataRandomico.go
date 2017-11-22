package main

import (
	"math/rand"
	"strconv"
	"strings"
	"time"
)

func randate() string {
	// pega a data atual
	var dataAtual = time.Now().Format(time.RFC1123Z)
	// separa por espaços
	var dataAtualSeparada = strings.Fields(dataAtual)
	var anoNumerico64, _ = strconv.ParseInt(dataAtualSeparada[3], 10, 0)
	// converte de int64 para int
	var anoAtual = rangeIn(1970, int(anoNumerico64))

	min := time.Date(1970, 1, 0, 0, 0, 0, 0, time.UTC).Unix()
	max := time.Date(anoAtual, 1, 0, 0, 0, 0, 0, time.UTC).Unix()
	delta := max - min

	sec := rand.Int63n(delta) + min
	var fullDate = time.Unix(sec, 0).Format(time.RFC1123Z)
	var campos = strings.Fields(fullDate)
	return campos[1] + " " + campos[2] +  " " + campos[3]
}


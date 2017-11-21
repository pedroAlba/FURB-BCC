package main

import (
	"math/rand"
	"strconv"
)

// CPF é a variavel final, que receberá os digitos princiais e os 2 verificadores
var CPF [11]int

// GeraNumeroPrincipal é método privado, por isso iniciado com letra maiúscula, nele geraremos os 9 primeiros dígitos
func GeraNumeroPrincipal() {
	for i := 0; i < 9; i++ {
		CPF[i] = rand.Intn(10)
	}
}

// GeraDigitoVerificador é metodo privado, por isso iniciado com letra maiúscula,
func GeraDigitoVerificador(posicao int) {
	var qtdFor int
	var peso int
	if posicao == 1 {
		qtdFor = 10
		peso = 10
	} else {
		qtdFor = 11
		peso = 11
	}
	var arrayMultiplicados [11]int
	for i := 0; i < qtdFor; i++ {
		arrayMultiplicados[i] = (CPF[i] * peso)
		peso--
	}
	var totalSomatoria int
	for i := 0; i < qtdFor; i++ {
		totalSomatoria += arrayMultiplicados[i]
	}
	var restoDaDivisao = totalSomatoria % 11
	if restoDaDivisao < 2 {
		// Adiciona o valor na posição do for + 2 para colocar na próxima posição no array
		CPF[(qtdFor - 1)] = 0
	} else {
		CPF[(qtdFor - 1)] = 11 - restoDaDivisao
	}
}

func ConverteCPF() string {
	var formatado string
	for i := 0; i < 11; i++ {
		formatado += strconv.Itoa(CPF[i])
		if i == 2 || i == 5 {
			formatado += "."
		}
		if i == 8 {
			formatado += "-"
		}
	}
	return formatado
}

// func geraCPF() [11]int {
func geraCPF() string {
	// Gera os 9 digitos principais
	GeraNumeroPrincipal()
	// Gera o primeiro dígito verificador
	GeraDigitoVerificador(1)
	// Gera o segundo dígito verificaor
	GeraDigitoVerificador(2)
	// Devolve o CPF completo convertido em string
	// var cpfString = strings.Trim(strings.Join(strings.Fields(fmt.Sprint(CPF)), ""), "[]")
	// var padrao = "^\\d{3}\\.?\\d{3}\\.?\\d{3}-?\\d{2}$"
	return ConverteCPF()
}

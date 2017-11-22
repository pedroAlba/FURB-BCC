package main

import (
	"fmt"	
)

// Pessoa é o objeto principal, que receberá todas as características geradas randomicamente
type Pessoa struct {
	Nome, Sobrenome, Pai, Mae, Cpf, Sexo /*, CEP*/, Aniversario string	
}

func geradorPessoa() Pessoa {
	var Sexo = geraBoolRandom()
	var sexoString = converteSexo(Sexo)
	//var Cep = geraCEP()
	var Nome = geraNomeRandom(Sexo)
	var Sobrenome = geraSobrenome()
	var sobrenomeMaterno = geraSobrenome()
	var Pai = GeraPai(Sobrenome)
	var Mae = GeraMae(Sobrenome, sobrenomeMaterno)
	var Cpf = geraCPF()
	var usaSobrenomeMaterno = geraBoolRandom()
	if usaSobrenomeMaterno {
		Sobrenome = sobrenomeMaterno + " " + Sobrenome
	}
	var dataAniversario = randate();
	return Pessoa{Nome: Nome, Sobrenome: Sobrenome, /*CEP:Cep,*/Sexo: sexoString, Cpf: Cpf, Pai: Pai, Mae: Mae, Aniversario: dataAniversario}
}

func GeraPai(Sobrenome string) string {
	var Pai = geraNomeRandom(true)
	var paiDoisSobrenomes = geraBoolRandom()
	if paiDoisSobrenomes {
		Pai = Pai + " " + geraSobrenome() + " " + Sobrenome
	} else {
		Pai = Pai + " " + Sobrenome
	}
	return Pai
}

func GeraMae(Sobrenome string, sobrenomeMaterno string) string {
	var Mae = geraNomeRandom(false)
	var maeSobrenomeMarido = geraBoolRandom()
	if maeSobrenomeMarido {
		Mae = Mae + " " + sobrenomeMaterno + " " + Sobrenome
	} else {
		Mae = Mae + " " + sobrenomeMaterno
	}
	return Mae
}

func (p Pessoa) String() string {
	return fmt.Sprintf("Nome: %s, Sobrenome: %s, Sexo: %s, CPF: %s, Pai: %s, Mãe: %s, Data: %s", p.Nome, p.Sobrenome, p.Sexo, p.Cpf, p.Pai, p.Mae, p.Aniversario)
}
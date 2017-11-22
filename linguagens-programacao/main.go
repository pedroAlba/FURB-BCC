package main

import (
    "fmt"
    "net/http"    
	"log" 
	"encoding/json"
)

func main() {
	http.HandleFunc("/", serve) // set router
    err := http.ListenAndServe(":9090", nil) // set listen port
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }	
}

func serve(w http.ResponseWriter, r *http.Request) {	

	var pessoa = geradorPessoa()

	pessoaJ := &Pessoa{Nome: pessoa.Nome, Sobrenome: pessoa.Sobrenome, Pai: pessoa.Pai, Mae:pessoa.Mae, Cpf: pessoa.Cpf, Sexo: pessoa.Sexo, Aniversario: pessoa.Aniversario}
	b, err := json.Marshal(pessoaJ)

	if err != nil {
        fmt.Println(err)
        return
	}
	
	fmt.Fprintf(w, string(b)) // send data to client side
}

package main

import (
	"strings"
    "fmt"
    "net/http"
	"log"	
	"strconv"
)

func main() {
	http.HandleFunc("/", serve) 
    err := http.ListenAndServe(":9090", nil) 
    if err != nil {
        log.Fatal("ListenAndServe: ", err)
    }
}

func serve(w http.ResponseWriter, r *http.Request) {

	var path = r.URL.Path;
	path = strings.Replace(path, "/", "", 10)
	qtdPessoas, e := strconv.Atoi(path)

	if e != nil{
		qtdPessoas = 1
	}

	for i := 0; i < qtdPessoas ; i++ {
		var pessoa = geradorPessoa()
		fmt.Fprintf(w, pessoa.String())
		fmt.Fprintf(w, "\n---------------------------\n")
	}
}

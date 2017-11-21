package main

import "fmt"

func main() {

	var qtd = 3

	for i := 0; i < qtd+1; i++ {
		var p = geradorPessoa()
		fmt.Println(p)
	}
}

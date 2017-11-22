package main

import (
	"fmt"
	"net/http"
	"strconv"
	"math/rand"	
	"io/ioutil"
	"strings"
)

func geraCEP() string {
	//var cepNumber = getRandomCEP();
	var cep = strconv.Itoa(rangeIn(10000000, 99999999))
	return request(cep)
}

func rangeIn(low, hi int) int {
    return low + rand.Intn(hi-low)
}


func request(cepNumber string) string {

	fmt.Println("cep gerado " + cepNumber)	

	response, err := http.Get("https://viacep.com.br/ws/" + cepNumber + "/json/")
		if err != nil {
			fmt.Printf("%s", err)
			return ""
		} else {
			defer response.Body.Close()
			contents, err := ioutil.ReadAll(response.Body)
			if err != nil {
				fmt.Printf("%s", err)
				return ""
			}			

			if(strings.Contains(string(contents), "erro")){
				return request(geraCEP())
			}
			return string(contents)
		}
}

package main

import (
	"fmt"
	"net/http"	
	"math/rand"	
	"io/ioutil"
	"encoding/json"	
)

var Endpoints = [...]string{"https://viacep.com.br/ws/SC/Blumenau/Alameda/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Missoes/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Martin/json/ ",
						"https://viacep.com.br/ws/SC/Blumenau/Avenida/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Rodovia/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Via/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Alm/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Pas/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Sao/json/",
						"https://viacep.com.br/ws/SC/Blumenau/Rua/json/"}


func geraCEP() string {	
	return request(Endpoints[rand.Intn(10)])
}

func rangeIn(low, hi int) int {
    return low + rand.Intn(hi-low)
}

//
func request(endpoint string) string {	

	response, err := http.Get(endpoint)
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

			pingData := string(contents)
			pingJSON := make(map[string][]CEP)
			var erro = json.Unmarshal([]byte(pingData), &pingJSON)
		
			if erro != nil {
				panic(err)
			}
		
			fmt.Printf("\n\n json object:::: %+v", pingJSON)			
			
			for key, value := range pingJSON {
				fmt.Println("Key:", key, "Value:", value)
			}
			return ""
		
		}
}


type CEP struct {
	Cep, Logradouro, Complemento, Bairro, Localicade, Uf, Ibge string	
}
package main

import (
	"fmt"
	"net/http"	
	"math/rand"	
	"io/ioutil"
	"encoding/json"	
	"bytes"
	//"time"
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
	//rand.Seed(time.Now().UTC().UnixNano())	
	return request(Endpoints[rand.Intn(10)])	
}

func rangeIn(low, hi int) int {
    return low + rand.Intn(hi-low)
}

func request(endpoint string) string {	

	response, err := http.Get(endpoint)
		if err != nil {
			fmt.Printf("%s", err)
			return "0"
		} else {
			defer response.Body.Close()
			contents, err := ioutil.ReadAll(response.Body)
			if err != nil {
				fmt.Printf("%s", err)
				return "0"
			}			
			
			dec := json.NewDecoder(bytes.NewReader(contents))			
			var d Data
			dec.Decode(&d)
			var cepSingleObject = d[rand.Intn(len(d))]	
			
			b, err := json.Marshal(cepSingleObject)
			if err != nil {
				fmt.Println(err)
				return ""
			}
			return string(b)
		}
}

type Data []CEP

type CEP struct {	
		Cep string `json:"cep"`
		Logradouro string `json:"logradouro"`	
		Complemento string `json:"complemento"`		
		Bairro string `json:"bairro"`
		Localidade string `json:"localidade"`
		Uf string `json:"uf"`
		Unidade string `json:"unidade"`
		Ibge string `json:"ibge"`
		Gia string `json:"gia"`	
}



package main

import (
	"fmt"
	"net/http"
	"strconv"
	"math/rand"
	"strings"
	"io/ioutil"
)

func geraCEP() string {
	var cep = strconv.Itoa(rangeIn(10000000, 99999999))
	return request(cep)
}

func rangeIn(low, hi int) int {
    return low + rand.Intn(hi-low)
}


func request(cepNumber string) string {

		fmt.Println(cepNumber)


		resp, err := http.Get("https://viacep.com.br/ws/" + cepNumber + "/json/")

		if err != nil {

		panic(err)

		}

		defer resp.Body.Close()

		body, err := ioutil.ReadAll(resp.Body)

		str := string(body)

		return "get:\n" + keepLines(str, 11)

}

func keepLines(s string, n int) string {

			result := strings.Join(strings.Split(s, "\n")[:n], "\n")

			return strings.Replace(result, "\r", "", -1)

		}
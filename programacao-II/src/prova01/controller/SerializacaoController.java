package prova01.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import prova01.model.Atleta;
import prova01.model.EArquivoOrigemNaoExiste;
import prova01.model.Endereco;

public class SerializacaoController {

	
	public static void serializar(String arqOrigem, String arqDestino) throws EArquivoOrigemNaoExiste, IOException{
		
		
		File arqInput = new File(arqOrigem);
		File arqOutput = new File(arqDestino);
		
		if(!arqInput.exists() || arqInput.isDirectory()) { 
		    throw new EArquivoOrigemNaoExiste();
		}
		
		ArrayList<String> linhas = new ArrayList<String>();
		
        FileReader fileReader = new FileReader(arqInput);
        BufferedReader leitorTextobuffer = new BufferedReader(fileReader);     
        
        while (leitorTextobuffer.ready()) {
            linhas.add(leitorTextobuffer.readLine());
        }
        
        leitorTextobuffer.close();
        
        ArrayList<Atleta> atletas = new ArrayList<>();
        
        for (String linha : linhas) {
			String atributos[] = linha.split(",");
			
			Atleta a = new Atleta();
			a.setNome(atributos[0].trim());
			a.setAltura(Float.valueOf(atributos[1].trim()));
			a.setPeso(Float.valueOf(atributos[2].trim()));
			
			Endereco e = new Endereco();
			e.setRua(atributos[3].trim());
			e.setNumero(atributos[4].trim());
			e.setComplemento(atributos[5].trim());
			e.setCEP(atributos[6].trim().trim());
			e.setCidade(atributos[7].trim());
			e.setEstado(atributos[8].trim());
			
			a.setEndereco(e);
			
			atletas.add(a);
			
		}
        
        if(!atletas.isEmpty()){
			ObjectOutputStream output = new ObjectOutputStream(
					new FileOutputStream(arqOutput));

			output.writeObject(atletas);
			output.close();
        }
	}
}

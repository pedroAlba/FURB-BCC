package listas.lista01.questao01;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Tela {

	public static void main(String[] args) throws IOException {
		
		
		String caminhoBinario = "C:\\users\\palba\\temp\\testeBinario.dat";
		String caminhoString = "C:\\users\\palba\\temp\\testeString.txt";
		
		// Questão 01 - Binário
		DataOutputStream out = new DataOutputStream(new FileOutputStream(new File(caminhoBinario)));
		out.writeInt(12345);
		out.close();	
		
//		// Questao 01 - String				
//		Path path = Paths.get(caminhoString);
//		FileWriter fw = new FileWriter(path.toFile());		
//		fw.write("12345");
//		fw.close();
//		
//		//Ler string
//		FileReader leitorTexto = new FileReader(new File(caminhoString));
//		BufferedReader leitorTextobuffer = new BufferedReader(leitorTexto);		
//		String s = leitorTextobuffer.readLine();
//		System.out.println("Arquivo texto = " + s);
//		leitorTextobuffer.close();
//		
//		//Ler binario		
//		BufferedReader leitorBinarioBuffer = new BufferedReader(new FileReader(new File(caminhoBinario)));
//		s = leitorBinarioBuffer.readLine();
//		System.out.println("Arquivo binario = " + s);
//		leitorBinarioBuffer.close();
//		
//		DataInputStream arqBinario = new DataInputStream(new FileInputStream(new File(caminhoBinario)));		
//		int i = arqBinario.readInt();
//		System.out.println("Arquivo binário, interpretado = " + i);
//		arqBinario.close();
	}
}

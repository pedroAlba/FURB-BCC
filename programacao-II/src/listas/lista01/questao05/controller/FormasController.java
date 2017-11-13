package listas.lista01.questao05.controller;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.util.ArrayList;

import listas.lista01.questao05.model.Forma;



public class FormasController {

	public static void salvarFormas(ArrayList<Forma> lista, Path path) throws IOException {

		ObjectOutputStream output = new ObjectOutputStream(
									new FileOutputStream("c:\\users\\palba\\desktop\\Persistencia.dat"));

		output.writeObject(lista);
		output.close();
	}

	public static ArrayList<Forma> carregarFormas(Path path) throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream input = new ObjectInputStream(
			new FileInputStream("c:\\users\\palba\\desktop\\Persistencia.dat"));
		
		Object retornoDoArquivo = input.readObject();
		
		
		return (ArrayList<Forma>) retornoDoArquivo;
		
	}
}

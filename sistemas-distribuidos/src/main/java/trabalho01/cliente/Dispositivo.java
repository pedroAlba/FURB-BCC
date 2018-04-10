package trabalho01.cliente;

import trabalho01.Constantes;
import trabalho01.Utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.net.InetAddress;
import java.nio.file.Paths;

public class Dispositivo {

	public static void main(String[] args) {
		try {
			InetAddress grupo = InetAddress.getByName("224.1.1.1");
			File raiz = Paths.get(Constantes.BASEPATH.toString(), "Arquivos").toFile();
			StringBuilder buffer;
			for (File arquivo : raiz.listFiles()) {
				if (arquivo.isFile()) {
					FileReader fis = new FileReader(arquivo);
					BufferedReader bufferedReader = new BufferedReader(fis);
					String linha;
					buffer = new StringBuilder();
					buffer.append(arquivo.getName()).append("\n");

					while ((linha = bufferedReader.readLine()) != null) {
						buffer.append(linha).append("\n");
					}

					fis.close();
					bufferedReader.close();

					Utils.enviaPacote("Dispositivo", buffer.toString(), grupo);

                    System.out.printf("Dispositivo enviou o arquivo %s\n", arquivo.getName());
				}
			}
			//Utils.enviaPacote("Dispositivo","ACABOU", grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

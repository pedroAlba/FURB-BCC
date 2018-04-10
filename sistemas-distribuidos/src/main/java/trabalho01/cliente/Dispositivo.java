package trabalho01.cliente;

import trabalho01.Constantes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.nio.file.Paths;

public class Dispositivo {
	
	public static String readString() {
		BufferedReader canal = new BufferedReader(new InputStreamReader(System.in));
		try {
			return canal.readLine();
		} catch (IOException e) {
			return null;
		}		
	}
	
	public static void enviaPacote(String parMensagem, InetAddress parGrupo) {
		
		DatagramPacket pacote;
		DatagramSocket socket = null;		
		
		try {
			pacote = new DatagramPacket(parMensagem.getBytes(), parMensagem.getBytes().length, parGrupo, Constantes.PORTA.toNumeric());
			socket = new DatagramSocket();
			socket.send(pacote);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			socket.close();
		}
	}
	
	public static String receberPacote() throws IOException {
		
		DatagramSocket socket = new DatagramSocket();
		byte[] dados = new byte[1000];
	 	DatagramPacket pacoteArquivos = new DatagramPacket(dados, dados.length);
		socket.receive(pacoteArquivos);
		socket.close();
		return (new String(pacoteArquivos.getData()));
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		
		try {
			InetAddress grupo = InetAddress.getByName("224.1.1.1");
			//InetAddress grupo = InetAddress.getByName(receberPacote());
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
					
					enviaPacote(buffer.toString(), grupo);

                    System.out.printf("Dispositivo enviou o arquivo %s", arquivo.getName());
				}
			}
			enviaPacote("ACABOU", grupo);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

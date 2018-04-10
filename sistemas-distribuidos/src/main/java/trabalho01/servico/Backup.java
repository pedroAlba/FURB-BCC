package trabalho01.servico;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.util.ArrayList;

public class Backup {
	
	public static InetAddress getServidorDisponivel( ) {
		
		ArrayList<String> servidor = new ArrayList<>();
		servidor.add("224.0.0.0");
		servidor.add("224.1.1.1");
		servidor.add("224.1.1.2");
		servidor.add("224.1.1.3");
		servidor.add("224.1.1.4");
		servidor.add("224.1.1.5");
		
		InetAddress acesso = null;
		try {
			acesso = InetAddress.getByName(servidor.get(1));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return acesso;
		
	}

	public static void enviaPacote(String parMensagem, InetAddress parGrupo) {
		
		DatagramPacket pacote;
		DatagramSocket socket = null;		
		
		try {
			pacote = new DatagramPacket(parMensagem.getBytes(), parMensagem.getBytes().length, parGrupo, 5000);			
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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MulticastSocket mSocket = null;
		
		try {
			
			int porta = 5000;
			mSocket = new MulticastSocket(porta);

			InetAddress servidor = getServidorDisponivel();
			
			mSocket.joinGroup(servidor);
			
			System.out.println("Servidor " + servidor.getHostName()  + " pronto para receber os arquivos para fazer backup.");
			
			//enviaPacote(servidor.getHostName(), servidor);
			
			byte[] dados = new byte[1000];
			
			while (true) {
			 	DatagramPacket pacoteArquivos = new DatagramPacket(dados, dados.length);
				mSocket.receive(pacoteArquivos);
				
				String conteudoArquivo = (new String(pacoteArquivos.getData()));
				if (conteudoArquivo.equalsIgnoreCase("ACABOU"))
					break;
				
				int posicao = conteudoArquivo.indexOf("\n");
				String nomeArquivo = conteudoArquivo.substring(0, posicao);
				conteudoArquivo = conteudoArquivo.substring(posicao + 1);
				
				File arquivoDestino = new File("C:/Backup/_Particular/Furb/SD/MultCast/Backup/" + nomeArquivo);
				FileWriter writer = new FileWriter(arquivoDestino);
				writer.write(conteudoArquivo);
				writer.flush();
				writer.close();
				System.out.println("Mensagem: " + new String(pacoteArquivos.getData(), 0, pacoteArquivos.getLength()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (mSocket != null)
				mSocket.close();
		}
	}

}

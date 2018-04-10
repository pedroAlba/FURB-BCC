package trabalho01.servico;

import trabalho01.Constantes;
import trabalho01.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.MulticastSocket;
import java.net.SocketException;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Backup {

    public static InetAddress getServidorDisponivel() {

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

    public static void main(String[] args) {

        try (MulticastSocket mSocket = new MulticastSocket((Constantes.PORTA.toNumeric()))) {

            InetAddress servidor = getServidorDisponivel();

            mSocket.joinGroup(servidor);

            System.out.println("Servidor " + servidor.getHostName() + " pronto para receber os arquivos para fazer backup.");

            new Thread(() -> {
                while(true){
                    Utils.enviaPacote("Backup",servidor.getHostName(), servidor);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).run();

            byte[] dados = new byte[1000];

            while (true) {
                DatagramPacket pacoteArquivos = new DatagramPacket(dados, dados.length);
                mSocket.receive(pacoteArquivos);

                String conteudoArquivo = (new String(pacoteArquivos.getData()));
                if (conteudoArquivo.contains("ACABOU"))
                    break;

                int posicao = conteudoArquivo.indexOf("\n");
                String nomeArquivo = conteudoArquivo.substring(0, posicao);
                conteudoArquivo = conteudoArquivo.substring(posicao + 1);

                File arquivoDestino = Paths.get(Constantes.BASEPATH.toString(), "Backup", nomeArquivo).toFile();
                FileWriter writer = new FileWriter(arquivoDestino);
                writer.write(conteudoArquivo);
                writer.flush();
                writer.close();
                System.out.println("Mensagem: " + new String(pacoteArquivos.getData(), 0, pacoteArquivos.getLength()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

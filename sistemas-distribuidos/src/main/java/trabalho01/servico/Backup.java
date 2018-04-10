package trabalho01.servico;

import trabalho01.Constantes;
import trabalho01.Utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.*;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Backup {

    public static InetAddress getServidorDisponivel() throws UnknownHostException {
        return InetAddress.getByName(Constantes.BASEIP.toString());
    }

    public static void main(String[] args) {

        try (MulticastSocket mSocket = new MulticastSocket((Constantes.PORTA.toNumeric()))) {

            InetAddress servidor = getServidorDisponivel();

            mSocket.joinGroup(servidor);

            System.out.println("Servidor " + servidor.getHostName() + " pronto para receber os arquivos para fazer backup.");

            byte[] dados = new byte[1000];

            while (true) {
                System.out.println("Servidor aguardando pacotes");
                DatagramPacket pacoteArquivos = new DatagramPacket(dados, dados.length);
                mSocket.receive(pacoteArquivos);

                String conteudoArquivo = (new String(pacoteArquivos.getData()));

                //if (conteudoArquivo.contains("ACABOU"))
                 //   break;

                int posicao = conteudoArquivo.indexOf("\n");
                String nomeArquivo = conteudoArquivo.substring(0, posicao);
                conteudoArquivo = conteudoArquivo.substring(posicao + 1);

                File arquivoDestino = Paths.get(Constantes.BASEPATH.toString(), "Backup", nomeArquivo).toFile();
                FileWriter writer = new FileWriter(arquivoDestino);
                writer.write(conteudoArquivo);
                writer.flush();
                writer.close();
                System.out.println("Servidor recebeu arquivo: " + new String(pacoteArquivos.getData(), 0, pacoteArquivos.getLength()));
            }
        } catch (Exception e) {
            System.out.println("ERRO " + e.getMessage());
            e.printStackTrace();
        }
    }
}

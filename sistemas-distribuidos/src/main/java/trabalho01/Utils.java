package trabalho01;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class Utils {

    public static void enviaPacote(String remetente, String parMensagem, InetAddress parGrupo) {

        DatagramPacket pacote;
        DatagramSocket socket = null;

        try {
            pacote = new DatagramPacket(parMensagem.getBytes(), parMensagem.getBytes().length, parGrupo, Constantes.PORTA.toNumeric());
            socket = new DatagramSocket();
            socket.send(pacote);

            System.out.printf("%s enviou pacote %s", remetente, parMensagem);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            socket.close();
        }
    }

}

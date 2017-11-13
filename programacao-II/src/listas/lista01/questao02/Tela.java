package listas.lista01.questao02;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class Tela extends JFrame {

    private JPanel contentPane;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela frame = new Tela();
                    frame.setVisible(false);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Create the frame.
     */
    public Tela() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        // variaveis
        FileReader fileReader;
        ArrayList<String> linhas = new ArrayList<String>();
        BufferedReader leitorTextobuffer = null;

        File arquivoSelecionado = this.exibeMenuArquivo();

        if (!Utils.validaArquivoTexto(arquivoSelecionado)) {
            JOptionPane.showMessageDialog(null, "Arquivo inválido!");
            System.exit(0);
        }

        // pegar valor pra criptografia
        int valorCrip = Integer.valueOf(JOptionPane.showInputDialog(null, "Informe o valor:"));

        try {

            fileReader = new FileReader(arquivoSelecionado);
            leitorTextobuffer = new BufferedReader(fileReader);
            // Itera sobre todas as linhas do arquivo 
            while (leitorTextobuffer.ready()) {
                linhas.add(leitorTextobuffer.readLine());
            }

            ArrayList<String> linhasCriptografadas = Utils.criptografaLinhas(linhas, valorCrip);
            
            String nomeArquivo = arquivoSelecionado.getAbsolutePath().replaceAll(".txt", ".saida");
            
            Path filePath = Paths.get(nomeArquivo);
            
            Files.write(filePath, linhasCriptografadas, Charset.forName("UTF-8"));
            
            JOptionPane.showMessageDialog(this, "Arquivo criptografado com sucesso!");
                    
                  
            

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {

        }

    }

    public File exibeMenuArquivo() {
        final JFileChooser fc = new JFileChooser();
        fc.showOpenDialog(this);
        return fc.getSelectedFile();
    }

}

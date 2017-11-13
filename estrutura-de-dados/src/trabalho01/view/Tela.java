package trabalho01.view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import trabalho01.controller.Calculadora;

public class Tela extends JFrame {

    private JPanel contentPane;
    private JTextField txtExpressao;

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Tela frame = new Tela();
                    frame.setVisible(true);
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
        setTitle("Calculadora pós fixa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 293, 198);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JRadioButton rbFixa = new JRadioButton("Fixa (Vetor)");
        rbFixa.setBounds(41, 22, 109, 23);
        contentPane.add(rbFixa);

        JRadioButton rbDinamica = new JRadioButton("Dinâmica");
        rbDinamica.setBounds(152, 22, 109, 23);
        contentPane.add(rbDinamica);

        txtExpressao = new JTextField();
        txtExpressao.setBounds(41, 65, 188, 20);
        contentPane.add(txtExpressao);
        txtExpressao.setColumns(10);
        rbFixa.setSelected(true);

        JButton btCalcular = new JButton("Calcular");
        btCalcular.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                if (rbDinamica.isSelected())
                    calcularExpressao(false);
                else
                    calcularExpressao(true);
            }
        });
        btCalcular.setBounds(41, 110, 188, 23);
        contentPane.add(btCalcular);

        ButtonGroup buttonGroup1 = new javax.swing.ButtonGroup();

        buttonGroup1.add(rbDinamica);
        buttonGroup1.add(rbFixa);
    }

    protected void calcularExpressao(boolean pilhaEhDinamica) {
        Double result = null;
        try {
            if (txtExpressao.getText().trim().isEmpty())
                throw new Exception("Informe uma expressão!");

            if (txtExpressao.getText().length() < 3)
                throw new Exception("Expressão inválida");

            if (pilhaEhDinamica) {
                result = Calculadora.calculaFixo(txtExpressao.getText());
            } else {
                result = Calculadora.calculaDinamico(txtExpressao.getText());
            }
            JOptionPane.showMessageDialog(this, "Resultado: " + result);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}

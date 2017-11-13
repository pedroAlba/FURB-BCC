package slidePersistencia;

import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;
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
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @throws IOException 
	 */
	public Tela() throws IOException {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 678, 516);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane textPane = new JTextPane();
		textPane.setBounds(5, 34, 647, 433);
		contentPane.add(textPane);

		JLabel lblInformaesSobreO = new JLabel("Informa\u00E7\u00F5es sobre o diretorio atual ");
		lblInformaesSobreO.setBounds(5, 9, 211, 14);
		contentPane.add(lblInformaesSobreO);

		textPane.setText(Principal.getAbsolutPathInfo());
		Principal.createFileInAbsolutePath("teste.txt", "diretorioTeste");
	}
}

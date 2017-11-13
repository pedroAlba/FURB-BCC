package trabalho01.view;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

import trabalho01.controller.ArquivoController;
import trabalho01.model.SRTErrorException;
import trabalho01.model.SRTWarningException;

public class Tela extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private static final Logger LOGGER = Logger.getLogger(Tela.class.getName());
	File arquivo;
	private JTextField textArquivoSelecionado;

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

		setTitle("Validador de legendas");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 482, 183);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JRadioButton rbConsole = new JRadioButton("Console");
		rbConsole.setBounds(39, 39, 109, 23);
		contentPane.add(rbConsole);

		JRadioButton rbLogger = new JRadioButton("JVM Logger");
		rbLogger.setBounds(184, 39, 109, 23);
		contentPane.add(rbLogger);

		JRadioButton rbTexto = new JRadioButton("Arquivo texto");
		rbTexto.setBounds(318, 39, 109, 23);
		contentPane.add(rbTexto);

		JLabel lblInformeOTipo = new JLabel("Escolha o tipo de saída da analise!");
		lblInformeOTipo.setFont(new Font("Sitka Small", Font.PLAIN, 11));
		lblInformeOTipo.setBounds(132, 11, 246, 14);
		contentPane.add(lblInformeOTipo);

		ButtonGroup grupoDeBotoes = new javax.swing.ButtonGroup();
		grupoDeBotoes.add(rbTexto);
		grupoDeBotoes.add(rbLogger);
		grupoDeBotoes.add(rbConsole);

		// centraliza a tela
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);

		JButton btnAnalisar = new JButton("Analisar");
		btnAnalisar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (textArquivoSelecionado.getText().equals((""))) {
					return;
				}

				String mensagemDeErro = "";
				try {
					ArquivoController.analisaArquivo(arquivo);
				} catch (SRTWarningException warningE) {
					mensagemDeErro = warningE.getMessage();
				} catch (SRTErrorException errorE) {
					mensagemDeErro = errorE.getMessage();
				} catch (IOException e1) {
					System.out.println(e1.getMessage());
				}

				if (mensagemDeErro.equals("")) {
					System.out.println(arquivo.getName() + " - Arquivo válido");
				}
				if (rbConsole.isSelected()) {
					System.out.println(mensagemDeErro);
				} else if (rbLogger.isSelected()) {
					LOGGER.log(Level.FINE, mensagemDeErro, 0);
				} else {

					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setDialogTitle("Informe o arquivo de saída!");
					fileChooser.setCurrentDirectory(
							javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());
					if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {

						try {

							BufferedWriter out = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
							out.write(mensagemDeErro);
							out.close();
						} catch (Exception ex) {
							// TODO: handle exception
						}
					}
				}
			}
		});
		btnAnalisar.setBounds(184, 103, 89, 23);
		contentPane.add(btnAnalisar);

		textArquivoSelecionado = new JTextField();
		textArquivoSelecionado.setBounds(64, 69, 335, 23);
		contentPane.add(textArquivoSelecionado);
		textArquivoSelecionado.setColumns(10);

		JButton btnBtescolherarquivo = new JButton("...");
		btnBtescolherarquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				arquivo = getArquivo();
				textArquivoSelecionado.setText(arquivo.getName());
			}
		});

		btnBtescolherarquivo.setBounds(409, 69, 32, 23);
		contentPane.add(btnBtescolherarquivo);

	}



	private File getArquivo() {
		File arquivo = exibeMenuArquivo();

		if (!validaArquivoSRT(arquivo)) {
			JOptionPane.showMessageDialog(this, "Arquivo inválido!", "ERRO", JOptionPane.ERROR_MESSAGE);
			getArquivo();
		}
		return arquivo;

	}

	/**
	 * Valida se o arquivo passado por parametro é um arquivo de legenda srt.
	 * @param file Arquivo a ser verificado
	 * @return se o arquivo é valido ou não
	 */
	private boolean validaArquivoSRT(File file) {

		if (file.isDirectory())
			return false;

		if (!file.exists())
			return false;

		if (!file.getAbsolutePath().endsWith(".srt"))
			return false;

		return true;
	}

	/**
	 * Exibe um menu de seleção de arquivos
	 * 
	 * @return o arquivo selecionado
	 */
	private File exibeMenuArquivo() {
		JFileChooser fc = new JFileChooser();

		fc.setCurrentDirectory(javax.swing.filechooser.FileSystemView.getFileSystemView().getHomeDirectory());

		fc.setFileFilter(new FileNameExtensionFilter("Arquivos srt", "srt"));

		if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
			return fc.getSelectedFile();
		} else {
			System.exit(0);
		}
		return null;
	}
}

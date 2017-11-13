package listas.lista01.questao05;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import listas.lista01.questao05.controller.FormasController;
import listas.lista01.questao05.model.Circulo;
import listas.lista01.questao05.model.Forma;
import listas.lista01.questao05.model.Retangulo;
import listas.lista01.questao05.model.Triangulo;


public class Tela extends JFrame {

	private JPanel contentPane;
	private ArrayList<Forma> listaDeFormas;
	private File file;

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
	 * 
	 * @throws IOException
	 * @throws ClassNotFoundException
	 * @throws FileNotFoundException
	 */
	public Tela() throws FileNotFoundException, ClassNotFoundException, IOException {
		listaDeFormas = new ArrayList<Forma>();

		file = new File("c:\\Temp\\Persistencia.dat");
		if (file.exists()) {
			listaDeFormas = FormasController.carregarFormas(null);
		}

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 195, 302);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btCirculo = new JButton("Ciruclo");
		btCirculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				criaCirculo();
			}

		});
		btCirculo.setBounds(30, 56, 113, 23);
		contentPane.add(btCirculo);

		JButton btRetangulo = new JButton("Retangulo");
		btRetangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaRetangulo();
			}

		});
		btRetangulo.setBounds(30, 90, 113, 23);
		contentPane.add(btRetangulo);

		JButton btTriangulo = new JButton("Triangulo");
		btTriangulo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				criaTriangulo();
			}
		});
		btTriangulo.setBounds(30, 120, 113, 23);
		contentPane.add(btTriangulo);

		JButton btSalvar = new JButton("Salvar");
		btSalvar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					FormasController.salvarFormas(listaDeFormas, null);
				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}
		});
		btSalvar.setBounds(30, 154, 113, 23);
		contentPane.add(btSalvar);

		JButton btnImprime = new JButton("Imprime");
		btnImprime.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exibeFormas();
			}
		});
		btnImprime.setBounds(30, 188, 113, 23);
		contentPane.add(btnImprime);
	}

	protected void exibeFormas() {

		listaDeFormas.forEach(s -> System.out.println(s + "\n"));

	}

	protected void criaTriangulo() {

		double lado1, lado2, lado3;

		lado1 = Integer.valueOf(JOptionPane.showInputDialog("Informe o lado 1 do triangulo"));
		lado2 = Integer.valueOf(JOptionPane.showInputDialog("Informe o lado 2 do triangulo"));
		lado3 = Integer.valueOf(JOptionPane.showInputDialog("Informe o lado 3 do triangulo"));

		Triangulo t = new Triangulo(lado1, lado2, lado3);
		listaDeFormas.add(t);

	}

	protected void criaRetangulo() {
		double lado, altura;
		lado = Integer.valueOf(JOptionPane.showInputDialog("Informe o lado  do retangulo"));
		altura = Integer.valueOf(JOptionPane.showInputDialog("Informe a altura do retangulo"));

		Retangulo r = new Retangulo(lado, altura);
		listaDeFormas.add(r);

	}

	private void criaCirculo() {

		double raio;
		raio = Integer.valueOf(JOptionPane.showInputDialog("Informe o raio do circulo"));
		Circulo c = new Circulo(raio);
		listaDeFormas.add(c);
	}

}

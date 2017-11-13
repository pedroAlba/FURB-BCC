package prova01.Testes;

import static org.junit.Assert.assertEquals;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import prova01.controller.SerializacaoController;
import prova01.model.Atleta;
import prova01.model.EArquivoOrigemNaoExiste;
import prova01.model.Endereco;

public class Testes {

	@Test
	public void PL001() throws IOException {

		String arqInput = "C:\\Users\\palba\\Desktop\\Prova01 - Arquivos\\dados.csv.txt";
		String arqOutput = "C:\\Users\\palba\\Desktop\\Prova01 - Arquivos\\dados.csv.output";

		List<String> lines = Arrays.asList(
				"Jose dos Santos, 1.92, 98.3, Av. Brasil, 123, Apto.203, 89020-120, Blumenau,SC",
				"Aparecida Maria, 1.70, 65.7, Rua Joao Pessoa, 500, Apt 1001, 89010-200, Blumenau, SC");
		
		Path file = Paths.get(arqInput);

		if (file.toFile().exists()) {
			file.toFile().delete();
		}

		Files.write(file, lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE);

		ArrayList<Atleta> atletas = null;
		try {
			SerializacaoController.serializar(arqInput, arqOutput);
		} catch (EArquivoOrigemNaoExiste e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {

			ObjectInputStream input = new ObjectInputStream(new FileInputStream(arqOutput));

			Object retornoDoArquivo = input.readObject();

			atletas = (ArrayList<Atleta>) retornoDoArquivo;

			input.close();

		} catch (Exception e) {
		}

		Atleta joseEsperado = new Atleta("Jose dos Santos", 1.92f, 98.3f,
				new Endereco("Av. Brasil", "123", "Apto.203", "89020-120", "Blumenau", "SC"));

		Atleta aparecidaEsperado = new Atleta("Aparecida Maria", 1.70f, 65.7f,
				new Endereco("Rua Joao Pessoa", "500", "Apt 1001", "89010-200", "Blumenau", "SC"));

		Atleta jose = atletas.get(0);
		Atleta aparecida = atletas.get(1);

		assertEquals(joseEsperado, jose);
		assertEquals(aparecidaEsperado, aparecida);

	}

	@Test(expected = EArquivoOrigemNaoExiste.class)
	public void PL02() throws EArquivoOrigemNaoExiste, IOException {

		SerializacaoController.serializar("c:\\Temp\\ArquivoInexistenteDeveLancarExeccao", "C:\\temp");

	}
	@Test
	public void teste() {
		
		try {
			SerializacaoController.serializar("c:\\Temp\\asdasdasdasd", "C:\\temp");
			assertEquals(true, false);
		} catch (Exception e) {
			assertEquals(EArquivoOrigemNaoExiste.class, e.getClass());
		}
	}

}

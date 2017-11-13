package slidePersistencia;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.BasicFileAttributes;

public class Principal {

	public static String getAbsolutPathInfo() throws IOException {

		String info = "";
		Path atual = Paths.get("");
		String diretorioAbsoluto = atual.toAbsolutePath().toString();
		info += "Diretorio atual : " + diretorioAbsoluto;

		BasicFileAttributes atributos = Files.readAttributes(atual, BasicFileAttributes.class);

		info += "\n Ultimo acesso " + atributos.lastAccessTime();
		info += "\n Ultima Modificacao " + atributos.lastModifiedTime();
		info += "\n Tamanho " + atributos.size();

		DirectoryStream<Path> stream = Files.newDirectoryStream(atual);

		info += "\nArquivos encontrados :";

		for (Path path : stream) {
			info += "\n\t" + path;
		}
		return info;
	}

	public static void createFileInAbsolutePath(String nomeDoArquivo, String nomeDiretorio) throws IOException {

		Path arqNovo = Paths.get("arquivoTexto.txt");
		Files.deleteIfExists(arqNovo);
		Files.createFile(arqNovo);

		Path dirNovo = Paths.get("Teste");
		if (!Files.exists(dirNovo)) {
			Files.createDirectory(dirNovo);
		}

		Files.move(arqNovo, dirNovo.resolve(arqNovo), StandardCopyOption.REPLACE_EXISTING);
	}

}

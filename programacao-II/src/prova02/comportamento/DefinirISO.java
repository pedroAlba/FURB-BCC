package prova02.comportamento;
/**
*
* @author Pedro Henrique Pereira Alba
*/
import java.util.List;

import prova02.Autor;
import prova02.Obra;

public class DefinirISO implements DefinirFormatacao {

	@Override
	public String definirFormatacaoBibliografica(Obra obra) {
		StringBuilder bibliografia = new StringBuilder();
		List<Autor> autores = obra.getAutores();

		if (autores.size() > 1) {
			Autor primeiro = autores.get(0);
			bibliografia.append(primeiro.getSobrenome());
			bibliografia.append(", " + primeiro.getNome());
			bibliografia.append("et al. ");
			bibliografia.append(obra.getAnoPublicacao() + ".");
			bibliografia.append(obra.getTitulo());
			bibliografia.append(": ");
			bibliografia.append(obra.getSubtitulo() + ". ");
			bibliografia.append(obra.getCidade() + ": ");
			bibliografia.append(obra.getEditora() + ", ");
			bibliografia.append(obra.getAnoPublicacao() + ". ");
		}

		return bibliografia.toString();
	}

	@Override
	public String definirFormatacaoReferencias(Obra obra) {
		StringBuilder referencia = new StringBuilder();
		referencia.append("(");
		Autor primeiro = obra.getAutores().get(0);

		if (primeiro != null)
			referencia.append(primeiro.getSobrenome().toUpperCase() + " ");

		if (obra.getAutores().size() > 1)
			referencia.append(" et al., ");

		referencia.append(obra.getAnoPublicacao());
		return referencia.toString() + ")";
	}

}

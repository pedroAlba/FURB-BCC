package prova02;
/**
*
* @author Pedro Henrique Pereira Alba
*/
import prova02.comportamento.DefinirABNT;
import prova02.comportamento.DefinirFormatacao;
import prova02.comportamento.DefinirISO;

public class Formatador implements DefinirFormatacao {

	public static Formatador instance;	
	private DefinirFormatacao formatacao;
	
	private Formatador(){}
	private Formatador(TipoFormatacao t){
		if(t.name().equals("ABNT_NBR_6023_2002"))
			formatacao = new DefinirABNT();
		if(t.name().equals("ISO_690"))
			formatacao = new DefinirISO();
	}
	
	public static Formatador getInstance(TipoFormatacao tipo) {
		if(instance == null)
			instance = new Formatador(tipo);	
		
		instance.formatacao = getFormatFromEnum(tipo);
		return instance;
	}
	
	@Override
	public String definirFormatacaoBibliografica(Obra obra) {
		return formatacao.definirFormatacaoBibliografica(obra);
	}

	@Override
	public String definirFormatacaoReferencias(Obra obra) {
		return formatacao.definirFormatacaoReferencias(obra);
	}

	private static DefinirFormatacao getFormatFromEnum(TipoFormatacao t){
		if(t.name().equals("ABNT_NBR_6023_2002"))
			return  new DefinirABNT();
		if(t.name().equals("ISO_690"))
			return  new DefinirISO();
		return null;
	}
}

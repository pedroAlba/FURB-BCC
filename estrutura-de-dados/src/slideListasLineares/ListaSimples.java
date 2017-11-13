package slideListasLineares;
public interface ListaSimples {

	void insere(String objeto);

	void insere(String objeto, int posicao) throws Exception;
	
	void insereInicio(String objeto);

	String retira(int posicao);

	int localiza(String objeto) throws Exception;

	String imprime();

	ListaComArray concatena(ListaComArray parLista);

	ListaComArray divide(ListaComArray parLista);

	ListaComArray copia(ListaComArray parLista);

}

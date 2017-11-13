package trabalhoFinal;

public class ListaDuplamenteEncadeada<T> implements Lista<T> {
	 private ElementoLista<T> primeiro;
	 private ElementoLista<T> anterior;
	 private ElementoLista<T> ultimo;
     private int qtdElementos = 0;
	    
	    public ListaDuplamenteEncadeada() {
	    }
	    
	    @Override
	    public void insere(T x){
	        ElementoLista novo = new ElementoLista();
	        novo.setInformacao(x);
	        
	        if (this.estaVazia()){
	            primeiro = novo;
	        } else {
	            ultimo.setProx(novo);
	        }
	        ultimo = novo;
	        qtdElementos++;
	    }
	    
	    public boolean estaCheia() {
	        return false;
	    }
	    
	    public boolean estaVazia() {
	        return (primeiro == null);
	    }
	    
	    public void insere(T x, int p){
	        if (p >= 0 
	            && p <= this.qtdElementos){
	            if (p == this.qtdElementos) {// ultima posição
	                this.insere(x);
	            } else {
	                ElementoLista novo = new ElementoLista();
	                novo.setInformacao(x);
	                ElementoLista elemE;
	                if (p == 0){ // primeira posição
	                    novo.setProx(primeiro);
	                    primeiro = novo;
	                } else { // posição intermediária
	                    elemE = this.consultaInterno(p-1);
	                    novo.setProx(elemE.getProx());
	                    elemE.setProx(novo);
	                }
	                qtdElementos++;
	            }
	        }
	    }
	    
	    private ElementoLista<T> consultaInterno(int p){
	        if (p >= 0 && p < this.qtdElementos){  // posi��o procurada � v�lida
	            ElementoLista proximo = primeiro;
	            
	            for (int i=0; i < p; i++){
	                proximo = proximo.getProx();
	            }
	            return proximo;
	        } else {
	            return null;
	        }
	    }
	    
	    public String imprime() {
	        String retorno = "[";
	        ElementoLista proximo = primeiro;
	        
	        while (proximo != null){
	            retorno += proximo.getInformacao()+"; ";
	            proximo = proximo.getProx();
	        }

	        try {
	            // para retirar a última vírgula e espaço
	            retorno = retorno.substring(0,retorno.length()-2);
	            return retorno+"]";
	        } catch (StringIndexOutOfBoundsException strExc){
	            return "[]";
	        }
	    }


		
		public T retira(int p) {
			if (p == 0){
				T retorno = this.primeiro.getInformacao();
				this.primeiro = primeiro.getProx();
				qtdElementos--;
				return retorno;
			} else if(p > 0 && p <qtdElementos){
				ElementoLista<T> elementoLista =  this.consultaInterno(p - 1);
				ElementoLista<T> elementoRemovido = elementoLista.getProx();
				elementoLista.setProx(elementoRemovido.getProx());
				qtdElementos--;
	                        return elementoRemovido.getInformacao();
			}else if (p == qtdElementos){
				T retorno = this.ultimo.getInformacao();
				ElementoLista novoUltimo = this.consultaInterno(p -1);
				this.ultimo = novoUltimo;
				novoUltimo.setProx(null);
				qtdElementos--;
				return retorno;
			}
			return null;
			
		}

		@Override
		public int localiza(T x) {
			ElementoLista elementoLista = primeiro;
			int i = 0;
			while ((elementoLista != null)){
				if (elementoLista.getInformacao().equals(x))
					return i;
				i++;
				elementoLista = elementoLista.getProx();
			}
			
			return -1;
		}

		@Override
		public int getTamanho() {
			return qtdElementos;
		}

		@Override
		public T consulta(int p) {
			return this.consultaInterno(p).getInformacao();
		}

		@Override
		public Lista concatena(Lista outra) {
			Lista novalista = new ListaEncadeada();
			
			ElementoLista elementoLista = this.consultaInterno(0);
			while ((elementoLista != null)){
				novalista.insere(elementoLista.getInformacao());
				elementoLista = elementoLista.getProx();
			}
			
			for(int i = 0; i < outra.getTamanho() ; i++){
				novalista.insere(outra.consulta(i));
			}
			
			return novalista;
		}

	    @Override
	    public Lista divide() {
	        if (this.estaVazia())
	            return null;
	        int tamanhoOriginal = this.getTamanho();
	        Lista nova = new ListaEncadeada();
	        int meio =  tamanhoOriginal/ 2;

	        for (int i = meio; i < tamanhoOriginal; i++){
	            nova.insere(this.consulta(meio));
	            this.retira(meio);
	        }
	        return nova;
	    }

	    @Override
	    public Lista copia() {		
			ElementoLista elementoLista = primeiro;
			Lista lista = new ListaEncadeada();
			while ((elementoLista != null)){
				lista.insere(elementoLista.getInformacao());
				elementoLista = elementoLista.getProx();
			}
			return lista;
		}
		
	private class ElementoLista<T> {

	    private T informacao;

	    private ElementoLista prox;

	    public T getInformacao() {
	        return informacao;
	    }

	    public void setInformacao(T informacao) {
	        this.informacao = informacao;
	    }

	    public ElementoLista getProx() {
	        return prox;
	    }

	    public void setProx(ElementoLista prox) {
	        this.prox = prox;
	    }

		    
		    
		    
		}
	}


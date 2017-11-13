package trabalho01.model;

public class PilhaDinamica<T> implements Pilha<T> {
	   private ElementoLista<T> topo;
	    private ElementoLista<T> anterior;
	    private int qtdElementos = 0;

		@Override
		public void push(T v) throws Exception {
			ElementoLista<T> novo = new ElementoLista<>();
			novo.setInformacao(v);
			
			if(this.vazia()){
				topo = novo;
				topo.setAnterior(null);
				qtdElementos++;
			}
			else {
				novo.setAnterior(topo);
				topo = novo;
				qtdElementos++;
			}
			
		}

		@Override
		public T pop() throws Exception {
		      if(topo == null){
		          throw new Exception("A pilha está vazia");
		       }
		       else{
		    	  ElementoLista<T> temp = new ElementoLista<>();
		          temp = topo;
		          topo = topo.getAnterior();
		          return (T) temp.getInformacao();
		       }
		    }

		@Override
		public T peek() throws Exception {
			if(topo == null)
				throw new Exception("A pilha está vazia");
			return topo.getInformacao();
		}

		@Override
		public boolean vazia() {
			return (topo == null);
		}

		@Override
		public void libera() {
			for(int i = 0; i == qtdElementos ; i++){
				anterior = topo;
				topo = null;
			}
			
		}
		
		private class ElementoLista<T> {

			private T informacao;

			private ElementoLista anterior;

			public T getInformacao() {
				return informacao;
			}

			public void setInformacao(T informacao) {
				this.informacao = informacao;
			}

			public ElementoLista getAnterior() {
				return anterior;
			}

			public void setAnterior(ElementoLista anterior) {
				this.anterior = anterior;
			}

		}
}

package slideListasLineares;
public class Nodo<T> {
		private T info;
		private Nodo prox;
		private Nodo prev;
		
		public T getInfo() {
			return info;
		}

		public void setInfo(T info) {
			this.info = info;
		}

		public Nodo getProx() {
			return prox;
		}

		public void setProx(Nodo prox) {
			this.prox = prox;
		}
		
		public Nodo getPrev() {
			return prev;
		}
		
		public void setPrev() {
			this.prev = prev;
	}
}
package pilha;

public class Principal {

	public static void main(String[] args) throws Exception {

		PilhaVetor<Integer> p = new PilhaVetor<>(10);
		
		p.push(
				1);
		p.push(2);
		p.push(3);
		p.push(4);
		p.push(5);		
		
		p.pop();
		
		System.out.println(p.peek());
		System.out.println(p.vazia());
		
		p.libera();
		
		System.out.println(p.vazia());

		
		System.out.println(p.peek());
		
	}

}

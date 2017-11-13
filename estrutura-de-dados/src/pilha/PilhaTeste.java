package pilha;

public class PilhaTeste {

	public static void main(String[] args) throws Exception {
		
		PilhaDinamica<String> p = new PilhaDinamica<>();
		
		p.push("A");
		p.push("B");
		p.push("C");
		
		System.out.println(p.pop());
		System.out.println(p.pop());
		
		System.out.println(p.peek());
		System.out.println(p.peek());
		
		p.push("J");
		
		System.out.println(p.peek());
	}
}

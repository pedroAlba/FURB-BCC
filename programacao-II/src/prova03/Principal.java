package prova03;
/**
 * 
 * @author Pedro Alba
 *
 */
public class Principal {

	public static void main(String[] args) {
	
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		
		p1.setCodigo(1);
		p1.setDescricao("Primeiro produto");
		p1.setQtdEstoque(10);
		p1.setValor(10);
		
		p2.setCodigo(2);
		p2.setDescricao("Segundo produto");
		p2.setQtdEstoque(20);
		p2.setValor(20);
		
		p3.setCodigo(50);
		p3.setDescricao("Terceiro produto");
		p3.setQtdEstoque(30);
		p3.setValor(30);
		
		
		Deposito d = new Deposito();
//		d.incluirProduto(p1);
//		d.incluirProduto(p2);
//		d.incluirProduto(p3);
		
		//System.out.println(d.buscarProduto(5));
		System.out.println("Quantidade protudos " + d.getQtdProdutos());
		System.out.println("Unidades em estoque " + d.getUnidadeEstoque());
		System.out.println("Maior codigo " + d.getUltimoCodigo());
		System.out.println("Remocao codigo existente " + d.excluirProduto(1));
		System.out.println("Remocao codigo inexistente " + d.excluirProduto(5410));
	}
}

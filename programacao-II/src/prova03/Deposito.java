package prova03;
/**
 * 
 * @author Pedro Alba
 *
 */
import java.util.Hashtable;
import java.util.Iterator;
import java.util.stream.Collectors;

public class Deposito {

	private Hashtable<Integer,Produto> produtos;
	
	public Deposito(){
		produtos = new Hashtable<>();
	}
	
	public void incluirProduto(Produto p){
		produtos.putIfAbsent(p.getCodigo(), p);
	}
	
	public Produto buscarProduto(int i){
		return produtos.get(i);
	}
	
	public boolean excluirProduto(int i){
		return produtos.remove(i) == null ? false:true;
	}
	
	public int getQtdProdutos(){
		return produtos.values().size();
	}
	
	public int getUnidadeEstoque(){
		return produtos.values()
					   .stream()
					   .map(Produto::getQtdEstoque)
					   .collect(Collectors.toList())
					   					  .stream()
					   					  .mapToInt(i -> i.intValue()).sum();
	}
	
	public Iterator<Produto> iterador(){
		return produtos.values().iterator();
	}
	
	public int getUltimoCodigo(){
		
		if(getQtdProdutos() == 0)
			return -1;
		
		return produtos.values()
					   .stream()
					   .map(Produto::getCodigo)
					   .collect(Collectors.toList())
					   					  .stream()
					   					  .max(Integer::compare)
					   					  .get();
		
	}
	
}

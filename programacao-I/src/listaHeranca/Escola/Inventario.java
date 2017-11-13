package listaHeranca.Escola;

import java.util.ArrayList;

public class Inventario implements IO{
	
	ArrayList<Material> materiais;
	
	Inventario(){
		 materiais = new ArrayList<Material>();
	}

	public ArrayList<Material> getInventario() {
		return materiais;
	}

	public void setInventario(ArrayList<Material> inventario) {
		this.materiais = inventario;
	}
	
	@Override
	public void salvar(Material m, String s) {
		materiais.add(m);
		System.out.println("salvou " + s );
		
	}

	@Override
	public String ler() {
		
		String s = "Listagem de todos os materiais. \n";
		
		for (Material material : materiais) {
			
			if (material instanceof Caneta){
				Caneta c = (Caneta) material;
				s += c.toString() + " " + c.getFinalidade() + " " +  c.getCor() + "\n";
			}
			if (material instanceof Giz){
				Giz g = (Giz) material;
				s += g.toString() + " " +  g.getFinalidade() + " " +  g.getCorDaCaixa() + "\n";
			}
			if (material instanceof Apagador){
				Apagador a = (Apagador) material;
				s += a.toString() + " " + a.getFinalidade() + "\n";
			}
		}
		return s;
	}
	
	
	
}

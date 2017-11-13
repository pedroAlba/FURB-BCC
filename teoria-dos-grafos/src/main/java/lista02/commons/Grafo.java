package lista02.commons;
/**
 * 
 * @author Pedro Alba
 * @author Renato Constancio
 */
import java.util.ArrayList;

public class Grafo {

	private ArrayList<No> nos;
	private ArrayList<Aresta> arestas;

	public Grafo() {
		nos = new ArrayList<>();
		arestas = new ArrayList<>();
	}

	public void adicionaNo(No n) {
		if(!nos.contains(n))
			nos.add(n);
	}

	public void adicionaAresta(Aresta a) {
		if(!arestas.contains(a)) {
			arestas.add(a);
			No origemTemp = a.getOrigem();
			No destinoTemp = a.getDestino();
			if(!nos.contains(origemTemp))
				nos.add(origemTemp);
			if(!nos.contains(destinoTemp))
				nos.add(destinoTemp);
		}

	}

//	public boolean completo(Integer[][] matAdj) {
//		for (int i = 0; i < matAdj.length; i++) {
//			for (int j = 0; j < matAdj[0].length; j++) {
//				if(i==j && !matAdj[i][j] == 0))
//					return false;
//				else
//					if(!matAdj[i][j].equals("1"))
//						return false;
//			}
//		}
//		return true;
//	}

	public boolean regular() {
		int qtdNos0 = nos.get(0).getAdjacentes().size();
		for (No no : nos) {
			if(no.getAdjacentes().size() != qtdNos0)
				return false;
		}
		return true;
	}

	public boolean nulo() {
		if(nos.isEmpty())
			return true;
		return false;
	}

	public boolean semAresta() {
		if(arestas.isEmpty())
			return true;
		return false;
	}

	public ArrayList<No> listaAdjacencias(){
		ArrayList<No> retorno = new ArrayList<>();
		for (int i = 0; i < nos.size(); i++) {
			No temp = nos.get(i);
			for (int j = 0; j < arestas.size(); j++) {
				if(arestas.get(j).getOrigem().equals(temp))
					temp.addAdjacentes(arestas.get(j).getDestino());
			}
			if(temp != null)
				retorno.add(temp);
		}
		return retorno;
	}




}

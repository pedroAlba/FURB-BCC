package arvoreBinaria;

public class ArvoreBST<T extends Comparable> extends ArvoreBinariaAbstract<T> {

    public ArvoreBST() {

    }

    public void inserir(T info) {
        if (raiz == null)
            raiz = new NoArvoreBST<T>(info);
        else
            ((NoArvoreBST<T>) raiz).inserir(info);
    }

    public NoArvoreBST<T> busca(T info) {
        NoArvoreBST<T> retorno = null;
        if (raiz != null)
            retorno = ((NoArvoreBST<T>) raiz).busca(info);
        return retorno;
    }

    public boolean retirar(T info) {

        NoArvoreBST<T> no = busca(info);
        NoArvoreBST<T> pai = ((NoArvoreBST<T>) raiz).buscaPai(info, no);

        boolean ehFolha = no.getDir() == null && no.getEsq() == null;
        boolean temUmFilho = no.getDir() != null || no.getEsq() != null;
        boolean temDoisFilhos = no.getDir() != null && no.getEsq() != null;

        if (ehFolha) {
            retiraFolha(info, pai);
        } else if (temDoisFilhos) {
            retiraComDoisFilhos(info, pai, no);
        } else if (temUmFilho) {
            retiraComUmFilho(info, pai, no);
        }
        return true;

    }

    private void retiraFolha(T info, NoArvoreBST<T> pai) {

        if (pai.getDir() != null)
            if (pai.getDir().getInfo().equals(info))
                pai.setDir(null);

        if (pai.getEsq() != null)
            if (pai.getEsq().getInfo().equals(info))
                pai.setEsq(null);

    }

    private void retiraComUmFilho(T info, NoArvoreBST<T> pai, NoArvoreBST<T> no) {

        NoArvoreBST<T> noEsquerda = (NoArvoreBST<T>) no.getEsq();
        NoArvoreBST<T> noDireita = (NoArvoreBST<T>) no.getDir();
        
        if (pai.getDir() != null && pai.getDir().getInfo().equals(info)) { // Se o nó esta a direita do pai

            if (noEsquerda != null)
                pai.setDir(noEsquerda);
            else            
                pai.setDir(noDireita);
            
        } else { 

            if (noEsquerda != null)
                pai.setEsq(noEsquerda);
            else            
                pai.setEsq(noDireita);
        }
    }
    
    private void retiraComDoisFilhos(T info, NoArvoreBST<T> pai, NoArvoreBST<T> no) {
        NoArvoreBST<T> sucessor = getSucessor(no);
        
        NoArvoreBST<T> paiDoSucessor = null;
        
        if(sucessor != null){
            paiDoSucessor = ((NoArvoreBST<T>) raiz).buscaPai((T)sucessor.getInfo(),sucessor);
        }
        
        if(paiDoSucessor == null)
            return;
        
        
        if(pai.getDir() != null && pai.getDir().getInfo().equals(info)){
            pai.getDir().setInfo(sucessor.getInfo());
        }else
            pai.getEsq().setInfo(sucessor.getInfo());
        
        if(paiDoSucessor.getDir() != null && paiDoSucessor.getDir().getInfo().equals(info))
            paiDoSucessor.setDir(null);
        else
            paiDoSucessor.setEsq(null);
    }

    private NoArvoreBST<T> getSucessor(NoArvoreBST<T> no){
        
        NoArvoreBST<T> direita = (NoArvoreBST<T>) no.getDir();        
        
        
        return null;

    }

}

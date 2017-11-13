package listas.listaRevisao.questao01.resolucao;


/**
 *
 * @author marcel
 */
public class SemPrimeiroException extends IllegalArgumentException{

    public SemPrimeiroException(){
        super("Não há primeiro membro da família");
    }

}

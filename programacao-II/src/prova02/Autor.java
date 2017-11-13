package prova02;
/** ESTA CLASSE NÃO PODE SER ALTERADA
 *  E NÃO PRECISA SER ENTREGUE.
 * @author Marcel
 */
public class Autor {
    private String sobrenome;
    private String nome;
    private int anoNascimento;

    public Autor(String sobrenome, String nome, int anoNascimento) {
        this.setSobrenome(sobrenome);
        this.setNome(nome);
        this.setAnoNascimento(anoNascimento);
    }

    
    public String getSobrenome() {
        return sobrenome;
    }

    public final void setSobrenome(String sobrenome) {
        if (sobrenome == null
            || sobrenome.length() < 2){
            throw new IllegalArgumentException("Sobrenome do autor inválido. Deve haver ao menos dois caracteres.");
        }
        else {
            this.sobrenome = this.converte(sobrenome);            
        }
    }

    public String getNome() {
        return nome;
    }

    public final void setNome(String nome) {
        if (nome == null
            || nome.isEmpty()){
            throw new IllegalArgumentException("Nome do autor inválido.");
        }
        else {
            this.nome = this.converte(nome);
        }
    }

    private String converte(String str){
        str = str.toLowerCase();
        str = str.substring(0,1).toUpperCase()+str.substring(1);
        return str;
    }
    
    public int getAnoNascimento() {
        return anoNascimento;
    }

    public final void setAnoNascimento(int anoNascimento) {
        this.anoNascimento = anoNascimento;
    }
    
    
}

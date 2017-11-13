package prova02;
/** ESTA CLASSE NÃO PODE SER ALTERADA
 *  E NÃO PRECISA SER ENTREGUE.
 * @author Marcel
 */

import java.util.ArrayList;
import java.util.List;

public class Obra {
    private String titulo;
    private String subtitulo;
    private String editora;
    private String cidade;
    private int anoPublicacao;
    private ArrayList<Autor> autores = new ArrayList();

    public Obra(String titulo, String subtitulo, String editora, String cidade, int anoPublicacao) {
        this.setTitulo(titulo);
        this.setSubtitulo(subtitulo);
        this.setEditora(editora);
        this.setCidade(cidade);
        this.setAnoPublicacao(anoPublicacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null
            || titulo.length() < 5){
            throw new IllegalArgumentException("Título da obra inválido. Deve haver ao menos cinco caracteres.");
        }
        this.titulo = titulo;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public int getAnoPublicacao() {
        return anoPublicacao;
    }

    public void setAnoPublicacao(int anoPublicacao) {
        this.anoPublicacao = anoPublicacao;
    }
        
    public void addAutor(Autor a){
        if (a == null){
            throw new IllegalArgumentException("Autor deve ser válido.");
        }
        else {
            autores.add(a);
        }            
    }

    public List<Autor> getAutores(){
        return (List<Autor>)this.autores.clone();
    }    
    
    public String getBibliografia(){
            return this.autores.get(0).getSobrenome().toUpperCase()
                   +". "+this.titulo+".";
    }

    public String getCitacao(){
            return this.autores.get(0).getSobrenome().toUpperCase()
                   + ", "+this.anoPublicacao+".";
    }
}

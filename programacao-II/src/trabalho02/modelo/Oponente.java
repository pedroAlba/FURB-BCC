package trabalho02.modelo;
import trabalho02.comportamento.Comportamento;
import trabalho02.comportamento.DefinirComportamentoAgressivo;
import trabalho02.comportamento.DefinirComportamentoDefensivo;
import trabalho02.comportamento.DefinirComportamentoNormal;
import trabalho02.comportamento.DefinirComportamentoOponente;

public class Oponente {

	private String nome;
	private Boolean armado;
	private int vida;
	private DefinirComportamentoOponente comportamento;

	public Oponente(Boolean armado, Comportamento comportamento, String nome) {
		setArmado(armado);
		setTipoComportamento(comportamento);
		setVida(10);
		setNome(nome);
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Boolean getArmado() {
		return armado;
	}

	protected void setArmado(Boolean armado) {
		this.armado = armado;
	}

	protected int getVida() {
		return vida;
	}

	protected void setVida(int vida) {
		this.vida = vida;
	}

	protected DefinirComportamentoOponente getComportamento() {
		return comportamento;
	}

	protected void setComportamento(DefinirComportamentoOponente comportamento) {
		this.comportamento = comportamento;
	}
	
	public void setComportamento(Comportamento c){
		setTipoComportamento(c);
	}

	private void setTipoComportamento(Comportamento c) {
		if (c.name().equals("DEFENSIVO"))
			setComportamento(new DefinirComportamentoDefensivo());
		if (c.name().equals("NORMAL"))
			setComportamento(new DefinirComportamentoNormal());
		if (c.name().equals("AGRESSIVO"))
			setComportamento(new DefinirComportamentoAgressivo());
	}

	@Override
	public String toString() {
		return (armado? "Estou armado\n" : "Estou desarmado\n") + this.comportamento.definirComportamento(this);
	}

	
}

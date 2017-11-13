package listas.listaApoio;

import java.util.Stack;

public class Validador {
	private String expressao;

	public Validador(String pExpressao) {
		this.expressao = pExpressao;
	}

	public boolean validarFormacao() {        
        Stack controle = new Stack();
        for (int i = 0; i < this.expressao.length(); i++) {
            if (this.expressao.charAt(i) == '{' || this.expressao.charAt(i) == '[' || this.expressao.charAt(i) == '(') {
                controle.push(this.expressao.charAt(i));
            } else if (this.expressao.charAt(i) == ')' || this.expressao.charAt(i) == ']' || this.expressao.charAt(i) == '}') {
                if (controle.isEmpty()) {
                    return false;
                } else if (this.expressao.charAt(i) == ')' && controle.peek().equals('(')) {
                    controle.pop();
                    continue;
                } else if (this.expressao.charAt(i) == ']' && controle.peek().equals('[')) {
                    controle.pop();
                    continue;
                } else if (this.expressao.charAt(i) == '}' && controle.peek().equals('{')) {
                    controle.pop();
                    continue;
                }
                return false;
            }
        }
        return controle.isEmpty(); 
        
    }

	public String getExpressao() {
		return expressao;
	}

}

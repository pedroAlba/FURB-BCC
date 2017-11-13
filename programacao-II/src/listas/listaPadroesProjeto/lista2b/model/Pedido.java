package listas.listaPadroesProjeto.lista2b.model;

import java.util.ArrayList;
import java.util.Date;

import listas.listaPadroesProjeto.lista2b.comportamento.DefinirValorEnvio;

public class Pedido {

    private int numero;
    private String nomecliente;
    private Date data;
    private String endereco;
    ArrayList<ItemDePedido> produtos;
    DefinirValorEnvio dv;

    public Pedido(DefinirValorEnvio dv) {
        produtos = new ArrayList<>();
        this.dv = dv;
    }

    public DefinirValorEnvio getDv() {
        return dv;
    }

    public void setDv(DefinirValorEnvio dv) {
        this.dv = dv;
    }

    public void incluirItem(Produto p, int quantidade) {
        ItemDePedido i = new ItemDePedido();
        i.setProduto(p);
        i.setQuantidade(quantidade);
        produtos.add(i);
    }

    public double getValorPedido() throws Exception {
        double valorDosProdutos = 0;
        for (ItemDePedido itemDePedido : produtos) {
            int qtdItens = itemDePedido.getQuantidade();
            double valor = itemDePedido.getProduto().getValor();
            valorDosProdutos += qtdItens * valor;
        }
        double pesoTotal = calculaPeso();
        return valorDosProdutos + dv.definirValor(pesoTotal);
    }

    private double calculaPeso() {
        double pesoTotal = 0;
        for (ItemDePedido itemDePedido : produtos) {
            pesoTotal += itemDePedido.getProduto().getPeso() * itemDePedido.getQuantidade();
        }
        return pesoTotal;
    }

    public String toString() {

        StringBuilder s = new StringBuilder("Informações do pedido");

        s.append("\nNumero do pedido = " + this.numero);
        s.append("\nEndereço = " + this.endereco);
        s.append("\nNome cliente = " + this.nomecliente);
        s.append("\nData do pedido = " + this.data);
        s.append("\n\n\tProdutos :");

        for (ItemDePedido item : produtos) {
            s.append("\n\t\tQuantidade = " + item.getQuantidade());
            s.append("\n\t\tDescrição = " + item.getProduto().getDescricao());
            s.append("\n\t\tPeso = " + item.getProduto().getPeso());
            s.append("\n\t\tValor = " + item.getProduto().getValor());
            s.append("\n\t ------------------------------------------ ");
        }

        try {
            s.append("\n\tValor total = " + this.getValorPedido());
        } catch (Exception e) {
        }

        return s.toString();
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getNumero() {
        return numero;
    }

    public void setNomecliente(String nomecliente) {
        this.nomecliente = nomecliente;
    }

    public String getNomecliente() {
        return nomecliente;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Date getData() {
        return data;
    }

}

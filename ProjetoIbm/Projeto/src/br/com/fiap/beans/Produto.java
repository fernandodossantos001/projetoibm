package br.com.fiap.beans;

public class Produto {

	private int codigo;
	private double valor;
	private String descricao;
	
	
	
	
	public Produto() {
		
	}
	public Produto(int codigo, double valor, String descricao) {
		setCodigo(codigo);
		setValor(valor);
		setDescricao(descricao);
	}
	
	public void setAll(int codigo, double valor, String descricao) {
		setCodigo(codigo);
		setValor(valor);
		setDescricao(descricao);
	}
	
	public String getAll() {
		return "C�digo" + codigo + "/n"+
				"Valor : " + valor +"/n" +
				"Descri��o :" + descricao;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getValor() {
		return valor;
	}
	public void setValor(double valor) {
		this.valor = valor;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	
}

package br.com.fiap.beans;

public class Cliente {

	private String nome;
	private int numero;
	private int qtdeEstrelas;
	private Endereco endereco;
	
	public Cliente(String nome, int numero, int qtdeEstrelas,Endereco endereco) {
		setNome(nome);
		setNumero(numero);
		setQtdeEstrelas(qtdeEstrelas);
	}
	
	public Cliente() {
		
	}
	
	public void setAll(String nome, int numero, int qtdeEstrelas,Endereco endereco) {
		setNome(nome);
		setNumero(numero);
		setQtdeEstrelas(qtdeEstrelas);
	}
	
	
	
	public String getAll() {
		return  "Nome"+ nome + "\n"+
				"Número :" + numero + "\n"+
				"Quantidade de Estrelas :" + qtdeEstrelas ;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public int getQtdeEstrelas() {
		return qtdeEstrelas;
	}
	public void setQtdeEstrelas(int qtdeEstrelas) {
		this.qtdeEstrelas = qtdeEstrelas;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}
	
	
}

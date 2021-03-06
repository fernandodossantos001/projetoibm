package br.com.fiap.beans;

// Comparable<Cargo> compara um cargo com o outro 
public class Cargo implements Comparable<Cargo> {
	private String nome;
	private double salario;
	private String nivel;
	

//	public int compareTo(Cargo outro) {
//		if(this.salario < outro.salario) {
//			return -1;
//		}else if(this.salario >outro.salario) {
//			return 1 ;
//		}else {
//			return 0;
//		}
//	}
	
	//trazendo em ordem normal
//	@Override
//	public int compareTo(Cargo outro) {
//		return this.nome.compareTo(outro.nome);
//	}
//	
	
	//trazendo em ordem decrescente
	@Override
	public int compareTo(Cargo outro) {
		return this.nome.compareTo(outro.nome);
	}
	
	public Cargo(String nome, double salario, String nivel) {
		setNome(nome);
		setSalario(salario);
		setNivel(nivel);
	}
	
	
//	
//	@Override
//	public String toString() {
//		return "Cargo : " + nome + " salario : " + salario + " nivel " + nivel;
//	}



	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public double getSalario() {
		return salario;
	}
	public void setSalario(double salario) {
		this.salario = salario;
	}
	public String getNivel() {
		return nivel;
	}
	public void setNivel(String nivel) {
		this.nivel = nivel;
	}




	
	

}

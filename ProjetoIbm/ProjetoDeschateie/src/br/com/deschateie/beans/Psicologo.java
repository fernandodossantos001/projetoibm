package br.com.deschateie.beans;

public class Psicologo extends Usuario implements Comparable<Usuario> {
	
	private int codPsicologo;
	private int crp;
	private String formacao;
	private String biografia;
	private long telefone;
	private double valorConsulta;
	
	
	
	
	public Psicologo(Psicologo p ) {
		super(p.getCodUsuario(), p.getNomeUsuario(), p.getEmail(), p.getDataNascimento(), p.getLogin(), p.getSenha(), p.getNivelPermissao(), p.getFoto(), p.getGenero());
		setCodPsicologo(p.getCodUsuario());
		setCrp(p.getCrp());
		setFormacao(p.getFormacao());
		setBiografia(p.getBiografia());
		setTelefone(p.getTelefone());
		setValorConsulta(p.getValorConsulta());
	}
	
	public Psicologo(int codUsuario, String nomeUsuario, String email, String dataNascimento, String login,
			String senha, int nivelPermissao, String foto, String genero, int crp, String formacao, String biografia,
			long telefone, double valorConsulta) {
		super(codUsuario, nomeUsuario, email, dataNascimento, login, senha, nivelPermissao, foto, genero);
		setCodPsicologo(codUsuario);
		setCrp(crp);
		setFormacao(formacao);
		setBiografia(biografia);
		setTelefone(telefone);
		setValorConsulta(valorConsulta);
	}
	
	
	public Psicologo() {
		
	}



	
	public void  setALl(int codUsuario, String nomeUsuario, String email, String dataNascimento, String login,
			String senha, int nivelPermissao, String foto, String genero, int crp, String formacao, String biografia,
			long telefone, double valorConsulta) {
		super.setAll(codUsuario, nomeUsuario, email, dataNascimento, login, senha, nivelPermissao, foto, genero);
		setCodPsicologo(codUsuario);
		setCrp(crp);
		setFormacao(formacao);
		setBiografia(biografia);
		setTelefone(telefone);
		setValorConsulta(valorConsulta);
	}




	public String getAll() {
		return super.getAll() + "\n" +
					"Codigo Psicologo : " + codPsicologo + "\n" +
					"Crp : " + crp +"\n" +
					"Forma�o : " + formacao + "\n" +
					"Biografia : "+ biografia + "\n" +
					"Telefone : " + telefone + "\n" +
					"Valor Consulta : " + valorConsulta ;
	}


	public int getCodPsicologo() {
		return codPsicologo;
	}






	public void setCodPsicologo(int codPsicologo) {
		this.codPsicologo = codPsicologo;
	}






	public int getCrp() {
		return crp;
	}






	public void setCrp(int crp) {
		this.crp = crp;
	}






	public String getFormacao() {
		return formacao;
	}






	public void setFormacao(String formacao) {
		this.formacao = formacao;
	}






	public String getBiografia() {
		return biografia;
	}






	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}






	public long getTelefone() {
		return telefone;
	}






	public void setTelefone(long telefone) {
		this.telefone = telefone;
	}






	public double getValorConsulta() {
		return valorConsulta;
	}






	public void setValorConsulta(double valorConsulta) {
		this.valorConsulta = valorConsulta;
	}






	@Override
	public int compareTo(Usuario outro) {
		return this.getNomeUsuario().compareTo(outro.getNomeUsuario());
	}
	
}
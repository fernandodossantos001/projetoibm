package br.com.deschateie.beans;
/**
 * Essa classe representa um endereco
 * possui os metodos metodos para recuperar cada atributo
 *  e todos os metodo para inserir dados nos atributos
 * @author Fernando Ribeiro
 * @version 1.0
 * @since 1.0
 */


public class Endereco implements Comparable <Endereco> {
	
	private int cdEndereco;
	private int cdAtendente;
	private String tipo;
	private String logradouro;
	private String numero;
	private String complemento;
	private int cep;
	private String bairro;
	private String cidade;
	private String uf;
	private String pais;
	
	
	public Endereco() {
		
	}
	
	
	/**
	 * Nesse m�todo construtor estamos inserindo os dados nos atributos da classe 
	 * @param recebe os parametros para que possa ser criado um endereco
	 * @author Fernando Ribeiro
	 * @return n�o h� retorno
	 *
	 */
	public Endereco(int cdEndereco, int cdAtendente ,String tipo, String logradouro, String numero,
			String complemento, int cep, String bairro, String cidade, String uf, String pais) {
		setCdEndereco(cdEndereco);
		setCdAtendente(cdAtendente);
		setTipo(tipo);
		setLogradouro(logradouro);
		setNumero(numero);
		setComplemento(complemento);
		setCep(cep);
		setBairro(bairro);
		setCidade(cidade);
		setUf(uf);
		setPais(pais);
	}
	
	
	/**
	 * Nesse m�todo estamos inserindo os dados nos atributos da classe 
	 * @param recebe os parametros para que possa ser criado um endereco
	 * @author Fernando Ribeiro
	 * @return n�o h� retorno
	 *
	 */
	public void setAll(int cdEndereco, int cdAtendente,String tipo, String logradouro, String numero,
			String complemento, int cep, String bairro, String cidade, String uf, String pais) {
		
		setCdEndereco(cdEndereco);
		setCdAtendente(cdAtendente);
		setTipo(tipo);
		setLogradouro(logradouro);
		setNumero(numero);
		setComplemento(complemento);
		setCep(cep);
		setBairro(bairro);
		setCidade(cidade);
		setUf(uf);
		setPais(pais);
	}

	/**
	 * esse m�todo compara dois atirbutos 
	 * @param um Endereco como parametro para que possa ser feita a comparacao
	 * @return retorna -1,0 e 1
	 */
	@Override
	public int compareTo(Endereco outro) {
		// TODO Auto-generated method stub
		return this.logradouro.compareTo(outro.logradouro);
	}
	
	
	
		
	
	public String getAll() {
		return  "C�digo Endereco : " + cdEndereco + "\n"+ 
				"C�gido Atendente : " + cdAtendente+ "\n"+ 
				"Tipo : " + tipo + "\n" +
				"Logradouro : " + logradouro + "\n"+ 
				"N�mero : " + numero + "\n" +
				"Complemento : " + complemento + "\n" +
				"Cep : " + cep + "\n" +
				"Bairro : " + bairro + "\n" +
				"Cidade : " + cidade + "\n" +
				"Uf :" + uf + "\n" +
				"Pais : " + pais ;
	}

	

	


	public int getCdAtendente() {
		return cdAtendente;
	}


	public void setCdAtendente(int cdAtendente) {
		this.cdAtendente = cdAtendente;
	}

	public int getCdEndereco() {
		return cdEndereco;
	}
	public void setCdEndereco(int cdEndereco) {
		this.cdEndereco = cdEndereco;
	}
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getLogradouro() {
		return logradouro;
	}
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
	}
	public String getComplemento() {
		return complemento;
	}
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
	public int getCep() {
		return cep;
	}
	public void setCep(int cep) {
		this.cep = cep;
	}
	public String getBairro() {
		return bairro;
	}
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}
	public String getCidade() {
		return cidade;
	}
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}
	public String getUf() {
		return uf;
	}
	public void setUf(String uf) {
		this.uf = uf;
	}
	public String getPais() {
		return pais;
	}
	public void setPais(String pais) {
		this.pais = pais;
	}


	


	

	
}

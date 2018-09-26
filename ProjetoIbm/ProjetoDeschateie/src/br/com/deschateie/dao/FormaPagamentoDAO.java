package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.FormaPagamento;
import br.com.deschateie.conexao.Conexao;

/**
 * Classe respons�vel por manipular os dados da tabela T_SCP_FORMA_PAGAMENTO
 * possui m�todos para criar,alterar,consultar e excluir dados da tabela T_SCP_FORMA_PAGAMENTO
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see FormaPagamento
 */
public class FormaPagamentoDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 * M�todo respons�vel por estabelecer conex�o com o banco de dados
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public FormaPagamentoDAO()throws Exception {
		this.con = new  Conexao().conectar();
	}
	
	
	/**
	 * M�todo respons�vel por consultar uma linha da tabela T_SCP_FORMA_PAGAMENTO
	 * @param codFormaPagamento Recebe um n�mero inteiro do codigo da FormaPagamento
	 * @return Retorna um Objeto do tipo FormaPagamento
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public FormaPagamento consultarFormaPagamento(int codFormaPagamento)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_FORMA_PAGAMENTO WHERE CD_FORMA_PGTO = ?");
		stmt.setInt(1, codFormaPagamento);
		rs = stmt.executeQuery();
		if (rs.next()) {
			return new FormaPagamento(
					rs.getInt("CD_FORMA_PGTO"),
					rs.getString("DS_FORMA_PGTO"));
		}
		
		return new FormaPagamento();
	}

	
	
	/**
	 * M�todo respons�vel por inserir uma linha na tabela T_SCP_FORMA_PAGAMENTO
	 * @param fp Recebe um Objeto do tipo  FormaPagamento
	 * @return Retorna uma String com uma mensagem de sucesso
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public String gravarFormaPagamento(FormaPagamento fp)throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_FORMA_PAGAMENTO(CD_FORMA_PGTO,DS_FORMA_PGTO) VALUES(?,?)");
		stmt.setInt(1, fp.getCodFormaPagamento());
		stmt.setString(2, fp.getFormaPagamento());
		stmt.executeUpdate();
		return "Forma de pagamento cadastrada com suceso";
		
	}

	/**
	 * M�todo respons�vel por alterar os dados de uma linha da tabela T_SCP_FORMA_PAGAMENTO
	 * @param fp Recebe um Objeto do tipo FormaPagamento
	 * @return	Retorna uma String informando que foi alterado
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public String alterarDadosFormaPagamento(FormaPagamento fp)throws Exception {
		stmt = con.prepareStatement("UPDATE T_SCP_FORMA_PAGAMENTO SET DS_FORMA_PGTO = ? WHERE CD_FORMA_PGTO = ?");
		stmt.setString(1, fp.getFormaPagamento());
		stmt.setInt(2, fp.getCodFormaPagamento());
		return stmt.executeUpdate()+"linha alterada";
	}
	
	
	/**
	 * M�todo respons�vel por excluir uma linha da tabela T_SCP_FORMA_PAGAMENTO
	 * @param codFormaPagamento Recebe um n�mero interio do c�digo da FormaPagamento
	 * @return Retorna uma String  informando que a linha foi excluida
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public String exluirFormaPagamento(int codFormaPagamento)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_FORMA_PAGAMENTO WHERE CD_FORMA_PGTO = ?");
		stmt.setInt(1,codFormaPagamento);
		return stmt.executeUpdate() + "linha excluida";
	}
	
	/**
	 * M�todo respons�vel por finalizar a conex�o com o banco de dados
	 * @author Deschateie
	 * @throws Exception Chamada da exce��o checked SQLException
	 */
	public void fechar()throws Exception{
		con.close();
	}
}

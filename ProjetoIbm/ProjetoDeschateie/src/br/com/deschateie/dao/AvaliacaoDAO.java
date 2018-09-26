package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Avaliacao;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.conexao.Conexao;


/**
 * Est� classe manipula a tabela T_SCP_AVALIACAO
* possui m�todo para : Cadastram consultar, alterar e excluir
 * @author Deschateie
 * @version 1.0
 * @since 1.0
 * @see Avaliacao
 *
 */

public class AvaliacaoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	
	/**
	 * Neste m�todo construtor estabelece a comunica��o com o banco
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public AvaliacaoDAO()throws Exception {
		con = new Conexao().conectar();
	}
	
	/**
	 * M�todo respons�vel por consultar a tabela T_SCP_AVALIACAO
	 * @param codAvaliacao recebe o c�digo da avalia��o 
	 * @return Retorna uma Avaliacao
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public Avaliacao consultarAvaliacao(int codAvaliacao)throws Exception{
		
		
		
		stmt = con.prepareStatement("SELECT * FROM T_SCP_AVALIACAO INNER JOIN t_scp_usuario ON(T_SCP_AVALIACAO.CD_USUARIO = T_SCP_USUARIO.CD_USUARIO)" + 
				"				INNER JOIN T_SCP_PSICOLOGO ON(T_SCP_AVALIACAO.CD_PSICOLOGO = T_SCP_PSICOLOGO.CD_PSICOLOGO )" + 
				"				WHERE T_SCP_AVALIACAO.CD_AVALIACAO = ? ");
		
		stmt.setInt(1, codAvaliacao);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
				return new Avaliacao
					(rs.getInt("CD_AVALIACAO"),
							new Psicologo(
									rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO"),
									rs.getInt("NR_CRP"),
									rs.getString("DS_FORMACAO"),
									rs.getString("DS_BIOGRAFIA"),
									rs.getLong("DS_TELEFONE"),
									rs.getDouble("VL_CONSULTA")),
							new Usuario(rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO")),
									rs.getString("DT_AVALIACAO"),
									rs.getString("DS_RESULTADO"));
		}
			return new Avaliacao();
		
	}
	
	
	
	
	
	/**
	 * M�todo respons�vel por consultar a tabela T_SCP_AVALIACAO
	 * @param codUsuario recebe o c�digo de usuario
	 * @return Retorna uma Avaliacao
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public Avaliacao consultarAvaliacaoCodUsuario(int codUsuairo)throws Exception{
		
		
		
		stmt = con.prepareStatement("SELECT * FROM T_SCP_AVALIACAO INNER JOIN t_scp_usuario ON(T_SCP_AVALIACAO.CD_USUARIO = T_SCP_USUARIO.CD_USUARIO)" + 
				"				INNER JOIN T_SCP_PSICOLOGO ON(T_SCP_AVALIACAO.CD_PSICOLOGO = T_SCP_PSICOLOGO.CD_PSICOLOGO )" + 
				"				WHERE T_SCP_AVALIACAO.CD_USUARIO = ? ");
		
		stmt.setInt(1, codUsuairo);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
				return new Avaliacao
					(rs.getInt("CD_AVALIACAO"),
							new Psicologo(
									rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO"),
									rs.getInt("NR_CRP"),
									rs.getString("DS_FORMACAO"),
									rs.getString("DS_BIOGRAFIA"),
									rs.getLong("DS_TELEFONE"),
									rs.getDouble("VL_CONSULTA")),
							new Usuario(rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO")),
									rs.getString("DT_AVALIACAO"),
									rs.getString("DS_RESULTADO"));
		}
			return new Avaliacao();
		
	}

	

	/**
	 * M�todo respons�vel por alterar o resultado(coluna) de uma linha da tabela T_SCP_AVALIACAO
	 *  de  um psicologo ou voluntario, de acordo com o crit�rio de avalia��o do
	 *  psic�logo respons�vel
	 * @param av Recebe um Objeto do tipo Avaliacao
	 * @return Retorna uma String informando que a altera��o foi feita
	 * @throws Exception chamada de exce��o checked SQLException
	 */
	public String avaliar(Avaliacao av)throws Exception{
		stmt = con.prepareStatement("UPDATE T_SCP_AVALIACAO SET DS_RESULTADO = ? WHERE CD_AVALIACAO = ?");
		stmt.setString(1, av.getResultado());
		stmt.setInt(2, av.getCodAvaliacao());
		stmt.executeUpdate();
		return "Avaliado com sucesso";
	}
	 
	/**
	 * M�todo respons�vel por consultar a tabela T_SCP_AVALIACAO usando 
	 * o c�digo do usuario como refer�ncia
	 * @param  codUsuario Recebe o c�digo do usuario para fazer a pesquisa
	 * @return Retornar uma Avaliacao
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public Avaliacao consultarAvaliacaoUsuario(int codUsuario)throws Exception{
		
		
		
		stmt = con.prepareStatement("SELECT * FROM T_SCP_AVALIACAO INNER JOIN t_scp_usuario ON(T_SCP_AVALIACAO.CD_USUARIO = T_SCP_USUARIO.CD_USUARIO)" + 
				"				INNER JOIN T_SCP_PSICOLOGO ON(T_SCP_AVALIACAO.CD_PSICOLOGO = T_SCP_PSICOLOGO.CD_PSICOLOGO )" + 
				"				WHERE T_SCP_AVALIACAO.CD_USUARIO = ? ");
		stmt.setInt(1, codUsuario);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
				return new Avaliacao
					(rs.getInt("CD_AVALIACAO"),
							new Psicologo(
									rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO"),
									rs.getInt("NR_CRP"),
									rs.getString("DS_FORMACAO"),
									rs.getString("DS_BIOGRAFIA"),
									rs.getLong("DS_TELEFONE"),
									rs.getDouble("VL_CONSULTA")),
							new Usuario(rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO")),
									rs.getString("DT_AVALIACAO"),
									rs.getString("DS_RESULTADO"));
		}
			return new Avaliacao();
		
	}


	/**
	 * M�todo respons�vel por criar uma linha na tabela T_SCP_AVALIACAO
	 * @param av Recebe como parametro um Objeto do tipo Avaliacao
	 * @return Retorna uma Avaliacao
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public String gravarDadosAvaliacao(Avaliacao av)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_SCP_AVALIACAO"
								+ "(CD_AVALIACAO,CD_PSICOLOGO,CD_USUARIO,DT_AVALIACAO,DS_RESULTADO)"
								+ "VALUES(?,?,?,TO_DATE(?,'dd/mm/yyyy'),?)");
		stmt.setInt(1, av.getCodAvaliacao());
		stmt.setInt(2, av.getPsicologo().getCodPsicologo());
		stmt.setInt(3, av.getUsuario().getCodUsuario());
		stmt.setString(4, av.getDataAvaliacao());
		stmt.setString(5, av.getResultado());
		stmt.executeUpdate();
		return "Avaliacao cadastrada com sucesso";
	}
	
	/**
	 * M�todo respons�vel por excluir uma linha da tabela T_SCP_AVALIACAO 
	 * @param codAvaliacao Recebe o c�digo da avaliacao para excluir a linha
	 * @return Retorna uma String  informando que a exclus�o foi efetuada
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public String excluirAvaliacao(int codAvaliacao)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_AVALIACAO WHERE CD_AVALIACAO = ?");
		stmt.setInt(1, codAvaliacao);
		return stmt.executeUpdate() + "linha excluida";
	}
	
	/**
	 * M�todo respons�vel por alterar os dados da tabela T_SCP_AVALIACAO
	 * @param av Recebe um Objeto do tipo Avaliacao
	 * @return Retorna uma String informando que a altera��o foi feita com sucesso
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public String alterarDadosAvaliacao(Avaliacao av)throws Exception{
		stmt = con.prepareStatement("UPDATE  T_SCP_AVALIACAO SET DT_AVALIACAO = ?, DS_RESULTADO = ? WHERE CD_AVALIACAO = ?");
		stmt.setString(1, av.getDataAvaliacao());
		stmt.setString(2, av.getResultado());
		stmt.setInt(3, av.getCodAvaliacao());
		stmt.executeUpdate();
		return "Alterado com sucesso";
		
	}
	
	
	/**
	 * M�todo respons�vel por fechar a conex�o com o banco
	 * @author Deschateie
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public void fechar()throws Exception{
		con.close();
	}
}

package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.AvaliacaoVoluntario;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Usuario;
import br.com.deschateie.conexao.Conexao;

public class AvaliacaoVoluntarioDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public AvaliacaoVoluntarioDAO()throws Exception {
		con = new Conexao().conectar();
	}
	public AvaliacaoVoluntario consultarAvaliacao(int codAvaliacao)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_AVALIACAO INNER JOIN t_scp_usuario " + 
				"ON(T_SCP_AVALIACAO.CD_USUARIO = T_SCP_USUARIO.CD_USUARIO)" + 
				"INNER JOIN T_SCP_PSICOLOGO ON(T_SCP_AVALIACAO.CD_PSICOLOGO = T_SCP_PSICOLOGO.CD_PSICOLOGO )\r\n" + 
				"WHERE T_SCP_AVALIACAO.CD_AVALIACAO = ?");
		stmt.setInt(1, codAvaliacao);
		rs = stmt.executeQuery();
		
		if (rs.next()) {
			return new AvaliacaoVoluntario
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
		}else {
			return new AvaliacaoVoluntario();
		}
	}

	public String gravarDadosAvaliacao(int codAvaliacao, int codPsicologo, int codUsuario, String dataAvaliacao,String resultado)throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_SCP_AVALIACAO"
								+ "(CD_AVALIACAO,CD_PSICOLOGO,CD_USUARIO,DT_AVALIACAO,DS_RESULTADO)"
								+ "VALUES(?,?,?,TO_DATE(?,'dd/mm/yyyy'),?)");
		stmt.setInt(1, codAvaliacao);
		stmt.setInt(2, codPsicologo);
		stmt.setInt(3, codUsuario);
		stmt.setString(4, dataAvaliacao);
		stmt.setString(5, resultado);
		stmt.executeUpdate();
		return "Avaliacao cadastrada com sucesso";
	}

	public String excluirAvaliacao(int codAvaliacao)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_AVALIACAO WHERE CD_AVALIACAO = ?");
		stmt.setInt(1, codAvaliacao);
		return stmt.executeUpdate() + "linha excluida";
	}
	
	public void fechar()throws Exception{
		con.close();
	}
}

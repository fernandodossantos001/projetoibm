package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Usuario;
import br.com.deschateie.conexao.Conexao;

/** 
 * Est� classe manipula a tabela T_SCP_USUARIO
 * possui m�todo para : Cadastrar, consultar, alterar ,excluir, fechar a conex�o e autenticar usuario
 * @author Fernando Santos Ribeiro
 * @version 1.0
 * @since 1.0
 * @see Usuario
 *
 */


public class UsuarioDAO {
	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	/**
	 *
	 * Neste m�todo construtor estabelecemos a comunica��o com o banco de dados
	 * @author Fernando Santos Ribeiro
	 * @param n�o possui par�metros
	 * @return n�o h� retorno
	 * @throws Exception chamada da exce��o checked SQLException
	 */
	public UsuarioDAO()throws Exception{
		con = new  Conexao().conectar();
	}
	
	

	/**
	 * M�todo respons�vel por consultar uma linha na tabela T_SCP_USUARIO
	 * @param recebe o nome de login do tipo String como par�metro
	 * @return retorna um objeto do tipo Usuairo
	 * @throws Exception - chamada da  Exce��o checked SQLException
	 */
	public Usuario consultarLoginUsuario(String login)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO WHERE DS_LOGIN = ?");
		stmt.setString(1, login);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Usuario(	
								rs.getInt("CD_USUARIO"),
								rs.getString("NM_USUARIO"),
								rs.getString("DS_EMAIL"),
								rs.getString("DT_NASCIMENTO"),
								rs.getString("DS_LOGIN"),
								rs.getString("DS_SENHA"),
								rs.getInt("NR_NIVEL_PERMISSAO"),
								rs.getString("DS_FOTO"),
								rs.getString("DS_GENERO"));
		}
		
		return new Usuario();
		
	}
	

	public Usuario consultarCodUsuario(int codUsuario)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO WHERE CD_USUARIO= ?");
		stmt.setInt(1, codUsuario);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Usuario(	
								rs.getInt("CD_USUARIO"),
								rs.getString("NM_USUARIO"),
								rs.getString("DS_EMAIL"),
								rs.getString("DT_NASCIMENTO"),
								rs.getString("DS_LOGIN"),
								rs.getString("DS_SENHA"),
								rs.getInt("NR_NIVEL_PERMISSAO"),
								rs.getString("DS_FOTO"),
								rs.getString("DS_GENERO"));
		}
		
		return new Usuario();
		
	}
	
	public Usuario consultarEmailUsuario(String email)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO WHERE DS_EMAIL = ?");
		stmt.setString(1, email);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Usuario(	
								rs.getInt("CD_USUARIO"),
								rs.getString("NM_USUARIO"),
								rs.getString("DS_EMAIL"),
								rs.getString("DT_NASCIMENTO"),
								rs.getString("DS_LOGIN"),
								rs.getString("DS_SENHA"),
								rs.getInt("NR_NIVEL_PERMISSAO"),
								rs.getString("DS_FOTO"),
								rs.getString("DS_GENERO"));
		}
		
		return new Usuario();
		
	}

	public List<Usuario> consultarNomesUsuario(String nome)throws Exception{
		List<Usuario> listaUsuario = new ArrayList<Usuario>();
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO WHERE NM_USUARIO LIKE ? ");
		
		stmt.setString(1, nome + "%");
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			listaUsuario.add(new Usuario(
					rs.getInt("CD_USUARIO"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					rs.getString("DS_LOGIN"),
					rs.getString("DS_SENHA"),
					rs.getInt("NR_NIVEL_PERMISSAO"),
					rs.getString("DS_FOTO"),
					rs.getString("DS_GENERO")
					));
		}
		return listaUsuario;
	}
	
	public String gravaUsuario(Usuario u)throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_USUARIO "
				+ "(CD_USUARIO,NM_USUARIO,DS_EMAIL,DT_NASCIMENTO,DS_LOGIN,DS_SENHA,NR_NIVEL_PERMISSAO,DS_GENERO,DS_FOTO)"
				+ "VALUES(?,?,?,TO_DATE(?,'dd/mm/yyyy'),?,?,?,?,?)");
		stmt.setInt(1, u.getCodUsuario());
		stmt.setString(2, u.getNomeUsuario());
		stmt.setString(3, u.getEmail());
		stmt.setString(4, u.getDataNascimento());
		stmt.setString(5, u.getLogin());
		stmt.setString(6, u.getSenha());
		stmt.setInt(7, u.getNivelPermissao());
		stmt.setString(8, u.getGenero());
		stmt.setString(9, u.getFoto());
		
		stmt.executeUpdate();
		
		return "Usu�rio cadastrado com sucesso" ; 
		
	}


	public String excluirUsuario(int codUsuario)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_USUARIO WHERE CD_USUARIO =  ?");
		stmt.setInt(1, codUsuario);
		return "Foram excluidas " + stmt.executeUpdate() + " linhas";
	}

	public String alterarDadosUsuario(Usuario u)throws Exception{
		stmt = con.prepareStatement("UPDATE T_SCP_USUARIO"
									+ "	SET NM_USUARIO = ?,"
									+ " DS_EMAIL = ?,"
									+ " DT_NASCIMENTO = TO_DATE(?,'DD/MM/YYYY'),"
									+ " DS_LOGIN = ?,"
									+ " DS_SENHA = ?,"
									+ " DS_FOTO = ?,"
									+ " DS_GENERO = ?"
									+ "WHERE CD_USUARIO = ?");
		stmt.setString(1, u.getNomeUsuario());
		stmt.setString(2, u.getEmail());
		stmt.setString(3, u.getDataNascimento());
		stmt.setString(4, u.getLogin());
		stmt.setString(5, u.getSenha());
		stmt.setString(6, u.getFoto());
		stmt.setString(7, u.getGenero());
		stmt.setInt(8, u.getCodUsuario());
		return "Foi atualizada " + stmt.executeUpdate() + "linha";
		
	}

	public Usuario autenticarUsuario(String login, String senha)throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO WHERE DS_LOGIN = ? AND DS_SENHA = ?");
		stmt.setString(1, login);
		stmt.setString(2, senha);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			 return new Usuario(	
					rs.getInt("CD_USUARIO"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					rs.getString("DS_LOGIN"),
					rs.getString("DS_SENHA"),
					rs.getInt("NR_NIVEL_PERMISSAO"),
					rs.getString("DS_FOTO"),
					rs.getString("DS_GENERO"));
		}
		
		return new Usuario();
	}

	public String alterarNivelAcesso(Usuario u)throws Exception{
		stmt = con.prepareStatement("UPDATE T_SCP_USUARIO SET NR_NIVEL_PERMISSAO = ? WHERE CD_USUARIO = ?");
		stmt.setInt(1, u.getNivelPermissao());
		stmt.setInt(2, u.getCodUsuario());
		return stmt.executeUpdate() + "linha atualizada";
	}
	public void fechar()throws Exception {
		con.close();
	}
}


package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.conexao.Conexao;

public class VoluntarioDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public VoluntarioDAO()throws Exception {
		con = new Conexao().conectar();
	}
	
	public Voluntario consultarVoluntario(int codVoluntario)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM  T_SCP_USUARIO INNER JOIN T_SCP_VOLUNTARIO "
				+ "ON (T_SCP_USUARIO.CD_USUARIO = T_SCP_VOLUNTARIO.CD_VOLUNTARIO) WHERE CD_USUARIO = ? ");
		stmt.setInt(1, codVoluntario);
		rs = stmt.executeQuery();
		
		if(rs.next()) { 
			return new  Voluntario(
									rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO"),
									rs.getString("NR_RG"),
									rs.getLong("NR_CPF"),
									rs.getString("DS_FORMACAO"),
									rs.getString("DS_PERIODO"),
									rs.getString("DS_COMENTARIO"),
									rs.getLong("DS_TELEFONE"));
		}else {
			return new Voluntario();
		}
	}

	public List<Voluntario> consultarVoluntario()throws Exception{
		List<Voluntario> voluntario = new ArrayList<Voluntario>();
		stmt = con.prepareStatement("SELECT * FROM T_SCP_USUARIO INNER JOIN " + 
				"T_SCP_VOLUNTARIO ON(T_SCP_USUARIO.CD_USUARIO = T_SCP_VOLUNTARIO.CD_VOLUNTARIO) " + 
				"WHERE T_SCP_USUARIO.NR_NIVEL_PERMISSAO = 7");
		rs = stmt.executeQuery();
		while(rs.next()) { 
			voluntario.add(
					new  Voluntario(
					rs.getInt("CD_USUARIO"),
					rs.getString("NM_USUARIO"),
					rs.getString("DS_EMAIL"),
					rs.getString("DT_NASCIMENTO"),
					rs.getString("DS_LOGIN"),
					rs.getString("DS_SENHA"),
					rs.getInt("NR_NIVEL_PERMISSAO"),
					rs.getString("DS_FOTO"),
					rs.getString("DS_GENERO"),
					rs.getString("NR_RG"),
					rs.getLong("NR_CPF"),
					rs.getString("DS_FORMACAO"),
					rs.getString("DS_PERIODO"),
					rs.getString("DS_COMENTARIO"),
					rs.getLong("DS_TELEFONE")));	
		}
			return voluntario;
		
	}

	public Voluntario consultarVoluntarioCpf(long cpf)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM  T_SCP_USUARIO ,T_SCP_VOLUNTARIO WHERE CR_CPF = ? ");
		stmt.setLong(1, cpf);
		rs = stmt.executeQuery();
		
		if(rs.next()) { 
			return new  Voluntario(
									rs.getInt("CD_USUARIO"),
									rs.getString("NM_USUARIO"),
									rs.getString("DS_EMAIL"),
									rs.getString("DT_NASCIMENTO"),
									rs.getString("DS_LOGIN"),
									rs.getString("DS_SENHA"),
									rs.getInt("NR_NIVEL_PERMISSAO"),
									rs.getString("DS_FOTO"),
									rs.getString("DS_GENERO"),
									rs.getString("NR_RG"),
									rs.getLong("NR_CPF"),
									rs.getString("DS_FORMACAO"),
									rs.getString("DS_PERIODO"),
									rs.getString("DS_COMENTARIO"),
									rs.getLong("DS_TELEFONE"));
		}else {
			return new Voluntario();
		}
	}
	
	public String gravarVoluntario(Voluntario v)throws Exception{
//		new UsuarioDAO().gravaUsuario(v);
		stmt = con.prepareStatement("INSERT INTO T_SCP_VOLUNTARIO"
									+ "	(CD_VOLUNTARIO,"
									+ " NR_RG,"
									+ " NR_CPF,"
									+ " DS_FORMACAO,"
									+ " DS_PERIODO,"
									+ " DS_COMENTARIO,"
									+ " DS_TELEFONE)"
									+ " VALUES (?,?,?,?,?,?,?)");
		stmt.setInt(1, v.getCodVoluntario());
		stmt.setString(2, v.getRg());
		stmt.setLong(3, v.getCpf());
		stmt.setString(4, v.getFormacao());
		stmt.setString(5, v.getPeriodo());
		stmt.setString(6, v.getComentario());
		stmt.setLong(7, v.getTelefone());
		stmt.executeUpdate();
		return "Cadastrado com sucesso";
	}


	public String excluirVoluntario(int codVoluntario)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_VOLUNTARIO WHERE CD_VOLUNTARIO  = ?");
		stmt.setInt(1, codVoluntario );
		return stmt.executeUpdate() + "linha exlcuida"; 
	}
	
	public void fechar()throws Exception {
		con.close();
		
	}

	public String alterarDadosVoluntario(Voluntario v)throws Exception {

		stmt= con.prepareStatement("UPDATE T_SCP_VOLUNTARIO"
									+ " SET NR_RG = ?,"
									+ "NR_CPF = ?,"
									+ "DS_FORMACAO =?,"
									+ "DS_PERIODO = ?,"
									+ "DS_COMENTARIO = ?,"
									+ "DS_TELEFONE = ?"
									+ "WHERE CD_VOLUNTARIO = ?");
		
		stmt.setString(1, v.getRg());
		stmt.setLong(2, v.getCpf());
		stmt.setString(3, v.getFormacao());
		stmt.setString(4, v.getPeriodo());
		stmt.setString(5, v.getComentario());
		stmt.setLong(6, v.getTelefone());
		stmt.setInt(7, v.getCodVoluntario());
		return stmt.executeUpdate() + "linha atualizada";
	}

	
}

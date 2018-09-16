package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Paciente;

import br.com.deschateie.conexao.Conexao;

public class PacienteDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public PacienteDAO()throws Exception {
		con= new Conexao().conectar();
	}
	
	
	public String gravarPaciente(Paciente p)throws Exception{
//		new  UsuarioDAO().gravaUsuario(p);		 
		stmt = con.prepareStatement("INSERT INTO T_SCP_PACIENTE"
									+ "(CD_PACIENTE, DS_CEP,NR_CPF,"
									+ "DS_HISTORICO,NR_CONSULTAS_REALIZADAS)"
									+ "VALUES(?,?,?,?,?) ");
		
		stmt.setInt(1, p.getCodUsuario());
		stmt.setInt(2, p.getCep());
		stmt.setLong(3, p.getCpf());
		stmt.setString(4, p.getHistorico());
		stmt.setInt(5, p.getConsultasReazlizadas());
		stmt.executeUpdate();
		return "cadastrado com sucesso";
	}
	
	
	public String AlteraDadosPaciente(Paciente p)throws Exception{
//		new UsuarioDAO().alterarDadosUsuario(p);
		
		stmt = con.prepareStatement(
									"UPDATE T_SCP_PACIENTE SET DS_CEP = ?, NR_CPF = ?, DS_HISTORICO = ? WHERE CD_PACIENTE = ?"
									);
		
		stmt.setInt(1, p.getCep());
		stmt.setLong(2, p.getCpf());
		stmt.setString(3, p.getHistorico());
		stmt.setInt(4,p.getCodPaciente());
		
		
		return "atualizado com sucesso " + stmt.executeUpdate();
	}


	public String excluirPaciente(int codPaciente) throws Exception{
		
		stmt = con.prepareStatement("DELETE FROM T_SCP_PACIENTE WHERE CD_PACIENTE=  ?");
		stmt.setInt(1, codPaciente);
		return "foi excluida " + stmt.executeUpdate() + " linha";
	}
	
	public Paciente consultarPaciente(int codPaciente)throws Exception{
		stmt= con.prepareStatement("SELECT * FROM  T_SCP_USUARIO NATURAL JOIN T_SCP_PACIENTE WHERE CD_USUARIO = ? AND CD_PACIENTE = ? ");
		stmt.setInt(1, codPaciente);
		stmt.setInt(2, codPaciente);
		
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Paciente(
								rs.getInt("CD_USUARIO"),
								rs.getString("NM_USUARIO"),
								rs.getString("DS_EMAIL"),
								rs.getString("DT_NASCIMENTO"),
								rs.getString("DS_LOGIN"),
								rs.getString("DS_SENHA"),
								rs.getInt("NR_NIVEL_PERMISSAO"),
								rs.getString("DS_FOTO"),
								rs.getString("DS_GENERO"),
								rs.getInt("DS_CEP"),
								rs.getLong("NR_CPF"),
								rs.getString("DS_HISTORICO"),
								rs.getInt("NR_CONSULTAS_REALIZADAS"));
			}else {
				return new Paciente();
			}
	}
	

	public void fechar()throws Exception{
		con.close();
	}


	public Paciente consultarPacienteCpf(long cpf)throws Exception {
		stmt= con.prepareStatement("SELECT * FROM  T_SCP_USUARIO NATURAL JOIN T_SCP_PACIENTE WHERE NR_CPF = ? ");
		stmt.setLong(1, cpf);
		
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Paciente(
								rs.getInt("CD_USUARIO"),
								rs.getString("NM_USUARIO"),
								rs.getString("DS_EMAIL"),
								rs.getString("DT_NASCIMENTO"),
								rs.getString("DS_LOGIN"),
								rs.getString("DS_SENHA"),
								rs.getInt("NR_NIVEL_PERMISSAO"),
								rs.getString("DS_FOTO"),
								rs.getString("DS_GENERO"),
								rs.getInt("DS_CEP"),
								rs.getLong("NR_CPF"),
								rs.getString("DS_HISTORICO"),
								rs.getInt("NR_CONSULTAS_REALIZADAS"));
			}else {
				return new Paciente();
			}
	}
}


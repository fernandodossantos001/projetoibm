package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.beans.Conversa;
import br.com.deschateie.beans.Paciente;
import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.beans.Psicologo;
import br.com.deschateie.beans.Voluntario;
import br.com.deschateie.conexao.Conexao;

public class ConsultaDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs ;
	
	public ConsultaDAO()throws Exception{
		con = new Conexao().conectar();
	}
	
	public Consulta pesquisarConsulta(int c)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_CONSULTA "+ 
				"INNER JOIN T_SCP_PSI_ONLINE ON(T_SCP_CONSULTA.CD_PSICOLOGO_ON = T_SCP_PSI_ONLINE.CD_PSICOLOGO_ON)" + 
				"INNER JOIN T_SCP_PSICOLOGO ON (T_SCP_PSICOLOGO.CD_PSICOLOGO = T_SCP_PSI_ONLINE.CD_PSICOLOGO_ON)" + 
				"INNER JOIN T_SCP_USUARIO ON (T_SCP_USUARIO.CD_USUARIO = T_SCP_PSI_ONLINE.CD_PSICOLOGO_ON)" + 
				"INNER JOIN T_SCP_PACIENTE ON(T_SCP_CONSULTA.CD_PACIENTE = T_SCP_PACIENTE.CD_PACIENTE)" + 
				"INNER JOIN T_SCP_USUARIO ON (T_SCP_USUARIO.CD_USUARIO = T_SCP_PACIENTE.CD_PACIENTE) WHERE CD_CONSULTA= ?");
		stmt.setInt(1, c);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Consulta(
					rs.getInt("CD_CONSULTA"),
					new PsiOnline(
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
							rs.getDouble("VL_CONSULTA"),
							rs.getString("DS_PERIODO"),
							rs.getString("DS_FORMA_ATENDIMENTO"),
							rs.getInt("VL_NOTA_ATENDIMENTO"),
							rs.getInt("QT_ATENDIMENTO")),
				new Paciente(
							rs.getInt("CD_USUARIO"),
							rs.getString("NM_USUARIO"),
							rs.getString("DS_EMAIL"),
							rs.getString("DT_NASCIMENTO"),
							rs.getString("DS_LOGIN"),
							rs.getString("DS_SENHA"),
							rs.getInt("NR_NIVEL_PERMISSAO"),
							rs.getString("DS_FOTO"),
							rs.getString("DS_GENERO"),
							rs.getString("DS_CEP"),
							rs.getLong("NR_CPF"),
							rs.getString("DS_HISTORICO"),
							rs.getInt("NR_CONSULTAS_REALIZADAS")),
				 			rs.getInt("CD_CONVERSA"),
					rs.getString("DT_CONSULTA"),
					rs.getInt("VL_NOTA_ATENDIMENTO"), 
					rs.getString("DS_COMENTARIO"));
		}
		
		return new Consulta();
	}

	
	public Consulta pesquisarConsultaCodPaciente(int c)throws Exception{
		stmt = con.prepareStatement("SELECT * FROM T_SCP_CONSULTA "+ 
				"INNER JOIN T_SCP_PSI_ONLINE ON(T_SCP_CONSULTA.CD_PSICOLOGO_ON = T_SCP_PSI_ONLINE.CD_PSICOLOGO_ON)" + 
				"INNER JOIN T_SCP_PSICOLOGO ON (T_SCP_PSICOLOGO.CD_PSICOLOGO = T_SCP_PSI_ONLINE.CD_PSICOLOGO_ON)" + 
				"INNER JOIN T_SCP_PACIENTE ON(T_SCP_CONSULTA.CD_PACIENTE = T_SCP_PACIENTE.CD_PACIENTE)" + 
				"INNER JOIN T_SCP_USUARIO ON (T_SCP_USUARIO.CD_USUARIO = T_SCP_PACIENTE.CD_PACIENTE) WHERE CD_CONSULTA= ?");
		stmt.setInt(1, c);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Consulta(
					rs.getInt("CD_CONSULTA"),
					new PsiOnline(
							rs.getInt("CD_PSICOLOGO_ON"),
							rs.getInt("NR_CRP"),
							rs.getString("DS_FORMACAO"),
							rs.getString("DS_BIOGRAFIA"),
							rs.getLong("DS_TELEFONE"),
							rs.getDouble("VL_CONSULTA"),
							rs.getString("DS_PERIODO"),
							rs.getString("DS_FORMA_ATENDIMENTO"),
							rs.getInt("VL_NOTA_ATENDIMENTO"),
							rs.getInt("QT_ATENDIMENTO")),
				new Paciente(
							rs.getInt("CD_USUARIO"),
							rs.getString("NM_USUARIO"),
							rs.getString("DS_EMAIL"),
							rs.getString("DT_NASCIMENTO"),
							rs.getString("DS_LOGIN"),
							rs.getString("DS_SENHA"),
							rs.getInt("NR_NIVEL_PERMISSAO"),
							rs.getString("DS_FOTO"),
							rs.getString("DS_GENERO"),
							rs.getString("DS_CEP"),
							rs.getLong("NR_CPF"),
							rs.getString("DS_HISTORICO"),
							rs.getInt("NR_CONSULTAS_REALIZADAS")),
				 			rs.getInt("CD_CONVERSA"),
					rs.getString("DT_CONSULTA"),
					rs.getInt("VL_NOTA_ATENDIMENTO"), 
					rs.getString("DS_COMENTARIO"));
		}
		
		return new Consulta();
	}
}

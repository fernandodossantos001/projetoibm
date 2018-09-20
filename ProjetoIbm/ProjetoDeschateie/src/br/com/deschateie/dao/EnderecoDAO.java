package br.com.deschateie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import br.com.deschateie.beans.Endereco;
import br.com.deschateie.conexao.Conexao;

public class EnderecoDAO {
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public  EnderecoDAO()throws Exception {
		con = new Conexao().conectar();
	}
	
	public String gravarEnderecoPsicologo(Endereco endereco) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_ENDERECO "
				+ "(CD_ENDERECO,CD_PSICOLOGO,DS_TIPO,DS_LOGRADOURO,NR_NUMERO,DS_COMPLEMENTO,"
				+ "NR_CEP,DS_BAIRRO,DS_CIDADE,DS_UF,DS_PAIS) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1, endereco.getCdEndereco());
		stmt.setInt(2, endereco.getCdAtendente());
		stmt.setString(3, endereco.getTipo());
		stmt.setString(4, endereco.getLogradouro());
		stmt.setString(5, endereco.getNumero());
		stmt.setString(6, endereco.getComplemento());
		stmt.setString(7,endereco.getCep());
		stmt.setString(8, endereco.getBairro());
		stmt.setString(9, endereco.getCidade());
		stmt.setString(10, endereco.getUf());
		stmt.setString(11, endereco.getPais());
		stmt.executeUpdate();
		
		return "Cadastrado com Sucesso";
		
	}
	
	

	public String gravarEnderecoVoluntario(Endereco endereco) throws Exception {
		stmt = con.prepareStatement("INSERT INTO T_SCP_ENDERECO "
				+ "(CD_ENDERECO,CD_VOLUNTARIO,DS_TIPO,DS_LOGRADOURO,NR_NUMERO,DS_COMPLEMENTO,"
				+ "NR_CEP,DS_BAIRRO,DS_CIDADE,DS_UF,DS_PAIS) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
		stmt.setInt(1, endereco.getCdEndereco());
		stmt.setInt(2, endereco.getCdAtendente());
		stmt.setString(3, endereco.getTipo());
		stmt.setString(4, endereco.getLogradouro());
		stmt.setString(5, endereco.getNumero());
		stmt.setString(6, endereco.getComplemento());
		stmt.setString(7,endereco.getCep());
		stmt.setString(8, endereco.getBairro());
		stmt.setString(9, endereco.getCidade());
		stmt.setString(10, endereco.getUf());
		stmt.setString(11, endereco.getPais());
		stmt.executeUpdate();
		
		return "Cadastrado com Sucesso";
		
	}
	
	
	public Endereco consultarEnderecoPsicologo(int cdEndereco)throws Exception {
		stmt= con.prepareStatement("SELECT * FROM T_SCP_ENDERECO WHERE CD_ENDERECO = ?");
		stmt.setInt(1, cdEndereco);
		rs = stmt.executeQuery();
		if(rs.next()) {
			 return new Endereco(
		 			rs.getInt("CD_ENDERECO"),
		 			rs.getInt("CD_PSICOLOGO"),
					rs.getString("DS_TIPO"),
					rs.getString("DS_LOGRADOURO"),
					rs.getString("NR_NUMERO"),
					rs.getString("DS_COMPLEMENTO"),
					rs.getString("NR_CEP"),
					rs.getString("DS_BAIRRO"),
					rs.getString("DS_CIDADE"),
					rs.getString("DS_UF"),
					rs.getString("DS_PAIS"));
			
			 
			 
		}
		
		return new Endereco();
		
	}
	

	public Endereco consultarEnderecoVoluntario(int cdEndereco)throws Exception {
		stmt= con.prepareStatement("SELECT * FROM T_SCP_ENDERECO WHERE CD_ENDERECO = ?");
		stmt.setInt(1, cdEndereco);
		rs = stmt.executeQuery();
		if(rs.next()) {
			return new Endereco(
		 			rs.getInt("CD_ENDERECO"),
		 			rs.getInt("CD_VOLUNTARIO"),
					rs.getString("DS_TIPO"),
					rs.getString("DS_LOGRADOURO"),
					rs.getString("NR_NUMERO"),
					rs.getString("DS_COMPLEMENTO"),
					rs.getString("NR_CEP"),
					rs.getString("DS_BAIRRO"),
					rs.getString("DS_CIDADE"),
					rs.getString("DS_UF"),
					rs.getString("DS_PAIS"));
		}
		
		return new Endereco();
		
	}
	
	public List<Endereco> consultarPorLogradouroEnderecoPsicologo(String logradouro)throws Exception{
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		
		stmt = con.prepareStatement("SELECT * FROM T_SCP_ENDERECO WHERE DS_LOGRADOURO LIKE ?");
		
		stmt.setString(1, logradouro+"%");
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			 listaEndereco.add( new Endereco(
					 			rs.getInt("CD_ENDERECO"),
					 			rs.getInt("CD_PSICOLOGO"),
								rs.getString("DS_TIPO"),
								rs.getString("DS_LOGRADOURO"),
								rs.getString("NR_NUMERO"),
								rs.getString("DS_COMPLEMENTO"),
								rs.getString("NR_CEP"),
								rs.getString("DS_BAIRRO"),
								rs.getString("DS_CIDADE"),
								rs.getString("DS_UF"),
								rs.getString("DS_PAIS")));
		}
		
		return listaEndereco;
		
		
	}
	
	
	public List<Endereco> consultarPorLogradouroEnderecoVoluntario(String logradouro)throws Exception{
		List<Endereco> listaEndereco = new ArrayList<Endereco>();
		
		stmt = con.prepareStatement("SELECT * FROM T_SCP_ENDERECO WHERE DS_LOGRADOURO LIKE ?");
		
		stmt.setString(1, logradouro+"%");
		
		rs = stmt.executeQuery();
		
		while(rs.next()) {
			 listaEndereco.add( new Endereco(
					 			rs.getInt("CD_ENDERECO"),
					 			rs.getInt("CD_VOLUNTARIO"),
								rs.getString("DS_TIPO"),
								rs.getString("DS_LOGRADOURO"),
								rs.getString("NR_NUMERO"),
								rs.getString("DS_COMPLEMENTO"),
								rs.getString("NR_CEP"),
								rs.getString("DS_BAIRRO"),
								rs.getString("DS_CIDADE"),
								rs.getString("DS_UF"),
								rs.getString("DS_PAIS")));
		}
		
		return listaEndereco;
		
		
	}
	
	
	public String apagarEndereco(int cdEndereco)throws Exception{
		stmt = con.prepareStatement("DELETE FROM T_SCP_ENDERECO WHERE CD_ENDERECO = ?");
		stmt.setInt(1, cdEndereco);
		
		return stmt.executeUpdate()+ "linhas excluidas" ;
	}
	
	
	public String alterarEnderecoPsicologo(Endereco endereco)throws Exception{
		stmt= con.prepareStatement("UPDATE T_SCP_ENDERECO SET DS_LOGRADOURO = ?,"
									+ "DS_TIPO = ?,"
									+ "NR_NUMERO = ?,"
									+ "DS_COMPLEMENTO = ?,"
									+ "NR_CEP = ?,"
									+ "DS_BAIRRO = ?,"
									+ "DS_CIDADE = ?,"
									+ "DS_UF = ?,"
									+ "DS_PAIS = ?"
									+ "WHERE CD_PSICOLOGO = ?");
		stmt.setString(1, endereco.getLogradouro());
		stmt.setString(2, endereco.getTipo());
		stmt.setString(3, endereco.getNumero());
		stmt.setString(4, endereco.getCidade());
		stmt.setString(5,endereco.getCep());
		stmt.setString(6, endereco.getBairro());
		stmt.setString(7, endereco.getCidade());
		stmt.setString(8, endereco.getUf());
		stmt.setString(9, endereco.getPais());
		stmt.setInt(10, endereco.getCdAtendente());
		return stmt.executeUpdate() + "linhas atualizadas";
	}
	
	public String alterarEnderecoVoluntario(Endereco endereco)throws Exception{
		stmt= con.prepareStatement("UPDATE T_SCP_ENDERECO SET DS_LOGRADOURO = ?,"
									+ "DS_TIPO = ?,"
									+ "NR_NUMERO = ?,"
									+ "DS_COMPLEMENTO = ?,"
									+ "NR_CEP = ?,"
									+ "DS_BAIRRO = ?,"
									+ "DS_CIDADE = ?,"
									+ "DS_UF = ?,"
									+ "DS_PAIS = ?"
									+ "WHERE CD_VOLUNTARIO = ?");
		stmt.setString(1, endereco.getLogradouro());
		stmt.setString(2, endereco.getTipo());
		stmt.setString(3, endereco.getNumero());
		stmt.setString(4, endereco.getCidade());
		stmt.setString(5,endereco.getCep());
		stmt.setString(6, endereco.getBairro());
		stmt.setString(7, endereco.getCidade());
		stmt.setString(8, endereco.getUf());
		stmt.setString(9, endereco.getPais());
		stmt.setInt(10, endereco.getCdAtendente());
		return stmt.executeUpdate() + "linhas atualizadas";
	}

	
	public void fechar()throws Exception{
		con.close();
	}
}

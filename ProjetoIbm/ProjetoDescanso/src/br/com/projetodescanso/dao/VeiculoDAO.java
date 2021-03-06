package br.com.projetodescanso.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.projetodescanso.beans.Veiculo;
import br.com.projetodescanso.conexao.Conexao;

public class VeiculoDAO {

	
	private Connection con;
	private PreparedStatement stmt;
	private ResultSet rs;
	
	public VeiculoDAO() throws Exception{
		con = new Conexao().conectar();
		
	}
	
	public String gravarDados(Veiculo veiculo) throws Exception{
		stmt = con.prepareStatement("INSERT INTO T_DDD_VEICULO"
				+ "(NR_PLACA,DS_MODELO,NM_COR,NR_ANO,VL_VENDA,VL_COMPRA) VALUES(?,?,?,?,?,?)");
		stmt.setString(1, veiculo.getPlaca());
		stmt.setString(2, veiculo.getModelo());
		stmt.setString(3, veiculo.getCor());
		stmt.setInt(4, veiculo.getAnoFab());
		stmt.setDouble(5, veiculo.getValorVenda());
		stmt.setDouble(6, veiculo.getValorCompra());
		
		stmt.executeUpdate();
		return "Cadastrado Com Sucesso";
	}
	
	public Veiculo consultarPorPlaca(String numeroPlaca)throws Exception {
		stmt = con.prepareStatement("SELECT * FROM T_DDD_VEICULO WHERE NR_PLACA = ?");
		stmt.setString(1, numeroPlaca);
		rs = stmt.executeQuery();
		
		if(rs.next()) {
			return new Veiculo(rs.getString("NR_PLACA"),
								rs.getString("DS_MODELO"),
								rs.getString("NM_COR"),
								rs.getInt("NR_ANO"),
								rs.getDouble("VL_VENDA"),
								rs.getDouble("VL_COMPRA"));
		}else {
			return new Veiculo();
		}
	}
	
	 public List<Veiculo> consultarPormodelo(String modelo) throws Exception{
		 
		 List<Veiculo> listaVeiculos = new ArrayList<Veiculo>();
		 
		 stmt = con.prepareStatement("SELECT * FROM T_DDD_VEICULO WHERE DS_MODELO LIKE ?");
		 
		 stmt.setString(1, modelo + "%");
		 
		 rs = stmt.executeQuery();
		 
		 while(rs.next()) {
			 listaVeiculos.add(new Veiculo
					 	(rs.getString("NR_PLACA"),
						rs.getString("DS_MODELO"),
						rs.getString("NM_COR"),
						rs.getInt("NR_ANO"),
						rs.getDouble("VL_VENDA"),
						rs.getDouble("VL_COMPRA")));
		 }
		 	return listaVeiculos;
		 }
	 
	 
	
	 public int depreciar() throws Exception{
		 stmt = con.prepareStatement("UPDATE T_DDD_VEICULO SET VL_VENDA = VL_VENDA * 0.97");
		 return stmt.executeUpdate();
	 }
		
	 public int apagarPorAno(int ano) throws Exception{
		 stmt = con.prepareStatement("DELETE FROM T_DDD_VEICULO WHERE NR_ANO = ?");
		 stmt.setInt(1, ano);
		 return stmt.executeUpdate();
	 }
	
	public int atualizar(int numeroPlaca ,String modelo,
							String cor, int ano, double valorVenda,
								double valorCompra)throws Exception {
		stmt = con.prepareStatement("UPDATE T_DDD_VEICULO SET DS_MODELO = ?, "
									+ "NM_COR = ?, NR_ANO = ?, VL_VENDA = ?,"
									+ " VL_COMPRA = ? WHERE NR_PLACA = ?");
		stmt.setString(1, modelo);
		stmt.setString(2, cor);
		stmt.setInt(3, ano);
		stmt.setDouble(4, valorVenda);
		stmt.setDouble(5, valorCompra);
		stmt.setInt(6, numeroPlaca);
		return stmt.executeUpdate();
	}
	 
	
	public int atualizar(Veiculo veiculo)throws Exception {
		stmt = con.prepareStatement("UPDATE T_DDD_VEICULO SET DS_MODELO = ?, "
									+ "NM_COR = ?, NR_ANO = ?, VL_VENDA = ?,"
									+ " VL_COMPRA = ? WHERE NR_PLACA = ?");
		stmt.setString(1, veiculo.getModelo());
		stmt.setString(2, veiculo.getCor());
		stmt.setInt(3, veiculo.getAnoFab());
		stmt.setDouble(4, veiculo.getValorVenda());
		stmt.setDouble(5, veiculo.getValorCompra());
		stmt.setString(6, veiculo.getPlaca());
		return stmt.executeUpdate();
	}
	 
	
	public void fechar()throws Exception{
		con.close();
	}
}

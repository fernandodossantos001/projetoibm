package br.com.fiap.teste;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JOptionPane;

import br.com.fiap.excecao.Excecao;

public class Conexao2 {

	public static void main(String[] args) {	
		Connection con = null;
		Statement stmt = null ;
		ResultSet rs = null;
		
		//modo errado de fazer conex�o
		try {
			con = DriverManager.getConnection
			//@localhost para usar em casa, ORCL = xe, no lugar do rm colocar "SYSTEM"
			("jdbc:oracle:thin:@oracle.fiap.com.br:1521:ORCL","RM81400","180900");
			System.out.println("Abriu");
			stmt = con.createStatement();
			String strNome = JOptionPane.showInputDialog("Digite um nome").toUpperCase();
			int intNumero = Integer.parseInt(JOptionPane.showInputDialog("Digite o n�mero do cliente"));
			rs = stmt.executeQuery("select * from  T_DDD_CLIENTE where NR_CLIENTE="+intNumero+" and NM_CLIENTE = '" + strNome +"'");
			if(rs.next()) {
				System.out.println("Autorizado ");
				System.out.print("N�mero :" + rs.getInt("NR_CLIENTE") + " ");
				System.out.print("Nome : "+rs.getString("NM_CLIENTE")+ " ");
				System.out.print("Estrelas : " + rs.getInt("QT_ESTRELAS")+ " ");
			}else {
				System.out.println("N�o autorizado");
			}
			
//			buscando todos os dados da tabela
//			while(rs.next()) {
//				System.out.print("N�mero :" + rs.getInt("NR_CLIENTE") + " ");
//				System.out.print("Nome : "+rs.getString("NM_CLIENTE")+ " ");
//				System.out.print("Estrelas : " + rs.getInt("QT_ESTRELAS")+ " ");
//				System.out.println();
//			}
//			
			
		}catch(Exception e) {
			//1� Momento: o sistema est� em teste
			e.printStackTrace();
			//2� Momento: o sistema est� homologado
			System.out.println(Excecao.tratarExcecao(e));
		}finally {
			// � sempre executado para encerrar conex�o
			try {
				con.close();
				System.out.println("fechou");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println(Excecao.tratarExcecao(e));
			}
		}

	}

}

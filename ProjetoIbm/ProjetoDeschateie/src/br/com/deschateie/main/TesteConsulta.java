package br.com.deschateie.main;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.excecao.Excecao;

public class TesteConsulta {

	public static void main(String[] args) {
		
		try {
			
			List<Consulta> lista = new ArrayList<Consulta>();
			
			for (Consulta consulta : lista) {
				System.out.println(consulta.getAll());
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}
		

	}

}

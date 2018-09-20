package br.com.deschateie.teste;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.bo.ConsultaBO;
import br.com.deschateie.excecao.Excecao;

public class TesteConsulta {

	public static void main(String[] args) {
		try {
			//System.out.println(ConsultaBO.pesquisarConsulta(1).getAll());
			
			List<Consulta> listaConsultas = new ArrayList<Consulta>();
			
			listaConsultas = ConsultaBO.pesquisarConsulta();
			for (Consulta consulta : listaConsultas) {
				System.out.println(consulta.getAll());
			}
			
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}

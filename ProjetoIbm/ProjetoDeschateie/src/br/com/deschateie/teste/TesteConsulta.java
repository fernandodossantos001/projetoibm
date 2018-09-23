package br.com.deschateie.teste;

import java.util.ArrayList;
import java.util.List;

import br.com.deschateie.beans.Consulta;
import br.com.deschateie.beans.Paciente;
import br.com.deschateie.beans.PsiOnline;
import br.com.deschateie.bo.ConsultaBO;
import br.com.deschateie.bo.PsiOnlineBO;
import br.com.deschateie.excecao.Excecao;

public class TesteConsulta {

	public static void main(String[] args) {
		try {
//			
//			List<List> listaConsultas = new ArrayList<List>();
//			List<PsiOnline> psicologos = new ArrayList<PsiOnline>();
//			List<Paciente> pacientes = new ArrayList<Paciente>();
//			List<Consulta> consultas = new ArrayList<Consulta>();
//			//System.out.println(ConsultaBO.pesquisarConsulta(1).getAll());
//			
//			listaConsultas = ConsultaBO.pesquisarConsultas();
//			consultas = listaConsultas.get(0);
//			psicologos = listaConsultas.get(2);
//			
//			for (Consulta consulta : consultas) {
//				
//					System.out.println(consulta.getAll());
//					
//					System.out.println("--------------------");
//					
//					System.out.println("-----------------");
//					for (PsiOnline psicologo : psicologos) {
//						psicologo = PsiOnlineBO.pesquisarPsicologoOnline(consulta.getCodPsiOnline());
//						System.out.println(psicologo.getAll());
//						System.out.println("------------------------");
//						break;
//						
//					}
//			}
			
			List<PsiOnline> lista = new ArrayList<PsiOnline>();
			List<Paciente> listaPaciente = new ArrayList<Paciente>();
			lista = ConsultaBO.pesquisarPsiOnline(15);
			listaPaciente = ConsultaBO.pesquisarPaciente(9);
			
			for(PsiOnline psi:lista) {
				System.out.println("----- Psicologo ----- ");
				System.out.println(psi.getAll());
				for (Paciente paciente : listaPaciente) {
					System.out.println("----- Paciente ------");
					System.out.println(paciente.getAll());
					break;
				}
				System.out.println("------------------");
			}
			
			
			
			//				A T E N � � O !!!!!!!!!!!!!!!
			//-----------------------------------------------------------
			
			// NAO ESQUECER DE VALIDAR ESSE CARA, VAI DAR BO !!
			
			//-----------------------------------------------------------			
			
			
		//	System.out.println(PsiOnlineBO.excluirPsicologoOnline(20));
			
			
			
			
			//-----------------------------------------------------------
			
			
			
			
			
//			listaConsultas = ConsultaBO.pesquisarConsultaCodPaciente(7);
//			for (Consulta consulta : listaConsultas) {
//				System.out.println(consulta.getAll());
//			}
//			
//			listaConsultas = ConsultaBO.pesquisarConsulta();
//			for (Consulta consulta : listaConsultas) {
//				System.out.println(consulta.getAll());
//			}
//		
//			listaConsultas=  ConsultaBO.pesquisarConsultaPaciente("G");
//			for(Consulta consultas:listaConsultas) {
//				System.out.println(consultas.getAll());
//			}
			
//			listaConsultas = ConsultaBO.pesquisarConsultas();
//			 
//			List <Consulta> consultas =new ArrayList<Consulta>() ;
//			List <Paciente> pacientes = new ArrayList<Paciente>();
//			List<Psicologo> psicologos = new ArrayList<Psicologo>();
//			consultas = listaConsultas.get(0);
//			pacientes = listaConsultas.get(1);
//			psicologos = listaConsultas.get(2);
//			
//			
//			for (Consulta consulta : consultas) {
//				System.out.println(consulta.getAll());
//				
//			}
//			
//			for (Paciente paciente2 : pacientes) {
//				System.out.println(paciente2.getAll());
//			}
//			
//			for (Psicologo psicologo : psicologos) {
//				System.out.println(psicologo.getAll());
//			}
			//System.out.println(listaConsultas.get(0));
			

			
			System.out.println();
			
			//System.out.println(new ConsultaDAO().excluirConsulta(10));
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(Excecao.tratarExcecao(e));
		}

	}

}

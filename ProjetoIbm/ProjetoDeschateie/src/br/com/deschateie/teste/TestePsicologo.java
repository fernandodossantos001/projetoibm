package br.com.deschateie.teste;

import br.com.deschateie.beans.Usuario;
import br.com.deschateie.dao.UsuarioDAO;
import br.com.deschateie.excecao.Excecao;

public class TestePsicologo {

	public static void main(String[] args) {
		try {
			
//			System.out.println(new PsicologoDAO().consultarPsicologo(20).getAll());
//			System.out.println("---------------------------------");
//			System.out.println(PsicologoBO.pesquisarPsicologo(20).getAll());
			System.out.println("---------------------------");
			System.out.println("Psicologo");
			
			
			//depois que que for feito psicologo online executar esse linha de codigo para testar
			//System.out.println(PsicologoBO.excluirPsicologo(44));
			
		//	System.out.println(PsicologoBO.NovoPsicologo(
			//		new Psicologo(46, "testepsicologo", "psicologo@teste.com", "12/01/1997", "psicologoFernando", "teste", 4, "c:","masculino", 1234569, "psicologia", "aaaa", 984738372, 200.60)));
		//	System.out.println(PsicologoBO.AlterarDadosPsicologo(new Psicologo(46, "testepsicologo", "psicologo@teste2.com", "12/01/1997", "psicologoFernando23", "teste", 4, "c:","masculino", 5014504, "psicologia", "aaaa", 984738372, 200.60)));
			
			
			//System.out.println(UsuarioBO.AlterarDadosUsuario(new Usuario(1,"Paki","libero@euismodest2.net","04/10/1990","Suspenddisse","MQS40JSP8VL",5,"OUTROS", "c:/")));

			//System.out.println(PsicologoBO.pesquisarPsicologo(44).getAll());
			
			
		} catch (Exception e) {
			e.printStackTrace();
			
			System.out.println(Excecao.tratarExcecao(e));
		}
	}

}

package br.com.deschateie.beans;

public class Consulta implements Comparable<Consulta>{
	
	private int codConsulta;
	private PsiOnline psiOnline;
	private Paciente paciente;
	private int codConversa;
	private String dataConsulta;
	private int notaAtendimento;
	private String comentario;
	
	
	public Consulta() {
		this.psiOnline = new PsiOnline();
		this.paciente = new Paciente();
	}
	
	public Consulta(int codConsulta, PsiOnline psiOnline, Paciente paciente, int codConversa, String dataConsulta,
			int notaAtendimento, String comentario) {
		setCodConsulta(codConsulta);
		setPsiOnline(psiOnline);
		setPaciente(paciente);
		setCodConversa(codConversa);
		setDataConsulta(dataConsulta);
		setNotaAtendimento(notaAtendimento);
		setComentario(comentario);
	}
	
	public void setAll(int codConsulta, PsiOnline psiOnline, Paciente paciente, int codConversa, String dataConsulta,
			int notaAtendimento, String comentario) {
		setCodConsulta(codConsulta);
		setPsiOnline(psiOnline);
		setPaciente(paciente);
		setCodConversa(codConversa);
		setDataConsulta(dataConsulta);
		setNotaAtendimento(notaAtendimento);
		setComentario(comentario);
	}
	
	
	
	
	public String getAll() {
		return "Codigo consulta :" + codConsulta + "\n"+
				"------------ DADOS PSI ONLINE -----------"+ "\n"
				+ psiOnline.getAll() +  "\n"
				+"------------------------"+ "\n"
				+ "----------- DADOS PACIENTE ----------" + "\n"
				+ paciente.getAll() + "\n"
				+"-----------------------------"
				+ "Codigo conversa : " +codConversa+ "\n"
				+"Data Consulta :" + dataConsulta + "\n"
				+ "Nota Atendimento :" + notaAtendimento+ "\n"
				+ "Comentario :" + comentario;
	}

	public int getCodConsulta() {
		return codConsulta;
	}
	public void setCodConsulta(int codConsulta) {
		this.codConsulta = codConsulta;
	}
	public PsiOnline getPsicologo() {
		return psiOnline;
	}
	public void setPsiOnline(PsiOnline psiOnline) {
		this.psiOnline = psiOnline;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public int getCodConversa() {
		return this.codConversa;
	}
	public void setCodConversa(int codConversa) {
		this.codConversa = codConversa;
	}
	public String getDataConsulta() {
		return dataConsulta;
	}
	public void setDataConsulta(String dataConsulta) {
		this.dataConsulta = dataConsulta;
	}
	public int getNotaAtendimento() {
		return notaAtendimento;
	}
	public void setNotaAtendimento(int notaAtendimento) {
		this.notaAtendimento = notaAtendimento;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	@Override
	public int compareTo(Consulta o) {
		if(this.codConsulta>o.codConsulta) {
			return 1;
		}else if (this.codConsulta<o.codConsulta) {
			return -1;
		}else {
			return 0;
		}
		
	}
	
	
}

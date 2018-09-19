package br.com.deschateie.beans;

public class Consulta implements Comparable<Consulta>{
	
	private int codConsulta;
	private Psicologo psicologo;
	private Paciente paciente;
	private Conversa conversa;
	private String dataConsulta;
	private int notaAtendimento;
	private String comentario;
	
	
	public Consulta() {
		this.psicologo = new Psicologo();
		this.paciente = new Paciente();
		this.conversa = new Conversa();
	}
	
	public Consulta(int codConsulta, Psicologo psicologo, Paciente paciente, Conversa conversa, String dataConsulta,
			int notaAtendimento, String comentario) {
		setCodConsulta(codConsulta);
		setPsicologo(psicologo);
		setPaciente(paciente);
		setConversa(conversa);
		setDataConsulta(dataConsulta);
		setNotaAtendimento(notaAtendimento);
		setComentario(comentario);
	}
	
	public void setAll(int codConsulta, Psicologo psicologo, Paciente paciente, Conversa conversa, String dataConsulta,
			int notaAtendimento, String comentario) {
		setCodConsulta(codConsulta);
		setPsicologo(psicologo);
		setPaciente(paciente);
		setConversa(conversa);
		setDataConsulta(dataConsulta);
		setNotaAtendimento(notaAtendimento);
		setComentario(comentario);
	}
	
	
	
	
	public String getAll() {
		return "Codigo consulta :" + codConsulta + "\n"
				+ psicologo.getAll() +  "\n"
				+ paciente.getAll() + "\n"
				+ conversa.getAll()+ "\n"
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
	public Psicologo getPsicologo() {
		return psicologo;
	}
	public void setPsicologo(Psicologo psicologo) {
		this.psicologo = psicologo;
	}
	public Paciente getPaciente() {
		return paciente;
	}
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}
	public Conversa getConversa() {
		return conversa;
	}
	public void setConversa(Conversa conversa) {
		this.conversa = conversa;
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

package modelo;

public class TelefoneAgenda {

	private long codigo;
	private String telefone;
	private Agenda codigoAgenda;

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public Agenda getCodigoAgenda() {
		return codigoAgenda;
	}

	public void setCodigoAgenda(Agenda codigoAgenda) {
		this.codigoAgenda = codigoAgenda;
	}



}

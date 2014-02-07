package modelo;

import java.util.HashSet;
import java.util.Set;

public class Agenda {

	private long codigoAgenda;
	private String nome;
	private String complemento;
	private String observacao;
	private Set<TelefoneAgenda> telefones = new HashSet<TelefoneAgenda>();
	private Set<EmailAgenda> emails = new HashSet<EmailAgenda>();

	public long getCodigoAgenda() {
		return codigoAgenda;
	}

	public void setCodigoAgenda(long codigoAgenda) {
		this.codigoAgenda = codigoAgenda;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Set<TelefoneAgenda> getTelefones() {
		return telefones;
	}

	public void setTelefones(Set<TelefoneAgenda> telefones) {
		this.telefones = telefones;
	}

	public Set<EmailAgenda> getEmails() {
		return emails;
	}

	public void setEmails(Set<EmailAgenda> emails) {
		this.emails = emails;
	}

	public void AddTelefones(TelefoneAgenda telefoneAgenda) {
		telefoneAgenda.setCodigoAgenda(this);
		telefones.add(telefoneAgenda);
	}

}
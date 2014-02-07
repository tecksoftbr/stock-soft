package controlador;

import java.util.List;

import modelo.Agenda;
import repositorio.IRepositorioAgenda;
import repositorio.RepositorioAgenda;

public class CadastroAgenda {
	
	private IRepositorioAgenda agendaRep;
	
	public CadastroAgenda(RepositorioAgenda repAgenda){
		setAgendaRep(repAgenda);
	}

	public void setAgendaRep(IRepositorioAgenda agendaRep) {
		this.agendaRep = agendaRep;
	}

	public IRepositorioAgenda getAgendaRep() {
		return agendaRep;
	}
	public void salvarAgenda(Agenda agenda) {
		agendaRep.salvarAgenda(agenda);	
	}

	public void removerAgenda(Agenda agenda) {
		agendaRep.removerAgenda(agenda);	
	}

	public void atualizarAgenda(Agenda agenda) {
		agendaRep.atualizarAgenda(agenda);	
	}
	
	public List<Agenda> procurarAgenda(String texto) {
		return agendaRep.procurarAgenda(texto);
	}

	public List<Agenda> listarAgenda() {		
		return agendaRep.listarAgenda();
	}

}

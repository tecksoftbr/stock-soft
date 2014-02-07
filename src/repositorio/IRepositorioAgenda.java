package repositorio;

import java.util.List;

import modelo.Agenda;


public interface IRepositorioAgenda {
	//metado para salvar Agenda passando o objeto agenda
	void salvarAgenda(Agenda agenda);
	
	//metado para remover Agenda passando o objeto agenda
	void removerAgenda(Agenda agenda);
	
	//metado para atualizar Agenda passando o objeto agenda
	void atualizarAgenda(Agenda agenda);
	
	//metado para salvar Agenda passando o objeto agenda
	public List<Agenda> procurarAgenda(String texto);
	
	//metado para listar todos os agenda que est�o no BD
	public List<Agenda> listarAgenda();

}

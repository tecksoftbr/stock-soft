package repositorio;

import java.util.List;

import modelo.Lembrete;

public interface IRepositorioLembrete {

	//metado para salvar Lembrete passando o objeto lembrete
	void salvarLembrete(Lembrete lembrete);
	
	//metado para remover Lembrete passando o objeto lembrete
	void removerLembrete(Lembrete lembrete);
	
	//metado para atualizar Lembrete passando o objeto lembrete
	void atualizarLembrete(Lembrete lembrete);
	
	//metado para salvar Lembrete passando o objeto lembrete
	public List<Lembrete> procurarLembrete(String texto);
	
	//metado para listar todos os lembrete que estão no BD
	public List<Lembrete> listarLembrete();
	
}

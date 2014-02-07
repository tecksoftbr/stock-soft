package repositorio;

import java.util.List;

import modelo.Orcamento;

public interface IRepositorioOrcamento {
	//metado para salvar Orcamento passando o objeto orcamento
	void salvarOrcamento(Orcamento orcamento);
	
	//metado para remover Orcamento passando o objeto orcamento
	void removerOrcamento(Orcamento orcamento);
	
	//metado para atualizar Orcamento passando o objeto orcamento
	void atualizarOrcamento(Orcamento orcamento);
	
	//metado para salvar Orcamento passando o objeto orcamento
	public List<Orcamento> procurarOrcamento(String texto);
	
	//metado para listar todos os orcamento que estão no BD
	public List<Orcamento> listarOrcamento();

}

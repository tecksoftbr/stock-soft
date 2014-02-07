package repositorio;

import java.util.List;

import modelo.ContasReceber;

public interface IRepositorioContasReceber {
	
	//metado para salvar ContasReceber passando o objeto  contasReceber
	void salvarContasReceber(ContasReceber contasReceber);
	
	//metado para remover ContasReceber passando o objeto contasReceber
	void removerContasReceber(ContasReceber contasReceber);
	
	//metado para atualizar ContasReceber passando o objeto contasReceber
	void atualizarContasReceber(ContasReceber contasReceber);
	
	//metado para salvar ContasReceber passando o objeto contasReceber
	public List<ContasReceber> procurarcontasReceber(String texto);
	
	//metado para listar todos os contasReceber que estão no BD
	public List<ContasReceber> listarContasReceber();

}

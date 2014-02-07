package repositorio;

import java.util.List;

import modelo.ContasPagar;


public interface IRepositorioContasPagar {
	
	//metado para salvar ContasPagar passando o objeto contasPagar
	void salvarContasPagar(ContasPagar contasPagar);
	
	//metado para remover ContasPagar passando o objeto contasPagar
	void removerContasPagar(ContasPagar contasPagar);
	
	//metado para atualizar ContasPagar passando o objeto contasPagar
	void atualizarContasPagar(ContasPagar contasPagar);
	
	//metado para salvar ContasPagar passando o objeto empresa
	public List<ContasPagar> procurarContasPagar(String texto);
	
	//metado para listar todos os contasPagar que estão no BD
	public List<ContasPagar> listarContasPagar();

}

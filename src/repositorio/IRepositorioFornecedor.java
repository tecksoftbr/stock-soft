package repositorio;

import java.util.List;

import modelo.Fornecedor;


public interface IRepositorioFornecedor {
	//metado para salvar Fornecedor passando o objeto fornecedor
	void salvarFornecedor(Fornecedor fornecedor);
	
	//metado para remover Fornecedor passando o objeto fornecedor
	void removerFornecedor(Fornecedor fornecedor);
	
	//metado para atualizar Fornecedor passando o objeto fornecedor
	void atualizarFornecedor(Fornecedor fornecedor);
	
	//metado para salvar Fornecedor passando o objeto fornecedor
	public List<Fornecedor> procurarFornecedor(String texto);
	
	//metado para listar todos os fornecedor que estão no BD
	public List<Fornecedor> listarFornecedor();
	

}

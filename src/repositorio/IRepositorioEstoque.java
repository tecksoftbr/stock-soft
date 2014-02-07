package repositorio;

import java.util.List;

import modelo.Estoque;

public interface IRepositorioEstoque {

	// metado para salvar Estoque passando o objeto estoque
	void salvarEstoque(Estoque estoque);

	// metado para remover Estoque passando o objeto estoque
	void removerEstoque(Estoque estoque);

	// metado para atualizar Estoque passando o objeto fornecedor
	void atualizarEstoque(Estoque estoque);

	// metado para salvar Estoque passando o objeto estoque
	public List<Estoque> procurarEstoque(String texto);

	// metado para listar todos os Estoque que estão no BD
	public List<Estoque> listarEstoque();
}
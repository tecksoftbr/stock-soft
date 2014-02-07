package repositorio;

import java.util.List;
import modelo.Produto;

public interface IRepositorioProduto {
	//metado para salvar Produto passando o objeto produto
	void salvarProduto(Produto produto);
	
	//metado para remover Produto passando o objeto produto
	void removerProduto(Produto produto);
	
	//metado para atualizar Produto passando o objeto produto
	void atualizarProduto(Produto produto);
	
	//metado para salvar Produto passando o objeto produto
	public List<Produto> procurarProduto(String texto);
	
	//metado para listar todos os produto que estão no BD
	public List<Produto> listarProduto();

}

package repositorio;

import java.util.List;
import modelo.TelefoneFornecedor;

public interface IRepositorioTelefoneFornecedor {
	
	//metado para salvar Fornecedor passando o objeto fornecedor
	void salvarTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor);
	
	//metado para remover Fornecedor passando o objeto fornecedor
	void removerTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor);
	
	//metado para atualizar Fornecedor passando o objeto fornecedor
	void atualizarTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor);
	
	//metado para salvar Fornecedor passando o objeto fornecedor
	public List<TelefoneFornecedor> procurarTelefoneFornecedor(String texto);
	
	//metado para listar todos os fornecedor que estão no BD
	public List<TelefoneFornecedor> listarTelefoneFornecedor();
	

}

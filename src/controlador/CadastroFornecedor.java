package controlador;

import java.util.List;
import modelo.Fornecedor;
import repositorio.IRepositorioFornecedor;
import repositorio.RepositorioFornecedor;

public class CadastroFornecedor {
	
	private IRepositorioFornecedor fornecedorRep;
	
	public CadastroFornecedor(RepositorioFornecedor repFornecedor){
		this.fornecedorRep = repFornecedor;
	}

	public void setFornecedorRep(IRepositorioFornecedor fornecedorRep) {
		this.fornecedorRep = fornecedorRep;
	}

	public IRepositorioFornecedor getFornecedorRep() {
		return fornecedorRep;
	}
	

	
//****************************************************************************************	
	
	public void salvarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
          fornecedorRep.salvarFornecedor(fornecedor);
	}

	
	public void removerFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		
		fornecedorRep.removerFornecedor(fornecedor);
		
	}

	
	public void atualizarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
         fornecedorRep.atualizarFornecedor(fornecedor);
	}

	
	public List<Fornecedor> procurarFornecedor(String texto) {
		// TODO Auto-generated method stub
        return fornecedorRep.procurarFornecedor(texto);
	}

	
	public List<Fornecedor> listarFornecedor() {
		// TODO Auto-generated method stub
	

		return fornecedorRep.listarFornecedor();
	}

}

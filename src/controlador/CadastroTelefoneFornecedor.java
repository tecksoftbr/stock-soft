package controlador;

import java.util.List;

import modelo.TelefoneFornecedor;
import repositorio.IRepositorioTelefoneFornecedor;
import repositorio.RepositorioTelefoneFornecedor;

public class CadastroTelefoneFornecedor {

	private IRepositorioTelefoneFornecedor telefoneFornecedorRep;

	public CadastroTelefoneFornecedor(
			RepositorioTelefoneFornecedor repTelefoneFornecedor) {
		setTelefoneFornecedorRep(repTelefoneFornecedor);
	}

	public IRepositorioTelefoneFornecedor getTelefoneFornecedorRep() {
		return telefoneFornecedorRep;
	}

	public void setTelefoneFornecedorRep(
			IRepositorioTelefoneFornecedor telefoneFornecedorRep) {
		this.telefoneFornecedorRep = telefoneFornecedorRep;
	}

	// **********************************************************************************

	public void salvarTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
		telefoneFornecedorRep.salvarTelefoneFornecedor(telefoneFornecedor);
	}

	public void removerTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
		telefoneFornecedorRep.removerTelefoneFornecedor(telefoneFornecedor);
	}

	public void atualizarTelefoneFornecedor(
			TelefoneFornecedor telefoneFornecedor) {
		telefoneFornecedorRep.atualizarTelefoneFornecedor(telefoneFornecedor);
	}

	public List<TelefoneFornecedor> procurarTelefoneFornecedor(String texto) {
		return telefoneFornecedorRep.procurarTelefoneFornecedor(texto);
	}

	public List<TelefoneFornecedor> listarTelefoneFornecedor() {
		return telefoneFornecedorRep.listarTelefoneFornecedor();
	}

}

package controlador;

import java.util.List;

import modelo.ContasPagar;
import repositorio.IRepositorioContasPagar;
import repositorio.RepositorioContasPagar;

public class CadastroContasPagar {
	
	private IRepositorioContasPagar contasPagarRep;
	
	public CadastroContasPagar(RepositorioContasPagar repContasPagar){
		this.setContasPagarRep(repContasPagar);
		
	}

	public IRepositorioContasPagar getContasPagarRep() {
		return contasPagarRep;
	}

	public void setContasPagarRep(IRepositorioContasPagar contasPagarRep) {
		this.contasPagarRep = contasPagarRep;
	}
	
//************************************************************************************	
	public void salvarContasPagar(ContasPagar contasPagar) {
		contasPagarRep.salvarContasPagar(contasPagar);	
	}

	
	public void removerContasPagar(ContasPagar contasPagar) {
		contasPagarRep.removerContasPagar(contasPagar);	
	}

	
	public void atualizarContasPagar(ContasPagar contasPagar) {
		contasPagarRep.atualizarContasPagar(contasPagar);
		
	}

	
	public List<ContasPagar> procurarContasPagar(String texto) {
		
		return contasPagarRep.procurarContasPagar(texto);
	}

	
	public List<ContasPagar> listarContasPagar() {
		
		return contasPagarRep.listarContasPagar();
	}

}

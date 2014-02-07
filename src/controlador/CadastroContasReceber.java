package controlador;

import java.util.List;

import modelo.ContasReceber;
import repositorio.IRepositorioContasReceber;
import repositorio.RepositorioContasReceber;

public class CadastroContasReceber {
	
	private IRepositorioContasReceber contasReceberRep;
	
	public CadastroContasReceber(RepositorioContasReceber repContasReceber){
		this.setContasReceberRep(repContasReceber);
		
	}

	public IRepositorioContasReceber getContasReceberRep() {
		return contasReceberRep;
	}

	public void setContasReceberRep(IRepositorioContasReceber contasReceberRep) {
		this.contasReceberRep = contasReceberRep;
	}
	
//**************************************************************************************
	public void salvarContasReceber(ContasReceber contasReceber) {
	     contasReceberRep.salvarContasReceber(contasReceber);
		
	}

	
	public void removerContasReceber(ContasReceber contasReceber) {
		contasReceberRep.removerContasReceber(contasReceber);
		
	}

	
	public void atualizarContasReceber(ContasReceber contasReceber) {
		contasReceberRep.atualizarContasReceber(contasReceber);
		
	}

	
	public List<ContasReceber> procurarcontasReceber(String texto) {
	
		return contasReceberRep.procurarcontasReceber(texto);
	}

	
	public List<ContasReceber> listarContasReceber() {
	
		return contasReceberRep.listarContasReceber();
	}
	

}

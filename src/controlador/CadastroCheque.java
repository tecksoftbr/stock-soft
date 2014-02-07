package controlador;

import java.util.List;

import modelo.Cheque;
import repositorio.IRepositorioCheque;
import repositorio.RepositorioCheque;

public class CadastroCheque {
	
	private IRepositorioCheque chequeRep;
	
	public CadastroCheque(RepositorioCheque repCheque){
		this.setChequeRep(repCheque);
	}

	public IRepositorioCheque getChequeRep() {
		return chequeRep;
	}

	public void setChequeRep(IRepositorioCheque chequeRep) {
		this.chequeRep = chequeRep;
	}
	
//******************************************************************

	public void salvarCheque(Cheque cheque) {
       chequeRep.salvarCheque(cheque);	
	}


	public void removerCheque(Cheque cheque) {
		chequeRep.removerCheque(cheque);	
	}


	public void atualizarCheque(Cheque cheque) {
		chequeRep.atualizarCheque(cheque);	
	}


	public List<Cheque> procurarCheque(String texto) {
		return chequeRep.procurarCheque(texto);
	}

	
	public List<Cheque> listarCheque() {
		return chequeRep.listarCheque();
	}
	

}

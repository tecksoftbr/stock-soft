package controlador;

import java.util.List;

import modelo.Orcamento;
import repositorio.IRepositorioOrcamento;
import repositorio.RepositorioOrcamento;

public class CadastroOrcamento {
	
	private IRepositorioOrcamento orcamentoRep;
	
	public CadastroOrcamento(RepositorioOrcamento repOrcamento){
		this.setOrcamentoRep(repOrcamento);
	}

	public IRepositorioOrcamento getOrcamentoRep() {
		return orcamentoRep;
	}

	public void setOrcamentoRep(IRepositorioOrcamento orcamentoRep) {
		this.orcamentoRep = orcamentoRep;
	}
//********************************************************************************
	public void salvarOrcamento(Orcamento orcamento) {
        orcamentoRep.salvarOrcamento(orcamento);		
	}

	public void removerOrcamento(Orcamento orcamento) {
		orcamentoRep.removerOrcamento(orcamento);
	}

	public void atualizarOrcamento(Orcamento orcamento) {
		orcamentoRep.atualizarOrcamento(orcamento);	
	}

	public List<Orcamento> procurarOrcamento(String texto) {

		return orcamentoRep.procurarOrcamento(texto);
	}

	public List<Orcamento> listarOrcamento() {
		return orcamentoRep.listarOrcamento();
	}


}

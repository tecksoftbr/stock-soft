package controlador;

import java.util.List;

import modelo.Estoque;
import repositorio.IRepositorioEstoque;
import repositorio.RepositorioEstoque;

public class CadastroEstoque {
	
	private IRepositorioEstoque estoqueRep;
	
	public CadastroEstoque(RepositorioEstoque RepEstoque){
     this.setEstoqueRep(RepEstoque);		
	}

	public IRepositorioEstoque getEstoqueRep() {
		return estoqueRep;
	}

	public void setEstoqueRep(IRepositorioEstoque estoqueRep) {
		this.estoqueRep = estoqueRep;
	}
	
	
//**********************************************************************
	
	public void salvarEstoque(Estoque estoque) {
		estoqueRep.salvarEstoque(estoque);	
	}

	
	public void removerEstoque(Estoque estoque) {
		estoqueRep.removerEstoque(estoque);	
	}

	
	public void atualizarEstoque(Estoque estoque) {
		estoqueRep.atualizarEstoque(estoque);	 	
	}

	
	public List<Estoque> procurarEstoque(String texto) {	
		return estoqueRep.procurarEstoque(texto);
	}

	
	public List<Estoque> listarEstoque() {
		return estoqueRep.listarEstoque();
	}

}

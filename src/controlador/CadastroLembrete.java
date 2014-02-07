package controlador;

import java.util.List;

import modelo.Lembrete;
import repositorio.IRepositorioLembrete;
import repositorio.RepositorioLembrete;

public class CadastroLembrete {
	
	private IRepositorioLembrete lembreteRep;
	
	public CadastroLembrete(RepositorioLembrete replembrete){
		this.setLembreteRep(replembrete);
	}

	public IRepositorioLembrete getLembreteRep() {
		return lembreteRep;
	}

	public void setLembreteRep(IRepositorioLembrete lembreteRep) {
		this.lembreteRep = lembreteRep;
	}


	public void salvarLembrete(Lembrete lembrete) {
	 lembreteRep.salvarLembrete(lembrete);
		
	}

	
	public void removerLembrete(Lembrete lembrete) {
	 lembreteRep.removerLembrete(lembrete);
		
	}

	
	public void atualizarLembrete(Lembrete lembrete) {
		lembreteRep.atualizarLembrete(lembrete);
		
	}

	
	public List<Lembrete> procurarLembrete(String texto) {
		
		return lembreteRep.procurarLembrete(texto);
	}

	
	public List<Lembrete> listarLembrete() {
		
		return lembreteRep.listarLembrete();
	}
}

package controlador;

import java.util.List;
import modelo.Empresa;
import repositorio.IRepositorioEmpresa;
import repositorio.RepositorioEmpresa;

public class CadastroEmpresa {
	
	private IRepositorioEmpresa empresaRep;

	public CadastroEmpresa(RepositorioEmpresa repEmpresa){
		this.empresaRep = repEmpresa;
	}

	public void setEmpresaRep(IRepositorioEmpresa empresaRep) {
		this.empresaRep = empresaRep;
	}

	public IRepositorioEmpresa getEmpresaRep() {
		return empresaRep;
	}
	public void salvarEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		
		empresaRep.salvarEmpresa(empresa);
	}

	public void removerEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		empresaRep.removerEmpresa(empresa);
	}

	public void atualizarEmpresa(Empresa empresa) {
		// TODO Auto-generated method stub
		empresaRep.atualizarEmpresa(empresa);
	}

	public List<Empresa> procurarEmpresa(String texto) {
		// TODO Auto-generated method stub
        return empresaRep.procurarEmpresa(texto);		
	}
	
	public List<Empresa> listarEmpresa() {
		// TODO Auto-generated method stub
		return empresaRep.listarEmpresa();
	}

}

package repositorio;

import java.util.List;

import modelo.Empresa;;

public interface IRepositorioEmpresa {
	
	//metado para salvar Empresa passando o objeto empresa
	void salvarEmpresa(Empresa empresa);
	
	//metado para remover Empresa passando o objeto empresa
	void removerEmpresa(Empresa empresa);
	
	//metado para atualizar Empresa passando o objeto empresa
	void atualizarEmpresa(Empresa empresa);
	
	//metado para salvar Empresa passando o objeto empresa
	public List<Empresa> procurarEmpresa(String texto);
	
	//metado para listar todos os empresas que estão no BD
	public List<Empresa> listarEmpresa();
	
		

}

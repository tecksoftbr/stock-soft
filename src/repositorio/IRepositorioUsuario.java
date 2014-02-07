/*
 * Projeto - StockSoft
 * Interface Usuário 
 * Data de Criação: 16/09/2012 às 21:27 PM
 * Autor:Paulo Roberto
 * Propiedade da TechCode
 * 
 */


package repositorio;

import java.util.List;

import modelo.Usuario;

public interface IRepositorioUsuario {
	
	//metado para salvar usuário passando o objeto Usuario
	void salvarUsuario(Usuario usuario);
	
	//metado para remover usuário passando o objeto Usuario
	void removerUsuario(Usuario usuario);
	
	//metado para atualizar usuário passando o objeto Usuario
	void atualizarUsuario(Usuario usuario);
	
	//metado para salvar usuário passando o objeto Usuario
	public List<Usuario> procurarUsuario(String texto);
	
	//metado para listar todos os usuários que estão no BD
	public List<Usuario> listarUsuario();
	
		

	

}

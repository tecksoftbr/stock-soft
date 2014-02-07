/*
 * Projeto - StockSoft
 * Interface Usu�rio 
 * Data de Cria��o: 16/09/2012 �s 21:27 PM
 * Autor:Paulo Roberto
 * Propiedade da TechCode
 * 
 */


package repositorio;

import java.util.List;

import modelo.Usuario;

public interface IRepositorioUsuario {
	
	//metado para salvar usu�rio passando o objeto Usuario
	void salvarUsuario(Usuario usuario);
	
	//metado para remover usu�rio passando o objeto Usuario
	void removerUsuario(Usuario usuario);
	
	//metado para atualizar usu�rio passando o objeto Usuario
	void atualizarUsuario(Usuario usuario);
	
	//metado para salvar usu�rio passando o objeto Usuario
	public List<Usuario> procurarUsuario(String texto);
	
	//metado para listar todos os usu�rios que est�o no BD
	public List<Usuario> listarUsuario();
	
		

	

}

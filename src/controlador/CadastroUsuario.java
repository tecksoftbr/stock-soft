package controlador;

import java.util.List;

import modelo.Usuario;
import repositorio.IRepositorioUsuario;
import repositorio.RepositorioUsuario;

public class CadastroUsuario {

	private IRepositorioUsuario usuarioRep;

	public CadastroUsuario(RepositorioUsuario repUsuario) {
		this.usuarioRep = repUsuario;
	}

	public IRepositorioUsuario getUsuario() {
		return usuarioRep;
	}

	public void setFuncionario(IRepositorioUsuario usuarioRep) {
		this.usuarioRep = usuarioRep;
	}

	// metado para salvar usuário passando o objeto Usuario
	public void salvarUsuario(Usuario usuario) {
		usuarioRep.salvarUsuario(usuario);
	}

	// metado para remover usuário passando o objeto Usuario
	public void removerUsuario(Usuario usuario) {
		usuarioRep.removerUsuario(usuario);
	}

	// metado para atualizar usuário passando o objeto Usuario
	public void atualizarUsuario(Usuario usuario) {
		usuarioRep.atualizarUsuario(usuario);
	}

	// metado para salvar usuário passando o objeto Usuario
	public List<Usuario> procurarUsuario(String texto) {
		return usuarioRep.procurarUsuario(texto);
	}

	// metado para listar todos os usuários que estão no BD
	public List<Usuario> listarUsuario() {
		return usuarioRep.listarUsuario();

	}
	
}
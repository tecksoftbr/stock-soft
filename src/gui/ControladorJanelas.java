package gui;

import controlador.CadastroTelefoneFornecedor;
import gui.cadastros.CadastroFornecedor;
import gui.cadastros.CadastroProduto;
import gui.cadastros.CadastroUsuario;

import gui.cadastros.CadastroCheque;
import gui.principais.TelaPrincipal;

public class ControladorJanelas {
	private TelaPrincipal telaPrincipal;
	private CadastroUsuario cadastroUsuario;
	private CadastroCheque cadastroCheque;
	private CadastroFornecedor cadastroFornecedor;
	private CadastroProduto cadastroProduto;
	
	public void AbrirJanelaPrincipal(){
		fecharTodasPrincipais();
		
		if(telaPrincipal == null){
			telaPrincipal = new TelaPrincipal();
			telaPrincipal.setVisible(true);
		}
	}

	public void AbrirJanelaCadastroUsuario() {

		fecharTodosUsuarios();

		if (cadastroUsuario == null) {

			cadastroUsuario = new CadastroUsuario(telaPrincipal, true);

		}

		cadastroUsuario.setVisible(true);
	}

	public void AbrirJanelaCadastroCheque() {
		fechartodosCheque();

		if (cadastroCheque == null) {

			cadastroCheque = new CadastroCheque();
			cadastroCheque.setVisible(true);
		}
	}
	
	public void AbrirJanelaCadastroFornecedor(){
		
		fechartodosFornecedor();
		if (cadastroFornecedor == null){
			cadastroFornecedor = new CadastroFornecedor();
			cadastroFornecedor.setVisible(true);
		}
	}
	
	public void AbrirJanelaCadastroProduto(){
		fechartodosProduto();
		
		if(cadastroProduto == null){
			
			cadastroProduto = new CadastroProduto();
			cadastroProduto.setVisible(true);
		}
	}
//****************************Fechar todos ***********************************	
	public void fecharTodasPrincipais(){
		
		if (telaPrincipal != null){
			telaPrincipal.setVisible(false);
			telaPrincipal.remove(telaPrincipal);
			telaPrincipal = null;
		}
		System.gc();
	}


	public void fecharTodosUsuarios() {

		if (cadastroUsuario != null) {

			cadastroUsuario.toFront();

		}
		
		System.gc();

	}

	public void fechartodosCheque() {

		if (cadastroCheque != null) {
			cadastroCheque.setVisible(false);
			cadastroCheque.remove(cadastroCheque);
			cadastroCheque = null;
		}

		System.gc();
	}


	
	public void fechartodosFornecedor(){
		
		if(cadastroFornecedor != null){
			cadastroFornecedor.setVisible(false);
			cadastroFornecedor.remove(cadastroFornecedor);
			cadastroFornecedor = null;
		}
		System.gc();
	}
	public void fechartodosProduto(){
		
		if(cadastroProduto != null){
			cadastroProduto.setVisible(false);
			cadastroProduto.remove(cadastroProduto);
			cadastroProduto = null;
		}
		System.gc();
	}
}

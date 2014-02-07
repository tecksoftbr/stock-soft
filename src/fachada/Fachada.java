package fachada;

import java.util.List;

import fachada.Fachada;
import controlador.CadastroAgenda;
import controlador.CadastroCheque;
import controlador.CadastroContasPagar;
import controlador.CadastroContasReceber;
import controlador.CadastroEmpresa;
import controlador.CadastroEstoque;
import controlador.CadastroFornecedor;
import controlador.CadastroLembrete;
import controlador.CadastroOrcamento;
import controlador.CadastroPedido;
import controlador.CadastroProduto;
import controlador.CadastroTelefoneFornecedor;
import controlador.CadastroUsuario;
import modelo.Agenda;
import modelo.Cheque;
import modelo.ContasPagar;
import modelo.ContasReceber;
import modelo.Empresa;
import modelo.Estoque;
import modelo.Fornecedor;
import modelo.Lembrete;
import modelo.Orcamento;
import modelo.Pedido;
import modelo.Produto;
import modelo.TelefoneFornecedor;
import modelo.Usuario;
import repositorio.RepositorioAgenda;
import repositorio.RepositorioCheque;
import repositorio.RepositorioContasPagar;
import repositorio.RepositorioContasReceber;
import repositorio.RepositorioEmpresa;
import repositorio.RepositorioEstoque;
import repositorio.RepositorioFornecedor;
import repositorio.RepositorioLembrete;
import repositorio.RepositorioOrcamento;
import repositorio.RepositorioPedido;
import repositorio.RepositorioProduto;
import repositorio.RepositorioTelefoneFornecedor;
import repositorio.RepositorioUsuario;


public class Fachada {
	
	private static Fachada instancia;
	
	private CadastroUsuario usuario;
	private CadastroEmpresa empresa;
	private CadastroFornecedor fornecedor;
	private CadastroProduto produto;
	private CadastroAgenda agenda;
	private CadastroLembrete lembrete;
	private CadastroContasPagar contasPagar;
	private CadastroContasReceber contasReceber;
	private CadastroCheque cheque;
	private CadastroPedido pedido;
	private CadastroOrcamento orcamento;
	private CadastroEstoque estoque;
	private CadastroTelefoneFornecedor telefoneFornecedor;
	
	private void iniciarCadastro() {
		
		RepositorioUsuario ru = new RepositorioUsuario();
		usuario = new CadastroUsuario(ru);
		
		RepositorioEmpresa re = new RepositorioEmpresa();
		empresa = new CadastroEmpresa(re);
		
		RepositorioFornecedor rf = new RepositorioFornecedor();
		fornecedor = new CadastroFornecedor(rf);
		
		RepositorioProduto rp = new RepositorioProduto();
		produto = new CadastroProduto(rp);
		
		RepositorioAgenda ra = new RepositorioAgenda();
		agenda = new CadastroAgenda(ra);
		
		RepositorioLembrete rl = new RepositorioLembrete();
		lembrete = new CadastroLembrete(rl);
		
		RepositorioContasPagar rc = new RepositorioContasPagar();
		contasPagar = new CadastroContasPagar(rc);
		
		RepositorioContasReceber rr = new RepositorioContasReceber();
		contasReceber = new CadastroContasReceber(rr);
		
		RepositorioCheque rh = new RepositorioCheque();
		cheque = new CadastroCheque(rh);
		
		RepositorioPedido rd = new RepositorioPedido();
		pedido = new CadastroPedido(rd);
		
		RepositorioOrcamento ro = new RepositorioOrcamento();
		orcamento = new CadastroOrcamento(ro);
		
		RepositorioEstoque rs = new RepositorioEstoque();
		estoque = new CadastroEstoque(rs);
		
		RepositorioTelefoneFornecedor rtf = new RepositorioTelefoneFornecedor();
		telefoneFornecedor = new CadastroTelefoneFornecedor(rtf);
		
	}
	
	private Fachada(){
		iniciarCadastro();
	}

	public static Fachada getInstance(){

		if (instancia == null){

			instancia = new Fachada();

		}
		return instancia;
		

	}
//************************* Usuario **********************************	
	
	//metado para salvar usuário passando o objeto Usuario
	 public void salvarUsuario(Usuario usuario){
		this.usuario.salvarUsuario(usuario);
	}
	
	//metado para remover usuário passando o objeto Usuario
	public void removerUsuario(Usuario usuario){
		this.usuario.removerUsuario(usuario);
	}
	
	//metado para atualizar usuário passando o objeto Usuario
	public void atualizarUsuario(Usuario usuario){
        this.usuario.atualizarUsuario(usuario);
	}
	
	//metado para salvar usuário passando o objeto Usuario
	public List<Usuario> procurarUsuario(String texto){
		return usuario.procurarUsuario(texto);
	}
	
	//metado para listar todos os usuários que estão no BD
	public List<Usuario> listarUsuario(){
		return usuario.listarUsuario();
		
	}
//********************************************************************
	
	
//********************* Empresa **************************************
	
	public void salvarEmpresa(Empresa empresa) {
		
		this.empresa.salvarEmpresa(empresa);
	}

	public void removerEmpresa(Empresa empresa) {
		this.empresa.removerEmpresa(empresa);
	}

	public void atualizarEmpresa(Empresa empresa) {
		this.empresa.atualizarEmpresa(empresa);
	}

	public List<Empresa> procurarEmpresa(String texto) {
        return this.empresa.procurarEmpresa(texto);		
	}
	
	public List<Empresa> listarEmpresa() {
		return this.empresa.listarEmpresa();
	}
//******************************************************************
	
//********************* Forneceodor ********************************	
	public void salvarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
          this.fornecedor.salvarFornecedor(fornecedor);
	}

	
	public void removerFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
		
		this.fornecedor.removerFornecedor(fornecedor);
		
	}

	
	public void atualizarFornecedor(Fornecedor fornecedor) {
		// TODO Auto-generated method stub
         this.fornecedor.atualizarFornecedor(fornecedor);
	}

	
	public List<Fornecedor> procurarFornecedor(String texto) {
		// TODO Auto-generated method stub
        return this.fornecedor.procurarFornecedor(texto);
	}

	
	public List<Fornecedor> listarFornecedor() {
		// TODO Auto-generated method stub
	

		return this.fornecedor.listarFornecedor();
	}
//**********************************************************************	
	
	
//***************************** Produto ********************************
	
	
	public void salvarProduto(Produto produto) {
		   this.produto.salvarProduto(produto);
		}

		
		public void removerProduto(Produto produto) {
	       this.produto.removerProduto(produto);
		}

		
		public void atualizarProduto(Produto produto) {
		   this.produto.atualizarProduto(produto);
		}


		public List<Produto> procurarProduto(String texto) {
			return this.produto.procurarProduto(texto);
		}

		
		public List<Produto> listarProduto() {
			return this.produto.listarProduto();
		}
//**********************************************************************
		
//***************************** Agenda *********************************
		
		
		public void salvarAgenda(Agenda agenda) {
			this.agenda.salvarAgenda(agenda);	
		}

		public void removerAgenda(Agenda agenda) {
			this.agenda.removerAgenda(agenda);	
		}

		public void atualizarAgenda(Agenda agenda) {
			this.agenda.atualizarAgenda(agenda);	
		}
		
		public List<Agenda> procurarAgenda(String texto) {
			return this.agenda.procurarAgenda(texto);
		}

		public List<Agenda> listarAgenda() {		
			return this.agenda.listarAgenda();
		}
//**********************************************************************
		
		
//******************************** Lembrete ****************************	
		
		public void salvarLembrete(Lembrete lembrete) {
			 this.lembrete.salvarLembrete(lembrete);
				
			}

			
			public void removerLembrete(Lembrete lembrete) {
			 this.lembrete.removerLembrete(lembrete);
				
			}

			
			public void atualizarLembrete(Lembrete lembrete) {
				this.lembrete.atualizarLembrete(lembrete);
				
			}

			
			public List<Lembrete> procurarLembrete(String texto) {
				
				return this.lembrete.procurarLembrete(texto);
			}

			
			public List<Lembrete> listarLembrete() {
				
				return this.lembrete.listarLembrete();
			}

//*********************************************************************
			
//********************************* ContasPagar ***********************
			public void salvarContasPagar(ContasPagar contasPagar) {
				this.contasPagar.salvarContasPagar(contasPagar);	
			}

			
			public void removerContasPagar(ContasPagar contasPagar) {
				this.contasPagar.removerContasPagar(contasPagar);	
			}

			
			public void atualizarContasPagar(ContasPagar contasPagar) {
				this.contasPagar.atualizarContasPagar(contasPagar);
				
			}
			
			public List<ContasPagar> procurarContasPagar(String texto) {
				
				return this.contasPagar.procurarContasPagar(texto);
			}

			
			public List<ContasPagar> listarContasPagar() {
				
				return this.contasPagar.listarContasPagar();
			}

//*********************************************************************
						
//******************************** ContasReceber **********************	
			
			public void salvarContasReceber(ContasReceber contasReceber) {
			     this.contasReceber.salvarContasReceber(contasReceber);
				
			}

			
			public void removerContasReceber(ContasReceber contasReceber) {
				this.contasReceber.removerContasReceber(contasReceber);
				
			}

			
			public void atualizarContasReceber(ContasReceber contasReceber) {
				this.contasReceber.atualizarContasReceber(contasReceber);
				
			}

			
			public List<ContasReceber> procurarcontasReceber(String texto) {
			
				return this.contasReceber.procurarcontasReceber(texto);
			}

			
			public List<ContasReceber> listarContasReceber() {
			
				return this.contasReceber.listarContasReceber();
			}
//*********************************************************************
			
//******************************** Cheque *****************************		
			public void salvarCheque(Cheque cheque) {
			       this.cheque.salvarCheque(cheque);	
				}


				public void removerCheque(Cheque cheque) {
					this.cheque.removerCheque(cheque);	
				}


				public void atualizarCheque(Cheque cheque) {
					this.cheque.atualizarCheque(cheque);	
				}


				public List<Cheque> procurarCheque(String texto) {
					return this.cheque.procurarCheque(texto);
				}

				
				public List<Cheque> listarCheque() {
					return this.cheque.listarCheque();
				}

				
//*********************************************************************
				
//******************************** Pedido *****************************	
				
				public void salvarPedido(Pedido pedido) {
					this.pedido.salvarPedido(pedido);
				}

				
				public void removerPedido(Pedido pedido) {
					this.pedido.removerPedido(pedido);		
				}


				public void atualizarPedido(Pedido pedido) {
					this.pedido.atualizarPedido(pedido);
				}


				public List<Pedido> procurarPedido(String texto) {
					return this.pedido.procurarPedido(texto);
				}

				public List<Pedido> listarPedido() {
					return this.pedido.listarPedido();
				}
				
//******************************************************************
				
//********************* Orcamento ********************************	
				
				public void salvarOrcamento(Orcamento orcamento) {
			        this.orcamento.salvarOrcamento(orcamento);		
				}

				public void removerOrcamento(Orcamento orcamento) {
					this.orcamento.removerOrcamento(orcamento);
				}

				public void atualizarOrcamento(Orcamento orcamento) {
					this.orcamento.atualizarOrcamento(orcamento);	
				}

				public List<Orcamento> procurarOrcamento(String texto) {

					return this.orcamento.procurarOrcamento(texto);
				}

				public List<Orcamento> listarOrcamento() {
					return this.orcamento.listarOrcamento();
				}
				
//******************************************************************
				
//********************* Estoque ************************************
				
				
				public void salvarEstoque(Estoque estoque) {
					this.estoque.salvarEstoque(estoque);	
				}

				
				public void removerEstoque(Estoque estoque) {
					this.estoque.removerEstoque(estoque);	
				}

				
				public void atualizarEstoque(Estoque estoque) {
					this.estoque.atualizarEstoque(estoque);	 	
				}

				
				public List<Estoque> procurarEstoque(String texto) {	
					return this.estoque.procurarEstoque(texto);
				}

				
				public List<Estoque> listarEstoque() {
					return this.estoque.listarEstoque();
				}
				
//******************************************************************
				
//********************* TelefoneFornecedor *************************
			public void salvarTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
				this.telefoneFornecedor.salvarTelefoneFornecedor(telefoneFornecedor);
			}

			public void removerTelefoneFornecedor(TelefoneFornecedor telefoneFornecedor) {
				this.telefoneFornecedor.removerTelefoneFornecedor(telefoneFornecedor);
			}

			public void atualizarTelefoneFornecedor(
					TelefoneFornecedor telefoneFornecedor) {
				this.telefoneFornecedor.atualizarTelefoneFornecedor(telefoneFornecedor);
			}

			public List<TelefoneFornecedor> procurarTelefoneFornecedor(String texto) {
				return this.telefoneFornecedor.procurarTelefoneFornecedor(texto);
			}

			public List<TelefoneFornecedor> listarTelefoneFornecedor() {
				return this.telefoneFornecedor.listarTelefoneFornecedor();
			}
}

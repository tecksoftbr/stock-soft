package repositorio;

import java.util.List;

import modelo.Pedido;

public interface IRepositorioPedido {
	
	//metado para salvar Pedido passando o objeto pedido
	void salvarPedido(Pedido pedido);
	
	//metado para remover Pedido passando o objeto pedido
	void removerPedido(Pedido pedido);
	
	//metado para atualizar Pedido passando o objeto pedido
	void atualizarPedido(Pedido pedido);
	
	//metado para salvar Pedido passando o objeto pedido
	public List<Pedido> procurarPedido(String texto);
	
	//metado para listar todos os pedido que estão no BD
	public List<Pedido> listarPedido();


}

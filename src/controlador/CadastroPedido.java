package controlador;

import java.util.List;

import modelo.Pedido;
import repositorio.IRepositorioPedido;
import repositorio.RepositorioPedido;

public class CadastroPedido {
	
	private IRepositorioPedido pedidoRep;
	
	public CadastroPedido(RepositorioPedido repPedido){
		this.setPedidoRep(repPedido);
		
	}

	public IRepositorioPedido getPedidoRep() {
		return pedidoRep;
	}

	public void setPedidoRep(IRepositorioPedido pedidoRep) {
		this.pedidoRep = pedidoRep;
	}

//*******************************************************************
	public void salvarPedido(Pedido pedido) {
		pedidoRep.salvarPedido(pedido);
	}

	
	public void removerPedido(Pedido pedido) {
		pedidoRep.removerPedido(pedido);		
	}


	public void atualizarPedido(Pedido pedido) {
		pedidoRep.atualizarPedido(pedido);
	}


	public List<Pedido> procurarPedido(String texto) {
		return pedidoRep.procurarPedido(texto);
	}

	public List<Pedido> listarPedido() {
		return pedidoRep.listarPedido();
	}
}

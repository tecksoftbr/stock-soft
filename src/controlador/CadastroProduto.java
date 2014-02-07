package controlador;
import java.util.List;
import modelo.Produto;
import repositorio.IRepositorioProduto;
import repositorio.RepositorioProduto;

public class CadastroProduto {
	
	private IRepositorioProduto produtoRep;
	
	public CadastroProduto(RepositorioProduto repProduto){
		this.setProdutoRep(repProduto);
	}

	public void setProdutoRep(IRepositorioProduto produtoRep) {
		this.produtoRep = produtoRep;
	}

	public IRepositorioProduto getProdutoRep() {
		return produtoRep;
	}

	public void salvarProduto(Produto produto) {
	   produtoRep.salvarProduto(produto);
	}

	
	public void removerProduto(Produto produto) {
       produtoRep.removerProduto(produto);
	}

	
	public void atualizarProduto(Produto produto) {
	   produtoRep.atualizarProduto(produto);
	}


	public List<Produto> procurarProduto(String texto) {
		return produtoRep.procurarProduto(texto);
	}

	
	public List<Produto> listarProduto() {
		return produtoRep.listarProduto();
	}
}

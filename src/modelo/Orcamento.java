package modelo;

import java.util.HashSet;

public class Orcamento {

	private long codigo;
	private String descricao;
	private String complemento;
	private Long volume;
	private double subtotal;
	private double desconto;
	private double total;
	private HashSet<Produto> produtosOrcamento = new HashSet<Produto>();

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public Long getVolume() {
		return volume;
	}

	public void setVolume(Long volume) {
		this.volume = volume;
	}

	public double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(double subtotal) {
		this.subtotal = subtotal;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public HashSet<Produto> getProdutosOrcamento() {
		return produtosOrcamento;
	}

	public void setProdutosOrcamento(HashSet<Produto> produtosOrcamento) {
		this.produtosOrcamento = produtosOrcamento;
	}

}

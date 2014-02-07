package modelo;

import java.util.Date;
import java.util.HashSet;

public class Produto {

	private long codigo;
	private String codigoBarra;
	private String descricao;
	private long quantidade;
	private long quantidadeMinima;
	private double precoCusto;
	private double precoVenda;
	private String unidade;
	private String localizacao;
	private String categoria;
	private String modelo;
	private String cor;
	private String peso;
	private String espessura;
	private Date dataVencimento;
	private String fabricante;
	private String urlImagem;
	private HashSet<Estoque> estoque = new HashSet<Estoque>();
	private HashSet<Fornecedor> fornecedor = new HashSet<Fornecedor>();

	public long getCodigo() {
		return codigo;
	}

	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}

	public String getCodigoBarra() {
		return codigoBarra;
	}

	public void setCodigoBarra(String codigoBarra) {
		this.codigoBarra = codigoBarra;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public long getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(long quantidade) {
		this.quantidade = quantidade;
	}

	public long getQuantidadeMinima() {
		return quantidadeMinima;
	}

	public void setQuantidadeMinima(long quantidadeMinima) {
		this.quantidadeMinima = quantidadeMinima;
	}

	public double getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(double precoCusto) {
		this.precoCusto = precoCusto;
	}

	public double getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(double precoVenda) {
		this.precoVenda = precoVenda;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getPeso() {
		return peso;
	}

	public void setPeso(String peso) {
		this.peso = peso;
	}

	public String getEspessura() {
		return espessura;
	}

	public void setEspessura(String espessura) {
		this.espessura = espessura;
	}

	public Date getDataVencimento() {
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento) {
		this.dataVencimento = dataVencimento;
	}

	public String getFabricante() {
		return fabricante;
	}

	public void setFabricante(String fabricante) {
		this.fabricante = fabricante;
	}

	public void setLocalizacao(String localizacao) {
		this.localizacao = localizacao;
	}

	public String getLocalizacao() {
		return localizacao;
	}

	public String getUrlImagem() {
		return urlImagem;
	}

	public void setUrlImagem(String urlImagem) {
		this.urlImagem = urlImagem;
	}

	public HashSet<Fornecedor> getFornecedor() {
		return fornecedor;
	}

	public void setFornecedor(HashSet<Fornecedor> fornecedor) {
		this.fornecedor = fornecedor;
	}

	public HashSet<Estoque> getEstoque() {
		return estoque;
	}

	public void setEstoque(HashSet<Estoque> estoque) {
		this.estoque = estoque;
	}

}

package trabalhoa3psc;

/**
 * Classe que representa um produto eletrônico na loja.
 */
public class Produto {
    private int id;
    private String nome;
    private String codigo;
    private double preco;
    private int quantidade;
    private TipoProdutoEletronico.Tipo tipo;

    public Produto(String nome, String codigo, double preco, int quantidade, TipoProdutoEletronico.Tipo tipo) {
        this.nome = nome;
        this.codigo = codigo;
        this.preco = preco;
        this.quantidade = quantidade;
        this.tipo = tipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public double getPreco() {
        return preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public TipoProdutoEletronico.Tipo getTipo() {
        return tipo;
    }

    @Override
    public String toString() {
        return "Produto [id=" + id + ", nome=" + nome + ", codigo=" + codigo + ", preco=" + preco
                + ", quantidade=" + quantidade + ", tipo=" + tipo + "]";
    }
}

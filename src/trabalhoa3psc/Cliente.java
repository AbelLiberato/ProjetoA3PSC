package trabalhoa3psc;

/**
 * Classe que representa um cliente na loja.
 */
public class Cliente {
    private String nome;
    private int id;

    public Cliente(String nome, int id) {
        this.nome = nome;
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public int getId() {
        return id;
    }
}

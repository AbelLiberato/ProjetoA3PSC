package trabalhoa3psc;

/**
 * Classe que define os tipos de produtos eletrônicos.
 */
public class TipoProdutoEletronico {
    public enum Tipo {
        ELETRODOMESTICO,
        CELULAR,
        COMPUTADOR,
        NOTEBOOK,
        ACESSORIOS // Adicionado para garantir que todos os tipos sejam válidos
    }

    /**
     * Lista os tipos de produtos eletrônicos disponíveis.
     */
    public static void listarTipos() {
        Tipo[] tipos = Tipo.values();
        for (int i = 0; i < tipos.length; i++) {
            System.out.println(i + ": " + tipos[i]);
        }
    }

    /**
     * Obtém o tipo de produto eletrônico pelo índice.
     * 
     * @param indice Índice do tipo
     * @return Tipo de produto eletrônico
     */
    public static Tipo obterTipoPorIndice(int indice) {
        Tipo[] tipos = Tipo.values();
        if (indice >= 0 && indice < tipos.length) {
            return tipos[indice];
        } else {
            throw new IllegalArgumentException("Índice inválido");
        }
    }
}

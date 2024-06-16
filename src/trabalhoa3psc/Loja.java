package trabalhoa3psc;

import java.sql.*;

/**
 * Classe responsável por gerenciar a loja de produtos eletrônicos.
 */
public class Loja {

    /**
     * Construtor da classe Loja. Inicializa as tabelas do banco de dados.
     */
    public Loja() {
        BancoDados.criarTabelas();
    }

    /**
     * Adiciona um produto ao banco de dados.
     * 
     * @param produto Produto a ser adicionado
     */
    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produtos(nome, codigo, preco, quantidade, tipo) VALUES(?,?,?,?,?)";

        try (Connection conexao = BancoDados.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, produto.getNome());
            pstmt.setString(2, produto.getCodigo());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setInt(4, produto.getQuantidade());
            pstmt.setString(5, produto.getTipo().name());
            pstmt.executeUpdate();
            System.out.println("Produto '" + produto.getNome() + "' adicionado ao banco de dados.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Vende um produto, diminuindo a quantidade em estoque.
     * 
     * @param codigo  Código do produto a ser vendido
     * @param cliente Cliente que está comprando o produto
     */
    public void venderProduto(String codigo, Cliente cliente) {
        String sqlSelecionar = "SELECT id, quantidade FROM produtos WHERE codigo = ?";
        String sqlAtualizar = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try (Connection conexao = BancoDados.conectar();
             PreparedStatement pstmtSelect = conexao.prepareStatement(sqlSelecionar);
             PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlAtualizar)) {

            pstmtSelect.setString(1, codigo);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int quantidade = rs.getInt("quantidade");

                if (quantidade > 0) {
                    pstmtUpdate.setInt(1, quantidade - 1);
                    pstmtUpdate.setInt(2, id);
                    pstmtUpdate.executeUpdate();
                    System.out.println("Produto vendido para o cliente " + cliente.getNome() + ".");
                } else {
                    System.out.println("Produto fora de estoque.");
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Atualiza a quantidade de um produto existente no banco de dados.
     * 
     * @param codigo      Código do produto a ser atualizado
     * @param quantidade  Quantidade adicional a ser adicionada ao estoque
     */
    public void atualizarQuantidadeProduto(String codigo, int quantidade) {
        String sqlSelecionar = "SELECT id, quantidade FROM produtos WHERE codigo = ?";
        String sqlAtualizar = "UPDATE produtos SET quantidade = ? WHERE id = ?";

        try (Connection conexao = BancoDados.conectar();
             PreparedStatement pstmtSelect = conexao.prepareStatement(sqlSelecionar);
             PreparedStatement pstmtUpdate = conexao.prepareStatement(sqlAtualizar)) {

            pstmtSelect.setString(1, codigo);
            ResultSet rs = pstmtSelect.executeQuery();

            if (rs.next()) {
                int id = rs.getInt("id");
                int quantidadeAtual = rs.getInt("quantidade");

                pstmtUpdate.setInt(1, quantidadeAtual + quantidade);
                pstmtUpdate.setInt(2, id);
                pstmtUpdate.executeUpdate();
                System.out.println("Quantidade do produto atualizada com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Exclui um produto do banco de dados.
     * 
     * @param codigo Código do produto a ser excluído
     */
    public void excluirProduto(String codigo) {
        String sql = "DELETE FROM produtos WHERE codigo = ?";

        try (Connection conexao = BancoDados.conectar();
             PreparedStatement pstmt = conexao.prepareStatement(sql)) {
            pstmt.setString(1, codigo);
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Produto excluído com sucesso.");
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Lista todos os produtos disponíveis no banco de dados.
     */
    public void listarProdutos() {
        String sql = "SELECT * FROM produtos";

        try (Connection conexao = BancoDados.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("Listando produtos...");
            while (rs.next()) {
                try {
                    Produto produto = new Produto(
                            rs.getString("nome"),
                            rs.getString("codigo"),
                            rs.getDouble("preco"),
                            rs.getInt("quantidade"),
                            TipoProdutoEletronico.Tipo.valueOf(rs.getString("tipo"))
                    );
                    produto.setId(rs.getInt("id"));
                    System.out.println(produto);
                } catch (IllegalArgumentException e) {
                    System.out.println("Tipo de produto inválido encontrado no banco de dados: " + rs.getString("tipo"));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

package trabalhoa3psc;

import java.util.Scanner;

/**
 * Classe principal do sistema de gerenciamento da loja de produtos eletrônicos.
 */
public class TrabalhoA3PSC {
    public static void main(String[] args) {
        Loja loja = new Loja();
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("=== Menu ===");
            System.out.println("1. Adicionar Produto");
            System.out.println("2. Vender Produto");
            System.out.println("3. Listar Produtos");
            System.out.println("4. Atualizar Quantidade de Produto");
            System.out.println("5. Excluir Produto");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // Consumir a nova linha após o número

            switch (opcao) {
                case 1:
                    // Adicionar Produto
                    System.out.print("Nome do Produto: ");
                    String nomeProduto = scanner.nextLine();
                    System.out.print("Código do Produto: ");
                    String codigoProduto = scanner.nextLine();
                    System.out.print("Preço do Produto: ");
                    double precoProduto = scanner.nextDouble();
                    System.out.print("Quantidade em Estoque: ");
                    int quantidadeProduto = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a quantidade

                    System.out.println("Selecione o tipo do produto:");
                    TipoProdutoEletronico.listarTipos();
                    int tipoProdutoIndice = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após o índice
                    TipoProdutoEletronico.Tipo tipoProduto;
                    try {
                        tipoProduto = TipoProdutoEletronico.obterTipoPorIndice(tipoProdutoIndice);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Índice inválido! Produto não adicionado.");
                        break;
                    }

                    Produto produto = new Produto(nomeProduto, codigoProduto, precoProduto, quantidadeProduto, tipoProduto);
                    loja.adicionarProduto(produto);
                    System.out.println("Produto adicionado com sucesso.");
                    break;
                case 2:
                    // Vender Produto
                    System.out.print("Código do Produto a ser vendido: ");
                    String codigoVenda = scanner.nextLine();
                    System.out.print("Nome do Cliente: ");
                    String nomeCliente = scanner.nextLine();
                    Cliente cliente = new Cliente(nomeCliente, 1);
                    loja.venderProduto(codigoVenda, cliente);
                    break;
                case 3:
                    // Listar Produtos
                    loja.listarProdutos();
                    break;
                case 4:
                    // Atualizar Quantidade de Produto
                    System.out.print("Código do Produto a ser atualizado: ");
                    String codigoAtualizar = scanner.nextLine();
                    System.out.print("Quantidade a ser adicionada: ");
                    int quantidadeAdicionar = scanner.nextInt();
                    scanner.nextLine(); // Consumir a nova linha após a quantidade
                    loja.atualizarQuantidadeProduto(codigoAtualizar, quantidadeAdicionar);
                    break;
                case 5:
                    // Excluir Produto
                    System.out.print("Código do Produto a ser excluído: ");
                    String codigoExcluir = scanner.nextLine();
                    loja.excluirProduto(codigoExcluir);
                    break;
                case 6:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 6);

        scanner.close();
    }
}

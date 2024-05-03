import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digite a ordem da árvore B:");
        int order = scanner.nextInt();
        ArvoreB arvoreB = new ArvoreB(order);
        // 10, 20, 50, 80, 30, 40, 70, 60, 90, 200, 150, 130, 120, 180, 140, 100, 110, 15, 25, 75, 85, 115, 190
        int op;
        do {
            System.out.println("\nMenu:");
            System.out.println("1. Inserir chave");
            System.out.println("2. Exibir chaves por nível");
            System.out.println("3. Exibir chaves em pré-ordem");
            System.out.println("4. Encontrar maior chave");
            System.out.println("5. Encontrar menor chave");
            System.out.println("6. Remover chave");
            System.out.println("7. Sair");
            System.out.println("Escolha uma opção:");
            op = scanner.nextInt();
            switch (op) {
                case 1:
                    System.out.println("Digite a chave a ser inserida:");
                    int insert = scanner.nextInt();
                    // Inserir a chave na árvore
                    arvoreB.insert(insert);
                    break;
                case 2:
                    // Exibir as chaves da árvore por nível
                    arvoreB.displayByLevel();
                    break;
                case 3:
                    // Exibir as chaves da árvore em pré-ordem
                    arvoreB.displayPreOrder();
                    break;
                case 4:
                    // Encontrar a maior chave na árvore
                    Retorno maior = arvoreB.findMaxKey();
                    if (maior != null) {
                        System.out.println("Maior chave encontrada: " + maior.getFilhoDir().getKeys(maior.getInfo()));
                    } else {
                        System.out.println("Árvore vazia!");
                    }
                    break;
                case 5:
                    // Encontrar a menor chave na árvore
                    Retorno menor = arvoreB.findMinKey();
                    if (menor != null) {
                        System.out.println("Menor chave encontrada: " + menor.getFilhoDir().getKeys(menor.getInfo()));
                    } else {
                        System.out.println("Árvore vazia!");
                    }
                    break;
                case 6:
                    System.out.println("Digite a chave a ser removida:");
                    int remove = scanner.nextInt();
                    break;
                case 7:
                    System.out.println("Encerrando o programa...");
                    break;
                default:
                    System.out.println("Opção inválida!");
            }
        } while (op != 7);

        scanner.close();
    }
}

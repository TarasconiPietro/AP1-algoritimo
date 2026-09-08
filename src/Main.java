import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] jogadores = {"Chico", "João", "Marcela", "Bruno", "Beatriz"};

        int[][] tabelaPontos = {
                {80, 90, 30, 50},
                {70, 60, 70, 90},
                {30, 70, 60, 40},
                {60, 30, 50, 60},
                {60, 50, 70, 80}
        };

        int[] ranking = new int[jogadores.length];

        entradaDados(scanner, jogadores, tabelaPontos);
        calcularPontuacaoTotal(tabelaPontos, ranking);

        int opcao;
        do {
            System.out.println("===== MENU =====");
            System.out.println("1 - Exibir ranking (Merge Sort)");
            System.out.println("2 - Exibir ranking (Quick Sort)");
            System.out.println("3 - Consultar jogador");
            System.out.println("4 - Soma das pontuações de uma rodada");
            System.out.println("0 - Sair");
            opcao = lerInteiro(scanner, "Escolha uma opção: ");

            if (opcao == 1) {
                exibirRanking(jogadores, ranking, 1);
            } else if (opcao == 2) {
                exibirRanking(jogadores, ranking, 2);
            } else if (opcao == 3) {
                System.out.print("Informe o nome do jogador: ");
                String nome = scanner.nextLine().trim();
                consultarJogador(jogadores, tabelaPontos, nome);
            } else if (opcao == 4) {
                int rodada = lerInteiro(scanner, "Informe o número da rodada (1 a 4): ");
                int soma = somaPontuacoesRodada(tabelaPontos, rodada);
                if (soma >= 0) {
                    System.out.println("Soma da rodada " + rodada + ": " + soma + " pontos.");
                }
            } else if (opcao != 0) {
                System.out.println("Opção inválida.");
            }
            System.out.println();
        } while (opcao != 0);

        System.out.println("Encerrando o programa...");
        scanner.close();
    }

    public static void entradaDados(Scanner scanner, String[] jogadores, int[][] tabelaPontos) {
        System.out.println("===== ENTRADA DE DADOS =====");
        System.out.println("1 - Usar dados padrão");
        System.out.println("2 - Cadastrar manualmente");
        System.out.println("3 - Gerar pontuações aleatórias");
        int opcao = lerInteiro(scanner, "Escolha uma opção: ");

        if (opcao == 1) {
            carregarDadosPadrao(jogadores, tabelaPontos);
            System.out.println("Dados padrão carregados.");
        } else if (opcao == 2) {
            cadastrarManualmente(scanner, jogadores, tabelaPontos);
        } else if (opcao == 3) {
            System.out.println("Mantendo os nomes já cadastrados. Gerando pontuações aleatórias...");
            gerarPontuacoesAleatorias(tabelaPontos, tabelaPontos[0].length);
            System.out.println("Pontuações aleatórias geradas.");
        } else {
            System.out.println("Opção inválida. Carregando dados padrão...");
            carregarDadosPadrao(jogadores, tabelaPontos);
        }
    }

    public static void carregarDadosPadrao(String[] jogadores, int[][] tabelaPontos) {
        jogadores[0] = "Chico";
        jogadores[1] = "João";
        jogadores[2] = "Marcela";
        jogadores[3] = "Bruno";
        jogadores[4] = "Beatriz";

        tabelaPontos[0][0] = 80; tabelaPontos[0][1] = 90; tabelaPontos[0][2] = 30; tabelaPontos[0][3] = 50;
        tabelaPontos[1][0] = 70; tabelaPontos[1][1] = 60; tabelaPontos[1][2] = 70; tabelaPontos[1][3] = 90;
        tabelaPontos[2][0] = 30; tabelaPontos[2][1] = 70; tabelaPontos[2][2] = 60; tabelaPontos[2][3] = 40;
        tabelaPontos[3][0] = 60; tabelaPontos[3][1] = 30; tabelaPontos[3][2] = 50; tabelaPontos[3][3] = 60;
        tabelaPontos[4][0] = 60; tabelaPontos[4][1] = 50; tabelaPontos[4][2] = 70; tabelaPontos[4][3] = 80;
    }

    public static void cadastrarManualmente(Scanner scanner, String[] jogadores, int[][] tabelaPontos) {
        for (int i = 0; i < jogadores.length; i++) {
            System.out.print("Nome do jogador " + (i + 1) + ": ");
            jogadores[i] = scanner.nextLine().trim();
            for (int r = 0; r < tabelaPontos[i].length; r++) {
                tabelaPontos[i][r] = lerInteiro(scanner, "Pontuação na rodada " + (r + 1) + ": ");
            }
        }
        System.out.println("Cadastro concluído.");
    }

    public static void gerarPontuacoesAleatorias(int[][] pontuacoes, int rodadas) {
        Random random = new Random();
        for (int i = 0; i < pontuacoes.length; i++) {
            for (int r = 0; r < rodadas; r++) {
                pontuacoes[i][r] = random.nextInt(101);
            }
        }
    }

    public static void calcularPontuacaoTotal(int[][] tabelaPontos, int[] ranking) {
        for (int i = 0; i < tabelaPontos.length; i++) {
            int soma = 0;
            for (int j = 0; j < tabelaPontos[i].length; j++) {
                soma += tabelaPontos[i][j];
            }
            ranking[i] = soma;
        }
    }

    public static void consultarJogador(String[] jogadores, int[][] tabelaPontos, String nome) {
        int indice = -1;
        for (int i = 0; i < jogadores.length; i++) {
            if (jogadores[i].equalsIgnoreCase(nome)) {
                indice = i;
                break;
            }
        }

        if (indice < 0) {
            System.out.println("Jogador não encontrado.");
            return;
        }

        int[] ranking = new int[jogadores.length];
        calcularPontuacaoTotal(tabelaPontos, ranking);

        int total = ranking[indice];
        int posicao = calcularPosicao(jogadores, ranking, indice);

        System.out.println(jogadores[indice] + " possui " + total
                + " pontos e está em " + posicao + "º lugar no ranking.");
    }

    public static int somaPontuacoesRodada(int[][] tabelaPontos, int numeroRodada) {
        if (numeroRodada < 1 || numeroRodada > tabelaPontos[0].length) {
            System.out.println("Rodada inválida. Informe um valor entre 1 e "
                    + tabelaPontos[0].length + ".");
            return -1;
        }

        int coluna = numeroRodada - 1;
        int soma = 0;
        for (int i = 0; i < tabelaPontos.length; i++) {
            soma += tabelaPontos[i][coluna];
        }
        return soma;
    }

    private static void exibirRanking(String[] jogadores, int[] ranking, int algoritmo) {
        String[] nomesOrdenados = copiarNomes(jogadores);
        int[] totaisOrdenados = copiarInteiros(ranking);

        if (algoritmo == 1) {
            ordenarRankingMergeSort(nomesOrdenados, totaisOrdenados);
            System.out.println("(Ordenado com Merge Sort)");
        } else {
            ordenarRankingQuickSort(nomesOrdenados, totaisOrdenados);
            System.out.println("(Ordenado com Quick Sort)");
        }

        System.out.println("===== RANKING =====");
        for (int i = 0; i < nomesOrdenados.length; i++) {
            System.out.println((i + 1) + "º " + nomesOrdenados[i] + " - " + totaisOrdenados[i] + " pontos");
        }
    }

    public static void ordenarRankingMergeSort(String[] jogadores, int[] ranking) {
        String[] nomesOriginais = copiarNomes(jogadores);
        int[] totaisOriginais = copiarInteiros(ranking);

        Ordenacao.mergeSort(ranking, 0, ranking.length - 1);
        reassociarNomes(jogadores, nomesOriginais, totaisOriginais, ranking);
        inverterRanking(jogadores, ranking);
    }

    public static void ordenarRankingQuickSort(String[] jogadores, int[] ranking) {
        String[] nomesOriginais = copiarNomes(jogadores);
        int[] totaisOriginais = copiarInteiros(ranking);

        Ordenacao.quickSort(ranking, 0, ranking.length - 1);
        reassociarNomes(jogadores, nomesOriginais, totaisOriginais, ranking);
        inverterRanking(jogadores, ranking);
    }

    private static int calcularPosicao(String[] jogadores, int[] ranking, int indiceAlvo) {
        String[] nomesOrdenados = copiarNomes(jogadores);
        int[] totaisOrdenados = copiarInteiros(ranking);
        ordenarRankingMergeSort(nomesOrdenados, totaisOrdenados);

        for (int i = 0; i < nomesOrdenados.length; i++) {
            if (nomesOrdenados[i].equals(jogadores[indiceAlvo])) {
                return i + 1;
            }
        }
        return -1;
    }

    private static void reassociarNomes(String[] destino, String[] nomesOriginais,
                                        int[] totaisOriginais, int[] totaisOrdenados) {
        boolean[] usado = new boolean[nomesOriginais.length];
        for (int i = 0; i < totaisOrdenados.length; i++) {
            for (int j = 0; j < totaisOriginais.length; j++) {
                if (!usado[j] && totaisOriginais[j] == totaisOrdenados[i]) {
                    destino[i] = nomesOriginais[j];
                    usado[j] = true;
                    break;
                }
            }
        }
    }

    private static void inverterRanking(String[] jogadores, int[] ranking) {
        int i = 0;
        int j = ranking.length - 1;
        while (i < j) {
            String tempNome = jogadores[i];
            jogadores[i] = jogadores[j];
            jogadores[j] = tempNome;

            int tempPontos = ranking[i];
            ranking[i] = ranking[j];
            ranking[j] = tempPontos;

            i++;
            j--;
        }
    }

    private static String[] copiarNomes(String[] original) {
        String[] copia = new String[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    private static int[] copiarInteiros(int[] original) {
        int[] copia = new int[original.length];
        for (int i = 0; i < original.length; i++) {
            copia[i] = original[i];
        }
        return copia;
    }

    private static int lerInteiro(Scanner scanner, String mensagem) {
        while (true) {
            System.out.print(mensagem);
            String entrada = scanner.nextLine().trim();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido. Digite um número inteiro.");
            }
        }
    }
}

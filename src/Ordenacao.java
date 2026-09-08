public class Ordenacao {
    public static void mergeSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            // Calcula o ponto médio
            int meio = (inicio + fim) / 2;

            // Divisões recursivas
            mergeSort(vetor, inicio, meio);
            mergeSort(vetor, meio + 1, fim);

            // Intercalação dos sub-arrays
            merge(vetor, inicio, meio, fim);
        }
    }
    public static void merge(int[] vetor, int ini, int meio, int fim) {
        // Passo 1: Determinar tamanhos dos subarrays
        int n1 = meio - ini + 1;
        int n2 = fim - meio;
        // Passo 2: Criar arrays temporários
        int[] esquerda = new int[n1];
        int[] direita = new int[n2];
        // Passo 3: Copiar dados para os temporários
        for (int i = 0; i < n1; i++)
            esquerda[i] = vetor[ini + i];
        for (int j = 0; j < n2; j++)
            direita[j] = vetor[meio + 1 + j];
        // Passo 4: Mesclar os arrays de volta no original
        int i = 0, j = 0;
        int k = ini;
        while (i < n1 && j < n2) {
            if (esquerda[i] <= direita[j]) {
                vetor[k] = esquerda[i];
                i++;
            } else {
                vetor[k] = direita[j];
                j++;
            }
            k++;
        }
        // Passo 5: Copiar elementos restantes de esquerda[]
        while (i < n1) {
            vetor[k] = esquerda[i];
            i++;
            k++;
        }
        // Passo 6: Copiar elementos restantes de direita[]
        while (j < n2) {
            vetor[k] = direita[j];
            j++;
            k++;
        }
    }
    public static void quickSort(int[] vetor, int inicio, int fim) {
        if (inicio < fim) {
            // Particiona o vetor e obtém o pivô
            int indicePivo = particionar(vetor, inicio, fim);
            // Ordena recursivamente a sublista esquerda
            quickSort(vetor, inicio, indicePivo - 1);
            quickSort(vetor, indicePivo + 1, fim);
        }
    }
    private static int particionar(int[] vetor, int inicio, int fim) {
        int pivo = vetor[fim]; // Escolhe o último como pivô
        int i = (inicio - 1); // Índice do menor elemento

        for (int j = inicio; j < fim; j++) {
            if (vetor[j] <= pivo) { // Se atual for <= ao pivô
                i++;
                // Realiza a troca dos elementos
                int temp = vetor[i];
                vetor[i] = vetor[j];
                vetor[j] = temp;
            }
        }
        // Troca vetor[i+1] com o pivô (vetor[fim])
        int temp = vetor[i + 1];
        vetor[i + 1] = vetor[fim];
        vetor[fim] = temp;
        // Retorna o índice do pivô
        return i + 1;
    }
}

package Etapa2;

import java.util.*;

class PrincipalCandidatos {
    public static void main(String[] args) {
        Random random = new Random();

        // Gera um tamanho aleatório para o array de candidatos
        int tamanhoArray = random.nextInt(100) + 1;
        Candidato[] candidatos = new Candidato[tamanhoArray];

        // Preenche o array de candidatos com informações aleatórias
        for (int i = 0; i < tamanhoArray; i++) {
            String nome = gerarNomeAleatorio();
            String partido = gerarPartidoAleatorio();
            int intencoesVotos = random.nextInt(1000) + 1;
            candidatos[i] = new Candidato(nome, partido, intencoesVotos);
        }

        ordenaCandidatosPorNome(candidatos);

        System.out.println("Candidatos ordenados por nome:");
        imprimirCandidatos(candidatos);

        ordenaCandidatosPorVotos(candidatos);

        System.out.println("Candidatos ordenados por intenções de voto:");
        imprimirCandidatos(candidatos);

        ordenaCandidatosPorPartido(candidatos);

        System.out.println("Candidatos ordenados por partido:");
        imprimirCandidatos(candidatos);

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Digite o nome de um candidato para pesquisa: ");
            String nomePesquisa = scanner.nextLine();

            ordenaCandidatosPorNome(candidatos); // Ordena os candidatos por nome antes da pesquisa
            int posicao = pesquisaBinariaCandidatos(candidatos, nomePesquisa);

            if (posicao != -1) {
                System.out.println("Candidato encontrado na posição " + posicao + ":");
                System.out.println(candidatos[posicao]);
            } else {
                System.out.println("Candidato não encontrado.");
            }

            System.out.print("Deseja fazer uma nova busca? (S - Sim, N - Não) ");
            String opcao = scanner.nextLine();

            if (!opcao.equalsIgnoreCase("S")) {
                break;
            }
        } while (true);
    }

    private static void ordenaCandidatosPorNome(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0 && candidatos[j].getNome().compareTo(atual.getNome()) > 0) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }

    private static void ordenaCandidatosPorVotos(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0 && candidatos[j].getIntencoesVotos() < atual.getIntencoesVotos()) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }

    private static void ordenaCandidatosPorPartido(Candidato[] candidatos) {
        for (int i = 1; i < candidatos.length; i++) {
            Candidato atual = candidatos[i];
            int j = i - 1;

            while (j >= 0 && candidatos[j].getPartido().compareTo(atual.getPartido()) > 0) {
                candidatos[j + 1] = candidatos[j];
                j--;
            }

            candidatos[j + 1] = atual;
        }
    }


    private static int pesquisaBinariaCandidatos(Candidato[] candidatos, String nome) {
        int inicio = 0;
        int fim = candidatos.length - 1;
        int posicao = -1;

        while (inicio <= fim) {
            int meio = (inicio + fim) / 2;

            if (candidatos[meio].getNome().equalsIgnoreCase(nome)) {
                posicao = meio;
                break;
            } else if (candidatos[meio].getNome().compareToIgnoreCase(nome) < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }

        return posicao;
    }


    private static void imprimirCandidatos(Candidato[] candidatos) {
        for (Candidato candidato : candidatos) {
            System.out.println(candidato);
        }
        System.out.println();
    }

    // Gera um nome aleatório
    private static String gerarNomeAleatorio() {
        String[] nomes = {"Nicolas", "Brenda", "Gabriela", "Alana", "Guilherme", "Maria", "Gabriel", "Pedro"};
        Random random = new Random();
        return nomes[random.nextInt(nomes.length)];
    }

    // Gera um partido aleatório
    private static String gerarPartidoAleatorio() {
        String[] partidos = {"Partido Noxus", "Partido Demacia", "Partido Ionia", "Partido Frelijord"};
        Random random = new Random();
        return partidos[random.nextInt(partidos.length)];
    }
}




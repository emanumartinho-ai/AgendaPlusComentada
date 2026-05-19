// Imports das classes usadas neste ficheiro.
import java.util.ArrayList;
import java.util.Scanner;

// A classe Menu cuida da interacao com o utilizador.
// Ela mostra textos na consola, le respostas e chama metodos da Agenda.
public class Menu {
    // "static final" cria uma constante.
    // static: pertence a classe Menu.
    // final: depois de receber valor, nao pode mudar.
    // Por convencao, constantes usam letras maiusculas e underscores.
    private static final String FICHEIRO_CONTACTOS = "contactos.txt";

    // O Menu tem uma Agenda.
    // Isto e composicao: um objeto Menu usa outro objeto Agenda.
    private Agenda agenda;

    // Scanner serve para ler texto escrito pelo utilizador na consola.
    private Scanner scanner;

    // Construtor do Menu.
    public Menu() {
        // Criamos uma Agenda vazia.
        agenda = new Agenda();

        // System.in representa a entrada padrao, neste caso o teclado/consola.
        scanner = new Scanner(System.in);

        // Carregamos os contactos guardados no ficheiro, se o ficheiro existir.
        agenda.carregarDeFicheiro(FICHEIRO_CONTACTOS);
    }

    // Metodo que inicia o ciclo principal do menu.
    public void iniciar() {
        // "int" guarda numeros inteiros.
        int opcao;

        // do/while executa o bloco primeiro e so depois testa a condicao.
        // Isto garante que o menu aparece pelo menos uma vez.
        do {
            mostrarMenu();
            opcao = lerInteiro("Escolha uma opcao: ");
            executarOpcao(opcao);
        // "!=" significa diferente.
        // O ciclo repete enquanto opcao for diferente de 0.
        } while (opcao != 0);
    }

    // Mostra o menu na consola.
    private void mostrarMenu() {
        // println imprime texto e muda de linha.
        // println() sem texto imprime apenas uma linha vazia.
        System.out.println();
        System.out.println("Agenda de Contactos");
        System.out.println("1. Adicionar contacto");
        System.out.println("2. Listar contactos");
        System.out.println("3. Procurar contacto por nome");
        System.out.println("4. Procurar por telefone ou email");
        System.out.println("5. Remover contacto");
        System.out.println("6. Ordenar alfabeticamente");
        System.out.println("7. Editar contacto");
        System.out.println("8. Alternar favorito");
        System.out.println("9. Listar favoritos");
        System.out.println("10. Listar por tipo");
        System.out.println("11. Mostrar estatisticas");
        System.out.println("0. Sair");
    }

    // Recebe a opcao escolhida e decide que metodo executar.
    private void executarOpcao(int opcao) {
        // switch compara o valor de "opcao" com varios casos possiveis.
        switch (opcao) {
            case 1:
                adicionarContacto();
                // break sai do switch. Sem break, o Java continuaria para o proximo case.
                break;
            case 2:
                listarContactos(agenda.getContactos());
                break;
            case 3:
                procurarPorNome();
                break;
            case 4:
                procurarPorTelefoneOuEmail();
                break;
            case 5:
                removerContacto();
                break;
            case 6:
                agenda.ordenarAlfabeticamente();
                agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
                System.out.println("Contactos ordenados.");
                break;
            case 7:
                editarContacto();
                break;
            case 8:
                alternarFavorito();
                break;
            case 9:
                listarContactos(agenda.listarFavoritos());
                break;
            case 10:
                listarPorTipo();
                break;
            case 11:
                mostrarEstatisticas();
                break;
            case 0:
                agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
                System.out.println("Contactos guardados. Ate breve!");
                break;
            default:
                // default executa quando nenhum case combina com a opcao.
                System.out.println("Opcao invalida.");
        }
    }

    // Pede dados ao utilizador e tenta adicionar um novo contacto.
    private void adicionarContacto() {
        String nome = lerTextoObrigatorio("Nome: ");
        String telefone = lerTelefone();
        String email = lerEmail();
        TipoContacto tipo = lerTipoContacto();
        boolean favorito = lerSimOuNao("Contacto favorito? (s/n): ");

        // Criamos um novo objeto Contacto com os dados recolhidos.
        Contacto contacto = new Contacto(nome, telefone, email, tipo, favorito);

        // A Agenda devolve true se conseguiu adicionar, false se havia duplicado.
        boolean adicionado = agenda.adicionarContacto(contacto);

        if (adicionado) {
            agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
            System.out.println("Contacto adicionado com sucesso.");
        } else {
            // else executa quando a condicao do if e false.
            System.out.println("Ja existe um contacto com esse telefone ou email.");
        }
    }

    // Pesquisa contactos por nome.
    private void procurarPorNome() {
        String nome = lerTextoObrigatorio("Nome a procurar: ");

        // ArrayList<Contacto> significa lista de objetos Contacto.
        ArrayList<Contacto> resultados = agenda.procurarPorNome(nome);

        listarContactos(resultados);
    }

    // Pesquisa contactos por telefone ou email.
    private void procurarPorTelefoneOuEmail() {
        String pesquisa = lerTextoObrigatorio("Telefone ou email a procurar: ");
        ArrayList<Contacto> resultados = agenda.procurarPorTelefoneOuEmail(pesquisa);
        listarContactos(resultados);
    }

    // Edita um contacto existente.
    private void editarContacto() {
        String nomeAntigo = lerTextoObrigatorio("Nome do contacto a editar: ");
        String novoNome = lerTextoObrigatorio("Novo nome: ");
        String novoTelefone = lerTelefone();
        String novoEmail = lerEmail();
        TipoContacto novoTipo = lerTipoContacto();

        boolean editado = agenda.editarContacto(nomeAntigo, novoNome, novoTelefone, novoEmail, novoTipo);

        if (editado) {
            agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
            System.out.println("Contacto editado.");
        } else {
            System.out.println("Contacto nao encontrado ou dados duplicados.");
        }
    }

    // Alterna o estado de favorito.
    private void alternarFavorito() {
        String nome = lerTextoObrigatorio("Nome do contacto: ");
        boolean alterado = agenda.alternarFavorito(nome);

        if (alterado) {
            agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
            System.out.println("Favorito alterado.");
        } else {
            System.out.println("Contacto nao encontrado.");
        }
    }

    // Remove um contacto pelo nome.
    private void removerContacto() {
        String nome = lerTextoObrigatorio("Nome do contacto a remover: ");
        boolean removido = agenda.removerPorNome(nome);

        if (removido) {
            agenda.guardarEmFicheiro(FICHEIRO_CONTACTOS);
            System.out.println("Contacto removido.");
        } else {
            System.out.println("Contacto nao encontrado.");
        }
    }

    // Lista contactos de um tipo escolhido pelo utilizador.
    private void listarPorTipo() {
        TipoContacto tipo = lerTipoContacto();
        listarContactos(agenda.listarPorTipo(tipo));
    }

    // Mostra estatisticas simples da agenda.
    private void mostrarEstatisticas() {
        // O operador "+" junta texto com valores.
        System.out.println("Total de contactos: " + agenda.contarContactos());
        System.out.println("Favoritos: " + agenda.contarFavoritos());

        // Percorre todos os valores possiveis do enum TipoContacto.
        for (TipoContacto tipo : TipoContacto.values()) {
            System.out.println(tipo.getDescricao() + ": " + agenda.contarPorTipo(tipo));
        }
    }

    // Mostra uma lista de contactos recebida como parametro.
    private void listarContactos(ArrayList<Contacto> contactos) {
        // isEmpty() devolve true se a lista nao tem elementos.
        if (contactos.isEmpty()) {
            System.out.println("Nao existem contactos para mostrar.");

            // return termina o metodo aqui.
            // Como o metodo e void, nao devolvemos nenhum valor.
            return;
        }

        // Para cada contacto dentro da lista, imprime o contacto.
        for (Contacto contacto : contactos) {
            // Quando imprimimos um objeto, Java chama automaticamente o toString() desse objeto.
            System.out.println(contacto);
        }
    }

    // Le texto obrigatorio, ou seja, nao aceita texto vazio.
    private String lerTextoObrigatorio(String mensagem) {
        String texto;

        do {
            // print imprime sem mudar de linha.
            System.out.print(mensagem);

            // nextLine() le uma linha escrita pelo utilizador.
            // trim() remove espacos antes e depois.
            texto = scanner.nextLine().trim();

            // isEmpty() devolve true se o texto estiver vazio.
            if (texto.isEmpty()) {
                System.out.println("Este campo nao pode ficar vazio.");
            }
        // Repete enquanto texto estiver vazio.
        } while (texto.isEmpty());

        return texto;
    }

    // Le e valida telefone com 9 digitos.
    private String lerTelefone() {
        String telefone;

        do {
            telefone = lerTextoObrigatorio("Telefone: ");

            // matches("\\d{9}") verifica se o texto tem exatamente 9 digitos.
            // "\\d" representa um digito; "{9}" significa exatamente nove vezes.
            // "!" nega o resultado. Entao, se NAO tiver 9 digitos, mostramos erro.
            if (!telefone.matches("\\d{9}")) {
                System.out.println("O telefone deve ter 9 digitos.");
            }
        } while (!telefone.matches("\\d{9}"));

        return telefone;
    }

    // Le e valida email de forma simples.
    private String lerEmail() {
        String email;

        do {
            email = lerTextoObrigatorio("Email: ");

            // contains("@") verifica se o texto contem @.
            // contains(".") verifica se o texto contem ponto.
            // "||" significa OU.
            // A condicao le-se:
            // se o email NAO contem @ OU NAO contem ponto, entao e invalido.
            if (!email.contains("@") || !email.contains(".")) {
                System.out.println("Email invalido.");
            }
        } while (!email.contains("@") || !email.contains("."));

        return email;
    }

    // Le o tipo de contacto escolhido pelo utilizador.
    private TipoContacto lerTipoContacto() {
        System.out.println("Tipo de contacto:");

        // for tradicional com contador.
        // Comeca em 0 porque arrays em Java comecam no indice 0.
        for (int i = 0; i < TipoContacto.values().length; i++) {
            // TipoContacto.values()[i] vai buscar o tipo na posicao i.
            TipoContacto tipo = TipoContacto.values()[i];

            // Mostramos i + 1 para o utilizador ver opcoes a comecar em 1.
            System.out.println((i + 1) + ". " + tipo.getDescricao());
        }

        int opcao;

        do {
            opcao = lerInteiro("Escolha o tipo: ");

            // "<" significa menor que.
            // ">" significa maior que.
            // "||" significa OU.
            // Se opcao for menor que 1 OU maior que o numero de tipos, e invalida.
            if (opcao < 1 || opcao > TipoContacto.values().length) {
                System.out.println("Tipo invalido.");
            }
        } while (opcao < 1 || opcao > TipoContacto.values().length);

        // Como o utilizador escolhe 1, 2, 3..., mas arrays comecam em 0,
        // usamos opcao - 1 para aceder ao indice correto.
        return TipoContacto.values()[opcao - 1];
    }

    // Le uma resposta "s" ou "n" e transforma em boolean.
    private boolean lerSimOuNao(String mensagem) {
        String resposta;

        do {
            System.out.print(mensagem);
            resposta = scanner.nextLine().trim().toLowerCase();

            // Para comparar Strings, usamos equals.
            // "!resposta.equals("s")" significa: resposta NAO e igual a "s".
            // "&&" significa E logico.
            // A condicao le-se:
            // se resposta NAO e "s" E resposta NAO e "n", entao e invalida.
            if (!resposta.equals("s") && !resposta.equals("n")) {
                System.out.println("Responda com s ou n.");
            }
        } while (!resposta.equals("s") && !resposta.equals("n"));

        // equals("s") devolve true se a resposta for "s".
        // Se for "n", devolve false.
        return resposta.equals("s");
    }

    // Le um numero inteiro com protecao contra texto invalido.
    private int lerInteiro(String mensagem) {
        // while (true) cria um ciclo infinito.
        // Ele so termina quando executarmos return dentro do try.
        while (true) {
            System.out.print(mensagem);

            try {
                // Integer.parseInt(...) tenta converter texto para int.
                // Se o utilizador escrever "abc", isto causa NumberFormatException.
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Este bloco executa se a conversao para int falhar.
                System.out.println("Introduza um numero valido.");
            }
        }
    }
}

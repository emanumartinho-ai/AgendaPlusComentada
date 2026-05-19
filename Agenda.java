// Imports dizem ao Java que queremos usar classes que existem noutras bibliotecas.
// Sem estes imports, teriamos de escrever nomes completos como java.util.ArrayList.
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Comparator;

// A classe Agenda representa uma colecao de contactos.
// Ela nao representa uma pessoa; representa o objeto que gere muitos contactos.
public class Agenda {
    // "ArrayList<Contacto>" significa uma lista dinamica que guarda objetos Contacto.
    // Os sinais < > indicam generics: dizem ao Java que tipo de objeto a lista aceita.
    private ArrayList<Contacto> contactos;

    // Construtor: chamado quando alguem escreve "new Agenda()".
    public Agenda() {
        // new ArrayList<>() cria uma lista vazia.
        // O diamante "<>" permite ao Java inferir que e uma lista de Contacto.
        contactos = new ArrayList<>();
    }P

    // Metodo publico para adicionar um contacto.
    // Devolve boolean: true se adicionou, false se nao adicionou.
    public boolean adicionarContacto(Contacto contacto) {
        // if executa o bloco entre { } se a condicao for verdadeira.
        // Aqui perguntamos se ja existe o mesmo telefone ou email.
        if (existeTelefoneOuEmail(contacto.getTelefone(), contacto.getEmail(), null)) {
            // return termina o metodo imediatamente e devolve false.
            return false;
        }

        // add(...) e um metodo do ArrayList que acrescenta um elemento a lista.
        contactos.add(contacto);

        // Chamamos outro metodo da mesma classe para manter os contactos ordenados.
        ordenarAlfabeticamente();

        // Se chegamos aqui, o contacto foi adicionado com sucesso.
        return true;
    }

    // Devolve uma copia da lista de contactos.
    public ArrayList<Contacto> getContactos() {
        // new ArrayList<>(contactos) cria uma nova lista com os mesmos contactos.
        // Isto protege a lista original contra alteracoes diretas feitas fora da Agenda.
        return new ArrayList<>(contactos);
    }

    // Procura contactos cujo nome contem o texto recebido.
    public ArrayList<Contacto> procurarPorNome(String nome) {
        // Criamos uma lista vazia para guardar os contactos encontrados.
        ArrayList<Contacto> resultados = new ArrayList<>();

        // for-each:
        // Para cada Contacto chamado "contacto" dentro da lista "contactos"...
        for (Contacto contacto : contactos) {
            // contacto.correspondeAoNome(nome) devolve true ou false.
            if (contacto.correspondeAoNome(nome)) {
                // Se a condicao for true, adicionamos este contacto aos resultados.
                resultados.add(contacto);
            }
        }

        // Devolvemos a lista de contactos encontrados.
        return resultados;
    }

    // Procura contactos cujo telefone ou email correspondem ao texto pesquisado.
    public ArrayList<Contacto> procurarPorTelefoneOuEmail(String pesquisa) {
        ArrayList<Contacto> resultados = new ArrayList<>();

        for (Contacto contacto : contactos) {
            if (contacto.correspondeAoTelefoneOuEmail(pesquisa)) {
                resultados.add(contacto);
            }
        }

        return resultados;
    }

    // Cria e devolve uma lista apenas com contactos favoritos.
    public ArrayList<Contacto> listarFavoritos() {
        ArrayList<Contacto> favoritos = new ArrayList<>();

        for (Contacto contacto : contactos) {
            // isFavorito() devolve true se o contacto estiver marcado como favorito.
            if (contacto.isFavorito()) {
                favoritos.add(contacto);
            }
        }

        return favoritos;
    }

    // Cria e devolve uma lista apenas com contactos de um certo tipo.
    public ArrayList<Contacto> listarPorTipo(TipoContacto tipo) {
        ArrayList<Contacto> resultados = new ArrayList<>();

        for (Contacto contacto : contactos) {
            // "==" compara valores primitivos ou, neste caso, valores de enum.
            // Para enums, usar == e correto porque cada valor do enum e unico.
            if (contacto.getTipo() == tipo) {
                resultados.add(contacto);
            }
        }

        return resultados;
    }

    // Edita um contacto ja existente.
    public boolean editarContacto(String nomeAntigo, String novoNome, String novoTelefone, String novoEmail, TipoContacto novoTipo) {
        // Tentamos encontrar o contacto pelo nome antigo.
        Contacto contacto = encontrarPorNomeExato(nomeAntigo);

        // "==" compara referencias quando usamos objetos.
        // "contacto == null" pergunta se a variavel nao aponta para nenhum objeto.
        if (contacto == null) {
            return false;
        }

        // Antes de editar, verificamos se o novo telefone ou email ja pertence a outro contacto.
        if (existeTelefoneOuEmail(novoTelefone, novoEmail, contacto)) {
            return false;
        }

        // Chamamos um metodo do proprio contacto para alterar os seus dados.
        contacto.atualizarDados(novoNome, novoTelefone, novoEmail, novoTipo);
        ordenarAlfabeticamente();
        return true;
    }

    // Marca como favorito se nao era; desmarca se ja era favorito.
    public boolean alternarFavorito(String nome) {
        Contacto contacto = encontrarPorNomeExato(nome);

        if (contacto == null) {
            return false;
        }

        contacto.alternarFavorito();
        return true;
    }

    // Remove o primeiro contacto com nome exatamente igual, ignorando maiusculas/minusculas.
    public boolean removerPorNome(String nome) {
        // Este e um for tradicional.
        // int i = 0 cria um contador.
        // i < contactos.size() continua enquanto i for menor que o tamanho da lista.
        // i++ aumenta i em 1 no fim de cada repeticao.
        for (int i = 0; i < contactos.size(); i++) {
            // contactos.get(i) vai buscar o contacto na posicao i.
            // equalsIgnoreCase compara textos ignorando maiusculas/minusculas.
            if (contactos.get(i).getNome().equalsIgnoreCase(nome)) {
                // remove(i) remove o elemento da posicao i.
                contactos.remove(i);
                return true;
            }
        }

        // Se o for terminou sem encontrar o contacto, devolvemos false.
        return false;
    }

    // Ordena os contactos alfabeticamente pelo nome.
    public void ordenarAlfabeticamente() {
        // sort(...) e um metodo do ArrayList.
        // Comparator.comparing(...) cria uma regra de ordenacao.
        // "contacto -> contacto.getNome().toLowerCase()" e uma lambda:
        // para cada contacto, usa o nome em minusculas como chave de comparacao.
        contactos.sort(Comparator.comparing(contacto -> contacto.getNome().toLowerCase()));
    }

    // Devolve o numero total de contactos.
    public int contarContactos() {
        // size() devolve quantos elementos existem na lista.
        return contactos.size();
    }

    // Devolve quantos contactos existem de um determinado tipo.
    public int contarPorTipo(TipoContacto tipo) {
        // Reutilizamos listarPorTipo(tipo) e contamos o tamanho dessa lista.
        return listarPorTipo(tipo).size();
    }

    // Devolve quantos contactos estao marcados como favoritos.
    public int contarFavoritos() {
        return listarFavoritos().size();
    }

    // Guarda todos os contactos num ficheiro.
    public void guardarEmFicheiro(String nomeFicheiro) {
        // Lista de linhas de texto que vao ser escritas no ficheiro.
        ArrayList<String> linhas = new ArrayList<>();

        for (Contacto contacto : contactos) {
            // Cada contacto sabe transformar-se numa linha de ficheiro.
            linhas.add(contacto.paraLinhaFicheiro());
        }

        // try/catch e usado para lidar com erros que podem acontecer.
        // Escrever ficheiros pode falhar, por exemplo por falta de permissoes.
        try {
            // Path.of(nomeFicheiro) transforma o nome do ficheiro num caminho.
            // Files.write(...) escreve todas as linhas no ficheiro.
            Files.write(Path.of(nomeFicheiro), linhas);
        } catch (IOException e) {
            // catch apanha o erro se ele acontecer.
            // e.getMessage() mostra uma mensagem tecnica do erro.
            System.out.println("Erro ao guardar contactos: " + e.getMessage());
        }
    }

    // Carrega contactos guardados num ficheiro.
    public void carregarDeFicheiro(String nomeFicheiro) {
        Path caminho = Path.of(nomeFicheiro);

        // "!" significa NAO.
        // Files.exists(caminho) devolve true se o ficheiro existir.
        // !Files.exists(caminho) significa "se o ficheiro NAO existir".
        if (!Files.exists(caminho)) {
            // return sem valor termina um metodo void.
            return;
        }

        try {
            // Files.readAllLines(caminho) le todas as linhas do ficheiro.
            // new ArrayList<>(...) transforma o resultado numa ArrayList.
            ArrayList<String> linhas = new ArrayList<>(Files.readAllLines(caminho));

            for (String linha : linhas) {
                // Contacto.deLinhaFicheiro(linha) tenta criar um objeto Contacto.
                Contacto contacto = Contacto.deLinhaFicheiro(linha);

                // "!=" significa diferente.
                // Aqui perguntamos: contacto e diferente de null?
                if (contacto != null) {
                    contactos.add(contacto);
                }
            }

            ordenarAlfabeticamente();
        } catch (IOException e) {
            System.out.println("Erro ao carregar contactos: " + e.getMessage());
        }
    }

    // Metodo auxiliar privado para encontrar um contacto pelo nome exato.
    private Contacto encontrarPorNomeExato(String nome) {
        for (Contacto contacto : contactos) {
            // Para comparar Strings, usamos equals ou equalsIgnoreCase.
            // Nao usamos "==" para Strings, porque "==" compara referencias de objetos.
            if (contacto.getNome().equalsIgnoreCase(nome)) {
                return contacto;
            }
        }

        // null indica que nao foi encontrado nenhum contacto.
        return null;
    }

    // Verifica se ja existe algum contacto com o mesmo telefone ou email.
    private boolean existeTelefoneOuEmail(String telefone, String email, Contacto contactoIgnorado) {
        for (Contacto contacto : contactos) {
            // Aqui usamos "==" de proposito porque queremos saber se e exatamente
            // o mesmo objeto na memoria. Isto e util quando estamos a editar um contacto
            // e nao queremos considera-lo duplicado de si proprio.
            if (contacto == contactoIgnorado) {
                // continue salta para a proxima volta do ciclo.
                continue;
            }

            // equals compara conteudo de Strings.
            boolean mesmoTelefone = contacto.getTelefone().equals(telefone);

            // equalsIgnoreCase compara Strings ignorando maiusculas/minusculas.
            boolean mesmoEmail = contacto.getEmail().equalsIgnoreCase(email);

            // "||" e OU logico.
            // Se mesmoTelefone for true OU mesmoEmail for true, ha duplicado.
            if (mesmoTelefone || mesmoEmail) {
                return true;
            }
        }

        // Se percorremos todos os contactos e nao encontramos duplicado, devolvemos false.
        return false;
    }
}

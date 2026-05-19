// Esta classe representa UM contacto da agenda.
// Em Programacao Orientada a Objetos, uma classe deve juntar dados e comportamentos
// que pertencem ao mesmo conceito. Aqui, esse conceito e "contacto".
public class Contacto {
    // Estes atributos sao "private", ou seja, so podem ser usados diretamente
    // dentro desta classe. Isto chama-se encapsulamento.
    private String nome;
    private String telefone;
    private String email;
    private TipoContacto tipo;
    private boolean favorito;

    // Construtor da classe Contacto.
    // Um construtor tem o mesmo nome da classe e nao tem tipo de retorno.
    // Ele e chamado quando escrevemos "new Contacto(...)".
    public Contacto(String nome, String telefone, String email, TipoContacto tipo, boolean favorito) {
        // "this.nome" e o atributo do objeto.
        // "nome" e o parametro recebido pelo construtor.
        // O operador "=" coloca o valor da direita dentro da variavel da esquerda.
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
        this.favorito = favorito;
    }

    // Getter do nome.
    // Como o atributo e private, outras classes usam este metodo para ler o nome.
    public String getNome() {
        return nome;
    }

    // Getter do telefone.
    public String getTelefone() {
        return telefone;
    }

    // Getter do email.
    public String getEmail() {
        return email;
    }

    // Getter do tipo.
    public TipoContacto getTipo() {
        return tipo;
    }

    // Getter de boolean costuma comecar com "is" em vez de "get".
    // "boolean" e um tipo que so pode ser true ou false.
    public boolean isFavorito() {
        return favorito;
    }

    // Metodo para alterar os dados principais do contacto.
    // "void" significa que este metodo nao devolve nada; apenas altera o objeto.
    public void atualizarDados(String nome, String telefone, String email, TipoContacto tipo) {
        this.nome = nome;
        this.telefone = telefone;
        this.email = email;
        this.tipo = tipo;
    }

    // Metodo que muda o favorito para o valor contrario.
    public void alternarFavorito() {
        // "!" e o operador de negacao.
        // Se favorito era true, !favorito e false.
        // Se favorito era false, !favorito e true.
        favorito = !favorito;
    }

    // Metodo que devolve true se o nome do contacto contem o texto pesquisado.
    public boolean correspondeAoNome(String pesquisa) {
        // toLowerCase() transforma o texto em minusculas.
        // Fazemos isto nos dois textos para a pesquisa ignorar maiusculas/minusculas.
        // contains(...) verifica se um texto contem outro texto.
        return nome.toLowerCase().contains(pesquisa.toLowerCase());
    }

    // Metodo que devolve true se a pesquisa aparecer no telefone ou no email.
    public boolean correspondeAoTelefoneOuEmail(String pesquisa) {
        // Criamos uma variavel auxiliar para nao repetir pesquisa.toLowerCase().
        String texto = pesquisa.toLowerCase();

        // "||" significa OU.
        // A linha devolve true se:
        // - o telefone contem a pesquisa
        // OU
        // - o email, em minusculas, contem o texto pesquisado.
        return telefone.contains(pesquisa) || email.toLowerCase().contains(texto);
    }

    // Converte o objeto Contacto numa linha de texto para guardar no ficheiro.
    public String paraLinhaFicheiro() {
        // "+" junta textos.
        // ";" e usado como separador entre campos no ficheiro.
        // tipo.name() devolve o nome interno do enum, por exemplo "FAMILIA".
        return escapar(nome) + ";" + escapar(telefone) + ";" + escapar(email) + ";" + tipo.name() + ";" + favorito;
    }

    // Metodo static que cria um Contacto a partir de uma linha do ficheiro.
    // E static porque pertence a classe Contacto, nao a um contacto ja existente.
    public static Contacto deLinhaFicheiro(String linha) {
        // split(";", -1) parte a linha sempre que encontra ";".
        // O -1 ajuda a manter campos vazios, caso existam.
        // "String[]" significa array de Strings, ou seja, varias Strings numa estrutura.
        String[] partes = linha.split(";", -1);

        // ".length" indica quantos elementos existem no array.
        // "!=" significa diferente.
        // Se a linha nao tem exatamente 5 partes, nao sabemos criar um Contacto valido.
        if (partes.length != 5) {
            // "null" significa ausencia de objeto.
            return null;
        }

        // "new Contacto(...)" cria e devolve um novo objeto Contacto.
        // partes[0] acede ao primeiro elemento do array.
        // Em Java, os indices comecam em 0.
        return new Contacto(
            limpar(partes[0]),
            limpar(partes[1]),
            limpar(partes[2]),
            TipoContacto.deTexto(partes[3]),
            Boolean.parseBoolean(partes[4])
        );
    }

    // "@Override" indica que estamos a substituir um metodo herdado de Object.
    // Todas as classes Java herdam de Object, e Object ja tem um metodo toString().
    @Override
    public String toString() {
        // Operador ternario:
        // condicao ? valorSeTrue : valorSeFalse
        // Se favorito for true, estrela recebe " *favorito*"; caso contrario recebe "".
        String estrela = favorito ? " *favorito*" : "";

        // Devolvemos a representacao textual do contacto para mostrar na consola.
        return nome + " - " + telefone + " - " + email + " - " + tipo.getDescricao() + estrela;
    }

    // Metodo privado auxiliar.
    // So esta classe precisa dele, por isso fica private.
    private String escapar(String texto) {
        // replace(";", ",") troca ponto e virgula por virgula.
        // Isto impede que um ";" dentro de um campo estrague o formato do ficheiro.
        return texto.replace(";", ",");
    }

    // Metodo privado e static.
    // E static porque nao depende dos atributos de um contacto especifico.
    private static String limpar(String texto) {
        // trim() remove espacos no inicio e no fim do texto.
        return texto.trim();
    }
}

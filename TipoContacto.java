// "enum" cria um tipo com valores fixos.
// Aqui, um contacto so pode ter um destes tipos: FAMILIA, AMIGO, TRABALHO ou OUTRO.
// Isto evita escrever tipos como texto livre e cometer erros como "Familiaa".
public enum TipoContacto {
    // Cada linha declara um valor possivel do enum.
    // O texto entre parenteses, por exemplo "Familia", e enviado para o construtor do enum.
    FAMILIA("Familia"),
    AMIGO("Amigo"),
    TRABALHO("Trabalho"),
    OUTRO("Outro");

    // "private" impede acesso direto a partir de outras classes.
    // "final" significa que, depois de receber valor no construtor, nao muda mais.
    // "String" e o tipo usado para texto.
    private final String descricao;

    // Este e o construtor do enum.
    // Nao escrevemos "public" porque os valores do enum ja estao fixos acima.
    TipoContacto(String descricao) {
        // "this.descricao" e o atributo do objeto enum.
        // "descricao" sem this e o parametro recebido pelo construtor.
        this.descricao = descricao;
    }

    // Metodo getter: devolve a descricao bonita do tipo.
    public String getDescricao() {
        // "return" devolve um valor a quem chamou o metodo.
        return descricao;
    }

    // "static" significa que podemos chamar este metodo assim:
    // TipoContacto.deTexto("Familia")
    // Ou seja, nao precisamos de ja ter um objeto TipoContacto.
    public static TipoContacto deTexto(String texto) {
        // trim() remove espacos no inicio e no fim.
        // toUpperCase() transforma tudo em maiusculas.
        // Fazemos isto para comparar texto de forma mais previsivel.
        String normalizado = texto.trim().toUpperCase();

        // "for (... : ...)" e um for-each.
        // Le-se: para cada TipoContacto chamado "tipo" dentro de values().
        // values() e um metodo automatico dos enums que devolve todos os valores.
        for (TipoContacto tipo : values()) {
            // "if" executa o bloco apenas se a condicao for verdadeira.
            // ".equals(...)" compara o conteudo de textos/objetos.
            // "||" significa OU logico: basta uma das condicoes ser verdadeira.
            if (tipo.name().equals(normalizado) || tipo.descricao.toUpperCase().equals(normalizado)) {
                // Se encontramos um tipo correspondente ao texto, devolvemos esse tipo.
                return tipo;
            }
        }

        // Se nenhum tipo combinar com o texto recebido, usamos OUTRO como valor seguro.
        return OUTRO;
    }
}

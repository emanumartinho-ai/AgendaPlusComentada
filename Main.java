// "public" significa que esta classe pode ser usada a partir de outros ficheiros.
// "class" e a palavra-chave usada para declarar uma classe em Java.
// "Main" e o nome da classe. Por convencao, nomes de classes comecam por letra maiuscula.
public class Main {
    // "public" permite que o Java encontre este metodo quando o programa arranca.
    // "static" significa que este metodo pertence a classe Main, nao a um objeto Main.
    // "void" significa que o metodo nao devolve nenhum valor.
    // "main" e o nome especial que o Java procura para iniciar o programa.
    // "String[] args" e uma lista de textos recebidos pelo programa pela linha de comandos.
    public static void main(String[] args) {
        // "Menu menu" cria uma variavel chamada menu que pode guardar um objeto do tipo Menu.
        // "=" atribui um valor a variavel.
        // "new Menu()" cria um novo objeto da classe Menu, chamando o construtor Menu().
        // ";" termina a instrucao. Em Java, quase todas as instrucoes acabam com ponto e virgula.
        Menu menu = new Menu();

        // O ponto "." e usado para chamar um metodo de um objeto.
        // Aqui estamos a dizer: no objeto "menu", executa o metodo "iniciar".
        // Os parenteses "()" indicam chamada de metodo. Estao vazios porque nao passamos argumentos.
        menu.iniciar();
    }
}

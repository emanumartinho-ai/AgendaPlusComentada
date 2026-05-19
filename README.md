# Agenda de Contactos em Java

Projeto pedagogico simples para praticar Programacao Orientada a Objetos em Java.

A aplicacao corre na consola e permite gerir contactos guardados num ficheiro
`contactos.txt`.

## Objetivo do projeto

Este projeto foi pensado para aprender POO com exemplos pequenos e diretos.
O codigo esta comentado para ajudar a perceber conceitos como:

- classes e objetos;
- atributos privados;
- encapsulamento;
- construtores;
- getters;
- metodos de instancia;
- metodos `static`;
- composicao entre objetos;
- `enum`;
- listas com `ArrayList`;
- leitura e escrita em ficheiros.

## Classes principais

- `Main`: ponto de entrada do programa. Cria o `Menu` e inicia a aplicacao.
- `Menu`: trata da interacao com o utilizador, mostra opcoes e le dados da consola.
- `Agenda`: gere a colecao de contactos e contem regras como adicionar, remover, editar, procurar e evitar duplicados.
- `Contacto`: representa um contacto individual, com nome, telefone, email, tipo e favorito.
- `TipoContacto`: `enum` com os tipos permitidos: `FAMILIA`, `AMIGO`, `TRABALHO` e `OUTRO`.

## Funcionalidades

1. Adicionar contacto
2. Listar contactos
3. Procurar contacto por nome
4. Procurar por telefone ou email
5. Remover contacto
6. Ordenar alfabeticamente
7. Editar contacto
8. Alternar favorito
9. Listar favoritos
10. Listar por tipo
11. Mostrar estatisticas
0. Sair

## Regras implementadas

- O nome, telefone e email nao podem ficar vazios.
- O telefone deve ter 9 digitos.
- O email deve conter `@` e `.`.
- Nao e permitido adicionar ou editar um contacto com telefone ou email ja usado por outro contacto.
- Os contactos sao ordenados alfabeticamente.
- Os contactos sao guardados automaticamente em `contactos.txt` depois de alteracoes importantes.

## Como compilar e executar

No terminal, dentro da pasta do projeto:

```bash
javac *.java
java Main
```

## Exemplo de organizacao POO

O projeto separa responsabilidades:

- `Contacto` sabe representar os dados de uma pessoa.
- `Agenda` sabe gerir muitos contactos.
- `Menu` sabe falar com o utilizador.
- `Main` apenas inicia o programa.

Esta separacao e importante porque torna o codigo mais facil de ler, testar,
alterar e expandir.

## Sugestao de estudo

Uma boa ordem para estudar o codigo e:

1. Comecar por `Main.java`, porque e o inicio do programa.
2. Ler `Contacto.java`, para perceber como um objeto guarda dados.
3. Ler `TipoContacto.java`, para perceber o uso de `enum`.
4. Ler `Agenda.java`, para perceber como uma classe gere uma lista de objetos.
5. Ler `Menu.java`, para perceber como a aplicacao conversa com o utilizador.

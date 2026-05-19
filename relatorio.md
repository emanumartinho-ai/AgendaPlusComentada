# Relatorio Tecnico - Agenda de Contactos em Java

## 1. Introducao e Contexto

Este projeto consiste no desenvolvimento de uma Agenda de Contactos em Java,
criada com objetivos pedagogicos. A aplicacao permite gerir uma pequena lista de
contactos atraves de um menu interativo na consola.

O projeto foi pensado para consolidar conceitos fundamentais de Programacao
Orientada a Objetos, tais como criacao de classes, encapsulamento, construtores,
metodos, composicao, enumeracoes, listas dinamicas com `ArrayList`, leitura de
dados com `Scanner` e persistencia simples em ficheiro de texto.

A aplicacao encontra-se organizada em cinco elementos principais:

- `Main`: ponto de entrada do programa.
- `Menu`: responsavel pela interacao com o utilizador.
- `Agenda`: responsavel pela gestao da lista de contactos.
- `Contacto`: representa os dados e comportamentos de cada contacto.
- `TipoContacto`: enumeracao que define os tipos validos de contacto.

O projeto evoluiu de uma versao simples para uma versao mais completa, com
validacao de dados, pesquisa, remocao, ordenacao, edicao, favoritos, filtros por
tipo, estatisticas, prevencao de duplicados e armazenamento dos contactos em
ficheiro.

## 2. Levantamento de Requisitos

### 2.1 Requisitos Funcionais

Os requisitos funcionais descrevem aquilo que o sistema deve permitir ao
utilizador fazer.

| Codigo | Requisito |
| --- | --- |
| RF01 | O sistema deve permitir adicionar um novo contacto. |
| RF02 | O sistema deve permitir listar todos os contactos existentes. |
| RF03 | O sistema deve permitir procurar contactos por nome. |
| RF04 | O sistema deve permitir procurar contactos por telefone ou email. |
| RF05 | O sistema deve permitir remover um contacto pelo nome. |
| RF06 | O sistema deve permitir ordenar os contactos alfabeticamente. |
| RF07 | O sistema deve guardar os contactos num ficheiro de texto. |
| RF08 | O sistema deve carregar automaticamente os contactos ao iniciar. |
| RF09 | O sistema deve permitir identificar se um contacto e favorito. |
| RF10 | O sistema deve validar campos obrigatorios, telefone e email. |
| RF11 | O sistema deve permitir editar os dados de um contacto existente. |
| RF12 | O sistema deve permitir alternar o estado de favorito de um contacto. |
| RF13 | O sistema deve permitir listar apenas os contactos favoritos. |
| RF14 | O sistema deve permitir listar contactos por tipo. |
| RF15 | O sistema deve apresentar estatisticas da agenda. |
| RF16 | O sistema deve impedir contactos duplicados por telefone ou email. |

### 2.2 Requisitos Nao Funcionais

Os requisitos nao funcionais descrevem caracteristicas de qualidade e restricoes
do sistema.

| Codigo | Requisito |
| --- | --- |
| RNF01 | A aplicacao deve ser simples de utilizar atraves da consola. |
| RNF02 | O codigo deve estar dividido por classes com responsabilidades claras. |
| RNF03 | A informacao dos contactos deve persistir entre execucoes do programa. |
| RNF04 | O sistema deve apresentar mensagens compreensiveis ao utilizador. |
| RNF05 | A estrutura do projeto deve ser adequada para fins pedagogicos. |
| RNF06 | A aplicacao deve poder ser compilada com comandos Java simples, como `javac *.java`. |
| RNF07 | O codigo deve favorecer bons principios de POO, como encapsulamento e separacao de responsabilidades. |
| RNF08 | O codigo deve estar comentado para facilitar a aprendizagem de iniciantes. |

## 3. Modelacao: Casos de Uso e User Stories

### 3.1 Descricao de Casos de Uso

#### Caso de Uso 1 - Adicionar Contacto

**Ator principal:** Utilizador

**Descricao:** O utilizador introduz os dados de um novo contacto, incluindo
nome, telefone, email, tipo de contacto e indicacao de favorito.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Adicionar contacto".
2. O sistema pede o nome.
3. O sistema pede o telefone.
4. O sistema valida se o telefone tem 9 digitos.
5. O sistema pede o email.
6. O sistema valida se o email contem `@` e `.`.
7. O sistema pede o tipo de contacto.
8. O sistema pergunta se o contacto e favorito.
9. O sistema verifica se ja existe contacto com o mesmo telefone ou email.
10. O contacto e adicionado a agenda.
11. A lista e guardada no ficheiro `contactos.txt`.

**Fluxo alternativo:**

- Se o telefone ou email ja existirem, o sistema nao adiciona o contacto e
  apresenta uma mensagem de aviso.

#### Caso de Uso 2 - Listar Contactos

**Ator principal:** Utilizador

**Descricao:** O utilizador visualiza todos os contactos existentes na agenda.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Listar contactos".
2. O sistema verifica se existem contactos.
3. Se existirem contactos, o sistema apresenta cada contacto na consola.
4. Se nao existirem contactos, o sistema apresenta uma mensagem informativa.

#### Caso de Uso 3 - Procurar Contacto por Nome

**Ator principal:** Utilizador

**Descricao:** O utilizador pesquisa contactos atraves de parte ou da totalidade
do nome.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Procurar contacto por nome".
2. O sistema pede o nome a pesquisar.
3. O sistema procura contactos cujo nome contenha o texto introduzido.
4. O sistema apresenta os resultados encontrados.

#### Caso de Uso 4 - Procurar por Telefone ou Email

**Ator principal:** Utilizador

**Descricao:** O utilizador pesquisa contactos atraves do telefone ou do email.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Procurar por telefone ou email".
2. O sistema pede o texto de pesquisa.
3. O sistema compara o texto com os telefones e emails existentes.
4. O sistema apresenta os contactos encontrados.

#### Caso de Uso 5 - Remover Contacto

**Ator principal:** Utilizador

**Descricao:** O utilizador remove um contacto da agenda atraves do nome.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Remover contacto".
2. O sistema pede o nome do contacto a remover.
3. O sistema procura um contacto com esse nome.
4. Se encontrar, remove o contacto e guarda a lista atualizada.
5. Se nao encontrar, apresenta uma mensagem de erro.

#### Caso de Uso 6 - Ordenar Contactos

**Ator principal:** Utilizador

**Descricao:** O utilizador ordena os contactos por ordem alfabetica.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Ordenar alfabeticamente".
2. O sistema ordena a lista de contactos pelo nome.
3. O sistema guarda a lista ordenada.
4. O sistema informa que a ordenacao foi concluida.

#### Caso de Uso 7 - Editar Contacto

**Ator principal:** Utilizador

**Descricao:** O utilizador altera os dados principais de um contacto existente.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Editar contacto".
2. O sistema pede o nome do contacto a editar.
3. O sistema pede o novo nome, telefone, email e tipo.
4. O sistema valida telefone e email.
5. O sistema verifica se os novos dados nao criam duplicados.
6. O sistema atualiza o contacto.
7. O sistema guarda a lista atualizada.

#### Caso de Uso 8 - Alternar Favorito

**Ator principal:** Utilizador

**Descricao:** O utilizador marca ou desmarca um contacto como favorito.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Alternar favorito".
2. O sistema pede o nome do contacto.
3. O sistema procura o contacto.
4. Se encontrar, inverte o estado de favorito.
5. O sistema guarda a alteracao.

#### Caso de Uso 9 - Listar Favoritos

**Ator principal:** Utilizador

**Descricao:** O utilizador visualiza apenas os contactos marcados como
favoritos.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Listar favoritos".
2. O sistema filtra os contactos favoritos.
3. O sistema apresenta os resultados.

#### Caso de Uso 10 - Listar por Tipo

**Ator principal:** Utilizador

**Descricao:** O utilizador visualiza contactos de um tipo especifico.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Listar por tipo".
2. O sistema apresenta os tipos disponiveis.
3. O utilizador escolhe um tipo.
4. O sistema filtra os contactos desse tipo.
5. O sistema apresenta os resultados.

#### Caso de Uso 11 - Mostrar Estatisticas

**Ator principal:** Utilizador

**Descricao:** O utilizador consulta uma visao resumida da agenda.

**Fluxo principal:**

1. O utilizador escolhe a opcao "Mostrar estatisticas".
2. O sistema mostra o total de contactos.
3. O sistema mostra o total de favoritos.
4. O sistema mostra a quantidade de contactos por tipo.

### 3.2 User Stories

| Codigo | User Story |
| --- | --- |
| US01 | Como utilizador, quero adicionar contactos para guardar informacoes de pessoas importantes. |
| US02 | Como utilizador, quero listar os contactos para consultar rapidamente a minha agenda. |
| US03 | Como utilizador, quero procurar contactos por nome para encontrar uma pessoa especifica. |
| US04 | Como utilizador, quero procurar por telefone ou email para identificar contactos atraves desses dados. |
| US05 | Como utilizador, quero remover contactos para manter a agenda atualizada. |
| US06 | Como utilizador, quero marcar contactos como favoritos para destacar contactos importantes. |
| US07 | Como utilizador, quero que os contactos sejam guardados em ficheiro para nao perder dados ao fechar o programa. |
| US08 | Como estudante, quero que o codigo esteja separado por classes para compreender melhor a Programacao Orientada a Objetos. |
| US09 | Como utilizador, quero editar contactos para corrigir dados sem apagar e criar tudo de novo. |
| US10 | Como utilizador, quero filtrar contactos por tipo para encontrar grupos de pessoas mais rapidamente. |
| US11 | Como utilizador, quero ver estatisticas para perceber a composicao da minha agenda. |
| US12 | Como utilizador, quero evitar duplicados para manter a agenda limpa e coerente. |

## 4. Arquitetura: Explicacao do Diagrama de Classes

A arquitetura do projeto segue uma divisao simples por responsabilidades,
adequada a uma aplicacao de consola e a um contexto de aprendizagem.

### 4.1 Classe `Main`

A classe `Main` contem o metodo `main`, que representa o ponto de entrada da
aplicacao. A sua responsabilidade e criar um objeto da classe `Menu` e iniciar a
execucao do programa.

```java
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        menu.iniciar();
    }
}
```

Esta classe nao contem regras de negocio, pois apenas arranca a aplicacao.

### 4.2 Classe `Menu`

A classe `Menu` e responsavel pela interacao com o utilizador. E nesta classe
que sao apresentadas as opcoes, lidas as respostas e chamadas as operacoes da
agenda.

Principais responsabilidades:

- Mostrar o menu principal.
- Ler opcoes do utilizador.
- Validar entradas, como telefone e email.
- Chamar os metodos da classe `Agenda`.
- Guardar os contactos apos alteracoes relevantes.

A classe `Menu` contem uma instancia de `Agenda`, o que demonstra composicao:
um objeto usa outro objeto para cumprir parte da funcionalidade.

### 4.3 Classe `Agenda`

A classe `Agenda` representa a estrutura que guarda e gere os contactos.
Internamente, utiliza um `ArrayList<Contacto>`.

Principais responsabilidades:

- Adicionar contactos.
- Impedir duplicados por telefone ou email.
- Devolver uma copia da lista de contactos.
- Procurar contactos por nome.
- Procurar contactos por telefone ou email.
- Remover contactos.
- Editar contactos.
- Alternar favoritos.
- Listar favoritos.
- Listar contactos por tipo.
- Calcular estatisticas.
- Ordenar contactos alfabeticamente.
- Guardar contactos em ficheiro.
- Carregar contactos a partir de ficheiro.

Esta classe concentra a logica principal da aplicacao, separando-a da interacao
com o utilizador.

### 4.4 Classe `Contacto`

A classe `Contacto` representa uma entidade do sistema. Cada objeto desta classe
corresponde a uma pessoa guardada na agenda.

Principais atributos:

- `nome`
- `telefone`
- `email`
- `tipo`
- `favorito`

Principais responsabilidades:

- Guardar os dados de um contacto.
- Disponibilizar metodos `get` para aceder aos dados.
- Atualizar os dados principais do contacto.
- Alternar o estado de favorito.
- Verificar se corresponde a uma pesquisa por nome.
- Verificar se corresponde a uma pesquisa por telefone ou email.
- Converter um contacto para uma linha de ficheiro.
- Criar um contacto a partir de uma linha de ficheiro.
- Apresentar o contacto em formato textual atraves do metodo `toString`.

### 4.5 Enum `TipoContacto`

O `TipoContacto` e uma enumeracao que define os tipos permitidos para um
contacto:

- `FAMILIA`
- `AMIGO`
- `TRABALHO`
- `OUTRO`

A utilizacao de um `enum` e melhor do que guardar o tipo como texto livre,
porque reduz erros de escrita e limita os valores a opcoes validas.

### 4.6 Relacoes entre Classes

O diagrama de classes pode ser descrito da seguinte forma:

```text
Main
 |
 | cria
 v
Menu
 |
 | usa
 v
Agenda
 |
 | contem varios
 v
Contacto
 |
 | usa
 v
TipoContacto
```

Em termos de multiplicidade:

- Um `Menu` possui uma `Agenda`.
- Uma `Agenda` possui zero ou muitos `Contacto`.
- Cada `Contacto` possui um `TipoContacto`.
- `TipoContacto` possui um conjunto fixo de valores possiveis.

Esta organizacao ajuda a manter o codigo mais claro, pois cada classe tem uma
funcao especifica.

## 5. Principios de POO Aplicados

### 5.1 Encapsulamento

Os atributos das classes `Contacto`, `Agenda` e `Menu` sao privados. Isto evita
que outras classes alterem diretamente o estado interno dos objetos.

Exemplo:

```java
private ArrayList<Contacto> contactos;
```

Em vez de aceder diretamente a lista, outras classes usam metodos como
`adicionarContacto`, `removerPorNome` ou `getContactos`.

### 5.2 Separacao de Responsabilidades

Cada classe tem um papel claro:

- `Contacto` representa um contacto.
- `Agenda` gere contactos.
- `Menu` interage com o utilizador.
- `Main` inicia o programa.
- `TipoContacto` define os tipos permitidos.

Isto torna o projeto mais facil de compreender e alterar.

### 5.3 Composicao

A classe `Menu` contem uma `Agenda`, e a classe `Agenda` contem varios
`Contacto`. Isto mostra composicao, um principio importante em POO.

### 5.4 Abstracao

O utilizador do objeto `Agenda` nao precisa de saber como a lista e guardada
internamente. Basta chamar metodos como `listarFavoritos` ou `editarContacto`.

### 5.5 Uso de Enum

O `TipoContacto` limita os tipos a valores conhecidos. Isto torna o codigo mais
seguro e mais facil de manter.

## 6. Conclusao e Reflexao Tecnica

O projeto Agenda de Contactos em Java cumpre o objetivo de apresentar uma
aplicacao simples, funcional e adequada ao ensino de Programacao Orientada a
Objetos.

Ao longo do desenvolvimento, foram aplicados conceitos importantes:

- Separacao de responsabilidades entre classes.
- Encapsulamento dos dados.
- Utilizacao de `ArrayList` para armazenar varios objetos.
- Criacao de menus interativos com `Scanner`.
- Validacao basica de dados introduzidos pelo utilizador.
- Persistencia simples em ficheiro de texto.
- Ordenacao, pesquisa e filtragem em listas.
- Edicao de objetos existentes.
- Prevencao de duplicados.
- Utilizacao de `enum` para valores controlados.

Do ponto de vista tecnico, a solucao e suficiente para um projeto introdutorio.
A utilizacao de ficheiro `.txt` facilita a compreensao da persistencia sem
introduzir complexidade adicional, como bases de dados.

Como melhorias futuras, o projeto poderia incluir validacao mais rigorosa de
emails, tratamento mais completo de caracteres especiais em ficheiros CSV,
testes automaticos, identificadores unicos para contactos, pesquisa por varios
campos em simultaneo ou uma interface grafica com JavaFX ou Swing.

Em conclusao, este projeto permite ao aluno compreender a evolucao natural de um
programa simples para uma aplicacao organizada em varias classes, aproximando-se
de uma estrutura mais realista de desenvolvimento de software.

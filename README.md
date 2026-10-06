# 🛒 Análise de Carrinhos com Java, Jackson e Streams

Projeto da **Tarefa de Recuperação P1**. O programa consome a API pública [DummyJSON](https://dummyjson.com/carts), converte o JSON em objetos Java e gera um relatório usando **Streams** e **Lambdas**, sem nenhum `for` ou `while`.

---

## 📋 Sumário

- [Tecnologias](#-tecnologias)
- [Estrutura](#-estrutura)
- [Como executar](#-como-executar)
- [Tratamento de erros](#-tratamento-de-erros)
- [Consultas implementadas](#-consultas-implementadas)
- [Desafios extras](#-desafios-extras)
- [Exemplo de saída](#-exemplo-de-saída)
- [Autor](#-autor)

---

## 🧰 Tecnologias

| Item | Detalhe |
|---|---|
| Linguagem | Java [VERSÃO] |
| Build | Maven |
| JSON | Jackson Databind 2.17.2 |
| HTTP | `java.net.http.HttpClient` |
| API | `GET https://dummyjson.com/carts?limit=0` |

---

## 📁 Estrutura

```
src/main/java/
├── Main.java                   → consultas com Streams
├── modelo/
│   ├── RespostaCarrinhos.java
│   ├── Carrinho.java           → inclui getEconomia()
│   └── ProdutoCarrinho.java
└── servico/
    └── CarrinhoService.java    → requisição, desserialização e erros
```

---

## ▶️ Como executar

```bash
git clone [URL-DO-REPOSITORIO]
cd [NOME-DA-PASTA]
```

Abra o projeto na IDE (IntelliJ, Eclipse ou VS Code), aguarde o Maven baixar as dependências e execute a classe `Main`.

> É necessário ter conexão com a internet.

---

## 🛡️ Tratamento de erros

O método `buscarCarrinhos()` retorna `Optional<RespostaCarrinhos>`. Se algo falhar, mostra uma mensagem e retorna vazio, e o programa termina sem exceção não tratada.

| Problema | Mensagem |
|---|---|
| Sem conexão | `Erro de conexão: ...` |
| Status ≠ 200 | `Erro: a API respondeu com status X` |
| JSON inválido | `Erro: o JSON recebido é inválido.` |
| Requisição interrompida | `Requisição interrompida.` |

**Como testar:**
- Troque a URL por `https://dummyjson.com/carts/9999` para forçar um status diferente de 200.
- Desligue a internet para forçar o erro de conexão.

---

## 🔎 Consultas implementadas

| # | O que faz | Operações |
|---|---|---|
| 1 | Carrinhos com total acima de US$ 1.000 | `filter` |
| 2 | Títulos de produtos com desconto acima de 15% | `flatMap`, `filter`, `map`, `distinct` |
| 3 | Carrinhos por economia, da maior para a menor | `sorted`, `reversed` |
| 4 | Soma do `discountedTotal` de todos os carrinhos | `map`, `reduce` |
| 5 | Quantidade de carrinhos por número de produtos | `groupingBy`, `counting` |

Todos os resultados são exibidos com `forEach`.

**Decisões de implementação**

- **`distinct()` (consulta 2):** o mesmo produto aparece em vários carrinhos; sem ele os títulos se repetem.
- **`Stream.of(soma).forEach(...)` (consulta 4):** o `reduce` gera um único valor, então foi usado um stream de um elemento para cumprir a exigência do `forEach`.
- **`TreeMap` (consulta 5):** mantém a saída ordenada pelo número de produtos.

---

## ⭐ Desafios extras

**1. Carrinho de maior valor** com `max` e `Optional`:

```java
carrinhos.stream()
        .max(Comparator.comparingDouble(Carrinho::getTotal))
        .ifPresentOrElse(
                c -> System.out.println(c),
                () -> System.out.println("Nenhum carrinho encontrado."));
```

**2. Formatação com lambda**, no padrão `Carrinho #[id] | Usuário: [userId] | Itens: [totalQuantity] | Total: US$ [valor]`:

```java
Function<Carrinho, String> formatar = c -> String.format(Locale.US,
        "Carrinho #%d | Usuário: %d | Itens: %d | Total: US$ %.2f",
        c.getId(), c.getUserId(), c.getTotalQuantity(), c.getTotal());
```

---

## 🖥️ Exemplo de saída

```

=== Carrinhos por número de produtos ===
2 produto(s): 58 carrinho(s)
3 produto(s): 36 carrinho(s)
4 produto(s): 33 carrinho(s)
5 produto(s): 42 carrinho(s)
6 produto(s): 39 carrinho(s)
```

---

## 👤 Autor

**[SEU NOME]**
FATEC Praia Grande, Desenvolvimento de Software Multiplataforma (DSM)
GitHub: [github.com/SEU-USUARIO](https://github.com/SEU-USUARIO)

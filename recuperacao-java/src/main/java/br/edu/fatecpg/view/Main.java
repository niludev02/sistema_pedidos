package br.edu.fatecpg.view;

import br.edu.fatecpg.model.Carrinho;
import br.edu.fatecpg.model.ProdutoCarrinho;
import br.edu.fatecpg.model.RespostaCarrinhos;
import br.edu.fatecpg.services.CarrinhoService;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        CarrinhoService service = new CarrinhoService();
        Optional<RespostaCarrinhos> resposta = service.buscarCarrinhos();

        if (resposta.isEmpty()) {
            return;
        }

        List<Carrinho> carrinhos = resposta.get().getCarts();

        carrinhosAcimaDeMil(carrinhos);
        produtosComDescontoAlto(carrinhos);
        carrinhosPorEconomia(carrinhos);
        somaDescontados(carrinhos);
        carrinhosPorQtdProdutos(carrinhos);
    }

    // 1) filter
    private static void carrinhosAcimaDeMil(List<Carrinho> carrinhos) {
        System.out.println("\n=== Carrinhos com total acima de US$ 1000 ===");
        carrinhos.stream()
                .filter(c -> c.getTotal() > 1000)
                .forEach(System.out::println);
    }

    // 2) flatMap + filter + map
    private static void produtosComDescontoAlto(List<Carrinho> carrinhos) {
        System.out.println("\n=== Produtos com desconto acima de 15% ===");
        carrinhos.stream()
                .flatMap(c -> c.getProducts().stream())
                .filter(p -> p.getDiscountPercentage() > 15)
                .map(ProdutoCarrinho::getTitle)
                .distinct()
                .forEach(System.out::println);
    }

    // 3) sorted
    private static void carrinhosPorEconomia(List<Carrinho> carrinhos) {
        System.out.println("\n=== Carrinhos por economia (maior para menor) ===");
        carrinhos.stream()
                .sorted(Comparator.comparingDouble(Carrinho::getEconomia).reversed())
                .forEach(c -> System.out.println(
                        "Carrinho #" + c.getId() + " | Economia: US$ "
                                + String.format("%.2f", c.getEconomia())));
    }

    // 4) reduce
    private static void somaDescontados(List<Carrinho> carrinhos) {
        System.out.println("\n=== Soma do discountedTotal de todos os carrinhos ===");
        double soma = carrinhos.stream()
                .map(Carrinho::getDiscountedTotal)
                .reduce(0.0, Double::sum);

        Stream.of(soma)
                .forEach(s -> System.out.println("Total: US$ " + String.format("%.2f", s)));
    }

    // 5) groupingBy
    private static void carrinhosPorQtdProdutos(List<Carrinho> carrinhos) {
        System.out.println("\n=== Carrinhos por número de produtos ===");
        TreeMap<Integer, Long> agrupado = carrinhos.stream()
                .collect(Collectors.groupingBy(
                        Carrinho::getTotalProducts,
                        TreeMap::new,
                        Collectors.counting()));

        agrupado.forEach((qtd, n) ->
                System.out.println(qtd + " produto(s): " + n + " carrinho(s)"));
    }

    // desafio 1: max + optional
    private static void carrinhoDeMaiorValor (List<Carrinho> carrinhos) {
        System.out.println("\n=== Carrinho de maior valor ===");
        Optional<Carrinho> maior = carrinhos.stream()
                .max(Comparator.comparingDouble(Carrinho::getTotal));

        maior.ifPresentOrElse(
                c -> System.out.println(c),
                () -> System.out.println("Nenhum carrinho encontrado")
        );
    }

    private static void carrinhosFormatados(List<Carrinho> carrinhos) {
        System.out.println("\n=== Carrinho formatados ===");
        Function<Carrinho, String> formatar = c -> String.format(Locale.US, "Carrinho: #%d | Usuário: %d | Itens: %d | Total: US$ %.2f", c.getId(), c.getUserId(), c.getTotalQuantity(), c.getTotal());

        carrinhos.stream()
                .map(formatar)
                .forEach(System.out::println);

    }
}
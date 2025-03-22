package br.house.main;

import br.house.model.Funcionario;
import br.house.model.Pessoa;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {
    public static void main(String[] args) {
        List<Funcionario> funcionarios = new ArrayList<>();

        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19119.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloísa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));

        funcionarios.removeIf(f -> f.getNome().equals("João"));

        System.out.println("Lista de todos os funcionários:");
        funcionarios.forEach(System.out::println);
        System.out.println();

        System.out.println("Lista de funcionários com 10% de aumento:");
        funcionarios.forEach(f -> f.aumentarSalario(new BigDecimal("0.10")));
        funcionarios.forEach(System.out::println);
        System.out.println();

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("Funcionários agrupados por função:");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println("Função: " + funcao);
            lista.forEach(f -> System.out.println("   " + f));
        });
        System.out.println();

        System.out.println("Funcionários que fazem aniversário em outubro (10) e dezembro (12):");
        funcionarios.stream()
                .filter(f -> f.getMesAniversario() == 10 || f.getMesAniversario() == 12)
                .forEach(System.out::println);
        System.out.println();

        Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getIdade));

        funcionarioMaisVelho.ifPresent(f -> {
            System.out.println("Funcionário com maior idade:");
            System.out.println("Nome: " + f.getNome() + ", Idade: " + f.getIdade());
        });
        System.out.println();

        System.out.println("Funcionários por ordem alfabética:");
        funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .forEach(System.out::println);
        System.out.println();

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("pt", "BR")));
        System.out.println("Total dos salários: R$ " + df.format(totalSalarios));
        System.out.println();

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println("Salários mínimos por funcionário:");
        funcionarios.forEach(f -> {
            BigDecimal qtdSalariosMinimos = f.getSalariosMinimos(salarioMinimo);
            System.out.println(f.getNome() + ": " + df.format(qtdSalariosMinimos) + " salários mínimos");
        });
    }
}
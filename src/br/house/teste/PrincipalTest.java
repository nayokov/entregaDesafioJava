package br.house.teste;

import br.house.model.Funcionario;
import br.house.model.Pessoa;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
public class PrincipalTest {

    private List<Funcionario> funcionarios;

    @org.junit.Test
    @Before
    public void setUp() {
        funcionarios = new ArrayList<>();
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
    }

    @org.junit.Test
    public void testRemoverFuncionarioJoao() {

        int tamanhoInicial = funcionarios.size();
        funcionarios.removeIf(f -> f.getNome().equals("João"));

        assertEquals(tamanhoInicial - 1, funcionarios.size());
        assertTrue(funcionarios.stream().noneMatch(f -> f.getNome().equals("João")));
    }

    @org.junit.Test
    public void testAgruparPorFuncao() {

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));


        assertTrue(funcionariosPorFuncao.containsKey("Operador"));
        assertTrue(funcionariosPorFuncao.containsKey("Coordenador"));
        assertTrue(funcionariosPorFuncao.containsKey("Diretor"));
        assertTrue(funcionariosPorFuncao.containsKey("Recepcionista"));
        assertTrue(funcionariosPorFuncao.containsKey("Contador"));
        assertTrue(funcionariosPorFuncao.containsKey("Gerente"));
        assertTrue(funcionariosPorFuncao.containsKey("Eletricista"));


        assertEquals(3, funcionariosPorFuncao.get("Operador").size());
        assertEquals(1, funcionariosPorFuncao.get("Coordenador").size());
        assertEquals(1, funcionariosPorFuncao.get("Diretor").size());
        assertEquals(1, funcionariosPorFuncao.get("Recepcionista").size());
        assertEquals(1, funcionariosPorFuncao.get("Contador").size());
        assertEquals(2, funcionariosPorFuncao.get("Gerente").size());
        assertEquals(1, funcionariosPorFuncao.get("Eletricista").size());
    }

    @org.junit.Test
    public void testAniversariantesOutubroDezembro() {
        List<Funcionario> aniversariantes = funcionarios.stream()
                .filter(f -> f.getMesAniversario() == 10 || f.getMesAniversario() == 12)
                .collect(Collectors.toList());

        assertEquals(2, aniversariantes.size());
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Maria")));
        assertTrue(aniversariantes.stream().anyMatch(f -> f.getNome().equals("Miguel")));
    }

    @org.junit.Test
    public void testFuncionarioMaisVelho() {
        Optional<Funcionario> funcionarioMaisVelho = funcionarios.stream()
                .max(Comparator.comparing(Pessoa::getIdade));

        assertTrue(funcionarioMaisVelho.isPresent());
        assertEquals("Caio", funcionarioMaisVelho.get().getNome());
    }

    @org.junit.Test
    public void testOrdenacaoAlfabetica() {

        List<Funcionario> ordenados = funcionarios.stream()
                .sorted(Comparator.comparing(Pessoa::getNome))
                .collect(Collectors.toList());

        assertEquals("Alice", ordenados.get(0).getNome());
        assertEquals("Arthur", ordenados.get(1).getNome());
        assertEquals("Caio", ordenados.get(2).getNome());
        assertEquals("Heitor", ordenados.get(3).getNome());
        assertEquals("Helena", ordenados.get(4).getNome());
        assertEquals("Heloísa", ordenados.get(5).getNome());
        assertEquals("João", ordenados.get(6).getNome());
        assertEquals("Laura", ordenados.get(7).getNome());
        assertEquals("Maria", ordenados.get(8).getNome());
        assertEquals("Miguel", ordenados.get(9).getNome());
    }

    @org.junit.Test
    public void testTotalSalarios() {
        // 3.11 - Total dos salários
        BigDecimal totalEsperado = new BigDecimal("48563.31");

        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        assertTrue(totalEsperado.subtract(totalSalarios).abs().compareTo(new BigDecimal("0.01")) < 0);
    }

    @org.junit.Test
    public void testSalariosMinimos() {

        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        System.out.println(this.funcionarios);
        Funcionario maria = funcionarios.stream()
                .filter(f -> f.getNome().equals("Maria"))
                .findFirst()
                .orElseThrow();

        BigDecimal salariosMinimosEsperado = new BigDecimal("1.66");
        BigDecimal salariosMinimosCalculado = maria.getSalariosMinimos(salarioMinimo);

        // Comparação com tolerância devido a possíveis diferenças de arredondamento
        assertTrue(salariosMinimosEsperado.subtract(salariosMinimosCalculado).abs().compareTo(new BigDecimal("0.01")) < 0);
    }
}
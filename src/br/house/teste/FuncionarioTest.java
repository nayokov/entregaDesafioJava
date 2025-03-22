package br.house.teste;

import br.house.model.Funcionario;
import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDate;

public class FuncionarioTest {
    @org.junit.Test
    public void testConstrutorEGetters() {
        String nome = "Maria";
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);
        BigDecimal salario = new BigDecimal("2009.44");
        String funcao = "Operador";

        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);

        assertEquals(nome, funcionario.getNome());
        assertEquals(dataNascimento, funcionario.getDataNascimento());
        assertEquals(salario, funcionario.getSalario());
        assertEquals(funcao, funcionario.getFuncao());
    }
    @org.junit.Test
    public void testSetters() {
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(2000, 1, 1),
                new BigDecimal("1000.00"), "Teste");

        String novaFuncao = "Nova Função";
        BigDecimal novoSalario = new BigDecimal("2500.50");

        funcionario.setFuncao(novaFuncao);
        funcionario.setSalario(novoSalario);

        assertEquals(novaFuncao, funcionario.getFuncao());
        assertEquals(novoSalario, funcionario.getSalario());
    }
    @org.junit.Test
    public void testGetSalarioFormatado() {
        BigDecimal salario = new BigDecimal("12345.67");
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(2000, 1, 1),
                salario, "Teste");

        assertEquals("12.345,67", funcionario.getSalarioFormatado());
    }
    @org.junit.Test
    public void testAumentarSalario() {
        BigDecimal salarioInicial = new BigDecimal("1000.00");
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(2000, 1, 1),
                salarioInicial, "Teste");

        funcionario.aumentarSalario(new BigDecimal("0.10"));

        BigDecimal salarioEsperado = new BigDecimal("1100.00");
        assertEquals(salarioEsperado, funcionario.getSalario());

        funcionario.aumentarSalario(new BigDecimal("0.15"));

        salarioEsperado = new BigDecimal("1265.00");
        assertEquals(salarioEsperado, funcionario.getSalario());
    }
    @org.junit.Test
    public void testGetSalariosMinimos() {
        BigDecimal salario = new BigDecimal("3636.00");
        BigDecimal salarioMinimo = new BigDecimal("1212.00");
        Funcionario funcionario = new Funcionario("Teste", LocalDate.of(2000, 1, 1),
                salario, "Teste");

        BigDecimal salariosMinimosEsperado = new BigDecimal("3.00");
        assertEquals(salariosMinimosEsperado, funcionario.getSalariosMinimos(salarioMinimo));
    }
    @org.junit.Test
    public void testToString() {
        String nome = "Maria";
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);
        BigDecimal salario = new BigDecimal("2009.44");
        String funcao = "Operador";

        Funcionario funcionario = new Funcionario(nome, dataNascimento, salario, funcao);

        String expected = "Nome: Maria, Data Nascimento: 18/10/2000, Salário: R$ 2.009,44, Função: Operador";
        assertEquals(expected, funcionario.toString());
    }
}
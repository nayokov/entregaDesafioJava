package br.house.teste;

import br.house.model.Pessoa;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

public class PessoaTest {

    @org.junit.Test
    public void testConstrutorEGetters() {
        String nome = "Maria";
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);

        Pessoa pessoa = new Pessoa(nome, dataNascimento);

        assertEquals(nome, pessoa.getNome());
        assertEquals(dataNascimento, pessoa.getDataNascimento());
    }
    @org.junit.Test
    public void testSetters() {
        Pessoa pessoa = new Pessoa("Teste", LocalDate.of(2000, 1, 1));

        String novoNome = "Novo Nome";
        LocalDate novaData = LocalDate.of(1995, 5, 15);

        pessoa.setNome(novoNome);
        pessoa.setDataNascimento(novaData);

        assertEquals(novoNome, pessoa.getNome());
        assertEquals(novaData, pessoa.getDataNascimento());
    }
    @org.junit.Test
    public void testGetIdade() {
        LocalDate hoje = LocalDate.now();
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);

        Pessoa pessoa = new Pessoa("Maria", dataNascimento);

        int idadeEsperada = hoje.getYear() - dataNascimento.getYear();
        if (hoje.getMonthValue() < dataNascimento.getMonthValue() ||
                (hoje.getMonthValue() == dataNascimento.getMonthValue() &&
                        hoje.getDayOfMonth() < dataNascimento.getDayOfMonth())) {
            idadeEsperada--;
        }

        assertEquals(idadeEsperada, pessoa.getIdade());
    }
    @org.junit.Test
    public void testGetDataNascimentoFormatada() {
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);
        Pessoa pessoa = new Pessoa("Maria", dataNascimento);

        assertEquals("18/10/2000", pessoa.getDataNascimentoFormatada());
    }
    @org.junit.Test
    public void testGetMesAniversario() {
        LocalDate dataNascimento = LocalDate.of(2000, 10, 18);
        Pessoa pessoa = new Pessoa("Maria", dataNascimento);

        assertEquals(10, pessoa.getMesAniversario());
    }
}
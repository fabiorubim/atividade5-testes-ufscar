import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class PessoaTest {

	@Test
    public void testExistePessoa() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setNome("Fabio");
        pessoas.add(pessoaExistente);

        RHService rhServiceMock = Mockito.mock(RHService.class);
        when(rhServiceMock.getAllPessoas()).thenReturn(pessoas);

        PessoaDAO pessoaDAO = new PessoaDAO(rhServiceMock);

        boolean result = pessoaDAO.existePessoa("Fabio");
        assertTrue(result);
    }

    @Test
    public void testNaoExistePessoa() {
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        Pessoa pessoaExistente = new Pessoa();
        pessoaExistente.setNome("Carlos");
        pessoas.add(pessoaExistente);

        RHService rhServiceMock = Mockito.mock(RHService.class);
        when(rhServiceMock.getAllPessoas()).thenReturn(pessoas);

        PessoaDAO pessoaDAO = new PessoaDAO(rhServiceMock);

        boolean result = pessoaDAO.existePessoa("Pedro");
        assertFalse(result);
    }

}

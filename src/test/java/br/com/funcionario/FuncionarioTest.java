package br.com.funcionario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

class FuncionarioTest {

    @Test
    public void testFuncionarioTecnicoSemBloqueioCPF() {

        String categoria = "tecnico";
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Funcionario tecnico1 = new Funcionario();
        tecnico1.setCpf("123456789-00");
        funcionarios.add(tecnico1);
        Funcionario tecnico2 = new Funcionario();
        tecnico2.setCpf("987654321-11");
        funcionarios.add(tecnico2);

        FuncionarioDAO funcionarioDaoMock = Mockito.mock(FuncionarioDAO.class);
        when(funcionarioDaoMock.getFuncionariosBy(categoria)).thenReturn(funcionarios);
        
        ReceitaFederal receitaFederalMock = Mockito.mock(ReceitaFederal.class);
        when(receitaFederalMock.isCPFBloqueado(tecnico1.getCpf())).thenReturn(false);
        when(receitaFederalMock.isCPFBloqueado(tecnico2.getCpf())).thenReturn(false);

        RelatorioDeFuncionarios relatorio = new RelatorioDeFuncionarios(funcionarioDaoMock);
        relatorio.setRf(receitaFederalMock);

        int result = relatorio.getFuncComCPFBloqueado(categoria);
        assertEquals(0, result);
    }

    @Test
    public void testGetFuncComCPFBloqueadoFuncionarioAnalistaComBloqueio() {
        String categoria = "analista";
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Funcionario analista = new Funcionario();
        analista.setCpf("052452590-01");
        funcionarios.add(analista);

        FuncionarioDAO funcionarioDaoMock = Mockito.mock(FuncionarioDAO.class);
        when(funcionarioDaoMock.getFuncionariosBy(categoria)).thenReturn(funcionarios);

        ReceitaFederal receitaFederalMock = Mockito.mock(ReceitaFederal.class);
        when(receitaFederalMock.isCPFBloqueado(analista.getCpf())).thenReturn(true);

        RelatorioDeFuncionarios relatorio = new RelatorioDeFuncionarios(funcionarioDaoMock);
        relatorio.setRf(receitaFederalMock);

        int result = relatorio.getFuncComCPFBloqueado(categoria);
        assertEquals(1, result);
    }

    @Test
    public void testGetFuncComCPFBloqueadoFuncionariosGerenteComBloqueio() {
        String categoria = "gerente";
        ArrayList<Funcionario> funcionarios = new ArrayList<>();
        Funcionario gerente1 = new Funcionario();
        gerente1.setCpf("123456789-00");
        funcionarios.add(gerente1);
        Funcionario gerente2 = new Funcionario();
        gerente2.setCpf("111222333-44");
        funcionarios.add(gerente2);
        Funcionario gerente3 = new Funcionario();
        gerente3.setCpf("654321987-23");
        funcionarios.add(gerente3);
        Funcionario gerente4 = new Funcionario();
        gerente4.setCpf("098876654-99");
        funcionarios.add(gerente4);

        FuncionarioDAO funcionarioDaoMock = Mockito.mock(FuncionarioDAO.class);
        when(funcionarioDaoMock.getFuncionariosBy(categoria)).thenReturn(funcionarios);

        ReceitaFederal receitaFederalMock = Mockito.mock(ReceitaFederal.class);
        when(receitaFederalMock.isCPFBloqueado(gerente1.getCpf())).thenReturn(false);
        when(receitaFederalMock.isCPFBloqueado(gerente2.getCpf())).thenReturn(true);
        when(receitaFederalMock.isCPFBloqueado(gerente3.getCpf())).thenReturn(false);
        when(receitaFederalMock.isCPFBloqueado(gerente4.getCpf())).thenReturn(true);

        RelatorioDeFuncionarios relatorio = new RelatorioDeFuncionarios(funcionarioDaoMock);
        relatorio.setRf(receitaFederalMock);

        int result = relatorio.getFuncComCPFBloqueado(categoria);
        assertEquals(2, result);
    }
}

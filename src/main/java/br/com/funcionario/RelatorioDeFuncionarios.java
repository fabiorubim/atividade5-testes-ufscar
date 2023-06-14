package br.com.funcionario;

import java.util.ArrayList;

public class RelatorioDeFuncionarios {
	FuncionarioDAO funcDao;
	ReceitaFederal rf;

	public RelatorioDeFuncionarios(FuncionarioDAO funcDao) {
		this.funcDao = funcDao;
	}

	public void setRf(ReceitaFederal rf) {
		this.rf = rf;
	}

// retorna a qtde de funcionarios da categoria fornecida com o cpf bloqueado
	public int getFuncComCPFBloqueado(String categoria) {
		int numeroDeFuncionarios = 0;
		ArrayList<Funcionario> funcCategoria = funcDao.getFuncionariosBy(categoria);
		for (Funcionario f : funcCategoria) {
			if (rf.isCPFBloqueado(f.getCpf()))
				numeroDeFuncionarios++;
		}
		return numeroDeFuncionarios;
	}
}
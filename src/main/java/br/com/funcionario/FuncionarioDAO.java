package br.com.funcionario;

import java.util.ArrayList;

public interface FuncionarioDAO {
	public ArrayList<Funcionario> getFuncionariosBy(String categoria);
}
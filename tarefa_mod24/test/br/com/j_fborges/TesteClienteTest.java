package br.com.j_fborges;

import org.junit.Assert;
import org.junit.Test;

import br.com.j_fborges.TesteCliente;

public class TesteClienteTest {

	@Test
	public void testeClasseCliente() {
		TesteCliente cli = new TesteCliente();
		cli.adicionarNome("Rodrigo");
		cli.adicionarNome1("Rodrigo");

		Assert.assertEquals("Rodrigo", cli.getNome());
	}
}

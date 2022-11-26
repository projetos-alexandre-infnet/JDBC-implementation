package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Produto;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ProdutoDAOTest {

	
	@Before
	public void inicializar() {
		IDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto(1L, "something", 123.90);
		produtoDAO.salvar(produto);
	}
	
	
	@Test
	public void test() {
		IDAO produtoDAO = new ProdutoDAO();
		Produto produto = new Produto(1L, "something", 123.90);
		boolean validacao = produtoDAO.salvar(produto);
		Assert.assertTrue(validacao);
	}
	
	
	@Test
	public void testUpdate() {
		IDAO produtoDAO = new ProdutoDAO();
		List<Produto> lista = produtoDAO.listarTodos();
		Produto produto = new Produto(lista.get(0).getCodigo(), "something", 123.90);
		boolean validacao = produtoDAO.atualizar(produto);
		Assert.assertTrue(validacao);
	}
	
	@Test
	public void testDelete() {
		IDAO produtoDAO = new ProdutoDAO();
		List<Produto> lista = produtoDAO.listarTodos();
		Produto produto = new Produto(lista.get(lista.size()-1).getCodigo(), "something", 123.90);
		boolean validacao = produtoDAO.deletar(produto);
		Assert.assertTrue(validacao);
	}
	
	
	@Test
	public void testListaClientes() {
		IDAO produtoDAO = new ProdutoDAO();
		List<Produto> lista = produtoDAO.listarTodos();
		Assert.assertTrue(lista.size() > 0);
	}
	

	@Test
	public void testObterCliente() {
		IDAO produtoDAO = new ProdutoDAO();
		List<Produto> lista = produtoDAO.listarTodos();
		Produto produto = (Produto) produtoDAO.obter(lista.get(0).getCodigo());
		Assert.assertNotNull(produto);
	}
	

}

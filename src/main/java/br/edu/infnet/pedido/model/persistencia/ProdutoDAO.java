package br.edu.infnet.pedido.model.persistencia;

import br.edu.infnet.pedido.model.entidade.Produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends JdbcDAO<Produto> {

	@Override
	public Boolean salvar(Produto produto) {
		String sql = "insert into produto(codigo, descricao, preco) values (?, ?, ?)";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, produto.getCodigo()); //sql injection
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean atualizar(Produto produto) {
		String sql = "update produto set preco = ? where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setDouble(1, produto.getPreco());
			pstm.setLong(2, produto.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Boolean deletar(Produto produto) {
		String sql = "delete from produto where codigo = ?";
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, produto.getCodigo());
			return pstm.executeUpdate() > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public Produto obter(Long codigo) {
		String sql = "select * from produto where codigo = ?";
		Produto produto = new Produto();
		try {
			pstm = con.prepareStatement(sql);
			pstm.setLong(1, codigo);
			rs = pstm.executeQuery();
			if(rs.next()) {
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long novoCodigo = rs.getLong("codigo");
				produto = new Produto(novoCodigo, descricao, preco);
			}
			return produto;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Produto> listarTodos() {
		String sql = "select * from produto";
		List<Produto> produtos = new ArrayList<>();
		try {
			stm = con.createStatement();
			rs = stm.executeQuery(sql);
			while(rs.next()) {
				String descricao = rs.getString("descricao");
				Double preco = rs.getDouble("preco");
				Long novoCodigo = rs.getLong("codigo");
				Produto produto = new Produto(novoCodigo, descricao, preco);
				produtos.add(produto);
			}
			return produtos;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}

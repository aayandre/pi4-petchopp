package com.senac.petchopp.model.produto;

import java.sql.SQLException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.model.Upload;
import com.senac.petchopp.wos.FormularioSearch;

public class ProdutoService {

	private ProdutoDAO produtoBanco = new ProdutoDAO();

	public ProdutoService() {
	}

	public void saveProduto(Object upload, Produto novo) throws Exception {
		MultipartFile arquivo = (MultipartFile) upload;
		try {
			Upload.salvar(arquivo);
			novo.setUrlImagem(arquivo.getOriginalFilename());
			produtoBanco.salvar(novo);
		} catch (SQLException e) {
			throw new Exception("Erro ao salvar o produto. (ProdutoService)", e.getCause());
		}
	}

	public Produto searchByCodigo(String codigo) {
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return adquirido;
	}

	public void disableProduto(String codigo) {
		produtoBanco.deletar(codigo);
	}

	public void updateProduto(Produto alterado) {
		produtoBanco.atualizar(alterado);
	}

	public List<Produto> searchByNome(String nome) throws SQLException {
		// Vai procurar o nome em qualquer posição da coluna Nome
		// por conta dos %
		List<Produto> lista = produtoBanco.getByNome("%" + nome + "%");
		return lista;
	}

	public List<Produto> searchByFormularioSearch(FormularioSearch procura) {
		return null;
	}

}

package com.senac.petchopp.model.produto;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.TagDAO;
import com.senac.petchopp.daos.TipoDAO;
import com.senac.petchopp.model.Upload;
import com.senac.petchopp.model.tag.Tag;
import com.senac.petchopp.model.tipo.Tipo;
import com.senac.petchopp.wos.FormularioProduto;
import com.senac.petchopp.wos.FormularioSearch;
import com.senac.petchopp.wos.HomeProdutos;

public class ProdutoService {

	private ProdutoDAO produtoBanco = new ProdutoDAO();
	private TipoDAO tipoBanco = new TipoDAO();
	private TagDAO tagBanco = new TagDAO();

	public ProdutoService() {
	}

	public void saveProduto(Object upload, FormularioProduto novo) throws SQLException {
		MultipartFile arquivo = (MultipartFile) upload;
		try {

			// Salvando
			novo.getProduto().setUrlImagem(arquivo.getOriginalFilename());
			produtoBanco.salvar(novo);
			
			// Só faz o upload se salvar o produto no banco
			Upload.salvar(arquivo);

		} catch (SQLException | IOException e) {
			throw new SQLException("Erro ao salvar o produto. (ProdutoService)", e.getCause());
		}
	}

	public Produto searchByCodigo(String codigo) {
		Produto adquirido = (Produto) produtoBanco.getByCodigo(codigo);
		return adquirido;
	}

	public void disableProduto(String codigo) {
		produtoBanco.deletar(codigo);
	}

	public void updateProduto(FormularioProduto alterado) {
		produtoBanco.atualizar(alterado);
	}

	public List<Produto> searchByNome(String nome) throws SQLException {
		// Vai procurar o nome em qualquer posição da coluna Nome
		// por conta dos %
		List<Produto> lista = produtoBanco.getByNome("%" + nome + "%");
		return lista;
	}

	public List<Produto> searchByFormularioSearch(FormularioSearch procura) throws SQLException {
		String tags = "%%";
		System.out.println(procura);

		if (!procura.getFiltros().getTags().isEmpty()) {
			tags = "";
			List<Tag> tages = procura.getFiltros().getTags();

			for (Tag tag : tages) {
				Tag tagNow = (Tag) tag;
				tags += tagNow.getNome();
				if (!(tages.indexOf(tagNow) == tages.size() - 1)) {
					tags += "|";
				}
			}
		}

		List<Produto> produtos = produtoBanco.searchByVarios(procura.getProcura(), tags);
		return produtos;
	}

	public List<Produto> listByTipoDescricao(String descricao) throws SQLException {
		List<Produto> produtos = produtoBanco.getByTipo(descricao);
		return produtos;
	}

	public List<Produto> listyTipoId(String idTipo) throws SQLException {
		int id = Integer.parseInt(idTipo);
		List<Produto> produtos = produtoBanco.getByTipo(id);
		return produtos;
	}

	public List<HomeProdutos> getSomeProdutos() throws SQLException {
		int[] tiposIds = { 6, 10 };
		// String[] tiposDescricao = {"nome1", "nome2"};

		List<HomeProdutos> hps = new ArrayList<>();

		for (int i : tiposIds) {
			HomeProdutos hp = new HomeProdutos();
			hp.setTipo(tipoBanco.getTipoByID(i));
			hp.setProdutos(produtoBanco.getByTipo(i));
			hps.add(hp);
		}
		return hps;
	}

	public FormularioProduto populaFormularioProduto() throws SQLException {
		FormularioProduto formProd = new FormularioProduto();
		try {
			// Lista tipos
			List<Tipo> tipos = tipoBanco.getAllTipos();
			// Pega tags por tipo e add no form
			for (Tipo tipo : tipos) {
				formProd.addTipoTag(tagBanco.getAllTagsByTipos(tipo));
			}
			formProd.setProduto(new Produto());
			return formProd;
		} catch (SQLException e) {
			throw new SQLException("Erro ao criar/popular o FormularioProduto.", e.getCause());
		}
	}

}

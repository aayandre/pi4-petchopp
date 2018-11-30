package com.senac.petchopp.model.produto;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.senac.petchopp.daos.ProdutoDAO;
import com.senac.petchopp.daos.TipoDAO;
import com.senac.petchopp.model.Upload;
import com.senac.petchopp.model.tag.Tag;
import com.senac.petchopp.model.tipo.Tipo;
import com.senac.petchopp.wos.FormularioSearch;
import com.senac.petchopp.wos.HomeProdutos;

public class ProdutoService {

	private ProdutoDAO produtoBanco = new ProdutoDAO();
	private TipoDAO tipoBanco = new TipoDAO();

	public ProdutoService() {
	}

	public void saveProduto(Object upload, Produto novo) throws Exception {
		MultipartFile arquivo = (MultipartFile) upload;
		try {
			// Arrumando a data
//			Calendar cal = Calendar.getInstance();
//			cal.setTime(novo.getDtCompra());
//			novo.setDtCompra(cal.getTime());

			// Salvando
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
		
		Tipo tipo = new Tipo();
		List<Produto> produtos = new ArrayList<>();
		List<HomeProdutos> hps = new ArrayList<>();

		for (int i : tiposIds) {
//			tipo = tipoBanco.getTipoByID(i);
//			produtos = produtoBanco.getByTipo(i);
			HomeProdutos hp = new HomeProdutos();
			hp.setTipo(tipoBanco.getTipoByID(i));
			hp.setProdutos(produtoBanco.getByTipo(i));
			hps.add(hp);
		}
		return hps;
	}
}

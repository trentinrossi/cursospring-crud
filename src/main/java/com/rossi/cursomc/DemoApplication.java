package com.rossi.cursomc;

import java.util.Arrays;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.model.Cidade;
import com.rossi.cursomc.model.Estado;
import com.rossi.cursomc.model.Produto;
import com.rossi.cursomc.repository.CategoriaRepository;
import com.rossi.cursomc.repository.CidadeRepository;
import com.rossi.cursomc.repository.EstadoRepository;
import com.rossi.cursomc.repository.ProdutoRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository repoCategoria;

	@Autowired
	private ProdutoRepository repoProduto;

	@Autowired
	private EstadoRepository repoEstado;

	@Autowired
	private CidadeRepository repoCidade;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// ############################################################
		// Categorias
		Categoria cat1 = new Categoria();
		Categoria cat2 = new Categoria();
		cat1.setNome("Informática");
		cat2.setNome("Escritório");

		// Produtos
		Produto p1 = new Produto();
		Produto p2 = new Produto();
		Produto p3 = new Produto();
		p1.setNome("Computador");
		p1.setPreco(2000.00);
		p2.setNome("Impressora");
		p2.setPreco(800.00);
		p3.setNome("Mouse");
		p3.setPreco(80.00);

		// Associações entre Categorias x Produtos
		cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));

		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));

		// Salvando no banco Categorias e Produtos
		repoCategoria.saveAll(Arrays.asList(cat1, cat2));
		repoProduto.saveAll(Arrays.asList(p1, p2, p3));
		// ############################################################

		// ############################################################
		// Estados e Cidades
		Estado est1 = new Estado();
		Estado est2 = new Estado();
		est1.setNome("Minas Gerais");
		est2.setNome("São Paulo");

		Cidade c1 = new Cidade();
		Cidade c2 = new Cidade();
		Cidade c3 = new Cidade();
		c1.setNome("Uberlândia");
		c1.setEstado(est1);
		c2.setNome("São Paulo");
		c2.setEstado(est2);
		c3.setNome("Campinas");
		c3.setEstado(est2);

		repoEstado.saveAll(Arrays.asList(est1, est2));
		repoCidade.saveAll(Arrays.asList(c1, c2, c3));
	}

}

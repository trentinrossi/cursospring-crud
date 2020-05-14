package com.rossi.cursomc;

import java.util.Arrays;

import com.rossi.cursomc.model.Categoria;
import com.rossi.cursomc.model.Produto;
import com.rossi.cursomc.repository.CategoriaRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
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
	}

}

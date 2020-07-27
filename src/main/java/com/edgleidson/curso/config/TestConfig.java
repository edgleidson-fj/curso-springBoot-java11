package com.edgleidson.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edgleidson.curso.entidade.Categoria;
import com.edgleidson.curso.entidade.Pedido;
import com.edgleidson.curso.entidade.Produto;
import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.entidade.enums.PedidoStatus;
import com.edgleidson.curso.repositorios.CategoriaRepository;
import com.edgleidson.curso.repositorios.PedidoRepository;
import com.edgleidson.curso.repositorios.ProdutoRepository;
import com.edgleidson.curso.repositorios.UserRepository;

// Configuração do Perfil de teste.

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	// Injeção de dependencia.
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	

	// Método da implementação CommandLineRunner.
	// Tudo dentro método será executado quando aplicação for iniciada.
	// Carga inicial do banco de dados.
	@Override
	public void run(String... args) throws Exception {
		// CLIENTE (id-nome-email-telefone-senha).
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// PEDIDO (id-momento-pedidoStatus-CLIENTE) --- Data com String no padrão ISO 8601.
		Pedido p1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), PedidoStatus.PAGO, u1);
		Pedido p2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), PedidoStatus.AGUARDANDO_PAGAMENTO, u2);
		Pedido p3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), PedidoStatus.ENVIADO, u1);
		
		// CATEGORIA (id-nome).
		Categoria cat1 = new Categoria(null, "Electronics"); 
		Categoria cat2 = new Categoria(null, "Books"); 
		Categoria cat3 = new Categoria(null, "Computers"); 
		
		// PRODUTO (id-nome-descricrao-preco-CATEGORIA).
		Produto prod1 = new Produto(null, "The Lord of the Rings", "Lorem ipsum dolor sit amet, consectetur.", 90.5, ""); 
		Produto prod2 = new Produto(null, "Smart TV", "Nulla eu imperdiet purus. Maecenas ante.", 2190.0, ""); 
		Produto prod3 = new Produto(null, "Macbook Pro", "Nam eleifend maximus tortor, at mollis.", 1250.0, ""); 
		Produto prod4 = new Produto(null, "PC Gamer", "Donec aliquet odio ac rhoncus cursus.", 1200.0, ""); 
		Produto prod5 = new Produto(null, "Rails for Dummies", "Cras fringilla convallis sem vel faucibus.", 100.99, "");

		// Insert.
		userRepository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(p1, p2, p3));
		categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5));
		
		// Add Categoria nos Produtos - (TB_PRODUTO_CATEGORIA).
		prod1.getCategories().add(cat2);
		prod2.getCategories().add(cat1);
		prod2.getCategories().add(cat3);
		prod3.getCategories().add(cat3);
		prod4.getCategories().add(cat3);
		prod5.getCategories().add(cat2);		
		produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5));
		
		
	}
}

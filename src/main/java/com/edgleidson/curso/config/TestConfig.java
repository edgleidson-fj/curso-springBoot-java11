package com.edgleidson.curso.config;

import java.time.Instant;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edgleidson.curso.entidade.Pedido;
import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.repositorios.PedidoRepository;
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

	// Método da implementação CommandLineRunner.
	// Tudo dentro método será executado quando aplicação for iniciada.
	// Carga inicial do banco de dados.
	@Override
	public void run(String... args) throws Exception {
		// Clientes (id-nome-email-telefone-senha).
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");

		// Pedidos (id-momento-CLIENTE) --- Data com String no padrão ISO 8601.
		Pedido p1 = new Pedido(null, Instant.parse("2019-06-20T19:53:07Z"), u1);
		Pedido p2 = new Pedido(null, Instant.parse("2019-07-21T03:42:10Z"), u2);
		Pedido p3 = new Pedido(null, Instant.parse("2019-07-22T15:21:22Z"), u1);

		// Insert.
		userRepository.saveAll(Arrays.asList(u1, u2));
		pedidoRepository.saveAll(Arrays.asList(p1,p2,p3));
	}
}

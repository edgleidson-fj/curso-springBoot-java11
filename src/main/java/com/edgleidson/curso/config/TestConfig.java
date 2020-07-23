package com.edgleidson.curso.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.edgleidson.curso.entidade.User;
import com.edgleidson.curso.repositorios.UserRepository;
import com.sun.xml.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

// Configuração do Perfil de teste.

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	// Injeção de dependencia.
	@Autowired
	private UserRepository userRepository;

	// Método da implementação CommandLineRunner.
	// Tudo dentro método será executado quando aplicação for iniciada.
	@Override
	public void run(String... args) throws Exception {
		//Instanciado manualmente.
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		// Insert.
		userRepository.saveAll(Arrays.asList(u1,u2));
	}
}

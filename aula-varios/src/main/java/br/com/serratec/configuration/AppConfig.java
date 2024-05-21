package br.com.serratec.configuration;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class AppConfig {
	
	@Value("${dominio.openapi.dev-url}")
	private String devUrl;
	
	@Value("${dominio.openapi.prod-url}")
	private String webUrl;
	
	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	OpenAPI myOpenAPI() {
		Server dbServer = new Server();
		dbServer.setDescription("URL do Server Desenvolvimento");
		dbServer.setUrl(devUrl);
		

		Server webServer = new Server();
		webServer.setDescription("URL do Server Produção");
		webServer.setUrl(webUrl);

		Contact contato = new Contact();
		contato.setEmail("roni@gmail.com");
		contato.setName("Roni Schanuel");
		
		License license = new License().name("Apache License").url("https://.....");
		
		Info info = new Info().title("API DE CADASTRO DE USUÁRIOS").version("1.0")
				.contact(contato).license(license).description("API PARA ESTUDOS");
		
		return new OpenAPI().info(info).servers(List.of(dbServer,webServer));
		
		
	}
	
}

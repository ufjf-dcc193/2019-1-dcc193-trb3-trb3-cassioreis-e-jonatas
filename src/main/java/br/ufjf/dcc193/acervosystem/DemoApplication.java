package br.ufjf.dcc193.acervosystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import br.ufjf.dcc193.acervosystem.model.Anotacao;
import br.ufjf.dcc193.acervosystem.model.Etiqueta;
import br.ufjf.dcc193.acervosystem.model.Item;
import br.ufjf.dcc193.acervosystem.model.Usuario;
import br.ufjf.dcc193.acervosystem.model.Vinculo;
import br.ufjf.dcc193.acervosystem.repository.AnotacaoRepository;
import br.ufjf.dcc193.acervosystem.repository.EtiquetaRepository;
import br.ufjf.dcc193.acervosystem.repository.ItemRepository;
import br.ufjf.dcc193.acervosystem.repository.UsuarioRepository;


@SpringBootApplication
public class DemoApplication {

	
	public static UsuarioRepository usuarioRepository;
	
	public static ItemRepository itemRepository;
	
	public static EtiquetaRepository etiquetaRepository;
	
	public static AnotacaoRepository anotacaoRepository;

	public static void main(String[] args) {
		ConfigurableApplicationContext ctx = SpringApplication.run(DemoApplication.class, args);
		usuarioRepository = ctx.getBean(UsuarioRepository.class);
		itemRepository = ctx.getBean(ItemRepository.class);
		etiquetaRepository = ctx.getBean(EtiquetaRepository.class);
		anotacaoRepository = ctx.getBean(AnotacaoRepository.class);
		popularDadosPrincipais();
	}

	public static void popularDadosPrincipais(){
		Usuario u = new Usuario("Usuario 1","descricao usuario 1","mail@mail.com","1234");
		Usuario u2 = new Usuario("Usuario 2","descricao usuario 1","jjsfandre@gmail.com","1234");
		
		Item i = new Item("Obra 1");
		Item i3 = new Item("Obra 2");
		Item i2 = new Item("Documento 1");
		Item i4 = new Item("Documento 2");
		
		Etiqueta e = new Etiqueta("Etiqueta 1", "Descrição etiqueta 1", "url.com.br");
		Etiqueta e2 = new Etiqueta("Etiqueta 2", "Descrição etiqueta 2", "url2.com.br");
		Etiqueta e3 = new Etiqueta("Etiqueta 3", "Descrição etiqueta 3", "url3.com.br");
		
		
		usuarioRepository.save(u);		
		usuarioRepository.save(u2);		
		etiquetaRepository.save(e);
		etiquetaRepository.save(e2);
		etiquetaRepository.save(e3);
		itemRepository.save(i);
		itemRepository.save(i2); 
		itemRepository.save(i3); 
		itemRepository.save(i4); 
		
	}

}

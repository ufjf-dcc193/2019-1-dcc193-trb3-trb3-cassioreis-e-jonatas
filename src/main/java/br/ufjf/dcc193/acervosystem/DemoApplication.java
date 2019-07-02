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
		Item i2 = new Item("Documento 1");
		
		Etiqueta e = new Etiqueta("Etiqueta 1", "Descrição etiqueta 1", "url.com.br");
		Etiqueta e2 = new Etiqueta("Etiqueta 2", "Descrição etiqueta 2", "url2.com.br");
		Etiqueta e3 = new Etiqueta("Etiqueta 3", "Descrição etiqueta 3", "url3.com.br");
		
		Anotacao a= new Anotacao("Anotação 1", "Descrição anotação 1", "url-anot.com.br", "01-07-19");
		Anotacao a2= new Anotacao("Anotação 2", "Descrição anotação 2", "url-anot2.com.br", "02-07-19");
		Anotacao a3= new Anotacao("Anotação 3", "Descrição anotação 3", "url-anot3.com.br", "03-07-19");
		a.setUsuario(u);
		a2.setUsuario(u);
		
		Vinculo v = new Vinculo(true);
		v.setItemOrigem(i);
		v.setItemDestino(i2);
		v.addEtiqueta(e3);
		v.addAnotacao(a3);
		
		/*i.addAnotacao(a);
		i.addEtiqueta(e);
		i.addEtiqueta(e2);
		
		i2.addAnotacao(a2);
		i2.addEtiqueta(e);
		i2.addEtiqueta(e2); */
		
		
		usuarioRepository.save(u);		
		usuarioRepository.save(u2);		
		anotacaoRepository.save(a);
		anotacaoRepository.save(a2);
		anotacaoRepository.save(a3);
		etiquetaRepository.save(e);
		etiquetaRepository.save(e2);
		etiquetaRepository.save(e3);
		itemRepository.save(i);
		itemRepository.save(i2); 
		
	}

}

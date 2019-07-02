package br.ufjf.dcc193.tbr3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.ufjf.dcc193.tbr3.model.Anotacao;
import br.ufjf.dcc193.tbr3.model.Etiqueta;
import br.ufjf.dcc193.tbr3.model.Item;
import br.ufjf.dcc193.tbr3.model.Usuario;
import br.ufjf.dcc193.tbr3.model.Vinculo;
import br.ufjf.dcc193.tbr3.repository.AnotacaoRepository;
import br.ufjf.dcc193.tbr3.repository.EtiquetaRepository;
import br.ufjf.dcc193.tbr3.repository.ItemRepository;
import br.ufjf.dcc193.tbr3.repository.UsuarioRepository;


@SpringBootApplication
public class DemoApplication {

	@Autowired
	public static UsuarioRepository usuarioRepository;
	@Autowired
	public static ItemRepository itemRepository;
	@Autowired
	public static EtiquetaRepository etiquetaRepository;
	@Autowired
	public static AnotacaoRepository anotacaoRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
		popularDadosPrincipais();
	}

	public static void popularDadosPrincipais(){
		Usuario u = new Usuario("Usuário 1","descrição usuário 1","mail@mail.com","1234");
		
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

		i.addAnotacao(a);
		i.addEtiqueta(e);
		i.addEtiqueta(e2);

		i2.addAnotacao(a2);
		i2.addEtiqueta(e);
		i2.addEtiqueta(e2);
		
		usuarioRepository.save(u);
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

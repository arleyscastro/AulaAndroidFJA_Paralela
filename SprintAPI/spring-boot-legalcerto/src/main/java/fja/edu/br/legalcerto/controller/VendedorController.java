package fja.edu.br.legalcerto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fja.edu.br.legalcerto.model.Vendedor;
import fja.edu.br.legalcerto.servico.VendedorServico;

@RestController
@RequestMapping("/vendedor")
public class VendedorController {

	@Autowired
	private VendedorServico servico;
	
	@GetMapping("todos")
	public List<Vendedor> boscaTodos(){
		return servico.findAll();
	}
	
	@GetMapping("/buscaporid/{id}")
	public Vendedor boscaPorId(@PathVariable("id") long id) {
		return servico.findById(id);
	}
	
	@PostMapping("criar")
	public Vendedor criarNovoVendedor(Vendedor vend) {
		return servico.create(vend);
	}
	
}

package fja.edu.br.legalcerto.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fja.edu.br.legalcerto.model.Vendedor;
import fja.edu.br.legalcerto.repositorio.VendedorRepositorio;

@Service
public class VendedorServico {
	
	@Autowired
	VendedorRepositorio repositorio;
	
	public Vendedor create(Vendedor vendedor) {		
		return repositorio.save(vendedor);
	}
	
	public List<Vendedor> findAll(){
		return repositorio.findAll();
	}
	
	public Vendedor findById(long id) {
		return repositorio.findById(id).orElseThrow(null);
	}
	
	public void delete(long id) {
		repositorio.deleteById(id);
	}
	
	public Vendedor update(Vendedor vendedor) {
		Vendedor vend = findById( vendedor.getIdvendedor() );
		
		vend.setNome(vendedor.getNome());
		vend.setComissao(vendedor.getComissao() );
		
		return repositorio.save(vend);
		
	}

}

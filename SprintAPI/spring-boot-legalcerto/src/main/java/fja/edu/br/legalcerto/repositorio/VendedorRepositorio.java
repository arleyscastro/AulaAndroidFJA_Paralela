package fja.edu.br.legalcerto.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fja.edu.br.legalcerto.model.Vendedor;

@Repository
public interface VendedorRepositorio extends JpaRepository<Vendedor, Long> {

}

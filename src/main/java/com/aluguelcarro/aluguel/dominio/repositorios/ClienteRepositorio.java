package com.aluguelcarro.aluguel.dominio.repositorios;

import com.aluguelcarro.aluguel.dominio.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepositorio extends JpaRepository<Cliente, Long> {
}

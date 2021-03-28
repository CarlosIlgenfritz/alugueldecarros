package com.aluguelcarro.aluguel.dominio.repositorios;

import com.aluguelcarro.aluguel.dominio.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AluguelRepositorio extends JpaRepository<Aluguel, Long> {
}

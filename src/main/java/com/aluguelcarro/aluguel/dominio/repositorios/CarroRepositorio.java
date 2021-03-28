package com.aluguelcarro.aluguel.dominio.repositorios;

import com.aluguelcarro.aluguel.dominio.Carro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarroRepositorio extends JpaRepository<Carro, Long> {
}

package com.hubfintech.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hubfintech.app.entities.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
	

}

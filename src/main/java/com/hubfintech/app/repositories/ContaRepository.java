package com.hubfintech.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hubfintech.app.entities.Conta;

public interface ContaRepository extends JpaRepository<Conta, Long> {
	

}

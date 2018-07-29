package com.hubfintech.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.hubfintech.app.entities.Historico;

public interface HistoricoRepository extends JpaRepository<Historico, Long> {
	

}

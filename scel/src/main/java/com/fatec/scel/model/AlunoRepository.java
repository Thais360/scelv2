package com.fatec.scel.model;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends CrudRepository<Aluno, Long> {
		
	@Query("SELECT l FROM Aluno l WHERE l.ra = :RA")
    public Aluno findByRA(@Param("RA") String RA);

	public void Salvar(@Valid Aluno aluno);
		
}

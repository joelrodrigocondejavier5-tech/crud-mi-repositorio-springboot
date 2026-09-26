package com.example.sis414recetas.Repositories;

import com.example.sis414recetas.Models.PacienteModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<PacienteModel, Long> {
}
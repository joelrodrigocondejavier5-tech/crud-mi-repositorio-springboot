package com.example.sis414recetas.Controllers;

import com.example.sis414recetas.Models.PacienteModel;
import com.example.sis414recetas.Repositories.PacienteRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    private final PacienteRepository pacienteRepository;

    public PacienteController(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    // Obtener todos los pacientes
    @GetMapping
    public List<PacienteModel> obtenerPacientes() {
        return pacienteRepository.findAll();
    }

    // Obtener un paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<PacienteModel> obtenerPacientePorId(@PathVariable Long id) {

        return pacienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // Crear paciente
    @PostMapping
    public PacienteModel guardarPaciente(@RequestBody PacienteModel paciente) {
        return pacienteRepository.save(paciente);
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<PacienteModel> actualizarPaciente(
            @PathVariable Long id,
            @RequestBody PacienteModel datosPaciente) {

        return pacienteRepository.findById(id)
                .map(paciente -> {

                    paciente.setNombre(datosPaciente.getNombre());
                    paciente.setCi(datosPaciente.getCi());
                    paciente.setEdad(datosPaciente.getEdad());
                    paciente.setMotivoConsulta(datosPaciente.getMotivoConsulta());

                    return ResponseEntity.ok(
                            pacienteRepository.save(paciente)
                    );
                })
                .orElse(ResponseEntity.notFound().build());
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPaciente(@PathVariable Long id) {

        if (!pacienteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        pacienteRepository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
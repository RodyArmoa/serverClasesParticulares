package ClasesParticulares.Controller;

import ClasesParticulares.Model.Profesor;
import ClasesParticulares.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/profesores")
public class ProfesorController {

    @Autowired
    private ProfesorRepository profesorRepository;

    // Obtener todos los profesores
    @GetMapping
    public List<Profesor> obtenerTodosLosProfesores() {
        return profesorRepository.findAll();
    }

    // Crear un nuevo profesor
    @PostMapping
    public Profesor crearProfesor(@RequestBody Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    // Obtener profesor por ID
    @GetMapping("/{id}")
    public Profesor obtenerProfesorPorId(@PathVariable Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    // Actualizar profesor
    @PutMapping("/{id}")
    public Profesor actualizarProfesor(@PathVariable Long id, @RequestBody Profesor profesorActualizado) {
        profesorActualizado.setId(id);
        return profesorRepository.save(profesorActualizado);
    }

    // Eliminar profesor
    @DeleteMapping("/{id}")
    public void eliminarProfesor(@PathVariable Long id) {
        profesorRepository.deleteById(id);
    }
}

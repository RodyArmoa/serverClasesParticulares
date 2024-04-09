package ClasesParticulares.Service;

import ClasesParticulares.Model.Profesor;
import ClasesParticulares.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfesorService {

    @Autowired
    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerTodosLosProfesores() {
        return profesorRepository.findAll();
    }

    public Profesor crearProfesor(Profesor profesor) {
        return profesorRepository.save(profesor);
    }

    public Profesor obtenerProfesorPorId(Long id) {
        return profesorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
    }

    public Profesor actualizarProfesor(Long id, Profesor profesorActualizado) {
        profesorActualizado.setId(id);
        return profesorRepository.save(profesorActualizado);
    }

    public void eliminarProfesor(Long id) {
        profesorRepository.deleteById(id);
    }
}

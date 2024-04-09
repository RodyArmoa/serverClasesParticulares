package ClasesParticulares.Service;

import ClasesParticulares.Model.Alumno;
import ClasesParticulares.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlumnoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    public Alumno crearAlumno(Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    public Alumno obtenerAlumnoPorId(Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    public Alumno actualizarAlumno(Long id, Alumno alumnoActualizado) {
        alumnoActualizado.setId(id);
        return alumnoRepository.save(alumnoActualizado);
    }

    public void eliminarAlumno(Long id) {
        alumnoRepository.deleteById(id);
    }

    // Método para buscar un alumno por su correo electrónico
    public Optional<Alumno> buscarAlumnoPorEmail(String email) {
        return alumnoRepository.findByEmail(email);
    }
}
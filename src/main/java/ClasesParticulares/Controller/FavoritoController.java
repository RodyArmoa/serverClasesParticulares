package ClasesParticulares.Controller;

import ClasesParticulares.Model.Alumno;
import ClasesParticulares.Model.Favorito;
import ClasesParticulares.Model.Profesor;
import ClasesParticulares.Repository.AlumnoRepository;
import ClasesParticulares.Repository.FavoritoRepository;
import ClasesParticulares.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/alumnos/{id}/favoritos")
public class FavoritoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private ProfesorRepository profesorRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    // Obtener todos los profesores favoritos de un alumno
    @GetMapping
    public List<Profesor> obtenerProfesoresFavoritos(@PathVariable Long id) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        return favoritoRepository.findByAlumno(alumno).stream()
                .map(Favorito::getProfesor)
                .collect(Collectors.toList());
    }




    // Agregar un profesor como favorito
    @PostMapping("/{profesorId}")
    public void agregarProfesorFavorito(@PathVariable Long id, @PathVariable Long profesorId) {
        Alumno alumno = alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Favorito favorito = new Favorito();
        favorito.setAlumno(alumno);
        favorito.setProfesor(profesor);
        favoritoRepository.save(favorito);
    }
}

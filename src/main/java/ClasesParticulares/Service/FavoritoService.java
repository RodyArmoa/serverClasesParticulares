package ClasesParticulares.Service;

import ClasesParticulares.Model.Alumno;
import ClasesParticulares.Model.Favorito;
import ClasesParticulares.Model.Profesor;
import ClasesParticulares.Repository.AlumnoRepository;
import ClasesParticulares.Repository.FavoritoRepository;
import ClasesParticulares.Repository.ProfesorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

// Otros imports necesarios

@Service
public class FavoritoService {

    @Autowired
    private AlumnoRepository alumnoRepository;

    @Autowired
    private FavoritoRepository favoritoRepository;

    @Autowired

    private ProfesorRepository profesorRepository;

    public List<Profesor> obtenerProfesoresFavoritos(Long idAlumno) {
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        List<Favorito> favoritos = favoritoRepository.findByAlumno(alumno);

        return favoritos.stream()
                .map(Favorito::getProfesor)
                .collect(Collectors.toList());
    }

    public void agregarProfesorFavorito(Long idAlumno, Long profesorId) {
        Alumno alumno = alumnoRepository.findById(idAlumno)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
        Profesor profesor = profesorRepository.findById(profesorId)
                .orElseThrow(() -> new RuntimeException("Profesor no encontrado"));
        Favorito favorito = new Favorito();
        favorito.setAlumno(alumno);
        favorito.setProfesor(profesor);
        favoritoRepository.save(favorito);
    }
}

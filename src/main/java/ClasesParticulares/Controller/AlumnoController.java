package ClasesParticulares.Controller;

import ClasesParticulares.Model.Alumno;
import ClasesParticulares.Repository.AlumnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alumnos")
public class AlumnoController {

    @Autowired
    private AlumnoRepository alumnoRepository;

    // Obtener todos los alumnos
    @GetMapping
    public List<Alumno> obtenerTodosLosAlumnos() {
        return alumnoRepository.findAll();
    }

    // Crear un nuevo alumno
    @PostMapping
    public Alumno crearAlumno(@RequestBody Alumno alumno) {
        return alumnoRepository.save(alumno);
    }

    // Obtener alumno por ID
    @GetMapping("/{id}")
    public Alumno obtenerAlumnoPorId(@PathVariable Long id) {
        return alumnoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));
    }

    // Actualizar alumno
    @PutMapping("/{id}")
    public Alumno actualizarAlumno(@PathVariable Long id, @RequestBody Alumno alumnoActualizado) {
        alumnoActualizado.setId(id);
        return alumnoRepository.save(alumnoActualizado);
    }

    // Eliminar alumno
    @DeleteMapping("/{id}")
    public void eliminarAlumno(@PathVariable Long id) {
        alumnoRepository.deleteById(id);
    }


    // Iniciar sesión de alumno



    @PostMapping("/login")
    public Long iniciarSesion(@RequestBody AlumnoLoginRequest request) {
        // Buscar el alumno por su correo electrónico
        Alumno alumno = alumnoRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Verificar que la contraseña coincida
        if (!alumno.getContraseña().equals(request.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Devolver el ID del alumno si las credenciales son válidas
        return alumno.getId();
    }

    public static class AlumnoLoginRequest {
        private String email;
        private String contraseña;

        // Constructor sin parámetros
        public AlumnoLoginRequest() {
        }

        // Constructor con parámetros para inicializar los campos
        public AlumnoLoginRequest(String email, String contraseña) {
            this.email = email;
            this.contraseña = contraseña;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
    }

}













  /*  @PostMapping("/login")
    public Alumno iniciarSesion(@RequestBody AlumnoLoginRequest request) {
        // Buscar el alumno por su correo electrónico
        Alumno alumno = alumnoRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new RuntimeException("Alumno no encontrado"));

        // Verificar que la contraseña coincida
        if (!alumno.getContraseña().equals(request.getContraseña())) {
            throw new RuntimeException("Contraseña incorrecta");
        }

        // Devolver el alumno si las credenciales son válidas
        return alumno;
    }

    public static class AlumnoLoginRequest {
        private String email;
        private String contraseña;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
    }*/



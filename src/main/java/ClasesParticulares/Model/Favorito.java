package ClasesParticulares.Model;

import jakarta.persistence.*;

@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ID_Alumno")
    private Alumno alumno;

    @ManyToOne
    @JoinColumn(name = "ID_Profesor")
    private Profesor profesor;


    public Favorito() {
    }

    public Favorito(Long id, Alumno alumno, Profesor profesor) {
        this.id = id;
        this.alumno = alumno;
        this.profesor = profesor;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }
}
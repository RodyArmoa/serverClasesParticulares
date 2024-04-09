package ClasesParticulares.Repository;

import ClasesParticulares.Model.Alumno;
import ClasesParticulares.Model.Favorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoritoRepository extends JpaRepository<Favorito, Long> {
    @Query("SELECT f FROM Favorito f WHERE f.alumno = :alumno")
    List<Favorito> findByAlumno(Alumno alumno);
}
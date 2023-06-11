/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.controllers;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.examen1juandaviddelgadomunoz.converters.GrupoConverter;
import org.uv.examen1juandaviddelgadomunoz.models.Alumno;
import org.uv.examen1juandaviddelgadomunoz.models.DTOGrupo;
import org.uv.examen1juandaviddelgadomunoz.models.Grupo;
import org.uv.examen1juandaviddelgadomunoz.models.Materia;
import org.uv.examen1juandaviddelgadomunoz.repository.RepositoryAlumno;
import org.uv.examen1juandaviddelgadomunoz.repository.RepositoryGrupo;
import org.uv.examen1juandaviddelgadomunoz.repository.RepositoryMateria;
/**
 *
 * @author juan
 */
@RestController
@RequestMapping("/examen/grupos")
public class GrupoController {
    @Autowired
    private RepositoryGrupo repositoryGrupo;
    @Autowired
    private RepositoryAlumno repositoryAlumno;
    @Autowired
    private RepositoryMateria repositoryMateria;
    
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("simpleJPA");
    EntityManager entityManager = emf.createEntityManager();
    
    private GrupoConverter converter=new GrupoConverter();
    
     
    @GetMapping("/")
    public List<DTOGrupo> getGrupos(){
        return converter.listconvertDTO((List<Grupo>) repositoryGrupo.findAll());
    }
     
    @GetMapping("/{clave_alumno}")
    public List<DTOGrupo> getGruposbyAlumno(@PathVariable("clave_alumno") Long claveAlumno) {
        String jpql = "SELECT g FROM Grupo g WHERE g.alumno.clave = :clave";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("clave", claveAlumno);
        return query.getResultList();
    }
    
    @PostMapping("/")
    public DTOGrupo saveGrupo(@RequestBody DTOGrupo grupo) {
        Optional<Alumno> a=repositoryAlumno.findById(grupo.getClaveAlumno());
        Alumno alumno=a.map(alum->{
            Alumno al=new Alumno();
            al.setId(alum.getId());
            al.setNombre(alum.getNombre());
            al.setDireccion(alum.getDireccion());
            al.setTelefono(alum.getTelefono());
            return al;
        }).orElse(null);
        Optional<Materia> m=repositoryMateria.findById(grupo.getClaveMateria());
        Materia materia=m.map(mat->{
            Materia ma=new Materia();
            ma.setID(mat.getId());
            ma.setNombreMateria(mat.getNombreMateria());
            return ma;
        }).orElse(null);
        Grupo g=converter.convertEntityGrupo(grupo, alumno, materia);
        g=repositoryGrupo.save(g);
        return converter.convertDTO(g);
    }
    
    @PutMapping("/{clave_alumno}/{clave_materia}")
    public Object updateGrupo(@PathVariable("clave_alumno") Long claveAlumno,
                                @PathVariable("clave_materia") Long claveMateria,
                                @RequestBody DTOGrupo grupo){
        entityManager.getTransaction().begin();
        String jpsql="UPDATE Grupo g SET g.nombre_grupo = :nombre_grupo WHERE g.alumno.clave = :clave_alumno AND g.materia.clave_materia = :clave_materia";
        Query query=entityManager.createQuery(jpsql);
        query.setParameter("nombre_grupo", grupo.getNombre());
        query.setParameter("clave_alumno", claveAlumno);
        query.setParameter("clave_materia", claveMateria);
        query.executeUpdate();
        entityManager.getTransaction().commit();

        jpsql="SELECT g FROM Grupo g WHERE g.alumno.clave=:clave_alumno AND g.materia.clave_materia=:clave_materia";
        query=entityManager.createQuery(jpsql);
        query.setParameter("clave_alumno", claveAlumno);
        query.setParameter("clave_materia", claveMateria);
        return query.getSingleResult();
    }
    
    @DeleteMapping("/{clave_alumno}/{clave_materia}")
    public void deleteGrupo(@PathVariable("clave_alumno") Long claveAlumno,
                            @PathVariable("clave_materia") Long claveMateria) {
        entityManager.getTransaction().begin();
        String jpsql="DELETE Grupo g WHERE g.alumno.clave=:alumno AND g.materia.clave_materia=:materia";
        Query query=entityManager.createQuery(jpsql);
        query.setParameter("alumno", claveAlumno);
        query.setParameter("materia", claveMateria);
        query.executeUpdate();
        entityManager.getTransaction().commit();
    } 
}

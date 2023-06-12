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
    
    
    private GrupoConverter converter=new GrupoConverter();
    
     
    @GetMapping("/")
    public List<DTOGrupo> getGrupos(){
        return converter.listconvertDTO((List<Grupo>) repositoryGrupo.findAll());
    }
     
    @GetMapping("/{clave_alumno}")
    public List<DTOGrupo> getGruposbyAlumno(@PathVariable("clave_alumno") Long claveAlumno) {
        EntityManager entityManager = emf.createEntityManager();
        String jpql = "SELECT g FROM Grupo g WHERE g.alumno.clave = :clave";
        Query query = entityManager.createQuery(jpql);
        query.setParameter("clave", claveAlumno);
        List<DTOGrupo> lista=query.getResultList();
        entityManager.close();
        return lista;
    }
    
    @PostMapping("/")
    public DTOGrupo saveGrupo(@RequestBody DTOGrupo grupo) {
        Alumno a=repositoryAlumno.findById(grupo.getClaveAlumno()).orElseThrow(() -> new RuntimeException("Alumno no encontrado con ID: " + grupo.getClaveAlumno()));
        
        Materia m=repositoryMateria.findById(grupo.getClaveMateria()).orElseThrow(() -> new RuntimeException("Materia no encontrada con ID: " + grupo.getClaveMateria()));
        
        Grupo g=converter.convertEntityGrupo(grupo, a, m);
        g=repositoryGrupo.save(g);
        return converter.convertDTO(g);
    }
    
    @PutMapping("/{clave_alumno}/{clave_materia}")
    public Object updateGrupo(@PathVariable("clave_alumno") Long claveAlumno,
                                @PathVariable("clave_materia") Long claveMateria,
                                @RequestBody DTOGrupo grupo){
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        String jpsql="UPDATE Grupo g SET g.nombreGrupo = :nombre_grupo WHERE g.alumno.clave = :clave_alumno AND g.materia.claveMateria = :clave_materia";
        Query query=entityManager.createQuery(jpsql);
        query.setParameter("nombre_grupo", grupo.getNombre());
        query.setParameter("clave_alumno", claveAlumno);
        query.setParameter("clave_materia", claveMateria);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        
  
        jpsql="SELECT g FROM Grupo g WHERE g.alumno.clave=:clave_alumno AND g.materia.claveMateria=:clave_materia";
        query=entityManager.createQuery(jpsql);
        query.setParameter("clave_alumno", claveAlumno);
        query.setParameter("clave_materia", claveMateria);
        Object a = query.getSingleResult();
        entityManager.close();
        return a;
    }
    
    @DeleteMapping("/{clave_alumno}/{clave_materia}")
    public void deleteGrupo(@PathVariable("clave_alumno") Long claveAlumno,
                            @PathVariable("clave_materia") Long claveMateria) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        String jpsql="DELETE Grupo g WHERE g.alumno.clave=:alumno AND g.materia.claveMateria=:materia";
        Query query=entityManager.createQuery(jpsql);
        query.setParameter("alumno", claveAlumno);
        query.setParameter("materia", claveMateria);
        query.executeUpdate();
        entityManager.getTransaction().commit();
        entityManager.close();
        
    } 
}

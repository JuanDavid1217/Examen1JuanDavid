/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.uv.examen1juandaviddelgadomunoz.models.Alumno;
import org.uv.examen1juandaviddelgadomunoz.models.DTOAlumno;
import org.uv.examen1juandaviddelgadomunoz.repository.RepositoryAlumno;
import org.uv.examen1juandaviddelgadomunoz.converters.AlumnoConverter;
/**
 *
 * @author juan
 */

@RestController
@RequestMapping("/examen/alumnos")
public class AlumnoController {
    @Autowired
    private RepositoryAlumno repositoryAlumno;

    private AlumnoConverter converter=new AlumnoConverter();
    
    private String alumnoexception= "Alumno no encontrado con ID: ";
     
    @GetMapping("/")
    public List<DTOAlumno> getAlumnos(){
        return converter.listconvertDTO((List<Alumno>) repositoryAlumno.findAll());
    }
     
    @GetMapping("/{id}")
    public DTOAlumno getAlumno(@PathVariable("id") Long id) {
        Alumno a=repositoryAlumno.findById(id).orElseThrow(() -> new RuntimeException(alumnoexception + id));
        
        return converter.convertDTO(a);
         
    }
    
    @PostMapping("/")
    public DTOAlumno saveAlumno(@RequestBody DTOAlumno alumno) {
        Alumno a=converter.convertEntity(alumno);
        a=repositoryAlumno.save(a);
        return converter.convertDTO(a);
    }
    
    @PutMapping("/{id}")
    public DTOAlumno updateAlumno(@PathVariable("id") Long id, @RequestBody DTOAlumno alumno){
        Alumno a=repositoryAlumno.findById(id).orElseThrow(() -> new RuntimeException(alumnoexception + id));
        Alumno alum=converter.convertEntity(alumno);
        alum.setId(a.getId());
        alum=repositoryAlumno.save(alum);
        return converter.convertDTO(alum);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable("id") Long id) {
        Alumno a=repositoryAlumno.findById(id).orElseThrow(() -> new RuntimeException(alumnoexception + id));
        if(a!=null){
            repositoryAlumno.deleteById(id);
        }
    }
     
}

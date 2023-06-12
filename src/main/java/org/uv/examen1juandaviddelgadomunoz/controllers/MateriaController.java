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
import org.uv.examen1juandaviddelgadomunoz.repository.RepositoryMateria;
import org.uv.examen1juandaviddelgadomunoz.converters.MateriaConverter;
import org.uv.examen1juandaviddelgadomunoz.models.DTOMateria;
import org.uv.examen1juandaviddelgadomunoz.models.Materia;
/**
 *
 * @author juan
 */
@RestController
@RequestMapping("/examen/materias")
public class MateriaController {
    @Autowired
    private RepositoryMateria repositoryMateria;
    
    private MateriaConverter converter=new MateriaConverter();
    
    private String materiaexception= "Materia no encontrada con ID: ";
    
    @GetMapping("/")
    public List<DTOMateria> getMateiras(){
        return converter.listconvertDTO((List<Materia>) repositoryMateria.findAll());
    }
     
    @GetMapping("/{id}")
    public DTOMateria getMateria(@PathVariable("id") Long id) {
        Materia m=repositoryMateria.findById(id).orElseThrow(() -> new RuntimeException(materiaexception + id));
        
        return converter.convertDTO(m); 
         
    }
    
    @PostMapping("/")
    public DTOMateria saveMateria(@RequestBody DTOMateria materia) {
        Materia m=converter.convertEntity(materia);
        m=repositoryMateria.save(m);
        return converter.convertDTO(m);
    }
    
    @PutMapping("/{id}")
    public DTOMateria updateMateria(@PathVariable("id") Long id, @RequestBody DTOMateria materia){
        Materia m=repositoryMateria.findById(id).orElseThrow(() -> new RuntimeException(materiaexception + id));
        Materia ma=converter.convertEntity(materia);
        ma.setID(m.getId());
        ma=repositoryMateria.save(ma);
        return converter.convertDTO(ma);
    }
    
    @DeleteMapping("/{id}")
    public void deleteAlumno(@PathVariable("id") Long id) {
        Materia m=repositoryMateria.findById(id).orElseThrow(() -> new RuntimeException(materiaexception + id));
        
        if(m!=null){
            repositoryMateria.deleteById(id);
        }
    } 
}

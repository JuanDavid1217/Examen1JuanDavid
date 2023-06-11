/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.models;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author juan
 */
@Entity
@Table(name = "grupo")
public class Grupo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    
    @ManyToOne()
    @JoinColumn(name="clave_alumno")
    private Alumno alumno;
    
    @ManyToOne()
    @JoinColumn(name="clave_materia")
    private Materia materia;
   
    @Column(name="nombre_grupo")
    String nombreGrupo;
    
    
    public Alumno getAlumno() {
        return alumno;
    }

    public void setAlumno(Alumno alumno) {
        this.alumno = alumno;
    }
    
    public Materia getMateria(){
        return materia;
    }
    
    public void setMateria(Materia materia){
        this.materia = materia;
    }

    public String getNombre() {
        return nombreGrupo;
    }


    public void setNombre(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }
    

}

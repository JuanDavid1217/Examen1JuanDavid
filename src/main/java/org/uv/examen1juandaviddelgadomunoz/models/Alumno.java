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
import javax.persistence.Table;

/**
 *
 * @author wbpat
 */
@Entity
@Table(name = "alumno")
public class Alumno {
   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "id")
   private long clave;

    @Column(name="nombre_alumno")
    String nombreAlumno;
    
    @Column(name="direccion")
    String direccion;
    
    @Column(name="telefono")
    String telefono;
    
    public long getId() {
    return clave;
  }
    
     public void setId(long clave) {
    this.clave = clave;
  }
     

    public String getNombre() {
        return nombreAlumno;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setNombre(String nombreAlumno) {
        this.nombreAlumno = nombreAlumno;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}
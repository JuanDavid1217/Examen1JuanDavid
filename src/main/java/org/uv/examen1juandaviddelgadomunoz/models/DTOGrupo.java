/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.models;


/**
 *
 * @author juan
 */
public class DTOGrupo {
    
   private long claveMateria;
   private long claveAlumno;
   private String nombreGrupo;
   
    
   public long getClaveMateria(){
    return claveMateria;
   }
    
   public void setClaveMateria(long claveMateria) {
    this.claveMateria=claveMateria;
   }
     

   public String getNombre() {
        return nombreGrupo;
   }


   public void setNombre(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
   }
    
   public long getClaveAlumno() {
        return claveAlumno;
   }


   public void setClaveAlumno(long claveAlumno) {
        this.claveAlumno = claveAlumno;
   }

}



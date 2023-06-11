/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.converters;

import java.util.List;
import java.util.stream.Collectors;
import org.uv.examen1juandaviddelgadomunoz.models.Alumno;
import org.uv.examen1juandaviddelgadomunoz.models.DTOAlumno;

/**
 *
 * @author juan
 */
public class AlumnoConverter implements Converter <Alumno, DTOAlumno>{

    @Override
    public DTOAlumno convertDTO(Alumno t) {
        DTOAlumno dto=new DTOAlumno();
        dto.setClave(t.getId());
        dto.setNombre(t.getNombre());
        dto.setDireccion(t.getDireccion());
        dto.setTelefono(t.getTelefono());
        return dto;
    }

    @Override
    public List<DTOAlumno> listconvertDTO(List<Alumno> resourcelist) {
        return resourcelist.stream()
                .map(this::convertDTO)
                .collect(Collectors.toList());
    }
    
    public Alumno convertEntity(DTOAlumno s){
        Alumno a=new Alumno();
        a.setNombre(s.getNombre());
        a.setDireccion(s.getDireccion());
        a.setTelefono(s.getTelefono());
        return a;
    }
    
    public List<Alumno> listconvertEntity(List<DTOAlumno> resourcelist) {
        return resourcelist.stream()
                .map(this::convertEntity)
                .collect(Collectors.toList());
    }
    
    
}

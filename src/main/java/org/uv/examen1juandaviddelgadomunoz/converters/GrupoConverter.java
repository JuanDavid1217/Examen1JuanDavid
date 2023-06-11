/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.converters;

import java.util.List;
import java.util.stream.Collectors;
import org.uv.examen1juandaviddelgadomunoz.models.Alumno;
import org.uv.examen1juandaviddelgadomunoz.models.DTOGrupo;
import org.uv.examen1juandaviddelgadomunoz.models.Grupo;
import org.uv.examen1juandaviddelgadomunoz.models.Materia;

/**
 *
 * @author juan
 */
public class GrupoConverter implements Converter<Grupo, DTOGrupo>{
    
    
    @Override
    public DTOGrupo convertDTO(Grupo s) {
        DTOGrupo dto=new DTOGrupo();
        dto.setClaveAlumno(s.getAlumno().getId());
        dto.setClaveMateria(s.getMateria().getId());
        dto.setNombre(s.getNombre());
        return dto;
    }

    @Override
    public List<DTOGrupo> listconvertDTO(List<Grupo> resourcelist) {
        return resourcelist.stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public Grupo convertEntityGrupo(DTOGrupo g, Alumno a, Materia m){
        Grupo grupo=new Grupo();
        grupo.setNombre(g.getNombre());
        grupo.setAlumno(a);
        grupo.setMateria(m);
        return grupo;
    }
    
}

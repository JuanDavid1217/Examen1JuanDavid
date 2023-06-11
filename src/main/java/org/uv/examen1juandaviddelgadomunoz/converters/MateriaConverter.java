/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.converters;

import java.util.List;
import java.util.stream.Collectors;
import org.uv.examen1juandaviddelgadomunoz.models.DTOMateria;
import org.uv.examen1juandaviddelgadomunoz.models.Materia;

/**
 *
 * @author juan
 */
public class MateriaConverter implements Converter <Materia, DTOMateria> {

    @Override
    public DTOMateria convertDTO(Materia s) {
        DTOMateria dto=new DTOMateria();
        dto.setClaveMateria(s.getId());
        dto.setNombreMateria(s.getNombreMateria());
        return dto;
    }

    @Override
    public List<DTOMateria> listconvertDTO(List<Materia> resourcelist) {
        return resourcelist.stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public Materia convertEntity(DTOMateria t) {
        Materia materia=new Materia();
        materia.setNombreMateria(t.getNombreMateria());
        return materia;
    }

    public List<Materia> listconvertEntity(List<DTOMateria> resourcelist) {
        return resourcelist.stream().map(this::convertEntity).collect(Collectors.toList());
    }
    
}

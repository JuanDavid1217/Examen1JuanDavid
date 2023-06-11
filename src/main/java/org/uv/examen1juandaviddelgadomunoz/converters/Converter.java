/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.uv.examen1juandaviddelgadomunoz.converters;

import java.util.List;

/**
 *
 * @author juan
 */
public interface Converter <S, T> {
    T convertDTO(S s);
    List<T> listconvertDTO (List<S> resourcelist);
}

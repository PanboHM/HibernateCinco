/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.jesushm.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author jesus
 */
@Entity
public class Libro implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLibro;
    @Column(length = 60, nullable = false)
    private String titulo;

    public Libro() {
    }

    public Libro(Long idLibro, String titulo) {
        this.idLibro = idLibro;
        this.titulo = titulo;
    }

    public Long getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(Long idLibro) {
        this.idLibro = idLibro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

}

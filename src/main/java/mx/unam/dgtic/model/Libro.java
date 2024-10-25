package mx.unam.dgtic.model;

import java.util.Objects;

public class Libro {
    private Integer id;
    private String titulo;
    private String autor;

    private Libro(){

    }

    public Libro(Integer id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(getId(), libro.getId()) && Objects.equals(getTitulo(), libro.getTitulo()) && Objects.equals(getAutor(), libro.getAutor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitulo(), getAutor());
    }

    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                '}';
    }
}

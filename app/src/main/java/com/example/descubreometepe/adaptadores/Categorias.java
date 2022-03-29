package com.example.descubreometepe.adaptadores;

public class Categorias {

    public int idImagen;
    public String Categoria;

    public Categorias(int idImagen, String nombre) {
        this.idImagen = idImagen;
        Categoria = nombre;
    }

    public int getIdImagen() {
        return idImagen;
    }

    public void setIdImagen(int idImagen) {
        this.idImagen = idImagen;
    }

    public String getCategoria() {
        return Categoria;
    }

    public void setCategoria(String categoria) {
        Categoria = categoria;
    }
}

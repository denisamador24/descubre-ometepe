package com.example.descubreometepe.adaptadores;

public class Lugares {


    public String NombreLugar;
    public String Comarca;
    public String direccion;
    public String descripcion;
    public String telefono;
    public String horario;
    public String Imagen;
    public int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Lugares(String nombreLugar, String comarca, String direccion, String descripcion, String telefono, String horario, String imagen, int id) {
        NombreLugar = nombreLugar;
        Comarca = comarca;
        this.direccion = direccion;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.horario = horario;
        Imagen = imagen;
        this.id = id;
    }

    public String getNombreLugar() {
        return NombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        NombreLugar = nombreLugar;
    }

    public String getComarca() {
        return Comarca;
    }

    public void setComarca(String comarca) {
        Comarca = comarca;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public String getImagen() {
        return Imagen;
    }

    public void setImagen(String imagen) {
        Imagen = imagen;
    }

}

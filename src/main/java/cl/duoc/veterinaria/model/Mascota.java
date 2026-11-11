package cl.duoc.veterinaria.model;

import java.sql.Date;
import java.time.LocalDate;

public class Mascota {
    private int chip;
    private String nombre;
    private LocalDate fechaNacimiento;

    public Mascota() {
    }

    public Mascota(int chip, String nombre, LocalDate fechaNacimiento) {
        setChip(chip);
        this.nombre = nombre;
        this.fechaNacimiento = fechaNacimiento;
    }

    public int getChip() {
        return chip;
    }

    public void setChip(int chip) {
        if(chip<=0){
            throw new IllegalArgumentException("El chip debe ser positivo");
        }
        this.chip = chip;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    @Override
    public String toString() {
        return "Mascota{" +
                "chip=" + chip +
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                '}';
    }
}

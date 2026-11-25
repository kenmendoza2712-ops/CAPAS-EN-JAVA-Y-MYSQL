/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author 
 */
public class Alumno extends Persona{
    
    private String _carnetEstudiante;

    public Alumno(String _carnetEstudiante, String _cedula, String _nombre, String _apellido1, String _apellido2, String _fechaN, String _correo, int _tipoP) {
        super(_cedula, _nombre, _apellido1, _apellido2, _fechaN, _correo, _tipoP);
        this._carnetEstudiante = _carnetEstudiante;
    }
    
    public Alumno()
    {
        this._carnetEstudiante = "";
    }

    public String getCarnetEstudiante() {
        return _carnetEstudiante;
    }

    public void setCarnetEstudiante(String _carnetEstudiante) {
        this._carnetEstudiante = _carnetEstudiante;
    }
    
    
}

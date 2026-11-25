/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaLogica;
import CapaDatos.*;
import Modelo.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 
 */
public class AlumnoLog {
    
    AlumnoDA ALD = new AlumnoDA();
    
    
    public void Agregar(String _carnetEstudiante, String _cedula, String _nombre, String _apellido1, String _apellido2, String _fechaN, String _correo, int _tipoP)
    {
        if (ALD.Agregar(new Alumno( _carnetEstudiante,  _cedula,  _nombre,  _apellido1,  _apellido2,  _fechaN,  _correo,_tipoP))) {
            System.out.println("Dato agregado");
        }
    }
    public void Actualizar(String _carnetEstudiante, String _cedula, String _nombre, String _apellido1, String _apellido2, String _fechaN, String _correo, int _tipoP)
    {
        if (ALD.Actualizar(new Alumno( _carnetEstudiante,  _cedula,  _nombre,  _apellido1,  _apellido2,  _fechaN,  _correo,_tipoP))) {
            System.out.println("Dato Actualizado");
        }
    }
    public void Eliminar(String cedula)
    {
        if (ALD.Eliminar(cedula)) {
            System.out.println("Dato Eliminado");
        }
    }
    public DefaultTableModel Persona(String cedula)
    {
        return ALD.Person(cedula);
    }
    public DefaultTableModel Lista()
    {
        return ALD.Lista();
    }
    
}

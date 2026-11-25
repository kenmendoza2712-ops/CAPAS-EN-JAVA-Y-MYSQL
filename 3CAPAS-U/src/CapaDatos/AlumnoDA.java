/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package CapaDatos;

import Modelo.*;
import java.sql.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author 
 */
public class AlumnoDA implements IPersona<Alumno>{

    Connection cn = new ConnectionDB().getConn();
    
    @Override
    public boolean Agregar(Alumno obj) {
       
    boolean band = false;
        try {
            
            CallableStatement cs = cn.prepareCall("{Call PA_AGREGAR_ALUMN (?,?,?,?,?,?,?,?)}");
            cs.setString(1, obj.getCedula());
            cs.setString(2, obj.getNombre());
            cs.setString(3, obj.getApellido1());
            cs.setString(4, obj.getApellido2());
            cs.setString(5, obj.getFechaN());
            cs.setString(6, obj.getCorreo());
            cs.setString(7, obj.getCarnetEstudiante());
            cs.setInt(8, obj.getTipoP());
            
            if (cs.executeUpdate()>0) 
            {
                band = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al insertar Alumno"+e.getMessage());
        }
    return band;
    }

    @Override
    public boolean Actualizar(Alumno obj) {
     
        boolean band = false;
        try {
            
            CallableStatement cs = cn.prepareCall("{Call PA_ACTUALIZAR_ALUMN (?,?,?,?,?,?,?,?)}");
            cs.setString(1, obj.getCedula());
            cs.setString(2, obj.getNombre());
            cs.setString(3, obj.getApellido1());
            cs.setString(4, obj.getApellido2());
            cs.setString(5, obj.getFechaN());
            cs.setString(6, obj.getCorreo());
            cs.setString(7, obj.getCarnetEstudiante());
            cs.setInt(8, obj.getTipoP());
            
            if (cs.executeUpdate()>0) 
            {
                band = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al actualizar Alumno"+e.getMessage());
        }
    return band;
    
    
    
    }

    @Override
    public boolean Eliminar(String codigo) {
       
       boolean band = false;
        try {
            CallableStatement cs = cn.prepareCall("{Call PA_ELIMINAR_ALUMN(?)}");
            cs.setString(1, codigo);
        if (cs.executeUpdate()>0) 
            {
                band = true;
            }
         } catch (Exception e) {
            System.out.println("Error al eliminar Alumno"+e.getMessage());
        }
    return  band;
    }

    @Override
    public DefaultTableModel Lista() {
       
        DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("Cedula");
        mdl.addColumn("Nombre");
        mdl.addColumn("Apellido1");
        mdl.addColumn("Apellido2");
        mdl.addColumn("FechaN");
        mdl.addColumn("Correo");
        mdl.addColumn("Carnet");
        mdl.addColumn("TipoP");
        
        try {
            CallableStatement cs = cn.prepareCall("{ Call PA_LISTAR_TODOS_ALUMN()}");
            ResultSet rst = cs.executeQuery();
            while(rst.next())
            {
            Object data[] = {
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getString(7),
                rst.getInt(8),   
            };
            mdl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println("Error al traer la lista de alumnos"+e.getLocalizedMessage());
        }
    return mdl;
    }

    @Override
    public DefaultTableModel Person(String codigo) {
       
      DefaultTableModel mdl = new DefaultTableModel();
        mdl.addColumn("Cedula");
        mdl.addColumn("Nombre");
        mdl.addColumn("Apellido1");
        mdl.addColumn("Apellido2");
        mdl.addColumn("FechaN");
        mdl.addColumn("Correo");
        mdl.addColumn("Carnet");
        mdl.addColumn("TipoP");
        
        try {
            CallableStatement cs = cn.prepareCall("{CALL PA_LISTAR_ALUMN (?) }");
            cs.setString(1, codigo);
            ResultSet rst = cs.executeQuery();
            while(rst.next())
            {
            Object data[] = {
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5),
                rst.getString(6),
                rst.getString(7),
                rst.getInt(8),   
            };
            mdl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println("Error al traer el alumno"+e.getLocalizedMessage());
        }
    return mdl;
    
    }
    
    
    
    
}

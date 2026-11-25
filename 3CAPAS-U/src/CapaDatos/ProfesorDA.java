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
public class ProfesorDA implements IPersona<Profesor> {

    Connection cn = new ConnectionDB().getConn();
    
    @Override
    public boolean Agregar(Profesor obj) {
       
    boolean band = false;
        try {
            
            CallableStatement cs = cn.prepareCall("{Call PA_AGREGAR_PROFESOR (?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, obj.getCedula());
            cs.setString(2, obj.getNombre());
            cs.setString(3, obj.getApellido1());
            cs.setString(4, obj.getApellido2());
            cs.setString(5, obj.getFechaN());
            cs.setString(6, obj.getCorreo());
            cs.setString(7, obj.getCarnetP());
            cs.setInt(8, obj.getExperiencia());
            cs.setInt(9, obj.getTipoP());
            
            if (cs.executeUpdate()>0) 
            {
                band = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al insertar Profesor"+e.getMessage());
        }
    return band;
    }

    @Override
    public boolean Actualizar(Profesor obj) {
     
        boolean band = false;
        try {
            
            CallableStatement cs = cn.prepareCall("{Call PA_ACTUALIZAR_PROFESOR (?,?,?,?,?,?,?,?,?)}");
            cs.setString(1, obj.getCedula());
            cs.setString(2, obj.getNombre());
            cs.setString(3, obj.getApellido1());
            cs.setString(4, obj.getApellido2());
            cs.setString(5, obj.getFechaN());
            cs.setString(6, obj.getCorreo());
            cs.setString(7, obj.getCarnetP());
            cs.setInt(8, obj.getExperiencia());
            cs.setInt(9, obj.getTipoP());
            
            if (cs.executeUpdate()>0) 
            {
                band = true;
            }
            
        } catch (Exception e) {
            System.out.println("Error al actualizar Profesor"+e.getMessage());
        }
    return band;
    
    
    
    }

    @Override
    public boolean Eliminar(String codigo) {
       
       boolean band = false;
        try {
            CallableStatement cs = cn.prepareCall("{Call PA_ELIMINAR_PROFESOR(?)}");
            cs.setString(1, codigo);
        if (cs.executeUpdate()>0) 
            {
                band = true;
            }
         } catch (Exception e) {
            System.out.println("Error al eliminar profesor"+e.getMessage());
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
        mdl.addColumn("Carnet Profesor");
        mdl.addColumn("ExpA");
        mdl.addColumn("Experiencia");
        mdl.addColumn("TipoP");
        
        try {
            CallableStatement cs = cn.prepareCall("{ Call PA_LISTAR_TODOS_PROFESORES()}");
            ResultSet rst = cs.executeQuery();
            while(rst.next())
            {
            Profesor p = new Profesor(rst.getInt(8),
                        rst.getString(7),
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getInt(9));
                
            Object data[] = {
                p.getCedula(),
                p.getNombre(),
                p.getApellido1(),
                p.getApellido2(),
                p.getFechaN(),
                p.getCorreo(),
                p.getCarnetP(),
                p.getExperiencia(),
                p._nExperiencia(), // ← Muestra Entry Level, Junior Level, etc.
                p.getTipoP()   
            };
            mdl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println("Error al traer la lista de profesores"+e.getLocalizedMessage());
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
        mdl.addColumn("Carnet Profesor");
        mdl.addColumn("Experiencia");
        mdl.addColumn("TipoP");
        
        try {
            CallableStatement cs = cn.prepareCall("{ Call PA_LISTAR_TODOS_PROFESORES()}");
            ResultSet rst = cs.executeQuery();
            while(rst.next())
            {
                Profesor p = new Profesor(rst.getInt(8),
                        rst.getString(7),
                        rst.getString(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getInt(9));
                
            Object data[] = {
                p.getCedula(),
                p.getNombre(),
                p.getApellido1(),
                p.getApellido2(),
                p.getFechaN(),
                p.getCorreo(),
                p.getCarnetP(),
                p._nExperiencia(), // ← Muestra Entry Level, Junior Level, etc.
                p.getTipoP()
            };
            mdl.addRow(data);
            }
        } catch (Exception e) {
            System.out.println("Error al traer la lista de profesores"+e.getLocalizedMessage());
        }
    return mdl;
    
    }
    
    
    
    
}


package cl.duoc.veterinaria.repository;

import cl.duoc.veterinaria.model.Mascota;
import cl.duoc.veterinaria.utils.Conexion;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MascotaRepository {
    private Connection coneLocal;

    public MascotaRepository() {
    }

    //Vamos a crear 5 métodos:

    //  Agregar Mascota:
    public boolean agregarMascota(Mascota m) throws SQLException {
        boolean inserto = false;
        try {
            coneLocal = new Conexion().getCone();
            String sql = "INSERT INTO mascota VALUES(?,?,?)";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            //se le entrega al PreparedStatement un número(int), al primer símbolo de pregunta, con el valor del chip de la mascota entregada
            ps.setInt(1,m.getChip() );
            ps.setString(2, m.getNombre());
            Date fechita = Date.valueOf(m.getFechaNacimiento());
            ps.setDate(3, fechita);

            //executeUpdate() ejecuta el comando SQL y nos entrega un 1 o un 0. UNO significa que se insertaron los datos en la base de datos, CERO significa que no se insertaron.
            int res = ps.executeUpdate();
            if(res==1){
                inserto = true;
            }
        }catch (Exception ex){

        } finally {
            coneLocal.close();
        }
        return inserto;
    }
    // Ver Mascotas:
    public ArrayList<Mascota> verMascotas() throws SQLException {
        ArrayList<Mascota> lista = new ArrayList<>();
        try {
            coneLocal = new Conexion().getCone();
            String sql = "SELECT chip, nombre, fecha_nacimiento FROM mascota";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int chip = rs.getInt(1);
                String nombre = rs.getString(2);
                Date fechita = rs.getDate(3);
                Mascota m = new Mascota(chip, nombre, fechita.toLocalDate());
                lista.add(m);
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            coneLocal.close();
        }
        return lista;
    }
    // Ver Mascota especifica:
    public void verMascota(int chipBuscar) throws SQLException {
        Mascota m = null;
        try {
            coneLocal = new Conexion().getCone();
            String sql = "SELECT nombre, fecha_nacimiento FROM mascota WHERE chip=?";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            ps.setInt(1,chipBuscar);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                String nombre = rs.getString(1);
                Date fechita = rs.getDate(2);
                m = new Mascota( chipBuscar, nombre, fechita.toLocalDate());
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        } finally {
            coneLocal.close();
        }

    }
    //Eliminar Mascota:
    public boolean eliminarMascota(int chipEliminar) throws SQLException {
        boolean elimino = false;
        try {
            coneLocal = new Conexion().getCone();
            String sql = "DELETE FROM mascota WHERE chip=?";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            ps.setInt(1, chipEliminar);

            //executeUpdate() nos entrega un 1 si se eliminó la mascota y un 0 si no se eliminó.
            int res = ps.executeUpdate();
            if(res==1){
                elimino = true;
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        } finally {
            coneLocal.close();
        }
        return elimino;
    }
    // Modificar Mascota:
    public boolean modificarMascota(Mascota m) throws SQLException {
        boolean modifico = false;
        try {
            coneLocal = new Conexion().getCone();
            String sql = "UPDATE mascota SET nombre=?, fecha_nacimiento=? WHERE chip=?";
            PreparedStatement ps = coneLocal.prepareStatement(sql);
            //se le entrega al PreparedStatement un número(int), al primer símbolo de pregunta, con el valor del chip de la mascota entregada
            ps.setInt(3,m.getChip() );
            ps.setString(1, m.getNombre());
            Date fechita = Date.valueOf(m.getFechaNacimiento());
            ps.setDate(2, fechita);

            //executeUpdate() ejecuta el comando SQL y nos entrega un 1 o un 0. UNO significa que se actualizaron los datos en la base de datos, CERO significa que no se actualizaron.
            int res = ps.executeUpdate();
            if(res==1){
                modifico = true;
            }
        }catch (Exception ex){
            System.err.println(ex.getMessage());
        } finally {
            coneLocal.close();
        }
        return modifico;
    }


}

package cl.duoc.veterinaria.controller;

import cl.duoc.veterinaria.model.Mascota;
import cl.duoc.veterinaria.repository.MascotaRepository;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class MascotaController {
    @FXML
    public Label labelChip;
    @FXML
    public Label labelNombre;
    @FXML
    public Label labelFechaNacimiento;
    @FXML
    public TextField textFieldChip;
    @FXML
    public TextField textFieldNombre;
    @FXML
    public DatePicker datePickerFechaNacimiento;
    @FXML
    public Button buttonAgregar;
    @FXML
    public Button buttonEliminar;
    @FXML
    public  Button buttonModificar;
    @FXML
    public TableView<Mascota> tableViewMascota;
    @FXML
    public TableColumn<Mascota, Integer> tableColumnChip;
    @FXML
    public TableColumn<Mascota, String> tableColumnNombre;
    @FXML
    public TableColumn<Mascota, LocalDate> tableColumnFechaNacimiento;
    @FXML
    public Label labelMensaje;



    // Agregar Mascota:
    @FXML
    protected void agregarMascota() {
        try{
            int chip = Integer.parseInt(textFieldChip.getText());
            String nombre = textFieldNombre.getText();
            LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();

            Mascota m = new Mascota(chip,nombre,fechaNacimiento);
            //mascotas.add(m);
            boolean res = new MascotaRepository().agregarMascota(m);
            if(res){
                mostrarMensaje("Mascota Registrada");
                cargarTabla();
                limpiar();
            }else{
                mostrarMensaje("Mascota no Registrada ");
            }
        }catch(Exception ex){
            System.err.println(ex.getMessage());
        }
    }

    // Modificar Mascota:
    @FXML
    public void modificarMascota() {
        try {
            int chip = Integer.parseInt(textFieldChip.getText());
            String nombre = textFieldNombre.getText();
            LocalDate fechaNacimiento = datePickerFechaNacimiento.getValue();

            Mascota m = new Mascota(chip, nombre, fechaNacimiento);
            boolean res = new MascotaRepository().modificarMascota(m);
            if(res){
                mostrarMensaje("Mascota Modificada");
                cargarTabla();
                limpiar();
            }else{
                mostrarMensaje("Mascota no Modificada");
            }
        }catch(Exception ex){
            mostrarMensaje(ex.getMessage());
        }
    }

    // Eliminar Mascota:
    @FXML
    public void eliminarMascota() {
        try {
            Mascota m = tableViewMascota.getSelectionModel().getSelectedItem();
            if(m == null){
                mostrarMensaje("Seleccione una mascota de la tabla");
            }else{
                boolean res = new MascotaRepository().eliminarMascota(m.getChip());
                if(res){
                    mostrarMensaje("Mascota Eliminada");
                    cargarTabla();
                    limpiar();
                }else{
                    mostrarMensaje("Mascota no Eliminada");
                }
            }
        }catch(Exception ex){
            mostrarMensaje(ex.getMessage());
        }
    }

    // Cargar  tabla:
    @FXML
    public void cargarTabla() {
        try {
            ArrayList<Mascota> mascotas = new MascotaRepository().verMascotas();
            System.out.println("Cantidad de mascotas: " + mascotas.size());
            ObservableList<Mascota> data = FXCollections.observableArrayList(mascotas);
            tableViewMascota.setItems(data);
        } catch (Exception ex) {
            mostrarMensaje(ex.getMessage());
        }
    }

    // Limpiar datos:
    @FXML
    public void limpiar() {
        textFieldChip.clear();
        textFieldNombre.clear();
        datePickerFechaNacimiento.setValue(null);
    }


    // Mostrar mensaje:
    @FXML
    public void mostrarMensaje(String mensaje) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setContentText(mensaje);
        alerta.show();
    }

    // Initialize:
    @FXML
    public  void initialize () {
        tableColumnChip.setCellValueFactory(new PropertyValueFactory<>("chip"));
        tableColumnNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        tableColumnFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        cargarTabla();
    }

    // Seleccionar deesde la tasbla:
    @FXML
    public void seleccionarMascota() {
        Mascota m = tableViewMascota.getSelectionModel().getSelectedItem();
        if(m != null){
            textFieldChip.setText(String.valueOf(m.getChip()));
            textFieldNombre.setText(m.getNombre());
            datePickerFechaNacimiento.setValue(m.getFechaNacimiento());
        }
    }
}




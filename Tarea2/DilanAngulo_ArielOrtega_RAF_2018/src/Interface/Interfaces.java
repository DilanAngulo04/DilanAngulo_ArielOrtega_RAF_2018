package Interface;

import Domain.Car;
import File.CarFile;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javax.swing.JOptionPane;

public class Interfaces {

    //Variables globales para el uso de la interfaz
    static boolean americanCB, americanUp; //variable del comboBox que indica si es americano o no
    Car car;
    static CarFile cfile;
    static int serial;
    static Button btn_updateCar, btn_update;
    static Label lbl_nameUpdate, lbl_yearUpdate, lbl_americanUpdate, lbl_notValue;
    static TextField tfd_nameUpdate, tfd_yearUpdate;
    static ComboBox cbx_americanUpdate;

    //Metodo para la interfaz de ingreso de vehiculos
    public static GridPane WindowsInsert() throws IOException {

        CarFile cfile = new CarFile(new File("./Car.dat"));

        //Layout que contiene los nodos para la insercion de un auto
        GridPane gpn_insert = new GridPane();
        gpn_insert.setPadding(new Insets(100));
        gpn_insert.setPrefSize(500, 500);
        //Ajuste en las filas y columnas del GridPane
        gpn_insert.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_insert.getColumnConstraints().add(new ColumnConstraints(100));
        gpn_insert.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_insert.getColumnConstraints().add(new ColumnConstraints(200));
        gpn_insert.getColumnConstraints().add(new ColumnConstraints(100));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.getRowConstraints().add(new RowConstraints(50));
        gpn_insert.setVgap(15);
        gpn_insert.setHgap(25);
        gpn_insert.setAlignment(Pos.CENTER);

        //label y textfield del nombre
        Label lbl_name = new Label("Nombre");
        lbl_name.setTextFill(Color.WHITE);
        lbl_name.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_name, 0, 0);

        TextField tfd_name = new TextField();
        gpn_insert.add(tfd_name, 1, 0);

        //label y textfield del anno
        Label lbl_year = new Label("Año");
        lbl_year.setTextFill(Color.WHITE);
        lbl_year.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_year, 3, 0);

        TextField tfd_year = new TextField();
        gpn_insert.add(tfd_year, 4, 0);

        //label y textfield del kilometraje
        Label lbl_mileage = new Label("Kilometraje");
        lbl_mileage.setTextFill(Color.WHITE);
        lbl_mileage.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_mileage, 0, 1);

        TextField tfd_mileage = new TextField();
        gpn_insert.add(tfd_mileage, 1, 1);

        //label y combobox de americano
        Label lbl_american = new Label("Americano");
        lbl_american.setTextFill(Color.WHITE);
        lbl_american.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_american, 3, 1);

        //ComboBox de caracter booleano
        ComboBox cbx_american = new ComboBox();
        cbx_american.setPrefWidth(200);
        cbx_american.getItems().addAll("Si", "No");
        cbx_american.setValue("Si");

        cbx_american.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equalsIgnoreCase("si")) {
                    americanCB = true;
                } else {
                    americanCB = false;
                }
            }
        });
        gpn_insert.add(cbx_american, 4, 1);

        //label y textfield de la serie
        Label lbl_serial = new Label("Serie");
        lbl_serial.setTextFill(Color.WHITE);
        lbl_serial.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_serial, 0, 2);

        TextField tfd_serial = new TextField();
        gpn_insert.add(tfd_serial, 1, 2);

        //Labels que ayudan con las excepciones, aclaran al usuario
        Label lbl_success = new Label("¡Vehiculo ingresado con exito!");
        lbl_success.setVisible(false);
        gpn_insert.add(lbl_success, 3, 4, 4, 4);

        Label lbl_error = new Label("Serie ya registrada");
        lbl_error.setVisible(false);
        gpn_insert.add(lbl_error, 3, 4, 4, 4);

        Label lbl_emptyFields = new Label("Llene todos los campos por favor");
        lbl_emptyFields.setVisible(false);
        gpn_insert.add(lbl_emptyFields, 3, 4, 4, 4);

        Label lbl_numberFormat = new Label("Llene todos los lugares e \ningrese solo numeros en \nlos campos correspondientes");
        lbl_numberFormat.setVisible(false);
        gpn_insert.add(lbl_numberFormat, 3, 4, 4, 4);

        //label si excede 20 caracteres
        Label lbl_chars = new Label("Excede el numero de caracteres");
        lbl_chars.setVisible(false);
        lbl_chars.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_insert.add(lbl_chars, 3, 4, 4, 4);

        //boton para ingresar cliente
        Button btn_enterCar = new Button("Ingresar");
        btn_enterCar.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {
                    //de string a tipo de variable y se hace creacion de objeto
                    int intSerial = Integer.parseInt(tfd_serial.getText().replaceAll(" ", ""));
                    int intYear = Integer.parseInt(tfd_year.getText().replace(" ", ""));
                    float floatMileage = Float.parseFloat(tfd_mileage.getText().replaceAll(" ", ""));
                    Car car1 = new Car(tfd_name.getText(), intYear, floatMileage, americanCB, intSerial);
                    lbl_numberFormat.setVisible(false);

                    //Validacion de llenado de campos de ingreso de texto
                    if (tfd_name.getText().isEmpty() || tfd_year.getText().isEmpty() || tfd_mileage.getText().isEmpty() || tfd_serial.getText().isEmpty()) {
                        lbl_emptyFields.setTextFill(Color.RED);
                        lbl_emptyFields.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                        lbl_numberFormat.setVisible(false);
                        lbl_emptyFields.setVisible(true);
                        lbl_error.setVisible(false);
                        lbl_success.setVisible(false);
                        lbl_chars.setVisible(false);

                        //Validacion del ingreso de serie
                    } else if (cfile.searchCar(intSerial) != -1) {
                        lbl_error.setTextFill(Color.RED);
                        lbl_error.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                        lbl_error.setVisible(true);
                        lbl_numberFormat.setVisible(false);
                        lbl_success.setVisible(false);
                        lbl_emptyFields.setVisible(false);
                        lbl_chars.setVisible(false);

                        //Validacion del tamaño del nombre (20 caracteres)    
                    } else if (tfd_name.getText().length() > 20) {
                        lbl_chars.setTextFill(Color.RED);
                        lbl_error.setVisible(false);
                        lbl_numberFormat.setVisible(false);
                        lbl_success.setVisible(false);
                        lbl_emptyFields.setVisible(false);
                        lbl_chars.setVisible(true);

                    } else {
                        //si se llega aqui, el ingreso fue exitoso
                        cfile.addEndRecord(car1);
                        cfile.close();

                        lbl_success.setTextFill(Color.GREEN);
                        lbl_success.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                        lbl_success.setVisible(true);
                        lbl_numberFormat.setVisible(false);
                        lbl_emptyFields.setVisible(false);
                        lbl_error.setVisible(false);
                        lbl_chars.setVisible(false);
                        tfd_mileage.setText("");
                        tfd_name.setText("");
                        tfd_serial.setText("");
                        tfd_year.setText("");
                        cbx_american.setPromptText("");

                    }
                } catch (IOException ex) {
                    //mensaje de error
                    Logger.getLogger(Interfaces.class.getName()).log(Level.SEVERE, "Error insertando auto", ex);
                } catch (NumberFormatException nfe) {
                    //mensaje de error
                    lbl_numberFormat.setVisible(true);
                    lbl_emptyFields.setVisible(false);
                    lbl_error.setVisible(false);
                    lbl_success.setVisible(false);
                    lbl_chars.setVisible(false);
                    lbl_numberFormat.setTextFill(Color.RED);
                    lbl_numberFormat.setFont(Font.font("Arial", FontWeight.BOLD, 15));
                }

            }
        });
        gpn_insert.add(btn_enterCar, 4, 3);

        return gpn_insert;
    }//end method

    //metodo para la interfaz de actualizar un vehiculo
    public static GridPane windowsUpdate() throws IOException {

        CarFile cfile = new CarFile(new File("./Car.dat"));

        GridPane gpn_update = new GridPane();
        gpn_update.setPadding(new Insets(100));
        gpn_update.setPrefSize(1000, 1400);
        //edicion de las columna y filas del GridPane
        gpn_update.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_update.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_update.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_update.getColumnConstraints().add(new ColumnConstraints(150));
        gpn_update.getColumnConstraints().add(new ColumnConstraints(250));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.getRowConstraints().add(new RowConstraints(50));
        gpn_update.setAlignment(Pos.CENTER);

        //Label y TextField del ingreso de la serie del vehiculo
        Label lbl_searchCar = new Label("Buscar vehiculo");
        lbl_searchCar.setTextFill(Color.WHITE);
        lbl_searchCar.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_searchCar, 1, 2);

        TextField txt_searchCar = new TextField();
        txt_searchCar.setPromptText("serie");
        gpn_update.add(txt_searchCar, 2, 2);

        //Label de advertencia en caso de no encontrar un vehiculo
        Label lbl_warning = new Label("El vehículo no está registrado");
        lbl_warning.setTextFill(Color.WHITE);
        lbl_warning.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_warning.setVisible(false);
        gpn_update.add(lbl_warning, 2, 3, 3, 3);

        //Labels que dan como salida de texto, la informacion del vehiculo que se desea actualizar
        Label lbl_registered = new Label("Registro:");
        lbl_registered.setVisible(false);
        lbl_registered.setTextFill(Color.WHITE);
        lbl_registered.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_registered, 0, 3);

        Label lbl_name = new Label();
        lbl_name.setTextFill(Color.WHITE);
        lbl_name.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_name, 0, 4);

        Label lbl_year = new Label();
        lbl_year.setTextFill(Color.WHITE);
        lbl_year.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_year, 1, 4);

        Label lbl_mileage = new Label();
        lbl_mileage.setTextFill(Color.WHITE);
        lbl_mileage.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_mileage, 2, 4);

        Label lbl_american = new Label();
        lbl_american.setTextFill(Color.WHITE);
        lbl_american.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_american, 3, 4);

        Label lbl_serial = new Label();
        lbl_serial.setTextFill(Color.WHITE);
        lbl_serial.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_serial, 4, 4);

        //Label de advertencia si el campo de ingreso de texto esta vacio
        lbl_notValue = new Label("Ingresar un valor");
        lbl_notValue.setTextFill(Color.RED);
        lbl_notValue.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_notValue.setVisible(false);
        gpn_update.add(lbl_notValue, 2, 3);

        //Label de advertencia si el campo de ingreso de texto no es un numero
        Label lbl_notNumber = new Label("Valor no valido");
        lbl_notNumber.setVisible(false);
        lbl_notNumber.setTextFill(Color.RED);
        lbl_notNumber.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        gpn_update.add(lbl_notNumber, 2, 3);

        Button btn_searchCar = new Button("Buscar vehiculo");
        gpn_update.add(btn_searchCar, 4, 2);

        //accion del boton buscar vehiculo
        btn_searchCar.setOnAction((event) -> {

            try {
                //se realiza un test con una variable para saber si el valor ingresado es entero
                int test = Integer.parseInt(txt_searchCar.getText());

                try {

                    //validacion de llenado
                    if (txt_searchCar.getText().length() == 0) {
                        lbl_notValue.setVisible(true);
                        lbl_warning.setVisible(false);
                        lbl_notNumber.setVisible(false);

                    } else {
                        serial = Integer.parseInt(txt_searchCar.getText());

                        //validacion de que el vehiculo exista
                        if (cfile.searchCar(serial) == -1) {
                            lbl_notValue.setVisible(false);
                            lbl_notNumber.setVisible(false);
                            lbl_warning.setVisible(true);
                            txt_searchCar.setText("");

                        } else {

                            //si llega aqui es porque el vehiculo fue encontrado
                            lbl_notValue.setVisible(false);
                            lbl_warning.setVisible(false);
                            lbl_notNumber.setVisible(false);
                            lbl_registered.setVisible(true);
                            btn_updateCar.setVisible(true);
                            lbl_name.setText("Nombre: " + cfile.getCar(cfile.searchCar(serial)).getName());
                            lbl_year.setText("Año: " + cfile.getCar(cfile.searchCar(serial)).getYear());
                            lbl_mileage.setText("Kilometraje: " + cfile.getCar(cfile.searchCar(serial)).getMileage());
                            String american;
                            if (cfile.getCar(cfile.searchCar(serial)).isAmerican()) {
                                american = "Si";
                            } else {
                                american = "No";
                            }
                            lbl_american.setText("Americano: " + american);
                            lbl_serial.setText("Serie: " + cfile.getCar(cfile.searchCar(serial)).getSerial());
                        }
                    }
                } catch (IOException ex) {
                    //mensaje de excepcion
                    Logger.getLogger(Interfaces.class.getName()).log(Level.SEVERE, null, ex);
                }
            } catch (NumberFormatException nfe) {
                //mensaje de excepcion
                lbl_notNumber.setVisible(true);
            }

        });

        btn_updateCar = new Button("Ir");
        btn_updateCar.setPrefWidth(150);
        btn_updateCar.setVisible(false);
        gpn_update.add(btn_updateCar, 4, 5);
        
        //Se le asigna la funcion al boton "ir"

        btn_updateCar.setOnAction((event) -> {

            lbl_searchCar.setVisible(false);
            txt_searchCar.setVisible(false);
            btn_searchCar.setVisible(false);
            btn_updateCar.setVisible(false);
            lbl_nameUpdate.setVisible(true);
            tfd_nameUpdate.setVisible(true);
            lbl_yearUpdate.setVisible(true);
            tfd_yearUpdate.setVisible(true);
            lbl_americanUpdate.setVisible(true);
            cbx_americanUpdate.setVisible(true);
            btn_update.setVisible(true);
            try {

                tfd_nameUpdate.setText(cfile.getCar(cfile.searchCar(serial)).getName());
                tfd_yearUpdate.setText("" + cfile.getCar(cfile.searchCar(serial)).getYear());
                String value;

                if (cfile.getCar(cfile.searchCar(serial)).isAmerican() == true) {
                    value = "Si";
                } else {
                    value = "No";
                }

                cbx_americanUpdate.setValue(value);

            } catch (IOException ex) {
                Logger.getLogger(Interfaces.class.getName()).log(Level.SEVERE, null, ex);
            }

        });

        //Label y TextField del nuevo valor de nombre
        lbl_nameUpdate = new Label("Nombre");
        lbl_nameUpdate.setTextFill(Color.WHITE);
        lbl_nameUpdate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_nameUpdate.setVisible(false);
        gpn_update.add(lbl_nameUpdate, 0, 0);

        tfd_nameUpdate = new TextField();
        tfd_nameUpdate.setVisible(false);
        gpn_update.add(tfd_nameUpdate, 1, 0);

        
        //Label y TextField del nuevo valor de año
        lbl_yearUpdate = new Label("Año");
        lbl_yearUpdate.setTextFill(Color.WHITE);
        lbl_yearUpdate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_yearUpdate.setVisible(false);
        gpn_update.add(lbl_yearUpdate, 3, 0);

        tfd_yearUpdate = new TextField();
        tfd_yearUpdate.setVisible(false);
        gpn_update.add(tfd_yearUpdate, 4, 0);
        
        
        //Label y ComboBox del nuevo valor booleano de american
        lbl_americanUpdate = new Label("Americano");
        lbl_americanUpdate.setTextFill(Color.WHITE);
        lbl_americanUpdate.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_americanUpdate.setVisible(false);
        gpn_update.add(lbl_americanUpdate, 0, 1);

        cbx_americanUpdate = new ComboBox();
        cbx_americanUpdate.setPrefWidth(200);
        cbx_americanUpdate.setVisible(false);
        cbx_americanUpdate.getItems().addAll("Si", "No");

        cbx_americanUpdate.valueProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue ov, String t, String t1) {
                if (t1.equalsIgnoreCase("si")) {
                    americanUp = true;
                } else {
                    americanUp = false;
                }
            }
        });
        gpn_update.add(cbx_americanUpdate, 1, 1);

        //label si no llena campos
        Label lbl_EmptyField = new Label("No ha llenado todos campos");
        lbl_EmptyField.setTextFill(Color.RED);
        lbl_EmptyField.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_EmptyField.setVisible(false);
        gpn_update.add(lbl_EmptyField, 4, 6);

        //label en caso de exito
        Label lbl_successful = new Label("Actualizacion exitosa!");
        lbl_successful.setTextFill(Color.GREEN);
        lbl_successful.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_successful.setVisible(false);
        gpn_update.add(lbl_successful, 4, 6);

        //label para la excepcion de ingresar texto en campos que necesita solo numeros
        Label lbl_numberFormat = new Label("Utilice solo numeros en serial y año");
        lbl_numberFormat.setTextFill(Color.GREEN);
        lbl_numberFormat.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_numberFormat.setVisible(false);
        gpn_update.add(lbl_numberFormat, 4, 6);

        //label si excede 20 caracteres
        Label lbl_charslimit = new Label("Excede el numero de caracteres");
        lbl_charslimit.setTextFill(Color.RED);
        lbl_charslimit.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        lbl_charslimit.setVisible(false);
        gpn_update.add(lbl_charslimit, 4, 6);

        btn_update = new Button("Actualizar");
        btn_update.setPrefWidth(150);
        btn_update.setVisible(false);
        
        //funcion del boton
        btn_update.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                try {

                    //validacion de llenado
                    if (tfd_nameUpdate.getText().isEmpty() || tfd_yearUpdate.getText().isEmpty() || (cbx_americanUpdate.getValue() + "").isEmpty()) {
                        lbl_EmptyField.setVisible(true);
                        lbl_successful.setVisible(false);
                        lbl_numberFormat.setVisible(false);
                        lbl_charslimit.setVisible(false);

                        //validacion de que el nuevo nombre tenga 20 caracteres
                    } else if (tfd_nameUpdate.getText().length() > 20) {
                        lbl_numberFormat.setVisible(false);
                        lbl_EmptyField.setVisible(false);
                        lbl_successful.setVisible(false);
                        lbl_charslimit.setVisible(true);

                    } else {
                        //si llega aqui es por que el vehiculo si se actualizo correctamente
                        String nameUpdate = tfd_nameUpdate.getText();
                        int yearUpdate = Integer.parseInt(tfd_yearUpdate.getText());
                        cfile.updateCar(serial, nameUpdate, yearUpdate, americanUp);

                        lbl_name.setText("Nombre: " + cfile.getCar(cfile.searchCar(serial)).getName());
                        lbl_year.setText("Año: " + cfile.getCar(cfile.searchCar(serial)).getYear());
                        lbl_mileage.setText("Kilometraje: " + cfile.getCar(cfile.searchCar(serial)).getMileage());
                        String american;

                        if (cfile.getCar(cfile.searchCar(serial)).isAmerican()) {
                            american = "Si";
                        } else {
                            american = "No";
                        }

                        lbl_american.setText("Americano: " + american);
                        lbl_serial.setText("Serie: " + cfile.getCar(cfile.searchCar(serial)).getSerial());

                        //label de exito
                        lbl_numberFormat.setVisible(false);
                        lbl_EmptyField.setVisible(false);
                        lbl_successful.setVisible(true);
                        lbl_charslimit.setVisible(false);
                    }
                } catch (IOException ex) {
                    Logger.getLogger(Interfaces.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NumberFormatException nfe) {
                    lbl_numberFormat.setVisible(true);
                    lbl_EmptyField.setVisible(false);
                    lbl_successful.setVisible(false);
                    lbl_charslimit.setVisible(false);
                }

            }
        });
        gpn_update.add(btn_update, 4, 5);

        return gpn_update;
    }//end method

    //Metodo de interfaz para mostrar los vehiculos registrados
    public static GridPane viewCars() throws IOException {

        GridPane gpn_viewCars = new GridPane();
        gpn_viewCars.setPadding(new Insets(20));
        gpn_viewCars.setHgap(10);
        gpn_viewCars.setVgap(10);
        gpn_viewCars.setAlignment(Pos.CENTER);

        TableView<Car> tvw_tableCars = new TableView<>();

        CarFile cfile = new CarFile(new File("./Car.dat"));
        ObservableList<Car> datos = cfile.getAllCars();
        tvw_tableCars.setItems(datos);

        TableColumn nameColumn = new TableColumn("Nombre");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn yearColumn = new TableColumn("Año");
        yearColumn.setMinWidth(150);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn mileageColumn = new TableColumn("Kilometraje");
        mileageColumn.setMinWidth(150);
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn americanColumn = new TableColumn("Americano");
        americanColumn.setMinWidth(150);
        americanColumn.setCellValueFactory(new PropertyValueFactory<>("american"));

        TableColumn serialColumn = new TableColumn("Serie");
        serialColumn.setMinWidth(150);
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));

        tvw_tableCars.getColumns().addAll(nameColumn, yearColumn, mileageColumn, americanColumn, serialColumn);
        tvw_tableCars.setPrefSize(750, 650);
        tvw_tableCars.setTableMenuButtonVisible(true);
        gpn_viewCars.add(tvw_tableCars, 0, 0);

        return gpn_viewCars;
    }//end method
    
    //Metodo para interfaz de eliminar vehiculos 
    public static GridPane deleteCars() throws IOException {

        GridPane gpn_viewCars = new GridPane();
        gpn_viewCars.setPadding(new Insets(20));
        gpn_viewCars.setHgap(10);
        gpn_viewCars.setVgap(10);
        gpn_viewCars.setAlignment(Pos.CENTER);

        //TableView que muestra los registros
        TableView<Car> tvw_tableCars = new TableView<>();

        CarFile cfile = new CarFile(new File("./Car.dat"));
        ObservableList<Car> datos = cfile.getAllCars();
        tvw_tableCars.setItems(datos);

        //Nombre de las columnas y el parametro del objeto que mostraran
        TableColumn nameColumn = new TableColumn("Nombre");
        nameColumn.setMinWidth(150);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn yearColumn = new TableColumn("Año");
        yearColumn.setMinWidth(150);
        yearColumn.setCellValueFactory(new PropertyValueFactory<>("year"));

        TableColumn mileageColumn = new TableColumn("Kilometraje");
        mileageColumn.setMinWidth(150);
        mileageColumn.setCellValueFactory(new PropertyValueFactory<>("mileage"));

        TableColumn americanColumn = new TableColumn("Americano");
        americanColumn.setMinWidth(150);
        americanColumn.setCellValueFactory(new PropertyValueFactory<>("american"));

        TableColumn serialColumn = new TableColumn("Serie");
        serialColumn.setMinWidth(150);
        serialColumn.setCellValueFactory(new PropertyValueFactory<>("serial"));

        //agregacion de nodulos a la tabla
        tvw_tableCars.getColumns().addAll(nameColumn, yearColumn, mileageColumn, americanColumn, serialColumn);
        tvw_tableCars.setPrefSize(750, 650);
        tvw_tableCars.setTableMenuButtonVisible(true);
        gpn_viewCars.add(tvw_tableCars, 0, 1);

        //Label que muestra si no se ha selecionado una opcion de la tabla a eliminar
        Label lbl_check = new Label();
        gpn_viewCars.add(lbl_check, 1, 0);

        //Boton con funcion de eliminar un registro
        Button btn_delete = new Button("Eliminar");
        btn_delete.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                {
                    Car cr1 = tvw_tableCars.getSelectionModel().getSelectedItem();
                    if (cr1 == null) {
                        lbl_check.setText("NO ha seleccionado un auto");;
                        lbl_check.setFont(Font.font("OPEN SANS", 20));
                        lbl_check.setTextFill(Color.RED);
                        lbl_check.setVisible(true);
                    } else {
                        //metodo para borrar
                        lbl_check.setVisible(false);
                        try {

                            int confirm = JOptionPane.showConfirmDialog(null, "Realmente desea eliminar el auto con la serie: "
                                    + tvw_tableCars.getSelectionModel().getSelectedItem().getSerial() + " ?");

                            if (confirm == JOptionPane.YES_OPTION) {
                                cfile.deleteCar(tvw_tableCars.getSelectionModel().getSelectedItem().getSerial());
                                ObservableList<Car> datos = cfile.getAllCars();
                                tvw_tableCars.setItems(datos);
                            }
                        } catch (IOException ex) {
                            Logger.getLogger(Interfaces.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }
            }
        });
        gpn_viewCars.add(btn_delete, 0, 0);

        return gpn_viewCars;
    }//end method

}//end class

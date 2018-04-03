
package Interface;

import File.CarFile;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Principal extends Application {
    
    //Dilan Angulo Ruiz / B70448
    //Ariel Ortega Brenes / B75567
    CarFile cfile;

    @Override
    public void start(Stage primaryStage) {

        //HBOX que mostrara el menu principal y las diferentes ventanas que puede mostrar el programa
        HBox root = new HBox();
        root.setStyle("-fx-background-color: Silver");

        //menu lateral para acceder a las diferentes secciones
        VBox menuV = new VBox();

        //grupo de radio buttons
        ToggleGroup group = new ToggleGroup();

        RadioButton rdb_ins = new RadioButton("Insertar");
        rdb_ins.setTextFill(Color.GAINSBORO);
        rdb_ins.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        rdb_ins.setToggleGroup(group);
        //accion del radiobutton insertar
        rdb_ins.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    root.getChildren().clear();//se limpian las demas interfaces
                    root.getChildren().addAll(menuV, Interfaces.WindowsInsert());//se muestra la interfaz de insertar vehiculo
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        RadioButton rdb_upd = new RadioButton("Actualizar");
        rdb_upd.setTextFill(Color.GAINSBORO);
        rdb_upd.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        rdb_upd.setToggleGroup(group);
        //accion radio button actualizar
        rdb_upd.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    root.getChildren().clear();//se limpian las demas interfaces
                    root.getChildren().addAll(menuV, Interfaces.windowsUpdate());//se muestra la interfaz de actualizar vehiculo
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        RadioButton rdb_del = new RadioButton("Eliminar");
        rdb_del.setTextFill(Color.GAINSBORO);
        rdb_del.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        rdb_del.setToggleGroup(group);
        //accion radio button eliminar
        rdb_del.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    root.getChildren().clear();//se limpian las demas interfaces
                    root.getChildren().addAll(menuV, Interfaces.deleteCars());//se muestra la interfaz de eliminar un vehiculo
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

        RadioButton rdb_shw = new RadioButton("Mostrar");
        rdb_shw.setTextFill(Color.GAINSBORO);
        rdb_shw.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        //accion radio button mostrar
        rdb_shw.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                
                try {
                    root.getChildren().clear();//se limpian las demas interfaces
                    root.getChildren().addAll(menuV, Interfaces.viewCars());//se muestra la interfaz que presenta todos los vehiculos
                } catch (IOException ex) {
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
        rdb_shw.setToggleGroup(group);

        //boton de salir
        Button btn_salir = new Button("Salir");
        btn_salir.setTextFill(Color.ANTIQUEWHITE);
        btn_salir.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        btn_salir.setStyle("-fx-background-color: DIMGRAY;-fx-border-color: TRANSPARENT");
        btn_salir.setOnMouseEntered((event) -> {

            btn_salir.setTextFill(Color.BLACK);
            btn_salir.setStyle("-fx-background-color: ANTIQUEWHITE;-fx-border-color: TRANSPARENT");

        });
        btn_salir.setOnMouseExited((event) -> {

            btn_salir.setTextFill(Color.ANTIQUEWHITE);
            btn_salir.setStyle("-fx-background-color: DIMGRAY;-fx-border-color: TRANSPARENT");

        });
        
        //se le asigna la accion al boton
        btn_salir.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                //confirmar salida
                Alert alert = new Alert(AlertType.CONFIRMATION);
                    alert.setTitle("Confirmacion");
                    alert.setHeaderText("Atencion");
                alert.setContentText("Desea salir?");

                Optional<ButtonType> result = alert.showAndWait();
                    if (result.get() == ButtonType.OK)
                        System.exit(0);
                    
              }});
        
        //Boton de comprimir
        Button btn_compress = new Button("Comprimir Archivo");
        btn_compress.setTextFill(Color.ANTIQUEWHITE);
        btn_compress.setFont(Font.font("OPEN SANS", FontWeight.BOLD, 15));
        btn_compress.setStyle("-fx-background-color: DIMGRAY;-fx-border-color: TRANSPARENT");
        btn_compress.setOnMouseEntered((event) -> {

            btn_compress.setTextFill(Color.BLACK);
            btn_compress.setStyle("-fx-background-color: ANTIQUEWHITE;-fx-border-color: TRANSPARENT");

        });
        btn_compress.setOnMouseExited((event) -> {

            btn_compress.setTextFill(Color.ANTIQUEWHITE);
            btn_compress.setStyle("-fx-background-color: DIMGRAY;-fx-border-color: TRANSPARENT");

        });
        //Se le asigna la accion al boton
        btn_compress.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                TextInputDialog dialog = new TextInputDialog("");
                    dialog.setTitle("Compresion");
                    dialog.setHeaderText("Archivo comprimido");
                    dialog.setContentText("Ingrese un nombre para guardarlo");
                Optional<String> result = dialog.showAndWait();
                //validacion
                if (result.isPresent()){
                    File file = new File("./Car.dat");
                    File destination = new File("./"+result.get()+".dat");
                    try {
                        
                        //Comprobacion de llenado del campo de texto
                        if(result.get().length()>0){
                            CarFile cfile = new CarFile(new File("./Car.dat"));
                        cfile.compressFile(file, destination);
                        
                        Alert alert = new Alert(AlertType.INFORMATION);
                            alert.setTitle("Notificacion");
                            alert.setHeaderText(null);
                            alert.setContentText("Archivo comprimido creado exitosamente");

                            alert.showAndWait();
                        } else {
                            //Resticcion si el campo de ingreso de texto esta vacio
                            Alert alert = new Alert(AlertType.ERROR);
                                alert.setTitle("Atencion");
                                alert.setHeaderText(null);
                                alert.setContentText("Nombre invalido");

                                alert.showAndWait();
                                
                        }
                        
                    } catch (IOException ex) {
                        Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });

        menuV.getChildren().addAll(rdb_ins, rdb_upd, rdb_del, rdb_shw, btn_compress, btn_salir);
        menuV.setSpacing(55);
        menuV.setStyle("-fx-background-color: Crimson");
        menuV.setPadding(new Insets(110, 20, 150, 20));
        root.getChildren().addAll(menuV);

        Scene scene = new Scene(root, 1350, 800);

        
        primaryStage.setTitle("Autos");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("Images/car_Icon.png"));
        primaryStage.show();
    }
    
    public static void main(String[] args) throws IOException {
        launch(args);
           }

}

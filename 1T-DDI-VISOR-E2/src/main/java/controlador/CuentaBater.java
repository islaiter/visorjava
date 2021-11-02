package controlador;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import modelo.Cuenta;
import java.io.IOException;
import java.util.ArrayList;

public class CuentaBater extends Application {

    Cuenta cuenta1 = new Cuenta(1, "Roberto", "15/07/2020", 1000.0);
    Cuenta cuenta2 = new Cuenta(2, "Margarita", "30/10/2020", 2000.0);
    Cuenta cuenta3 = new Cuenta(3, "Jose Carlos", "12/04/2020", 50000.0);
    Cuenta cuenta4 = new Cuenta(4, "Ángel", "6/07/2020", 15000.0);
    Cuenta cuenta5 = new Cuenta(5, "Andrea", "20/09/2021", 500.0);

    private ArrayList<Cuenta> arrayCuentas = new ArrayList<>();

    @Override
    public void start(Stage stage) throws IOException {

        arrayCuentas.add(cuenta1);
        arrayCuentas.add(cuenta2);
        arrayCuentas.add(cuenta3);
        arrayCuentas.add(cuenta4);
        arrayCuentas.add(cuenta5);

        FXMLLoader fxmlLoader = new FXMLLoader(CuentaBater.class.getResource("/vista/VisualizaCuentas.fxml"));
        Parent root = fxmlLoader.load();

        VisualizaCuentasController controlador = fxmlLoader.getController();
        controlador.iniciaLista(arrayCuentas);
        Scene scene = new Scene(root);

        stage.getIcons().add(new Image(CuentaBater.class.getResourceAsStream("/vista/logo.png")));
        stage.setTitle("Aplicación Cuentas Bancarias");
        scene.getStylesheets().add(getClass().getResource("/vista/VisorStyle.css").toExternalForm());
        stage.setScene(scene);

        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {


        launch();
    }
}
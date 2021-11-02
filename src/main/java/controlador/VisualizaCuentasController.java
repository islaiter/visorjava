package controlador;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import modelo.Cuenta;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class VisualizaCuentasController implements Initializable {

    List<Cuenta> listaCuentas = new ArrayList<>();

    protected int contadorCuentas = 0;
    protected boolean identBotNueva = false;

    @FXML
    protected Label etiExist;
    @FXML
    protected Label etiNueva;
    @FXML
    protected Label numExist;
    @FXML
    protected Label num50000;
    @FXML
    protected TextField numero;
    @FXML
    protected TextField titular;
    @FXML
    protected TextField fecha;
    @FXML
    protected TextField saldo;
    @FXML
    protected Button siguiente;
    @FXML
    protected Button atras;
    @FXML
    protected Button nueva;
    @FXML
    protected Button aceptar;
    @FXML
    protected Button cancelar;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Inicializa el metodo
    }

    public void iniciaLista(List<Cuenta> listaCuentas){
        this.listaCuentas = listaCuentas;
        contador();
        actualizarDatos();
        menuContextual();
    }

    public void accionSiguiente() {
        if (contadorCuentas == (listaCuentas.size()) - 2) {
            identBotNueva = true;
            siguiente.setVisible(false);
            siguiente.setDisable(true);
            nueva.setVisible(true);
            nueva.setDisable(false);
        }

        if (contadorCuentas < (listaCuentas.size()) - 1) {
            contadorCuentas++;
        }

        if (contadorCuentas > 0) {
            atras.setVisible(true);
            atras.setDisable(false);
        }

        actualizarDatos();
    }

    public void accionAtras() {
        if (identBotNueva) {
            siguiente.setVisible(true);
            siguiente.setDisable(false);
            nueva.setVisible(false);
            nueva.setDisable(true);
            identBotNueva = false;
        }

        if (contadorCuentas > 0) {
            contadorCuentas--;
        }

        if (contadorCuentas == 0) {
            atras.setVisible(false);
            atras.setDisable(true);
        }

        actualizarDatos();
    }

    public void accionNueva() {
        etiExist.setVisible(false);
        etiNueva.setVisible(true);

        numero.clear();
        titular.clear();
        fecha.clear();
        saldo.clear();

        numero.setEditable(true);
        titular.setEditable(true);
        fecha.setEditable(true);
        saldo.setEditable(true);

        nueva.setVisible(false);
        nueva.setDisable(true);
        atras.setVisible(false);
        atras.setDisable(true);

        aceptar.setVisible(true);
        aceptar.setDisable(false);
        cancelar.setVisible(true);
        cancelar.setDisable(false);
    }

    public void accionAceptar() {
        String redField = "-fx-background-color: #ed6d6d";

        numero.setStyle("");
        titular.setStyle("");
        fecha.setStyle("");
        saldo.setStyle("");

        boolean isValid = true; //Boolean para indicar si el formato es correcto o no.

        if (!numero.getText().isEmpty() && !isNumeric(numero.getText())) {

            for (Cuenta cuenta : listaCuentas) {
                if (Integer.parseInt(numero.getText()) == cuenta.getNumeroCuenta()) {
                    numero.setText("");
                    isValid = false;
                    numero.setStyle(redField);
                    numero.setPromptText("El número de cuenta ya existe");
                }
            }
        } else {
            numero.setText("");
            isValid = false;
            numero.setStyle(redField);
            numero.setPromptText("No es un número");
        }


        if(titular.getText().isEmpty()) { // Se ejecuta si el campo está "Titular" vacío.
            titular.setText("");
            isValid = false;
            titular.setPromptText("Este campo no debe estar vacio");
            titular.setStyle(redField);
        } else if (titulardigit(titular.getText())) {
            titular.setText("");
            isValid = false;
            titular.setPromptText("el titular contiene número");
            titular.setStyle(redField);
        }


        fecha.setPromptText("dd/mm/yyyy");
        if(fecha.getText().isEmpty()) { // Se ejecuta si el campo está "Fecha" vacío.
            fecha.setText("");
            isValid = false;
            fecha.setPromptText("Este campo no puede estar vacio");
            fecha.setStyle(redField);
        }
        else if (!validDate(fecha.getText())) {
            fecha.setText("");
            isValid = false;
            fecha.setStyle(redField);
            fecha.setPromptText("Fecha incorrecta");
        }

        if(saldo.getText().isEmpty()) {
            saldo.setText("");
            isValid = false;
            saldo.setPromptText("Este campo no puede estar vacio");
            saldo.setStyle(redField);
        }
        else if (isNumeric(saldo.getText())) {
            saldo.setText("");
            isValid = false;
            saldo.setPromptText("Debe ser un numero");
            saldo.setStyle(redField);
        }


        if (isValid) {
            Cuenta newCuenta = new Cuenta(Integer.parseInt(numero.getText()), titular.getText(), fecha.getText(), Double.parseDouble(saldo.getText()));
            listaCuentas.add(newCuenta);
            contadorCuentas++;

            etiNueva.setVisible(false);
            etiExist.setVisible(true);

            numero.setEditable(false);
            titular.setEditable(false);
            fecha.setEditable(false);
            saldo.setEditable(false);

            aceptar.setVisible(false);
            aceptar.setDisable(true);
            cancelar.setVisible(false);
            cancelar.setDisable(true);

            atras.setVisible(true);
            atras.setDisable(false);
            nueva.setVisible(true);
            nueva.setDisable(false);

            contador();
        }
    }

    public void accionCancelar() {
        numero.setPromptText("");
        titular.setPromptText("");
        fecha.setPromptText("");
        saldo.setPromptText("");

        numero.setStyle("");
        titular.setStyle("");
        fecha.setStyle("");
        saldo.setStyle("");

        etiNueva.setVisible(false);
        etiExist.setVisible(true);

        numero.setEditable(false);
        titular.setEditable(false);
        fecha.setEditable(false);
        saldo.setEditable(false);

        actualizarDatos();

        aceptar.setVisible(false);
        aceptar.setDisable(true);
        cancelar.setVisible(false);
        cancelar.setDisable(true);

        atras.setVisible(true);
        atras.setDisable(false);
        nueva.setVisible(true);
        nueva.setDisable(false);
    }

    public void contador() {
        int cont = 0;

        numExist.setText(String.valueOf(listaCuentas.size()));

        for (Cuenta cuenta : listaCuentas) {
            if (cuenta.getSaldo() >= 50000) {
                cont++;
            }
        }

        num50000.setText(String.valueOf(cont));
    }

    public void actualizarDatos() {
        numero.setText(String.valueOf(listaCuentas.get(contadorCuentas).getNumeroCuenta()));
        titular.setText(listaCuentas.get(contadorCuentas).getTitular());
        fecha.setText(listaCuentas.get(contadorCuentas).getFechApertura());
        saldo.setText(String.valueOf(listaCuentas.get(contadorCuentas).getSaldo()));
    }

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }

        try {
            Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return true;
        }

        return false;
    }
    public boolean titulardigit(String passCode){
        for (int i = 0; i < passCode.length(); i++) {
            if(Character.isDigit(passCode.charAt(i))) {
                return true;
            }
        }
        return false;
    }
    public boolean validDate(String fecha) {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);

        try {
            dateFormat.parse(fecha);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }

    public void menuContextual() {
        ContextMenu menuAtras = new ContextMenu(new MenuItem("Atrás"), new MenuItem("Back"), new MenuItem("Derrière"), new MenuItem("Atrás"));
        ContextMenu menuSiguiente = new ContextMenu(new MenuItem("Adelante"), new MenuItem("Next"), new MenuItem("Suivant"), new MenuItem("Seguinte"));

        menuSiguiente.setStyle("");
        siguiente.setContextMenu(menuSiguiente);
        atras.setContextMenu(menuAtras);
    }
}
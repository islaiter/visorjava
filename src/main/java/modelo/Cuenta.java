package modelo;

public class Cuenta {

    private int numeroCuenta;
    private String titular;
    private String fechApertura;
    private double saldo;

    public Cuenta(int numeroCuenta, String titular, String fechApertura, double saldo) {
        this.numeroCuenta = numeroCuenta;
        this.titular = titular;
        this.fechApertura = fechApertura;
        this.saldo = saldo;
    }

    public int getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(int numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getFechApertura() {
        return fechApertura;
    }

    public void setFechApertura(String fechApertura) {
        this.fechApertura = fechApertura;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

}

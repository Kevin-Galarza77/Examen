import javax.swing.*;

public class Usuario {
    private String tipoCuenta;
    private int cedula;
    private String name;
    public double saldo;
    private int edad;
    private int celular;
    private String gmail;
    public Usuario(String tipoCuenta,int cedula, String name, double saldo, int edad, int celular, String gmail) {
        this.tipoCuenta=tipoCuenta;
        this.cedula = cedula;
        this.name = name;
        this.saldo = saldo;
        this.edad = edad;
        this.celular = celular;
        this.gmail = gmail;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getCelular() {
        return celular;
    }

    public void setCelular(int celular) {
        this.celular = celular;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String tostring(){
        return "Hola "+getName()+"!\n Tu numero de Cedula es: "
        +getCedula()+"\nTu saldo actual es de: "+getSaldo();
    }
    public String tostring2(){
        return getTipoCuenta()+"\n"+getCedula()+"\n"+getName()+"\n"+getSaldo()+"\n"+getEdad()+"\n"+getCelular()
                +"\n"+getGmail();
    }
}

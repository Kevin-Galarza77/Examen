import Transacciones.Deposito;
import Transacciones.Retiro;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class Procesos {
    public static int menu(){
        String op;
        boolean a;
        int op2;
        do {
            op= JOptionPane.showInputDialog(null,"\n1.-Registrar Usuario" +
                    "\n2.-Depositar Dinero" +
                    "\n3.-Retirar Dinero" +
                    "\n0.-Salir" +
                    "\nSeleccione: ");
            a=comprobar(op);
            if (a==true){
                op2= Integer.parseInt(op);
            }else{
                JOptionPane.showMessageDialog(null,"Error. Intenta Denuevo");
                op2=-1;
            }
        }while(op2<0 || op2>3);
        return op2;
    }
    private static boolean comprobar(String a){
        int num;
        try{
            num=Integer.parseInt(a);
            return true;
        }
        catch (Exception e )
        {
            return false;
        }
    }
    public static Usuario CrearDatos (){
         String tipoCuenta=JOptionPane.showInputDialog(null,"Ingresa el tipo de Cuenta: ");
         int cedula=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa el numero de Cedula:"));
         String name=JOptionPane.showInputDialog(null,"Ingresa tu Nombre: ");
         double saldo=0;
         int edad;
         do {
             edad=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa tu edad:"));
             if (edad<18){
                 JOptionPane.showMessageDialog(null,"Solo mayores de 18 años");
             }
         }while (edad<18);
         int celular=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa tu numero Celular:"));
        String gmail=JOptionPane.showInputDialog(null,"Ingresa tu Gmail: ");
         Usuario usuario = new Usuario(tipoCuenta, cedula, name, saldo, edad, celular, gmail);
         return usuario;
    }
    public double deposito(){
        double cantidad;
        do {
            cantidad=Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa la cantidad a depositar: "));
            if (cantidad<0){
                JOptionPane.showMessageDialog(null,"Error. Intenta Denuevo");
            }
        }while (cantidad<=0);
        return cantidad;
    }
    public double retirar(){
        double cantidad;
        do {
            cantidad=Double.parseDouble(JOptionPane.showInputDialog(null,"Ingresa la cantidad a Retirar: "));
            if (cantidad<0){
                JOptionPane.showMessageDialog(null,"Error. Intenta Denuevo");
            }
        }while (cantidad<=0);
        return cantidad;
    }
    public static int indice(ArrayList<Usuario> usuarios){
       String nombre=JOptionPane.showInputDialog(null,"Ingreso de Credenciales" +
               "\nIngresa tu Nombre: ");
       int cedula=Integer.parseInt(JOptionPane.showInputDialog(null,"Ingresa tu Cedula: "));
       int i=0,indice=-1;
       for (Usuario usuario:usuarios){
           if(usuario.getName().equals(nombre) && usuario.getCedula()==cedula){
               indice=i;
           }
           i++;
       }
       return indice;
    }
    public static ArrayList<Usuario> usuarios2(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        int indice=0;
        try(BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\ESFOT\\IdeaProjects\\EXAMEN\\Usuarios.txt"))){
             String tipoCuenta;
             int cedula;
             String name;
             double saldo;
             int edad;
             int celular;
             String gmail;
            while ((tipoCuenta = br.readLine())!=null){
                cedula= Integer.parseInt(br.readLine());
                name= br.readLine();
                saldo= Double.parseDouble(br.readLine());
                edad=Integer.parseInt(br.readLine());
                celular=Integer.parseInt(br.readLine());
                gmail=br.readLine();
                Usuario usuario = new Usuario(tipoCuenta,cedula,name,saldo,edad,celular,gmail);
                usuarios.add(usuario);
            }
            JOptionPane.showMessageDialog(null,"Datos Cargados Exitosamente!");
        }catch (FileNotFoundException e){
            System.out.println("NO HAY ARCHIVO");
            throw new RuntimeException(e);
        }catch (IOException e){
            System.out.println("ERROR");
            throw new RuntimeException(e);
        }
        return usuarios;
    }
    public static void guardar(ArrayList<Usuario> usuarios){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\ESFOT\\IdeaProjects\\EXAMEN\\Usuarios.txt"))){
            for (Usuario a:usuarios){
                bw.write(a.tostring2());
            }
            System.out.println("Escritura Correcta");
        }catch (IOException e){
            System.out.println("Error en el direccionamiento");
        }
    }
    public ArrayList<Usuario> usuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        usuarios=usuarios2();
        int op;
        do {
            op=menu();
            switch (op){
                case 1:
                {
                    usuarios.add(CrearDatos());
                    break;
                }
                case 2:
                {
                    int ind=indice(usuarios);
                    if(ind<0){
                        JOptionPane.showMessageDialog(null,"EL usuario ingresado no existe!");
                    }else{
                        Deposito deposito = new Deposito(deposito());
                        double cant=usuarios.get(ind).getSaldo()+deposito.getCantidad();
                        usuarios.get(ind).setSaldo(cant);
                        JOptionPane.showMessageDialog(null,"Transaccion Exitosa");
                        JOptionPane.showMessageDialog(null,usuarios.get(ind).tostring());
                    }
                    break;
                }
                case 3:
                {
                    int ind=indice(usuarios);
                    if(ind<0){
                        JOptionPane.showMessageDialog(null,"EL usuario ingresado no existe!");
                    }else{
                        Retiro retiro = new Retiro(retirar());
                        double cant=usuarios.get(ind).getSaldo()-retiro.getCantidad();
                        if (cant<0){
                            JOptionPane.showMessageDialog(null,"Error! El saldo no es Suficiente!");
                        }else {
                            usuarios.get(ind).setSaldo(cant);
                            JOptionPane.showMessageDialog(null,"Transaccion Exitosa");
                            JOptionPane.showMessageDialog(null,usuarios.get(ind).tostring());
                        }
                    }
                }
            }
        }while (op!=0);
        return usuarios;
    }
}

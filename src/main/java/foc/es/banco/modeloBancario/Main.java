package foc.es.banco.modeloBancario;

import foc.es.banco.modeloBancario.classes.Cliente;
import foc.es.banco.modeloBancario.classes.Cuenta;
import foc.es.banco.modeloBancario.classes.CuentaAhorro;
import foc.es.banco.modeloBancario.classes.CuentaCorriente;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Cuenta> cuentas = new ArrayList<>();
    public static void main(String[] args) {



        int opcion = -1;
        Scanner sc = new Scanner(System.in);

        while(opcion != 0){
            mostrarMenu();
            opcion = sc.nextInt();
            switch(opcion){
                case 1:
                    System.out.println("Haz seleccionado la opción de crear una nueva cuenta");
                    System.out.println("0. Crear a partir de un cliente existente");
                    System.out.println("1. Crear a partir de un nuevo cliente");
                    int opcionCrear = sc.nextInt();
                    switch (opcionCrear){
                        case 1:
                            System.out.println("Introduce el nombre del usuario");
                            String nombre = sc.next();
                            System.out.println("Introduce la dirección del usuario");
                            String direccion = sc.next();
                            System.out.println("Introduce el teléfono del usuario");
                            String telefono = sc.next();
                            Cliente cliente = new Cliente(nombre, direccion, telefono);

                            System.out.println("Introduce el saldo inicial de la cuenta");
                            double saldo = 0;
                            try{
                                saldo = sc.nextDouble();

                            } catch (Exception e){
                                System.out.println("Introduce un número válido");
                            }

                            System.out.println("Introduce el tipo de cuenta");
                            System.out.println("1. Cuenta corriente");
                            System.out.println("2. Cuenta ahorro");
                            int tipoCuenta = sc.nextInt();
                            crearCuenta(sc, cliente, saldo, tipoCuenta);
                            break;

                        case 0:
                            Cliente clienteExistente = null;
                            try{
                                System.out.println("Introduce el id del cliente");
                                int id = sc.nextInt();
                                clienteExistente = buscarCliente(id);
                                if (clienteExistente == null){
                                    System.out.println("Cliente no encontrado");
                                    break;
                                }
                            } catch (Exception e){
                                System.out.println("Introduce un número válido");
                            }

                            System.out.println("Introduce el saldo inicial de la cuenta");
                            double saldo2 = 0;
                            try{
                                saldo2 = sc.nextDouble();

                            } catch (Exception e){
                                System.out.println("Introduce un número válido");
                            }

                            System.out.println("Introduce el tipo de cuenta");
                            System.out.println("1. Cuenta corriente");
                            System.out.println("2. Cuenta ahorro");
                            int tipoCuenta2 = sc.nextInt();
                            crearCuenta(sc, clienteExistente, saldo2, tipoCuenta2);
                            break;
                    }


                    break;
                case 2:
                    System.out.println("Ingresar dinero");
                    break;
                case 3:
                    System.out.println("Retirar dinero");
                    break;
                case 4:
                    System.out.println("Consultar saldo");
                    break;
                case 5:
                    System.out.println("Consultar movimientos");
                    break;
                case 0:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }

        }




    }

    private static void crearCuenta(Scanner sc, Cliente cliente, double saldo, int tipoCuenta) {
        switch (tipoCuenta){
            case 1:
                CuentaCorriente cuentaCorriente = new CuentaCorriente( saldo, cliente);
                break;
            case 2:
                double interes = 0;
                double saldoMinimo = 0;
                try{
                    System.out.println("Introduce el interés de la cuenta");
                    interes = sc.nextDouble();
                    System.out.println("Introduce el saldo mínimo de la cuenta");
                    saldoMinimo = sc.nextDouble();
                } catch (Exception e){
                    System.out.println("Introduce un número válido");
                }

                CuentaAhorro cuentaAhorro = new CuentaAhorro(saldo, cliente, interes, saldoMinimo);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void mostrarMenu(){
        System.out.println("1. Crear cuenta");
        System.out.println("2. Ingresar dinero");
        System.out.println("3. Retirar dinero");
        System.out.println("4. Consultar saldo");
        System.out.println("5. Consultar movimientos");
        System.out.println("0. Salir");
    }

    private static Cliente buscarCliente(int id){
        for (Cliente cliente : clientes){
            if (cliente.getId() == id){
                return cliente;
            }
        }
        return null;
    }
}

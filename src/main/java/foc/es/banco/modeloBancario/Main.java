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

        while (opcion != 0) {
            mostrarMenu();
            opcion = sc.nextInt();
            switch (opcion) {
                case 1:
                    System.out.println("Haz seleccionado la opción de crear un nuevo cliente");
                    try {
                        System.out.println("Introduce el nombre del usuario");
                        String nombre = sc.next();
                        System.out.println("Introduce la dirección del usuario");
                        String direccion = sc.next();
                        System.out.println("Introduce el teléfono del usuario");
                        String telefono = sc.next();

                        boolean clienteExistente = clientes.stream().anyMatch(c ->
                                c.getNombre().equals(nombre) &&
                                        c.getDireccion().equals(direccion) &&
                                        c.getTelefono().equals(telefono)
                        );

                        if (clienteExistente) {
                            System.out.println("Ya existe un cliente con los mismos atributos.");
                        } else {
                            Cliente cliente = new Cliente(nombre, direccion, telefono);
                            clientes.add(cliente);
                        }
                    } catch (Exception e) {
                        System.out.println("Introduce un número válido");
                    }
                    break;


                case 2:
                    System.out.println("Haz seleccionado la opción de crear una nueva cuenta");
                    System.out.println("Introduce el tipo de cuenta");
                    System.out.println("1. Cuenta corriente");
                    System.out.println("2. Cuenta ahorro");
                    int tipoCuenta = sc.nextInt();

                    System.out.println("0. Crear a partir de un cliente existente");
                    System.out.println("1. Crear a partir de un nuevo cliente");
                    int opcionCrear = sc.nextInt();

                    switch (opcionCrear) {
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
                            try {
                                saldo = sc.nextDouble();
                            } catch (Exception e) {
                                System.out.println("Introduce un número válido");
                            }

                            crearCuenta(sc, cliente, saldo, tipoCuenta);
                            break;

                        case 0:
                            if (clientes.isEmpty()) {
                                System.out.println("No hay clientes existentes");
                                break;
                            }

                            Cliente clienteExistente = null;
                            double saldo2 = 0;

                            try {
                                System.out.println("Introduce el id del cliente");
                                int id = sc.nextInt();
                                clienteExistente = buscarCliente(id);
                                if (clienteExistente == null) {
                                    System.out.println("Cliente no encontrado");
                                    break;
                                }

                                System.out.println("Introduce el saldo inicial de la cuenta");
                                saldo2 = sc.nextDouble();

                            } catch (Exception e) {
                                System.out.println("Introduce un número válido");
                            }
                            crearCuenta(sc, clienteExistente, saldo2, tipoCuenta);
                            break;
                    }
                    break;


                case 3:
                    System.out.println("Haz seleccionado la opción de realizar una transferencia");
                    if (cuentas.isEmpty() || cuentas.size() == 1) {
                        System.out.println("No hay cuentas suficientes existentes");
                        break;
                    }

                    try {
                        System.out.println("Introduce el número de cuenta de origen");
                        int numeroCuentaOrigen = sc.nextInt();
                        Cuenta cuentaOrigen = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuentaOrigen).findFirst().orElse(null);
                        if (cuentaOrigen == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }
                        System.out.println("Introduce el número de cuenta de destino");
                        int numeroCuentaDestino = sc.nextInt();
                        Cuenta cuentaDestino = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuentaDestino).findFirst().orElse(null);
                        if (cuentaDestino == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }

                        System.out.println("Introduce la cantidad a transferir");
                        double cantidad = sc.nextDouble();

                        if (cuentaOrigen instanceof CuentaAhorro) {
                            ((CuentaAhorro) cuentaOrigen).transferir(cuentaDestino, cantidad);
                        } else if (cuentaOrigen instanceof CuentaCorriente) {
                            ((CuentaCorriente) cuentaOrigen).transferir(cuentaDestino, cantidad);
                        }

                        System.out.println("Transferencia realizada con éxito");
                        System.out.println("Saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
                        System.out.println("Saldo de la cuenta de destino: " + cuentaDestino.getSaldo());

                    } catch (Exception e) {
                        System.out.println("Se ha producido un error");
                    }
                    break;


                case 4:
                    System.out.println("Haz seleccionado la opción de ingresar dinero");
                    if (noCuentas()) break;

                    try {
                        System.out.println("Introduce el número de cuenta");
                        int numeroCuenta = sc.nextInt();
                        Cuenta cuenta = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
                        if (cuenta == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }

                        System.out.println("Introduce la cantidad a ingresar");
                        double cantidad = sc.nextDouble();
                        cuenta.ingresar(cantidad);

                        System.out.println("Ingreso realizado con éxito");
                        System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

                    } catch (Exception e) {
                        System.out.println("Se ha producido un error");
                    }
                    break;

                case 5:
                    System.out.println("Haz seleccionado la opción de retirar dinero");
                    if (noCuentas()) break;

                    try {
                        System.out.println("Introduce el número de cuenta");
                        int numeroCuenta = sc.nextInt();
                        Cuenta cuenta = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
                        if (cuenta == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }

                        System.out.println("Introduce la cantidad a retirar");
                        double cantidad = sc.nextDouble();
                        cuenta.retirar(cantidad);

                        System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

                    } catch (Exception e) {
                        System.out.println("Se ha producido un error");
                    }
                    break;


                case 6:
                    System.out.println("Haz seleccionado la opción de consultar saldo");
                    if (noCuentas()) break;

                    try {
                        System.out.println("Introduce el número de cuenta");
                        int numeroCuenta = sc.nextInt();
                        Cuenta cuenta = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
                        if (cuenta == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }

                        System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

                    } catch (Exception e) {
                        System.out.println("Se ha producido un error");
                    }
                    break;


                case 7:
                    System.out.println("Haz seleccionado la opción de consultar movimientos");
                    if (noCuentas()) break;

                    try {
                        System.out.println("Introduce el número de cuenta");
                        int numeroCuenta = sc.nextInt();
                        Cuenta cuenta = cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
                        if (cuenta == null) {
                            System.out.println("Cuenta no encontrada");
                            break;
                        }

                        cuenta.consultarMovimientos();

                    } catch (Exception e) {
                        System.out.println("Se ha producido un error");
                    }
                    break;


                case 8:
                    System.out.println("Haz seleccionado la opción de consultar cuentas existentes");
                    if (cuentas.isEmpty()) {
                        System.out.println("No hay cuentas existentes");
                        break;
                    }

                    System.out.println("Cuentas existentes: ");
                    for (Cuenta cuenta : cuentas) {
                        System.out.printf("Número de cuenta: %d, Saldo: %.2f, Titular: %s%n", cuenta.getNumeroDeCuenta(), cuenta.getSaldo(), cuenta.getTitular().getNombre());
                    }
                    break;


                case 0:
                    break;

                default:
                    System.out.println("Opción no válida");
                    break;
            }

        }


    }

    private static boolean noCuentas() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return true;
        }
        return false;
    }

    private static void crearCuenta(Scanner sc, Cliente cliente, double saldo, int tipoCuenta) {
        switch (tipoCuenta) {
            case 1:
                CuentaCorriente cuentaCorriente = new CuentaCorriente(saldo, cliente);
                break;
            case 2:
                double interes = 0;
                double saldoMinimo = 0;
                try {
                    System.out.println("Introduce el interés de la cuenta");
                    interes = sc.nextDouble();
                    System.out.println("Introduce el saldo mínimo de la cuenta");
                    saldoMinimo = sc.nextDouble();
                } catch (Exception e) {
                    System.out.println("Introduce un número válido");
                }

                CuentaAhorro cuentaAhorro = new CuentaAhorro(saldo, cliente, interes, saldoMinimo);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void mostrarMenu() {
        System.out.println("Selecciona una opción");
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear cuenta");
        System.out.println("3. Realizar una transferencia");
        System.out.println("4. Ingresar dinero");
        System.out.println("5. Retirar dinero");
        System.out.println("6. Consultar saldo");
        System.out.println("7. Consultar movimientos");
        System.out.println("8. Consultar cuentas existentes");
        System.out.println("0. Salir");
    }

    private static Cliente buscarCliente(int id) {
        for (Cliente cliente : clientes) {
            if (cliente.getId() == id) {
                return cliente;
            }
        }
        return null;
    }
}

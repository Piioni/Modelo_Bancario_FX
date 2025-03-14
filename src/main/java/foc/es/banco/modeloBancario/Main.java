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
            try {
                opcion = Integer.parseInt(sc.nextLine());
                manejarOpcion(opcion, sc);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido");
            }
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

    private static void manejarOpcion(int opcion, Scanner sc) {
        switch (opcion) {
            case 1:
                crearCliente(sc);
                break;
            case 2:
                crearCuenta(sc);
                break;
            case 3:
                realizarTransferencia(sc);
                break;
            case 4:
                ingresarDinero(sc);
                break;
            case 5:
                retirarDinero(sc);
                break;
            case 6:
                consultarSaldo(sc);
                break;
            case 7:
                consultarMovimientos(sc);
                break;
            case 8:
                consultarCuentasExistentes();
                break;
            case 0:
                System.out.println("Saliendo...");
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static Cliente crearCliente(Scanner sc) {
        System.out.println("Introduce el nombre del usuario");
        String nombre = sc.nextLine();
        System.out.println("Introduce la dirección del usuario");
        String direccion = sc.nextLine();
        System.out.println("Introduce el teléfono del usuario");
        String telefono = sc.nextLine();

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
            System.out.println("Cliente creado con éxito");
            return cliente;
        }
        return null;
    }

    private static void crearCuenta(Scanner sc) {
        System.out.println("Introduce el tipo de cuenta");
        System.out.println("1. Cuenta corriente");
        System.out.println("2. Cuenta ahorro");
        int tipoCuenta = Integer.parseInt(sc.nextLine());

        System.out.println("0. Crear a partir de un cliente existente");
        System.out.println("1. Crear a partir de un nuevo cliente");
        int opcionCrear = Integer.parseInt(sc.nextLine());

        Cliente cliente = null;
        if (opcionCrear == 1) {
            cliente = crearCliente(sc);
        } else if (opcionCrear == 0) {
            cliente = seleccionarClienteExistente(sc);
        }

        if (cliente != null) {
            System.out.println("Introduce el saldo inicial de la cuenta");
            double saldo = Double.parseDouble(sc.nextLine());
            crearCuentaParaCliente(sc, cliente, saldo, tipoCuenta);
        }
    }

    private static Cliente seleccionarClienteExistente(Scanner sc) {
        if (clientes.isEmpty()) {
            System.out.println("No hay clientes existentes");
            return null;
        }

        System.out.println("Introduce el id del cliente");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = buscarCliente(id);
        if (cliente == null) {
            System.out.println("Cliente no encontrado");
        }
        return cliente;
    }

    private static void crearCuentaParaCliente(Scanner sc, Cliente cliente, double saldo, int tipoCuenta) {
        switch (tipoCuenta) {
            case 1:
                CuentaCorriente cuentaCorriente = new CuentaCorriente(saldo, cliente);
                cuentas.add(cuentaCorriente);
                break;
            case 2:
                System.out.println("Introduce el interés de la cuenta");
                double interes = Double.parseDouble(sc.nextLine());
                System.out.println("Introduce el saldo mínimo de la cuenta");
                double saldoMinimo = Double.parseDouble(sc.nextLine());
                CuentaAhorro cuentaAhorro = new CuentaAhorro(saldo, cliente, interes, saldoMinimo);
                cuentas.add(cuentaAhorro);
                break;
            default:
                System.out.println("Opción no válida");
                break;
        }
    }

    private static void realizarTransferencia(Scanner sc) {
        if (cuentas.size() < 2) {
            System.out.println("No hay cuentas suficientes existentes");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta de origen");
            int numeroCuentaOrigen = Integer.parseInt(sc.nextLine());
            Cuenta cuentaOrigen = buscarCuenta(numeroCuentaOrigen);
            if (cuentaOrigen == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            System.out.println("Introduce el número de cuenta de destino");
            int numeroCuentaDestino = Integer.parseInt(sc.nextLine());
            Cuenta cuentaDestino = buscarCuenta(numeroCuentaDestino);
            if (cuentaDestino == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            System.out.println("Introduce la cantidad a transferir");
            double cantidad = Double.parseDouble(sc.nextLine());

            if (cuentaOrigen instanceof CuentaCorriente) {
                ((CuentaCorriente) cuentaOrigen).transferir(cuentaDestino, cantidad);
            } else if (cuentaOrigen instanceof CuentaAhorro) {
                ((CuentaAhorro) cuentaOrigen).transferir(cuentaDestino, cantidad);
            }
            System.out.println("Transferencia realizada con éxito");
            System.out.println("Saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
            System.out.println("Saldo de la cuenta de destino: " + cuentaDestino.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido");
        }
    }

    private static void ingresarDinero(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            System.out.println("Introduce la cantidad a ingresar");
            double cantidad = Double.parseDouble(sc.nextLine());
            cuenta.ingresar(cantidad);

            System.out.println("Ingreso realizado con éxito");
            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido");
        }
    }

    private static void retirarDinero(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            System.out.println("Introduce la cantidad a retirar");
            double cantidad = Double.parseDouble(sc.nextLine());
            cuenta.retirar(cantidad);

            System.out.println("Retiro realizado con éxito");
            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido");
        }
    }

    private static void consultarSaldo(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido");
        }
    }

    private static void consultarMovimientos(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("Cuenta no encontrada");
                return;
            }

            cuenta.consultarMovimientos();

        } catch (NumberFormatException e) {
            System.out.println("Introduce un número válido");
        }
    }

    private static void consultarCuentasExistentes() {
        if (cuentas.isEmpty()) {
            System.out.println("No hay cuentas existentes");
            return;
        }

        System.out.println("Cuentas existentes: ");
        for (Cuenta cuenta : cuentas) {
            System.out.printf("Número de cuenta: %d, Saldo: %.2f, Titular: %s%n", cuenta.getNumeroDeCuenta(), cuenta.getSaldo(), cuenta.getTitular().getNombre());
        }
    }

    private static Cliente buscarCliente(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private static Cuenta buscarCuenta(int numeroCuenta) {
        return cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
    }
}
package foc.es.banco.modeloBancario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import foc.es.banco.modeloBancario.classes.Cliente;
import foc.es.banco.modeloBancario.classes.Cuenta;
import foc.es.banco.modeloBancario.classes.CuentaAhorro;
import foc.es.banco.modeloBancario.classes.CuentaCorriente;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Cliente> clientes = new ArrayList<>();
    private static final List<Cuenta> cuentas = new ArrayList<>();
    private static final Gson gson = new Gson();


    public static void main(String[] args) {
        int opcion = -1;
        Scanner sc = new Scanner(System.in);
        cargarDatos();

        while (opcion != 0) {
            mostrarMenu();
            try {
                opcion = Integer.parseInt(sc.nextLine());
                manejarOpcion(opcion, sc);
            } catch (NumberFormatException e) {
                System.out.println("Introduce un número válido");
            }
        }
        guardarDatos();
    }

    private static void guardarDatos() {
        try (FileWriter writer = new FileWriter("src/main/resources/clientes.json")) {
            gson.toJson(clientes, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar clientes: " + e.getMessage());
        }

        try (FileWriter writer = new FileWriter("src/main/resources/clientes.json")) {
            gson.toJson(cuentas, writer);
        } catch (IOException e) {
            System.out.println("Error al guardar cuentas: " + e.getMessage());
        }
    }

  private static void cargarDatos() {
      try (FileReader reader = new FileReader("src/main/resources/clientes.json")) {
          Type clienteListType = new TypeToken<List<Cliente>>() {}.getType();
          List<Cliente> loadedClientes = gson.fromJson(reader, clienteListType);
          if (loadedClientes != null) {
              clientes.addAll(loadedClientes);
          }
      } catch (IOException e) {
          System.out.println("Error al cargar clientes: " + e.getMessage());
          crearArchivoSiNoExiste("src/main/resources/clientes.json");
      }

      try (FileReader reader = new FileReader("src/main/resources/cuentas.json")) {
          Type cuentaListType = new TypeToken<List<Cuenta>>() {}.getType();
          List<Cuenta> loadedCuentas = gson.fromJson(reader, cuentaListType);
          if (loadedCuentas != null) {
              cuentas.addAll(loadedCuentas);
          }
      } catch (IOException e) {
          System.out.println("Error al cargar cuentas: " + e.getMessage());
          crearArchivoSiNoExiste("src/main/resources/cuentas.json");
      }
  }

  private static void crearArchivoSiNoExiste(String ruta) {
      try {
          File archivo = new File(ruta);
          if (archivo.createNewFile()) {
              System.out.println("Archivo creado: " + archivo.getName());
          } else {
              System.out.println("El archivo ya existe.");
          }
      } catch (IOException e) {
          System.out.println("Error al crear el archivo: " + e.getMessage());
      }
  }


    private static void mostrarMenu() {
        System.out.println("\nSelecciona una opción\n");
        System.out.println("1. Crear cliente");
        System.out.println("2. Crear cuenta");
        System.out.println("3. Realizar una transferencia");
        System.out.println("4. Ingresar dinero");
        System.out.println("5. Retirar dinero");
        System.out.println("6. Consultar saldo");
        System.out.println("7. Consultar movimientos");
        System.out.println("8. Consultar cuentas existentes");
        System.out.println("0. Salir\n");
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
                System.out.println("\nSaliendo...\n");
                break;
            default:
                System.out.println("\nOpción no válida\n");
                break;
        }
    }

    private static Cliente crearCliente(Scanner sc) {
        System.out.println("\nIntroduce el nombre del usuario");
        String nombre = sc.nextLine().trim();
        while (nombre.isEmpty()) {
            System.out.println("El nombre no puede estar vacío. Introduce el nombre del usuario");
            nombre = sc.nextLine().trim();
        }

        System.out.println("Introduce la dirección del usuario");
        String direccion = sc.nextLine().trim();
        while (direccion.isEmpty()) {
            System.out.println("La dirección no puede estar vacía. Introduce la dirección del usuario");
            direccion = sc.nextLine().trim();
        }

        System.out.println("Introduce el teléfono del usuario");
        String telefono = sc.nextLine().trim();
        while (!telefono.matches("\\d{10}")) {
            System.out.println("El teléfono debe tener 10 dígitos. Introduce el teléfono del usuario");
            telefono = sc.nextLine().trim();
        }

        final String finalNombre = nombre;
        final String finalDireccion = direccion;
        final String finalTelefono = telefono;

        boolean clienteExistente = clientes.stream().anyMatch(c ->
                c.getNombre().equals(finalNombre) &&
                        c.getDireccion().equals(finalDireccion) &&
                        c.getTelefono().equals(finalTelefono)
        );

        if (clienteExistente) {
            System.out.println("\nYa existe un cliente con los mismos atributos.\n");
        } else {
            Cliente cliente = new Cliente(nombre, direccion, telefono);
            clientes.add(cliente);
            System.out.println("\nCliente creado con éxito\n");
            return cliente;
        }
        return null;
    }

    private static void crearCuenta(Scanner sc) {
        System.out.println("\nIntroduce el tipo de cuenta");
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
            System.out.println("\nNo hay clientes existentes\n");
            return null;
        }

        System.out.println("Introduce el id del cliente");
        int id = Integer.parseInt(sc.nextLine());
        Cliente cliente = buscarCliente(id);
        if (cliente == null) {
            System.out.println("\nCliente no encontrado\n");
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
                System.out.println("\nOpción no válida\n");
                break;
        }
    }

    private static void realizarTransferencia(Scanner sc) {
        if (cuentas.size() < 2) {
            System.out.println("\nNo hay cuentas suficientes existentes\n");
            return;
        }

        consultarCuentasExistentes();

        try {
            System.out.println("Introduce el número de cuenta de origen");
            int numeroCuentaOrigen = Integer.parseInt(sc.nextLine());
            Cuenta cuentaOrigen = buscarCuenta(numeroCuentaOrigen);
            if (cuentaOrigen == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            System.out.println("Introduce el número de cuenta de destino");
            int numeroCuentaDestino = Integer.parseInt(sc.nextLine());
            Cuenta cuentaDestino = buscarCuenta(numeroCuentaDestino);
            if (cuentaDestino == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            System.out.println("Introduce la cantidad a transferir");
            double cantidad = Double.parseDouble(sc.nextLine());

            if (cuentaOrigen instanceof CuentaCorriente) {
                ((CuentaCorriente) cuentaOrigen).transferir(cuentaDestino, cantidad);
            } else if (cuentaOrigen instanceof CuentaAhorro) {
                ((CuentaAhorro) cuentaOrigen).transferir(cuentaDestino, cantidad);
            }
            System.out.println("\nTransferencia realizada con éxito\n");
            System.out.println("Saldo de la cuenta de origen: " + cuentaOrigen.getSaldo());
            System.out.println("Saldo de la cuenta de destino: " + cuentaDestino.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("\nIntroduce un número válido\n");
        }
    }

    private static void ingresarDinero(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("\nNo hay cuentas existentes\n");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            System.out.println("Introduce la cantidad a ingresar");
            double cantidad = Double.parseDouble(sc.nextLine());
            cuenta.ingresar(cantidad);

            System.out.println("\nIngreso realizado con éxito\n");
            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("\nIntroduce un número válido\n");
        }
    }

    private static void retirarDinero(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("\nNo hay cuentas existentes\n");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            System.out.println("Introduce la cantidad a retirar");
            double cantidad = Double.parseDouble(sc.nextLine());
            cuenta.retirar(cantidad);

            System.out.println("\nRetiro realizado con éxito\n");
            System.out.println("Saldo de la cuenta: " + cuenta.getSaldo());

        } catch (NumberFormatException e) {
            System.out.println("\nIntroduce un número válido\n");
        }
    }

    private static void consultarSaldo(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("\nNo hay cuentas existentes\n");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            System.out.println("\nSaldo de la cuenta: " + cuenta.getSaldo() + "\n");

        } catch (NumberFormatException e) {
            System.out.println("\nIntroduce un número válido\n");
        }
    }

    private static void consultarMovimientos(Scanner sc) {
        if (cuentas.isEmpty()) {
            System.out.println("\nNo hay cuentas existentes\n");
            return;
        }

        try {
            System.out.println("Introduce el número de cuenta");
            int numeroCuenta = Integer.parseInt(sc.nextLine());
            Cuenta cuenta = buscarCuenta(numeroCuenta);
            if (cuenta == null) {
                System.out.println("\nCuenta no encontrada\n");
                return;
            }

            cuenta.consultarMovimientos();

        } catch (NumberFormatException e) {
            System.out.println("\nIntroduce un número válido\n");
        }
    }

    private static void consultarCuentasExistentes() {
        if (cuentas.isEmpty()) {
            System.out.println("\nNo hay cuentas existentes\n");
            return;
        }

        System.out.println("\nCuentas existentes: ");
        for (Cuenta cuenta : cuentas) {
            System.out.printf("Número de cuenta: %d, Saldo: %.2f, Titular: %s%n", cuenta.getNumeroDeCuenta(), cuenta.getSaldo(), cuenta.getTitular().getNombre());
        }
        System.out.println();
    }


    private static Cliente buscarCliente(int id) {
        return clientes.stream().filter(c -> c.getId() == id).findFirst().orElse(null);
    }

    private static Cuenta buscarCuenta(int numeroCuenta) {
        return cuentas.stream().filter(c -> c.getNumeroDeCuenta() == numeroCuenta).findFirst().orElse(null);
    }
}
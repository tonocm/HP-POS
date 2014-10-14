import java.io.*;
class Prototype
{
public static void main (String [] args) throws IOException
{
  String input, output;
  
  PrintWriter stdOut = new PrintWriter(System.out, true);
  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  
  stdOut.println("Bienvenido a HP Store!");
  stdOut.println();
  stdOut.println("Nombre de Usuario:");
  input = stdIn.readLine();
  stdOut.println("Contrase–a:");
  input = stdIn.readLine();
  stdOut.println("Tipo de Usuario: Empleado");
  stdOut.println("Bienvenido!");
  stdOut.println();
  stdOut.println("*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*~*");
  stdOut.println("Menu:"); 
  stdOut.println("1. Hacer una Venta");
  stdOut.println("2. Mis Estadisticas");
  input = stdIn.readLine();
}
}
  
import java.io.*;
class Prototype
{
public static void main (String [] args) throws IOException
{
  String a;
  
  PrintWriter stdOut = new PrintWriter(System.out, true);
  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  
  stdOut.println("ID del Producto");
  a=stdIn.readLine();
  stdOut.println("Laptop HP Envy UltraBook");
  stdOut.println("Descripci—n del Producto: Laptop HP Envy Color Gris de 15 Pulgadas con Windows 7");
  stdOut.println("Numero de Serie: 55x55x55x55x");
  stdOut.println("Precio: $10,000 MXN");
  stdOut.println("El Producto es Correcto? (Si/No)");
  a=stdIn.readLine();
}
}
import java.io.*;
class Prototype
{
public static void main (String [] args) throws IOException
{
  String a;
  
  PrintWriter stdOut = new PrintWriter(System.out, true);
  BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  
  stdOut.println("Nombre de la Persona:");
  a = stdIn.readLine();
  stdOut.println("RFC de la PERSONA:");
  a=stdIn.readLine();
  stdOut.println("Los Caracteres del RFC Son Correctos...");
  stdOut.println("Revisar RFC: " + a + " Presione 1 Para Continuar o 2 Para Modificarlo");
  a = stdIn.readLine();
  stdOut.println("...");
  stdOut.println("Enviando Informaci—n al Servidor");
  stdOut.println("Registro Exitoso!");
  stdOut.println();
  stdOut.println("La Informaci—n del Cliente Fue Guardada Con Exito");
  stdOut.println("Imprimiendo Recibo...");
  stdOut.println("Felicidades Por su Venta!");
}
}
import java.io.*;
import java.util.*;
/**
 * HŽctor Antonio C‡rdenas Marcos
 * Tecnologico de Monterrey - Campus Santa Catarina
 * purpose of prog
 * date
 * candidate number
 **/
class Alfa
{
  public static void main (String[] args) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String username, password;
    boolean loginCheck = false;
    int menu;
    Product cart[]= new Product[10];
    Product products[] = new Product[150];
    
    File inventory = new File("inventory.dat");
    
    if(inventory.exists())
    {
      products = fillArray(products, inventory);
      stdOut.println("Se carg— Exitosamente el Inventario...");
    }
    menu=0;
    username=null;
    
    while(loginCheck == false)
    {
      stdOut.println("Usuario:");
      username=stdIn.readLine();
      if(!username.equalsIgnoreCase("exit"))
      {
        stdOut.println("Contrase–a:");
        password=stdIn.readLine();
        loginCheck= verifyLogin(username, password);
        if(loginCheck == false)
          stdOut.println("El Usuario y/o Contrase–a no Coinciden, Intenta de Nuevo");
      }
      else
        loginCheck = true;
    }
    
    while(!username.equalsIgnoreCase("exit"))
    {
      stdOut.println("Bienvenido " + username+"!");
      
      while(menu != 6)
      {
        stdOut.println("Menu:");
        stdOut.println("1 - Venta");
        stdOut.println("2 - Busqueda de Cliente");
        stdOut.println("3 - Ventas del D’a");
        stdOut.println("4 - Ventas del Mes");
        stdOut.println("5 - Buscar Producto");
        stdOut.println("6 - REGRESAR A LA PANTALLA DE INICIO");
        if(username.equals("a"))
        {
          stdOut.println("7 - Agregar Producto al Inventario");
          stdOut.println("8 - Editar Inventario");
        }
        menu = Integer.parseInt(stdIn.readLine());
        switch(menu)
        {
          case(1):
          {
            selectProducts(cart, products);
            //General Data:
            //sell(); //this method will enclose all the selling procedures!
          }
          break;
          case(2):
          {
            searchClient(); //general searching method...
          }
          break;
          case(3):
          {
            statistics(username, menu); //will need a sort sub-method
          }
          break;
          case(4):
          {
            statistics(username, menu); //will need a sort sub-method
          }
          break;
          case(5):
          {
            searchProduct(); //general searching method...
          }
          break;
          case(6):
          {
            stdOut.println("Cerrando Sesi—n... Muchas Gracias!");
          }
          break;
          case(7):
          {
            if(username.equals("a")) //a = name of the admin!!
              inventory(menu);
            else
              stdOut.println("Error! Favor de escribir una opci—n numerica valida del menœ");
          }
          break;
          case(8):
          {
            if(username.equals("a")) // a = name of the admin!!
              inventory(menu);
            else
              stdOut.println("Error! Favor de escribir una opci—n numerica valida del menœ");
          }
          break;
          default:
          {
            stdOut.println("Error! Favor de escribir una opci—n numerica valida del menœ");
          }
          break;
        }
      }
      
      //Reset Variables
      loginCheck = false;
      menu = 0;
      
      while(loginCheck == false)
      {
        stdOut.println("Usuario:");
        username=stdIn.readLine();
        if(!username.equalsIgnoreCase("exit"))
        {
          stdOut.println("Contrase–a:");
          password=stdIn.readLine();
          loginCheck= verifyLogin(username, password);
          if(loginCheck == false)
            stdOut.println("El Usuario y/o Contrase–a no Coinciden, Intenta de Nuevo");
        }
        else
          loginCheck = true;
      }
    }
  }
  
  public static boolean verifyLogin(String username, String password)
  {
    boolean test = false;
    if(username.equals("a") && password.equals("1")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    if(username.equals("b") && password.equals("2")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    if(username.equals("c") && password.equals("3")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    return test;
  }
  public static void sell()
  {
  }
  public static void searchClient()
  {
  }
  public static void statistics(String username, int option)
  {
  }
  public static void searchProduct()
  {
  }
  public static void inventory(int option) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
  }
  public static void selectProducts(Product cart[], Product products[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String product;
    int i=0, serial=0, j=0;
    boolean found = false;
    char confirm;
    
    do
    {
      stdOut.println("ID del Producto que Desea Vender:");
      product = stdIn.readLine();
      stdOut.println("Resultados para " + product+":");
      
      try
      {
        while(found = false && i < products.length)
        {
          if(product.equalsIgnoreCase(products[i].getIdNumber()))
          {
            stdOut.println(products[i].getName());
            stdOut.println(products[i].getDescription());
            found = true;
          }
          else
            i++;
        }
      }
      catch(NullPointerException e)
      {
        i=i+1;
      }
      if(found == true)
      {
        try
        {
          for(i=0; i < products.length; i++)
          {
            if (product.equalsIgnoreCase(products[i].getIdNumber()))
            {
              stdOut.println(i + " - " + products[i].getSerialNumber());
            }
            i++;
          }
        }
        catch(NullPointerException e)
        {
          i=i+1;
        }
        stdOut.println("Introduzca el numero a la izquierda del Numero De Serie correspondiente");
        serial = Integer.parseInt(stdIn.readLine());
        cart[j] = products[serial];
        j++;
      }
      else
      {
        stdOut.println("No se Encontr— el Producto en la Base de Datos...");
      }
      stdOut.println("A–adir Otro Producto?");
      confirm = stdIn.readLine().toUpperCase().charAt(0);
    }
    while(confirm == 'S');
  }
  public static Product[] fillArray(Product[] products, File inventory) throws IOException
  {
    ObjectInputStream inFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inventory)));
    try{
      products = (Product[]) inFile.readObject();
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("No se Encontr— el Archivo! Contacte a su Provedor de Software");
    }
    catch(EOFException e)
    {
      System.out.println("Archivo leeido exitosamente!!!");
    }
    inFile.close();
    return products;
  }
  public static void saveFile(Product products[], File inventory) throws IOException
  {
    ObjectOutputStream outFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(inventory)));
    outFile.writeObject(products);
    System.out.println("File Wrote Successfully...");
    outFile.close();
  }
}
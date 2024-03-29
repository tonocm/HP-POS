import java.io.*;
import java.util.*;
import java.awt.Desktop;
/**
 * H�ctor Antonio C�rdenas Marcos
 * Tecnologico de Monterrey - Campus Santa Catarina
 * purpose of prog
 * date
 * candidate number
 **/
class HPStoreSystemBeta
{
  public static void main (String[] args) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    
    //Declaring and Initializing Variables
    String username, password, stars;
    boolean loginCheck = false, test = false;
    int menu, clientCounter = 0, i = 0, pCounter = 0;
    Product cart[] = new Product[10];
    Product products[] = new Product[150];
    Client clients[] = new Client[3650];
    
    File inventory = new File("inventory.dat");
    File database = new File("clients.dat");
    
    if(inventory.exists()) //Testing if File exists
    {
      products = fillArrayProducts(products, inventory); //Loading Array
      stdOut.println("Se carg� Exitosamente el Inventario...");
    }
    
    if(database.exists()) //Testing if File exists
    {
      clients = fillArrayClients(clients, database); //Loading Array
      stdOut.println("Se carg� Exitosamente la Base de Datos...");
    }
    
    menu=0;
    username=null;
    
    while(loginCheck == false) //Login Screen Test
    {
      stdOut.println("Usuario:");
      username=stdIn.readLine();
      if(!username.equalsIgnoreCase("exit"))
      {
        stdOut.println("Contrase�a: (Cuidado con Mayusculas y Minusculas)");
        password=stdIn.readLine();
        loginCheck= verifyLogin(username, password);
        if(loginCheck == false)
          stdOut.println("El Usuario y/o Contrase�a no Coinciden, Intenta de Nuevo");
      }
      else
        loginCheck = true;
    }
    
    while(!username.equalsIgnoreCase("exit")) //Return to Login
    {
      stars = starsLarge(58);
      
      stdOut.println(stars);
      stdOut.println(starsSide()+ "                     Bienvenido " + username+"!                      "+ starsSide());
      stdOut.println(stars);
      
      while(menu != 6) //Menu
      {
        stdOut.println("Menu:");
        stdOut.println("1 - Venta");
        stdOut.println("2 - Busqueda de Cliente");
        stdOut.println("3 - Ventas del D�a");
        stdOut.println("4 - Ventas del Mes");
        stdOut.println("5 - Buscar Producto");
        stdOut.println("6 - REGRESAR A LA PANTALLA DE INICIO");
        if(username.equals("a"))
        {
          stdOut.println("7 - Agregar Producto al Inventario");
          stdOut.println("8 - Editar Inventario");
        }
        while(test == false)
        {
          test = true;
          try{
            menu = Integer.parseInt(stdIn.readLine());
          }
          catch(NumberFormatException e)
          {
            stdOut.println("Utilize Solamente Numeros..");
            test = false;
          }
        }
        test = false; // Resetting Sentinel
        switch(menu)
        {
          case(1):
          {
            for(i = 0; i < clients.length; i++)
            {
              if(clients[i] != null)
                pCounter++;
            }
            i = clients.length - pCounter;
            if(i < 20)
              stdOut.println("Precauci�n, Solamente Dispones de " + i + " Espacios Para Registrar Clientes... Contactar Administrador del Sistema");
            i = 0; //Resetting Variable
            
            if(pCounter < clients.length)
            {
              selectProducts(cart, products);
              //From now on, use cart[] as the array
              
              //General Client's Data without products
              if(cart[0] != null)
              {
                clientCounter = clientsInfo(clients, username);
                //Client has been created but products had not been added
                
                //Finalize Sale
                sell(cart, clients, clientCounter);
                
                //Save Clients and Products
                saveFileClients(clients, database);
                saveFileProducts(products, inventory);
                
                //Print The Info
                printing(clients, products, clientCounter);
              }
              //Resetting cart
              for(i = 0; i< cart.length; i++)
              {
                cart[i] = null;
              }
            }
            else
              stdOut.println("Lo Sentimos, El Archivo Esta Lleno.. Contacte Al Proveedor del Sistema Inmediatamente");
          }
          break;
          case(2):
          {
            searchClient(clients); //general searching method...
          }
          break;
          case(3):
          {
            statistics(username, menu, clients); //will need a sort sub-method
          }
          break;
          case(4):
          {
            statistics(username, menu, clients); //will need a sort sub-method
          }
          break;
          case(5):
          {
            searchProduct(products); //general searching method...
          }
          break;
          case(6):
          {
            stdOut.println("Cerrando Sesi�n... Muchas Gracias!");
          }
          break;
          case(7):
          {
            if(username.equals("a")) //a = name of the admin!!
            {
              inventory(menu, products);
              saveFileProducts(products, inventory);
            }
            else
              stdOut.println("Error! Favor de escribir una opci�n numerica valida del men�");
          }
          break;
          case(8):
          {
            if(username.equals("a")) // a = name of the admin!!
            {
              inventory(menu, products);
              saveFileProducts(products, inventory);
            }
            else
              stdOut.println("Error! Favor de escribir una opci�n numerica valida del men�");
          }
          break;
          default:
          {
            stdOut.println("Error! Favor de escribir una opci�n numerica valida del men�");
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
          stdOut.println("Contrase�a:");
          password=stdIn.readLine();
          loginCheck= verifyLogin(username, password);
          if(loginCheck == false)
            stdOut.println("El Usuario y/o Contrase�a no Coinciden, Intenta de Nuevo");
        }
        else
          loginCheck = true;
      }
    }
  }
  
  //Methods:
  public static boolean verifyLogin(String username, String password)
  {
    boolean test = false;
    if(username.equalsIgnoreCase("a") && password.equals("1")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    if(username.equalsIgnoreCase("b") && password.equals("2")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    if(username.equalsIgnoreCase("c") && password.equals("3")) //EDIT THIS DATA WHEN USERNAME AND PASSWORD ARE REGISTRED!
      test = true;
    return test;
  }
  
  public static void searchClient(Client clients[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String name, stars;
    int i = 0;
    boolean found = false, testTest = false;
    char test = 'I'; //Initializing Variable
    
    stars = starsLarge(58);
    
    while(test != 'N')
    {
      stdOut.println("Nombre del Cliente que Desea Buscar:");
      name = stdIn.readLine();
      while(found == false && i < clients.length)
      {
        try{
          if(name.equalsIgnoreCase(clients[i].getName()))
          {
            found = true;
            
            stdOut.println("Nombre: " + clients[i].getName());
            stdOut.println("Telefono: " + clients[i].getPhoneNumber());
            stdOut.println("Email: " + clients[i].getEmail());
            stdOut.println();
            stdOut.println("Calle: " + clients[i].getStreet());
            stdOut.println("Numero de Casa: " + clients[i].getHouseNumber());
            stdOut.println("Colonia: " + clients[i].getNeighborhood());
            stdOut.println("Ciudad: " + clients[i].getCity());
            stdOut.println("Estado: " + clients[i].getState());
            stdOut.println("Codigo Postal: " + clients[i].getZipCode());
            stdOut.println("Razon Social: " + clients[i].getRazonSocial());
            stdOut.println("RFC: " + clients[i].getOfficialID());
            stdOut.println();
          }
          else
            i++;
        }
        catch(NullPointerException e)
        {
          i=i+1;
        }
      }
      
      if(found == true)
      {
        for(i=i; i < clients.length; i++)
        {
          try
          {
            if(name.equalsIgnoreCase(clients[i].getName()))
            {
              stdOut.println(stars);
              stdOut.println(clients[i].getPaymentArrayString());
              stdOut.println("* Venta: $" + clients[i].getPrice());
              stdOut.println(clients[i].getCartString());
              stdOut.println("* Vendedor: " + clients[i].getEmployee());
              stdOut.println("* Dia: " + clients[i].getDay());
              stdOut.println("* Mes: " + clients[i].getMonth());
              stdOut.println("* Hora: " + clients[i].getHours());
              stdOut.println("* A�o: " + clients[i].getYear());
              stdOut.println("*");
              stdOut.println("* Numero de Tarjeta: " + clients[i].getCardNumber());
              stdOut.println("* Fecha de Expiraci�n: " + clients[i].getExpiracyDate());
              stdOut.println("* Nombre del Banco: " + clients[i].getBankName());
              stdOut.println("* Numero de Cheque: " + clients[i].getCheckNumber());
              stdOut.println("* Clave de Autorizaci�n: " + clients[i].getAuthorizationNumber());
              stdOut.println(stars);
              stdOut.println();
            }
            else
              i++;
          }
          catch(NullPointerException e)
          {
            i=i;
          }
        }
      }
      else
        stdOut.println("No se Encontr� el Cliente...");
      //Resetting Vars
      found = false;
      i=0;
      
      stdOut.println("Desea Buscar de Nuevo? (S/N)");
      while(testTest == false)
      {
        try{
          test = stdIn.readLine().toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e)
        {
          test = 'A'; //to neglify the next if
        }
        if(test == 'N' || test == 'S')
          testTest = true;
        else
          stdOut.println("Escriba Si o No Para Buscar un Cliente de Nuevo");
      }
      testTest = false; //Resetting value
    }
  }
  
  public static void statistics(String username, int option, Client clients[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    Calendar today = Calendar.getInstance();
    
    String employee;
    int search = 0, i = 0, hourCheck, dayCheck, checkMonth, checkYear;
    double total = 0, totalA = 0, totalB = 0, totalC = 0;
    boolean test = false;
    
    hourCheck = today.get(today.HOUR)+1;
    dayCheck = today.get(today.DATE);
    checkMonth = today.get(today.MONTH)+1;
    checkYear = today.get(today.YEAR);
    
    if(option == 4) //Sales of the MONTH
    {
      if(username.equals("a")) //ADministrator!!!
      {
        stdOut.println("1 - Busqueda Por Empleado");
        stdOut.println("2 - Busqueda General");
        
        while(test == false)
        {
          try{
            search = Integer.parseInt(stdIn.readLine());
            if(search == 1 || search == 2)
              test = true;
            else
              stdOut.println("Escriba solamente '1' o '2'");
          }
          catch(NumberFormatException e)
          {
            stdOut.println("Escriba Solamente Numeros...");
            test = false;
          }
        }
        test = false; // Resetting Sentinel
        
        if(search == 1)
        {
          stdOut.println("Nombre del Empleado"); //Name or ID........ I need to check that
          employee = stdIn.readLine();
          
          stdOut.println("Resultados para " + employee);
          
          for(i=0; i < clients.length; i++)
          {
            try
            {
              if(employee.equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() && checkYear == clients[i].getYear())
              {
                total = total + clients[i].getPrice();
              }
            }
            catch(NullPointerException e)
            {
              i=i;
            }
          }
          
          stdOut.println("Monto Total del Mes:");
          stdOut.println("$"+ total);
          stdOut.println("Promedio de Venta por Semana:");
          stdOut.println("$"+ (total/4));
        }
        else
        {
          for(i=0; i < clients.length; i++)
          {
            try
            {
              if("a".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() && checkYear == clients[i].getYear())
                totalA = totalA + clients[i].getPrice();
              else if("b".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() && checkYear == clients[i].getYear())
                totalB = totalB + clients[i].getPrice();
              else if("c".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() && checkYear == clients[i].getYear())
                totalC = totalC + clients[i].getPrice();
              else
                i=i;
            }
            catch(NullPointerException e)
            {
              i=i;
            }
          }
          
          stdOut.println("Resultados para a -----------------------------------------------------------"); //Manually Input the Three Different Employee Username
          stdOut.println("Monto Total del Mes:");
          stdOut.println("$"+ totalA);
          stdOut.println("Promedio de Venta por Semana:");
          stdOut.println("$"+ (totalA/4));
          
          stdOut.println("Resultados para b -----------------------------------------------------------"); //Manually Input the Three Different Employee Username
          stdOut.println("Monto Total del Mes:");
          stdOut.println("$"+ totalB);
          stdOut.println("Promedio de Venta por Semana:");
          stdOut.println("$"+ (totalB/4));
          
          stdOut.println("Resultados para c -----------------------------------------------------------"); //Manually Input the Three Different Employee Username
          stdOut.println("Monto Total del Mes:");
          stdOut.println("$"+ totalC);
          stdOut.println("Promedio de Venta por Semana:");
          stdOut.println("$"+ (totalC/4));
          
          stdOut.println("Monto TOTAL GENERAL:");
          stdOut.println("$" + (totalA+totalB+totalC));
        }
      }
      else
      {
        stdOut.println("Resultados para "+ username);
        
        for(i=0; i < clients.length; i++)
        {
          try
          {
            if(username.equalsIgnoreCase(clients[i].getName()) && checkMonth == clients[i].getMonth())
            {
              total = total + clients[i].getPrice();
            }
          }
          catch(NullPointerException e)
          {
            i=i;
          }
        }
        stdOut.println("Monto Total del Mes: $");
        stdOut.println("$"+ total);
        stdOut.println("Promedio de Venta por Semana: $" + (total/4));
      }
    }
    else //Sales of the DAAAAYYYY
    {      
      if(username.equals("a")) //Administrator!!!
      {
        stdOut.println("1 - Busqueda Por Empleado");
        stdOut.println("2 - Busqueda General");
        while(test == false)
        {
          try{
            search = Integer.parseInt(stdIn.readLine());
            if(search == 1 || search == 2)
              test = true;
            else
              stdOut.println("Escriba solamente '1' o '2'");
          }
          catch(NumberFormatException e)
          {
            stdOut.println("Escriba Solamente Numeros...");
            test = false;
          }
        }
        test = false; // Resetting Sentinel
        
        if(search == 1)
        {
          stdOut.println("Nombre del Empleado"); //Name or ID........ I need to check that
          employee = stdIn.readLine();
          
          stdOut.println("Resultados para " + employee);
          
          for(i=0; i < clients.length; i++)
          {
            try
            {
              if(employee.equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() &&
                 checkYear == clients[i].getYear() && dayCheck == clients[i].getDay())
                total = total + clients[i].getPrice();
            }
            catch(NullPointerException e)
            {
              i=i;
            }
          }
          
          stdOut.println("Monto Total del Dia:");
          stdOut.println("$"+ total);
        }
        else
        {
          for(i=0; i < clients.length; i++)
          {
            try
            {
              if("a".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() &&
                 checkYear == clients[i].getYear() && dayCheck == clients[i].getDay())
                totalA = totalA + clients[i].getPrice();
              else if("b".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() &&
                      checkYear == clients[i].getYear() && dayCheck == clients[i].getDay())
                totalB = totalB + clients[i].getPrice();
              else if("c".equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() &&
                      checkYear == clients[i].getYear() && dayCheck == clients[i].getDay())
                totalC = totalC + clients[i].getPrice();
              else
                i=i;
            }
            catch(NullPointerException e)
            {
              i=i;
            }
          }
          
          stdOut.println("Resultados para a -----------------------------------------------------------"); //Manually Input the Three Different Employee Usernamed
          stdOut.println("Monto Total del Dia:");
          stdOut.println("$"+ totalA);
          
          stdOut.println("Resultados para b -----------------------------------------------------------"); //Manually Input the Three Different Employee Username
          stdOut.println("Monto Total del Dia:");
          stdOut.println("$"+ totalB);
          
          stdOut.println("Resultados para c -----------------------------------------------------------"); //Manually Input the Three Different Employee Username
          stdOut.println("Monto Total del Dia:");
          stdOut.println("$"+ totalC);
          
          stdOut.println("Monto TOTAL GENERAL:");
          stdOut.println("$" + (totalA+totalB+totalC));
        }
      }
      else //non admin
      {
        stdOut.println("Resultados para "+ username); //Manually Input the Three Different Employee Usernamed
        
        for(i=0; i < clients.length; i++)
        {
          try
          {
            if(username.equalsIgnoreCase(clients[i].getEmployee()) && checkMonth == clients[i].getMonth() &&
               checkYear == clients[i].getYear() && dayCheck == clients[i].getDay())
              total = total + clients[i].getPrice();
          }
          catch(NullPointerException e)
          {
            i=i;
          }
        }
        
        stdOut.println("Monto Total del Dia:");
        stdOut.println("$"+ total);
      }
    }
  }
  
  public static void searchProduct(Product products[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String productID;
    int i = 0, j = 0;
    boolean found = false, testTest = false;
    char test = 'I'; //Initializng Variable
    
    while(test != 'N')
    {
      stdOut.println("ID del Producto que Desea Buscar:");
      productID = stdIn.readLine();
      
      
      while(i < products.length && found == false)
      {
        try
        {
          if(productID.equalsIgnoreCase(products[i].getIdNumber()))
          {
            stdOut.println("ID: " + products[i].getIdNumber());
            stdOut.println("Nombre: " + products[i].getName());
            stdOut.println("Precio: $" + products[i].getPrice());
            stdOut.println("Descripci�n: " + products[i].getDescription());
            found = true;
          }
          else
            i++;
        }
        catch(NullPointerException e)
        {
          i++;
        }
      }
      stdOut.println("Numeros de Serie:");
      if(found == true)
      {
        for(j=i; j<products.length; j++)
        {
          try{
            if(productID.equalsIgnoreCase(products[j].getIdNumber()))
              stdOut.println(products[j].getSerialNumber());
          }
          catch(NullPointerException e)
          {
            j=j;
          }
        }
      }
      
      if(found == false)
        stdOut.println("No se Encontr� el Producto...");
      
      //Resetting Search Variables
      found = false;
      i=0;
      
      stdOut.println("Desea Buscar de Nuevo? (S/N)");
      while(testTest == false)
      {
        try{
          test = stdIn.readLine().toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e)
        {
          test = 'A'; //To neglect the next if...
        }
        if(test == 'N' || test == 'S')
          testTest = true;
        else
          stdOut.println("Escriba Si o No Para Buscar de Nuevo un Producto");
      }
      testTest = false;
    }
  }
  
  public static void inventory(int option, Product products[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    boolean found = false, edit = false;
    int stock = 0, i=0, j=0, pCounter = 0, k=0;
    double price = 0, editPrice = 0;
    String part = "", name = "", serial = "", description = "", editName = null, editDescription = null, editSerial = null,
      editProductID = null, editSerialNumber = null, preEditPrice = null, add = null;
    
    for(i = 0; i < products.length; i++)
    {
      if(products[i] != null)
        pCounter++;
    }
    i = products.length - pCounter;
    if(i < 10)
      stdOut.println("Precauci�n, Solamente Dispones de " + i + " Espacios Disponibles... Contactar Administrador del Sistema");
    i = 0; //Resetting Variables
    
    if(pCounter < products.length)
    {
      if(option == 7) //Adding Products
      {
        stdOut.println("Numero de Parte:");
        while(found == false)
        {
          part = stdIn.readLine();
          if(part.equals(""))
          {
            stdOut.println("Es Necesario Escribir Un Numero de Parte!");
          }
          else
            found = true;
        }
        
        found = false; // Resetting Variable
        
        while(found == false && i<products.length)
        {
          try
          {
            if(part.equalsIgnoreCase(products[i].getIdNumber()))
              found = true;
            else
              i++;
          }
          catch(NullPointerException e)
          {
            i++;
          }
        }
        
        if(found == false)
        { 
          
          stdOut.println("Nombre del Producto:");
          while(found == false)
          {
            name = stdIn.readLine();
            if(name.equals(""))
            {
              stdOut.println("Es Necesario Escribir Un Nombre Para el Producto!");
            }
            else
              found = true;
          }
          found = false; //Resetting boolean...
          
          stdOut.println("Precio:");
          while(found == false)
          {
            found = true;
            try{
              price = Double.parseDouble(stdIn.readLine());
            }
            catch (NumberFormatException e)
            {
              found = false;
              stdOut.println("Favor de Escribir Numeros Solamente...");
            }
          }
          found = false; //Resetting boolean...
          
          
          stdOut.println("Descripci�n:");
          while(found == false)
          {
            description=stdIn.readLine();
            if(description.equals(""))
            {
              stdOut.println("Es Necesario Escribir Una Descripci�n Para el Producto!");
            }
            else
              found = true;
          }
          found = false; //Resetting boolean...
          
          stdOut.println("Cantidad de Productos Iguales a �ste:");
          while(found == false)
          {
            found = true;
            try{
              stock = Integer.parseInt(stdIn.readLine());
            }
            catch (NumberFormatException e)
            {
              found = false;
              stdOut.println("Favor de Escribir Numeros Solamente...");
            }
          }
          found = false; //Resetting boolean...
          
          for(i=0; i < stock; i++)
          {
            stdOut.println("Numero de Serie " + (i+1) + ":");
            while(found == false)
            {
              serial = stdIn.readLine();
              if(serial.equals(""))
              {
                stdOut.println("Es Necesario Escribir Un Numero de Serie Para el Producto!");
              }
              else
                found = true;
            }
            found = false; //Resetting boolean...
            
            while(found == false)
            {
              if(products[j] == null)
              {
                products[j] = new Product(name, description, part, serial, price);
                found = true;
              }
              else
                j++;
            }
            found = false; //resetting variables
          }
        }
        else
          stdOut.println("El Producto Existe, En Lugar de Agregarlo, Edite el Producto...");
      }
      else //Editing Products
      {
        stdOut.println("ID del Producto Existente");
        while(found == false)
        {
          part = stdIn.readLine();
          if(part.equals(""))
          {
            stdOut.println("Es Necesario Escribir Un Numero de Parte!");
          }
          else
            found = true;
        }
        
        found = false; //Resetting Variable
        
        while(found == false && i < products.length)
        {
          try
          {
            if(part.equalsIgnoreCase(products[i].getIdNumber()))
            {
              stdOut.println("Nombre: " + products[i].getName());
              stdOut.println("Descripci�n: " + products[i].getDescription());
              found = true;
            }
            else
              i++;
          }
          catch(NullPointerException e)
          {
            i++;
          }
        }
        // i is the first found object with the corresponding parameters
        
        if(found == true)
        {
          found = false; //Resetting Variable.
          
          stdOut.println("Editar Producto, si no requiere edici�n presionar la tecla enter");
          
          //Edit All
          stdOut.println("Nombre: " + products[i].getName());
          editName=stdIn.readLine();
          
          stdOut.println("Descripci�n: " + products[i].getDescription());
          editDescription = stdIn.readLine();
          
          stdOut.println("Precio: $" + products[i].getPrice());
          while(found == false)
          {
            found = true;
            preEditPrice = stdIn.readLine();
            try{
              if(!preEditPrice.equals(""))
                editPrice= Double.parseDouble(preEditPrice);
              else
                editPrice=-1; //No product could have this price, so its a "flag"
            }
            catch(NumberFormatException e)
            {
              found = false;
              stdOut.println("Favor de Escribir Numeros Solamente...");
            }
          }
          found = false; //Resetting Variable...
          
          stdOut.println("ID: " + products[i].getIdNumber());
          editProductID = stdIn.readLine();
          
          stdOut.println("Si Desea Agregar Articulos, Escriba la Palabra 'add'");
          while(found == false)
          {
            add = stdIn.readLine();
            if(add.equalsIgnoreCase("add") || add.equalsIgnoreCase(""))
              found = true;
            else
              stdOut.println("Solamente escriba 'add' o Deje el Espacio en Blanco Para Continuar...");
          }
          found = false; //Resetting var
          
          if(!add.equalsIgnoreCase("add"))
          {
            stdOut.println("Si Desea Borrar Un Articulo, Escriba " +
                           "la Palabra 'delete' en el Numero de Serie Correspondiente");
            for(j=i; j < products.length; j++)
            {
              try
              {
                if(products[j].getIdNumber().equalsIgnoreCase(part))
                {
                  if(!editName.equals(""))
                    products[j].setName(editName);
                  
                  if(!editDescription.equals(""))
                    products[j].setDescription(editDescription);
                  
                  if(editPrice != -1)
                    products[j].setPrice(editPrice);
                  
                  if(!editProductID.equals(""))
                    products[j].setIdNumber(editProductID);
                  
                  stdOut.println("Numero de Serie " + (j+1) +": " + products[j].getSerialNumber());
                  editSerialNumber = stdIn.readLine();
                  
                  if(!editSerialNumber.equals(""))
                    products[j].setSerialNumber(editSerialNumber);
                  if(editSerialNumber.equalsIgnoreCase("delete"))
                    products[j] = null;
                }
              }
              catch(NullPointerException e)
              {
                j=j;
              }
            }
          }
          else
          {
            stdOut.println("Cantidad de Productos a Agregar:");
            while(found == false)
            {
              found = true;
              try{
                stock = Integer.parseInt(stdIn.readLine());
              }
              catch (NumberFormatException e)
              {
                found = false;
                stdOut.println("Favor de Escribir Numeros Solamente...");
              }
            }
            found = false; //Resetting boolean...
            
            if(name.equals(""))
              name = products[i].getName();
            if(description.equals(""))
              description = products[i].getDescription();
            if(part.equals(""))
              part = products[i].getIdNumber();
            if(price == -1)
              products[i].getPrice();
            
            
            for(k=0; k < stock; k++)
            {
              stdOut.println("Numero de Serie " + (k+1) + ":");
              while(found == false)
              {
                serial = stdIn.readLine();
                if(serial.equals(""))
                {
                  stdOut.println("Es Necesario Escribir Un Numero de Serie Para el Producto!");
                }
                else
                  found = true;
              }
              found = false; //Resetting boolean...
              
              while(found == false)
              {
                if(products[j] == null)
                {
                  products[j] = new Product(name, description, part, serial, price);
                  found = true;
                }
                else
                  j++;
              }
              found = false; //resetting variables
            }
          }
          
        }
        else
          stdOut.println("No se encontr� el producto buscado...");
      }
    }
    else
      stdOut.println("Lo Sentimos, El Archivo Esta Lleno.. Contacte Al Proveedor del Sistema Inmediatamente");
  }
  
  public static void selectProducts(Product cart[], Product products[]) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String product;
    int i=0, serial=0, j=0, k=0, l=0;
    boolean found = false, test = false, flag = false;
    char confirm = 'I'; //Initializing Variable
    int securityIndex [] = new int[25]; //The array has this size because there's no product in the store repeating 25 times itself.
    
    do
    {
      stdOut.println("ID del Producto que Desea Vender:");
      product = stdIn.readLine();
      stdOut.println("Resultados para " + product+":");
      
      
      while(found == false && i < products.length)
      {
        try
        {
          if(product.equalsIgnoreCase(products[i].getIdNumber()))
          {
            stdOut.println("Nombre: " + products[i].getName());
            stdOut.println("Descripci�n: " + products[i].getDescription());
            found = true;
          }
          else
            i++;
        }
        catch(NullPointerException e)
        {
          i=i+1;
        }
      }
      
      if(found == true)
      {
        stdOut.println("Numeros de Serie Correspondientes al Producto Buscado:");
        
        for(i=0; i < products.length; i++)
        {
          try
          {
            if (product.equalsIgnoreCase(products[i].getIdNumber()))
            {
              stdOut.println((i+1) + ") " + products[i].getSerialNumber());
              securityIndex[k] = (i+1);
              k=k+1;
            }
          }
          catch(NullPointerException e)
          {
            i=i;
          }
        }
        
        while(flag == false)
        {
          stdOut.println("Introduzca el numero a la izquierda del Numero De Serie correspondiente");
          
          
          while(test == false)
          {
            test = true;
            try{
              serial = Integer.parseInt(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Utilize solamente numeros");
              test = false;
            }
          }
          test = false; //Resetting Variables
          
          while(test == false && l < securityIndex.length)
          {
            if(securityIndex[l] == serial && securityIndex[l] != 0)
              test = true;
            else
              l++;
          }
          
          if(test == true)
          {
            flag = true;
            serial = serial-1;
            cart[j] = products[serial];// Product is passed to the Cart
            products[serial] = null;
            j++; 
          }
          else
          {
            stdOut.println("Escriba uno de los Numeros Mostrados Anteriormente...");
          }
          l=0; //Resetting..
        }
      }
      else
      {
        stdOut.println("No se Encontr� el Producto en la Base de Datos...");
      }
      
      test = false; //Resetting..
      stdOut.println("A�adir Otro Producto? (Si/No)");
      while(test == false)
      {
        try{
          confirm = stdIn.readLine().toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e)
        {
          confirm = 'A'; //Neglecting the next if, to loop again in case of error
        }
        if(confirm == 'S' || confirm == 'N')
          test = true;
        else
          stdOut.println("Favor de Escribir Si o No Para Buscar Otro Producto");
      }
      //Resetting Variables
      i=0;
      test = false;
      found = false;
      flag = false;
    }
    while(confirm == 'S');
  }
  
  public static Product[] fillArrayProducts(Product[] products, File inventory) throws IOException
  {
    ObjectInputStream inFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(inventory)));
    try{
      products = (Product[]) inFile.readObject();
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("No se Encontr� el Archivo! Contacte a su Provedor de Software");
    }
    catch(EOFException e)
    {
      System.out.println("Archivo Leeido Exitosamente!");
    }
    inFile.close();
    return products;
  }
  
  public static Client[] fillArrayClients(Client[] clients, File database) throws IOException
  {
    ObjectInputStream inFile = new ObjectInputStream(new BufferedInputStream(new FileInputStream(database)));
    try{
      clients = (Client[]) inFile.readObject();
    }
    catch(ClassNotFoundException e)
    {
      System.out.println("No se Encontr� el Archivo! Contacte a su Provedor de Software");
    }
    catch(EOFException e)
    {
      System.out.println("Archivo Leeido Exitosamente!");
    }
    inFile.close();
    return clients;
  }
  
  public static void saveFileProducts(Product products[], File inventory) throws IOException
  {
    ObjectOutputStream outFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(inventory)));
    outFile.writeObject(products);
    System.out.println("Archivo Guardado Exitosamente!");
    outFile.close();
  }
  
  public static void saveFileClients(Client clients[], File database) throws IOException
  {
    ObjectOutputStream outFile = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(database)));
    outFile.writeObject(clients);
    System.out.println("Archivo Guardado Exitosamente!");
    outFile.close();
  }
  
  public static int clientsInfo(Client clients[], String username) throws IOException
  {
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String name = "", email = "", phone = "", state, city, neighborhood, hNumber, street,
      razonSocial, rfc = "";
    char note = 'I', info = 'I'; //Initializing Variable
    int zipCode = 0, noteType = 0, i = 0, clientSearcher = 0;
    boolean flag = false, found = false, test = false, clientExist = false;
    
    stdOut.println("Nombre del Cliente:");
    while(flag == false)
    {
      name = stdIn.readLine();
      if(!name.equals(""))
        flag = true;
      else
        stdOut.println("Escriba un Nombre Por Favor..");
    }
    flag = false; //Resetting..
    
    while(clientExist == false && clientSearcher < clients.length)
    {
      try{
        if(name.equalsIgnoreCase(clients[clientSearcher].getName()))
        {
          stdOut.println("Se Encontr� el Cliente en la Base de Datos.. Desea Actualizar su Informaci�n? (Si/No)");
          clientExist = true;
        }
        else
          clientSearcher++;
      }
      catch(NullPointerException e)
      {
        clientSearcher++;
      }
    }
    
    if(clientExist == true)
    {
      while(flag == false)
      {
        try{
          info = stdIn.readLine().toUpperCase().charAt(0);
        }
        catch(StringIndexOutOfBoundsException e)
        {
          info = 'A'; //To neglect the next if..
        }
        if(info == 'S' || info == 'N')
          flag = true;
        else
          stdOut.println("Solamente Si o No!");
      }
    }
    
    flag = false; //Resetting Variable
    
    if(info == 'S'|| clientExist == false)
    {
      stdOut.println("Telefono:");
      while(flag == false)
      {
        phone = stdIn.readLine();
        if(!phone.equals(""))
          flag = true;
        else
          stdOut.println("Escriba un Telefono Por Favor..");
      }
      flag = false; //Resetting...
      
      stdOut.println("Email:");
      while(flag == false)
      {
        email = stdIn.readLine();
        if(!email.equals(""))
          flag = true;
        else
          stdOut.println("Escriba un Email Por Favor..");
      }
      flag = false; //Resetting var..
    }
    else
    {
      phone = clients[clientSearcher].getPhoneNumber();
      email = clients[clientSearcher].getEmail();
    }
    
    stdOut.println("Requiere Factura? (S/N)");
    while(test == false)
    {
      try{
        note = stdIn.readLine().toUpperCase().charAt(0);
      }
      catch(StringIndexOutOfBoundsException e)
      {
        note = 'A'; //Neglecting the next if in case of an error
      }
      
      if(note == 'S' || note == 'N')
        test = true;
      else
        stdOut.println("Favor de Escribir Si o No para Capturar Datos de Factura");
    }
    test = false; //Resetting vars
    
    if((note == 'S' && info == 'S') || (clientExist == false && note == 'S'))
    {
      stdOut.println("Direcci�n");
      stdOut.println();
      
      stdOut.println("Calle:");
      street = stdIn.readLine();
      
      stdOut.println("Numero de Casa:");
      hNumber = stdIn.readLine();
      
      stdOut.println("Colonia:");
      neighborhood = stdIn.readLine();
      
      stdOut.println("Ciudad:");
      city = stdIn.readLine();
      
      stdOut.println("Estado:");
      state = stdIn.readLine();
      
      stdOut.println("Codigo Postal:");
      while(test == false)
      {
        test = true;
        try{
          zipCode = Integer.parseInt(stdIn.readLine());
        }
        catch(NumberFormatException e)
        {
          stdOut.println("Utilize solamente numeros");
          test = false;
        }
      }
      test = false; //Resetting Variable
      
      stdOut.println("Persona Fisica o Persona Moral?");
      stdOut.println("1) Persona Fisica - Factura a Nombre Propio");
      stdOut.println("2) Persona Moral - Factura a Nombre de la Empresa");
      while(test == false)
      {
        try{
          noteType = Integer.parseInt(stdIn.readLine());
        }
        catch(NumberFormatException e)
        {
          stdOut.println("Utilize solamente los numeros '1' o '2'");
        }
        if(noteType == 1 || noteType == 2)
          test = true;
        else
          stdOut.println("Utilize solamente los numeros '1' o '2'");
      }
      test = false; //Resetting Variable
      
      if(noteType == 1)
      {
        stdOut.println("Nombre Completo de la Persona:");
        razonSocial = stdIn.readLine();
        
        while(flag == false)
        {
          stdOut.println("RFC:");
          rfc = stdIn.readLine();
          flag = analyzeRFC(rfc, noteType);
          
          if(flag == false)
            stdOut.println("Formato No Coincide, Intenta de Nuevo");
        }
      }
      else
      {
        stdOut.println("Nombre Completo de la Empresa:");
        razonSocial = stdIn.readLine();
        
        while(flag == false)
        {
          stdOut.println("RFC:");
          rfc = stdIn.readLine();
          flag = analyzeRFC(rfc, noteType);
          
          if(flag == false)
            stdOut.println("Formato No Coincide, Intenta de Nuevo");
        }
      }
      stdOut.println("RFC Validado!");
      
      //Client Creation with Official Note
      
      while(found == false && i < clients.length)
      {
        if(clients[i] == null)
        {
          clients[i] = new Client (razonSocial, phone, email, street, hNumber, neighborhood, city, state, zipCode,
                                   razonSocial, rfc, username);
          found = true;
        }
        else
          i++;
      } 
    }
    else if(note == 'S' && info == 'N')
    {
      street = clients[clientSearcher].getStreet();
      hNumber = clients[clientSearcher].getHouseNumber();
      neighborhood = clients[clientSearcher].getNeighborhood();
      city = clients[clientSearcher].getCity();
      state = clients[clientSearcher].getState();
      zipCode = clients[clientSearcher].getZipCode();
      razonSocial = clients[clientSearcher].getRazonSocial();
      rfc = clients[clientSearcher].getOfficialID();
      
      //Client Creation with Official Note and previous data....
      
      while(found == false && i < clients.length)
      {
        if(clients[i] == null)
        {
          clients[i] = new Client (razonSocial, phone, email, street, hNumber, neighborhood, city, state, zipCode,
                                   razonSocial, rfc, username);
          found = true;
        }
        else
          i++;
      }
    }
    else
    {
      stdOut.println("Venta sin Factura..");
      //Client Creation without Official Note
      
      while(found == false && i < clients.length)
      {
        if(clients[i] == null)
        {
          clients[i] = new Client (name, phone, email, username);
          found = true;
        }
        else
          i++;
      }
    }
    return i;
  }
  
  public static void sell(Product cart[], Client clients[], int i) throws IOException
  {
    Calendar today = Calendar.getInstance();
    PrintWriter stdOut = new PrintWriter(System.out, true);
    BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
    
    String[] iDS = new String[10];
    double[] payment = new double[4];
    
    int month, day, year, hours, minutes, j = 0, payOption = 0, checkNumber = 0, expDate = 0,
      autorize = 0;
    double totalSale = 0, paying = 0, change = 0, due = 0, totalSum = 0;
    boolean test = false;
    long cardNumber =0;
    String bankName = "";
    
    month = today.get(today.MONTH)+1;
    day = today.get(today.DATE);
    year = today.get(today.YEAR);
    hours = today.get(today.HOUR)+1;
    minutes = today.get(today.MINUTE);
    
    //Setting time of Sale
    clients[i].setMonth(month);
    clients[i].setDay(day);
    clients[i].setHours(hours);
    clients[i].setMinutes(minutes);
    clients[i].setYear(year);
    
    
    for(j=0; j < payment.length; j++) //Setting payments a 0 value
    {
      payment[j] = 0;
    }
    
    //Payment Methods:
    
    for(j=0; j < cart.length; j++)
    {
      try
      {
        totalSale = cart[j].getPrice() + totalSale;
      }
      catch(NullPointerException e)
      {
        j=j;
      }
    }
    
    
    due = totalSale; //initializing variable
    do
    {
      stdOut.println("Total a Pagar:");
      stdOut.println("$ "+ due);
      
      stdOut.println("Forma de Pago: (1. Efectivo, 2. Cheque, 3. Debito, 4. Credito");
      while(test == false)
      {
        test = true;
        try
        {
          payOption = Integer.parseInt(stdIn.readLine());
        }
        catch(NumberFormatException e)
        {
          stdOut.println("Escriba Solamente UNA de las Opciones Mencionadas Anteriormente");
          test = false;
        }
      }
      test = false; //Resetting Sentinel
      
      switch(payOption)
      {
        case 1:
        {
          stdOut.println("Monto a Pagar en Efectivo:");
          while(test == false)
          {
            test = true;
            try{
              paying = Double.parseDouble(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          if(paying > due)
          {
            change = paying - due;
            stdOut.println("El cambio es: $" + change);
          }
          payment[0] = paying-change + payment[0];
        }
        break;
        case 2:
        {
          stdOut.println("Monto a Pagar en Cheque:");
          while(test == false)
          {
            test = true;
            try{
              paying = Double.parseDouble(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Numero de Cheque");
          while(test == false)
          {
            test = true;
            try{
              checkNumber = Integer.parseInt(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          
          stdOut.println("Nombre del Banco");
          bankName = stdIn.readLine();
          
          stdOut.println("Numero de Autorizaci�n del Cheque");
          while(test == false)
          {
            test = true;
            try{
              autorize = Integer.parseInt(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          clients[i].setCheckNumber(checkNumber); //Sending Check Number
          clients[i].setBankName(bankName); //Sending Bank Name
          clients[i].setAuthorizationNumber(autorize); //Sending the Authorization Number for the Bank Check
          
          if(paying > due)
          {
            change = paying - due;
            stdOut.println("El cambio es: " + change);
          }
          payment[1] = paying-change + payment[1];
        }
        break;
        case 3:
        {
          stdOut.println("Monto a Pagar en Debito:");
          while(test == false)
          {
            test = true;
            try{
              paying = Double.parseDouble(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Numero de Tarjeta:");
          while(test == false)
          {
            test = true;
            try{
              cardNumber = Long.parseLong(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Fecha de vencimiento: mmyy");
          while(test == false)
          {
            test = true;
            try{
              expDate = Integer.parseInt(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Nombre del Banco:");
          bankName = stdIn.readLine();
          
          clients[i].setCardNumber(cardNumber); //Sending Card Number
          clients[i].setBankName(bankName); //Sending Bank Name
          clients[i].setExpiracyDate(expDate); //Expiracy Date
          
          if(paying > due)
          {
            change = paying - due;
            stdOut.println("El cambio es: " + change);
          }
          payment[2] = paying-change + payment[2];
        }
        break;
        case 4:
        {
          stdOut.println("Monto a Pagar en Credito:");
          while(test == false)
          {
            test = true;
            try{
              paying = Double.parseDouble(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Numero de Tarjeta");
          while(test == false)
          {
            test = true;
            try{
              cardNumber = Long.parseLong(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Fecha de vencimiento: mmyy");
          while(test == false)
          {
            test = true;
            try{
              expDate = Integer.parseInt(stdIn.readLine());
            }
            catch(NumberFormatException e)
            {
              stdOut.println("Escriba Solamente Numeros...");
              test = false;
            }
          }
          test = false; // Resetting Sentinel
          
          stdOut.println("Nombre del Banco:");
          bankName = stdIn.readLine();
          
          clients[i].setCardNumber(cardNumber); //Sending Card Number
          clients[i].setBankName(bankName); //Sending Bank Name
          clients[i].setExpiracyDate(expDate); //Expiracy Date
          
          if(paying > due)
          {
            change = paying - due;
            stdOut.println("El cambio es: " + change);
          }
          
          payment[3] = paying-change + payment[3];
        }
        break;
        default:
        {
          stdOut.println("No se Puede Identificar la Forma de Pago... Intenta de Nuevo");
        }
        break;
      }
      
      for(j=0; j < payment.length; j++)
      {
        totalSum = payment[j] + totalSum;
      }
      
      due = totalSale-totalSum; //counter to verify the remaining ammount to pay
      if(totalSum == totalSale)
      {
        test = true;
        clients[i].setPrice(totalSale);
      }
      else
        stdOut.println("No se Cubri� el Monto Total...");
      
      totalSum = 0; //resetting variable for next loop
    }while(test == false);
    
    clients[i].setPaymentMethod(payment); //Sending Payment Method
    clients[i].setCart(cart); //Sending All the products Bought
    
    //Finished Creating a Client...
  }
  
  public static void printing(Client clients[], Product products[], int i) throws IOException
  {
    Calendar today = Calendar.getInstance();
    Desktop dt = Desktop.getDesktop();
    String day, month, hour, year;
    hour = String.valueOf(today.get(today.HOUR)+1);
    day = String.valueOf(today.get(today.DATE));
    month = String.valueOf(today.get(today.MONTH)+1);
    year = String.valueOf(today.get(today.YEAR));
    
    File reciept = new File("VENTA"+year+month+day+hour+".txt");
    PrintWriter writeFile = new PrintWriter(new BufferedWriter(new FileWriter(reciept)));
    String stars = starsLarge(81);
    
    writeFile.println(stars);
    writeFile.println(starsSide()+ "                               Recibo de Compra                                " + starsSide());
    writeFile.println(starsSide()+ "                              HP Store Monterrey                               " + starsSide());
    writeFile.println(starsSide()+ "                        DIRECCION DE LA COMPANIA PENDEIENTE                    " + starsSide());
    writeFile.println(starsSide()+ "                            PUEDE SER EN DOS LINEAS                            " + starsSide());
    writeFile.println(stars);
    writeFile.println();
    writeFile.println("Fecha de Compra: " + day+"."+month+"."+year);
    writeFile.println("Numero de Ticket: " + year+month+day+hour);
    writeFile.println();
    writeFile.println("Vendedor: " + clients[i].getEmployee());
    writeFile.println("Cliente: " + clients[i].getName());
    writeFile.println("         Email: " + clients[i].getEmail());
    writeFile.println("         Telefono: " + clients[i].getPhoneNumber());
    writeFile.println();
    writeFile.println();
    writeFile.println("    Nombre del Producto                 ID                            Precio");
    writeFile.println(" -----------------------             ---------                     -------------");
    for(int j = 0; j < 10; j++)
    {
      writeFile.println(clients[i].getNoteName(j));
    }
    writeFile.println("                                                             -----------------");
    writeFile.println("Subtotal                                                     $" + (clients[i].getPrice()*0.84));
    writeFile.println("IVA                                                          $" + (clients[i].getPrice()*0.16));
    writeFile.println("Total                                                        $" + clients[i].getPrice());
    writeFile.println();
    writeFile.println();
    writeFile.println("Metodos de Pago:");
    writeFile.println("     " + clients[i].getPayments());
    
    
    
    writeFile.close();
    dt.open(reciept);
    
  }
  
  public static String starsLarge(int length)
  {
    String out = "";
    for(int i=0; i < length; i++)
    {
      out = out+"*";
    }
    return out;
  }
  
  public static String starsSide()
  {
    String a;
    a="*";
    return a;
  }
  
  public static boolean analyzeRFC(String rfc, int noteType)
  {
    
    String analisisOne, analisisTwo, analisisThree;
    boolean flag = false, testOne = false, testTwo = true, testThree =  false, utlimateTest = false;
    int intTest;
    
    if(rfc.length() == 13 || rfc.length() == 12)
    {
      if(noteType == 1)
      {
        analisisOne = rfc.substring(0,4);
        if(analisisOne.length() == 4)
          testOne = true;
        
        analisisTwo = rfc.substring(4,10);
        try
        {
          intTest = Integer.parseInt(analisisTwo);
        }
        catch(NumberFormatException nfe)
        {
          testTwo = false;
        }
        
        analisisThree = rfc.substring(10, 13);
        if(analisisThree.length() == 3)
          testThree = true;
        
        //Checking Error
        if(testOne == false)
          System.out.println("El Error Se Encuentra en las Iniciales de la Persona...");
        if(testTwo == false)
          System.out.println("El Error Se Encuentra en la Fecha...");
        if(testThree == false)
          System.out.println("El Error Se Encuentra en la Homoclave...");   
      }
      else
      {
        analisisOne = rfc.substring(0,3);
        if(analisisOne.length() == 3)
          testOne = true;
        
        analisisTwo = rfc.substring(3,9);
        try
        {
          intTest = Integer.parseInt(analisisTwo);
        }
        catch(NumberFormatException nfe)
        {
          testTwo = false;
        }
        
        analisisThree = rfc.substring(9, 12);
        if(analisisThree.length() == 3)
          testThree = true;
        
        //Checking Error
        if(testOne == false)
          System.out.println("El Error Se Encuentra en las Iniciales de la Empresa...");
        if(testTwo == false)
          System.out.println("El Error Se Encuentra en la Fecha...");
        if(testThree == false)
          System.out.println("El Error Se Encuentra en la Homoclave...");
        
      }
    }
    else
      System.out.println("El RFC No concide, intenta de nuevo...");
    
    if(testOne == true && testTwo == true && testThree == true)
      flag = true;
    
    return flag;
  }
}
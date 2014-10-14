import java.io.*;
class Product implements Serializable
{
  private String name, description, idNumber, serialNumber;
  private double price;
  
  public Product(String name, String description, String idNumber, String serialNumber, double price)
  {
    this.name = name;
    this.description = description;
    this.idNumber = idNumber;
    this.serialNumber = serialNumber;
    this.price = price;
  }
  public String getName()
  {
    return name;
  }
  public String getDescription()
  {
    return description;
  }
  public String getIdNumber()
  {
    return idNumber;
  }
  public String getSerialNumber()
  {
    return serialNumber;
  }
  public double getPrice()
  {
    return price;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setDescription(String description)
  {
    this.description = description;
  }
  public void setIdNumber(String idNumber)
  {
    this.idNumber = idNumber;
  }
  public void setSerialNumber(String serialNumber)
  {
    this.serialNumber = serialNumber;
  }
  public void setPrice(double price)
  {
    this.price = price;
  }
  public String toString()
  {
    String out;
    out = ("Nombre del Producto: " + name + '\n' + "Precio: " + price + '\n' + "Descripci—n: " + description + '\n' +
           "ID: " + idNumber + '\n' + "Numero de Serie: " +serialNumber + '\n');
    return out;
  }
}
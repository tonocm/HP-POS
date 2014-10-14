import java.io.*;
class Cart implements Serializable
{
  private String name, description, idNumber, serialNumber, bankCredit, bankDebit, bankCheck;
  private double price, paymentMethod[];
  private long cardNumberCredit, cardNumberDebit, checkNumber;
  private int day, hour, year, month, expDateCredit, expDateDebit;
  
  public Cart(String name, String description, String idNumber, String serialNumber, double price)
  {
    this.name = name;
    this.description = description;
    this.idNumber = idNumber;
    this.serialNumber = serialNumber;
    this.price = price;
  }
  public int getDay()
  {
    return day;
  }
  public int getHour()
  {
    return hour;
  }
  public int getYear()
  {
    return year;
  }
  public int getMonth()
  {
    return month;
  }
  public double getPrice()
  {
    return price;
  }
  public double[] getPaymentMethod()
  {
    return paymentMethod;
  }
  public long getCardNumberCredit()
  {
    return cardNumberCredit;
  }
  public long getCardNumberDebit()
  {
    return cardNumberDebit;
  }
  public long getCheckNumber()
  {
    return checkNumber;
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
  private void setCardNumberCredit(long cardNumberCredit)
  {
    this.cardNumberCredit = cardNumberCredit;
  }
  private void setCardNumberDebit(long cardNumberDebit)
  {
    this.cardNumberDebit = cardNumberDebit;
  }
  private void setCheckNumber(long checkNumber)
  {
    this.checkNumber = checkNumber;
  }
  public void setDay(int day)
  {
    this.day = day;
  }
  public void setExpDateDebit(int expDate)
  {
    expDateDebit = expDate;
  }
  public void setExpDateCredit(int expDate)
  {
    expDateCredit = expDate;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setMonth(int month)
  {
    this.month = month;
  }
  public void setHour(int hour)
  {
    this.hour = hour;
  }
  public void setBankCredit(String bankCredit)
  {
    this.bankCredit = bankCredit;
  }
  public void setBankCheck(String bankCheck)
  {
    this.bankCheck = bankCheck;
  }
  public void setPaymentMethod(double[] pMethod)
  {
     paymentMethod = pMethod;
  }
  public void setBankDebit(String bankDebit)
  {
    this.bankDebit = bankDebit;
  }
  public void setPrice(double price)
  {
    this.price = price;
  }
  public String toString()
  {
    int i;
    String display = "", paymentArray = "", out;
    
    for(i=0; i<paymentMethod.length; i++)
    {
      switch(i)
      {
        case 0:
        {
          display = "Efectivo $";
        }
        break;
        case 1:
        {
          display = "Cheque $";
        }
        break;
        case 2:
        {
          display = "Tarjeta de Debito $";
        }
        break;
        case 3:
        {
          display = "Tarjeta de Credito $";
        }
        break;
        default:
        {
          System.out.println("There's a Bug in the Payment Methods ");
        }
        break;
      }
      paymentArray = paymentArray + '\n' + display + paymentMethod[i];
    }
    
    out = "Nombre del Producto: " + name + '\n' + "Precio: " + price + '\n' + "Descripci—n: " + description + '\n' +
           "ID: " + idNumber + '\n' + "Numero de Serie: " + serialNumber + '\n' + "Fecha de venta: " +
          "Metodo de pago: " + paymentArray;
    return out;
  }
}
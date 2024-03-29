import java.io.*;
class Client implements Serializable
{
  private String name, phoneNumber, email, street, houseNumber, neighborhood, city, state, razonSocial,
    officialID, productID[], serialNumber, employee, bankName;
  private double sale, discountPercentage, paymentMethod[];
  private int day, hours, minutes, checkNumber, authorizationNumber, expiracyDate, zipCode, month, year;
  private long cardNumber;
  private Product cart[];
  
  public Client(String name, String pNumber, String email, String street, String hNumber, String neighborhood,
                String city, String state, int zipCode, String razonSocial, String officialID, String employee)
  {
    this.name = name;
    phoneNumber = pNumber;
    this.email = email;
    this.street = street;
    houseNumber=hNumber;
    this.neighborhood = neighborhood;
    this.city = city;
    this.state = state;
    this.zipCode = zipCode;
    this.razonSocial = razonSocial;
    this.officialID = officialID;
    this.employee = employee;
  }
  
  public Client(String name, String pNumber, String email, String employee)
  {
    this.name = name;
    phoneNumber = pNumber;
    this.email = email;
    this.employee = employee;
  }
  public String getName()
  {
    return name;
  }
  public String getPhoneNumber()
  {
    return phoneNumber;
  }
  public String getEmail()
  {
    return email;
  }
  public String getStreet()
  {
    return street;
  }
  public String getHouseNumber()
  {
    return houseNumber;
  }
  public String getNeighborhood()
  {
    return neighborhood;
  }
  public String getCity()
  {
    return city;
  }
  public String getState()
  {
    return state;
  }
  public int getZipCode()
  {
    return zipCode;
  }
  public String getRazonSocial()
  {
    return razonSocial;
  }
  public String getOfficialID()
  {
    return officialID;
  }
  public Product[] getCart()
  {
    return cart;
  }
  public String getEmployee()
  {
    return employee;
  }
  public long getCardNumber()
  {
    return cardNumber;
  }
  public String getBankName()
  {
    return bankName;
  }
  public double[] getPaymentMethod()
  {
    return paymentMethod;
  }
  public double getPrice()
  {
    return sale;
  }
  public double getDiscountPercentage()
  {
    return discountPercentage;
  }
  public int getDay()
  {
    return day;
  }
  public int getHours()
  {
    return hours;
  }
  public int getAuthorizationNumber()
  {
    return authorizationNumber;
  }
  public int getExpiracyDate()
  {
    return expiracyDate;
  }
  public int getCheckNumber()
  {
    return checkNumber;
  }
  public int getMonth()
  {
    return month;
  }
  public int getYear()
  {
    return year;
  }
  public int getMinutes()
  {
    return minutes;
  }
  public void setName(String name)
  {
    this.name = name;
  }
  public void setPhoneNumber(String phoneNumber)
  {
    this.phoneNumber = phoneNumber;
  }
  public void setEmail(String email)
  {
    this.email = email;
  }
  public void setStreet(String street)
  {
    this.street = street;
  }
  public void setHouseNumber(String hNumber)
  {
    houseNumber = hNumber;
  }
  public void setNeighborhood(String neighborhood)
  {
    this.neighborhood = neighborhood;
  }
  public void getCity(String city)
  {
    this.city = city;
  }
  public void setState(String state)
  {
    this.state = state;
  }
  public void setZipCode(int zip)
  {
    zipCode = zip;
  }
  public void setRazonSocial(String rSocial)
  {
    razonSocial = rSocial;
  }
  public void setOfficialID(String official)
  {
    officialID = official;
  }
  public void setCart(Product[] cart)
  {
    this.cart = cart;
  }
  public void setEmployee(String employee)
  {
    this.employee = employee;
  }
  public void setCardNumber(long cNumber)
  {
    cardNumber = cNumber;
  }
  public void setBankName(String bName)
  {
    bankName = bName;
  }
  public void setPaymentMethod(double[] pMethod)
  {
    paymentMethod = pMethod;
  }
  public void setPrice(double sale)
  {
    this.sale = sale;
  }
  public void setDiscountPercentage(double dPercentage)
  {
    discountPercentage = dPercentage;
  }
  public void setDay(int day)
  {
    this.day = day;
  }
  public void setHours(int hours)
  {
    this.hours = hours;
  }
  public void setAuthorizationNumber(int aNumber)
  {
    authorizationNumber = aNumber;
  }
  public void setExpiracyDate(int expDate)
  {
    expiracyDate = expDate;
  }
  public void setCheckNumber(int checkNumber)
  {
    this.checkNumber = checkNumber;
  }
  public void setMonth(int month)
  {
    this.month = month;
  }
  public void setYear(int year)
  {
    this.year = year;
  }
  public void setMinutes(int minutes)
  {
    this.minutes = minutes;
  }
  public String getNoteName(int i)
  {
    String out = "", preOne = "", preTwo = "";
    boolean flag = false;
    
    if(cart[i] != null)
    {
      preOne = cart[i].getName();
      while(flag == false)
      {
      if(preOne.length() < 38)
        preOne = preOne + " ";
      else
        flag = true;
      }
      flag = false; //resetting sentinel
      
      preTwo = cart[i].getIdNumber();
      while(flag == false)
      {
      if(preTwo.length() < 30)
        preTwo = preTwo + " ";
      else
        flag = true;
      }
      //out = "  " + cart[i].getName() + "                       " + cart[i].getIdNumber() + "                            $" + cart[i].getPrice();
      out = "  " + preOne + preTwo + "$" + cart[i].getPrice();
    }
    return out;
  }
  public String getPayments()
  {
    String display = "", paymentArray = "";
    
    for(int i=0; i<paymentMethod.length; i++)
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
      if(paymentMethod[i] != 0)
        paymentArray = paymentArray + '\n' + display + paymentMethod[i];
    }
    
    return paymentArray;
  }
  public String getPaymentArrayString()
  {
    String display = "", paymentArray = "", outPayment = "";
    int i;
    
    for(i=0; i<paymentMethod.length; i++)
    {
      switch(i)
      {
        case 0:
        {
          display = "* Efectivo $";
        }
        break;
        case 1:
        {
          display = "* Cheque $";
        }
        break;
        case 2:
        {
          display = "* Tarjeta de Debito $";
        }
        break;
        case 3:
        {
          display = "* Tarjeta de Credito $";
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
    
    outPayment = "* Metodo de Pago: " + paymentArray + '\n' + "*";
    
    return outPayment;
  }
  public String getCartString()
  {
    int i;
    String cartArray = "", outCart = "";
    
    for(i=0; i<cart.length; i++)
    {
      if(cart[i] != null)
      {
        cartArray = cartArray + '\n' + cart[i];
      }
    }
    
    outCart = "* Carrito de Compras: --------------------" + cartArray + '\n' + "-------------------------------------------";
    
    return outCart;
  }
  public String toString()
  {
    String out, outName, outTelephone, outEmail, outStreet, outHouse, outNeighborhood, outCity, outState, outCP,
      outRazonSocial, outRFC, outPayment, paymentArray = "", outSale, outDiscount, outCart, outEmployee, outDay,
      outHours, outCard, outExpDate, outBank, outCheck, outAuthorization, cartArray = "", display = "";
    int i = 0;
    
    outName = "Cliente: " + name + '\n';
    outTelephone = "Telefono: " + phoneNumber + '\n';
    outEmail = "Email: " + email + '\n';
    outStreet = "Calle: " + street + '\n';
    outHouse = "Numero de Casa: " + houseNumber + '\n';
    outNeighborhood = "Colonia: " + neighborhood + '\n';
    outCity = "Ciudad: " + city + '\n';
    outState = "Estado: " + state + '\n';
    outCP = "Codigo Postal: " + zipCode + '\n';
    outRazonSocial = "Razon Social: " + razonSocial + '\n';
    outRFC = "RFC: " + officialID + '\n';
    
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
    
    outPayment = "Metodo de Pago: " + paymentArray + '\n';
    outSale = "Venta: " + sale + '\n';
    outDiscount = "Descuento: " + discountPercentage + '\n';
    
    for(i=0; i<cart.length; i++)
    {
      if(cart[i] != null)
      {
        cartArray = cartArray + '\n' + cart[i];
      }
    }
    
    outCart = "Carrito de Compras: " + cartArray + '\n';
    outEmployee = "Empleado que Concreto la Venta: " + employee + '\n';
    outDay = "Dia: " + day + '\n';
    outHours = "Hora: " + hours + '\n';
    
    if(cardNumber != 0)
      outCard = "Numero de Tarjeta: " + cardNumber + '\n';
    else
      outCard = "";
    
    if(expiracyDate != 0)
      outExpDate = "Fecha de Expiración: " + expiracyDate + '\n';
    else
      outExpDate = "";
    
    if(bankName != null)
      outBank = "Banco: " + bankName + '\n';
    else
      outBank = "";
    
    if(checkNumber !=0)
      outCheck = "Numero de Cheque: " + checkNumber + '\n';
    else
      outCheck = "";
    
    if(authorizationNumber != 0)
      outAuthorization = "Autorización: " + authorizationNumber + '\n';
    else
      outAuthorization = "";
    
    out = (outName + outTelephone + outEmail + '\n' + outStreet + outHouse + outNeighborhood + outCity + outState
             + outCP + outRazonSocial + outRFC + '\n' + outPayment + '\n' + outSale + outDiscount + '\n' + outCart
             + outEmployee + outDay + outHours + '\n' + outCard + outExpDate + outBank + outCheck + outAuthorization);
    
    return out;
  }
}
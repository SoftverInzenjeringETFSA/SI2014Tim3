package ba.unsa.etf.si.projekt.dodatno;

import java.util.List;

public class Validacija 
{
    public static boolean praznoPolje(String vrijednost)
	{
    	if (vrijednost == null || vrijednost.trim().equals( "" ))
    		return true;
    	else
    		return false;
	}
    
    public static boolean validirajJMBG(String vrijednost)
	{
    	if (praznoPolje(vrijednost) == false && jeInt(vrijednost) == true && vrijednost.length() == 13)
    		return true;
    	else
    		return false;
	}
    
    public static boolean jeInt(String vrijednost)
    {
        try 
        { 
            Integer.parseInt(vrijednost); 
        } catch(NumberFormatException e) 
        { 
            return false; 
        } catch(NullPointerException e) 
        {
            return false;
        }
        return true;
    }
    
    public static boolean sadrziCifru(String s)
    {  
        boolean sadrzi = false;

        if(s != null && ! s.isEmpty())
        {
            for(char c : s.toCharArray())
            {
                if(sadrzi = Character.isDigit(c))
                {
                	
                    break;
                }
            }
        }
        return sadrzi;
    }
    
    public static boolean alfaNumerickaVrijednost(String s)
    {  
        boolean jeste = true;
        if(s != null && ! s.isEmpty())
        {
            for(char c : s.toCharArray())
            {
                if(!(Character.isDigit(c) || Character.isLetter(c) ))
                {
                	jeste=false;
                	break;
                }
            }
        }
        return jeste;
    }
    
    public static boolean validirajPass(String vrijednost)
    {
    	if (vrijednost != null && vrijednost.trim().equals( "" ) && vrijednost.length() >= 8 && sadrziCifru(vrijednost) && !alfaNumerickaVrijednost(vrijednost))
    		return true;
    	else
    		return false;
    	
    }
    
    public static boolean validirajKorisnickoIme(List<String> imena, String ime)
    {
       if(imena.contains(ime)) 
    	   return false;
       else 
    	   return true;
    }
    
    public static boolean radnaPozicija(String rpozicija)
    {
       if(rpozicija=="Menadzer"|| rpozicija=="Administrator"|| rpozicija=="SalterskiRadnik")
    	   return true;
       else
    	   return false;
    }
    
    public static boolean validirajKapacitet(String kapacitet)
    {
    	if (praznoPolje(kapacitet) == false && jeInt(kapacitet) && Integer.parseInt(kapacitet) > 0 && Integer.parseInt(kapacitet) <= 60)
    		return true;
    	else
    		return false;
    }
    
    public static boolean validirajBrojPerona(String broj)
    {
    	if (Integer.parseInt(broj) > 0 && Integer.parseInt(broj) < 6)
    		return true;
    	else
    		return false;
    }
    
    public static boolean validirajCijenuKarte(String cijena)
    {
    	if (Integer.parseInt(cijena) > 0 && Integer.parseInt(cijena) <= 300)
    		return true;
    	else 
    		return false;
    }
    
    public static boolean validirajMjesto(String mjesto, String kapacitet)
    {
    	if (Integer.parseInt(mjesto) <= Integer.parseInt(kapacitet)  && Integer.parseInt(mjesto) > 0)
    		return true;
    	else 
    		return false;
    }
    
    public static boolean validirajBrojAutobusa(List<String> autobusi, String autobus)
    {
       if(autobusi.contains(autobus)) 
    	   return false;
       else 
    	   return true;
    }
    
    public static boolean validirajBrojLinije(List<String> linije, String linija)
    {
       if(linije.contains(linija)) 
    	   return false;
       else 
    	   return true;
    }
}

package ba.unsa.etf.si.projekt.dodatno;

import java.util.List;

public class Validacija 
{
	public static void main( String[] args)
	{
		String proba = "12:12";
		if (validirajVrijeme(proba))
			System.out.println("OK");
		else
			System.out.println("NOK");
	}
	
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
    	for (int i = 0; i < vrijednost.length(); i++)
    	{
    		if (Character.isDigit(vrijednost.charAt(i)))
    			return true;
    		else
    			return false;
    	}
    	return false;
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
    	if (praznoPolje(vrijednost) == false && vrijednost.length() >= 8 && sadrziCifru(vrijednost) && !alfaNumerickaVrijednost(vrijednost))
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
    
    public static boolean validirajCijenuKarte(double cijena)
    {
    	if (cijena > 0 && cijena <= 300)
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
    
    public static boolean validirajTablice(String tablice)
    {
    	if (tablice.length() != 9)
    		return false;
    	char prvi = tablice.charAt(0);
    	char drugi = tablice.charAt(1);
    	char treci = tablice.charAt(2);
    	char cetvrti = tablice.charAt(3);
    	char peti = tablice.charAt(4);
    	char sesti = tablice.charAt(5);
    	char sedmi = tablice.charAt(6);
    	char osmi = tablice.charAt(7);
    	char deveti = tablice.charAt(8);
    	if(jeSlovoTablica(prvi) == true && Character.isDigit(drugi) && Character.isDigit(treci) && cetvrti == '-' && jeSlovoTablica(peti) == true && sesti == '-' && Character.isDigit(sedmi) && Character.isDigit(osmi) && Character.isDigit(deveti))
    		return true;
    	else 
    	    return false;
    }
    
    public static boolean jeSlovoTablica(char c)
    {
    	if (c == 'A' || c == 'E' || c == 'J' || c == 'K' || c == 'M' || c == 'O' || c == 'T')
    		return true;
    	else
    		return false;
    }
    
    public static boolean jeTekst(String vrijednost)
    {
    	for (int i = 0; i < vrijednost.length(); i++)
    	{
    		if (Character.isLetter(vrijednost.charAt(i)))
    			return true;
    		else
    			return false;
    	}
    	return false;
    }
    
    public static boolean validirajVrijeme(String vrijednost)
    {
    	if (vrijednost.length() != 5)
    		return false;
    	char prvi = vrijednost.charAt(0);
    	char drugi = vrijednost.charAt(1);
    	char treci = vrijednost.charAt(2);
    	char cetvrti = vrijednost.charAt(3);
    	char peti = vrijednost.charAt(4);
    	if(Character.isDigit(prvi) && Character.isDigit(drugi) && treci == ':' && Character.isDigit(cetvrti) && Character.isDigit(peti))
    		return true;
    	else 
    	    return false;
    }
    
}

package ba.unsa.etf.si.projekt.dodatno;

import java.util.List;

public class Validacija 
{
    public static boolean praznoPolje(String vrijednost)
	{
    	if (vrijednost == null || vrijednost.trim().equals( "" ))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
	}
    
    public static boolean validirajJMBG(String vrijednost)
	{
    	if (vrijednost != null && vrijednost.trim().equals( "" ) && jeInt(vrijednost) == true && vrijednost.length() == 13)
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    		
    	}
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
    
    public static boolean sadrziCifru(String s){  
        boolean sadrzi = false;

        if(s != null && ! s.isEmpty()){
            for(char c : s.toCharArray()){
                if(sadrzi = Character.isDigit(c)){
                	
                    break;
                }
            }
        }

        return sadrzi;
    }
    public static boolean alphaNumerickaVrijednost(String s){  
        boolean jeste = true;

        if(s != null && ! s.isEmpty()){
            for(char c : s.toCharArray()){
                if(!(Character.isDigit(c) || Character.isLetter(c) )){
                	jeste=false;
                	break;
                	}
            }
        }

        return jeste;
    }
    
    public static boolean validirajPass(String vrijednost){
    	if (vrijednost != null && vrijednost.trim().equals( "" ) && vrijednost.length() >= 8 && sadrziCifru(vrijednost) && !alphaNumerickaVrijednost(vrijednost))
    	{
    		return true;
    	}
    	else
    	{
    		return false;
    	}
    	
    }
    
    public static boolean korisnickoIme(List<String> imena, String ime)
    {
       if(imena.contains(ime)) return false;
       else return true;
    }
    
    public static boolean radnaPozicija(String rpozicija)
    {
       if(rpozicija=="Menadzer"|| rpozicija=="Administrator"|| rpozicija=="SalterskiRadnik") return true;
       else return false;
    }
    
    
}

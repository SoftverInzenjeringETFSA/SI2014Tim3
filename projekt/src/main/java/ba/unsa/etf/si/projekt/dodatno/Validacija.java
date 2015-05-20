package ba.unsa.etf.si.projekt.dodatno;

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
    
}

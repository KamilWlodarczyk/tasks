package get_stock;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class Get_Stock {
	public static String kurs = "";
	public static String zmiana = "";
	public static String wartosc_obrotu = "";
	public static String company = "";
	public static void company(String string)
	{
		company = string;
		//System.out.println(company);
		
	}
public static void main(String[] args) throws IOException
		{
		
			URL urlAdress = new URL("http://newconnect.pl/index.php?page=wykres&ncc_index=" + company);
				
			// getting source of url
			BufferedReader bufferedRead = new BufferedReader(
					new InputStreamReader(
							urlAdress.openStream()));
			String inputLine = "";
			String prevString = "";
			while((inputLine = bufferedRead.readLine()) != null){
				
				if(inputLine.contains("wynik1")){
					inputLine = inputLine.trim();
				int inputLineLenght = inputLine.length();
				if(inputLine.substring(0,19).equals("<td class=\"wynik1\">")
						&&
						inputLine.substring(inputLineLenght-5,inputLineLenght).equals("</td>")
						);
				{
							//ify dla zmiany musza byc 2 z niczym i z rosnie/maleje
							
					if(inputLine.substring(inputLineLenght-10,inputLineLenght).equals("span></td>"))
					{// jest span czas sie pobawic i zobaczyc czy rosnie maleje czy nic sie niedzieje
						//zbieram do int dlugosci stringow ktore trzeba porownac do zeby ladnie pobrac wynik
												int lengthOfComparingString =("<td class=\"wynik1\"><span class=\"rosnie\" >").length();
												int lengthOfComparingString2 =("<td class=\"wynik1\"><span  >").length();
						if(inputLine.substring(0,lengthOfComparingString).equals("<td class=\"wynik1\"><span class=\"rosnie\" >")
								||
								inputLine.substring(0,lengthOfComparingString).equals("<td class=\"wynik1\"><span class=\"maleje\" >"))
						zmiana = inputLine.substring(lengthOfComparingString,inputLineLenght-12);
						else
							zmiana = inputLine.substring(lengthOfComparingString2,inputLineLenght-12);
						
					}
					
					else
					{// pobieranie z poprzedniego stringa opisu wartosci na podstawie przyciecia lini 
						if(prevString.substring(prevString.length() - 9, prevString.length()).equals("Kurs</td>"))
						kurs = inputLine.substring(19,inputLineLenght-5);//podaje kurs 
						else if(prevString.substring(prevString.length() - 9, prevString.length()).equals("rotu</td>"))
							wartosc_obrotu = inputLine.substring(19,inputLineLenght-5);//podaje  warotsc obrotu
					
					}	
					}
				}
				prevString = inputLine;
			}//end of while
			
			bufferedRead.close();
		}//end of main
	public static void callMain() throws IOException
	{
		main(null);
	}
		public static String getZmiana()
		{
			return zmiana;
		}
		public static String getKurs()
		{
			return kurs;
		}
		public static String getWartoscObrotu()
		{
			return wartosc_obrotu;
		}
}

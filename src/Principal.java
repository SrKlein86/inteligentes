import java.io.IOException;

import org.jdom2.JDOMException;

public class Principal {
	
	public static void main (String[] Args) throws IOException, JDOMException
	{
		mapGetter.getMap((float)-3.9425, (float)38.9684, (float)-3.9101, (float)38.9977);
		System.out.println(TratarXML.BuscarNodosCalle("Calle Calatrava"));
		
	}

}

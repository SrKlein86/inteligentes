import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

public class mapGetter {	
	
private static final String OPENSTREETMAP_API_06 = "http://www.openstreetmap.org/api/0.6/";	
	
	//FUNCION QUE CREA UN FICHERO CON EL MAPA XML OBTENIDO DE LA API
	//DE OPENSTREETMAPS DADAS UNAS COORDENADAS
	public static void getMap (float izda, float abajo, float dcha, float arriba) throws IOException
	{
		//REVISAR FORMATO DE LOS PARAMETROS DE ENTRADA, POR SI TIENE MAS DE 4 DECIMALES?
		String string = OPENSTREETMAP_API_06 + "map?bbox=" + izda + "," + abajo + "," + dcha + "," + arriba;
		URL osm = new URL(string);
		HttpURLConnection connection = (HttpURLConnection) osm.openConnection();

		String mapaXML =IOUtils.toString(connection.getInputStream(), "UTF-8");  
		FileUtils.writeStringToFile(new File("./mapas/map.osm"), mapaXML, "UTF-8");				
		
	}
	
	
}

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

public class TratarXML {
	
	public TratarXML(){}
	
	public static String BuscarNodosCalle(String nom_calle) throws JDOMException, IOException{
		String salida="";
		
		SAXBuilder builder = new SAXBuilder();
		File xmlFile = new File("mapas/map.osm");
		
		Document document = (Document) builder.build(xmlFile);
		Element rootNode = document.getRootElement();
		List<Element> list_calles = null;
		List<Element> list_way = rootNode.getChildren("way");
		
		//Construimos la cabecera de la salida String
		salida = "Calle: "+nom_calle+"\nNodos:\n";
		
		//Primero recorremos el bucle buscando en la lista de nodos que tienen como cabecera way
		for(int i=0;i<list_way.size();i++){
			//Cogemos el nodo way para analizarlo
			Element way = (Element) list_way.get(i);
			//Obtenemos todos los nodos que contendran la informacion sobre la propia calle
			List<Element> nodos_hijos = way.getChildren("tag");
			for(int j=0;j<nodos_hijos.size();j++){
				//Si la informacion es un name, y ademas concuerda con la calle que buscamos la seleccionamos
				if((nodos_hijos.get(j).getAttributeValue("k").compareTo("name")==0)&&(nodos_hijos.get(j).getAttributeValue("v").compareTo(nom_calle)==0)){
					//Ahora, como nos hemos dado cuenta de que hay varios ways con el nombre de nuestra calle,
					//para no tener que hacer mas bucles gastando mas recursos del pc, recorremos un ultimo bucle
					//para obtener todas las referencias de los nodos que corresponden a esa calle.
					List<Element> ref = way.getChildren("nd");
					for(int k=0;k<ref.size();k++){
						Element node = (Element) ref.get(k);
						salida = salida + "\tId: "+node.getAttributeValue("ref")+"\n";
					}
				}
			}			
		}		
		return salida;
	}

}

package bid.socrata.api;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import bid.socrata.api.resources.DatasetTemp;
import bid.socrata.api.resources.SField;

import com.google.common.net.MediaType;
import com.socrata.api.HttpLowLevel;
import com.socrata.api.Soda2Consumer;
import com.socrata.api.SodaDdl;
import com.socrata.exceptions.LongRunningQueryException;
import com.socrata.exceptions.SodaError;
import com.socrata.model.importer.Column;
import com.socrata.model.importer.Dataset;
import com.socrata.model.importer.DatasetInfo;
import com.socrata.model.importer.Metadata;
import com.socrata.model.soql.SoqlQuery;
import com.sun.jersey.api.client.ClientResponse;

public class CatalogApi {

	private final static String URL="url";
	private final static String USER="user";
	private final static String PWD="password";
	private final static String TOKEN="token";
	
	

	private Soda2Consumer consumer;
	
	
	public CatalogApi()
	{
		connect();
	}
	
	private void connect()
	{
		try
		{
			Properties prop = new Properties();
			FileInputStream fis;
			fis = new FileInputStream("./user.properties");
			prop.load(fis);
			consumer = Soda2Consumer.newConsumer(prop.getProperty(URL),prop.getProperty(USER),prop.getProperty(PWD),prop.getProperty(TOKEN));

		} 
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void downloadCommonFields()
	{
		try {

			PrintWriter pw=new PrintWriter("./data/dataset.csv");
		
			//ClientResponse cr=consumer.query("/api/catalog/v1?only=datasets", HttpLowLevel.JSON_TYPE, SoqlQuery.SELECT_ALL);
			//URI url=new URI("https://mydata.iadb.org/api/catalog/v1?only=datasets");
			
			//ClientResponse cr=consumer.getHttpLowLevel().queryRaw(url, HttpLowLevel.JSON_TYPE);

			//cr.getEntity(Dataset.LIST_TYPE);
			
			ClientResponse cr=consumer.query("gb7u-r58j", HttpLowLevel.JSON_TYPE, SoqlQuery.SELECT_ALL);
			
			List<DatasetTemp> datasets=cr.getEntity(DatasetTemp.LIST_TYPE);
			
			for (DatasetTemp dsID:datasets) {
				if(dsID.getType().equals("table"))
				{
					SodaDdl importer = new SodaDdl(consumer.getHttpLowLevel());
					   
			         String ds=dsID.getId();
			         Dataset dsi=(Dataset)importer.loadDatasetInfo(ds);
			         List<Column> columns=dsi.getColumns();
			         	String country[]= new String[]{"Country","Country_es","País","Pais","Pais Nombre EN","Pais Nombre ES","ID_PaisRequisito","PAIS","COUNTRY"};
			         	String city[]= new String[]{"Ciudad","City","CiudadId"};
			         	String year[]= new String[]{"año","Year","year","Año","Año_Fecha","PrimeraMedicionIndicadores","PrimeraMedicionEncuestas","Año_Texto",
			         			"Year_Date","Year_Text","Año_FechayHora","Ano",	
			         			"Year_Date&Time"
			         	};
			        	String indicatorName[]= new String[]{"Nombre_Indicador",
			        			"Indicador_es",
			        			"Indicador",
			        			"IdIndicador",
			        			"TituloIndicador",
			        			"INDICATOR_ID",
			        			"INDICATOR","IndicadorId",
			        			"Indicador Nombre ES",
			        			"Indicador Nombre PT",
			        			"Indicador_EN",
			        			"Indicador_Nombre_ES",
			        			"IndicadorNombre EN",
			        			"IndicadorNombre ES",
			        			"Indicador Nombre EN"};

			        	String indicatorCategory[]= new String[]{"Categoría_Indicador","Indicador"};
			        	String indicatorValue[]= new String[]{"Puntaje","Valor","VALUE"};
			        	String indicatorUnit[]= new String[]{"UNIT","Unit_ES","Indicator/Unit",	"UNITS","Varunit","Valor %",
			        			"Valor %","Unit_EN","indicatorCategory",
			        			"valueusd",
			        			"Value","Aggregated_Value"
			        	};

			        	ArrayList<SField> fieldsToFind=new ArrayList<SField>();
			        	fieldsToFind.add(new SField(country,"country"));
			        	fieldsToFind.add(new SField(city,"city"));

			        	fieldsToFind.add(new SField(year,"year"));
			        	fieldsToFind.add(new SField(indicatorName,"indicatorName"));
			        	fieldsToFind.add(new SField(indicatorCategory,"indicatorCategory"));
			        	fieldsToFind.add(new SField(indicatorValue,"indicatorValue"));
			        	fieldsToFind.add(new SField(indicatorUnit,"indicatorUnit"));			        	
			        	
			        	
			         for (Column c : columns) {
						//System.out.println(c.getFieldName());
			        	
			        	 boolean found=false;
			        	 for (int i = 0; i < fieldsToFind.size()&&!found; i++) {
			        		 SField f=fieldsToFind.get(i);
			        		 int id=isValue(c,f.getValues());
			 	        	 if(id>=0)
			 	        	 {
			 	        		 found=true;
			 	        		//System.out.println(f.name+": "+f.values[id]);
			 	        		printColumn(pw,ds,f.getName(),f.value(id),c.getDataTypeName());
			 	        	 }
			        	 }
			        	 
			        	 if(!found)
			        		 printColumn(pw,ds,"Other",c.getName(),c.getDataTypeName());
			        	
			        	
					}
				}
				
			}
	         
		pw.close();
		}
		
		catch (LongRunningQueryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SodaError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void printColumn(PrintWriter pw,String ds, String name, String value,
			String dataTypeName) {
		pw.println(ds+","+name+","+value+","+dataTypeName);
		
	}
	
	private int isValue(Column c,String[] values) {
		
		for (int i = 0; i < values.length; i++) {
			if(values[i].equalsIgnoreCase(c.getName()))
				return i;
		}
		return -1;
	}
	
	public static void main(String[] args) {
		
	CatalogApi ca=new CatalogApi();
	ca.downloadCommonFields();
		
		
	}

	

	

}

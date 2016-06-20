package bid.socrata.api.resources;

import java.util.Date;
import java.util.List;

import com.sun.jersey.api.client.GenericType;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DatasetTemp {
	
    public static final GenericType<List<DatasetTemp>> LIST_TYPE = new GenericType<List<DatasetTemp>>() {};

    @JsonCreator
    public DatasetTemp(@JsonProperty("name") String name,
    		@JsonProperty("u_id") String id, 
    		@JsonProperty("type") String type
    	//	@JsonProperty("routing_approval") String routing_approval
//    		@JsonProperty("owner") String owner,
//    		@JsonProperty("creation_date") Date creation_date,
//    		@JsonProperty("public") String publiC,
//    		@JsonProperty("dataset_link") String dataset_link 		
   		) {
		super();
		this.name = name;
		this.id = id;
		this.type = type;
//		this.routing_approval=routing_approval;
//		this.owner=owner;
//		this.creation_date=creation_date;
//		this.publiC=publiC;
//		this.dataset_link=dataset_link;
		
	}
	
    String name;
    String id;
    String type;
     String routing_approval;
     String owner;
     Date creation_date;
     String publiC;
     String dataset_link;
    
 
    
     
    @JsonProperty("name")
     public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	@JsonProperty("u_id")
 	public String getId() {
 		return id;
 	}
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	@JsonProperty("type")
 	public String getType() {
 		return type;
 	}
 	public void setType(String type) {
 		this.type = type;
 	}
     
}

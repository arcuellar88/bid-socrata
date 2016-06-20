package bid.socrata.api.resources;

import java.util.Date;
import java.util.List;

import com.socrata.model.Location;
import com.sun.jersey.api.client.GenericType;

import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class DatasetID {
	
    public static final GenericType<List<DatasetID>> LIST_TYPE = new GenericType<List<DatasetID>>() {};

    @JsonCreator
    public DatasetID(@JsonProperty("name") String name,
    		@JsonProperty("u_id") String id, 
    		@JsonProperty("nbe_fxf") String nbe_fxf, 
    		@JsonProperty("description") String description,
    		@JsonProperty("type") String type, 
    		@JsonProperty("updatedAt") Date updatedAt,
    		@JsonProperty("createdAt") Date createdAt,
    		@JsonProperty("view_count") String view_count,
    		@JsonProperty("columns_name") String columns_name,
    		@JsonProperty("columns_field_name") String columns_field_name) {
		super();
		this.name = name;
		this.id = id;
		this.nbe_fxf = nbe_fxf;
		this.description = description;
		this.type = type;
		this.updatedAt = updatedAt;
		this.createdAt = createdAt;
		this.view_count = view_count;
		this.columns_name = columns_name;
		this.columns_field_name = columns_field_name;
	}
	String name;
    String id;
    String nbe_fxf;
    String description;
    String type;
    Date updatedAt;
    Date createdAt;
    String view_count;
    String columns_name;
    String columns_field_name;
    
     
    @JsonProperty("name")
     public String getName() {
 		return name;
 	}
 	public void setName(String name) {
 		this.name = name;
 	}
 	@JsonProperty("id")
 	public String getId() {
 		return id;
 	}
 	public void setId(String id) {
 		this.id = id;
 	}
 	
 	@JsonProperty("nbe_fxf")
 	public String getNbe_fxf() {
 		return nbe_fxf;
 	}
 	public void setNbe_fxf(String nbe_fxf) {
 		this.nbe_fxf = nbe_fxf;
 	}
 	
 	@JsonProperty("description")
 	public String getDescription() {
 		return description;
 	}
 	public void setDescription(String description) {
 		this.description = description;
 	}
 	
 	@JsonProperty("type")
 	public String getType() {
 		return type;
 	}
 	public void setType(String type) {
 		this.type = type;
 	}
 	
 	@JsonProperty("updatedAt")
 	public Date getUpdatedAt() {
 		return updatedAt;
 	}
 	public void setUpdatedAt(Date updatedAt) {
 		this.updatedAt = updatedAt;
 	}
 	
 	@JsonProperty("createdAt")
 	public Date getCreatedAt() {
 		return createdAt;
 	}
 	public void setCreatedAt(Date createdAt) {
 		this.createdAt = createdAt;
 	}
 	
 	@JsonProperty("view_count")
 	public String getView_count() {
 		return view_count;
 	}
 	public void setView_count(String view_count) {
 		this.view_count = view_count;
 	}
 	
 	@JsonProperty("columns_name")
 	public String getColumns_name() {
 		return columns_name;
 	}
 	public void setColumns_name(String columns_name) {
 		this.columns_name = columns_name;
 	}
 	
 	@JsonProperty("columns_field_name")
 	public String getColumns_field_name() {
 		return columns_field_name;
 	}
 	public void setColumns_field_name(String columns_field_name) {
 		this.columns_field_name = columns_field_name;
 	}
 	
 	
     
}

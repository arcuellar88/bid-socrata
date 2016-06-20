package bid.socrata.api.resources;

public class SField {
	
	private String[]values;
	private String name;
	

	public SField(String[]values, String name)
	{
		this.values=values;
		this.name=name;
	}
	
	
	public String[] getValues() {
		return values;
	}

	public void setValues(String[] values) {
		this.values = values;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String value(int id) {
		
		return values[id];
	}

}

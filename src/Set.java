import java.util.Arrays;

public class Set {
	String variable_name;
	String set_name;
	String type;
	int[] values;
	float membership_value;
	float centroid_val;

	public float getCentroid_val() {
		return centroid_val;
	}

	public void setCentroid_val(float centroid_val) {
		this.centroid_val = centroid_val;
	}

	public Set() {
	}

	public Set(String variable_name, String set_name, String type, int[] values) {
		this.variable_name = variable_name;
		this.set_name = set_name;
		this.type = type;
		this.values = values;
	}

	public String getVariable_name() {
		return variable_name;
	}

	public void setVariable_name(String variable_name) {
		this.variable_name = variable_name;
	}

	public String getSet_name() {
		return set_name;
	}

	public void setSet_name(String set_name) {
		this.set_name = set_name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int[] getValues() {
		return values;
	}

	public void setValues(int[] values) {
		this.values = values;
	}

	public float getMembership_value() {
		return membership_value;
	}

	public void setMembership_value(float membership_value) {
		this.membership_value = membership_value;
	}
	
	

	public void CentroidCalc(Set s) {
		
		float sum = 0;
		for (int i = 0; i < s.values.length; i++) {
			sum +=s.values[i];
			//System.out.println(sum);
		}

		float centroid = sum / values.length;
		s.setCentroid_val(centroid);
		s.centroid_val=centroid;
	}

	@Override
	public String toString() {
		return ("Variable name: " + this.getVariable_name() + " Set name : " + this.getSet_name() + " Type: "
				+ this.getType() + " values : " + Arrays.toString(this.getValues()) + " membership value : "
				+ this.getMembership_value()+
				 "    centroid value : "+this.getCentroid_val());

	}
}

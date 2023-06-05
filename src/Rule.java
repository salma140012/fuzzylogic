

public class Rule {

	
	String in_var1;
	String in_var2;
	String out_var;
	String set_name1;
	String set_name2;
	String set_name3;
	String operator;
	
    public Rule() {}

    public Rule(String in_var1, String in_var2, String out_var, String set_name1, String set_name2, String set_name3, String operator) {
        this.in_var1 = in_var1;
        this.in_var2 = in_var2;
        this.out_var = out_var;
        this.set_name1 = set_name1;
        this.set_name2 = set_name2;
        this.set_name3 = set_name3;
        this.operator = operator;
    }
	 
	    public String getIn_var1() {
	        return in_var1;
	    }

	    public void setIn_var1(String in_var1) {
	        this.in_var1 = in_var1;
	    }
	    public String getIn_var2() {
	        return in_var2;
	    }

	    public void setIn_var2(String in_var2) {
	        this.in_var2 = in_var2;
	    }


	    public String getOut_var() {
	        return out_var;
	    }

	    public void setOut_var(String out_var) {
	        this.out_var = out_var;
	    }

	    public String getSet_name1() {
	        return set_name1;
	    }

	    public void setSet_name1(String set_name1) {
	        this.set_name1 = set_name1;
	    }
	    
	    public String getSet_name2() {
	        return set_name2;
	    }

	    public void setSet_name2(String set_name2) {
	        this.set_name2 = set_name2;
	    }
	    
	    public String getSet_name3() {
	        return set_name3;
	    }

	    public void setSet_name3(String set_name3) {
	        this.set_name3 = set_name3;
	    }
	    public String getOperator() {
	        return operator;
	    }

	    public void setOperator(String operator) {
	        this.operator = operator;
	    }
	    
	

}

import java.util.ArrayList;
import java.util.Scanner;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class FuzzyLogic {

	ArrayList<Variable> variables = new ArrayList<Variable>();
	ArrayList<Variable> in_variables = new ArrayList<Variable>();
	ArrayList<Variable> out_variables = new ArrayList<Variable>();
	ArrayList<Set> sets = new ArrayList<Set>();
	ArrayList<Set> in_sets = new ArrayList<Set>();
	ArrayList<Set> out_sets = new ArrayList<Set>();
	ArrayList<Rule> rules = new ArrayList<Rule>();

	public void runSimulation() {  
		float risk=0;
		String set="";
		System.out.println("Running the simulation...");
		fuzzification();
		setOutVariable();
		setOutSet();
		System.out.println("Fuzzification => done");
		inference();
		System.out.println("Inference => done");
		risk=defuzzification();
		System.out.println("Defuzzification => done");
		set=getHighestOutMembership();
		System.out.println("The predicted risk is " +set+ " ("+risk+")");
		

		
	}

	FuzzyLogic(){
		
		
	}
	public void fuzzification() {
		setInSet();
		
		for (Set s : this.in_sets) {

			float membership = 0;

			if (s.getType().equals("TRI")) {
				float a = s.values[0];
				float b = s.values[1];
				float c = s.values[2];
				float x = 0;
				for (Variable v : this.in_variables) {
					if (v.getName().equals(s.getVariable_name())) {
						x = v.getCrisp_value();
					}
				}

				if (b != c && a != b) {
					membership = Math.max(Math.min((x - a) / (b - a), (c - x) / (c - b)), 0);
				} else if (b == c) {
					membership = Math.max((x - a) / (b - a), 0);
				} else {
					membership = Math.max((c - x) / (c - b), 0);
				}

				s.setMembership_value(membership);
				//System.out.println(s);
			}

			else if (s.getType().equals("TRAP")) {
				float a = s.values[0];
				float b = s.values[1];
				float c = s.values[2];
				float d = s.values[3];
				float x = 0;
				for (Variable v : this.in_variables) {
					if (v.getName().equals(s.getVariable_name())) {
						x = v.getCrisp_value();
					}
				}
				if (d != c && a != b) {
					membership = Math.max(Math.min(Math.min((x - a) / (b - a), 1), (d - x) / (d - c)), 0);
				} else if (d == c) {
					membership = Math.max(Math.min((x - a) / (b - a), 1), 0);
				} else {
					membership = Math.max(Math.min(1, (d - x) / (d - c)), 0);
				}
				s.setMembership_value(membership);
				//System.out.println(s);
			}
		}

	}

	public void inference() {

		float num1 = 0;
		float num2 = 0;
		float min = 0;
		float max = 0;

		for (Rule r : this.rules) {

			if (r.getOperator().equals("and")) {

				num1 = getMembershipDegree(r.getIn_var1(), r.getSet_name1());
				num2 = getMembershipDegree(r.getIn_var2(), r.getSet_name2());
				min = Math.min(num1, num2);
				for (Set s : this.out_sets) {
					if (s.getSet_name().equals(r.getSet_name3())) {
						float maxx = Math.max(min, s.getMembership_value());
						s.setMembership_value(maxx);
					}
				}

			} else if (r.getOperator().equals("or")) {
				num1 = getMembershipDegree(r.getIn_var1(), r.getSet_name1());
				num2 = getMembershipDegree(r.getIn_var2(), r.getSet_name2());
				max = Math.max(num1, num2);
				for (Set s : this.out_sets) {
					if (s.getSet_name().equals(r.getSet_name3())) {
						float maxx = Math.max(max, s.getMembership_value());
						s.setMembership_value(maxx);
					}
				}

			} else if (r.getOperator().equals("and_not")) {

				num1 = getMembershipDegree(r.getIn_var1(), r.getSet_name1());
				num2 = getMembershipDegree(r.getIn_var2(), r.getSet_name2());
				min = Math.min(num1, (1 - num2));
				for (Set s : this.out_sets) {
					if (s.getSet_name().equals(r.getSet_name3())) {
						float maxx = Math.max(min, s.getMembership_value());
						s.setMembership_value(maxx);
					}
				}

			}

			else if (r.getOperator().equals("or_not")) {
				num1 = getMembershipDegree(r.getIn_var1(), r.getSet_name1());
				num2 = getMembershipDegree(r.getIn_var2(), r.getSet_name2());
				max = Math.max(num1, (1 - num2));
				for (Set s : this.out_sets) {
					if (s.getSet_name().equals(r.getSet_name3())) {
						float maxx = Math.max(max, s.getMembership_value());
						s.setMembership_value(maxx);
					}
				}

			}

		}

	}

	public float defuzzification() {
	
		float multiplication=0;
		float sum=0;
		float Fn=0;
		
        for(Set s:this.out_sets) {
        	s.CentroidCalc(s);
        	multiplication+=s.getMembership_value()*s.getCentroid_val();
        	sum+=s.getMembership_value();
    	
        }
	
		Fn=multiplication/sum;
	
	 return Fn;
		
		
	}

	public void addVariable(Variable v) {
		this.variables.add(v);
	}

	public void addSet(Set s) {
		this.sets.add(s);
	}

	public void addRule(Rule r) {
		this.rules.add(r);
	}

	public void setInVariable() {
		for (Variable v : this.variables) {
			if (v.getType().equals("IN")) {
				this.in_variables.add(v);
			}
		}

	}

	public void setOutVariable() {
		for (Variable v : this.variables) {
			if (v.getType().equals("OUT")) {
				this.out_variables.add(v);
			}
		}

	}

	public void setInSet() {
		setInVariable();
		for (Variable v : this.in_variables) {
			for (Set s : this.sets) {
				if (v.getName().equals(s.getVariable_name())) {
					this.in_sets.add(s);
				}
			}

		}
	}

	public void setOutSet() {
		for (Variable v : this.out_variables) {
			for (Set s : this.sets) {
				if (v.getName().equals(s.getVariable_name())) {
					this.out_sets.add(s);
				}
			}

		}
	}

	public void getInVariableCrisp() {
		for (Variable v : this.variables) {
			if (v.getType().equals("IN")) {
				Scanner sc = new Scanner(System.in);
				System.out.print(v.getName() + " : ");
				int n = sc.nextInt();
				v.setCrisp_value(n);
			
			}
		}

	}

	public float getMembershipDegree(String v_name, String s_name) {
		for (Variable v : this.in_variables) {
			if (v.getName().equals(v_name)) {
				for (Set s : this.in_sets) {
					if (s.getSet_name().equals(s_name)) {
						return s.getMembership_value();
					}

				}

			}
		}

		return -1;
	}
	
	public String getHighestOutMembership() {
		String n="";
		float max=0;
		for(Set s:this.out_sets) {
			if(s.getMembership_value()>=max) {
				max=s.getMembership_value();
				n=s.getSet_name();
			}
			
		}
		return n;
	}
 
	
}


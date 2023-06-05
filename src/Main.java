import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Scanner;



public class Main {

	public static void main(String[] args) throws IOException {
		
		
		boolean main = true;
		boolean sub = true;
	
		
		
		while (main) {
			sub=true;
			System.out.println("Fuzzy Logic Toolbox");
			System.out.println("===================");
			System.out.println("1- Create a new fuzzy system");
			System.out.println("2- Read input from file and write output to file");
			System.out.println("3- Quit\n");
			Scanner sc = new Scanner(System.in);
			int n = sc.nextInt(); // user's choice
			if (n == 3) {
				main = false;
			} else if(n==1){
				FuzzyLogic fl = new FuzzyLogic();
				System.out.println("Enter the system’s name and a brief description:");
				System.out.println("------------------------------------------------");
				Scanner string = new Scanner(System.in);
				String description = string.nextLine();
				while (sub) {
					System.out.println("Main Menu:");
					System.out.println("==========");
					System.out.println("1- Add variables.");
					System.out.println("2- Add fuzzy sets to an existing variable.");
					System.out.println("3- Add rules.");
					System.out.println("4- Run the simulation on crisp values.");
					System.out.println("5- Close\n");
					n = sc.nextInt();

					switch (n) {
					case 1:
						System.out.println("Enter the variable’s name, type (IN/OUT) and range like lower upper :");
						System.out.println("(Press x to finish)");
						System.out.println("--------------------------------------------------------------------");
						while (true) {
							Variable variable = new Variable();
							//fl.printList();
							String s = string.nextLine();

							if (s.equals("x")) {
								break;
							} else {
								String[] parts = s.split(" ");

								variable.setName(parts[0]);
								variable.setType(parts[1]);
								variable.setRange_start(Float.parseFloat(parts[2]));
								variable.setRange_end(Float.parseFloat(parts[3]));
								fl.addVariable(variable);
							}

						}
						break;

					case 2:
						System.out.println("Enter the variable’s name:");
						System.out.println("--------------------------");
						String s = string.nextLine();
						String var_name=s;
						System.out.println("Enter the fuzzy set name, type (TRI/TRAP) and values: (Press x to finish)");
						System.out.println("-----------------------------------------------------");
						while (true) {
							Set set = new Set();
							s = string.nextLine();

							if (s.equals("x")) {
								break;

							} else {
								set.setVariable_name(var_name);
								String[] parts = s.split(" ");
								set.setSet_name(parts[0]);
								set.setType(parts[1]);
								if (parts[1].equals("TRI")) {
									int[] arr = { Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
											Integer.parseInt(parts[4]) };
									set.setValues(arr);
									fl.addSet(set);

								}

								else if (parts[1].equals("TRAP")) {
									int[] arr = { Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
											Integer.parseInt(parts[4]), Integer.parseInt(parts[5]) };
									set.setValues(arr);
									fl.addSet(set);
								}

							}

						}
						break;
						
					case 3:
						System.out.println("Enter the rules in this format: (Press x to finish)");
						System.out.println("IN_variable set operator IN_variable set => OUT_variable set-");
						System.out.println("------------------------------------------------------------");
						while (true) {
							Rule rule=new Rule();
							s = string.nextLine();

							if (s.equals("x")) {
								break;

							} else {
								String[] parts = s.split(" ");
								rule.setIn_var1(parts[0]);
								rule.setSet_name1(parts[1]);
								rule.setOperator(parts[2]);
								rule.setIn_var2(parts[3]);
								rule.setSet_name2(parts[4]);
								rule.setOut_var(parts[6]);
								rule.setSet_name3(parts[7]);
								fl.addRule(rule);

							}
						}
						break;
						
					case 4:
						if(fl.sets.size()==0 || fl.rules.size()==0) {
							System.out.println("Simulation can't be started!! Please add the fuzzy sets and rules first.\n");
							break;
						}
						else {
						System.out.println("Enter the crisp values:");
						System.out.println("-----------------------");
				
						fl.getInVariableCrisp();
						
						fl.runSimulation();
						
						}
						
						break;
					case 5:	
						sub=false;
						break;
                     default:
                    	 System.out.println("Wrong input!! please enter your choice again.");
                    	 break;
					}

				}
			}
			else {
				FuzzyLogic fl = new FuzzyLogic();
				String in_f="";
				String out_f="";
				 System.out.println("\nEnter which input file you'd like");
				 System.out.println("1- Lab example");
				 System.out.println("2-Assignment example");
				 Scanner in = new Scanner(System.in);
				 int nn=in.nextInt();
				 if (nn==1) {
					 in_f="1.txt";
					 out_f="Output1.txt";
				 }
				 else {in_f="2.txt";
				 out_f="Output2.txt";}
				BufferedReader fileReader =null;
		        try {
		            fileReader = new BufferedReader(new FileReader(in_f));
		        } catch (FileNotFoundException e) {
		            throw new RuntimeException(e);
		        }
		    	PrintStream o = new PrintStream(new File(out_f));
				PrintStream console = System.out;
				System.setOut(o);
				String description = fileReader.readLine();
				while (sub) {
					
					String n2 = fileReader.readLine();

					switch ( Integer.parseInt(n2)) {
					case 1:
						
						while (true) {
							Variable variable = new Variable();
							
							String s =  fileReader.readLine();

							if (s.equals("x")) {
								break;
							} else {
								String[] parts = s.split(" ");

								variable.setName(parts[0]);
								variable.setType(parts[1]);
								variable.setRange_start(Float.parseFloat(parts[2]));
								variable.setRange_end(Float.parseFloat(parts[3]));
								fl.addVariable(variable);
							}

						}
						break;

					case 2:
						
						String s =  fileReader.readLine();
						String var_name=s;
						
						while (true) {
							Set set = new Set();
							s =  fileReader.readLine();

							if (s.equals("x")) {
								break;

							} else {
								set.setVariable_name(var_name);
								String[] parts = s.split(" ");
								set.setSet_name(parts[0]);
								set.setType(parts[1]);
								if (parts[1].equals("TRI")) {
									int[] arr = { Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
											Integer.parseInt(parts[4]) };
									set.setValues(arr);
									fl.addSet(set);

								}

								else if (parts[1].equals("TRAP")) {
									int[] arr = { Integer.parseInt(parts[2]), Integer.parseInt(parts[3]),
											Integer.parseInt(parts[4]), Integer.parseInt(parts[5]) };
									set.setValues(arr);
									fl.addSet(set);
								}

							}

						}
						break;
						
					case 3:
						
						while (true) {
							Rule rule=new Rule();
							s = fileReader.readLine();
							if (s.equals("x")) {
								break;

							} else {
								String[] parts = s.split(" ");
								rule.setIn_var1(parts[0]);
								rule.setSet_name1(parts[1]);
								rule.setOperator(parts[2]);
								rule.setIn_var2(parts[3]);
								rule.setSet_name2(parts[4]);
								rule.setOut_var(parts[6]);
								rule.setSet_name3(parts[7]);
								fl.addRule(rule);

							}
						}
						break;
						
					case 4:
							for (Variable v : fl.variables) {
								
								if (v.getType().equals("IN")) {
									String n3 =fileReader.readLine();
									v.setCrisp_value(Integer.parseInt(n3));
								}
								}
						
						fl.runSimulation();
						System.setOut(console);
						
						if (nn==1) {
							System.out.println("\nCheck output1.txt for the output!\n");
						 }
						 else {System.out.println("\nCheck output2.txt for the output!\n");}
						
							
						break;
						
					case 5:	
						sub=false;
						break;
						
                     default:
                    	 System.out.println("Wrong input!! please enter your choice again.");
                    	 break;
					}

				}			
			}
		}
		
		
	}
	
	
}

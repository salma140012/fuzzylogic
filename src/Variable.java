
public class Variable {
String name;
String type;
int crisp_value;
float range_start;
float range_end;

Variable(){}
public Variable(String name, String type, float range_start, float range_end, int crisp_value) {
    this.name = name;
    this.type = type;
    this.crisp_value = crisp_value;
    this.range_start = range_start;
    this.range_end = range_end;
}

/*Variable(String name,String type,float range_start,float range_end){
	this.name=name;
	this.type=type;
	this.range_start=range_start;
	this.range_end=range_end;
}
*/
public String getName() {
    return name;
}

public void setName(String name) {
    this.name = name;
}

public String getType() {
    return type;
}

public void setType(String type) {
    this.type = type;
}

public float getRange_start() {
    return range_start;
}

public void setRange_start(float range_start) {
    this.range_start = range_start;
}

public float getRange_end() {
    return range_end;
}

public void setRange_end(float range_end) {
    this.range_end = range_end;
}

public int getCrisp_value() {
    return crisp_value;
}

public void setCrisp_value(int crisp_value) {
    this.crisp_value = crisp_value;
}
@Override
public String toString() {
     return ("Variable name: "+this.getName()+
                 " Type : "+ this.getType() +
                 " Lower bound: "+ this.getRange_start() +
                 " Upper bound : " + this.getRange_end() +
                 " Crisp value : " + this.getCrisp_value());
}
}

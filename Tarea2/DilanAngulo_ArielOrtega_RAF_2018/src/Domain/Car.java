package Domain;

//Clase constructor
public class Car {

    //atributos privados
    private String name;
    private int year;
    private float mileage;
    private boolean american;
    private int serial;
    
    
    //Constructor
    public Car(String name, int year, float mileage, boolean american, int serial) {
        this.name = name;
        this.year = year;
        this.mileage = mileage;
        this.american = american;
        this.serial = serial;
    }

    //Constructor vacio
    public Car() {
        this.name = "";
        this.year = 0;
        this.mileage = 0;
        this.american = false;
        this.serial = 0;
    }

    //Setter and Getter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public float getMileage() {
        return mileage;
    }

    public void setMileage(float mileage) {
        this.mileage = mileage;
    }

    public boolean isAmerican() {
        return american;
    }

    public void setAmerican(boolean american) {
        this.american = american;
    }

    public int getSerial() {
        return serial;
    }

    public void setSerial(int serial) {
        this.serial = serial;
    }

    @Override
    public String toString() {
        return "Car{" + "name=" + name + ", year=" + year + ", mileage=" + mileage + ", american=" + american + ", serial=" + serial + '}';
    }

    public int sizeInBytes() {        
        return 40 + 4 + 4 + 1 + 4;
        //40 ya que se permite un maximo de 20 caracteres, por 2 bytes que requiere cada uno
        //4 bytes que requiere un entero para año
        //4 bytes que requiere un numero flotante para kilometraje
        //1 byte que requiere el atributo american
        //4 bytes que requiere el entero de la serie del vehiculo
    }

}

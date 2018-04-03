package File;

import Domain.Car;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.zip.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CarFile {

    public RandomAccessFile randomAccessFile;
    private int regsQuantity;//cantidad de registros en el archivo
    private int regSize = 300;
    private String myFilePath;//ruta

    public CarFile(File file) throws IOException {
        //Se almacena la ruta
        myFilePath = file.getPath();
        
        //Se realiza una comprobacion de que el archivo realmente exista y sea real 
        if (file.exists() && !file.isFile()) {
            throw new IOException(file.getName() + " is an invalid file");
        } else {
            randomAccessFile = new RandomAccessFile(file, "rw");
            this.regsQuantity
                    = (int) Math.ceil((double) randomAccessFile.length() / (double) regSize);
        }//end else
    }//end method

    //Metodo para cerrar el archivo luego de su uso
    public void close() throws IOException {
        randomAccessFile.close();
    }//end method

    //indicar la cantidad de registros de nuestro archivo
    public int fileSize() {
        return this.regsQuantity;
    }//end method

    //Metodo para insertar un nuevo registro en una posicion especifica
    public boolean putValue(int position, Car car) throws IOException {
        //primero: verificar que sea v'alida la inserci'on
        if (position < 0 && position > fileSize()) {
            System.err.println("1001 - Record position is out of bounds");
            return false;
        } else {
            if (car.sizeInBytes() > this.regSize) {
                System.err.println("1002 - Record size is out of bounds");
                return false;
            } else {
                //si llega aqui es por que ambos parametros son correctos
                randomAccessFile.seek(position * this.regSize);
                randomAccessFile.writeUTF(car.getName());
                randomAccessFile.writeInt(car.getYear());
                randomAccessFile.writeFloat(car.getMileage());
                randomAccessFile.writeBoolean(car.isAmerican());
                randomAccessFile.writeInt(car.getSerial());
                return true;
            }//end else nested
        }//end else
    }//end method

    //insertar al final del archivo
    public void addEndRecord(Car car) throws IOException {
        if (putValue(this.regsQuantity, car)) {
            //si se cumple la accion aumenta el contador de cantidad de registros
            regsQuantity++;
        }//end if
    }//end method

    //obtener un auto
    public Car getCar(int position) throws IOException {
        // Se valida la posicion dada 
        if (position >= 0 && position <= this.regsQuantity) {
            //colocamos el brazo en el lugar adecuado
            randomAccessFile.seek(position * this.regSize);

            //llevamos a cabo la lectura
            Car carTemp = new Car();
            carTemp.setName(randomAccessFile.readUTF());
            carTemp.setYear(randomAccessFile.readInt());
            carTemp.setMileage(randomAccessFile.readFloat());
            carTemp.setAmerican(randomAccessFile.readBoolean());
            carTemp.setSerial(randomAccessFile.readInt());
            return carTemp;
        } else {
            System.err.println("1003 - position is out of bounds");
            return null;
        }//end else
    }//end method

    //Metodo para eliminar un auto
    public boolean deleteCar(int serial) throws IOException {
        Car mycar;

        //buscar el auto
        for (int i = 0; i < this.regsQuantity; i++) {
            //obtener el estudiante de la posici'on actual
            mycar = this.getCar(i);

            //preguntar si es el auto que deseo eliminar
            if (mycar.getSerial() == serial) {
                //marcar como eliminado
                mycar.setSerial(-1);

                return this.putValue(i, mycar);
            }//end if
        }//end for
        return false;
    }//end method

    //Retornar una lista de autos con excepciones de autos "eliminados"    
    public ObservableList<Car> getAllCars() throws IOException {
        ArrayList<Car> carsArray = new ArrayList<Car>();

        for (int i = 0; i < this.regsQuantity; i++) {
            Car cTemp = this.getCar(i);

            if (cTemp != null && (!cTemp.getName().equalsIgnoreCase("")) && cTemp.getSerial() != -1 && !cTemp.getName().equalsIgnoreCase("deleted")) {
                carsArray.add(cTemp);
            }

        }//end for       
        ObservableList<Car> listCar = FXCollections.observableArrayList(carsArray);
        return listCar;

    }//end method

    //Metodo para buscar un vehiculo en el archivo
    public int searchCar(int serial) throws IOException {
        Car mycar;
        Car carTemp = new Car();

        //buscar el auto
        for (int i = 0; i < fileSize(); i++) {
            //Se va moviendo el braz conforme corre el ciclo para una busqueda
            randomAccessFile.seek(i * regSize);

            //obtener el auto de la posici'on actual
            mycar = this.getCar(i);

            if (mycar.getSerial() == serial) {
                return i;
            }
        }
        return -1;
    }//end method

    //Metodo que funciona para cambiar datos del archivo (actualizar un auto)
    public Car updateCar(int serial, String name, int year, boolean american) throws IOException {
        Car mycar;

        for (int i = 0; i < this.regsQuantity; i++) {
            mycar = this.getCar(i);

            //preguntar si es el auto que deseo actualizar
            if (mycar.getSerial() == serial) {

                mycar.setName(name);
                mycar.setYear(year);
                mycar.setAmerican(american);
                this.putValue(i, mycar);

                return mycar;

            }
        }
        return null;
    }//end method

    //Metodo que funcionna para comprimir el archivo
    public void compressFile(File source, File destination) throws IOException {
        byte[] buffer = new byte[4096];
        FileInputStream fis = new FileInputStream(source);
        FileOutputStream fos = new FileOutputStream(destination);
        GZIPOutputStream gos = new GZIPOutputStream(fos);
        int read;

        while ((read = fis.read(buffer)) != -1) {
            gos.write(buffer, 0, read);
        }
        gos.finish();
        gos.close();
        fos.close();
        fis.close();
    }//end method
}

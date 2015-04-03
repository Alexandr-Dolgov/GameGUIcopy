package sample;

import java.io.*;

/**
 * Created by ankovol on 26.02.2015.
 * Changed by Alexandr-Dplgov
 */
public class Man implements Serializable{
    //поля класса
    private String name;
    private int age;

    //конструкторы
    public Man(String name) {
        this.name = name;
    }
    public Man(String name,int age) {
        this.name=name;
        this.age = age;
    }
    // методы
    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public int getAge (){
        return age;
    }

    public void save (String path){
        try(FileOutputStream fos = new FileOutputStream(path);
            ObjectOutputStream oos = new ObjectOutputStream(fos)
        ){
            oos.writeObject(this);
        }catch (FileNotFoundException ex){
            System.out.println("File not found");
        }catch (IOException ex) {
            System.out.println("Error");
        }
    }

    public static Man load (String path) {
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(path);
        } catch (FileNotFoundException ex) {
            System.out.println("Файл "+path+"не найден");
        }
        ObjectInputStream ois = null;
        Man man = null;
        try{
            ois = new ObjectInputStream(fis);
            man = (Man) ois.readObject();
        }catch (ClassNotFoundException ex){
            System.out.println("Из фаила находящегося по адресу " + path + " не удалось прочитаь объект типа Man");
        }catch (IOException ex){
            System.out.println("Ошибка чтения из файла" + path);
        }
        try {
            ois.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия потока ObjectInputStream ois\n" +
                    "ассоциированного с потоком FileInputStream fis\n" +
                    "ассоциированного с файлом " + path);
            e.printStackTrace();
        }
        try {
            fis.close();
        } catch (IOException e) {
            System.out.println("Ошибка закрытия потока FileInputStream fis\n" +
                    "ассоциированного с файлом " + path);
        }
        return man;
    }
}

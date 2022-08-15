package lib;

import model.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class XFile {
    public static void writeFile(String path){
        File f = new File(path);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(f);
            byte[] arr = {10,20,30};
            fos.write(arr);
            fos.close();
        } catch(IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void readFile(String path) {
        File f = new File(path); // ko co file se tu tao ra
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(f);
            int fileSize = (int) f.length();
            byte[] arr = new byte[fileSize];

            fis.read(arr);
            for (int i = 0; i < arr.length; i++) {
                System.out.println(arr[i]);
            }
            fis.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }

    }
    public static void writeDataFile(String path) {
        try{
            FileOutputStream fos = new FileOutputStream(path);
            DataOutputStream dos = new DataOutputStream(fos);
            dos.writeInt(5);
            dos.writeUTF("Thông");
            dos.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static void readDataFile(String path) {
        try{
            FileInputStream fos = new FileInputStream(path);
            DataInputStream dis = new DataInputStream(fos);
            System.out.println(dis.readInt());
            System.out.println(dis.readUTF());
            System.out.println(dis.readDouble());
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }

    public static void writeBuffer(String path, String text) {
        try {
            FileWriter fw= new FileWriter(path);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(text);
            bw.close();
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
    public static String readBuffer(String path) {
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr= new FileReader(path);
            BufferedReader br = new BufferedReader(fr);
            String str = br.readLine(); //first line auto turn cursor to  next row
            while(str!=null) {
                sb.append(str);
                str = br.readLine();
                if(str!=null) sb.append("\n");
            }

        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return sb.toString();
    }
    public static void writeObject(String path, Object o) {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(o);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    public static Object readObject(String path) {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path));
            Object o = ois.readObject();
            ois.close();
            return o;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static void main(String[] args) {
//        String filePath = "testFile.dat";
//        writeFile(filePath);
//        readFile(filePath);
//        writeDataFile(filePath);
//        readDataFile(filePath);
//        String text = "Name:\nAge:\nGender:";
//        writeBuffer(filePath, text);
//        System.out.println(readBuffer(filePath));


        // Add object
        String filePath = "testStudent.dat";
        List<Student> students = new ArrayList<>();
        students.add(new Student("John", "GCC0902"));
        students.add(new Student("Harley", "GCC0903"));
        students.add(new Student("Jenny", "GCC0904"));
        writeObject(filePath, students);

        List<Student> newLst = (List<Student>) readObject(filePath);
        for (Student o: newLst) {
            System.out.println(o.getName());
        }
    }

}

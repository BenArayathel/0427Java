package Srializarion;
import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ArrayListSerialization
{
   public static void main(String [] args)
   {
       ArrayList<String> data=new ArrayList<>();
       data.add("REVATURE");
       data.add("JAVA");

       //Serialization
       try{
         FileOutputStream fos= new FileOutputStream("/Users/amir/Desktop/bank.txt");
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(data);
         oos.close();
       }catch(IOException e){
            e.printStackTrace();
        }
    
     //DeSerialization
       try
       {
           FileInputStream fis = new FileInputStream("/Users/amir/Desktop/bank.txt");
           ObjectInputStream ois = new ObjectInputStream(fis);
           data =(ArrayList) ois.readObject();
           ois.close();
        }catch(IOException e){
            e.printStackTrace();
            
         }catch(ClassNotFoundException e){
            e.printStackTrace();
         }
       for(String s: data){
           System.out.println(s);
       }
  
 
   
   }
   
   
   
}
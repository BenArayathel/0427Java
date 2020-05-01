package cerealization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashSet;
import java.util.Set;


public class MainWriter {
	
public static void main(String[] args) {
	Robot Ez8 = new Robot(8, "Shiro", "240 mm cannon");
	String filename = "./0427Java/giantrobot1.txt"; //you don't get to see my path names
	writeObject(filename, Ez8);
	Robot groundG = readObject(filename);
	System.out.println(groundG);
	Set<Robot> SRW = new HashSet<>();
	SRW.add(new Robot(14, "Coop", "Everything"));
	SRW.add(new Robot(2, "Kouji", "Rocket Punch"));
	SRW.add(new Robot(5, "Kid", "Bry Sword"));
	String filename2 = "./0427Java/giantrobot2.txt";
	writeObject(filename2, SRW);
}

private static Robot readObject (String filename)
{

Object obj = null;
Robot r = null;

try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename)))
{
obj = ois.readObject(); //de-serialization; this gets you an object
r = (Robot)obj; //this specifies what the object is through casting

}
catch(FileNotFoundException er)
{
	er.printStackTrace();
}
catch(IOException er)
{
	er.printStackTrace();
} catch (ClassNotFoundException e) {
	e.printStackTrace();
}
return r;
}

private static void writeObject (String filename, Robot r1) {
	try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
		oos.writeObject(r1); 
	}
		catch(FileNotFoundException er) {
			er.printStackTrace();
		}
	catch(IOException er)
	{
		er.printStackTrace();
}
}
private static void writeObject (String filename, Set<Robot> r1) {
	try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))){
		oos.writeObject(r1); 
	}
		catch(FileNotFoundException er) {
			er.printStackTrace();
		}
	catch(IOException er)
	{
		er.printStackTrace();
}
}
}
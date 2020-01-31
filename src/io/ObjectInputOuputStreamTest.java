package io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.Test;

public class ObjectInputOuputStreamTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// 序列化 為了方便就先不try cash
	@Test
	public void testObjectOutputStream() {
		try {

			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("object.dat"));

			oos.writeObject(new String("我愛101"));
			oos.flush();
			
			oos.writeObject(new Person("Mark", 18));
			oos.flush();
			
			oos.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 反序列化 為了方便就先不try cash
	@Test
	public void testObjectInputStream() throws FileNotFoundException, IOException, ClassNotFoundException {

		ObjectInputStream ois = new ObjectInputStream(new FileInputStream("object.dat"));

		Object obj = ois.readObject();
		String str = (String) obj;
		
		Object obj2 = ois.readObject();
		Person p =  (Person) obj2;
		
		System.out.println(str);
		System.out.println(p);
		ois.close();
	}
}

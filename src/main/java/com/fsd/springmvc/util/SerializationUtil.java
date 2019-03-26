package com.fsd.springmvc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URISyntaxException;
import java.net.URL;

public class SerializationUtil {
	
		// deserialize to Object from given file
		public static Object deserialize(String fileName) throws IOException,
				ClassNotFoundException, URISyntaxException {
			
			//URL url = SerializationUtil.class.getClassLoader().getResource(fileName);
			
			File file = new File(fileName);
			
			if(file.length() > 0) {
			
				//InputStream is = SerializationUtil.class.getClassLoader().getResourceAsStream(fileName);
				InputStream is = new FileInputStream(fileName);
							
				ObjectInputStream ois = new ObjectInputStream(is);
				Object obj = ois.readObject();
				ois.close();
				return obj;
			
			}
			
			return null;
		}

		// serialize the given object and save it to file
		public static void serialize(Object obj, String fileName)
				throws IOException, URISyntaxException {
			
			//URL url = SerializationUtil.class.getClassLoader().getResource(fileName);
			
			//FileOutputStream fos = new FileOutputStream(new File(url.toURI()));
			FileOutputStream fos = new FileOutputStream(fileName);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);

			fos.close();
		}

}

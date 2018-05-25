package org.vincent.khiops;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;

public class KhiopsDictionnaryComposer {
	Writer writer;
	
	public KhiopsDictionnaryComposer(String dir){
		try {
			this.writer = new BufferedWriter(new OutputStreamWriter(
			          new FileOutputStream(dir + "dictionnary.kdic"), "utf-8"));
			writer.write("Dictionary Training\r\n{\r\n");
			
		} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
		}
	}
	
	public void addField(String field, String type){
		try {
			writer.write(" "+ type + " " + field +  "  ;\r\n");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close(){
		try {
			writer.write("};\r\n");
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

package org.vincent.khiops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class KhiopsTestComposer {
	
	String model;
	String scoring;
	String separator;
	String result_path;
	
	public KhiopsTestComposer(String model_path, String scoring_path, String separator, String result_path){
		this.model = model_path;
		this.scoring = scoring_path;
		this.separator = separator;
		this.result_path = result_path;
	}
	
	public String compose(String path){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(path + "scoring._kh"), "utf-8"))) {
			//Read dictionnary
			writer.	write("ClassManagement.OpenFile \r\n");
			writer.write("ClassFileName " + model + "\r\n");
			writer.write("OK\r\n");
			
			writer.write("ClassManagement.Classes.List.Key MNB_Training \r\n");
			writer.write("ClassManagement.ClassName SNB_Training \r\n");
			writer.write("TransferDatabase\r\n");
			
			writer.write("SourceDatabase.DatabaseFiles.List.Key SNB_Training \r\n");
			writer.write("SourceDatabase.DatabaseFiles.DataTableName " + scoring + "\r\n");
			writer.write("SourceDatabase.FieldSeparator "+ separator+ "\r\n");
			
			writer.write("TargetDatabase.DatabaseFiles.List.Key SNB_Training\r\n");
			writer.write("TargetDatabase.DatabaseFiles.DataTableName "+  result_path + "T_SNB_Validation.txt " + " \r\n");
			writer.write("TargetDatabase.FieldSeparator "+ separator+ "\r\n");
			writer.write("TransferDatabase\r\n");
			writer.write("Exit\r\n");
			
			writer.write("ClassManagement.ClassName MNB_Training\r\n");
			writer.write("TransferDatabase\r\n");
			writer.write("SourceDatabase.FieldSeparator ;\r\n");
			
			writer.write("SourceDatabase.DatabaseFiles.List.Key MNB_Training  \r\n");
			writer.write("SourceDatabase.DatabaseFiles.DataTableName "+  result_path + "T_MNB_Validation.txt " + " \r\n");
			writer.write("SourceDatabase.DatabaseFiles.DataTableName " + scoring + " \r\n");
			writer.write("SourceDatabase.FieldSeparator ;   \r\n");
			
			writer.write("TargetDatabase.DatabaseFiles.List.Key MNB_Training  \r\n");
			writer.write("TargetDatabase.DatabaseFiles.DataTableName "+  result_path + "T_MNB_Validation.txt " + " \r\n");
			writer.write("TransferDatabase \r\n");
			writer.write("Exit\r\n");
			
			writer.write("Exit\r\n");
			writer.write("OK\r\n");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path + "scoring._kh";
	}

}

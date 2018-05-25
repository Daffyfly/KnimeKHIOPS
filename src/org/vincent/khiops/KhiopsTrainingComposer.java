package org.vincent.khiops;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;

public class KhiopsTrainingComposer {
	
	String dict;
	String training;
	String separator;
	String result_path;
	String prediction;
	
	public KhiopsTrainingComposer(String dict_path, String training_path, String separator, String result_path, String prediction){
		this.dict = dict_path;
		this.training = training_path;
		this.separator = separator;
		this.result_path = result_path;
		this.prediction = prediction;
	}
	
	public String compose(String path){
		try (Writer writer = new BufferedWriter(new OutputStreamWriter(
	              new FileOutputStream(path + "training._kh"), "utf-8"))) {
			//Read dictionnary
			writer.	write("ClassManagement.OpenFile \r\n");
			writer.write("ClassFileName " + dict + "\r\n");
			writer.write("OK\r\n");
			writer.write("TrainDatabase.DatabaseFiles.List.Key Training\r\n");
			writer.write("TrainDatabase.DatabaseFiles.DataTableName " + training + "\r\n");
			writer.write("TrainDatabase.FieldSeparator " + separator + "\r\n");
			writer.write("TrainDatabase.SampleNumberPercentage 70\r\n");
			writer.write("TrainDatabase.FillTestDatabaseSettings\r\n");
			writer.write("AnalysisSpec.ModelingSpec.MAPNaiveBayesPredictor true\r\n");
			writer.write("AnalysisSpec.TargetAttributeName "+ prediction +"\r\n");
			writer.write("AnalysisSpec.MainTargetModality 2\r\n");
			writer.write("AnalysisSpec.AttributeConstructionSpec.RecodingClass true\r\n");
			writer.write("AnalysisResults.ResultFilesDirectory "+ result_path + "\r\n");
			writer.write("ComputeStats\r\n");
			writer.write("Exit\r\n\r\n");
			writer.write("OK");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path + "training._kh";
	}

}

package com.conference.management.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.conference.management.configuration.Configuration;
import com.conference.management.vo.Talk;



public class FileSourceManager {
	 public List<Talk> fetchTalks(String fileName,String path) throws FileNotFoundException{
		  
		 FileInputStream fstream = null;
	        List<Talk> talkList = new ArrayList<>(); 

	        try {
	            fstream = new FileInputStream(path+fileName);
	        } catch (FileNotFoundException e) {
	            System.err.println("Input file specified not found : " + Configuration.TALKS_INPUT_FILE + ". Make sure the file exists");
	            throw e;
	        }
	        BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

	        String strLine;
	        int intMinutes;

	        // Read Input File Line By Line
	        try {
	            while ((strLine = br.readLine()) != null) {
	                // handle comments or empty lines.
	               

	                String title = strLine.substring(0, strLine.lastIndexOf(" "));
	                String minutesString = strLine.substring(strLine.lastIndexOf(" ") + 1);
	                // get only the integers as string from the line.
	                String minutes = strLine.replaceAll("\\D+", "");
	                if (Configuration.LIGHTNING_TALK.equals(minutesString)) {
	                    intMinutes = Configuration.LIGHTNING_TALK_DURATION_MINUTES;
	                } else {
	                    try {
	                        intMinutes = Integer.parseInt(minutes);
	                    } catch (NumberFormatException e) {
	                        System.err.println("Could not parse the line : " + strLine);
	                        throw e;
	                    }
	                }
	                Talk singleTalk = new Talk(title, intMinutes);
	                talkList.add(singleTalk);

	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                fstream.close();
	                br.close();
	            } catch (IOException e){
	                System.err.println(e.getMessage());
	            }
	        }
	        return talkList;
	    }

	    public List<Talk> fetchTalks() throws FileNotFoundException{
	        return fetchTalks(Configuration.TALKS_INPUT_FILE,Configuration.PATH);
	    }
}

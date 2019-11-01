package com.conference.management;

 

import org.junit.Assert;
import org.junit.Test;

import com.conference.management.io.FileSourceManager;
import com.conference.management.test.configuration.TestConfiguration;
import com.conference.management.vo.Talk;

import java.io.FileNotFoundException;
import java.util.List;
 
public class ConferenceFileSourceManagerTest {

    FileSourceManager manager = new FileSourceManager();

    @Test(expected = FileNotFoundException.class)
    public void testFetchTalksFileNotFound() throws FileNotFoundException {
        manager.fetchTalks("conference-random-file.txt",TestConfiguration.PATH);
    }


    @Test
    public void testFetchTalksValidFile() throws FileNotFoundException {
        List<Talk> talks = manager.fetchTalks("conference-two-talks.txt",TestConfiguration.PATH);
        Assert.assertEquals(2, talks.size());
    }


  


    @Test(expected = NumberFormatException.class)
    public void testFetchTalksInvalidFile() throws FileNotFoundException {
        List<Talk> talks = manager.fetchTalks("conference-invalid-talks.txt",TestConfiguration.PATH);
    }

}
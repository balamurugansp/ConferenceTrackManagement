package com.conference.management;

import java.util.List;

import com.conference.management.configuration.ConferenceManager;
import com.conference.management.enums.DataOutputEnum;
import com.conference.management.enums.DataSourceEnum;
import com.conference.management.exception.UnsupportedDestinationException;
import com.conference.management.exception.UnsupportedSourceException;
import com.conference.management.util.ConferenceUtil;
import com.conference.management.vo.Conference;
import com.conference.management.vo.Talk;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {

		ConferenceManager conferenceManager = new ConferenceManager();
		List<Talk> talkList = null;
 
		try {
			talkList = conferenceManager.fetchTalksListFromSource(DataSourceEnum.FILE);
		} catch (UnsupportedSourceException e) {
			System.err.println(e.getMessage());
		}

		if (talkList == null || talkList.size() == 0)
			return;

		// Print talks.
		ConferenceUtil.printTalks(talkList);

		// Process and schedule talks into events and slots.
		Conference conference = conferenceManager.processAndScheduleTalks(talkList);

		// Output the conference events.
		try {
			conferenceManager.outputConferenceSchedule(conference, DataOutputEnum.CONSOLE);
		} catch (UnsupportedDestinationException e) {
			System.err.println(e.getMessage());
		}

	}

}

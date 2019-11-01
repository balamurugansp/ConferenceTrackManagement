package com.conference.management.io;

import java.text.SimpleDateFormat;
import java.util.List;

import com.conference.management.configuration.Configuration;
import com.conference.management.vo.Conference;
import com.conference.management.vo.Event;
import com.conference.management.vo.Slot;
import com.conference.management.vo.Track;



public class ConsoleOutputManager {
	public void printSchedule(Conference conference) {

		SimpleDateFormat sdf = Configuration.DATE_FORMAT;
		System.out.println("Output: Conference Schedule :");
		System.out.println("--------------------------------------------------------");
		for (Track track : conference.getTracks()) {
			System.out.println("Track " + track.getTrackId());
			List<Slot> slots = track.getSlots();
			System.out.println("");

			// Output the talks into tracks based on the totalTalks and the
			// count of Talks.
			for (Slot slot : slots) {

				for (Event event : slot.getEvents()) {
					// Print the prepared talk's title for this Track
					System.out.println(sdf.format(event.getStartTime().getTime()) + " " + event.getTitle() );
				}
			}
			System.out.println("--------------------------------------------------------");
		}
	}

}

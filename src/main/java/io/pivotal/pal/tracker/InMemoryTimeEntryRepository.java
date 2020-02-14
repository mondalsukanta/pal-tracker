package io.pivotal.pal.tracker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private long nextId=1;
    private HashMap<Long, TimeEntry> inMemoryDataStore = new HashMap<Long, TimeEntry>();


    public TimeEntry create(TimeEntry timeEntry) {
        TimeEntry timeEntryWithId = new TimeEntry(
                nextId++,
                timeEntry.getProjectId(),
                timeEntry.getUserId(),
                timeEntry.getDate(),
                timeEntry.getHours()
        );
        inMemoryDataStore.put(timeEntryWithId.getId(), timeEntryWithId);
        return timeEntryWithId;
    }

    public TimeEntry find(long id) {
        return inMemoryDataStore.get(id);
    }

    public List<TimeEntry> list() {
        List<TimeEntry> timeEntries = new ArrayList<>(inMemoryDataStore.values());
        return timeEntries;
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        if (inMemoryDataStore.containsKey(id)) {
            TimeEntry timeEntryWithId = new TimeEntry(
                    id,
                    timeEntry.getProjectId(),
                    timeEntry.getUserId(),
                    timeEntry.getDate(),
                    timeEntry.getHours()
            );
            inMemoryDataStore.put(id, timeEntryWithId);
        }
        return inMemoryDataStore.get(id);
    }

    public void delete(long id) {
        inMemoryDataStore.remove(id);
    }
}
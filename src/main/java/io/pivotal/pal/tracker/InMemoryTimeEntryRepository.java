package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    HashMap<Long, TimeEntry> database = new HashMap<>();

    public TimeEntry create(TimeEntry timeEntry) {
        long nextId = (long)database.size() + 1L;
        TimeEntry timeEntryWithId = new TimeEntry(nextId, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        database.put(nextId, timeEntryWithId);
        return timeEntryWithId;
    }

    public TimeEntry find(long id) {
        return database.get(id);
    }

    public List<TimeEntry> list() {
        return database.values().stream().collect(Collectors.toList());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry timeEntryWithId = new TimeEntry(id, timeEntry.getProjectId(), timeEntry.getUserId(), timeEntry.getDate(), timeEntry.getHours());
        database.replace(id, timeEntryWithId);
        return timeEntryWithId;
    }

    public void delete(long id) {
        database.remove(id);
    }
}

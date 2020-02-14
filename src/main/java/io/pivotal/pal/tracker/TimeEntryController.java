package io.pivotal.pal.tracker;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/time-entries")
public class TimeEntryController {
    private TimeEntryRepository timeEntryRepository;

    public TimeEntryController(TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping
    public ResponseEntity<TimeEntry> create(@RequestBody TimeEntry timeEntryToCreate) {
        TimeEntry body = timeEntryRepository.create(timeEntryToCreate);
        return new ResponseEntity(body, CREATED);
    }

    @GetMapping("/{timeEntryId}")
    public ResponseEntity<TimeEntry> read(@PathVariable Long timeEntryId) {
        TimeEntry timeEntry = this.timeEntryRepository.find(timeEntryId);
        return (timeEntry == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(timeEntry);
    }

    @GetMapping
    public ResponseEntity<List<TimeEntry>> list() {
        return ResponseEntity.ok(this.timeEntryRepository.list());
    }

    @PutMapping("/{timeEntryId}")
    public ResponseEntity update(@PathVariable Long timeEntryId, @RequestBody TimeEntry timeEntry) {
        TimeEntry timeEntryResponse = this.timeEntryRepository.update(timeEntryId, timeEntry);
        return (timeEntryResponse == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok().body(timeEntryResponse);
    }

    @DeleteMapping("/{timeEntryId}")
    public ResponseEntity delete(@PathVariable long timeEntryId) {
        this.timeEntryRepository.delete(timeEntryId);
        return ResponseEntity.noContent().build();
    }
}
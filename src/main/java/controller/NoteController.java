package controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.Note;
import service.NoteService;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    // Cоздаём заметку и передаём серверу данные от юзера из класса Note
    //http status
    @PostMapping
    public ResponseEntity<?> createNote(@RequestBody Note note) {
        noteService.create(note);
        return ResponseEntity.status(HttpStatus.CREATED).body(note);
    }

    @GetMapping
    public List<Note> allNotes() {
        return noteService.findAll();
    }

    //http status
    @GetMapping("/{id}")
    public ResponseEntity<?> singleNote(@PathVariable Long id) {
        Note note = noteService.notes.get(id);
        if (note == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok(note);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updateNote) {
        return noteService.update(id, updateNote);
    }

    //http status
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNOte(@PathVariable Long id) {
        if (noteService.delete(id)) return ResponseEntity.noContent().build();
        else return ResponseEntity.notFound().build();
    }

    @GetMapping("/search")
    public List<Note> search(@RequestParam String keyword) {
        return noteService.search(keyword);
    }

    @PostMapping("/{id}/remind")
    public String remind(@PathVariable Long id, @RequestParam(defaultValue = "30") Integer minutes) {
        Note note = noteService.notes.get(id);
        return " Напоминание о заметке " + note.getTitle() + " через " + minutes + " минут ";
    }

    @GetMapping("/export/json")
    public List<Note> exportJson() {
        return noteService.findAll();
    }

    @GetMapping("/export/text")
    public String exportText () {
        return noteService.findAll()
                .stream()
                .map(note -> note.getTitle() + " " + note.getContent())
                .collect(Collectors.joining("/n"));
    }

    @PostMapping("/batch")
    public List<Note> batchIn(@RequestBody List<Note> notesToAdd) {
        for (Note n : notesToAdd) {
            noteService.create(n);
        }
        return notesToAdd;
    }
}









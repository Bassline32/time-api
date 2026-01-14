package controller;


import org.springframework.web.bind.annotation.*;
import service.Note;
import service.NoteService;

import java.io.IOException;
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
    @PostMapping
    public Note createNote(@RequestBody Note note) {
        noteService.create(note);
        return note;
    }

    @GetMapping
    public List<Note> allNotes() {
        return noteService.findAll();
    }

    @GetMapping("/{id}")
    public Note singleNote(@PathVariable Long id) {
        return noteService.notes.getOrDefault(id, null);
    }

    @PutMapping("/{id}")
    public Note updateNote(@PathVariable Long id, @RequestBody Note updateNote) {
        return noteService.update(id, updateNote);
    }

    @DeleteMapping("/{id}")
    public void deleteNOte(@PathVariable Long id) {
        noteService.delete(id);
    }

    @GetMapping("/search")
    public List<Note> search(@RequestParam String keyword) {
        return noteService.search(keyword);
    }


    @PostMapping("/{id}/remind")
    public String remind(@PathVariable Long id, @RequestParam(defaultValue = "30") Integer minutes) {
        Note note = noteService.notes.get(id);
        return " Напоминание о заметке " + note.getTitle() + " через " + minutes + " минут ";

        //Object тип возвращаемого значения метода в JSON или в text

    }
}










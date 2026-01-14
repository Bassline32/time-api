package controller;


import org.springframework.web.bind.annotation.*;
import service.Note;
import service.NoteService;

import java.util.List;

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
}







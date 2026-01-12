package controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import service.NoteService;

@RestController
@RequestMapping("/api/v1/notes")
public class NoteController {

    private final NoteService noteService;


    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }
}

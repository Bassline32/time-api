package service;

import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;


@Service
public class NoteService {
    //мапа ключ-значение
    public Map<Long, Note> notes = new HashMap<>();

    //возвращаем список заметок
    public List<Note> findAll() {
        return new ArrayList<>(notes.values());
    }

    //создаём заметку метод create
    public void create(Note note) {
        //в переменной будем хранить номер новой заметке
        // условие ? значение если истина : значение если ложь
        long nextId = notes.size() > 0 ? Collections.max(notes.keySet()) + 1 : 1;
        //устаноаливаем новой заметке  тот самый номер,
        // который получили на предыдущей строке
        note.setId(nextId);
        notes.put(note.getId(), note);
    }

    //Обновляем заметку
    public Note update(Long id, Note updateNote) {
        if (!notes.containsKey(id)) throw new RuntimeException();
        notes.replace(id, updateNote);
        return notes.get(id);
    }

    //удаляем заметку
    public boolean delete(Long id) {
        return notes.remove(id) != null;
    }

    //поиск заметки
    public List<Note> search(String keyWord) {
        return notes.values().stream()
                .filter(note -> note.getTitle().contains(keyWord) || note.getContent().contains(keyWord))
                .collect(Collectors.toList());
    }
}

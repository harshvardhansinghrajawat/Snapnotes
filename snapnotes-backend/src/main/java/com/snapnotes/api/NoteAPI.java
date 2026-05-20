package com.snapnotes.api;

import com.snapnotes.dto.NoteDTO;
import com.snapnotes.exception.SnapnotesNoteException;
import com.snapnotes.service.NoteService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(value = "/snapnotes")
public class NoteAPI {
    @Autowired
    private NoteService noteService;

    @Autowired
    private Environment environment;

    private static final Log LOGGER = LogFactory.getLog(NoteAPI.class);

    @GetMapping(value = "/notes")
    public ResponseEntity<List<NoteDTO>> getAllNotes() throws SnapnotesNoteException{
        List<NoteDTO> noteDTOS = noteService.getAllNotes();
        return  new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }

    @GetMapping(value = "/notes/{id}")
    public ResponseEntity<NoteDTO> getNoteById(@PathVariable Long id) throws SnapnotesNoteException{
        NoteDTO noteDTO = noteService.getNoteById(id);
        return new ResponseEntity<>(noteDTO, HttpStatus.OK);
    }

    @GetMapping(value = "/notes/category/{category}")
    public ResponseEntity<List<NoteDTO>> getNotesByCategory(@PathVariable String category) throws SnapnotesNoteException {
        List<NoteDTO> noteDTOS = noteService.getNotesByCategory(category);
        return new ResponseEntity<>(noteDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/notes")
    public ResponseEntity<String> addNote(@RequestBody NoteDTO noteDTO)throws SnapnotesNoteException{
        Long id = noteService.addNote(noteDTO);
        String successMessage = "New note added successfully, with id: "+id;
        return new ResponseEntity<>(successMessage, HttpStatus.CREATED);
    }

    @PutMapping(value = "/notes/{id}")
    public ResponseEntity<String> updateNote(@RequestBody NoteDTO noteDTO, @PathVariable Long id) throws SnapnotesNoteException{
        noteService.updateNote(noteDTO, id);
        String successMessage = "Note updated successfully.";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }

    @DeleteMapping(value = "/notes/{id}")
    public ResponseEntity<String> deleteNote(@PathVariable Long id) throws SnapnotesNoteException{
        noteService.deleteNote(id);
        String successMessage = "Note delete successfully.";
        return new ResponseEntity<>(successMessage, HttpStatus.OK);
    }
}

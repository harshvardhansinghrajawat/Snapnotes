package com.snapnotes.service;

import com.snapnotes.dto.NoteDTO;
import com.snapnotes.entity.Note;
import com.snapnotes.exception.SnapnotesNoteException;
import com.snapnotes.repository.NoteRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service(value = "noteService")
@Transactional
public class NoteServiceImpl implements NoteService{
    @Autowired
    public NoteRepository noteRepository;

    ModelMapper modelMapper = new ModelMapper();

    @Override
    public List<NoteDTO> getAllNotes() throws SnapnotesNoteException {
        List<Note> notesList = noteRepository.findAll();
        if(notesList.isEmpty()){
            throw new SnapnotesNoteException("Sorry No notes are available");
        }
        return notesList.stream().map(note -> modelMapper.map(note, NoteDTO.class)).toList();
    }

    @Override
    public NoteDTO getNoteById(Long id) throws SnapnotesNoteException {
        Note note = noteRepository.findById(id).orElseThrow(() -> new SnapnotesNoteException("Sorry No notes are available"));
        return modelMapper.map(note, NoteDTO.class);
    }

    @Override
    public List<NoteDTO> getNotesByCategory(String category) throws SnapnotesNoteException {
        List<Note> notesList = noteRepository.findByCategory(category);
        if(notesList.isEmpty()){
            throw new SnapnotesNoteException("Sorry No notes are available.");
        }
        return notesList.stream().map(note -> modelMapper.map(note, NoteDTO.class)).toList();
    }

    @Override
    public Long addNote(NoteDTO noteDTO) throws SnapnotesNoteException {
        Note newNote = new Note();
        newNote.setId(noteDTO.getId());
        newNote.setText(noteDTO.getText());
        newNote.setCategory(noteDTO.getCategory());
        newNote.setCreatedAt(LocalDateTime.now());

        noteRepository.save(newNote);

        return newNote.getId();
    }

    @Override
    public void updateNote(NoteDTO noteDTO, Long id) throws SnapnotesNoteException {
        Note note = noteRepository.findById(id).orElseThrow(()->new SnapnotesNoteException("Sorry note is not available."));
        if (noteDTO.getText() != null){
            note.setText(noteDTO.getText());
        }
        if (noteDTO.getCategory() != null){
            note.setCategory(noteDTO.getCategory());
        }
        noteRepository.save(note);
    }

    @Override
    public void deleteNote(Long id) throws SnapnotesNoteException {
        Note note = noteRepository.findById(id).orElseThrow(()->new SnapnotesNoteException("Sorry note is not available."));
        noteRepository.delete(note);
    }
}

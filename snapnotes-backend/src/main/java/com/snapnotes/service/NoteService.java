package com.snapnotes.service;

import com.snapnotes.dto.NoteDTO;
import com.snapnotes.entity.Note;
import com.snapnotes.exception.SnapnotesNoteException;

import java.util.List;

public interface NoteService {
    List<NoteDTO> getAllNotes() throws SnapnotesNoteException;
    NoteDTO getNoteById(Long id) throws SnapnotesNoteException;
    List<NoteDTO> getNotesByCategory(String category) throws SnapnotesNoteException;
    Long addNote(NoteDTO noteDTO) throws SnapnotesNoteException;
    void updateNote(NoteDTO noteDTO,Long id) throws SnapnotesNoteException;
    void deleteNote(Long id) throws SnapnotesNoteException;
}

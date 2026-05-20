import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Note } from '../models/note';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class NoteService {
  private baseUrl = "http://localhost:8080/snapnotes/notes"

  constructor(private http: HttpClient){}

  getAllNotes(): Observable<Note[]>{
    return this.http.get<Note[]>(this.baseUrl)
  }

  getNoteById(id: number): Observable<Note>{
    return this.http.get<Note>(`${this.baseUrl}/${id}`)
  }
  
  getNotesByCategory(category: string): Observable<Note[]>{
    return this.http.get<Note[]>(`${this.baseUrl}/category/${category}`)
  }

  addNote(noteDto: Note):Observable<Note>{
    return this.http.post<Note>(this.baseUrl, noteDto)
  }

  updateNote(id: number, noteDto: Note): Observable<void>{
    return this.http.put<void>(`${this.baseUrl}/${id}`,noteDto)
  }

  deleteNote(id: number): Observable<void>{
    return this.http.delete<void>(`${this.baseUrl}/${id}`)
  }
}

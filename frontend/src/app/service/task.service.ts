import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTaskRequest, Task ,UpdateTaskRequest} from '../models/task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskService {


  private readonly apiUrl = 'http://localhost:8081/api/tasks';

  constructor(private http: HttpClient) { }



  getTasks(): Observable<Task[]> {

    return this.http.get<Task[]>(this.apiUrl);
  }


  createTask(request: CreateTaskRequest): Observable<Task> {
    return this.http.post<Task>(this.apiUrl, request)
  }

  deleteTask(id: number): Observable<void> {

    return this.http.delete<void>(`${this.apiUrl}/${id}`)

  }

  updateTask(id:number,request:UpdateTaskRequest):Observable<Task>{
    return this.http.put<Task>(`${this.apiUrl}/${id}`,request)
  }


}

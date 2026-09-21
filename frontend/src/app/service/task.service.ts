import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CreateTaskRequest, Task ,TaskPriority,TaskStatus,UpdateTaskRequest} from '../models/task.model';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TaskService {


  private readonly apiUrl = 'http://localhost:8081/api/tasks';

  constructor(private http: HttpClient) { }



  getTasks(
    status?: TaskStatus,
    priority?: TaskPriority,
    search?: string,
    sortBy?: string,
    direction?: string
  ): Observable<Task[]> {


  let params = new HttpParams();

  if (status) {
    params = params.set('status', status);
    }

  if (priority) {
    params = params.set('priority', priority);
    }

  if (search && search.trim()) {
    params = params.set('search', search.trim());
    }

    if (sortBy) {
      params = params.set('sortBy', sortBy);
    }
    
    if (direction) {
      params = params.set('direction',direction)
    }

  return this.http.get<Task[]>(this.apiUrl, { params });

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

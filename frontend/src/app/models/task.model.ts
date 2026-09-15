export type TaskStatus = 
  'TODO' |
  'IN_PROGRESS' |
  'DONE';  


export type TaskPriority =
  'LOW' |
  'MEDIUM' |
  'HIGH';


export interface Task {

  id: number;
  title: string;
  description: string;
  status: TaskStatus;
  priority: TaskPriority;
  dueDate: string;
  createdAt: string;
  updatedAt: string | null;

}


export interface CreateTaskRequest{
  title: string;
  description: string;
  priority: TaskPriority;
  dueDate: string;
}

export interface UpdateTaskRequest {
  title:string;
  description:string;
  status:TaskStatus
  priorirty:TaskPriority;
  dueDate:string;
}
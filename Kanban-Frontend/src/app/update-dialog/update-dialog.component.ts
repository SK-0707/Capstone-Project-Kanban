import { Component, Inject, Input, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { MatCalendarCellClassFunction } from '@angular/material/datepicker';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import Swal from 'sweetalert2';
import { Task } from '../model/task';
import { KanbanService } from '../service/kanban.service';
import { TaskService } from '../service/task.service';
import { TaskDialogComponent } from '../task-dialog/task-dialog.component';

@Component({
  selector: 'app-update-dialog',
  templateUrl: './update-dialog.component.html',
  styleUrls: ['./update-dialog.component.css']
})
export class UpdateDialogComponent implements OnInit {

  @Input() min: any;
  minDate:Date = new Date();
  
  dialogTitle: String;
 

  deatils:Task ={
    taskId: 0,
    taskName: "",
    description: "",
    taskStatus: "",
    assignTo: "",
    date: ""
  }

 

  form!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private dialogRef: MatDialogRef<TaskDialogComponent>,
    private route: ActivatedRoute,
    @Inject(MAT_DIALOG_DATA) data:any,
    
    private kanbanService: KanbanService,

    private taskService: TaskService) {
      console.log(data); 
     this.dialogTitle = data.title;
      this.createTaskForm();
      this.form.setValue(data);
      this.minDate.setDate(this.minDate.getDate() );
   }

   createTaskForm() :void{
    this.form=new FormGroup({
      "taskId":new FormControl (0),
      "taskName":new FormControl(this.deatils.taskName,[Validators.required]),
       "description":new FormControl(this.deatils.description,[Validators.required]),
       "taskStatus":new FormControl(this.deatils.taskStatus,[Validators.required]),
       "assignTo":new FormControl(this.deatils.assignTo,[Validators.required]),
       "date":new FormControl(this.deatils.date,[Validators.required])
    });
  }

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    // Only highligh dates inside the month view.
    if (view === 'month') {
      const date = cellDate.getDate();

      // Highlight the 1st and 20th day of each month.
      return date === 1 || date === 20 ? 'example-custom-date-class' : '';
    }

    return '';
  };



  ngOnInit() {
  }

    id!:any;
  update(){
     
     this.id=sessionStorage.getItem('id');
     console.log(this.id);
    this.mapFormToTaskModel();
    
    const Observable= this.kanbanService.updateTaskToKanban(this.id,this.form.value);
    Observable.subscribe((data:{}) =>{
    });
    
   
  this.dialogRef.close();
  Swal.fire({   
    icon: 'success',  
    title: 'you Updated task successfuly',  
    showConfirmButton: false,  
    timer: 3000  
  });
   window.location.reload();
  
}





  close() {
      this.dialogRef.close();
  } 

  private mapFormToTaskModel(): void {

    this.deatils.taskName = this.form.value;
    this.deatils.description= this.form.value;
    this.deatils.taskStatus = this.form.value;
    this.deatils.assignTo=this.form.value;

  }

}

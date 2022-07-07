import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {Router } from '@angular/router';
import Swal from 'sweetalert2';
import { AuthService } from '../service/auth.service';
import { KanbanService } from '../service/kanban.service';
@Component({
  selector: 'app-login-form',
  templateUrl: './login-form.component.html',
  styleUrls: ['./login-form.component.css']
})
export class LoginFormComponent implements OnInit {

  constructor(private router:Router,private kanbanService:KanbanService, private authService:AuthService) {}
  ngOnInit(): void {}
  loginForm=new FormGroup({
    email:new FormControl('',[Validators.required]),
    password:new FormControl('',[Validators.required,Validators.minLength(8)])
    })
    get email() {
      return this.loginForm.get('email') as FormControl;
      
    }
    
    get password(){
      return this.loginForm.get('password') as FormControl;
    }
    emailId!:any;
    emailId1!:any;

  //authentication and navigate to login
  onSubmit(){

    this.emailId=this.loginForm.controls;
    this.emailId1=this.emailId.email.value
    console.log(this.emailId1);
    
    sessionStorage.setItem('email',this.emailId1);
    
    this.kanbanService.authenticate(this.loginForm.value).subscribe(result=>{
      console.log(result)

      sessionStorage.setItem('token',result.jwtToken);
      
      const Toast = Swal.mixin({
        toast: true,
        position: 'top-end',
        showConfirmButton: false,
        timer: 1700,
        timerProgressBar: true,
        didOpen: (toast) => {
          toast.addEventListener('mouseenter', Swal.stopTimer)
          toast.addEventListener('mouseleave', Swal.resumeTimer)
        }
      })
      
      Toast.fire({
        icon: 'success',
        title: 'Signed in successfully'
      })  
       this.authService.login();
      this.router.navigate(['/kanban'])
    },
    
    err=>{alert("Email & Password Mismatching")
      
    }
    )
}
  
  //navigate to register page
  register(){
    this.router.navigate(['/register']);
  }
}
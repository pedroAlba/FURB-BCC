import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Http } from '@angular/http';
import { environment } from '../../environments/environment';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  form: FormGroup;

  constructor(
    private fb: FormBuilder,
    private http: Http,
    private router: Router
  ) { }

  ngOnInit() {
    this.form = this.fb.group({
      name: ['', Validators.required],
      username: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
      address: ['', Validators.required],
      phone: ['', Validators.required]
    });
  }
  register() {
    this.http.post(`${environment.baseUrl}/api/users`, this.form.value).subscribe(res => { 
      this.form.reset();
      this.router.navigateByUrl('/signin');
    })
  }
}

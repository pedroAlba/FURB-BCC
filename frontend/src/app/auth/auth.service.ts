import { Injectable, EventEmitter } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from "rxjs/BehaviorSubject";
import { Observable } from 'rxjs/Rx';
import { Http, Response } from '@angular/http';
import { environment } from '../../environments/environment';
import { User } from './user.interface';


@Injectable()
export class AuthService {

  public showNavBarEmitter: EventEmitter<boolean> = new EventEmitter<boolean>();

  private authenticated = false;

  constructor(private router: Router,
              private http: Http) { }

  signIn(user: User) {
    this.http.post(`${environment.baseUrl}/api/login`, user).subscribe(res => {
      if (eval(res.text())) {
        this.authenticated = true;
        this.showNavBar(true);
        this.router.navigate(['/']);
      } else {
        this.authenticated = false;
      }
    })
  }

  logout() {
    this.authenticated = false;
    this.showNavBar(false);
    this.router.navigate(['/signin']);
  }

  isAuthenticated() {
    return this.authenticated;
  }

  private showNavBar(ifShow: boolean) {
    this.showNavBarEmitter.emit(ifShow);
  }
}

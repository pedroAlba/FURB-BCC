import { Component, OnInit, OnDestroy, AfterViewInit } from '@angular/core';
import { NavbarService } from './navbar.service';
import { AuthenticationService, UserService } from '../_services';
import { Router } from '@angular/router';
import { User } from '../_models';

@Component({
  moduleId: module.id,
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html'
})

export class NavbarComponent implements AfterViewInit, OnDestroy{

  private currentUser: User;

  constructor(public nav: NavbarService,
    private auth: AuthenticationService,
    private router: Router,
    private user: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngAfterViewInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnDestroy(){
    console.log('destroy');
  }
}

import { Component, OnInit, OnDestroy, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { NavbarService } from './navbar.service';
import { AuthenticationService, UserService } from '../_services';
import { Router } from '@angular/router';
import { User } from '../_models';

@Component({
  moduleId: module.id,
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css']
})

export class NavbarComponent implements AfterViewInit, OnDestroy{

  private currentUser: string;
  private isAdmin = false;

  constructor(public nav: NavbarService,
    private auth: AuthenticationService,
    private router: Router,
    private changeDetector: ChangeDetectorRef,
    private user: UserService) {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    this.isAdmin = this.currentUser && this.currentUser === 'admin';
  }

  ngAfterViewInit() {
    this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
  }

  ngOnDestroy() {
    console.log('destroy');
  }

  ngAfterViewChecked(){
    this.changeDetector.detectChanges();
  }

}

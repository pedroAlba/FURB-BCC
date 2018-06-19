import { Component, OnDestroy, AfterViewInit, ChangeDetectorRef } from '@angular/core';
import { NavbarService } from './navbar.service';
import { AuthenticationService, UserService } from '../_services';
import { Router } from '@angular/router';

@Component({
  moduleId: module.id,
  selector: 'app-navbar',
  templateUrl: 'navbar.component.html',
  styleUrls: ['navbar.component.css']
})

export class NavbarComponent implements AfterViewInit, OnDestroy{

  private currentUser: string;

  constructor(public nav: NavbarService,
    private auth: AuthenticationService,
    private router: Router,
    private changeDetector: ChangeDetectorRef,
    private user: UserService,
    ) {
    this.auth.getLoggedInName.subscribe(name => {
      this.currentUser = name;
    })
  }

  ngAfterViewInit() {
  }

  ngOnDestroy() {
  }

  ngAfterViewChecked(){
    this.changeDetector.detectChanges();
  }

  isAdmin(){
    return this.currentUser === 'admin';
  }

}

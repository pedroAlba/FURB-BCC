import { Component, OnInit, Inject, Injectable } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';

import { AuthenticationService, AlertService } from '../_services/index';
import { NavbarService } from '../navbar/navbar.service';

@Component({
    moduleId: module.id.toString(),
    templateUrl: 'login.component.html'
})

export class LoginComponent implements OnInit {
    model: any = {};
    loading = false;
    returnUrl: string;

    constructor(
        private route: ActivatedRoute,
        private router: Router,
        private authenticationService: AuthenticationService,
        private nav: NavbarService,
        private alertService: AlertService) { }

    ngOnInit() {

        this.nav.hide();

        // reset login status
        this.authenticationService.logout();

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    login() {
        this.loading = true;
        this.authenticationService.login(this.model.username, this.model.password)
            .subscribe(
                data => {
                    if (data) {
                        this.router.navigate([this.returnUrl]);
                    } else {
                        //TODO: Descobrir porque alertservice vem undefined
                        this.alertService.error('Usuário ou senha inválidos');
                        this.loading = false;
                    }

                },
                error => {
                    this.alertService.error(error);
                    this.loading = false;
                });
    }
}

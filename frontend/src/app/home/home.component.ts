import { Component, OnInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService, AuthenticationService } from '../_services/index';
import { MatCardContent } from '@angular/material';
import { NavbarService } from '../navbar/navbar.service';
import { VehicleDTO } from '../_models/vehicle';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';

@Component({
    moduleId: module.id.toString(),
    styleUrls: ['home.component.css'],
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    currentUser: string;
    
    vehicles: VehicleDTO[];

    constructor(private authService: AuthenticationService,
                private nav: NavbarService,
                private http: HttpClient) {
        this.currentUser = authService.getCurrentUser();
    }

    ngOnInit() {
        this.nav.show();
        this.http.get<VehicleDTO[]>(`${environment.baseURL}/api/vehicles`).subscribe(response => {
            this.vehicles = response;
        })
    }  
}

import { Component, OnInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService, AuthenticationService } from '../_services/index';
import { MatCardContent, MatIconRegistry, MatDialog, MatSnackBar } from '@angular/material';
import { NavbarService } from '../navbar/navbar.service';
import { VehicleDTO } from '../_models/vehicle';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { DomSanitizer } from '@angular/platform-browser';
import { RentService } from '../_services/rent.service';
import { VehicleService } from '../_services/vehicle.service';
import { RentDialogComponent } from '../dialogs/rent/rent-dialog.component';
import { RentDTO } from '../_models/rent';

@Component({
    moduleId: module.id.toString(),
    styleUrls: ['home.component.css'],
    templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {
    currentUser: string;
    
    rent: RentDTO = new RentDTO();

    vehicles: VehicleDTO[];

    constructor(private authService: AuthenticationService,
                private nav: NavbarService,
                private http: HttpClient,
                private auth: AuthenticationService,
                iconRegistry: MatIconRegistry,
                sanitizer: DomSanitizer,
                public dialog: MatDialog,
                private rentService: RentService,
                private vehicleService: VehicleService,
                private snackBar: MatSnackBar) {
        this.currentUser = authService.getCurrentUser();
    }

    ngOnInit() {
        this.nav.show();
        this.http.get<VehicleDTO[]>(`${environment.baseURL}/api/vehicles`).subscribe(response => {
            this.vehicles = response;
        })
    }  
    doRent(vehicle){

            const dialogRef = this.dialog.open(RentDialogComponent, {
              width: '300px',
              height: '270px',
              data: { date: this.rent.date }
            });
        
            this.rentService.getOccupiedDays(vehicle.id + '').subscribe(r => {
              dialogRef.componentInstance.disabledDays = r;
            });
        
            dialogRef.afterClosed().subscribe(r => {
              if (r) {
                const rent = new RentDTO();
                rent.date = r.toLocaleDateString();
                rent.userName = this.auth.getCurrentUser();
                rent.vehicleId = vehicle.id + '';
                this.rentService.create(rent).subscribe(res => {
                  this.snackBar.open('Veículo reservado com sucesso', '', {
                    duration: 2000,
                  });
                });
              }
            });
    }
}

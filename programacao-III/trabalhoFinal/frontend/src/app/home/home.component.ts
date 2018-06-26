import { Component, OnInit } from '@angular/core';

import { AuthenticationService } from '../_services/index';
import { MatDialog, MatSnackBar } from '@angular/material';
import { NavbarService } from '../navbar/navbar.service';
import { VehicleDTO } from '../_models/vehicle';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../environments/environment';
import { RentService } from '../_services/rent.service';
import { RentDialogComponent } from '../dialogs/rent/rent-dialog.component';
import { RentDTO } from '../_models/rentDTO';

@Component({
  moduleId: module.id.toString(),
  styleUrls: ['home.component.css'],
  templateUrl: 'home.component.html'
})

export class HomeComponent implements OnInit {

  rent: RentDTO = new RentDTO();

  vehicles: VehicleDTO[];

  constructor(
    private nav: NavbarService,
    private http: HttpClient,
    private auth: AuthenticationService,
    public dialog: MatDialog,
    private rentService: RentService,
    private snackBar: MatSnackBar) {
  }

  ngOnInit() {
    this.nav.show();
    this.http.get<VehicleDTO[]>(`${environment.baseURL}/api/vehicles`).subscribe(response => {
      this.vehicles = response;
    })
  }
  doRent(vehicle) {

    const dialogRef = this.dialog.open(RentDialogComponent, {
      width: '300px',
      height: '270px',
      data: { date: this.rent.date }
    });

    this.rentService.getOccupiedDays(vehicle.id + '').subscribe(r => {
      dialogRef.componentInstance.disabledDays = r;
    });

    dialogRef.componentInstance.buttonAction = 'Reservar';

    dialogRef.afterClosed().subscribe(r => {
      if (r) {
        const rent = new RentDTO();
        rent.date = r.toLocaleDateString();
        rent.userName = this.auth.getCurrentUser();
        rent.vehicleId = vehicle.id + '';
        this.rentService.create(rent).subscribe(() => {
          this.snackBar.open('Veículo reservado com sucesso', '', {
            duration: 2000,
          });
        });
      }
    });
  }
}

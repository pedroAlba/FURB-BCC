import { NavbarService } from '../navbar/navbar.service';
import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { merge } from 'rxjs/observable/merge';
import { of as observableOf } from 'rxjs/observable/of';
import { catchError } from 'rxjs/operators/catchError';
import { map } from 'rxjs/operators/map';
import { startWith } from 'rxjs/operators/startWith';
import { switchMap } from 'rxjs/operators/switchMap';
import { VehicleDTO } from '../_models/vehicle';
import { DomSanitizer } from '@angular/platform-browser';
import { RentDTO } from '../_models/rentDTO';
import { RentService } from '../_services/rent.service';
import { VehicleService } from '../_services/vehicle.service';
import { RentDialogComponent } from '../dialogs/rent/rent-dialog.component';
import { MatTableDataSource, MatPaginator, MatSort, MatIconRegistry, MatDialog, MatSnackBar } from '@angular/material';
import { AuthenticationService } from '../_services/authentication.service';

@Component({
  selector: 'app-rents',
  templateUrl: './rents.component.html',
  styleUrls: ['./rents.component.css']
})
export class RentsComponent implements OnInit {

  displayedColumns = ['vehicleId', 'date', 'actions'];

  data: VehicleDTO[] = [];

  rent: RentDTO = new RentDTO();

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(
    private nav: NavbarService,
    private auth: AuthenticationService,
    iconRegistry: MatIconRegistry,
    sanitizer: DomSanitizer,
    public dialog: MatDialog,
    private rentService: RentService,
    private vehicleService: VehicleService,
    private snackBar: MatSnackBar) {
    iconRegistry.addSvgIcon(
      'rent',
      sanitizer.bypassSecurityTrustResourceUrl('assets/car.svg'));
  }

  ngOnInit() {

    this.nav.show();

    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.rentService.getUserRents(this.auth.getCurrentUser());     
        }),
        map(data => {
          this.isLoadingResults = false;
          this.isRateLimitReached = false;
          this.resultsLength = data.length;
          return data;
        }),
        catchError(() => {
          this.isLoadingResults = false;
          this.isRateLimitReached = true;
          return observableOf([]);
        })
      ).subscribe(data => this.dataSource.data = data);
  }

  delete(row){
    console.log(row);
    this.rentService.delete(row.id).subscribe(() => {
      this.snackBar.open('Reserva deletada com sucesso!', '', {
        duration: 2000,
      });
      this.refreshTable();
    });
  }

  refreshTable() {
    this.paginator._changePageSize(this.paginator.pageSize);
  }
}

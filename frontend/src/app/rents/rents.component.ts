import { NavbarService } from '../navbar/navbar.service';
import { Component, OnInit, ViewChild, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { MatPaginator, MatSort, MatTableDataSource, MatDialogRef, MAT_DIALOG_DATA, MatIconRegistry, MatDialog } from '@angular/material';
import { Observable } from 'rxjs/Observable';
import { merge } from 'rxjs/observable/merge';
import { of as observableOf } from 'rxjs/observable/of';
import { catchError } from 'rxjs/operators/catchError';
import { map } from 'rxjs/operators/map';
import { startWith } from 'rxjs/operators/startWith';
import { switchMap } from 'rxjs/operators/switchMap';
import { environment } from '../../environments/environment';
import { VehicleDTO } from '../_models/vehicle';
import { DomSanitizer } from '@angular/platform-browser';
import { RentDTO } from '../_models/rent';
import { FormControl } from '@angular/forms';
import { RentService } from '../_services/rent.service';
import { AuthenticationService } from '../_services';

@Component({
  selector: 'app-rents',
  templateUrl: './rents.component.html',
  styleUrls: ['./rents.component.css']
})
export class RentsComponent implements OnInit {

  displayedColumns = ['location', 'model', 'rentalValue', 'category', 'actions'];

  exampleDatabase: ExampleHttpDao | null;
  data: GithubApi[] = [];

  rent: RentDTO = new RentDTO();

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;

  constructor(private nav: NavbarService,
              private http: HttpClient,
              iconRegistry: MatIconRegistry,
              sanitizer: DomSanitizer,
              public dialog: MatDialog,
              private rentService: RentService,
              private authService: AuthenticationService) {
    iconRegistry.addSvgIcon(
      'rent',
      sanitizer.bypassSecurityTrustResourceUrl('assets/car.svg'));
  }

  ngOnInit() {
    this.nav.show();

    this.exampleDatabase = new ExampleHttpDao(this.http);

    // If the user changes the sort order, reset back to the first page.
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.exampleDatabase.getVehicles(
            this.sort.active, this.sort.direction, this.paginator.pageIndex);
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

  doRent(row) {
    console.log(row);
    //TODO: Pesquisar no backend quais são os dias que estão reservados para esse veiculo
    const dialogRef = this.dialog.open(RentDialog, {
      width: '400px',
      height: '370px',
      data: { date: this.rent.date }
    });

    dialogRef.afterClosed().subscribe(r => {
      if(r){
        console.log(r);
        let rent = new RentDTO();
        rent.date = r.toLocaleDateString();
        rent.userName = JSON.parse(localStorage.getItem('currentUser'));
        rent.vehicleId = row.id + '';
        console.log("rent before post");
        console.log(rent);
        this.rentService.create(rent).subscribe(res =>{
          console.log(res);
        });
      }
    });
  }
}

export class ExampleHttpDao {
  constructor(private http: HttpClient) { }

  getVehicles(sort: string, order: string, page: number): Observable<VehicleDTO[]> {
    return this.http.get<VehicleDTO[]>(`${environment.baseURL}/api/vehicles`);
  }
}

export interface GithubApi {
  items: VehicleDTO[];
}

@Component({
  selector: 'app-dialog-overview-example-dialog',
  templateUrl: 'rent-dialog.html',
  styleUrls: ['rent-dialog.component.css']
})
export class RentDialog {

  constructor(
    public dialogRef: MatDialogRef<RentDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}

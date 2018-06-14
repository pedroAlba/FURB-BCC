import { Component, OnInit, Inject, ViewChild, ChangeDetectorRef } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar, MatTableDataSource, MatPaginator, MatSort } from '@angular/material';
import { VehicleDTO } from '../_models/vehicle';
import { VehicleService } from '../_services/vehicle.service';
import { merge } from 'rxjs/observable/merge';
import { startWith } from 'rxjs/operators/startWith';
import { switchMap } from 'rxjs/operators/switchMap';
import { map } from 'rxjs/operators/map';
import { catchError } from 'rxjs/operators/catchError';
import { Observable } from 'rxjs/Observable';
import { FormControl } from '@angular/forms';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  vehicle: VehicleDTO;
  displayedColumns = ['id', 'location', 'doors', 'model', 'year', 'category', 'rentalValue', 'actions'];

  exampleDatabase: VehicleService | null;
  data: VehicleDTO[] = [];

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private vehicleService: VehicleService) {
    this.vehicle = new VehicleDTO();
}

  ngOnInit() {
    this.sort.sortChange.subscribe(() => this.paginator.pageIndex = 0);

    merge(this.sort.sortChange, this.paginator.page)
      .pipe(
        startWith({}),
        switchMap(() => {
          this.isLoadingResults = true;
          return this.vehicleService.getVehicles(
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
          return Observable.of([]);
        })
      ).subscribe(data => this.dataSource.data = data);
  }

  edit(row) {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '500px',
      height: '400px',
      data: { location: row.location + '',
              doors: row.doors + '',
              model: row.model + '',
              year: row.year + '',
              category: row.category + '',
              rentalValue: row.rentalValue + '',
              characteristics: row.characteristics + '',
              imageURL: row.imageURL + '',}
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.vehicleService.updateVehicle(row.id, result).subscribe(response => {
          this.snackBar.open('Veículo atualizado com sucesso!', '', {
            duration: 2000,
          });
          // Recarrega e lista o novo veiculo
          this.refreshTable();
          this.vehicle = new VehicleDTO();
        }, err => {
          console.log(err);
        });
      }
    });
  }

  delete(row) {
    this.vehicleService.deleteVehicle(row.id).subscribe(() => {
      this.snackBar.open('Veículo deletado com sucesso!', '', {
        duration: 2000,
      });
      this.refreshTable();
    });
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '500px',
      height: '400px',
      data: { location: this.vehicle.location,
              doors: this.vehicle.doors,
              model: this.vehicle.model,
              year: this.vehicle.year,
              category: this.vehicle.category,
              rentalValue: this.vehicle.rentalValue,
              characteristics: this.vehicle.characteristics,
              imageURL: this.vehicle.imageURL,
            }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.vehicle = result;
        this.vehicleService.create(this.vehicle).subscribe(response => {
          this.snackBar.open('Veículo cadastrado com sucesso!', '', {
            duration: 2000,
          });
          // Recarrega e lista o novo veiculo
          this.refreshTable();
          this.vehicle = new VehicleDTO();
        });
      }
    });
  }

  refreshTable() {
    this.paginator._changePageSize(this.paginator.pageSize);
  }
}


@Component({
  selector: 'app-dialog-overview-example-dialog',
  templateUrl: 'add-vehicle-dialog.html',
  styleUrls: ['./add-vehicle-dialog.component.css']
})
export class DialogOverviewExampleDialog {

  stateCtrl: FormControl;
  filteredStates: Observable<any[]>;

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) {}

  onNoClick(): void {
    this.dialogRef.close();
  }
}

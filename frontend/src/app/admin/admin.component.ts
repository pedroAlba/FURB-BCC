import { Component, OnInit, ViewChild } from '@angular/core';
import { MatDialog,  MatSnackBar, MatTableDataSource, MatPaginator, MatSort, MatDialogConfig } from '@angular/material';
import { VehicleDTO } from '../_models/vehicle';
import { VehicleService } from '../_services/vehicle.service';
import { merge } from 'rxjs/observable/merge';
import { startWith } from 'rxjs/operators/startWith';
import { switchMap } from 'rxjs/operators/switchMap';
import { map } from 'rxjs/operators/map';
import { catchError } from 'rxjs/operators/catchError';
import { Observable } from 'rxjs/Observable';
import { BrandService } from '../_services/brand.service';
import { Brand } from '../_models/brand';
import { VehicleDialogComponent } from '../dialogs/vehicle/vehicle-dialog.component';
import { RentService } from '../_services/rent.service';
import { RentEditDialogComponent } from '../dialogs/rent/edit/rent-edit-dialog.component';
import { RentDialogComponent } from '../dialogs/rent/rent-dialog.component';
import { RentDTO } from '../_models/rentDTO';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  displayedVehicleColumns = ['id', 'location', 'doors', 'model', 'year', 'category', 'rentalValue', 'actions'];
  vehicleDataSource = new MatTableDataSource();
  vehicleResultsLength = 0;
  @ViewChild(MatPaginator) vehiclePaginator: MatPaginator;
  @ViewChild(MatSort) vehicleSort: MatSort;

  displayedRentColumns = ['id', 'username', 'vehicle', 'date', 'actions'];
  rentsDataSource = new MatTableDataSource();
  rentsResultsLength = 0;
  @ViewChild(MatPaginator) rentsPaginator: MatPaginator;
  @ViewChild(MatSort) rentsSort: MatSort;

  imageURL;
  brandName;

  constructor(public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private vehicleService: VehicleService,
    private brandService: BrandService,
    private rentsService: RentService) {
  }

  ngOnInit() {
    this.initiateVehicleDatatable();
    this.initiateRentsDatatable();
  }

  saveVehicle(): void {

    const dialogConfig = this.buildDefaultConfigDialog();

    dialogConfig.data = {}

    const dialogRef = this.dialog.open(VehicleDialogComponent, dialogConfig);

    dialogRef.componentInstance.description = 'Cadastro'
    dialogRef.componentInstance.status = 'Salvar';

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.vehicleService.create(result).subscribe(() => {
          this.snackBar.open('Veículo cadastrado com sucesso!', '', {
            duration: 2000,
          });
          this.refreshVehicleTable();
        });
      }
    });
  }

  editVehicle(row) {

    const dialogConfig = this.buildDefaultConfigDialog();

    dialogConfig.data = {
      location: row.location + '',
      doors: row.doors + '',
      model: row.model + '',
      year: row.year + '',
      category: row.category + '',
      rentalValue: row.rentalValue + '',
      characteristics: row.characteristics + '',
      imageURL: row.imageURL + '',
      brand: row.brand,
    }

    const dialogRef = this.dialog.open(VehicleDialogComponent, dialogConfig);

    dialogRef.componentInstance.description = 'Atualização';
    dialogRef.componentInstance.status = 'Atualizar';

    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        this.vehicleService.updateVehicle(row.id, result).subscribe(() => {
          this.snackBar.open('Veículo atualizado com sucesso!', '', {
            duration: 2000,
          });
          this.refreshVehicleTable();
        })
      };
    });
  }

  deleteVehicle(row) {
    this.vehicleService.deleteVehicle(row.id).subscribe(() => {
      this.snackBar.open('Veículo deletado com sucesso!', '', {
        duration: 2000,
      });
      this.refreshVehicleTable();
    });
  }

  editRent(row){
    const dialogRef = this.dialog.open(RentDialogComponent, {
      width: '300px',
      height: '270px',
    });

    this.rentsService.getOccupiedDays(row.vehicle.id + '').subscribe(r => {
      dialogRef.componentInstance.disabledDays = r;
    });

    dialogRef.componentInstance.buttonAction = 'Atualizar';

    dialogRef.afterClosed().subscribe(r => {
      console.log(r);
      const rent = new RentDTO();
      rent.date = r.toLocaleDateString();
      rent.userName = row.user.username;
      rent.vehicleId = row.vehicle.id;
      this.rentsService.update(row.id, rent).subscribe(() => {
        this.snackBar.open('Reserva alterada com sucesso', '', {
          duration: 2000,
        });
        this.refreshRentsTable();
      });
    });
  }

  deleteRent(row){
    this.rentsService.delete(row.id).subscribe(() => {
      this.snackBar.open('Reserva deletada com sucesso', '', {
        duration: 2000,
      });
      this.refreshRentsTable();
    })
  }

  saveBrand() {
    const b = new Brand();
    b.imageURL = this.imageURL;
    b.name = this.brandName;
    this.brandService.create(b).subscribe(() => {
      this.snackBar.open('Marca cadastrada com sucesso', '', {
        duration: 2000,
      });
      this.brandName = '';
      this.imageURL = '';
    });
  }

  private buildDefaultConfigDialog(): MatDialogConfig {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;
    dialogConfig.width = '500px';
    dialogConfig.height = '500px';
    return dialogConfig;
  }

  refreshVehicleTable() {
    this.vehiclePaginator._changePageSize(this.vehiclePaginator.pageSize);
  }
  refreshRentsTable(): any {
    this.rentsPaginator._changePageSize(this.rentsPaginator.pageSize);
  }

  private initiateVehicleDatatable() {
    this.vehicleSort.sortChange.subscribe(() => this.vehiclePaginator.pageIndex = 0);
    merge(this.vehicleSort.sortChange, this.vehiclePaginator.page)
      .pipe(startWith({}), switchMap(() => {
        return this.vehicleService.getVehicles();
      }), map(data => {
        this.vehicleResultsLength = data.length;
        return data;
      }), catchError(() => {
        return Observable.of([]);
      })).subscribe(data => this.vehicleDataSource.data = data);
  }

  private initiateRentsDatatable() {
    this.rentsSort.sortChange.subscribe(() => this.rentsPaginator.pageIndex = 0);
    merge(this.rentsSort.sortChange, this.rentsPaginator.page)
      .pipe(startWith({}), switchMap(() => {
        return this.rentsService.get();
      }), map(data => {
        this.rentsResultsLength = data.length;
        return data;
      }), catchError(() => {
        return Observable.of([]);
      })).subscribe(data => this.rentsDataSource.data = data);
  }
}

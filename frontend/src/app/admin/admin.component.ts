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

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  displayedColumns = ['id', 'location', 'doors', 'model', 'year', 'category', 'rentalValue', 'actions'];

  exampleDatabase: VehicleService | null;
  data: VehicleDTO[] = [];

  imageURL;
  brandName;

  resultsLength = 0;
  isLoadingResults = true;
  isRateLimitReached = false;

  dataSource = new MatTableDataSource();

  @ViewChild(MatPaginator) paginator: MatPaginator;
  @ViewChild(MatSort) sort: MatSort;


  constructor(public dialog: MatDialog,
    private snackBar: MatSnackBar,
    private vehicleService: VehicleService,
    private brandService: BrandService) {
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

  save(): void {

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
          this.refreshTable();
        });
      }
    });
  }

  edit(row) {

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
          this.refreshTable();
        })
      };
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

  saveBrand() {
    const b = new Brand();
    b.imageURL = this.imageURL;
    b.name = this.brandName;
    this.brandService.create(b).subscribe(r => {
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

  refreshTable() {
    this.paginator._changePageSize(this.paginator.pageSize);
  }
}

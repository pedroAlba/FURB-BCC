import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatSnackBar } from '@angular/material';
import { VehicleDTO } from '../_models/vehicle';
import { VehicleService } from '../_services/vehicle.service';

@Component({
  selector: 'app-admin',
  templateUrl: './admin.component.html',
  styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

  vehicle: VehicleDTO;

  ngOnInit() {
  }

  constructor(public dialog: MatDialog,
              private snackBar: MatSnackBar,
              private vehicleService: VehicleService) {
    this.vehicle = new VehicleDTO();
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(DialogOverviewExampleDialog, {
      width: '500px',
      height: '370px',
      data: { location: this.vehicle.location,
              doors: this.vehicle.doors,
              model: this.vehicle.model,
              year: this.vehicle.year,
              category: this.vehicle.category,
              rentalValue: this.vehicle.rentalValue,
              characteristics: this.vehicle.characteristics}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      console.log(result);
      if (result) {
        this.vehicle = result;
        this.vehicleService.create(this.vehicle).subscribe(response => {
          this.snackBar.open('Veículo cadastrado com sucesso!', '', {
            duration: 2000,
          });
        });
      }
    });
  }
}

@Component({
  selector: 'app-dialog-overview-example-dialog',
  templateUrl: 'add-vehicle-dialog.html',
  styleUrls: ['./add-vehicle-dialog.component.css']
})
export class DialogOverviewExampleDialog {

  constructor(
    public dialogRef: MatDialogRef<DialogOverviewExampleDialog>,
    @Inject(MAT_DIALOG_DATA) public data: any) { }

  onNoClick(): void {
    this.dialogRef.close();
  }
}


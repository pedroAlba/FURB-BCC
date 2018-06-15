import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';

@Component({
    selector: 'app-dialog-overview-example-dialog',
    templateUrl: 'rent-dialog.component.html',
    styleUrls: ['rent-dialog.component.css']
})
export class RentDialogComponent {

    disabledDays: String[];
    minDate;

    constructor(
        public dialogRef: MatDialogRef<RentDialogComponent>,
        @Inject(MAT_DIALOG_DATA) public data: any,
    ) {
        this.minDate = new Date();
    }

    onNoClick(): void {
        this.dialogRef.close();
    }

    myFilter = (d: Date): boolean => {
        return !this.disabledDays.some(dayLoop => dayLoop === d.toLocaleDateString());
    }
}

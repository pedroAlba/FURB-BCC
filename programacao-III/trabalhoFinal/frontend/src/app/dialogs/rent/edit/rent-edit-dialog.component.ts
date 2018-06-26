import { Component, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { Brand } from '../../../_models/brand';
import { Validators, FormBuilder, FormGroup } from '@angular/forms';
import { RentDTO } from '../../../_models/rentDTO';
import { Rent } from '../../../_models/rent';

@Component({
    selector: 'app-dialog-overview-example-dialog',
    templateUrl: 'rent-edit-dialog.component.html',
    styleUrls: ['rent-edit-dialog.component.css']
})
export class RentEditDialogComponent {

    form: FormGroup;
    description: string;
    brands: Brand[];
    status;

    constructor(
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<RentEditDialogComponent>,
        @Inject(MAT_DIALOG_DATA) {
            user,
            vehicle,
            date
        }: Rent,
    ) {

        this.minDate = new Date();
        this.form = fb.group({
            user: [user, Validators.required],
            vehicle: [vehicle, Validators.required],
            datel: [date, Validators.required],
        });
    }

    ngOnInit() {
    }

    save() {
        console.log(this.form.value);
        this.dialogRef.close(this.form.value);
    }

    close() {
        this.dialogRef.close();
    }

    displayName(brand?: Brand): String | undefined {
        return brand ? brand.name : undefined;
    }

    disabledDays: String[];
    minDate;

    onNoClick(): void {
        this.dialogRef.close();
    }

    myFilter = (d: Date): boolean => {
        return !this.disabledDays.some(dayLoop => dayLoop === d.toLocaleDateString());
    }
}

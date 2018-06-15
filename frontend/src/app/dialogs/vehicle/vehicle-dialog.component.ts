import { Component, Inject, OnInit } from "@angular/core";
import { MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { VehicleDTO } from "../../_models/vehicle";
import { FormGroup, FormBuilder, Validators } from "@angular/forms";
import { Brand } from "../../_models/brand";
import { BrandService } from "../../_services/brand.service";

@Component({
    templateUrl: 'vehicle-dialog.component.html',
    styleUrls: ['vehicle-dialog.component.css']
  })
export class VehicleDialogComponent implements OnInit{
  
    form: FormGroup;
    description: string;
    brands: Brand[];
    status;

    constructor(
        private brandService: BrandService,
        private fb: FormBuilder,
        private dialogRef: MatDialogRef<VehicleDialogComponent>,
        @Inject(MAT_DIALOG_DATA) { 
            location,
            doors,
            model,
            year,
            category,
            rentalValue,
            characteristics,
            imageURL,
            brand,
         }: VehicleDTO,
        ) {        

        this.form = fb.group({
            location: [location, Validators.required],
            doors: [doors, Validators.required],
            model: [model, Validators.required],
            year: [year, Validators.required],
            category: [category, Validators.required],
            rentalValue: [rentalValue, Validators.required],
            characteristics: [characteristics, Validators.required],
            imageURL: [imageURL, Validators.required],
            brand: [brand, Validators.required],
        });
    }

    ngOnInit() {
        this.brandService.findAll().subscribe(res => {
            this.brands = res;
        })
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
  }
  
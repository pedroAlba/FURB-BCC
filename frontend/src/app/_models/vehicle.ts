import { Brand } from "./brand";

export class VehicleDTO {
    id: string;
    location: string;
    doors: string;
    model: string;
    year: string;
    category: string;
    rentalValue: string;
    characteristics: string;
    imageURL: string;
    brand: Brand;
}

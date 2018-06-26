import { VehicleDTO } from "./vehicle";
import { User } from "./user";

export class Rent{
    id: string;
    vehicle: VehicleDTO;
    user: User;
    date: string;
}
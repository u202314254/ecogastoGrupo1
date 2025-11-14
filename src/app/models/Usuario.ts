import { Hogar } from './Hogar';

export class Usuario{
    id:number=0
    hogar: Hogar = {idHogar:0,
    distrito:"",
    ubicacion:"",
    tipohogar:"",
    numpersonas:0}
    username:string=""
    correo:string=""
    password:string=""
    estado:boolean=false
}
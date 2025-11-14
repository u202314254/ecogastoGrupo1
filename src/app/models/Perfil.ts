import { Usuario } from "./Usuario";

export class Perfil{
    idPerfil:number=0
    usuario: Usuario = {id:0,
    hogar: {idHogar:0,
        distrito:"",
        ubicacion:"",
        tipohogar:"",
        numpersonas:0},
        username:"",
        correo:"",
        password:"",
        estado:false,}
    nombre:string=""
    edad:number=0
    genero:string=""
    telefono:string=""
}
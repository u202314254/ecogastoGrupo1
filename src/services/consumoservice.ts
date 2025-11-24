import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { Consumo } from '../models/Consumo';
import { Subject } from 'rxjs';

const base_url=environment.base
@Injectable({
    providedIn: 'root',
})
export class Consumoservice {
    private url=`${base_url}/consumos`;
    private listaCambio = new Subject<Consumo[]>();

    constructor(private http:HttpClient) {}

    list(){
    return this.http.get<Consumo[]>(this.url)
    }

    
  insert(c: Consumo) {
    return this.http.post(this.url, c);
  }

  setList(listaNueva: Consumo[]) {
    this.listaCambio.next(listaNueva);
  }
  getList() {
    return this.listaCambio.asObservable();
  }

  listId(id: number) {
    return this.http.get<Consumo>(`${this.url}/${id}`);
  }

  update(c: Consumo) {
    return this.http.put(this.url, c, { responseType: 'text' });
  }


  delete(id:number){ return this.http.delete(`${this.url}/${id}`,{responseType:'text'})

  }

  
}
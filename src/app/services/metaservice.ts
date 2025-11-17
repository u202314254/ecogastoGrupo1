import { Injectable } from '@angular/core';
import { environment } from '../../environments/environments';
import { HttpClient } from '@angular/common/http';
import { Meta } from '../models/Meta';
import { Observable, Subject } from 'rxjs';
const base_url = environment.base;
@Injectable({
  providedIn: 'root',
})
export class Metaservice {
  private url = `${base_url}/metas`;
  private listaCambio = new Subject<Meta[]>();

  constructor(private http: HttpClient) {
  }

  list() {
    return this.http.get<Meta[]>(this.url);
  }

  insert(m: Meta) {
    return this.http.post(this.url, m);
  }

  setList(listaNueva: Meta[]) {
    this.listaCambio.next(listaNueva);
  }
  getList() {
    return this.listaCambio.asObservable();
  }

  listId(id: number) {
    return this.http.get<Meta>(`${this.url}/${id}`);
  }

  update(m: Meta) {
    return this.http.put(`${this.url}`, m, { responseType: 'text' });
  }

  delete(id: number) {
    return this.http.delete(`${this.url}/${id}`, { responseType: 'text' });
  }
}

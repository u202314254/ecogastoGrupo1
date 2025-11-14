import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs';
import { environment } from '../../environments/environments';
import { Meta } from '../models/Meta';

const base_url = environment.base;

@Injectable({
  providedIn: 'root',
})
export class Metaservice {
  private url = `${base_url}/metas`;
  private listaCambio = new Subject<Meta[]>();

  constructor(private http: HttpClient) {}

  list(): Observable<Meta[]> {
    return this.http.get<Meta[]>(this.url);
  }

  insert(m: any): Observable<string> {
    return this.http.post<string>(this.url, m, {
      responseType: 'text' as 'json',
    });
  }

  listId(id: number): Observable<Meta> {
    return this.http.get<Meta>(`${this.url}/${id}`);
  }

  update(m: any): Observable<string> {
    return this.http.put<string>(this.url, m, {
      responseType: 'text' as 'json',
    });
  }

  delete(id: number): Observable<string> {
    return this.http.delete<string>(`${this.url}/${id}`, {
      responseType: 'text' as 'json',
    });
  }

  setList(listaNueva: Meta[]): void {
    this.listaCambio.next(listaNueva);
  }

  getList(): Observable<Meta[]> {
    return this.listaCambio.asObservable();
  }
}

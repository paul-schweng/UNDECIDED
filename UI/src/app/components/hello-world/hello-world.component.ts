import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.scss']
})
export class HelloWorldComponent implements OnInit {

  title = 'Demo';
  greeting: any = {};
  constructor(private http: HttpClient) {
    http.get('/api/resource').subscribe(data => this.greeting = data);
  }

  ngOnInit(): void {
  }

}

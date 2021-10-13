import { Component, OnInit } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {TranslateService} from "@ngx-translate/core";

@Component({
  selector: 'hello-world',
  templateUrl: './hello-world.component.html',
  styleUrls: ['./hello-world.component.scss']
})
export class HelloWorldComponent implements OnInit {

  isBusy: boolean = true;

  title = 'Demo';
  greeting: any = {};


  constructor(private http: HttpClient,
              public translate: TranslateService) {

    http.get('/api/resource').toPromise()
      .then(data => this.greeting = data)
      .finally(() => this.isBusy = false);

  }


  ngOnInit(): void {
    this.translate.addLangs(['de','en']);
    this.translate.setDefaultLang('en');

    if(this.translate.getLangs().includes(this.translate.getBrowserLang()))
      this.translate.use(this.translate.getBrowserLang());
    else
      this.translate.use(this.translate.getDefaultLang());
  }


  switchLang(lang: string) {
    this.translate.use(lang);
  }

}

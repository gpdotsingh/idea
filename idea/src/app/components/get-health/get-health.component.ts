import { Component, OnInit } from '@angular/core';
import { Health } from 'src/app/model/health';
import { HealthService } from 'src/app/services/health.service';

@Component({
  selector: 'app-get-health',
  templateUrl: './get-health.component.html',
  styleUrls: ['./get-health.component.css']
})
export class GetHealthComponent implements OnInit {

  constructor(private service: HealthService) { }
  status!: Health;
  ngOnInit(): void {
    this.getHealthFromServices();
  }

  getHealthFromServices() {
    this.service
      .getHealth()
      .subscribe((response: any) => {
        console.log(response);
        this.status = response;
      },
        (error: any) => {
          console.error(error);
        })
  }
}

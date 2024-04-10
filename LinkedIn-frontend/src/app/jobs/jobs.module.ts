import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { JobsRoutingModule } from './jobs-routing.module';
import { JobsComponent } from './jobs.component';
import { JobmodalsComponent } from './jobmodals/jobmodals.component';
import { FormsModule } from '@angular/forms';


@NgModule({
  declarations: [
    JobsComponent,
    JobmodalsComponent
  ],
  imports: [
    CommonModule,
    JobsRoutingModule,
    FormsModule
  ]
})
export class JobsModule { }

import { Component } from '@angular/core';
import { ICellRendererAngularComp } from 'ag-grid-angular';
import { ICellRendererParams } from 'ag-grid-community';

@Component({
  standalone: true,
  selector: 'app-edit-button-renderer',
  templateUrl: './edit-button-renderer.component.html',
  styleUrls: ['./edit-button-renderer.component.css']
})
export class EditButtonRendererComponent implements ICellRendererAngularComp {
  private params: any;

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  refresh(params: ICellRendererParams): boolean {
    return false;
  }

  onEditClick(): void {
    if (this.params && this.params.onClick) {
      this.params.onClick(this.params.data);
    }
  }
}

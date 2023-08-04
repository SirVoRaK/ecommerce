import { Component, Input } from '@angular/core';
import { Order } from '../models/Order';

@Component({
  selector: 'app-order-entry',
  templateUrl: './order-entry.component.html',
  styleUrls: ['./order-entry.component.scss'],
})
export class OrderEntryComponent {
  @Input() order!: Order;
}

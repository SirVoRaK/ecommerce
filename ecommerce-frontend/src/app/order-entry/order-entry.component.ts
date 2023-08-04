import { Component, Input } from '@angular/core';
import { Order } from '../models/Order';
import { Money } from '../utils/Money';

@Component({
  selector: 'app-order-entry',
  templateUrl: './order-entry.component.html',
  styleUrls: ['./order-entry.component.scss'],
})
export class OrderEntryComponent {
  @Input() order!: Order;

  get total(): string {
    return Money.format(this.order.total);
  }
}

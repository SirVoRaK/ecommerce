import { Component, Input } from '@angular/core';
import { Item } from '../models/Item';
import { Money } from '../utils/Money';

@Component({
  selector: 'app-item-entry',
  templateUrl: './item-entry.component.html',
  styleUrls: ['./item-entry.component.scss'],
})
export class ItemEntryComponent {
  @Input() item!: Item;

  get total(): string {
    return Money.format(this.item.cost * this.item.qty);
  }
  get cost(): string {
    return Money.format(this.item.cost);
  }
}

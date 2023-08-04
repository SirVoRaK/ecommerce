import { Component, Input } from '@angular/core';
import { Item } from '../models/Item';

@Component({
  selector: 'app-items-list',
  templateUrl: './items-list.component.html',
  styleUrls: ['./items-list.component.scss'],
})
export class ItemsListComponent {
  @Input() items: Item[] = [];
}

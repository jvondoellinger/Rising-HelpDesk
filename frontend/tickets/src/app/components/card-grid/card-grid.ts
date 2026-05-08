import { Component } from '@angular/core';
import {Card} from '../card/card';

@Component({
  selector: 'app-card-grid',
  standalone: true,
  imports: [Card],
  templateUrl: './card-grid.html',
})
export class CardGrid {}

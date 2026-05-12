import { Component } from '@angular/core';
import {CardGrid} from '../card-grid/card-grid';
import {RouterOutlet} from '@angular/router';
import { Navbar } from '../../components/navbar/navbar';

@Component({
  selector: 'app-home',
  imports: [CardGrid, RouterOutlet, Navbar],
  standalone: true,
  templateUrl: './home.html'
})
export class Home {}

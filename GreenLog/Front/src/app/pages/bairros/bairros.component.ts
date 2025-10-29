import { Component, OnInit } from '@angular/core';
import { BairroService } from '../../services/bairro.service';
import { PontoColetaService } from '../../services/ponto-coleta.service';
import { RouterModule } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FooterComponent } from "../../components/footer/footer.component";


@Component({
  selector: 'app-bairros',
  imports: [CommonModule, RouterModule, FooterComponent],
  templateUrl: './bairros.component.html',
  styleUrls: ['./bairros.component.scss'],
})
export class BairrosComponent implements OnInit {
  bairros: any[] = [];
  pontos: any[] = [];

  constructor(
    private bairroService: BairroService,
    private pontoService: PontoColetaService
  ) {}

  ngOnInit(): void {
    this.bairroService.listarTodos().subscribe((res) => (this.bairros = res));
    this.pontoService.listarTodos().subscribe((res) => (this.pontos = res));
  }

  getPontosPorBairro(bairroId: number) {
    return this.pontos.filter(p => p.bairro?.id === bairroId);
  }
}

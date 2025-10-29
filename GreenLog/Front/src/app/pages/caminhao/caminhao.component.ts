import { Component, OnInit } from '@angular/core';
import { Caminhao, CaminhaoService } from '../../services/caminhao.service';
import { CommonModule } from '@angular/common';
import { CadastroCaminhaoComponent } from '../../components/cadastro-caminhao/cadastro-caminhao.component';
import { FooterComponent } from '../../components/footer/footer.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-caminhao',
  imports: [CommonModule, CadastroCaminhaoComponent, FooterComponent, RouterModule],
  templateUrl: './caminhao.component.html',
  styleUrls: ['./caminhao.component.scss']
})
export class CaminhaoComponent implements OnInit {
  modoCadastro = false;
  modoCadastroOuEdicao = false;
  caminhaoEditando: Caminhao | null = null;

  caminhoes: Caminhao[] = [];
  placasExistentes: string[] = [];

  constructor(private caminhaoService: CaminhaoService) {}

  getImagemResiduo(tipo: string): string {
    switch (tipo.toLowerCase()) {
      case 'organico':
        return 'assets/truck_organico.png';
      case 'plastico':
        return 'assets/truck_plastico.png';
      case 'metal':
        return 'assets/truck_metal.png';
      case 'papel':
        return 'assets/truck_papel.png';
      case 'vidro':
        return 'assets/truck_vidro.png';
      default:
        return 'assets/truck.png'; // imagem padrão
    }
  }

  ngOnInit() {
    this.carregarCaminhoes();
  }

  carregarCaminhoes() {
    this.caminhaoService.listarTodos().subscribe(dados => {
      this.caminhoes = dados;
      this.placasExistentes = dados.map(c => c.placa);
    });
  }

  adicionar() {
    this.caminhaoEditando = null;
    this.modoCadastro = true;
    this.modoCadastroOuEdicao = true;
  }

  editar(caminhao: Caminhao) {
    this.caminhaoEditando = caminhao;
    this.modoCadastro = true;
    this.modoCadastroOuEdicao = true;
  }

  salvar(caminhao: Caminhao) {
    if (!this.caminhaoEditando) {
      this.caminhaoService.criar(caminhao).subscribe(() => {
        this.carregarCaminhoes();
        this.modoCadastro = false;
        this.modoCadastroOuEdicao = false;
      });
    } else {
      this.caminhaoService.atualizar(caminhao).subscribe(() => {
        this.carregarCaminhoes();
        this.modoCadastro = false;
        this.modoCadastroOuEdicao = false;
      });
    }
  }

  excluir(placa: string) {
    if (confirm('Tem certeza que deseja excluir este caminhão?')) {
      this.caminhaoService.excluir(placa).subscribe(() => {
        this.carregarCaminhoes();
        this.modoCadastro = false;
        this.modoCadastroOuEdicao = false;
      });
    }
  }

  cancelar() {
    this.modoCadastro = false;
    this.modoCadastroOuEdicao = false;
  }
}

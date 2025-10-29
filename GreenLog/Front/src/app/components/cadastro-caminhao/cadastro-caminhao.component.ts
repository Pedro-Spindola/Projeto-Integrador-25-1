import { Component, EventEmitter, Input, Output } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MotoristaService } from '../../services/motorista.service';
import { TipoResiduoService } from '../../services/tipoResiduo.service';

interface Motorista {
  cpf: string;
  nome: string;
  dataNascimento: string;
}

interface Caminhao {
  placa: string;
  motorista: Motorista;
  tipoResiduo: string;
}

@Component({
  selector: 'app-cadastro-caminhao',
  imports: [CommonModule, FormsModule],
  templateUrl: './cadastro-caminhao.component.html',
  styleUrls: ['./cadastro-caminhao.component.scss']
})
export class CadastroCaminhaoComponent {
  @Input() caminhao: Caminhao | null = null;
  @Input() placasExistentes: string[] = [];
  @Input() motoristas: Motorista[] = []; // lista de motoristas para selecionar

  @Output() salvar = new EventEmitter<Caminhao>();
  @Output() cancelar = new EventEmitter<void>();
  @Output() excluir = new EventEmitter<string>();

  placa: string = '';
  motoristaSelecionadoCpf: string = ''; // seleciona pelo cpf
  residuos = ['Papel', 'Plastico', 'Metal', 'Vidro', 'Organico'];
  tipoResiduoSelecionado: string = '';

  constructor(
    private motoristaService: MotoristaService,
    private tipoResiduoService: TipoResiduoService
  ) {}
  
  ngOnInit() {
    this.motoristaService.listarTodos().subscribe(motoristas => {
      this.motoristas = motoristas;
    });
  
    this.tipoResiduoService.listarTipos().subscribe(residuos => {
      this.residuos = residuos;
    });
  
    // preenche edição
    if (this.caminhao) {
      this.placa = this.caminhao.placa;
      this.motoristaSelecionadoCpf = this.caminhao.motorista.cpf;
      this.tipoResiduoSelecionado = this.caminhao.tipoResiduo;
    }
  }
  

  get isFormValid(): boolean {
    return (
      this.placa.trim() !== '' &&
      this.motoristaSelecionadoCpf.trim() !== '' &&
      this.tipoResiduoSelecionado.trim() !== ''
    );
  }

  onSubmit() {
    if (!this.isFormValid) {
      alert('Por favor, preencha todos os campos antes de salvar.');
      return;
    }
    if (!this.caminhao && this.placasExistentes.includes(this.placa)) {
      alert('Já existe um caminhão com essa placa.');
      return;
    }

    const motoristaObj = this.motoristas.find(m => m.cpf === this.motoristaSelecionadoCpf);
    if (!motoristaObj) {
      alert('Motorista inválido!');
      return;
    }

    this.salvar.emit({
      placa: this.placa,
      motorista: motoristaObj,
      tipoResiduo: this.tipoResiduoSelecionado
    });
  }
}

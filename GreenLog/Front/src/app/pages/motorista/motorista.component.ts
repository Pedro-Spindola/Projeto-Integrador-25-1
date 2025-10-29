import { Component, OnInit } from '@angular/core';
import { Motorista, MotoristaService } from '../../services/motorista.service';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { FooterComponent } from "../../components/footer/footer.component";
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-motorista',
  imports: [RouterModule, FormsModule, FooterComponent, CommonModule],
  templateUrl: './motorista.component.html',
  styleUrls: ['./motorista.component.scss']
})

export class MotoristaComponent implements OnInit {
  motoristas: Motorista[] = [];
  modoCadastro = false;
  motoristaEditando: Motorista | null = null;

  cpf: string = '';
  nome: string = '';
  dataNascimento: string = '';

  constructor(private motoristaService: MotoristaService) {}

  ngOnInit() {
    this.carregarMotoristas();
  }

  carregarMotoristas() {
    this.motoristaService.listarTodos().subscribe(dados => {
      this.motoristas = dados;
    });
  }

  calcularIdade(dataNascimento: string): number {
    const nascimento = new Date(dataNascimento);
    const hoje = new Date();
    let idade = hoje.getFullYear() - nascimento.getFullYear();
    const mesDiff = hoje.getMonth() - nascimento.getMonth();
    if (mesDiff < 0 || (mesDiff === 0 && hoje.getDate() < nascimento.getDate())) {
      idade--;
    }
    return idade;
  }

  adicionar() {
    this.modoCadastro = true;
    this.motoristaEditando = null;
    this.cpf = '';
    this.nome = '';
    this.dataNascimento = '';
  }

  editar(motorista: Motorista) {
    this.modoCadastro = true;
    this.motoristaEditando = motorista;
    this.cpf = motorista.cpf;
    this.nome = motorista.nome;
    this.dataNascimento = motorista.dataNascimento;
  }

  cancelar() {
    this.modoCadastro = false;
  }

  excluir(cpf: string) {
    if (confirm('Tem certeza que deseja excluir este motorista?')) {
      this.motoristaService.excluir(cpf).subscribe(() => {
        this.carregarMotoristas();
        this.modoCadastro = false;
      });
    }
  }  

  onSubmit() {
    const motorista: Motorista = {
      cpf: this.cpf,
      nome: this.nome,
      dataNascimento: this.dataNascimento
    };

    if (this.motoristaEditando) {
      this.motoristaService.atualizar(this.cpf, motorista).subscribe(() => {
        this.modoCadastro = false;
        this.carregarMotoristas();
      });
    } else {
      this.motoristaService.adicionar(motorista).subscribe(() => {
        this.modoCadastro = false;
        this.carregarMotoristas();
      });
    }
  }
}
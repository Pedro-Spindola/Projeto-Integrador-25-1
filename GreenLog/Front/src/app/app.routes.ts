import { Routes } from '@angular/router';
import { LoginComponent } from './pages/login/login.component';
import { HomeComponent } from './pages/home/home.component';
import { CaminhaoComponent } from './pages/caminhao/caminhao.component';
import { MotoristaComponent } from './pages/motorista/motorista.component';
import { BairrosComponent } from './pages/bairros/bairros.component';

export const routes: Routes = [
    { path: '', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'caminhoes', component: CaminhaoComponent },
    { path: 'motoristas', component: MotoristaComponent },
    { path: 'bairros', component: BairrosComponent },
];

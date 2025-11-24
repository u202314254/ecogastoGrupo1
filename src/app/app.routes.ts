import { Routes } from '@angular/router';
import { Insigniainsertar } from './components/insignia/insigniainsertar/insigniainsertar';
import { Insignia } from './components/insignia/insignia';
import { Hogar } from './components/hogar/hogar';
import { Hogarinsetar } from './components/hogar/hogarinsertar/hogarinsertar';
import { Recomendacion } from './components/recomendacion/recomendacion';
import { Recomendacioninsertar } from './components/recomendacion/recomendacioninsertar/recomendacioninsertar';
import { Meta } from './components/metaa/meta';
import { Consumo } from './components/consumo/consumo';
import { Consumoinsertar } from './components/consumo/consumoinsertar/consumoinsertar';
import { Metainsertar } from './components/metaa/metainsertar/metainsertar';
import { Notificacion } from './components/notificacion/notificacion';
import { Notificacioninsertar } from './components/notificacion/notificacioninsertar/notificacioninsertar';
import { Perfil } from './components/perfil/perfil';
import { Perfilinsertar } from './components/perfil/perfilinsertar/perfilinsertar';
import { Usuario } from './components/usuario/usuario';
import { Recursoinsertar } from './components/recurso/recursoinsertar/recursoinsertar';
import { Recurso } from './components/recurso/recurso';
import { Usuarioinsertar } from './components/usuario/usuarioinsertar/usuarioinsertar';
import { Rol } from './components/rol/rol';
import { Rolinsertar } from './components/rol/rolinsertar/rolinsertar';
import { Seguridad } from './components/seguridad/seguridad';
import { Seguridadinsertar } from './components/seguridad/seguridadinsertar/seguridadinsertar';
import { Login } from './pages/login/login';
import { Home } from './pages/home/home';
import { Funcionalidades } from './pages/funcionalidades/funcionalidades';
import { Beneficios } from './pages/beneficios/beneficios';
import { Nosotros } from './pages/nosotros/nosotros';
import { Contacto } from './pages/contacto/contacto';
import { Menu } from './pages/menu/menu';
import { Registrar } from './pages/registrar/Registrar';
import { RecomendacionPorCategoria } from './components/recomendacion-por-categoria/recomendacion-por-categoria';
import { Notificacionporusuario } from './components/notificacionporusuario/notificacionporusuario';



export const routes: Routes = [
{ path: '', redirectTo: 'home', pathMatch: 'full' },

  // páginas públicas
  { path: 'home', component: Home },
  { path: 'funcionalidades', component: Funcionalidades },
  { path: 'beneficios', component: Beneficios },
  { path: 'nosotros', component: Nosotros },
  { path: 'contacto', component: Contacto },

  { path: 'login', component: Login },
  {path: 'registrar', component: Registrar},

  // dashboard
  { path: 'menu', component: Menu },

    {path:'insignias',component:Insignia,
        children:[
            {path:'Inuevo',component:Insigniainsertar},
            {path:'Iedits/:id',component:Insigniainsertar}
        ]
    },

    {path:'hogares',component:Hogar,
        children:[
            {path:'Hnuevo',component:Hogarinsetar},
            {path:'Hedits/:id',component:Hogarinsetar}
        ]
    },

    {path:'recomendaciones',component:Recomendacion,
        children:[
            {path:'Rnuevo',component:Recomendacioninsertar},
            {path:'Redits/:id',component:Recomendacioninsertar}
        ]
    },

    {path:'consumos',component:Consumo,
        children:[
            {path:'Cnuevo',component:Consumoinsertar},
            {path:'Cedits/:id',component:Consumoinsertar}
        ]
    },

    {path:'metas',component:Meta,
        children:[
            {path:'Mnuevo',component:Metainsertar},
            {path:'Medits/:id',component:Metainsertar}
        ]
    },
    
    {path:'notificaciones',component:Notificacion,
        children:[
            {path:'Nnuevo',component:Notificacioninsertar},
            {path:'Nedits/:id',component:Notificacioninsertar}
        ]
    },

    {path:'perfiles',component:Perfil,
        children:[
            {path:'Pnuevo',component:Perfilinsertar},
            {path:'Pedits/:id',component:Perfilinsertar}
        ]
    },
        {path:'recursos',component:Recurso,
        children:[
            {path:'Renuevo',component:Recursoinsertar},
            {path:'Reedits/:id',component:Recursoinsertar}
        ]
    },

    {path:'usuarios',component:Usuario,
        children:[
            {path:'Unuevo',component:Usuarioinsertar},
            {path:'Uedits/:id',component:Usuarioinsertar}
        ]
    },

    {path:'roles',component:Rol,
        children:[
            {path:'Ronuevo',component:Rolinsertar},
            {path:'Roedits/:id',component:Rolinsertar}
        ]
    },

    {path:'seguridades',component:Seguridad,
        children:[
            {path:'Snuevo',component:Seguridadinsertar},
            {path:'Sedits/:id',component:Seguridadinsertar}
        ]
    },

    {
        path:'buscar', component: RecomendacionPorCategoria,
    },

    {
        path:'reporte-usuarios', component: Notificacionporusuario
    }
];
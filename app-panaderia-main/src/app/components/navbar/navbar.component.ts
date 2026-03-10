import { Component } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { AutenticacionService } from '../../service/autenticacion.service';
import { CarritoService } from '../../service/carrito.service';
import { ProductoService } from '../../service/producto.service';

@Component({
  selector: 'app-navbar',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {

  isMenuOpen: boolean = false;
  carritoCount: number = 0;
    productos: any[] = [];

  constructor(
    public authServicio: AutenticacionService,
    private router: Router,
    private carritoService: CarritoService,
) {}

get rolUsuario(): string {
    const usuario = localStorage.getItem('usuario');
    if (usuario) {
      return JSON.parse(usuario).rol;
    }
    return '';
  }

  ngOnInit(): void {
this.carritoService.carrito$.subscribe(items => {
      this.carritoCount = items.reduce((total, item) => total + (item.cantidad || 1), 0);
    });
    // Suscribirse a los cambios en el carrito
    this.carritoService.carrito$.subscribe(items => {
      this.carritoCount = items.reduce((total, item) => total + (item.cantidad || 1), 0);
    });
  }

  toggleMenu(): void {
    this.isMenuOpen = !this.isMenuOpen;
  }

  logout(): void {
    this.authServicio.logout();
    this.router.navigateByUrl('/');
  }

  irAlCarrito(): void {
    this.router.navigate(['/carrito']);
  }


  
}
package com.example.miapp.config;

import com.example.miapp.model.Producto;
import com.example.miapp.model.User;
import com.example.miapp.repository.ProductoRepository;
import com.example.miapp.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadData(ProductoRepository productoRepository, UserRepository userRepository) {
        return args -> {

            // ====== Usuarios ======
            if (userRepository.count() == 0) {

                userRepository.save(new User(
                        null,
                        "Admin",
                        "admin@mail.com",
                        "1234",
                        "RM",
                        "Santiago",
                        User.Rol.ADMIN
                ));

                userRepository.save(new User(
                        null,
                        "Cliente 1",
                        "cliente1@mail.com",
                        "1234",
                        "RM",
                        "Puente Alto",
                        User.Rol.CLIENTE
                ));

                userRepository.save(new User(
                        null,
                        "Cliente 2",
                        "cliente2@mail.com",
                        "1234",
                        "Valparaiso",
                        "Viña",
                        User.Rol.CLIENTE
                ));

                System.out.println("Usuarios cargados correctamente");
            } else {
                System.out.println("Usuarios ya existen, no se insertaron nuevamente");
            }

            // ====== Productos ======
            if (productoRepository.count() == 0) {

                // ================= VINILOS =================
                productoRepository.save(new Producto(null,
                        "Los Prisioneros",
                        150000.0,
                        "La banda chilena que marcó a toda una generación, ahora en formato vinilo para coleccionistas.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Queen",
                        300000.0,
                        "El legado de Freddie Mercury y Queen en vinilo, un imprescindible del rock clásico.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Green Day",
                        70000.0,
                        "Los éxitos de Green Day en formato vinilo, perfectos para los fanáticos del punk rock.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Arctic Monkeys",
                        75000.0,
                        "El sonido alternativo de Arctic Monkeys en vinilo, ideal para coleccionistas modernos.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Pearl Jam - Ten",
                        65000.0,
                        "El icónico álbum 'Ten' de Pearl Jam en vinilo, un clásico del grunge de los 90.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Doja Cat - Planet Her",
                        300000.0,
                        "El aclamado 'Planet Her' de Doja Cat en vinilo, con un estilo pop moderno y fresco.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Beethoven",
                        300000.0,
                        "Obras maestras de Beethoven en vinilo, ideal para los amantes de la música clásica.",
                        Producto.Categoria.VINILO
                ));

                productoRepository.save(new Producto(null,
                        "Luis Miguel",
                        80000.0,
                        "El clásico de Luis Miguel en vinilo, ideal para los nostálgicos del pop latino.",
                        Producto.Categoria.VINILO
                ));

                // ================= CDS =================
                productoRepository.save(new Producto(null,
                        "Kendrick Lamar",
                        70000.0,
                        "Las rimas únicas de Kendrick Lamar en CD, considerado uno de los mejores raperos de su generación.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Dua Lipa - Radical",
                        75000.0,
                        "El sonido pop renovado de Dua Lipa en su álbum 'Radical', disponible en CD.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Los Bunkers",
                        300000.0,
                        "El rock chileno de Los Bunkers en CD, una pieza esencial para fanáticos nacionales.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Gorillaz - Demon Days",
                        300000.0,
                        "El icónico 'Demon Days' de Gorillaz en CD, con una mezcla única de estilos.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Bruno Mars - Doo-Wops & Hooligans",
                        50000.0,
                        "El debut de Bruno Mars con 'Doo-Wops & Hooligans', lleno de hits inolvidables.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Bob Marley",
                        60000.0,
                        "Los mejores éxitos de Bob Marley en CD, un viaje directo al reggae más auténtico.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Benson Boone - American Heartbreak",
                        300000.0,
                        "El álbum 'American Heartbreak' de Benson Boone en CD, con un sonido fresco y juvenil.",
                        Producto.Categoria.CDS
                ));

                productoRepository.save(new Producto(null,
                        "Olivia Rodrigo - Sour",
                        300000.0,
                        "El explosivo debut de Olivia Rodrigo en CD, con todos sus himnos juveniles.",
                        Producto.Categoria.CDS
                ));

                // ================= ACCESORIOS =================
                productoRepository.save(new Producto(null,
                        "Lector de Vinilos",
                        200000.0,
                        "Reproductor de vinilos de alta calidad, ideal para disfrutar tu colección.",
                        Producto.Categoria.ACCESORIOS
                ));

                productoRepository.save(new Producto(null,
                        "Reproductor CD",
                        180000.0,
                        "Compacto y moderno reproductor de CDs, perfecto para escuchar tu música favorita.",
                        Producto.Categoria.ACCESORIOS
                ));

                productoRepository.save(new Producto(null,
                        "Fundas para Vinilos",
                        30000.0,
                        "Set de fundas protectoras para vinilos, mantiene tu colección en perfecto estado.",
                        Producto.Categoria.ACCESORIOS
                ));

                // KIT DE LIMPIEZA arreglado (quité el texto incorrecto '85/9*-')
                productoRepository.save(new Producto(null,
                        "Kit Limpieza Vinilo",
                        70000.0,
                        "Kit de limpieza para vinilos, elimina polvo y estática para mejor sonido.",
                        Producto.Categoria.ACCESORIOS
                ));

                productoRepository.save(new Producto(null,
                        "Maleta para Vinilos",
                        75000.0,
                        "Maleta rígida para transportar y proteger tus vinilos de forma segura.",
                        Producto.Categoria.ACCESORIOS
                ));

                productoRepository.save(new Producto(null,
                        "Púa para Tornamesa",
                        100000.0,
                        "Aguja de repuesto para tornamesa, garantiza la mejor calidad de reproducción.",
                        Producto.Categoria.ACCESORIOS
                ));

                System.out.println("Productos cargados correctamente en la base de datos");
            } else {
                System.out.println("Los productos ya existen, no se insertaron nuevamente");
            }
        };
    }
}
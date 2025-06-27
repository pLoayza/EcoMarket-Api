package ecoMarket.duoc.cl.productos.repository;

import ecoMarket.duoc.cl.productos.model.Producto;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Locale;
import java.util.Random;

@Component
@Profile("!test") // No se ejecuta en pruebas unitarias
public class ProductoDataLoader implements CommandLineRunner {

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker(new Locale("es"));
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            Producto producto = new Producto();
            producto.setNombre(faker.commerce().productName());
            producto.setDescripcion(faker.lorem().sentence(5));
            producto.setPrecio(1 + random.nextInt(10000)); // precios >= 1
            productoRepository.save(producto);
        }
    }
}

package ecoMarket.duoc.cl.usuarios.repository;

import ecoMarket.duoc.cl.usuarios.model.Usuario;
import net.datafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.Random;
@Profile("!test")
@Component

public class UsuarioDataLoader implements CommandLineRunner {
    @Autowired
    private UsuarioRepository usuarioRepository;
    @Override
    public void run(String... args) throws Exception {
        Faker faker = new Faker();
        Random random = new Random();

        for (int i = 0; i < 15; i++) {
            Usuario usuario = new Usuario();
            usuario.setNombre(faker.name().firstName());
            usuario.setEmail(faker.internet().emailAddress());
            usuario.setPassword(faker.internet().password());
            usuario.setTelefono(faker.phoneNumber().phoneNumber());
            usuario.setEstado(faker.bool().bool());
            usuario.setRut(faker.numerify("##.###.###-#"));
            usuario.setApellidos(faker.name().lastName());
            //noinspection removal
            usuario.setFechaNacimiento(faker.date().birthday(18, 65));
            usuarioRepository.save(usuario);
        }



    }
}

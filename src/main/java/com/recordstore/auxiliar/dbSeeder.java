package com.recordstore.auxiliar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.recordstore.enums.ALBUM_FORMAT;
import com.recordstore.enums.ALBUM_GENRE;
import com.recordstore.enums.HEADPHONES_TYPE;
import com.recordstore.enums.MECHANISM;
import com.recordstore.enums.NOISE_CANCELING;
import com.recordstore.enums.PORTABLE_TYPE;
import com.recordstore.enums.POWER_TYPE;
import com.recordstore.enums.RESISTANCE;
import com.recordstore.enums.TRACTION;
import com.recordstore.enums.VINYL_RPM;
import com.recordstore.enums.VINYL_SIZE;
import com.recordstore.model.Album;
import com.recordstore.model.Headphone;
import com.recordstore.model.OrderProduct;
import com.recordstore.model.Player;
import com.recordstore.model.Portable;
import com.recordstore.model.Product;
import com.recordstore.model.Speaker;
import com.recordstore.model.Turntable;
import com.recordstore.model.User;
import com.recordstore.model.Vinyl;
import com.recordstore.model.Wishlist;
import com.recordstore.repository.AlbumRepository;
import com.recordstore.repository.HeadphoneRepository;
import com.recordstore.repository.OrderRepository;
import com.recordstore.repository.PlayerRepository;
import com.recordstore.repository.PortableRepository;
import com.recordstore.repository.ProductRepository;
import com.recordstore.repository.SpeakerRepository;
import com.recordstore.repository.TurntableRepository;
import com.recordstore.repository.UserRepository;
import com.recordstore.repository.VinylRepository;
import com.recordstore.repository.WishlistRepository;
import com.recordstore.service.OrderService;
import com.recordstore.service.WishlistService;

import jakarta.annotation.PostConstruct;

/**
 * Clase encargada de poblar la base de datos con datos de prueba para los diferentes objetos del sistema.
 * Esta clase se ejecuta despues de que los repositorios y servicios necesarios hayan sido inyectados,
 * gracias al uso de la anotacion {@link javax.annotation.PostConstruct}.
 * 
 * Los datos generados incluyen informacion sobre usuarios, productos, albumes, vinilos, tornamesas,
 * audifonos, bocinas, reproductores y listas de deseos. Ademas, genera ordenes para los usuarios con productos aleatorios.
 * 
 * <p>Las principales responsabilidades de esta clase incluyen:</p>
 * <ul>
 *     <li>Crear usuarios de prueba.</li>
 *     <li>Crear albumes, vinilos y otros productos relacionados.</li>
 *     <li>Generar y almacenar listas de deseos para los usuarios.</li>
 *     <li>Generar ordenes de productos asociadas a los usuarios.</li>
 * </ul>
 * 
 * Los datos generados se utilizan para simular una base de datos con informacion de ejemplo,
 * permitiendo pruebas en un entorno de desarrollo o pruebas.
 * 
 * Se inyectan multiples repositorios y servicios a traves de la anotacion {@link org.springframework.beans.factory.annotation.Autowired}.
 * 
 * <p>Ejemplo de uso:</p>
 * <pre>
 * {@code
 * @PostConstruct
 * public void allSeeder() {
 *     userSeeder();
 *     albumSeeder();
 *     turntableSeeder();
 *     vinylSeeder();
 *     headphoneSeeder();
 *     speakerSeeder();
 *     playerSeeder();
 *     portableSeeder();
 *     wishlistSeeder();
 *     orderSeeder();
 * }
 * }
 * </pre>
 */
@Component
public class dbSeeder {



	/** Repositorios */
	@Autowired
	private final UserRepository userRepository;
	private final WishlistRepository wishlistRepository;
    //Productos:
    private final ProductRepository productRepository;

	//Albumes
	private final AlbumRepository albumRepository;
	private final VinylRepository vinylRepository;

	//Audio Equipment
	private final HeadphoneRepository headphonesRepository;
	private final SpeakerRepository speakerRepository;

	//Players
	private final PortableRepository portableRepository;
	private final PlayerRepository playerRepository;
	private final TurntableRepository turntableRepository;
    
    //*Services */
    //User order y wishlist
    private final OrderService orderService;
    private final WishlistService wishlistService;
    
    
        @Autowired
        public dbSeeder(
            UserRepository userRepository, OrderRepository orderRepository, WishlistService wishlistService, 
            WishlistRepository wishlistRepository,  AlbumRepository albumRepository, 
            VinylRepository vinylRepository, HeadphoneRepository headphonesRepository, SpeakerRepository speakerRepository, 
            PortableRepository portableRepository, PlayerRepository playerRepository, TurntableRepository turntableRepository, ProductRepository productRepository,
            
            OrderService orderService) {
           
            //*Repositorios */
            this.wishlistService = wishlistService;
            this.userRepository = userRepository;
            this.wishlistRepository = wishlistRepository;
            this.productRepository = productRepository;
            this.albumRepository = albumRepository;
            this.vinylRepository = vinylRepository;
            this.headphonesRepository = headphonesRepository;
            this.speakerRepository = speakerRepository;
            this.portableRepository = portableRepository;
            this.playerRepository = playerRepository;
            this.turntableRepository = turntableRepository;
    
            //*Services */
            this.orderService = orderService;
    }

    @PostConstruct//Se ejecuta despues de que se inyecten los repositorios
    public void allSeeder() {
        userSeeder();
        albumSeeder();
        turntableSeeder();
        vinylSeeder();
        headphoneSeeder();
        speakerSeeder();
        playerSeeder();
        portableSeeder();
        wishlistSeeder();
        orderSeeder() ;

    }

    public void userSeeder() {

		List<User> usuarios = Arrays.asList(
			new User("Juan Perez", "5845464864", "juanperez@gmail.com", "juanperez", "Callle 3ra #15"),
			new User("Maria Lopez", "5845464865", "marialopez@gmail.com", "marialopez", "Avenida 5ta #20"),
			new User("Carlos Sanchez", "5845464866", "carlossanchez@gmail.com", "carlossanchez", "Calle 8va #30"),
			new User("Ana Gomez", "5845464867", "anagomez@gmail.com", "anagomez", "Boulevard 10ma #40"),
			new User("Luis Fernandez", "5845464868", "luisfernandez@gmail.com", "luisfernandez", "Calle 12va #50")
		);


			userRepository.saveAll(usuarios);

		
    }

    public void albumSeeder() {


		List<Album> albumes = Arrays.asList(
    new Album("The Dark Side of the Moon", 20.0, 10, "Pink Floyd", 1973, ALBUM_FORMAT.CD, ALBUM_GENRE.ROCK, "43:00"),
    new Album("Thriller", 25.0, 15, "Michael Jackson", 1982, ALBUM_FORMAT.DVD, ALBUM_GENRE.POP, "42:19"),
    new Album("Back in Black", 22.0, 12, "AC/DC", 1980, ALBUM_FORMAT.CD_DVD, ALBUM_GENRE.ROCK, "41:59"),
    new Album("Rumours", 18.0, 8, "Fleetwood Mac", 1977, ALBUM_FORMAT.BOXSET, ALBUM_GENRE.ROCK, "39:43"),
    new Album("Abbey Road", 24.0, 20, "The Beatles", 1969, ALBUM_FORMAT.DVD, ALBUM_GENRE.ROCK, "47:23")
);

		 	albumRepository.saveAll(albumes);

    }

		private void turntableSeeder() {

		List<Turntable> tornamesas = Arrays.asList(
			new Turntable("Audio-Technica AT-LP120XUSB", 349.00, 8, "Audio-Technica", "Black", 2,
				true, true, false, true, true, false, true, VINYL_RPM.RPM_33_45, TRACTION.DIRECT_DRIVE, MECHANISM.MANUAL),
			new Turntable("Pro-Ject Debut Carbon EVO", 499.00, 5, "Pro-Ject", "Red", 2,
				false, false, false, true, true, false, false, VINYL_RPM.RPM_33_45, TRACTION.BELT_DRIVE, MECHANISM.MANUAL),
			new Turntable("Sony PS-LX310BT", 198.00, 12, "Sony", "Black", 1,
				true, false, false, true, false, true, false, VINYL_RPM.RPM_33_45, TRACTION.BELT_DRIVE, MECHANISM.AUTOMATIC),
			new Turntable("Rega Planar 1", 475.00, 6, "Rega", "White", 2,
				false, false, false, false, false, false, false, VINYL_RPM.RPM_33_45, TRACTION.BELT_DRIVE, MECHANISM.MANUAL),
			new Turntable("Technics SL-1500C", 1199.00, 3, "Technics", "Silver", 2,
				true, true, false, true, true, false, true, VINYL_RPM.RPM_33_45, TRACTION.DIRECT_DRIVE, MECHANISM.MANUAL)
		);



			turntableRepository.saveAll(tornamesas);
		

	
		}

		private void vinylSeeder() {
		//?Vinilos de prueba
		List<Vinyl> vinilos = Arrays.asList(
            new Vinyl("Lateralus", 29.99, 50, "Tool", 2001, ALBUM_FORMAT.LP, ALBUM_GENRE.METAL, "78:00", VINYL_SIZE.S_12, VINYL_RPM.RPM_33, "Black"),
            new Vinyl("DTMF", 24.99, 100, "Bad Bunny", 2025, ALBUM_FORMAT.LP, ALBUM_GENRE.REGGAETON, "53:21", VINYL_SIZE.S_12, VINYL_RPM.RPM_33, "Blue"),
            new Vinyl("Dear Science", 19.99, 75, "TV on the Radio", 2008, ALBUM_FORMAT.LP, ALBUM_GENRE.INDIE, "50:45", VINYL_SIZE.S_12, VINYL_RPM.RPM_33, "Red"),
            new Vinyl("Re", 22.99, 60, "Cafe Tacvba", 1994, ALBUM_FORMAT.LP, ALBUM_GENRE.ROCK, "45:30", VINYL_SIZE.S_12, VINYL_RPM.RPM_33, "Green"),
            new Vinyl("Siembra", 27.99, 80, "Ruben Blades", 1983, ALBUM_FORMAT.LP, ALBUM_GENRE.SALSA, "50:00", VINYL_SIZE.S_12, VINYL_RPM.RPM_33, "Yellow")
        );


			vinylRepository.saveAll(vinilos);
		

		}

		private void headphoneSeeder() {

		List<Headphone> headphones = Arrays.asList(  
            new Headphone( "WH-1000XM4", 349.99, 200, "Sony", "Black", 30, 24, 
			true, true, true, false, true, 
			HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.ACTIVE),
            new Headphone( "Beoplay H95", 849.99, 150, "B&O", "Gold", 38, 24, true, true, true, false, true, HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.ACTIVE),
            new Headphone( "ATH-M50X", 149.99, 300, "Audio-Technica", "Black", 0, 12, false, false, false, false, true, HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.PASSIVE),
            new Headphone( "Momentum 3 Wireless", 399.99, 250, "Sennheiser", "Black", 17, 24, true, true, true, false, true, HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.ACTIVE),
            new Headphone( "Harman Kardon FLY ANC", 199.99, 180, "Harman/Kardon", "Silver", 25, 12, true, true, true, false, true, HEADPHONES_TYPE.OVER_EAR, NOISE_CANCELING.ACTIVE)
        );


			headphonesRepository.saveAll(headphones);
		

		}

		private void speakerSeeder() {
		 List<Speaker> speakers = Arrays.asList(
			
		 	new Speaker("SRS-XB43", 199.99, 150, "Sony", "Black", 24, 12, false, true, true, true, true, false, 30, 6, 20, 20000, 3000, POWER_TYPE.DC, RESISTANCE.WS),
			new Speaker( "SoundLink Revolve+", 299.99, 100, "Bose", "Silver", 16, 12, false, true, true, false, true, false, 20, 4, 50, 20000, 2000, POWER_TYPE.DC, RESISTANCE.WATER),
        	new Speaker( "Charge 5", 179.99, 200, "JBL", "Blue", 20, 12, false, true, true, true, true, false, 40, 8, 65, 20000, 2500, POWER_TYPE.DC, RESISTANCE.WSD),
            new Speaker("Stanmore II", 349.99, 80, "Marshall", "Black", 0, 24, false, false, true, false, true, false, 80, 6, 50, 20000, 4500, POWER_TYPE.AC, RESISTANCE.SHOCK),
             new Speaker("Onyx Studio 7", 299.99, 120, "Harman Kardon", "Gray", 8, 12, false, true, true, false, true, false, 50, 6, 50, 20000, 3300, POWER_TYPE.DC, RESISTANCE.WD)
		 );

			speakerRepository.saveAll(speakers);
		

		}

		private void playerSeeder() {

		List<Player> players = Arrays.asList(
            new Player("CDP-CE500", 199.99, 50, "Sony", "Black", 12, false, true, false, true, true, false),
            new Player("CD-S300", 249.99, 40, "Yamaha", "Silver", 24, false, true, false, true, true, false),
            new Player("DCD-800NE", 349.99, 30, "Denon", "Black", 24, true, true, false, true, true, false),
            new Player("PD-30AE", 299.99, 35, "Pioneer", "Silver", 12, false, true, false, true, true, false),
            new Player("C-7030", 399.99, 25, "Onkyo", "Black", 24, false, true, false, true, true, false)
        );

			playerRepository.saveAll(players);
	

		}

		private void portableSeeder() {

              List<Portable> portables = Arrays.asList(
                new Portable("NW-ZX507", 699.99, 30, "Sony", "Black", "Walkman digital de alta fidelidad con soporte para Hi-Res Audio", 12, true, true, false, true, false, false, PORTABLE_TYPE.DIGITAL, POWER_TYPE.DC, 20, RESISTANCE.WATER),
                    new Portable("WM-FX290", 99.99, 50, "Sony", "Silver", "Walkman de cassette con radio FM y resistencia al agua", 12, false, false, true, true, false, false, PORTABLE_TYPE.CASSETTE, POWER_TYPE.DC, 15, RESISTANCE.WS),
                    new Portable("D-NE319", 129.99, 40, "Sony", "Blue", "Reproductor de CD portatil con radio FM y resistencia a golpes", 12, false, false, true, true, false, false, PORTABLE_TYPE.CD, POWER_TYPE.DC, 10, RESISTANCE.SHOCK),
                    new Portable("Plenue D2", 499.99, 25, "Cowon", "Black", "Reproductor digital portatil con sonido de alta fidelidad y diseño compacto", 12, false, true, false, true, false, false, PORTABLE_TYPE.DIGITAL, POWER_TYPE.DC, 22, RESISTANCE.WD),
                    new Portable("CT-X10", 79.99, 60, "Panasonic", "Red", "Reproductor de cassette con radio AM/FM y resistencia al polvo", 12, false, false, true, true, false, false, PORTABLE_TYPE.CASSETTE, POWER_TYPE.DC, 12, RESISTANCE.DUST)
                );
        
        
                    portableRepository.saveAll(portables);
                
              
    }

        private void wishlistSeeder() {
            List<User> users = userRepository.findAll();
            List<Product> products = productRepository.findAll();
            Random random = new Random();
        
            for (User user : users) {
                Wishlist wishlist = new Wishlist(user);  // Crear una wishlist para el usuario
                wishlistRepository.save(wishlist);  // Guardar la wishlist
        
                // Agregar entre 1 y 3 productos aleatorios a la wishlist
                int numProducts = random.nextInt(3) + 1;
                for (int i = 0; i < numProducts; i++) {
                    Product product = products.get(random.nextInt(products.size()));
                    wishlistService.addProductToWishlist(wishlist.getUser().getId(), product.getId());
                                       
                }
            }
    }

private void orderSeeder() {
    printMsg.flag("Impreso desde orderSeeder");
    List<User> users = userRepository.findAll();
    List<Product> products = productRepository.findAll();
    Random random = new Random();

    if (users.isEmpty() || products.isEmpty()) {
        System.out.println("No hay usuarios o productos disponibles para generar ordenes.");
        return;
    }

    for (User user : users) {
        int ordersCount = random.nextInt(3) + 1; // 1 a 3 ordenes por usuario

        for (int i = 0; i < ordersCount; i++) {
            List<OrderProduct> orderProducts = new ArrayList<>(); // Crear lista para los productos
            Set<Product> selectedProducts = new HashSet<>(); // Evita productos repetidos en la misma orden

            int productCount = random.nextInt(3) + 1; // 1 a 3 productos por orden

            for (int j = 0; j < productCount; j++) {
                Product product;
                do {
                    product = products.get(random.nextInt(products.size()));
                } while (!selectedProducts.add(product)); // Evita productos repetidos en la misma orden

                OrderProduct orderProduct = new OrderProduct();
                orderProduct.setProduct(product);
                orderProduct.setQuantity(random.nextInt(5) + 1); // Cantidad entre 1 y 5

                orderProducts.add(orderProduct); // Agregar a la lista
            }

            //Creo una orden con la lista completa
            orderService.createOrder(user, orderProducts);
        }
    }
}

}


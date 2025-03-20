package com.recordstore.enums;

/**
 * Enum que representa los diferentes generos musicales disponibles en la tienda de discos.
 * Este enum se utiliza en la entidad {@link com.recordstore.model.Album} para definir el genero musical de un album.
 * 
 * Los generos disponibles incluyen una variedad de estilos populares y tradicionales:
 * 
 * <ul>
 *   <li>ROCK: Genero musical caracterizado por el uso de guitarras electricas y baterias, con influencias del blues y el country.</li>
 *   <li>POP: Genero musical accesible, generalmente con melodias pegajosas y de facil escucha.</li>
 *   <li>HIP_HOP: Estilo musical que se origino en la cultura urbana, caracterizado por el rap y los beats electronicos.</li>
 *   <li>JAZZ: Genero musical que se distingue por su improvisacion y complejidad armonica, nacido en Estados Unidos.</li>
 *   <li>BLUES: Genero basado en el uso de escalas menores, con un ritmo melancolico y una fuerte carga emocional.</li>
 *   <li>COUNTRY: Genero musical con raices en la musica folk y western, caracterizado por guitarras acusticas y letras narrativas.</li>
 *   <li>ELECTRONICA: Genero musical que utiliza tecnologia y maquinas electronicas para crear sonidos, frecuentemente en ambientes de dance y rave.</li>
 *   <li>REGGAE: Estilo originado en Jamaica, caracterizado por ritmos relajados y letras de conciencia social.</li>
 *   <li>CLASICA: Musica tradicional y formal, con composiciones complejas interpretadas por orquestas y solistas.</li>
 *   <li>TROVA: Genero tradicional latinoamericano caracterizado por canciones de autoria personal y liricas poeticas.</li>
 *   <li>SALSA: Estilo de musica bailable originado en Cuba y Puerto Rico, con ritmos rapidos y una fuerte influencia del jazz.</li>
 *   <li>METAL: Genero musical que se caracteriza por su sonido pesado y distorsionado, con letras que suelen ser oscuras o filosoficas.</li>
 *   <li>PUNK: Estilo musical de caracter rebelde, con guitarras distorsionadas y letras de protesta.</li>
 *   <li>FUNK: Genero basado en el ritmo, caracterizado por su groove y una fuerte seccion de percusion.</li>
 *   <li>SOUL: Genero que mezcla gospel, jazz y Rythm and Blues, con un enfoque en la expresion emocional a traves de la voz.</li>
 *   <li>DISCO: Genero bailable de musica electronica, popular en las discotecas de los anos 70.</li>
 *   <li>INDIE: Estilo que engloba a artistas y bandas que trabajan de forma independiente a las grandes discograficas.</li>
 *   <li>FOLK: Musica tradicional que se transmite oralmente y refleja las culturas populares, a menudo usando instrumentos acusticos.</li>
 *   <li>RAP: Genero musical basado en el recitado ritmico y rimas, originado en las comunidades urbanas afroamericanas.</li>
 *   <li>REGGAETON: Estilo de musica bailable de influencia latina, con un ritmo pegajoso y letras en espanol que hablan de fiestas y amor.</li>
 * </ul>
 *
 * Ejemplo de uso:
 * <pre>
 * Album album = new Album();
 * album.setGenre(ALBUM_GENRE.ROCK);
 * </pre>
 */

public enum ALBUM_GENRE {
    
    /** Genero musical caracterizado por el uso de guitarras electricas y baterias, con influencias del blues y el country. */
    ROCK,
    
    /** Genero musical accesible, generalmente con melodias pegajosas y de facil escucha. */
    POP,
    
    /** Estilo musical que se origino en la cultura urbana, caracterizado por el rap y los beats electronicos. */
    HIP_HOP,
    
    /** Genero musical que se distingue por su improvisacion y complejidad armonica, nacido en Estados Unidos. */
    JAZZ,
    
    /** Genero basado en el uso de escalas menores, con un ritmo melancolico y una fuerte carga emocional. */
    BLUES,
    
    /** Genero musical con raices en la musica folk y western, caracterizado por guitarras acusticas y letras narrativas. */
    COUNTRY,
    
    /** Genero musical que utiliza tecnologia y maquinas electronicas para crear sonidos, frecuentemente en ambientes de dance y rave. */
    ELECTRONICA,
    
    /** Estilo originado en Jamaica, caracterizado por ritmos relajados y letras de conciencia social. */
    REGGAE,
    
    /** Musica tradicional y formal, con composiciones complejas interpretadas por orquestas y solistas. */
    CLASICA,
    
    /** Genero tradicional latinoamericano caracterizado por canciones de autoria personal y liricas poeticas. */
    TROVA,
    
    /** Estilo de musica bailable originado en Cuba y Puerto Rico, con ritmos rapidos y una fuerte influencia del jazz. */
    SALSA,
    
    /** Genero musical que se caracteriza por su sonido pesado y distorsionado, con letras que suelen ser oscuras o filosoficas. */
    METAL,
    
    /** Estilo musical de caracter rebelde, con guitarras distorsionadas y letras de protesta. */
    PUNK,
    
    /** Genero basado en el ritmo, caracterizado por su groove y una fuerte seccion de percusion. */
    FUNK,
    
    /** Genero que mezcla gospel, jazz y Rythm and Blues, con un enfoque en la expresion emocional a traves de la voz. */
    SOUL,
    
    /** Genero bailable de musica electronica, popular en las discotecas de los anos 70. */
    DISCO,
    
    /** Estilo que engloba a artistas y bandas que trabajan de forma independiente a las grandes discograficas. */
    INDIE,
    
    /** Musica tradicional que se transmite oralmente y refleja las culturas populares, a menudo usando instrumentos acusticos. */
    FOLK,
    
    /** Genero musical basado en el recitado ritmico y rimas, originado en las comunidades urbanas afroamericanas. */
    RAP,
    
    /** Estilo de musica bailable de influencia latina, con un ritmo pegajoso y letras en espanol que hablan de fiestas y amor. */
    REGGAETON;
}

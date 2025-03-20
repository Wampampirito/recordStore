package com.recordstore.enums;

/**
 * Enum that represents the different music genres available in the record store.
 * This enum is used in the {@link com.recordstore.model.Album} entity to define the musical genre of an album.
 * 
 * Available genres include a variety of popular and traditional styles:
 * 
 * <ul>
 *   <li>ROCK: A musical genre characterized by the use of electric guitars and drums, with influences from blues and country.</li>
 *   <li>POP: A widely accessible musical genre, generally with catchy melodies and easy-to-listen-to tunes.</li>
 *   <li>HIP_HOP: A musical style originating from urban culture, characterized by rap and electronic beats.</li>
 *   <li>JAZZ: A genre of music distinguished by its improvisation and harmonic complexity, born in the United States.</li>
 *   <li>BLUES: A genre based on the use of minor scales, with a melancholic rhythm and strong emotional depth.</li>
 *   <li>COUNTRY: A musical genre with roots in folk and western music, characterized by acoustic guitars and narrative lyrics.</li>
 *   <li>ELECTRONICA: A genre that uses technology and electronic machines to create sounds, often in dance and rave environments.</li>
 *   <li>REGGAE: A style originated in Jamaica, characterized by relaxed rhythms and socially conscious lyrics.</li>
 *   <li>CLASSICAL: Traditional and formal music, with complex compositions performed by orchestras and soloists.</li>
 *   <li>TROVA: A Latin American traditional genre, characterized by personal authorship songs and poetic lyrics.</li>
 *   <li>SALSA: A dance music style originating from Cuba and Puerto Rico, with fast rhythms and strong jazz influence.</li>
 *   <li>METAL: A musical genre characterized by heavy and distorted sound, with lyrics that are often dark or philosophical.</li>
 *   <li>PUNK: A musical style with a rebellious character, featuring distorted guitars and protest lyrics.</li>
 *   <li>FUNK: A genre focused on rhythm, characterized by its groove and strong percussion section.</li>
 *   <li>SOUL: A genre that blends gospel, jazz, and Rhythm and Blues, with an emphasis on emotional expression through the voice.</li>
 *   <li>DISCO: A danceable electronic music genre, popular in 1970s nightclubs.</li>
 *   <li>INDIE: A style that includes artists and bands working independently of major record labels.</li>
 *   <li>FOLK: Traditional music passed down orally, reflecting popular cultures, often using acoustic instruments.</li>
 *   <li>RAP: A musical genre based on rhythmic speech and rhymes, originating from African American urban communities.</li>
 *   <li>REGGAETON: A Latin-influenced dance music style with catchy rhythms and lyrics in Spanish, often revolving around parties and love.</li>
 * </ul>
 *
 * Example usage:
 * <pre>
 * Album album = new Album();
 * album.setGenre(ALBUM_GENRE.ROCK);
 * </pre>
 */
public enum ALBUM_GENRE {
    
    /** A musical genre characterized by the use of electric guitars and drums, with influences from blues and country. */
    ROCK,
    
    /** A widely accessible musical genre, generally with catchy melodies and easy-to-listen-to tunes. */
    POP,
    
    /** A musical style originating from urban culture, characterized by rap and electronic beats. */
    HIP_HOP,
    
    /** A genre of music distinguished by its improvisation and harmonic complexity, born in the United States. */
    JAZZ,
    
    /** A genre based on the use of minor scales, with a melancholic rhythm and strong emotional depth. */
    BLUES,
    
    /** A musical genre with roots in folk and western music, characterized by acoustic guitars and narrative lyrics. */
    COUNTRY,
    
    /** A genre that uses technology and electronic machines to create sounds, often in dance and rave environments. */
    ELECTRONICA,
    
    /** A style originated in Jamaica, characterized by relaxed rhythms and socially conscious lyrics. */
    REGGAE,
    
    /** Traditional and formal music, with complex compositions performed by orchestras and soloists. */
    CLASSICAL,
    
    /** A Latin American traditional genre, characterized by personal authorship songs and poetic lyrics. */
    TROVA,
    
    /** A dance music style originating from Cuba and Puerto Rico, with fast rhythms and strong jazz influence. */
    SALSA,
    
    /** A musical genre characterized by heavy and distorted sound, with lyrics that are often dark or philosophical. */
    METAL,
    
    /** A musical style with a rebellious character, featuring distorted guitars and protest lyrics. */
    PUNK,
    
    /** A genre focused on rhythm, characterized by its groove and strong percussion section. */
    FUNK,
    
    /** A genre that blends gospel, jazz, and Rhythm and Blues, with an emphasis on emotional expression through the voice. */
    SOUL,
    
    /** A danceable electronic music genre, popular in 1970s nightclubs. */
    DISCO,
    
    /** A style that includes artists and bands working independently of major record labels. */
    INDIE,
    
    /** Traditional music passed down orally, reflecting popular cultures, often using acoustic instruments. */
    FOLK,
    
    /** A musical genre based on rhythmic speech and rhymes, originating from African American urban communities. */
    RAP,
    
    /** A Latin-influenced dance music style with catchy rhythms and lyrics in Spanish, often revolving around parties and love. */
    REGGAETON;
}

package game;

public class GameStatus {

    private static int playerScore = 0;

    private static int playerLives = 3;

    private static boolean gameOver = false;

    public static String[] wordsList = {
            "abrigo","agujeta","calcetines","calzoncillo","camisa","camiseta","corbata","gorra","algodon","blusa","bolsa","cierre","cinturon","falda","guantes","medias",
            "plantas","arbol","arbusto","cactus","abeja","alacran","alce","aguila","anguila","araña","ardilla","ardillita","armadillo","bisonte","lechuza","burro","caballo",
            "cabra","caiman","camaleon","cardenal","cebra","cerdo","cocodrilo","colmillo","conejo","cisne","cucaracha","cuerno","cuervo","elefante","escarabajo","gato","gaviota",
            "gorrion","grillo","gusano","halcon","hipopotamo","hormiga","babero","biberon","carrito","chupador","cuna","baño","bañera","champu","desague","ducha","espejo","cocina",
            "abrelatas","caldero","colador","congelador","cuchara","destapador","escurridor","estufa","fregadero","gabinete","horno","horno","jarra","cuarto","aspiradora","escoba",
            "lavadora","limpiadora","cuarto","cama","mesita","comoda","almohada","cobija","escalera","jarron","lampara","mesita","pared","pintura","repisa","sofa","sillon","herramientas",
            "metrica","destornillador","formon","aeropuerto","bolso","equipaje","restaurante","cocinero","menu","gasolinera","grandes","playa","aletas","arena","alga","castillo","bronceadora",
            "gafas","apartamentos","ascensor","banca","basurero","edificio","oficinas","tornado","huracan","inundacion","lluvia","nieve","nublado","hace","sol","tenis","basquetbol","golf","futbol",
            "futbol","voleibol","ping","badminton","beisbol","guitarra","tambores","trompeta","rojo","azul","amarillo","verde","violeta","morado","limon","lima","frijoles","tomate","remolacha",
            "rabano","mantequilla","miel","nuez","mermelada","jalea","jugo","dulce","mayonesa","kechup","mostaza","piña","banana","durazno","albaricoque","pera","uva","pasa","harina","comida",
            "familia","mama","papa","hermano","hermana","abuelos","abuela","abuelo","primos","sobrino","cuñado","cuñada","suegra","cajuela","ventana","rueda","llanta","claxon","volante","chofer",
            "pajarita","pantalones","sombrero","sueter","traje","zapatos","pijama","pantaleta","pantimedia","sandalias","vestido","zapatos","hoja","margarita","tallo","tulipan","violeta","rosa",
            "iguana","jirafa","lagartija","leon","libelula","llama","loro","mantis","mariposa","mono","mosca","mosquito","pajaro","paloma","perro","petirrojo","pez","oso","oveja","rana","rata",
            "raton","raya","renacuajo","rinoceronte","salmon","saltamontes","tiburon","tigre","tortuga","trompa","trucha","vaca","venado","zorro","zancudo","cuna","oso","pañal","esponja","excusado",
            "jabon","jabonera","lavamanos","tina","toalla","lavaplatos","licuadora","mesa","nevera","olla","plato","pimentero","refrigerador","salero","sarten","servilleta","tapa","tenedor","tostador",
            "vaporera","vaso","recogedor","secadora","despertador","sabanas","armario","colgador","techo","muebles","mesa","cama","sillon","silla","escritorio","piano","basurero","llave","martillo",
            "sierra","maleta","mesa","camarera","bolso","oferta","mar","onda","orilla","toalla","traje","sombrilla","oficina","autobus","calor","temperatura","termometro","nebuloso","neblina","ventoso",
            "hace","humedad","nube","jockey","rugby","equitacion","natacion","equipo","piano","letra","anaranjado","rosa","marron","negro","blanco","aperitivo","aguacate","carnederes","desayuno","coliflor",
            "apio","queso","pollo","postre","cena","huevo","pescado","hamburguesa","hotdog","papas","almuerzo","lechuga","leche","puerco","papas","ensalada","sandwich","sopa","azucar","pavo","agua","helado",
            "suegro","novio","hijo","hija","nuera","yerno","amigo","novio","marido","esposa","madre","padre","sobrina","gasolina","cinturon","bulbo","fachada","pasar","pelota","pakistani","pronto","como,",
            "cuanto","archipielago","demorado,","avion","retrasado","telefono","ulcera","floristeria","septimo","escuadra","noche","mayordomo","cebolletas","padrastro","tumbona","asco,","repulsion","mortificacion",
            "oval","bahia","funda","calamar","berro","cantante","remordimiento","tendencia","desmayarse","pamela","despejado","snowboard","cubico","barajar","cartas","bañero","vibora","cascabel","cacerola",
            "bragas","tanto","gimnasia","potro","playo","dentista","estudio","hinchazon","trabajo","cadena","piraguismo","aspero,","rugoso","groenlandes","soledad","tienda","deportes","balon","jersey","berberechos",
            "derecho","laboral","epidemia","grueso","derrota","copa","mundo","para,","objeto","extremidades","tener","pinchazo","pepino","embargo","hojas","despues","cabo","pañuelo","bolsillo","clasificacion",
            "dormitorio","lirio","repugnancia","hasta","embarcar","solar","ochenta","buey","enchufe","pezones,","tetillas","mejillon","caracol","abanico","septuagesimo","actualizado","decimoquinto","cordero",
            "horror","corazones","angustia","manzana","plata","gladiolo","chaque","amor","ajedrez","ira,","colera","choza","compañia","aerea","planta","deseo","saque","puerta","paperas","tension","pasaporte",
            "tarantula","poste","chichon","armario,","ropero","boxeo","grados","quemadura","hipodromo","atraccion","aire","acondicionado","despegar","primogenito","halterofilia","ahijada","desierto","pecho",
            "esteticista","canadiense","toca","mover","picadura","insecto","aprendiz","viento","corral","carreras","coches","cadera,","caderas","impermeable","cocida","perito","mercantil","rascacielos","griego",
            "veterinario","oftalmologo","nieta","metros","cubierto,","encapotado","inseguridad","anticuado","entrada","para","celo","vendedor","judo","transmision","bunker","portico,","porche","motor","torax",
            "sanguijuela","coreano","tirar","boliviano","cuartel","militar","borrador","misil","esmalte","uñas","cazadora","obrero","agricola","torero","berenjena","director","orquesta","polo","norte","hinojo",
            "hipo","coles","bruselas","maestro","chaleco","prismaticos","cubo","salsa","carne","baloncesto","hostal"};

    public static void addScore(int points) {
        playerScore += points;
    }

    public static int getPlayerScore() {
        return playerScore;
    }

    public static void subtractOneLife() {
        playerLives -= 1;
    }

    public static int getPlayerLives() {
        return playerLives;
    }

    public static boolean isGameOver() {
        return gameOver;
    }

    public static void setGameOver() {
        gameOver = true;
    }
}

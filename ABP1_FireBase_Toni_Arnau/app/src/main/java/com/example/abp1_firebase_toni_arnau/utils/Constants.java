package com.example.abp1_firebase_toni_arnau.utils;

import com.example.abp1_firebase_toni_arnau.R;

public interface Constants {
    int GOOGLE_SIGN_IN = 100;
    //Les arrays & things que serveixin per als jocs.

    int[] drawablesAhorcado = {R.drawable.dinamita_1, R.drawable.dinamita_2, R.drawable.dinamita_3, R.drawable.dinamita_4, R.drawable.dinamita_5};

    int[] drawables = {R.drawable.p1, R.drawable.p2};

    String[][] paraulogics = {{ "anap", "anar", "anió", "anoa", "arna", "línia", "inri", "ioni", "naia", "naipe", "nana",
            "xaval", "napa", "niar", "nina", "nipó", "noia", "nora", "nori", "orni", "pana", "pian", "poni", "prono", "raní",
            "ainar", "arnar", "arran", "irona", "napar", "napia", "ninot", "norai", "orina", "adornar", "panna", "paona", "piano",
            "pinar", "pinna", "piran", "prona", "airina", "annona", "apanar", "arniar", "inòpia", "ionona", "iranià", "ironia",
            "ennar", "narrar", "nina", "nina", "nipona", "opinar", "opinió", "orinar", "piorna", "pirona", "raonar", "ruïnar",
            "aiorina", "arponar", "arranar", "arriana", "non-non", "panarra", "papaïna", "pariona", "porrona", "propina", "annonari",
            "iranià", "opianina", "paranoia", "propinar", "aparionar", "posar-se", "aporrinar", "ninot", "nona", "dolenta",
            "aparionar", "aporrinar", "opinar", "paranoia", "pariona", "piorna", "pirona", "propina", "propinar" },
            {"abans","aede","alada","llogaret","aldol","aldosa","ajada","alotada","asalada","assolellada","atotada","doneu","dada",
                    "dal","dalet","sega","dalla","baixada","del","amunt","data","dea","dédal","deessa","del","delada","delat",
                    "allà","delta","des","guardada","desè","desolada","desolat","disset","sota","detall","detallada","detallat",
                    "dodo","doella","dol","raig","dolla","dolós","dolosa","dos","dosè","dosset","dot","dotal","edat","edetà",
                    "elodea","estada","estadal","desbrossament","estelada","estesa","estesa","ladella","lledoner","beurada","losada",
                    "oda","ollada","agosarada","sad","sachuelo","sacia","salada","saldo","saltada","assolellada","seda","sedal",
                    "garbell","sedós","sedosa","soda","sodada","sodat","paviment","soldada","soldat","solitud","assolellada","solod",
                    "bajolada","tallada","taxada","telleda","tesellada","toledà","tossada","tsade"},
            {"acrofòbia", "afàcia", "afí","afició","aforar","africà","africació","africar","baf","bàfia","bafor","bifi","bífia","bifòbia","bífora","bocafí","bòfia"
                    ,"caf", "caroficia", "cof", "cofa", "cofar", "cofí", "còfia", "satisfet", "satisfeta", "escorça", "en off", "fabàcia", "fabaria",
                    "fabrià", "fàbrica", "fabricació", "fabricar", "facció", "fai", "faió", "far", "farao", "emplenar","fàrfara","faria","faró",
                    "farro", "fibra", "fic", "ficar", "ficció", "fira", "ferir", "fòbia", "foc", "foca", "fofa", "fofo",
                    "foia", "fora", "foraria", "forbir", "forco", "forca", "forcar", "forcó", "folra", "folre", "fra", "frac",
                    "fracció", "fraró", "fricció", "ofici", "oficiar", "òrfic", "òrfica","orifici","rafi","ràfia","rafió","rarificar", "rifa", "rifar"}};

    String[] ahorcado = { "anap", "anar", "anió", "anoa", "arna", "línia", "inri", "ioni", "naia", "naipe", "nana",
            "xaval", "napa", "niar", "nina", "nipó", "noia", "nora", "nori", "orni", "pana", "pian", "poni", "prono", "raní",
            "ainar", "arnar", "arran", "irona", "napar", "napia", "ninot", "norai", "orina", "adornar", "panna", "paona", "piano",
            "pinar", "pinna", "piran", "prona", "airina", "annona", "apanar", "arniar", "inòpia", "ionona", "iranià", "ironia",
            "ennar", "narrar", "nina", "nina", "nipona", "opinar", "opinió", "orinar", "piorna", "pirona", "raonar", "ruïnar",
            "aiorina", "arponar", "arranar", "arriana", "non-non", "panarra", "papaïna", "pariona", "porrona", "propina", "annonari",
            "iranià", "opianina", "paranoia", "propinar", "aparionar", "posar-se", "aporrinar", "ninot", "nona", "dolenta",
            "aparionar", "aporrinar", "opinar", "paranoia", "pariona", "piorna", "pirona", "propina", "propinar" };


    String[] anagrama = {"Nepal","Panel","Nacionalista – Altisonancia",
    "Valora","Álvaro", "Frase","Fresa",
    "Colinas","Nicolás","Pagar","Praga","Integrarla","Inglaterra",
    "Quieren","Enrique","Cornisa","Narciso","Acuerdo","Ecuador",
    "Japonés","Esponja","Amparo","Páramo",Deudora","Eduardo",
    "Ramon","Norma","Camino","Mónica","Fotolitografía","Litofotografía",
    "Animal","Lámina 	Matar","Marta","Saco","Cosa",
    "Mora","Roma","Brasil","Silbar","Sentido","Destino",
    "Saunas","Susana","Croacia","Arcaico","Tinieblas","Sibilante",
    "Aretes","Teresa","Andalucía","Alucinada","Cronista","Cortinas",
    "Ventilan – Valentín","Aparcamiento","Metacarpiano","Calienta","Alicante",
    "Trama – Marta 	Ballena","Llenaba","Reposaré","Reposera",
    "Cardiografía – Radiográfica","Conejo","Encojo","Demostración","Domesticaron",
    "Desamparador – Desparramado","Polonia","Opalino","Sopera","Sopear",
    "Conservadora – Conversadora","Ardientemente","Retenidamente","Áspero","Espora"
    "Irónicamente – Renacimiento","Riesgo","Sergio","Aires – Aries",
    "Escandalizar – Zascandilear","Agonista","Santiago","Presa","Pesar",
    "Enfriamiento – Refinamiento","Calor","Colar","Esta","Ates",
    "Romina – Marino 	Prisa – París 	Astringencia","Transigencia
    "Materialismo – Memorialista 	Poder – Pedro 	Resto","Retos
    "Enérgicamente – genéricamente 	Necrófila – Florencia 	Reías – Ríase
    "Presuposición – Superposición 	Armonía – Mariano 	Terso – Teros
    "Enamoramientos – Armoniosamente 	Salario – Rosalía 	Caracas – Cáscara
    "Rectificable – Certificable 	Ovoide – Oviedo 	España – Apañes
    "Reconquistados – Conquistadores 	Camelia – Micaela 	Alondra – Ladrona
    "Escabullimiento – Bulliciosamente 	Enlodar – Leandro 	Conservación – Conversación
    "Electromagnético – Magnetoeléctrico 	Delira – Lidera 	Derrocamiento – Recordamiento
    "Imponderable – imperdonable 	Agranda – Granada 	Manila – Animal
    "Armonización – Romanización 	Licúa – Lucía 	Ruanda – Anudar
    "Pronosticación","Contraposición","Amor","Roma";"Protesta","Portaste"}

}

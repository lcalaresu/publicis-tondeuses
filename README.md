# Exercice de développement - La tondeuse

## SUJET

### Présentation

La société MowItNow a décidé de développer une tondeuse à gazon automatique, destinée aux surfaces rectangulaires.
La tondeuse peut être programmée pour parcourir l'intégralité de la surface.

La position de la tondeuse est représentée par une combinaison de coordonnées (x,y) et d'une lettre indiquant l'orientation selon la notation cardinale anglaise (N,E,W,S). La pelouse est divisée en grille pour simplifier la navigation.

#### Contrôle de la tondeuse 

Par exemple, la position de la tondeuse peut être « 0, 0, N », ce qui signifie qu'elle se situe dans le coin inférieur gauche de la pelouse, et orientée vers le Nord.

Pour contrôler la tondeuse, on lui envoie une séquence simple de lettres. Les lettres possibles sont « D », « G » et « A ». « D » et « G » font pivoter la tondeuse de 90° à droite ou à gauche respectivement, sans la déplacer. « A » signifie que l'on avance la tondeuse d'une case dans la direction à laquelle elle fait face, et sans modifier son orientation.

Si la position après mouvement est en dehors de la pelouse, la tondeuse ne bouge pas, conserve son orientation et traite la commande suivante.
On présuppose que la case directement au Nord de la position (x, y) a pour coordonnées (x, y+1).

#### Format du fichier 

Pour programmer la tondeuse, on lui fournit un fichier d'entrée construit comme suit :

 - La première ligne correspond aux coordonnées du coin supérieur droit de la pelouse, celles du coin inférieur gauche sont supposées être (0,0)
 - La suite du fichier permet de piloter toutes les tondeuses qui ont été déployées. Chaque tondeuse a deux lignes la concernant :
   - la première ligne donne la position initiale de la tondeuse, ainsi que son orientation. La position et l'orientation sont fournies sous la forme de 2 chiffres et d’une lettre, séparés
par un espace 
   - la seconde ligne est une série d'instructions ordonnant à la tondeuse d'explorer la pelouse. Les instructions sont une suite de caractères sans espaces.

Chaque tondeuse se déplace de façon séquentielle, ce qui signifie que la seconde tondeuse ne bouge que lorsque la première a exécuté intégralement sa série d'instructions.
Lorsqu'une tondeuse achève une série d'instruction, elle communique sa position et son orientation.

### OBJECTIF

Concevoir et écrire un programme dans un des langages suivants : Java, Scala, Kotlin, JavaScript, Typescript, Python, Go (Si vous souhaitez utiliser un autre langage, bien le valider avant auprès de votre chargé de recrutement)
Ce programme devra implémenter la spécification ci-dessus et passer le test ci-après. 

### TEST

Le fichier suivant est fourni en entrée :

    5 5
    1 2 N
    GAGAGAGAA
    3 3 E
    AADAADADDA

On attend le résultat suivant (position finale des tondeuses) :

    1 3 N
    5 1 E

NB: Les données en entrée sont injectées sous forme de fichier

## Utilisation

Le projet utilise Gradle pour la génération. 

### Sous Linux

 - Lancement des tests

    ./gradlew test
    

 - Création d'une archive ZIP contenant l'applicatif. Le fichier ZIP est déposé dans build\distributions\app.zip.
   Pour lancer l'application, il suffit de lancer 'app' situé dans le répertoire 'bin' fraichement décompressé.
   
    ./gradlew assembleDist


 - Execution de l'application, avec demande d'affiche de l'aide
   
    ./gradlew run --args="--help"


 - Execution de l'application avec la locale par défaut, avec le fichier de test 
   
    ./gradlew run --args="--locale=fr --file=src/test/resources/test.lpf"
    

 - Execution de l'application en francais, avec le fichier de test 
   
    ./gradlew run --args="--locale=fr --file=src/test/resources/test.lpf"

### Sous Windows

 - Lancement des tests

    .\gradlew.bat test
    

 - Création d'une archive ZIP contenant l'applicatif. Le fichier ZIP est déposé dans build\distributions\app.zip.
   Pour lancer l'application, il suffit de lancer 'app.bat' situé dans le répertoire 'bin' fraichement décompressé.
   
    .\gradlew.bat assembleDist


 - Execution de l'application, avec demande d'affiche de l'aide
   
    .\gradlew.bat run --args="--help"


 - Execution de l'application avec la locale par défaut, avec le fichier de test 
   
    .\gradlew.bat run --args="--locale=fr --file=src/test/resources/test.lpf"
    

 - Execution de l'application en francais, avec le fichier de test 
   
    .\gradlew.bat run --args="--locale=fr --file=src/test/resources/test.lpf"
   
   
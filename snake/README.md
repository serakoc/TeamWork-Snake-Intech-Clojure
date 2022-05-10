Projet développé par :

Abdel Rahim DEBECHE
Idriss SAGARA
Sami CHIBAH
Serhat KARAKOC

# Snake
Un jeu Clojure "Snake". Les règles sont simples - Mangez autant de granulés vertes que possible sans vous heurte à vous même. Utilisez les touches fléchées pour déplacer votre serpent. Votre score est la longueur de votre serpent. Si vous vous heurtez vous-même, vous réinitialiserez.

## Compiler 
Cette application peut être exécutée de 2 façons :

* Une application Java précompilée sous forme de fichier jar autonome.
Pour le compiler, tapez : `lein do clean, compile, uberjar`. Votre exécutable se trouvera dans le dossier "target".

* Une application exécutable lein. Tapez simplement `lein run`.

* Pour l'application web : `lein cljsbuild once`

Pour ce projet, nous avons utilisé la bibliothèque d'animation 2D/3D "quil"
Elle permet la création de dessin et d'animation :
http://www.quil.info/
Elle permet aussi de modifier le sketch "à chaud" sans avoir à re-ouvrir celui-ci.

La bibliotheque ClojureScript à été implémenter, le but étant de fournir le jeu sur un navigateur -> compilation du clojure en javascript


1 - Socle (10 pts)

Utilisation raisonnée des éléments de Clojure : 

    fonctions -> OK
    variables locales -> OK
    récursivité -> OK
    collections (structures de données) -> OK
    références (Atom ou Agent) -> OK

2 - Elements avancés (8 pts)

En fonction de votre projet, un ou plusieurs éléments parmi :

    programmation concurrentielle
    application web -> OK 
    intégration d'éléments de Java (par exemple pour l'interface utilisateur graphique) -> OK
    architecture client serveur 
    IA /aide à la décision
    ... 

3 - Projet (4 pts)

    Qualité et documentation du code -> OK
    Tests unitaires
    Utilisation de Leiningen -> OK
    Originalité et intérêt du projet -> ?
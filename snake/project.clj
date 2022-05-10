;; Définition du projet :
;; - sa description
;; - ses dependances 
;; - la location du point de depart de l'application
;; - les fichiers exclues lors de la création d'une archive jar
;; - la location des différentes sources
;; - l'ajout d'un lien, de plugins ou de la license reste possible


(defproject
  snake "0.1.0-SNAPSHOT"
  :description "Snake game in Clojure"
  :url "https://github.com/intech"
  :license {:name "GNU GPL v3 ou toute version ultérieure"
            :url "https://www.gnu.org/licenses/#GPL"}

  :dependencies [[org.clojure/clojure "1.7.0"]
                 [org.clojure/clojurescript "0.0-3308"]
                 [quil "2.2.6"]]

  :main snake.launcher

  :jar-exclusions [#"\.swp|\.swo|\.DS_Store"]

  :profiles {:uberjar {:aot :all}
             :dev {:plugins [[lein-cljsbuild "1.1.8"]
                             [org.clojure/clojurescript "1.11.4"]]}
             :cljs {:plugins [[lein-cljsbuild "1.1.8"]] }}

  :source-paths ["src/clj" "src/cljc"]

  :clj {:builds [{ :source-paths ["src/clj" "src/cljc" "test"] }]}

  :cljsbuild {:builds [{ :source-paths ["src/clj" "src/cljc"]
                        :compiler { :output-to "resources/public/js/snake.js"
                                   :optimizations :advanced
                                   :pretty-print true}}]})

;; Définition du namespace
(ns snake.core
  ;; assignation des différentes dépendances du namespace
  (:require [quil.core :as q #?@(:cljs [:include-macros true])]
            [quil.middleware :as m]))

;; definition de la taille du jeu
;; Nombre de case sur l'abscisse et sur l'ordonnée
(def world { :width 50 :height 50 :food-amount 100 })


;;Generation de la nourriture n'importe ou sur 
;;la carte entre 0 et la variable associé à world
(defn gen-food [] [(rand-int (world :width)) (rand-int (world :width))])


;;Ajoute une unité de nourriture tant que le "montant" entrée est inférieur à "initial"
(defn replenish-food [initial amount]
  (loop [food initial] (if (>= (count food) amount) food (recur (conj food (gen-food))))))


(defn wrap [i m]
  (loop [x i] (cond (< x 0) (recur (+ x m)) (>= x m) (recur (- x m)) :else x)))


(defn grow-snake [{:keys [snake velocity] :as state}]
  (let [[px py] (map + (peek snake) velocity)]
    (assoc state :snake (conj snake [(wrap px (world :width)) (wrap py (world :height))]))))


(defn eat [{:keys [snake food] :as state}]
  (if-let [pellet (food (peek snake))]
    (-> state (update :food disj pellet))
    (-> state (update :snake subvec 1))))


(defn reset? [{:keys [snake] :as state}]
  (if (apply distinct? snake)
    state
    (assoc state :snake [(peek snake)])))

;;Definition de la configuration 
(defn setup []
  (do (q/smooth)
      (q/frame-rate 30);; 30 images par secondes
      {:snake [[50 50]] :velocity [1 0] :food (replenish-food #{} (world :food-amount))}))

;; Dessine le fond, la nourriture et le serpent
(defn draw [{ :keys [snake food] }]
  (let [w (/ (q/width) (world :width))
        h (/ (q/height) (world :height))]
    (do
      (q/smooth)
      (q/stroke-cap :round)
      (q/stroke-join :round)
      (q/background 189 189 189)

      (q/fill 90 140 100)
      (q/stroke 90 140 100)
      (doseq [[x y] food](q/rect (* w x) (* h y) w h)) ;;Dessine la nourriture 

      (q/fill 227 119 57)
      (q/stroke 227 119 57)
      (doseq [[x y] snake](q/rect (* w x) (* h y) w h));;Dessine le serpent 

      (q/fill 0 0 0)
      (q/text (str "Score: " (count snake)) 10 15))));;Modifie le score


;; fonction permettant l'interpretation des touches fléchées

(defn launch-sketch [{:keys[width height host]}]
  (q/sketch
    :title "Snake"
    :setup setup ;;definition de la configuration
    :update #(-> % grow-snake eat (update :food replenish-food (world :food-amount)) reset?)
    :draw draw ;;fonction de dessin 
    :key-pressed ;;interpretation des touches fléchées (37-40)
    (fn [{ :keys [velocity] :as state} { :keys [key-code] }]
      (case key-code
        37 (if (not= [1 0] velocity) (assoc state :velocity [-1 0]) state)
        39 (if (not= [-1 0] velocity) (assoc state :velocity [1 0]) state)
        38 (if (not= [0 1] velocity) (assoc state :velocity [0 -1]) state)
        40 (if (not= [0 -1] velocity) (assoc state :velocity [0 1]) state)
        state))
    :middleware [m/fun-mode] ;;applique le middleware fun-mode en même temps que la fonction draw
    :size [width height] ;; recupere la taille de la fenetre via les paramètres de la fonction
    #?@(:cljs [:host host])))

#?(:cljs (defn ^:export launch-app[host width height]
           (launch-sketch { :width width :height height :host host})))

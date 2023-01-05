# java-project

## Jak uruchomić aplikację 

Projekt uruchamia sie z wykorzystaniem Gradle'a, gdzie główną klasą inicjującą jest klasa World.

### Dodatkowe informacje odnoście projektu 

Początkowa konfiguracja świata jest wczytywana po uruchomieniu aplikacji. Chcąc zmienić rozmiary mapy, liczbę zwierząch należy zmienić wartości w pliku configuration.json znajdującym się w katalogu resources. 

Dane symulacji po każdym dniu są zapisywane do pliku csv również w katalogu resources w formacie dzień, liczba zwierząt, liczba roślin, liczba wolnych pozycji na siatce. 



# Plan projektu 

## Szkic ogólny 

Aplikacja będzie wyświetlała nam albo barwy zwierząt (i i ch energii) i roślin lub będzie wyświatlała za pomocą obrazków, które będą się zmieniać w zależności od warunków istnienia. 

## Klasa Plant 

Bedzie zawiarała getery, które pobiorą pozycję rośliny oraz jej energię (jeśli się na nią zdecydujemy). Klasa będzie posiadała również wyświetlanie na mapie

## Klasa animal 

Bedzie posiadała: 
- energię,
- pozycję, 
- geny,
- informaję z którego geny skorzysta w następnym dniu, 
- listę aktualnych genów dla zwierzęcia,
- bedzie posiadała obserwatora, 
- ile czasu żyje zwierzątko,


## Klasa Genes

Ta klasa będzie odpowiadała za pobranie genów rodziców oraz zwrócenie nowego zwierzątka na mapę. Dodatkowo klasa bęzie odpowiedzialna za mutację nowo narodzonego zwierzątka. 

## Klasa Vector2d 

Odpowiada za porusznie się zwierzęcia. Podobnie zdefiniowania jak na zajęciach, 

## Klasa Map 

Tutaj będą dwa warianty mapy, które będą wybierane podczas startu symulacji (jeśli ma tak być jak myślimy, patrz pytanie)

Bezdzie również klasa Engine, która będzie odpowadała za symulacje danego dnia (podobnie jak na laboratoriach)
W foldrze world będą klasy odpowiedzialne za wyświetlenie menu aplikacji (GUI), wczytywanie konfiguracji oraz zapis istniejącej konfiguracji mapy do pliku CSV. 


# Wskazówki 

## Trello do projektu w celu organizacji 

## eXtreme programing 
 
 Takie fizyczne lub discordowe programowanie 
 
 Jeden klepie drugi się patrzy.


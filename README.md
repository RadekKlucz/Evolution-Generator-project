# java-project

Treść:
[strona projektu](https://github.com/apohllo/obiektowe-lab/tree/master/proj1)

# Oba warianty mapy + Toksyczne trupy + Lekka korekta + Nieco szaleństwa 
 
# Punktacja 16/16

Punkty przyznawana na końcu w tym dwa punkty przed świętami 

# Za tydzień do zrobienia
- Zaprojektowanie za tydzień, rozrysowanie, zaklepanie funkcji, która zaklepuje na 2 pkt za tydzień 21.12.2022

# Ważne daty 

-  oddanie projektu: 02.01.2023, 23:59 +- jakiś poślizg,
- 04.01.2023 wprowadzenie do kolejnego projektu,
- 11.01.2023 - obrona projektu.

# Darwin World (Algorytmy ewolucyjne) 

1. Tworzenie populacji,
2. Selekcja (dobór naturalny),
3. Krzyżowanie,
4. Mutacja.

Przykład: podział z godzinami działa identycznie jak powyższy algorytm 

## Zwierzątko sklłada się z: 
- pozycji,
- orientacja (również na skos będzie potrzebne np. NW),
- energia (jak pasek życia, 0 energii umiera),
- genotyp (ciąg genow, czyli numerki z orientacji poukładanie w jakiś sposób, 
  każdy numerek będzie nam mówił jak zwierzątko się porusza).
  
Zwierzątko się rozmnaża, ale jest tylko jedno, poświęcienie w imię ewolucji może zostać wykonanane. 

Mutacja jest losowa podczas tworzenia dziecka 

## Trawa

# Model aplikacji 

- Wczytujemy plik, 
- Nastepnie klikamy przycisk start i symulacja w nowym okienku startuje,
- Możemy zatrzymać tą symulację, 
- Kliknąć na zwierzątko i wyświetlić jego parametry, 
- Chcemy to zapisywać do pliku CSV, 

Możemy stworzyć od nowa nie bazując na poprzednim


max 16 pkt w tym 2 pkt przed świętami
min 8 pkt uzyskać?
(rozrysuj napisz jakąś funkcję)
deadline 3 stycznia 23:59
11.01 2023 obrona

atrybuty zwierzątka:
-pozycja (x,y)
-orientacja 8 kierunków (oznacza np N=0 i wprawo plus 1)
-energia (pasek życia) np .(15/20)
-genotyp ciąg genów, numerki orientacji ułożone w jakiś sposób długość ustalana na początku w specyfikacji

żeby się poruszać aktywujemy pierwszą kratkę genotypu, dziń się kończy pierwszy genotyp zużytu używamy drugiego, jak dojdziemy na koniec to wracamy na początek.

po ruchu zwierząt patrzymy czy na trawie (zwierze je trawa znika i dodaje się energię do góry)
gdy więcej zwierząt na jednym polu z trawą patrz specyfikacja

jak zwierzęta pojedzą

to ostatnia faza dnia rozmnażanie 
min 2 zwierzątka na 1 polu
zwierzę 1         zwierzę 2
energia 15                  20
genotype 220173        117032

rodzi się zwierze
pozycja rodzi się w tym samym polu
orientacja domyślnie
energia (każde rozmnożenie np 5 energii)
 każde z rodziców traci po 5 energii
dziecko ma 10 energii
genotyp dziecka patrzymy ile energii ma każde z rodziców i tyle procentowo genów
losujemy połówkę i bierzemy część genów
227032
następnie mutacja
bierzemy losowe geny dziecka i zmieniamy

kilka plików z konfiguracją
przycisk wczytaj  i uruchamia się mapa 
uruchom n symulacji
każdą symulacje możemy zatrzymać i podglądnąć co się dzieje (np statystyki) 
można kliknąć w zwierzątko i wyświetlić atrybuty informacje
można zapisać zrzut z każdej symulacji w pliku csv



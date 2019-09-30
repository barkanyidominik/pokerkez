# Pókerkéz
A program egy vagy több kevert pakliból képes meghatározni a pókerkezet és annak értékét. Ehhez növekvő sorrendbe állítja a kártyákat és egyetlen ciklussal bejárja azt. Közben információkat gyűjt róla.

## Pókerkéz meghatározása
A pókerkéz meghatározásához a következő három információt gyűjtjük:
* Milyen elosztásban vannak az azonos kártyák a kézben. (magas lap, pár, két pár, drill, full, póker)
* A kártyák egymás után következnek-e? (sor)
* A kártyák azonos színűek-e? (szín)

Ebből a három információból meghatározható, hogy mi a pókerkéz.

#### Kártyák elosztása
Mivel a kártyák rendezve vannak, ezért mindig azt figyeljük, hogy a jelenlegi lap megegyezik-e az előtte lévővel.
Ehhez két változót használunk. Az egyik az aktuális egymás mellett lévő lapokat számlálja, míg a másikban egy olyan szám található, ami tartalmaz minden információt az azonos lapok felosztásáról.

Az egyszerűség és a hatékonyság kedvéért megtehetjük, hogy minden alkalommal egy változóhoz adjuk azt a számot, amit éppen számláltunk.

Például:
```
[ 4 8 K K A ] (Pár) kártyák esetén az eredmény 2. 

[ 7 7 10 10 Q ] (Két pár) kártyák esetén az eredmény 2 + 2 = 4.

[ 4 4 4 8 Q ] (Drill) kártyák esetén az eredmény 3.
 
[ 3 3 J J J ] (Full) kártyák esetén az eredmény 2 + 3 = 5. 

[ Q Q Q Q K ] (Póker) kártyák esetén az eredmény 4.
```
Viszont ez sajnos nem elég, mert nem egyértelmű. A két pár és a póker is négyet adna eredményül.
Így nem tudnánk a ciklus után meghatározni, hogy két párunk vagy pókerünk van.

<br>

Emeljünk négyzetre!

```
[ 4 8 K K A ] (Pár) kártyák esetén az eredmény 2^2 = 4. 

[ 7 7 10 10 Q ] (Két pár) kártyák esetén az eredmény 2^2 + 2^2 = 8.

[ 4 4 4 8 Q ] (Drill) kártyák esetén az eredmény 3^2 = 9.
 
[ 3 3 J J J ] (Full) kártyák esetén az eredmény 2^2 + 3^2 = 13. 

[ Q Q Q Q K ] (Póker) kártyák esetén az eredmény 4^2 = 16.
```

Így egyértelműen meghatározható a számból, hogy mi a pókerkéz felosztása.

## Érték meghatározása
Az érték meghatározása a következő három módszerrel történik.

* A ciklus ideje alatt meghatározzuk a pókerkéz értékét. (Pár, Két pár, Drill, Full, Póker)
* A ciklus után vesszük a kártyák legutolsó elemét. (Magas lap, Sor, Szín, Színsor, Royal Flush)
* A ciklus után vesszük a kártyák középső elemét. (Full)

Az első pont nem kíván további magyarázatot.
A második pontnál a növekvő sorrendet kihasználva a legutolsó lapunk lesz a kívánt érték.
A harmadik pontnál a Full-hoz keressük az értéket. A Full egy Drillből és egy Párból áll, és a Drill része határozza meg az értékét.
A Drill három lapból áll, míg a pár kettőből. Ez a rendezés miatt csak úgy fér el 5 helyen, hogy a középén a Drill értéke van.


## Build
```
mvn clean package
```

## Futtatás
Egy pakli esetén
```
java -jar target/pokerkez-1.0-SNAPSHOT.jar
```

Több pakli esetén paraméterben adjuk meg a kívánt értéket.
```
java -jar target/pokerkez-1.0-SNAPSHOT.jar 2
```


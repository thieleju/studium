-- Formulieren Sie die SQL-Anfragen zu folgenden Fragen
-- Die Seite  w3schools kann als Hilfe dienen 


-- Liste die Namen aller Cocktails auf
SELECT c.name
FROM cocktails c;

-- Wie viele Cocktails befinden sich in der Cocktaildatenbank?
SELECT COUNT(c.name) AS "Anzahl Cocktails"
FROM cocktails c;

-- Liste die Namen aller Cocktils in der Reihenfolge, in der sie in die Datenbank aufgenommen wurden. 
SELECT c.name,
  c.aufgenommen as aufnahmedatum
FROM cocktails c
ORDER BY c.aufgenommen ASC;

-- Liste die Namen aller nichtalkoholischen Zutaten (in alphabetischer Reihenfolge)
select z.name
from zutaten z
where z.alkohol = 0
order by z.name;

-- Welches ist die Zutat mit dem höchsten Alkoholgehalt? Wieviel Alkohol enthält die Zutat?
select z.name,
  max(z.alkohol)
from zutaten z;

-- Welches sind die 5 alkoholhaltigsten Zutaten? 
select z.name,
  z.alkohol
from zutaten z
order by z.alkohol desc
limit 5;

-- Welche Zutaten starten mit einem A?
select z.name
from zutaten z
where z.name like "A%";

-- Wie hoch ist der durchschnittliche Alkoholgehalt der alkoholischen Zutaten?
select AVG(z.alkohol) as "Durchschnittlicher Alkoholgehalt"
from zutaten z
where z.alkohol > 0;

-- welche fruchtigen alkoholischen Zutaten gibt es? 
select z.name
from zutaten z
where z.geschmack like "%fruchtig%";

-- welche Lieferanten liefern Rum?
select l.name as "lieferant",
  z.name as "zutat"
from lieferanten l
  join liefert li on li.lieferant = l.l_id
  join zutaten z on z.z_id = li.zutat
where z.name like "%rum%"
group by l.name;

-- Welche Zutaten beinhaltet ein Caipirinha?
select z.name as "Zutaten"
from zutaten z
  join beinhaltet b on z.z_id = b.zutat
  join cocktails c on c.c_id = b.cocktail
where c.name = "Caipirinha"
group by z.name;

-- Wie viele Caipirinha-Rezepte gibt es in der Datenbank?
select count(c.name) as "Caipi Rezepte Anzahl"
from cocktails c
where c.name like "%aipirinha%";

-- Liste für jeden cocktail die Anzahl der Zutaten, mit denen dieser Cocktail gemixt werden kann
select c.name as "Cocktail",
  count(distinct z.name) as "Anzahl Zutaten"
from zutaten z
  join beinhaltet b on z.z_id = b.zutat
  join cocktails c on c.c_id = b.cocktail
group by c.c_id; -- c.name

-- Liste für jeden Cocktail die Anzahl der Zutaten auf, 
-- mit denen der Cocktail gemixt werden kann. 
-- Liste nur cocktails mit mehr als 5 Zutaten auf
select c.name as "Cocktail",
  count(distinct z.name) as "Anzahl Zutaten"
from zutaten z
  join beinhaltet b on z.z_id = b.zutat
  join cocktails c on c.c_id = b.cocktail
group by c.c_id
having count(distinct z.name) > 5;

-- Welche Lieferanten liefern Zutaten für Caipirinha?
select l.name as "lieferant"
from lieferanten l
  join liefert li on li.lieferant = l.l_id
  join zutaten z on z.z_id = li.zutat
  join beinhaltet b on b.zutat = z.z_id
  join cocktails c on c.c_id = b.cocktail
where c.name like "%aipirinha%"
group by l.name;

-- Liste alle Lieferanten mit ihren Ansprechpartnern
select l.name as "Lieferant",
  k.name as "Ansprechpartner"
from lieferanten l
  join kontakte k on k.kontakt_fuer = l.l_id;

-- Löschen Sie den Kontakt für den Lieferanten mit Nummer 75
delete from kontakte
where kontakte.kontakt_fuer = 75;

-- Liste alle Lieferanten, ggf mit ihren Ansprechpartnern
select l.name as "Lieferant",
  k.name as "Ansprechpartner"
from lieferanten l
  left join kontakte k on k.kontakt_fuer = l.l_id;
@startuml
top to bottom direction

state Veranstaltung_Anpassen {
  [*] --> A_Alle
  A_Alle --> A_Datum_ab : Dreh +1
  A_Datum_ab --> A_Alle : Dreh -1

  A_Datum_ab --> A_Uhrzeit_ab : Dreh +1
  A_Uhrzeit_ab --> A_Datum_ab : Dreh -1

  A_Uhrzeit_ab --> A_Uhrzeit_bis : Dreh +1
  A_Uhrzeit_bis --> A_Uhrzeit_ab : Dreh -1

  A_Uhrzeit_bis --> A_Datum_bis : Dreh +1
  A_Datum_bis --> A_Uhrzeit_bis : Dreh -1

  A_Datum_bis --> A_Alle : Dreh +1
  A_Alle --> A_Datum_bis : Dreh -1

  A_Alle --> S_Alle : Ok
  S_Alle --> A_Alle : Zurück

  state S_Alle {
    [*] --> Alle
    Alle --> Heizkörper : Dreh +1
    Heizkörper --> Alle : Dreh -1

    Heizkörper --> Fußbodenheizung : Dreh +1
    Fußbodenheizung --> Heizkörper : Dreh -1

    Fußbodenheizung --> Warmwasser : Dreh +1
    Warmwasser --> Fußbodenheizung : Dreh -1

    Warmwasser --> Alle : Dreh +1
    Alle --> Warmwasser : Dreh -1

    Alle --> [*] : Ok, Zurück
    Heizkörper --> [*] : Ok, Zurück
    Fußbodenheizung --> [*] : Ok, Zurück
    Warmwasser --> [*] : Ok, Zurück
  }

  A_Datum_ab --> S_Datum_ab : Ok
  S_Datum_ab --> A_Datum_ab : Zurück

  state S_Datum_ab {
    [*] --> Datum_Tag
    Datum_Tag --> Datum_Tag : Dreh +1, Dreh -1
    Datum_Tag --> Datum_Monat : Ok
    Datum_Monat --> Datum_Tag : Zurück
    Datum_Monat --> Datum_Monat : Dreh +1, Dreh -1
    Datum_Monat --> Datum_Jahr : Ok
    Datum_Jahr --> Datum_Monat : Zurück
    Datum_Jahr --> Datum_Jahr : Dreh +1, Dreh -1
    Datum_Jahr --> [*] : Ok
    Datum_Tag --> [*] : Zurück
  }

  A_Uhrzeit_ab --> S_Uhrzeit_ab : Ok
  S_Uhrzeit_ab --> A_Uhrzeit_ab : Zurück

  state S_Uhrzeit_ab {
    [*] --> Uhrzeit_Stunden
    Uhrzeit_Stunden --> Uhrzeit_Stunden : Dreh +1, Dreh -1
    Uhrzeit_Stunden --> Uhrzeit_Minuten : Ok
    Uhrzeit_Minuten --> Uhrzeit_Stunden : Zurück
    Uhrzeit_Minuten --> Uhrzeit_Minuten : Dreh +1, Dreh -1
    Uhrzeit_Minuten --> [*] : Ok
    Uhrzeit_Stunden --> [*] : Zurück
  }



}

@enduml

state Wochenplaner {
  [*] --> Auswahl_Montag
  Auswahl_Montag --> Auswahl_Dienstag : Dreh +1
  Auswahl_Montag --> Auswahl_Sonntag : Dreh -1
  Auswahl_Dienstag --> Auswahl_Montag : Dreh -1
  Auswahl_Dienstag --> Auswahl_Mittwoch : Dreh +1
  Auswahl_Mittwoch --> Auswahl_Dienstag : Dreh -1
  Auswahl_Mittwoch --> Auswahl_Donnerstag : Dreh +1
  Auswahl_Donnerstag --> Auswahl_Mittwoch : Dreh -1
  Auswahl_Donnerstag --> Auswahl_Freitag : Dreh +1
  Auswahl_Freitag --> Auswahl_Donnerstag : Dreh -1
  Auswahl_Freitag --> Auswahl_Samstag : Dreh +1
  Auswahl_Samstag --> Auswahl_Freitag : Dreh -1
  Auswahl_Samstag --> Auswahl_Sonntag : Dreh +1
  Auswahl_Sonntag --> Auswahl_Samstag : Dreh -1
  Auswahl_Sonntag --> Auswahl_Montag : Dreh +1

  Auswahl_Montag --> Einstellungen_Tag : Ok
  Einstellungen_Tag --> Auswahl_Montag : Zurück


  state Einstellungen_Tag {
    [*] --> Slider1 
    Slider1 --> Slider1 : Dreh +1, Dreh -1
    Slider1 --> Slider2 : Ok
    Slider2 --> Slider1 : Zurück
    Slider2 --> Slider2 : Dreh +1, Dreh -1
    Slider1 --> [*] : Zurück
    Slider2 --> [*] : Ok
  }

}





state Startbildschirm {
  [*] --> Heizkörper_WTemperatur
  Heizkörper_WTemperatur --> Einstellungen : Dreh -1
  Heizkörper_WTemperatur --> Heizkörper_ATemperatur : Dreh +1
  Heizkörper_WTemperatur --> Heizkörper_WTemperatur_Anpassen : Ok
  Heizkörper_WTemperatur_Anpassen --> Heizkörper_WTemperatur : Zurück

  Heizkörper_ATemperatur --> Heizkörper_WTemperatur : Dreh -1
  Heizkörper_ATemperatur --> Heizkörper_Wochenplaner : Dreh +1
  Heizkörper_ATemperatur --> Heizkörper_ATemperatur_Anpassen : Ok
  Heizkörper_ATemperatur_Anpassen --> Heizkörper_ATemperatur : Zurück

  Heizkörper_Wochenplaner --> Heizkörper_ATemperatur : Dreh -1
  Heizkörper_Wochenplaner --> Fußbodenheizung_WTemperatur : Dreh +1
  Heizkörper_Wochenplaner --> Heizkörper_Wochenplaner_Anpassen : Ok
  Heizkörper_Wochenplaner_Anpassen --> Heizkörper_Wochenplaner : Zurück

  Fußbodenheizung_WTemperatur --> Heizkörper_Wochenplaner : Dreh -1
  Fußbodenheizung_WTemperatur --> Fußbodenheizung_ATemperatur : Dreh +1
  Fußbodenheizung_WTemperatur --> Fußbodenheizung_Wochenplaner : Ok
  Fußbodenheizung_Wochenplaner --> Fußbodenheizung_WTemperatur : Zurück

  Fußbodenheizung_ATemperatur --> Fußbodenheizung_WTemperatur : Dreh -1
  Fußbodenheizung_ATemperatur --> Fußbodenheizung_Wochenplaner : Dreh +1
  Fußbodenheizung_ATemperatur --> Fußbodenheizung_ATemperatur_Anpassen : Ok
  Fußbodenheizung_ATemperatur_Anpassen --> Fußbodenheizung_ATemperatur : Zurück


  Fußbodenheizung_Wochenplaner --> Fußbodenheizung_ATemperatur : Dreh -1
  Fußbodenheizung_Wochenplaner --> Warmwasser_WTemperatur : Dreh +1
  Fußbodenheizung_Wochenplaner --> Fußbodenheizung_Wochenplaner_Anpassen : Ok
  Fußbodenheizung_Wochenplaner_Anpassen --> Fußbodenheizung_Wochenplaner : Zurück

  Warmwasser_WTemperatur --> Fußbodenheizung_Wochenplaner : Dreh -1
  Warmwasser_WTemperatur --> Warmwasser_Frostschutz : Dreh +1
  Warmwasser_WTemperatur --> Warmwasser_Wochenplaner : Ok
  Warmwasser_Wochenplaner --> Warmwasser_WTemperatur : Zurück

  Warmwasser_Frostschutz --> Warmwasser_WTemperatur : Dreh -1
  Warmwasser_Frostschutz --> Warmwasser_Wochenplaner : Dreh +1
  Warmwasser_Frostschutz --> Warmwasser_Frostschutz_Anpassen : Ok
  Warmwasser_Frostschutz_Anpassen --> Warmwasser_Frostschutz : Zurück

  Warmwasser_Wochenplaner --> Warmwasser_Frostschutz : Dreh -1
  Warmwasser_Wochenplaner --> M_Veranstaltung : Dreh +1
  Warmwasser_Wochenplaner --> Warmwasser_Wochenplaner_Anpassen : Ok
  Warmwasser_Wochenplaner_Anpassen --> Warmwasser_Wochenplaner : Zurück

  M_Veranstaltung --> Warmwasser_Wochenplaner : Dreh -1
  M_Veranstaltung --> M_Abwesenheit : Dreh +1
  M_Veranstaltung --> M_Veranstaltung_Anpassen : Ok
  M_Veranstaltung_Anpassen --> M_Veranstaltung : Zurück

  M_Abwesenheit --> M_Veranstaltung : Dreh -1
  M_Abwesenheit --> Einstellungen : Dreh +1
  M_Abwesenheit --> M_Abwesenheit_Anpassen : Ok
  M_Abwesenheit_Anpassen --> M_Abwesenheit : Zurück

  Einstellungen --> M_Abwesenheit : Dreh -1
  Einstellungen --> Heizkörper_WTemperatur : Dreh +1
  Einstellungen --> Einstellungen_Anpassen : Ok
  Einstellungen_Anpassen --> Einstellungen : Zurück
}




state Wochenplaner {
  [*] --> Wochentage
  Wochentage -d-> Optionen : Montag
  Optionen -u-> Wochentage : Zurück
  Optionen --> Z_BearbeitenAbUhrzeitStunden : Zeitfenster1
  Z_BearbeitenAbUhrzeitStunden --> Optionen : Zurück
  Z_BearbeitenAbUhrzeitStunden --> Z_BearbeitenAbUhrzeitMinuten : Ok
  Z_BearbeitenAbUhrzeitStunden --> Z_BearbeitenAbUhrzeitStunden : Dreh +1, Dreh -1

  Z_BearbeitenAbUhrzeitMinuten --> Z_BearbeitenAbUhrzeitStunden : Zurück
  Z_BearbeitenAbUhrzeitMinuten --> Z_BearbeitenBisUhrzeitStunden : Ok
  Z_BearbeitenAbUhrzeitMinuten --> Z_BearbeitenAbUhrzeitMinuten : Dreh +1, Dreh -1

  Z_BearbeitenBisUhrzeitStunden --> Z_BearbeitenAbUhrzeitMinuten : Zurück
  Z_BearbeitenBisUhrzeitStunden --> Z_BearbeitenBisUhrzeitMinuten : Ok
  Z_BearbeitenBisUhrzeitStunden --> Z_BearbeitenBisUhrzeitStunden : Dreh +1, Dreh -1

  Z_BearbeitenBisUhrzeitMinuten --> Z_BearbeitenBisUhrzeitStunden : Zurück
  Z_BearbeitenBisUhrzeitMinuten --> Z_BearbeitenTemperatur : Ok
  Z_BearbeitenBisUhrzeitMinuten --> Z_BearbeitenBisUhrzeitMinuten : Dreh +1, Dreh -1

  Z_BearbeitenTemperatur --> Z_BearbeitenBisUhrzeitMinuten : Zurück
  Z_BearbeitenTemperatur --> Z_BearbeitenTemperatur : Dreh +1, Dreh -1
  Z_BearbeitenTemperatur --> Gespeichert : Ok

  Optionen --> Z_HinzufügenAbUhrzeitStunden : Zeitfenster hinzufügen
  Z_HinzufügenAbUhrzeitStunden --> Optionen : Zurück
  Z_HinzufügenAbUhrzeitStunden --> Z_HinzufügenAbUhrzeitMinuten : Ok
  Z_HinzufügenAbUhrzeitStunden --> Z_HinzufügenAbUhrzeitStunden : Dreh +1, Dreh -1

  Z_HinzufügenAbUhrzeitMinuten --> Z_HinzufügenAbUhrzeitStunden : Zurück
  Z_HinzufügenAbUhrzeitMinuten --> Z_HinzufügenBisUhrzeitStunden : Ok
  Z_HinzufügenAbUhrzeitMinuten --> Z_HinzufügenAbUhrzeitMinuten : Dreh +1, Dreh -1

  Z_HinzufügenBisUhrzeitStunden --> Z_HinzufügenAbUhrzeitMinuten : Zurück
  Z_HinzufügenBisUhrzeitStunden --> Z_HinzufügenBisUhrzeitMinuten : Ok
  Z_HinzufügenBisUhrzeitStunden --> Z_HinzufügenBisUhrzeitStunden : Dreh +1, Dreh -1

  Z_HinzufügenBisUhrzeitMinuten --> Z_HinzufügenBisUhrzeitStunden : Zurück
  Z_HinzufügenBisUhrzeitMinuten --> Z_HinzufügenTemperatur : Ok
  Z_HinzufügenBisUhrzeitMinuten --> Z_HinzufügenBisUhrzeitMinuten : Dreh +1, Dreh -1

  Z_HinzufügenTemperatur --> Z_HinzufügenBisUhrzeitMinuten : Zurück
  Z_HinzufügenTemperatur --> Z_HinzufügenTemperatur : Dreh +1, Dreh -1
  Z_HinzufügenTemperatur --> Gespeichert : Ok

  Optionen -u-> Z_Löschen : Alle Zeitfenster löschen
  Z_Löschen -d-> Optionen : Zurück
  Z_Löschen --> Gespeichert : Ok

  Optionen -u-> Z_KopierenAuf: Einstellungen für Tag kopieren
  Z_KopierenAuf -d-> Optionen : Zurück
  Z_KopierenAuf --> Z_KopierenAuf : Dreh +1, Dreh -1
  Z_KopierenAuf --> Z_Überschreiben : Ok
  Z_Überschreiben --> Z_KopierenAuf : Zurück
  Z_Überschreiben --> Gespeichert : Ok
  Gespeichert --> Optionen : nach 1s
  
}



@startuml
top to bottom direction

state Startbildschirm {
  [*] -r-> Heizkreis1
  Heizkreis1 --> Heizkreis2 : Ok, Zurück
  Heizkreis1 : Heizkörper
  Heizkreis2 --> Heizkreis1 : Zurück, Ok
  Heizkreis2 : Fußbodenheizung
  Heizkreis1 -r-> Menü : Menü
  Heizkreis2 -r-> Menü : Menü 
}

state Menü {
  [*] -r-> M_Steuerung
  M_Steuerung -> M_Status : Dreh +1
  M_Status -> M_Steuerung : Dreh -1
  M_Status -> M_Einstellungen : Dreh +1
  M_Einstellungen -> M_Status : Dreh -1
  M_Einstellungen -> M_Steuerung : Dreh +1
  M_Steuerung -> M_Einstellungen : Dreh -1

  M_Steuerung --> MM_Steuerung : Ok
  MM_Steuerung --> M_Steuerung : Zurück
  M_Status --> MM_Status : Ok
  MM_Status --> M_Status : Zurück
  M_Einstellungen --> MM_Einstellungen : Ok
  MM_Einstellungen --> M_Einstellungen : Zurück 

  
state MM_Einstellungen {
  [*] -r-> E_Sprache
  E_Sprache --> E_Fachhandwerkerebene : Dreh -1
  E_Sprache --> E_Datum : Dreh +1
  E_Datum --> E_Sprache : Dreh -1
  E_Datum --> E_Zeit : Dreh +1
  E_Zeit --> E_Datum : Dreh -1
  E_Zeit --> E_Anlage : Dreh +1
  E_Anlage --> E_Zeit : Dreh -1
  E_Anlage --> E_Helligkeit : Dreh +1
  E_Helligkeit --> E_Anlage : Dreh -1
  E_Helligkeit --> E_Werkseinstellungen : Dreh +1
  E_Werkseinstellungen --> E_Helligkeit : Dreh -1
  E_Fachhandwerkerebene --> E_Werkseinstellungen : Dreh +1
  E_Werkseinstellungen --> E_Fachhandwerkerebene : Dreh -1
  E_Fachhandwerkerebene --> E_Sprache : Dreh +1
}


state MM_Status {
  [*] -r-> S_Meldungen
    S_Meldungen: 
  }

state MM_Steuerung {
  [*] -r-> S_Heizkreis1
  S_Heizkreis1: Heizkörper
  S_Heizkreis2: Fußbodenheizung
  S_Heizkreis1 --> S_Heizkreis2 : Dreh +1
  S_Heizkreis2 --> S_Heizkreis1 : Dreh -1
  S_Heizkreis2 --> A_Warmwasser : Dreh +1
  A_Warmwasser --> S_Heizkreis2 : Dreh -1
  A_Warmwasser --> S_Abwesenheit : Dreh +1
  S_Abwesenheit --> A_Warmwasser : Dreh -1
  S_Abwesenheit --> S_Veranstaltung : Dreh +1
  S_Veranstaltung --> S_Abwesenheit : Dreh -1
  S_Veranstaltung --> S_Stoßlüften : Dreh +1
  S_Stoßlüften --> S_Veranstaltung : Dreh -1
  S_Stoßlüften --> S_Heizkreis1 : Dreh +1
  S_Heizkreis1 --> S_Stoßlüften : Dreh -1
}
@enduml
  



    
    


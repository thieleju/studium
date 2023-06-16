@startuml
top to bottom direction

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

@enduml

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
  



    
    


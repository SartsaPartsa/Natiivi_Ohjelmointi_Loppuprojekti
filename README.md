# Trivia Quiz

Tämä on yksinkertainen trivia-visailusovellus, joka on rakennettu Androidille käyttäen moderneja kehitystyökaluja. Sovellus hakee kysymyksiä avoimesta [Open Trivia DB](https://opentdb.com/) -rajapinnasta ja esittää ne käyttäjälle monivalintakysymyksinä.

## Ominaisuudet

*   Hakee 10 monivalintakysymystä kerrallaan.
*   Näyttää käyttäjän pistemäärän pelin edetessä.
*   Antaa välittömän palautteen oikeasta tai väärästä vastauksesta.
*   Mahdollistaa pelin aloittamisen alusta.
*   Sisältää info-näkymän, jossa kerrotaan sovelluksen teknisestä toteutuksesta.

## Tekninen toteutus

Projekti noudattaa MVVM (Model-View-ViewModel) -arkkitehtuurimallia.

*   **Käyttöliittymä (UI):** Rakennettu täysin **Jetpack Compose** -kirjastolla.
*   **Tilan hallinta:** **ViewModel** vastaa käyttöliittymän tilan (`UiState`) hallinnasta ja pelilogiikasta.
*   **Verkkotoiminnot:** Kysymysten haku API-rajapinnasta on toteutettu **Retrofit**-kirjastolla.
*   **Asynkronisuus:** Verkkokutsut ja muut taustatoiminnot hoidetaan **Kotlin Coroutines** -rutiineilla.
*   **Navigaatio:** Sovelluksen sisäinen navigointi on toteutettu **Jetpack Navigation-Compose** -kirjastolla.
*   **API:** [Open Trivia DB](https://opentdb.com/api.php?amount=10&type=multiple)

## Näkymät

*   **StartScreen:** Aloitusnäyttö, josta peli voidaan käynnistää tai siirtyä info-näkymään.
*   **QuizScreen:** Päänäkymä, jossa käyttäjä vastaa kysymyksiin ja näkee tuloksensa.
*   **InfoScreen:** Näyttö, joka kertoo sovelluksen ideasta ja teknisestä toteutuksesta.

## Kääntäminen ja ajaminen

1.  Kloonaa tämä repositorio.
2.  Avaa projekti Android Studiossa.
3.  Paina "Run" -nappia (vihreä kolmio) asentaaksesi ja käynnistääksesi sovelluksen emulaattorissa tai laitteessa.

Projekti on itsenäinen, eikä vaadi erillisiä API-avaimia tai konfiguraatiota.

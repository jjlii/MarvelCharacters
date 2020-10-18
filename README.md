Marvel Characters
==================

Este projecto es una aplicación Android para testear la [API de Marvel](https://developer.marvel.com/docs)

Requisitos
================== 
Antes de ejecutar el proyecto es necesario tener una clave pública y privada para poder usar la API, dichas claves los consigues registrandote en [la página de la API](https://developer.marvel.com/account)

Después de tener las claves es necesario sustituirlo en la clase constante.

<pre><code>
class Constant {
    object ConnectionUtils{
        const val PUB_KEY = "PUB_KEY"
        const val PV_KEY = "PV_KEY"
    }
}
</code></pre>
Principales librerías
======================== 

- [Kotlin](https://kotlinlang.org/)
- [Retrofit](https://square.github.io/retrofit/)
- [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)
- [Glide](https://github.com/bumptech/glide)
- [Koin](https://insert-koin.io/)
- [Mockito](https://site.mockito.org/)
- [JUnit 4](https://junit.org/junit4/)

Organización del Proyecto 
===========================

Este proyecto se ha montado a base de 4 módulos.

- App, es el módulo en donde se encuentran interfaz de usuario, los ViewModel y Koin
- Data, es el módulo en donde se encuentran las peticiones a la API usando Retrofit
- Domain, es el módulo en donde se encuentran los entities y la interfaz del DataSource
- UseCase, es el módulo en donde se encuentran los usecase

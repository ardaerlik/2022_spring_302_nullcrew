# Alien Asteroid Game - NullCrew / Demo 1

## Project Overview

</br>

### Group Members:

Arda Erlik              76108

Atakan Özkan            76227

Buğrahan Yaman          76070

Can Köz                 75192

Muhammed Ali Kerdiğe    71913

</br>

### Date: 24.04.2022
</br>

---
## How To Run The Game
For the Demo 1, there are two main methods on the project. After the demo 1, we will introduce the AlienAsteroidGame singleton class for navigating between different scenes and the program will be initiated from AlienAsteroidGame’s main method. For the expected use cases on the Demo 1, it should be run from GameView class’s main method under the com.nullcrew.UI.Views package. For the MenuView, it should be run from MenuView class’s main method under the com.nullcrew.UI.Views package.

</br>

---
## Implementation of Design Patterns

### Factory Pattern
Factory design pattern is used for generating and initializing objects in an application. We used a factory design pattern in the GameObjectFactory class. We apply a factory design pattern in GameObjectFactory that uses factory methods to create AlienAsteroid objects. Since there are subclasses of AlienAsteroid, we create all new asteroid objects of any type with GameObjectFactory.  We also use this class to initialize the paddle and the ball. 

createPaddle function is used for creating the paddle object.

createBall function is used for creating the ball object.

createAsteroids creates all of the asteroids and collects them in a list named “asteroids”.

Additionally we collect all of the game objects in a list named “list_objects”.

We also designed a private function named placeAsteroids that creates the invisible grid boundaries which is responsible for the placement of the AsteroidObjects. We create a grid like structure and this is why it is under the factory design pattern. 

</br>

### Singleton Pattern
We will use the singleton design pattern in AlienAsteroidGame.java and DBManager because we initialize it for once and there is no reason to recreate multiple times after its initialization. Singleton is used when there is no need for recreation after initialization. AlienAsteroidGame will never be stopped or recreated for any reason therefore we chose to use a singleton design pattern for AlienAsteroidGame. AlienAsteroidGame class works as a navigation controller of the application and the same instance will be used on the same software cycle.

</br>

---
## Implementation of Model-View Seperation
Our program uses the MVC (Model-View-Controller) pattern effectively.

- Model is responsible for creating the game object classes. It is related to logic handling of domains.
- Controller handles the game logic and is a bridge between the model and UI (View). It is responsible for notifying the model according to the inputs from View.
- View is simply the visual interface of our program.

It is important to note that Model and View do not communicate directly with each other. It is the controller that controls the data flow between the Model and View.

When interacting, the program should establish the following principle:

- Controllers should act as an intermediary to provide model and view interaction.
- Model and view should not interact directly.

Model and view structures cannot interact with each other by communicating directly. In order for this interaction to take place and for the model and view structures to do their duties, "controller" help should be needed.

If we want to examine it through an example use case, we can explain this situation as follows. 

- User creates a signal via the Move the Paddle with the help of the key event on the view.
- With the help of this signal, the paddleMoved(MoveDirection d) method is activated and supports the Paddle Model method.
- As a result, the process is completed by updating the view with a timer.

</br>

---

## Demonstration of Building Mode
[https://youtu.be/GS_1FLKvces](https://youtu.be/GS_1FLKvces)

</br>

---
## License
    MIT License

    Copyright (c) 2022 NullCrew

    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:

    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.

    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

---
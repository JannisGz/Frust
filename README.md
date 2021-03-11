# Frust

<strong>Frust</strong> is a phone game developed in Android Studio for android devices. The core
concept of the game is to test the players reaction speed and quick decision making skills. This
application is a remake of a group project, originally developed during the first term as a
computer science student at the Technical University Dresden.

## Table of Contents

[1. Introduction](#introduction)  
[2. Getting Started](#gettingStarted)  
[3. About](#about)  

<a name="introduction"/></a>
## Introduction
<img src="https://github.com/JannisGz/Frust/blob/master/doc/level1.png" width="300" alt="Screenshot for level 1" float="center">

Frust is all about making correct decisions, coupled with an ever increasing number of options and
decreasing decision making time. There are many different levels, but the core concept is the same
for all of them: Green and red shapes will appear on the screen and move around or change their
size. Tapping the green shapes (targets) will increase the players score, while hitting a red
shape terminates the game. The faster a green shape is tapped the more points are awarded to the
player.

A detailed description of the game loop and the different levels can be found in the <em>About</em>
section of this readme.

<a name="gettingStarted"/></a>
## Getting started

The main directory contains a <em>frust.apk</em> file. Move this file to an android device and
execute it to start the installation process. The device might ask for permission for this
step, because the file was not downloaded using the app store and is therefore recognized as a
potential threat.

Alternatively, by cloning this entire repository, the application can also be started by emulating
it using Android Studio.

<a name="about"/></a>
## About

The game consists of three different types of levels which are repeated as long as the player can
"survive". The goal for every level is the same: Tap the green target as fast and often  as possible
and avoid hitting the red shapes. Tapping a green shape will increase the players score. Hitting a
red shape will lead to a 'Game Over' screen. If the players current score is high enough, he is
prompted to enter his name in a Highscore list.

Before each level, the current level number and a short description is displayed.

#### Shrinking and Growing

<img src="https://github.com/JannisGz/Frust/blob/master/doc/level1.png" width="300" alt="Screenshot for level 1" float="center">

This is the first level of the game and the 'original' idea behind Frust. A green circle appears at
a random position on the screen. When this target is tapped, it will disappear and a new target will
be generated at another random location. At the same time red circles will start to appear at other
locations of the screen. Overtime more and more red circles will be added.

At the same time both green and red circles will change their size constantly. The green circle
shrinks until it is no longer visible (resulting in a 'Game Over'). The red circles will
increase in size, until they fill the whole screen. Each time the green target is tapped both types
are reset to their starting size and their locations are randomized. However, successfully hitting
the target will also result in the game speed increasing, making the shapes grow or shrink even
faster.

#### Falling ball

<img src="https://github.com/JannisGz/Frust/blob/master/doc/level2.png" width="300" alt="Screenshot for level 2" float="center">


In the second level, a green ball "falls" from the top of the screen towards the bottom. The earlier
the player can tap it, the more points are awarded. If the ball can not be "caught" before it hits
the bottom of the screen, it will simply reappear at the top of the screen and fall once again.

At the same time red rectangles will move horizontally over the screen. Some move from right to left
and vice versa. They completely cover the ball when both shapes overlap. Every time the ball is
successfully "caught", the speed of both the falling ball and the other shapes increases.

#### Speed tapping

<img src="https://github.com/JannisGz/Frust/blob/master/doc/level3.png" width="300" alt="Screenshot for level 3" float="center">


This last level includes only a single green circle, that will slowly increase in size. It is meant
as a bonus round for completing the other levels. However, how many bonus points are awarded to the
player is solely based on how fast the green circle can be tapped. This time the target will not
disappear when tapped, but simply increase even more in size. The bigger the target gets, the more
points are gained with each tap.

This level will end automatically after a few seconds and restart the level cycle.
<p float="center">
  <img src="https://github.com/JannisGz/Frust/blob/master/doc/level1desc.png" width="300" alt="Screenshot of the interlude screen before level 1">
  <img src="https://github.com/JannisGz/Frust/blob/master/doc/gameover.png" width="300" alt="Screenshot of the Game Over Screen">
</p>

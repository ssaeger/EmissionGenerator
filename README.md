EmissionGenerator
=================
![UI](http://i.imgur.com/dKekPmZ.png)

The EmissionGenerator is a small tool that generates some random data of the following format:

> "0","3","14","2","14","1","0","12","18","11","14","0","13","12","13","3","13","1","2","3","20","0","0","12","11","0","12",...

This data is created in the following way:

1. The tool generates internally a sequence of vectors (these vectors should represent the movement of an individual)
2. These vectors will be quantized into a finite number of 'emissions' (at the moment 21)
3. These emissions can be used to work with Hidden Markov Models for example.

The generated sequences are dependent of the selected 'Model' and the 'Confounder' which defines the amount of noise that is present in the sequence. 

Each model contains of a class with the process that creates the movement vectors.
At the moment there are the following models implemented:
- EatModel
- MoveModel

The EmissionGenerator is distributed under the GNU Lesser General Public License (LGPL).

Dependencies
============

The tool uses the following libraries:
- JFreeChart 1.0.14 (LGPL) http://www.jfree.org/jfreechart/
- JCommons 1.0.17 (LGPL) http://www.jfree.org/jcommon/
- JGoodies Forms 1.3.0
- Uncommons Maths 1.2.3 (Apache Software Licence, Version 2.0) http://maths.uncommons.org/

(These libraries are inside the project.)

Run it
======

You need the Oracle JRE 7 or higher

Then you just have to run the EmissionGenerator.jar

(e.g. on a system with ubuntu use the following command: EmissionGenerator/EmissionGenerator.jar)

Contributing
============

Feel free to create new models or improve the tool in any way you can think of. Currently, I'm not planning to develop the tool further, but I will take care of the project by handling pull requests. You can even open issues and if I have the time I will look into it, but I cannot promise it. 

Architecture
============
![Classdiagram](http://i.imgur.com/0c0nCP5.png)

Due to this structure it is easy to integrate new models for the movement vector generation.

TODO
====

It would be nice if the 'EmissionsequenceModel' will be split up into a similar structure as the 'Movementmodel', because in that case the way of trasforming the movement vectors into emissions can be changed much easier.

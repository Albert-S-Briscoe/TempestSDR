TempestSDR
=============

This project is a software toolkit for remotely eavesdropping video monitors using a Software Defined Radio (SDR) receiver. It exploits compromising emanations from cables carrying video signals.

Raster video is usually transmitted one line of pixels at a time, encoded as a varying current. This generates an electromagnetic wave that can be picked up by an SDR receiver. The software maps the received field strength of a pixel to a gray-scale shade in real-time. This forms a false colour estimate of the original video signal.

The toolkit uses unmodified off-the-shelf hardware which lowers the costs and increases mobility compared to existing solutions. It allows for additional post-processing which improves the signal-to-noise ratio. The attacker does not need to have prior knowledge about the target video display. All parameters such as resolution and refresh rate are estimated with the aid of the software. 

The software consists of a library written in C, a collection of plug-ins for various Software Define Radio (SDR) front-ends and a Java based Graphical User Interface (GUI). It is a multi-platform application, with all native libraries pre-compiled and packed into a single Java jar file.

What Is This Fork?
------------

This fork is mainly intended for Linux, but some plugins might compile on windows, and some with some minor tweaks.

Linux compatible plugins included:

 * RTL-SDR
 * Airspy
 * HackRF
 * UHD
 * Rough SDRPlay API v3 plugin (not included yet)

I'm only able to test RTL-SDR and SDRPlay.

#### SDRPlay support

Currently, the plugin will just choose the first device it finds.

I have no idea if anything other than the RSP1A will work, but the RSP1 probably will. This is a work in progress.

Building the executable
------------

This is the Java wrapper and GUI. It builds all projects and supported plugins including the Java GUI itself. Go into the JavaGUI folder and type in

    make all

If it fails to find "jni.h", you should run one of the following commands:

    make all JAVA_HOME=path_to_jdk_installation

On Ubuntu with openjdk it could look like

    make all JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64

On other Linux distros like Arch it might look like

    make all JAVA_HOME=/usr/lib/jvm/java-8-openjdk

Note: This will also compile the plugins. Some of them require additional libraries! You can disable the plugin compilation by editing the Makefile in JavaSDR.

### Windows

You need to have MinGW installed.

On Windows, this will also build the SDRplay RSP plugin. You first need to install the SDR driver from http://www.sdrplay.com/

Example of how to compile for x64 Windows

    export PATH=$PATH:"/cygdrive/C/Program Files/Java/jdk<put_your_version_here>/bin"
    make all JAVA_HOME=C:/PROGRA~1/Java/jdk<put_your_version_here> CC=x86_64-w64-mingw32-gcc ARCHNAME=X64

And replace `<put_your_version_here>` with your JDK version.

If running SDRplay Plugin, make sure the mir_sdr_api.dll and sdrplay_api.dll are in the library path (or in the same directory as the executable).
You can find the dll in C:/Program Files/SDRplay/API/x86 or C:/Program Files/SDRplay/API/x64 depending on your architecture. 

If you don't intend to use the RSP dongle, you can skip this step by editing the Makefile in the JavaGUI directory. Remove TSDRPlugin\_SDRPlay from line 89, changing it from

    PLUGINS += TSDRPlugin_SDRPlay TSDRPlugin_ExtIO

to

    PLUGINS += TSDRPlugin_ExtIO

### Linux and OS X

On Linux and OS X, compiling the GUI will also compile the UHD driver, so you will need to have UHD and the corresponding boost libraries installed (UHD will install them automatically). If you don't want the UHD drivers, then you can skip their compilation by removing the line 91 for Linux and line 93 for OS X from the Makefile in the JavaGUI directory.

Folder Structure
------------

The different folders contain the different subprojects. The TempestSDR folder contains the main C library. The project aims to be crossplatform with plugin support for SDR frontends in different folders.

Requirements for Building
------------

The project is built with Eclipse with the CDT plugin (but this is not required). Currently it supports Windows, Linux and OS X. Some frontend plugins might not be crossplatform. You also need a Java Development Kit (JDK) installed.

### Windows

You need to have MinGW installed and gcc and make commands need to be in your path. Also javac and javah also need to be in your path.

Building the executable on Ubuntu
-----

### Ubuntu 18.04.1 LTS Bionic Beaver 
- End of Life Date: April 2023
- ubuntu-18.04.1-desktop-amd64.iso
- https://www.ubuntu.com/

#### Update, Upgrade, install tools and compilers
```
sudo apt update
sudo apt upgrade
sudo apt dist-upgrade
sudo apt install vim aptitude git openjdk-8-jdk make gcc g++
```

#### Install base libraries
```
sudo apt install libuhd-dev libhackrf-dev librtlsdr-dev
```

#### Install libraries for Airspy and from Ettus Research
```
sudo apt install libairspy-dev
sudo add-apt-repository ppa:ettusresearch/uhd
sudo apt-get update
sudo apt-get install libuhd-dev libuhd003 uhd-host
```

#### git clone and build
```
mkdir ~/src
cd ~/src
git clone https://github.com/Albert-S-Briscoe/TempestSDR.git
cd TempestSDR/
make clean
make all JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

#### Run Application
```
java -jar ~/src/TempestSDR/JavaGUI/JTempestSDR.jar 
```

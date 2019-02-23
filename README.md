TempestSDR
=============

This project is a software toolkit for remotely eavesdropping video monitors using a Software Defined Radio (SDR) receiver. It exploits compromising emanations from cables carrying video signals.

Raster video is usually transmitted one line of pixels at a time, encoded as a varying current. This generates an electromagnetic wave that can be picked up by an SDR receiver. The software maps the received field strength of a pixel to a gray-scale shade in real-time. This forms a false colour estimate of the original video signal.

The toolkit uses unmodified off-the-shelf hardware which lowers the costs and increases mobility compared to existing solutions. It allows for additional post-processing which improves the signal-to-noise ratio. The attacker does not need to have prior knowledge about the target video display. All parameters such as resolution and refresh rate are estimated with the aid of the software. 

The software consists of a library written in C, a collection of plug-ins for various Software Define Radio (SDR) front-ends and a Java based Graphical User Interface (GUI). It is a multi-platform application, with all native libraries pre-compiled and packed into a single Java jar file.

Linux
-----
### Ubuntu 18.04.1 LTS Bionic Beaver 
- End of Life Date: April 2023
- ubuntu-18.04.1-desktop-amd64.iso
- https://www.ubuntu.com/

#### Update, Upgrade Ubuntu
```
sudo apt update
sudo apt upgrade
sudo apt dist-upgrade
```

#### Install tools, compilers and libraries
```
sudo apt install vim aptitude git
sudo apt install openjdk-8-jdk
sudo apt install make gcc g++
```

#### Install base libraries
```
sudo apt install libuhd-dev libhackrf-dev librtlsdr-dev
```

#### Install libraries for Airspy
```
sudo apt install libairspy-dev
```

#### Install updated libraries from Ettus Research
```
sudo add-apt-repository ppa:ettusresearch/uhd
sudo apt-get update
sudo apt-get install libuhd-dev libuhd003 uhd-host
```

#### Clone git and build
```
mkdir ~/development
cd ~/development
git clone https://github.com/tanpc/TempestSDR.git
cd TempestSDR/
make clean
make all JAVA_HOME=/usr/lib/jvm/java-8-openjdk-amd64
```

#### Run Application
```
java -jar ~/development/TempestSDR/JavaGUI/JTempestSDR.jar 
```


AirSpy
------
It provides rudimentary native support for Airspy in Linux.
Airspy branch has been merged to master.

Ettus
------
It provides updated support for Ettus SDR in Linux.
Ettus branch has been merged to master.

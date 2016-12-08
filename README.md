# ptiChat

## What is it ?
This project is a school chat project for CentraleSupelec. The chat, written in Java, is using a peer-to-peer connection between the users.

## The latest version
Details of the latest version can be found on the github repository : (here)[https://github.com/CRollin/ptiChat]

## License
Please see the file LICENSE.

## Features
- Connect to one person
- Send text messages
- Send files using `/file /path/to/file` in the chat, for example `/file /Users/me/Documents/test.jpg`. Several extensions can be sent: .jpg, .png, .txt, .md, .pdf
- Quit the chat using `/exit`

## Installation

### Prerequisites
- Java : http://www.oracle.com/technetwork/java/javase/downloads/index.html  
- Maven : https://maven.apache.org/download.cgi#Installation  

### Source code
In order to get the source code either download the compressed package or make sure you have git installed and execute the following:  
`$ git clone https://github.com/CRollin/ptiChat.git`  
`$ cd ptiChat/`  

### Open in IntelliJ
PtiChat was written in IntelliJ, but you should also be able to open it in Eclipse or Netbeans.

## How to use ?
To use ptiChat, you first need to install the maven dependencies.  
Then, you can run the project (or the class main.java). The UI will offer you the possibility to enter the required information.

## Authors
Written by : Melanie BERARD, Charles ROLLIN, Roxane ROUX.

## How to submit bug fixes or new features
To implement new changes, developers should use the following steps:
- Create a new branch ("git checkout -b username-example-name-of-branch")
- Work on several commits ("git commit --amend" to modify/amend a previous commit)
- Merge Master on the current branch (username-example-name-of-branch")
- Ask for a pull request

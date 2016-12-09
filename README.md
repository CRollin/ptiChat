# ptiChat

## What is it ?
This project is a school chat project for CentraleSupelec. The chat, written in Java, is using a peer-to-peer connection between the users.

## The latest version
Details of the latest version can be found on the github repository: (here)[https://github.com/CRollin/ptiChat]

## License
Please see the file LICENSE.

## Features
- Connect to a peer using his IP and port
- Choose where to save the files you will receive through this chat (default will be the current working directory)
- Send text messages to your peer!
- Send files using the afferent button in the chat. Several extensions can be sent: .jpg, .png, .txt, .md, .pdf, ...
- Close the Chat screen to go back to the Connection screen

## Installation

### Prerequisites
- Java 8: http://www.oracle.com/technetwork/java/javase/downloads/index.html  
- Maven: https://maven.apache.org/download.cgi#Installation (if installing from source code)

### From source

#### Source code
In order to get the source code either download the compressed package or make sure you have git installed and execute the following:  
`$ git clone https://github.com/CRollin/ptiChat.git`  
`$ cd ptiChat/`  

#### Open in IntelliJ
PtiChat was written in IntelliJ, but you should also be able to open it in Eclipse or Netbeans.     
The project might not build at first in IntelliJ, as our source folder is /src (instead of default /src/main/java for IntelliJ).    
Go to File > Project Structure to fix this.

### From jar
`(!jarDownloaded) ? downloadJar() : profit();`

## How to use ?

### From source
To use ptiChat, you first need to install the maven dependencies.   
Then, you can run the project (or the class main.java). The UI will let you enter the required information.

### From jar
If java is in your path:    
`$ java -jar path/to/file.jar`  
Otherwise:  
`$ path/to/java -jar path/to/file.jar`

## Limitations

- Cross-OS issues:
    - Sending files from Mac to Windows won't work (files are correctly received but not saved)
    - Some encoding issues between OS in text messages
- No feedbacks on file shipment:
    - the user doesn't receive a notification message when his peer has not correctly received a file

## Authors
Written by: Melanie BERARD, Charles ROLLIN, Roxane ROUX.

## How to submit bug fixes or new features
To implement new changes, developers should use the following steps:
- Create a new branch ("git checkout -b username-example-name-of-branch")
- Work on several commits ("git commit --amend" to modify/amend a previous commit)
- Merge Master on the current branch (username-example-name-of-branch")
- Ask for a pull request

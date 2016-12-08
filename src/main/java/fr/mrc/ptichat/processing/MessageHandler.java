package main.java.fr.mrc.ptichat.processing;

import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import java.io.*;

public class MessageHandler {
    private MessageSignatureController msc;

    public MessageHandler() { this.msc = new MessageSignatureController() ;}

    /**
     * Checks if the message is a file transmission.
     * @param message the message to send
     * @return <code>true</code> if the message starts with /file, <code>false</code> otherwise
     */
    public boolean isFileTransmission(String message) {
        return message.startsWith(this.msc.getFileTerminationSignature());
    }

    /***
     * Transforms the input into a String containing "/file /path/to/file fileInBytesString"
     * @param input
     * @return <code>String</code>
     */
    public String fileToMessage(String input){
        String filename = getFilePath(input);
        return fileToString(filename);
    }

    /***
     * Transforms the received message into a file
     * @param message
     * @return <code>String</code> the path of the newly created file
     */
    public String messageToFile(String message){
        String[] messageContent = getContentFromMessage(message);
        String filename = buildUserFilePath(messageContent[1]);
        String result = "";
        try {
            stringToFile(messageContent[2], filename);
            result = "New file received at " + filename;
        } catch(IOException e){
            result = "The File could not be saved in your computer, try again.";
        } catch(ArrayIndexOutOfBoundsException e){
            result = "Someone tried to send you a file, but it didn't work. Try again.";
        }
        return result;
    }

    /***
     * Remove /file from the message in order to get the file's path
     * @param input
     * @return the path of the file
     */
    public String getFilePath(String input) {
        return input.substring(this.msc.getFileTerminationSignature().length() + 1);
    }

    /***
     * Build the path where the user should save the file ex "Users/username/Documents/..."
     * @param filepath
     * @return <code>String</code>
     */
    public String buildUserFilePath(String filepath){
        String[] dir = filepath.split("/");
        String filename = System.getProperty("user.dir") + "/new_" + dir[dir.length - 1];
        return filename;
    }

    /***
     * Transforms "/file /path/to/file fileInBytesString" into an array of these 3 elements
     * @param message
     * @return <code>String[]</code>
     */
    public String[] getContentFromMessage(String message){
        // Split whenever at least one whitespace is encountered
        String[] splitArray = message.split("\\s+");
        return splitArray;
    }

    /**
     * Encodes a file into an array of bytes.
     * @param filename the location of the file
     * @return the <code>byte[]</code> representation of the file
     */
    public String fileToString(String filename) {
        String fileDataString = new String();
        try {
            File file = new File(filename);

            // Reading a file from file system
            FileInputStream contentInFile = new FileInputStream(file);
            byte fileData[] = new byte[(int) file.length()];
            contentInFile.read(fileData);

            // Converting file byte array into Base64 String
            fileDataString = FileManipulation.encodeImage(fileData);

            // Close ContentInFile
            contentInFile.close();
        } catch (FileNotFoundException e) {
            System.out.println("Image not found" + e);
        } catch (IOException ioe) {
            System.out.println("Exception while reading the Image " + ioe);
        }
        return fileDataString;
    }

    /***
     * Transform the file in Bytes into a real file and save it at the path filename
     * @param fileDataString
     * @param filename
     * @throws IOException
     */
    public void stringToFile(String fileDataString, String filename) throws IOException{
        // Converting a Base64 String into Image byte array
        byte[] imageByteArray = FileManipulation.decodeImage(fileDataString);

        // Write a image byte array into file system
        FileOutputStream imageOutFile = new FileOutputStream(filename);

        imageOutFile.write(imageByteArray);
        imageOutFile.close();
    }

    /**
     * Checks if a String is a termination message.
     * @param input the <code>String</code> to check
     * @return <code>true</code> if the String is a termination message, false otherwise
     */
    public boolean isTerminationMessage(String input) {
        return msc.getTerminationSignature().equals(input);
    }
}

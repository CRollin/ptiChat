package main.java.fr.mrc.ptichat.processing;

import main.java.fr.mrc.ptichat.utils.LanguagesController;
import main.java.fr.mrc.ptichat.utils.MessageSignatureController;

import java.io.*;

public class MessageHandler {
    private MessageSignatureController msc;
    private LanguagesController languagesController = LanguagesController.getInstance();

    public MessageHandler() { this.msc = new MessageSignatureController() ;}

    /**
     * Checks if the message is a file transmission.
     * @param message the message to send
     * @return <code>true</code> if the message starts with /file, <code>false</code> otherwise
     */
    public boolean isFileTransmission(String message) {
        return message != null && message.startsWith(this.msc.getFileTerminationSignature());
    }

    /***
     * Transforms the received message into a file
     * @param sentFilename
     * @param fileContent
     * @return <code>String</code> the path of the newly created file
     */
    public String messageToFile(String sentFilename, String fileContent) throws IOException {
        String filename = buildUserFilePath(sentFilename);
        String result;
        try {
            stringToFile(fileContent, filename);
            result = languagesController.getWord("NEW_FILE_RECEIVED") + filename;
            return result;
        } catch(IOException e){
            throw new IOException("/!\\ " + languagesController.getWord("FILE") + " " + sentFilename + " " + languagesController.getWord("NOT_SAVED"), e);
        }
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
    public String[] getContentFromMessage(String message) throws IOException {
        // Split whenever at least one whitespace is encountered
        String[] splitArray = message.split("\\s+");
        if (splitArray.length < 2) throw new IOException(languagesController.getWord("NO_FILE_EXCEPTION"));
        return splitArray;
    }

    /**
     * Encodes a file into a Base64 encoded String.
     * @param filename the location of the file
     * @return the <code>String</code> representation of the file
     */
    public String fileToString(String filename) throws IOException {
        String fileDataString;
        FileInputStream contentInFile = null;
        try {
            File file = new File(filename);
            contentInFile = new FileInputStream(file);

            // Reading a file from file system
            byte fileData[] = new byte[(int) file.length()];
            contentInFile.read(fileData);

            // Converting file byte array into Base64 String
            fileDataString = FileManipulation.encodeImage(fileData);

            return fileDataString;
        } catch (FileNotFoundException e) {
            throw new FileNotFoundException("/!\\ " + languagesController.getWord("NO_SUCH_FILE"));
        } catch (IOException ioe) {
            throw new IOException("/!\\ " + languagesController.getWord("UNABLE_TO_SEND_FILE"), ioe);
        } finally {
            // Close ContentInFile
            if (contentInFile != null) contentInFile.close();
        }
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
        return input != null && msc.getTerminationSignature().equals(input);
    }
}

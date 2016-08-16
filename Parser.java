package quiz;
import java.io.*;
/**
 * This class is thread safe. It reads and writes string content from/to the specified file.
 */
public class Parser {

  private final File file;
  public synchronized void setFile(File f) {
    file = f;
  }
  public synchronized File getFile() {
    return file;
  }
  
  /**
   * Reads the file to a string, optionally allowing unicode.
   * @param allowUnicode whether to allow unicode
   * @throws IOException
   * @return String
   */
  public String getContent(boolean allowUnicode = false) throws IOException {
    Reader inpReader = new InputStreamReader(new FileInputStream(file));
    StringBuilder output = new StringBuilder("");
    int data;
    while ((data = inpReader.read()) >= 0) {
      if (allowUnicode || isAscii(data)) {
        output.append((char) data);
      }
    }
    inpReader.close();
    return output.toString();
  }
  
  /**
   * Writes the content to the file.
   * @param content the content to write to file
   * @throws IOException
   * @return void
   */
  public void saveContent(String content) throws IOException {
    FileOutputStream o = new FileOutputStream(file);
    for (int i = 0; i < content.length(); i += 1) {
      o.write(content.charAt(i));
    }
  }
  
  private static final int MAX_ASCII = 0x80;
  private static boolean isAscii(int data) {
    return data < MAX_ASCII;
  }
  
}

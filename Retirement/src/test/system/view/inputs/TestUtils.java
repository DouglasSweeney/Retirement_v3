package test.system.view.inputs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class TestUtils {
  private final static int BLK_SIZ = 8192;
  public final static String FILENAME_TEMP99 = "temp99.xml";
  
  public void delete(final String filename) {
    final File file = new File(filename);
    
    if (file.exists()) {
      file.delete();
    }
  }
  
  public String readFileIntoString(final String filename) {
    final StringBuffer stringBuffer = new StringBuffer();
    final char[] b = new char[BLK_SIZ];
    int n;  
    FileReader is = null;
    try {
      final File file = new File(filename);
      try {
        file.createNewFile();
      } catch (IOException e) {
        e.printStackTrace();
      }
      is = new FileReader(file);
    } catch (FileNotFoundException e1) {
      e1.printStackTrace();
    }
    
    try {
      n = is.read(b);
      while (n > 0) {
        stringBuffer.append(b, 0, n);
        n = is.read(b);
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      if (is != null) {
        is.close();
      }
    } catch (IOException e) {
       e.printStackTrace();
    }
    
    return stringBuffer.toString();
  }
  
  public boolean findEnclosingElements(final String fileContents, final String enclosingElement) {
    boolean valid;
    final String startString = "<" + enclosingElement + " name=" + '"' + enclosingElement + "\">";
    final String endString = "</" + enclosingElement + ">";
    final int startElementIndex = fileContents.indexOf(startString);
    final int endElementIndex = fileContents.indexOf(endString);
    
    valid = false;
    if (startElementIndex != -1 && endElementIndex != -1 && startElementIndex < endElementIndex) {
      valid = true;
    }
  
    return valid;
  }
  
  public boolean findElementAndAttribute(final String fileContents, final String element, 
    final String attributeName, final String attributeValue) {
    final String str = "<" + element + " name=" + '"' + element + "\" " + attributeName + "=\"" +  attributeValue + "\"/>";
    final int elementIndex = fileContents.indexOf(str);
    boolean valid;
    
    valid = false;
    if (elementIndex != -1) {
      valid = true;
    }
  
    return valid;
  }
 
  public static void main(final String[] args) {
    final TestUtils testUtils = new TestUtils();
    String fileContents;
    
    fileContents = testUtils.readFileIntoString(FILENAME_TEMP99);
    
    if (testUtils.findEnclosingElements(fileContents, "Account401K")) {
      System.out.println("found the enclosing element");
    } else {
      System.out.println("did not find the enclosing element");
    }
    if (testUtils.findElementAndAttribute(fileContents, "account401kBalance", "doubleValue", "354001.0")) {
      System.out.println("found the attribute");
    } else {
      System.out.println("did not find the attribute");
    }
    testUtils.delete(FILENAME_TEMP99);
  }
}

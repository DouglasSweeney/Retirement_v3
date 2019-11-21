package application.main.pdf;

public class Util {
  
  public boolean isAnotherPageRequired(final int dataLines, final Integer pageLines) {
    boolean anotherPage;
  
    if (dataLines > pageLines) {
       anotherPage = true;
    }
    else {
      anotherPage = false;
    }
  
    return anotherPage;
  }
}

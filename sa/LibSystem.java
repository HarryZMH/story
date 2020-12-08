// --== CS400 File Header Information ==--
// Name: Minghao Zhou
// Email: mzhou222@wisc.edu
// Team: JB
// Role: Back End Developer 2
// TA: Harper
// Lecturer: Gary Dahl
// Notes to Grader: N/A
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.List;



public class LibSystem {
  
  ArrayList<ArticleItem> articleList = new ArrayList<ArticleItem>();

  public static void main(String[] args) {
    String a = "moon";
    String b = "what are moons";
    String c = "moon";
    String d = "we are not those";
    String e = "and";
    String f = "article is not what we want";
    String g = "so what";
    String h = "what are moonmoon moons";
    double w = compareStrings(a, h);
    System.out.print(w);
    

  }


  public LinkedList<ArticleItem> filter(String filterMethod, Object filterTarget) {

    LinkedList<ArticleItem> resultList = new LinkedList<ArticleItem>();

    if (filterMethod.equalsIgnoreCase("year")) {
      for (ArticleItem article : articleList) {
        if (article.year == filterTarget) {
          resultList.add(article);
        }
      }
    }

    if (filterMethod.equalsIgnoreCase("publisher")) {
      for (ArticleItem article : articleList) {
        if (article.publisher.equals(filterTarget)) {
          resultList.add(article);
        }
      }
    }

    if (filterMethod.equalsIgnoreCase("journal")) {
      for (ArticleItem article : articleList) {
        if (article.journal.equals(filterTarget)) {
          resultList.add(article);
        }
      }
    }
    return resultList;
  }

  
  
  
  public LinkedList<ArticleItem> search(String searchInput) {
   int n = articleList.size();
   Pair arr[] = new Pair[n]; 
   
   for (int i = 0; i < n; i++) {
     double stringDifference = compareStrings(searchInput, articleList.get(i).getTitle());
     arr[i] = new Pair(articleList.get(i), stringDifference);
   }
   compare(arr, n);
   LinkedList<ArticleItem> resultList = new LinkedList<ArticleItem>();
   for (int j = n - 1; j >= 0; j--) {
     resultList.add(arr[j].x);
   }
    return resultList;
  }
  
  
  
  
  
  
  

  
  private static void compare(Pair arr[], int n) {
    // Comparator to sort the pair according to second element
    Arrays.sort(arr, new Comparator<Pair>() {
      @Override
      public int compare(Pair p1, Pair p2) {
        if (p1.y < p2.y) {
          return -1;
        }
        if (p1.y > p2.y) {
          return 1;
        }
        return 0;
      }
    });
    for (int i = 0; i < n; i++) { 
      System.out.print(arr[i].x + " "); 
  } 
  System.out.println(); 
  }


  
//  private static int getLevenshteinDistance(String s, String t) {
//    if (s == null || t == null) {
//      throw new IllegalArgumentException("Strings must not be null");
//    }
//
//    int n = s.length(); // length of s
//    int m = t.length(); // length of t
//
//    if (n == 0) {
//      return m;
//    } else if (m == 0) {
//      return n;
//    }
//
//    if (n > m) {
//      // swap the input strings to consume less memory
//      String tmp = s;
//      s = t;
//      t = tmp;
//      n = m;
//      m = t.length();
//    }
//
//    int p[] = new int[n + 1]; // 'previous' cost array, horizontally
//    int d[] = new int[n + 1]; // cost array, horizontally
//    int _d[]; // placeholder to assist in swapping p and d
//
//    // indexes into strings s and t
//    int i; // iterates through s
//    int j; // iterates through t
//
//    char t_j; // jth character of t
//
//    int cost; // cost
//
//    for (i = 0; i <= n; i++) {
//      p[i] = i;
//    }
//
//    for (j = 1; j <= m; j++) {
//      t_j = t.charAt(j - 1);
//      d[0] = j;
//
//      for (i = 1; i <= n; i++) {
//        cost = s.charAt(i - 1) == t_j ? 0 : 1;
//        // minimum of cell to the left+1, to the top+1, diagonally left and up +cost
//        d[i] = Math.min(Math.min(d[i - 1] + 1, p[i] + 1), p[i - 1] + cost);
//      }
//
//      // copy current distance counts to 'previous row' distance counts
//      _d = p;
//      p = d;
//      d = _d;
//    }
//
//    // our last action in the above loop was to switch d and p, so p now
//    // actually has the most recent cost counts
//    return p[n];
//  }
//  
  
  
  
  
  
  /** @return an array of adjacent letter pairs contained in the input string */

  private static String[] letterPairs(String str) {

      int numPairs = str.length()-1;

      String[] pairs = new String[numPairs];

      for (int i=0; i<numPairs; i++) {

          pairs[i] = str.substring(i,i+2);

      }

      return pairs;

  }
  /** @return an ArrayList of 2-character Strings. */

  private static ArrayList wordLetterPairs(String str) {

      ArrayList allPairs = new ArrayList();

      // Tokenize the string and put the tokens/words into an array

      String[] words = str.split("\\s");

      // For each word

      for (int w=0; w < words.length; w++) {

          // Find the pairs of characters

          String[] pairsInWord = letterPairs(words[w]);

          for (int p=0; p < pairsInWord.length; p++) {

              allPairs.add(pairsInWord[p]);

          }

      }

      return allPairs;

  }
  
  /** @return lexical similarity value in the range [0,1] */

  public static double compareStrings(String str1, String str2) {

      ArrayList pairs1 = wordLetterPairs(str1.toUpperCase());

      ArrayList pairs2 = wordLetterPairs(str2.toUpperCase());

      int intersection = 0;

      int union = pairs1.size() + pairs2.size();

      for (int i=0; i<pairs1.size(); i++) {
          Object pair1=pairs1.get(i);
          for(int j=0; j<pairs2.size(); j++) {
              Object pair2=pairs2.get(j);
              if (pair1.equals(pair2)) {
                  intersection++;
                  pairs2.remove(j);
                  break;
              }
          }
      }
      return (2.0*intersection)/union;
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  



}

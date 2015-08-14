/** 
 This class finds the smallest, middle, and largest of 
 three strings. 
 @author Ramzey Ghanaim
 @version 1/10/1014
*/ 
public class StringSet 
{ 
private String n= "Try again";
 /** 
 Constructs a string set that processes three strings. 
 @param str1 the first string to sort 
 @param str2 the second string to sort 
 @param str3 the third string to sort 
 */ 
 public StringSet(String str1, String str2, String str3) 
 { 
   str1= str1;
   str2= str2;
   str3=str3;
 } 
 
 /** 
 Gets the smallest string in the string set. 
 @return smallest the smallest of three strings 
 */ 
 public String getSmallest() 
 { 
    if (str1.compareToIgnoreCase(str2)<= 0 && str1.compareToIgnoreCase(str3) <=0)
    {
        return str1;
    }
    else if(str2.compareToIgnoreCase(str3)<=0 && str2.compareToIgnoreCase(str1)<=0)
    {
        return str2;
    }
    else if(str3.compareToIgnoreCase(str3) <=0 && str3.compareToIgnoreCase(str1)<=0)
    {
        return str3;
    }
    return n;
 } 
 
 /** 
 Gets the largest string in the string set. 
 @return largest the largest of three strings 
 */ 
 public String getLargest() 
 { 
    if (str1.compareToIgnoreCase(str2)>= 0 && str1.compareToIgnoreCase(str3) >=0)
    {
        return str1;
    }
    else if(str2.compareToIgnoreCase(str3)>=0 && str2.compareToIgnoreCase(str1)>=0)
    {
        return str2;
    }
    else if(str3.compareToIgnoreCase(str3) >=0 && str3.compareToIgnoreCase(str1)>=0)
    {
        return str3;
    }
    return n;
 } 
 
 /** 
 Gets the middle string in the string set. 
 @return middle the middle string of three strings 
 */ 
 public String getMiddle() 
 { 
    if (str1.compareToIgnoreCase(str2)<= 0 && str1.compareToIgnoreCase(str3) >=0)
    {
        return str1;
    }
    else if(str2.compareToIgnoreCase(str3)<=0 && str2.compareToIgnoreCase(str1)>=0)
    {
        return str2;
    }
    else if(str3.compareToIgnoreCase(str3) <=0 && str3.compareToIgnoreCase(str1)>=0)
    {
        return str3;
    }
    return n;
} 
}
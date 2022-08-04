import java.io.*;
import java.util.*;

class Playfair{
    String key, ptext;
    char[][] key_matrix = new char[5][5];

    public Playfair(String key, String ptext){
        this.key = key.toLowerCase();
        this.ptext = ptext.toLowerCase();
    }

    public void clean_key(){
        LinkedHashSet<Character> set = new LinkedHashSet<Character>();
	
		String newKey = "";
	
		for (int i = 0; i < key.length(); i++){
            set.add(key.charAt(i));
        }

		Iterator<Character> it = set.iterator();
	
		while (it.hasNext()){
            newKey += (Character)it.next();
        }

		key = newKey;
    }

    public void create_matrix(){
        Set<Character> set = new HashSet<Character>();
        for(int i = 0; i<key.length();i++){
            if(key.charAt(i) == 'j'){
                continue;
            }
            else{
                set.add(key.charAt(i));            }
        }

        String tempKey = new String(key);

        for(int i = 0; i < 26; i++){
            char ch = (char)(i+97);
            if(ch == 'j'){
                continue;
            }
            if(!set.contains(ch)){
                tempKey += ch;
            }
        }

        for(int i=0, k=0; i<5; i++){
            for(int j=0; j<5; j++){
                key_matrix[i][j] = tempKey.charAt(k++);
            }    
        }

        for(int i=0; i<5;i++){
            System.out.println(key_matrix[i]);
        }
    }

    public String format_ptext(){
        String message = "";
        int len = ptext.length();
       
        for (int i = 0; i < len; i++){
            if (ptext.charAt(i) == 'j')
                message += 'i';
            else
                message += ptext.charAt(i);
        }
 
        
        for (int i = 0; i < message.length(); i += 2){
            if (message.charAt(i) == message.charAt(i + 1))
                message = message.substring(0, i + 1) + 'x' + message.substring(i + 1);
        }

        if (len % 2 == 1)
            message += 'x'; 
       
        return message;
    }

    public String[] formPairs(String message)
    {
        int len = message.length();
        String[] pairs = new String[len / 2];
       
        for (int i = 0, cnt = 0; i < len / 2; i++)
            pairs[i] = message.substring(cnt, cnt += 2);
       
        return pairs;
    }

    public int[] charPos(char ch){
        int[] pos = new int[2];

        for(int i=0;i<5;i++){
            for(int j=0;j<5;j++){
                if(key_matrix[i][j]==ch){
                    pos[0]=i;
                    pos[1]=j;
                    break;
                }
            }
            
        }
        return pos;
    }

    public String Encrypt(){
        String message = format_ptext();
        String[] msgPairs = formPairs(message);
        String encText = "";
       
        for (int i = 0; i < msgPairs.length; i++){
            char ch1 = msgPairs[i].charAt(0);
            char ch2 = msgPairs[i].charAt(1);
            int[] ch1Pos = charPos(ch1);
            int[] ch2Pos = charPos(ch2);
 
            
            if (ch1Pos[0] == ch2Pos[0]) {
                ch1Pos[1] = (ch1Pos[1] + 1) % 5;
                ch2Pos[1] = (ch2Pos[1] + 1) % 5;
            }
           
            
            else if (ch1Pos[1] == ch2Pos[1])
            {
                ch1Pos[0] = (ch1Pos[0] + 1) % 5;
                ch2Pos[0] = (ch2Pos[0] + 1) % 5;
            }
           
            
            else {
                int temp = ch1Pos[1];
                ch1Pos[1] = ch2Pos[1];
                ch2Pos[1] = temp;
            }
           
            encText = encText + key_matrix[ch1Pos[0]][ch1Pos[1]] + key_matrix[ch2Pos[0]][ch2Pos[1]];
        }
       
        return encText;
    }

}

public class PlayfairMain{
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String ptext, key;

        System.out.println("Enter plain text: ");
        ptext = sc.next();

        System.out.println("Enter key: ");
        key = sc.next();

        Playfair pfc = new Playfair(key, ptext);
        pfc.clean_key();
        pfc.create_matrix();

        String ctext = pfc.Encrypt();
        System.out.println(ctext);
    }
}


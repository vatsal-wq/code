import java.util.*;

class CaesarCipher{
    String ccipher(String ptext1, int key){
        String ctext1 = new String();
        for(int i=0;i<ptext1.length();i++){
            ctext1+=(char)(ptext1.charAt(i)+key);
        }
        return ctext1;
    }

    String cdecipher(String ctext2, int key){
        String ptext2 = new String();
        for(int i=0;i<ctext2.length();i++){
            ptext2+=(char)(ctext2.charAt(i)-key);
        }
        return ptext2;
    }
}

class RailFenceCipher{
    String Encrypt(String ptext3, int key){
        int r=key,len=ptext3.length();
        int c=len/key;
        char mat[][]=new char[r][c];
        int k=0;
  
        String ctext3="";
  
        for(int i=0;i< c;i++){
            for(int j=0;j< r;j++){
                if(k!=len)
                    mat[j][i]=ptext3.charAt(k++);
                else
                    mat[j][i]='X';
            }
        }
  
        for(int i=0;i< r;i++){
            for(int j=0;j< c;j++){
                ctext3+=mat[i][j];
            }
        }

        return ctext3;
    }

    String Decrypt(String ctext4, int key){
        int r=key,len=ctext4.length();
        int c=len/key;
        char mat[][]=new char[r][c];
        int k=0;
        String ptext4="";
  
        for(int i=0;i< r;i++){
           for(int j=0;j< c;j++){
                mat[i][j]=ctext4.charAt(k++);
            }
        }
        for(int i=0;i< c;i++){
            for(int j=0;j< r;j++){
                ptext4+=mat[j][i];
            }
        }

        return ptext4;
    }
}

public class Ciphers2 {
    public static void main(String args[]){
        CaesarCipher cc = new CaesarCipher();
        RailFenceCipher rfc = new RailFenceCipher();
        Scanner sc = new Scanner(System.in);
        String ptext = new String();
        String ctext = new String();
        String ptext2 = new String();
        String ctext2 = new String();
        String ptext3 = new String();
        int key;

        System.out.println("Enter plaintext: ");
        ptext=sc.next();
        System.out.println("Enter key: ");
        key = sc.nextInt();

        
        ctext = cc.ccipher(ptext, key);
        System.out.println(ctext);

        ctext2 = rfc.Encrypt(ctext, key) ;
        System.out.println(ctext2);

        ptext3 = rfc.Decrypt(ctext2, key);
        System.out.println(ptext3);

        ptext2 = cc.cdecipher(ptext3, key);
        System.out.println(ptext2);
        
        sc.close();
    }
}

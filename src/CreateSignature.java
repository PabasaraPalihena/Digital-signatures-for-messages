import java.security.*;
import java.util.Scanner;

public class CreateSignature {
    public static void main(String[] args) throws Exception {

        //Accepting text from user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter text : ");
        String msg = sc.nextLine();

        //Creating KeyPair generator object
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

        //Initializing the key pair generator
        keyPairGen.initialize(2048);

        //Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();

        //Getting the private key from the key pair
        PrivateKey privKey = pair.getPrivate();

        //Creating a Signature object
        Signature sign = Signature.getInstance("SHA256withDSA");

        //Initialize the signature
        sign.initSign(privKey);
        byte[] bytes = "msg".getBytes();

        //Adding data to the signature
        sign.update(bytes);

        //Calculating the signature
        byte[] signature = sign.sign();

        //Printing the signature
        System.out.println("Digital signature for given text : "+new String(signature, "UTF8"));

        //Initializing the signature
        sign.initVerify(pair.getPublic());

        //add data to the signature
        sign.update(bytes);

        //Verifying the signature
        boolean bool = sign.verify(signature);

        if(bool) {
            System.out.println("Signature verified.....");
        } else {
            System.out.println("Signature failed.....");
        }

    }
}
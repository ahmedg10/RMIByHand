import java.io.*;
import java.net.*;




public class Client {
    /**
     * This method name and parameters must remain as-is
     */

     public static int add(int lhs, int rhs)  {
        //
        try{
            Socket socket = new Socket("localhost", PORT);
            
            //remote object is created sending the method and arguments to it to be seralized
            RemoteMethod add = new RemoteMethod("add", new Object[]{lhs, rhs});
            

            //Opening the servers output stream and sending the remote method we created
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(add);

            //reading results we recieved back from the server when we sent the add request
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            
            //The result is deserialized to an integer and assigned to the result variable.
            int result = (int) is.readObject();

            os.close();
            is.close();
            socket.close();
            return result;
            
        }catch(ConnectException e){
                System.err.println("Failed to connect to server: " + e.getMessage());
                System.exit(1);
                return -1;
        } catch(IOException | ClassNotFoundException  e ){
            e.printStackTrace();
            return -1;
        }
        

     }



    /**
     * This method name and parameters must remain as-is
     */
    public static int divide(int num, int denom) throws ArithmeticException{
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream())){
    
            // create an instance of RemoteMethod with the "divide" method name and arguments
            RemoteMethod divide = new RemoteMethod("divide", new Object[]{num, denom});
    
            // serialize the RemoteMethod instance and send it to the server
            os.writeObject(divide);

            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
    
            // read the result sent back from the server
            Object response = is.readObject();
            // close streams and socket
            os.close();
            is.close();
            socket.close();
            
            if (response instanceof String && ((String) response).startsWith("ArithmeticException")) {
                throw new ArithmeticException((String) response);
            }
            
            return response != null ? (int) response : 0;
        } catch (ConnectException e) {
            System.err.println("Failed to connect to server: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return -1;
    }
    

    /**
     * This method name and parameters must remain as-is
     */
    public static String echo(String message) {
        try {
            // create a socket and connect to the server
            Socket socket = new Socket("localhost", PORT);

            // create an instance of RemoteMethod with the "echo" method name and arguments
            RemoteMethod echo = new RemoteMethod("echo", new Object[]{message});

            // create an ObjectOutputStream to serialize the RemoteMethod instance and send it to the server
            ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());
            os.writeObject(echo);

            // create an ObjectInputStream to read the result sent back from the server
            ObjectInputStream is = new ObjectInputStream(socket.getInputStream());
            String result = (String) is.readObject();

            // close the streams and the socket
            os.close();
            is.close();
            socket.close();

            // return the result
            return result;
        }catch(ConnectException e){
            System.err.println("Failed to connect to server: " + e.getMessage());
            return "";
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Do not modify any code below this line
    // --------------------------------------
    String server = "localhost";
    public static final int PORT = 10314;

    public static void main(String... args){
        // All of the code below this line must be uncommented
        // to be successfully graded.
        System.out.print("Testing... ");

        if (add(2, 4) == 6)
            System.out.print(".");
        else
            System.out.print("X");

        try {
            divide(1, 0);
           

            System.out.print("X");
        }
        catch (ArithmeticException x) {
            System.out.print(".");
        }

        if (echo("Hello").equals( "You said Hello!"))
            System.out.print(".");
            
        else
            System.out.print("X");
        
        System.out.println(" Finished");
    }
}
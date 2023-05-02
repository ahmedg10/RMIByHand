import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectOutputStream;

public class Server {
    public static void main(String[] args) {
        try {
            //// lisiting to the port
            ServerSocket server = new ServerSocket(10314);

            Socket client = null;
            //This creates an infinite loop that will listen to incoming client connections
            //and handle them
            while((client = server.accept())!= null){
                try{

                //we are instantating instance that reads the data input stream, set from the
                //client socket
                ObjectInputStream ois = new ObjectInputStream(client.getInputStream());

                //readObject is uses deseralization to get the method we are asking for
                RemoteMethod remoteMethod = (RemoteMethod) ois.readObject();
                
                String methodName = remoteMethod.getMethodName();

                //This hold the parameters of the various methods. So, the index positon
                // matters to get the correct information to get these methods to work.
                Object[] argsList = remoteMethod.getArguments();

                Object info = handleRequest(methodName, argsList);
                ObjectOutputStream oos= new ObjectOutputStream(client.getOutputStream());
                oos.writeObject(info);
                oos.flush();
                oos.close();


        
                

            } catch(Exception e) {
                e.printStackTrace();
            }
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Object handleRequest(String method, Object[] argsList){
        switch (method) {
            case "echo":
                String message = (String) argsList[0];
                return Server.echo(message);
            case "add":
                int lhs = (int) argsList[0];
                int rhs = (int) argsList[1];
                return Server.add(lhs, rhs);
                case "divide":
                try {
                    int num = (int) argsList[0];
                    int denom = (int) argsList[1];
                    return Server.divide(num, denom);
                } catch (ArithmeticException e) {
                    return "ArithmeticException: Division by zero";
                }
            default:
                throw new RuntimeException("Invalid method name");
        }
    }

    // Do not modify any code below tihs line
    // --------------------------------------
    public static String echo(String message) { 
        return "You said " + message + "!";
    }
    public static int add(int lhs, int rhs) {
        return lhs + rhs;
    }
    public static int divide(int num, int denom) {
        if (denom == 0)
            throw new ArithmeticException();

        return num / denom;
    }
}
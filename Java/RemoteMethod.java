import java.io.*;
import java.rmi.*;

//Since, we implement Seralizable it will automatically seralize and deseralize the arguments
public class RemoteMethod implements Serializable{
    private String methodName;
    private Object[] args;

    public RemoteMethod(String methodName, Object[] args) throws RemoteException {
        this.methodName = methodName;
        this.args = args;
    }

    public String getMethodName(){
        return methodName;
    }

    public Object[] getArguments(){
        return args;
    }
}

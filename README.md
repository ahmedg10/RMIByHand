# What is a Remote Prodecure Call

RPC is a way for one computer to call a function on anothe computer over a network, as if the function was the same comptuer. Its like asking your freind to do something for you, even though they not in the same room. 



## When would we use RPC:
- Lets say you have a game on your computer and you want to play it with your friend who lives far away. If the game is designed to work with RPC, you can noth connect to the same computer and play together, even though you are not in the same room 
- In short, RPC is useful when you want to communicate functions between diff computers over a network, making it possible to share data and resources even weh. 


## Why do we need to seralize (marshal) the function? 
We need to seralize or marshal the function we are sending over the network because the information we want to send may not be formatted in a way were its easy to send it through the network. For example, **Parameters** that are in a formation such as an object or array, we need to seralize so that it can be sent through the network. We want to covert the object and array into stream of bytes taht cna be sent over network and reconstructed into an object on the otherside (unmarshled/ deserilized)

Yes, serialization is mainly used for the function parameters.

## Steps of An RPC 
1. The server opens for biz: The computer is ready ro recieve function calls. 
2. The client marshals the call parameters into a wire friendly format: The computer gets the functions information and seralizes into a format were we able to send it through the network. 
3. The client connects to the server
4. client sends the marshaled call data
5. The server recieves the marshaled call data
6. The server unmarshals the call data back into parameters. This step allows us to translate the necesary information parameters to have the server understand and execute the necessary code
7. The server exectures the call with parameters
8. if call sucessed ths sever captures the results 
9. if call fails the server needs to capture and returns errrors



## Issue I had with Divide: 
Before I was using returns cause of exception, but instead I return a string and then that 
string if it was instance of string and had Arthmetic Expression in it, it then throws the arthemtic exxpression in the client. 
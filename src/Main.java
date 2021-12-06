import controller.CardController;
import controller.DatasetController;
import controller.InputContoller;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        if(args.length==0){
            System.out.println("File path to Stock Inventory/Dataset not provided. Please enter one.");
            System.exit(0);
        }
        processStart(args);
    }
    private static void processStart(String[] args) throws IOException {
        DatasetController datasetController = new DatasetController(args[0]);
        datasetController.datasetCreation();
        CardController cardController = new CardController(args[1]);
        cardController.cardCreation();
        startOrder(args[2]);
    }

    private static void startOrder(String path){
        InputContoller inputContoller = new InputContoller(path);
        if(inputContoller.startOrder()){
            if(inputContoller.checkOrder()){
                inputContoller.calculateTotal();
                if(inputContoller.getTotal()>0){
                    inputContoller.checkoutOrder();
                    System.out.println("The total amount of the ordered item is $" + inputContoller.getTotal());
                }
            }else {
                System.out.println("Error! Please check Error Text file in files folder");
                inputContoller.generateOutputFile();
            }
        }else {
            System.out.println("Order file not found");
        }
    }
}

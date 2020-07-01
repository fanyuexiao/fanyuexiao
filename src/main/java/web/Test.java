package web;

import java.util.Iterator;
import java.util.ServiceLoader;

public class Test {
    public static void main(String[] args) {
        ServiceLoader<SPIService> spiServices = ServiceLoader.load(SPIService.class);
        Iterator<SPIService> iterator = spiServices.iterator();
        while(iterator.hasNext()) {
            SPIService ser = iterator.next();
            ser.say();
        }
    }
}

package edu.eci.arsw.conc;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hector.cadavid@escuelaing.edu.co
 */
public class TransferencesSim {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Account[] acnts=new Account[10];
        TransactionSimulationThread[] tthreads=new TransactionSimulationThread[1];
        
        for (int i=0;i<acnts.length;i++){
            acnts[i]=new Account(1000);
        }
        
        int totalBalance=0;
        for (Account acnt : acnts) {
            totalBalance += acnt.getBalance();
        }
        System.out.println("Total balance:"+totalBalance);

        
        for (int i=0;i<tthreads.length;i++){
            tthreads[i]=new TransactionSimulationThread(acnts);
        }

        for (TransactionSimulationThread tthread : tthreads) {
            tthread.start();
        }
        
        for (TransactionSimulationThread tthread : tthreads) {
            try {
                tthread.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(TransferencesSim.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        totalBalance=0;
        for (Account acnt : acnts) {
            System.out.println("Balance for "+acnt+":"+acnt.getBalance());
            totalBalance += acnt.getBalance();
        }
        System.out.println("NEW Total balance:"+totalBalance);


        
    }
    
}

class TransactionSimulationThread extends Thread{
    
     Account[] acnts;

    public TransactionSimulationThread(Account[] acnts) {
        this.acnts = acnts;
    }
     
    public void run(){
        Random rnd=new Random(System.currentTimeMillis());
        for (int i=0;i<100;i++){
            int acnt1=Math.abs(rnd.nextInt()%(acnts.length));
            int acnt2=Math.abs(rnd.nextInt()%(acnts.length));
            //avoid self-transactions
            if (acnt1==acnt2){
                acnt1=(acnt1+1)%(acnts.length);
            }
            acnts[acnt1].transferFrom(acnts[acnt2], 500);
            
        }
    } 
    
    
}

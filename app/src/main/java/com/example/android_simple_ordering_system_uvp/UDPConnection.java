/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * @author Nicolás Penagos Montoya
 * nicolas.penagosm98@gmail.com
 **~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
package com.example.android_simple_ordering_system_uvp;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPConnection extends Thread {

    private DatagramSocket socket;
    private boolean kill;

    @Override
    public void run(){

        new Thread(

                ()->{

                    try {

                        socket = new DatagramSocket(6000);

                        kill = false;
                        while(!kill){

                            byte[] buffer = new byte[1024];

                            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                            socket.receive(packet);

                            Gson gson = new Gson();

                        }


                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

        ).start();

    }

    // -------------------------------------
    // Getters and setters
    // -------------------------------------
    public boolean isKill() {
        return kill;
    }

    public void setKill(boolean kill) {
        this.kill = kill;
    }
}

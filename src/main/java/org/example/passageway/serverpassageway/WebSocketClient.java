package org.example.passageway.serverpassageway;

/**
 * @author Liu
 */
public class WebSocketClient implements Runnable {
    private final String url;

    public WebSocketClient(String url){
        this.url = url;
    }

    @Override
    public void run() {
        new Client(this.url);
    }
}

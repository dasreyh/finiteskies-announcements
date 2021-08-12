package com.weirtz.announcements.announce;

import java.util.List;

public class ServerMessage {

    private List<String> sentences;
    private String name;

    public ServerMessage(String messageName, List<String> lines){
        this.name = messageName;
        this.sentences = lines;
    }

    public List<String> getSentences() {
        return sentences;
    }

    public String getName() {
        return name;
    }

}

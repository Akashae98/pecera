package com.mycompany.animacionpecera;

public class SpriteComponent extends Component{
    private final String path;
    public SpriteComponent(String path) {
        this.path = path;
    }
    public String getPath() {
        return path;
    }
}

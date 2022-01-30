package org.pjp.cag.directive;

public abstract class Directive {

    private final String type;

    public Directive(String type) {
        super();
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Directive [type=" + type + "]";
    }

}

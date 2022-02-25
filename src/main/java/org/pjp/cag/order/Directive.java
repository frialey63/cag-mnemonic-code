package org.pjp.cag.order;

/**
 * This abstract class represents the type for any Directive.
 * @author developer
 *
 */
public abstract class Directive {

    private final String type;

    /**
     * @param type The type
     */
    public Directive(String type) {
        super();
        this.type = type;
    }

    /**
     * @return The type of the directive
     */
    public String type() {
        return type;
    }

    @Override
    public String toString() {
        return "Directive [type=" + type + "]";
    }

}

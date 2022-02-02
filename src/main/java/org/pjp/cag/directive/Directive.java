package org.pjp.cag.directive;

/**
 * This abstract class represents the type for any directive.
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
    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Directive [type=" + type + "]";
    }

}
